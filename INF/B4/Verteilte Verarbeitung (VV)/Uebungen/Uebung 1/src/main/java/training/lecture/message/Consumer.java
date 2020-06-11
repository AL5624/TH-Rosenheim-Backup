package training.lecture.message;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
  public static void main(String[] args) {
    ConnectionFactory factory = new ConnectionFactory();

    try {
      factory.setUri("amqp://guest:guest@192.168.99.100:5672/%2F");
      factory.setConnectionTimeout(30000);
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel();
      channel.queueDeclare(Producer.WORK_QUEUE, true, false, false, null);

      DeliverCallback deliverCallback = new DeliverCallback() {
        @Override
        public void handle(String consumerTag, Delivery message) throws IOException {
          String text = new String(message.getBody(), "UTF-8");
          try {
            System.out.println("[x] Received '" + text + "'");
            Thread.sleep(2000);
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
          } catch (Exception e) {
            Thread.currentThread().interrupt();
          } finally {
            System.out.println("[x] done");
          }
        }
      };

      boolean autoAcknowledge = false;
      channel.basicConsume(Producer.WORK_QUEUE, autoAcknowledge, deliverCallback, (consumerTag -> {
      }));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
