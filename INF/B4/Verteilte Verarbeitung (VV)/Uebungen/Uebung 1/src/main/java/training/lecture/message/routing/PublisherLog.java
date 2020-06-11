package training.lecture.message.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PublisherLog {

  public static final String EXCHANGE_NAME = "direct-log-exchange";

  public static void main(String[] args) {
    ConnectionFactory factory = new ConnectionFactory();

    try {
      factory.setUri("amqp://guest:guest@192.168.99.100:5672/%2F");
      factory.setConnectionTimeout(30000);
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel();
      channel.exchangeDeclare(EXCHANGE_NAME, "direct");

      for (int i = 0; i < 5000; i++) {
        String message = "Nachricht Nr. [" + i + "]";
        String severity = "info";
        if(i%3 ==0) {
          severity = "warning";
        } else if(i%7 == 0) {
          severity = "error";
        }
        channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
        System.out.println(" [" + severity + "] '" + message + "' sent");
        Thread.sleep(2000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
