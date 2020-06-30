package training.lecture.eai;

import com.google.gson.*;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.rabbitmq.RabbitMQComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class TransfromJson2Json {
  public static void main(String[] args) throws Exception {
    RabbitMQComponent component = initializeQueues();

    CamelContext context = new DefaultCamelContext();
    context.addComponent("amqp", component);

    context.addRoutes(new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        from("file://data/inbox?noop=true")
            .log("Dateiname: ${header.CamelFileName}")
            .filter((Exchange exc) -> {
              return exc.getIn().getHeader("CamelFileName").toString().endsWith(".json");
            })
            .process(new Processor() {
              @Override
              public void process(Exchange exchange) throws Exception {
                String body = exchange.getIn().getBody(String.class);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonObject bodyAsJson = gson.fromJson(body, JsonObject.class);

                JsonObject shortContent = new JsonObject();
                shortContent.addProperty("age", bodyAsJson.get("alter").getAsString());

                JsonArray positionen = bodyAsJson.getAsJsonArray("kinder");
                JsonArray positions = new JsonArray();
                shortContent.add("children", positions);

                for (JsonElement e : positionen) {
                  JsonObject obj = e.getAsJsonObject();
                  if (obj.get("alter").getAsInt() == 14) {
                    obj.remove("schulabschluss");
                    positions.add(obj);
                  }
                }


                String shortBody = gson.toJson(shortContent);
                exchange.getIn().setBody(shortBody);

                String fileName = exchange.getIn().getHeader("CamelFileName", String.class);
                String shortName = fileName.replaceAll(".json", "_short.json");
                exchange.getIn().setHeader("CamelFileName", shortName);
              }
            })
            .to("file://data/outbox");
      }
    });

    context.start();
    Thread.currentThread().sleep(10000);
    context.stop();

  }

  public static RabbitMQComponent initializeQueues() throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    RabbitMQComponent component = getRabbitMQComponent(factory);
    factory.setUri("amqp://guest:guest@192.168.99.100:5672/%2F");
    factory.setConnectionTimeout(30000);
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.exchangeDeclare("eingang", "direct", true);
    channel.queueDeclare("offene_bestellungen", true, false, false, null);
    channel.queueBind("offene_bestellungen", "eingang", "bestellung");
    return component;
  }

  public static RabbitMQComponent getRabbitMQComponent(ConnectionFactory factory) {
    RabbitMQComponent component = new RabbitMQComponent();
    component.setConnectionFactory(factory);
    component.setUsername("guest");
    component.setPassword("guest");
    component.setPortNumber(5672);

    return component;
  }
}
