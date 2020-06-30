package training.lecture.eai;

import com.rabbitmq.client.ConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.rabbitmq.RabbitMQComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class RoutingDemoJsonPath {
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
            .filter(new Predicate() {
              @Override
              public boolean matches(Exchange exchange) {
                return (exchange.getIn().getHeader("CamelFileName", String.class).endsWith(".json"));
              }
            })
            .choice()
            .when().jsonpath("[?($.beruf=='Beruf')]")
            .to("amqp://Beruf?queue=berufqueue")
            .when().jsonpath("[?($.beruf=='Beruf2')]")
            .to("amqp://Beruf2?queue=beruf2queue")
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
