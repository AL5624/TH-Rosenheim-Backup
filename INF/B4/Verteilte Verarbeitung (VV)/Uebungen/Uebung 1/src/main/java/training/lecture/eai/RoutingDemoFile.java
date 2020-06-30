package training.lecture.eai;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RoutingDemoFile {
  public static void main(String[] args) throws Exception {
    CamelContext context = new DefaultCamelContext();

    context.addRoutes(new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        from("file://data/inbox?noop=true")
            .log("Processing: ${header.CamelFileName}")
            .choice()
              .when(header("CamelFileName").endsWith(".json"))
              .to("file://data/outbox/json")
              .when(header("CamelFileName").endsWith(".csv"))
              .to("file://data/outbox/csv")
              .otherwise()
              .to("file://data/outbox/other")
            .end();
      }
    });

    context.start();
    Thread.currentThread().sleep(10000);
    context.stop();
  }
}
