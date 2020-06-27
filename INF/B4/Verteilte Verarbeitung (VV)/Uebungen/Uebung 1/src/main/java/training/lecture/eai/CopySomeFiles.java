package training.lecture.eai;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CopySomeFiles {
  public static void main(String[] args) {

    try {
      CamelContext context = new DefaultCamelContext();

      context.addRoutes(
          new RouteBuilder() {
            @Override
            public void configure() throws Exception {
              from("file:data/inbox?noop=true")
                  .log("Nachricht: ${body}")
                  .to("file:data/outbox");
            }
          }
      );

      context.start();
      Thread.currentThread().sleep(10000);
      context.stop();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
