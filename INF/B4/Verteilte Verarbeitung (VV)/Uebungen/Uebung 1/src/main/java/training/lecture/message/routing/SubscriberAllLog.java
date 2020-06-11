package training.lecture.message.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import training.lecture.message.PublisherLog;

public class SubscriberAllLog {
  public static void main(String[] args) {
    ConnectionFactory factory = new ConnectionFactory();

    try {
      factory.setUri("amqp://guest:guest@192.168.99.100:5672/%2F");
      factory.setConnectionTimeout(30000);
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel();

      channel.exchangeDeclare(training.lecture.message.routing.PublisherLog.EXCHANGE_NAME, "direct");
      String queueName = channel.queueDeclare().getQueue();
      channel.queueBind(queueName, training.lecture.message.routing.PublisherLog.EXCHANGE_NAME, "error");
      channel.queueBind(queueName, training.lecture.message.routing.PublisherLog.EXCHANGE_NAME, "warning");
      channel.queueBind(queueName, training.lecture.message.routing.PublisherLog.EXCHANGE_NAME, "info");

      System.out.println(" [*] Waiting for messages.");

      DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
      };

      boolean autoAcknowledge = true;
      channel.basicConsume(queueName, autoAcknowledge, deliverCallback, (consumerTag -> {
      }));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
