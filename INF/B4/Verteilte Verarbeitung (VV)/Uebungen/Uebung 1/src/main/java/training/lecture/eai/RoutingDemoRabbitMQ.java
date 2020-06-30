package training.lecture.eai;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.rabbitmq.RabbitMQComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class RoutingDemoRabbitMQ {
  public static void main(String[] args) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri("amqp://guest:guest@192.168.99.100:5672/%2F");
    RabbitMQComponent component = getRabbitMQComponent(factory);

    CamelContext context = new DefaultCamelContext();
    context.addComponent("amqp", component);

    context.addRoutes(new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        from("file://data/inbox?noop=true")
            .log("Processing: ${header.CamelFileName}")
            .choice()
            .when(header("CamelFileName").endsWith(".json"))
            .log("JSON")
            .to("amqp://json?queue=jsonqueue")
            .when(header("CamelFileName").endsWith(".csv"))
            .log("CSV")
            .to("amqp://csv?queue=csvqueue")
            .otherwise()
            .log("OTHER")
            .to("amqp://other?queue=otherqueue")
            .end();
      }
    });

    context.start();
    Thread.currentThread().sleep(10000);
    context.stop();
  }

  public static RabbitMQComponent getRabbitMQComponent(ConnectionFactory factory) {
    RabbitMQComponent component = new RabbitMQComponent();
    component.setConnectionFactory(factory);
    component.setUsername("guest");
    component.setPassword("guest");
    component.setHostname("localhost");
    component.setPortNumber(5672);

    return component;
  }
}
