package training.lecture.message;

import com.rabbitmq.client.*;

import java.io.IOException;

public class SubscriberLog {
  public static void main(String[] args) {
    ConnectionFactory factory = new ConnectionFactory();

    try {
      factory.setUri("amqp://guest:guest@192.168.99.100:5672/%2F");
      factory.setConnectionTimeout(30000);
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel();

      channel.exchangeDeclare(PublisherLog.EXCHANGE_NAME, "fanout");
      String queueName = channel.queueDeclare().getQueue();
      channel.queueBind(queueName, PublisherLog.EXCHANGE_NAME, "");

      System.out.println(" [*] Waiting for messages.");

      DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
      };

      boolean autoAcknowledge = true;
      channel.basicConsume(queueName, autoAcknowledge, deliverCallback, (consumerTag -> { }));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
