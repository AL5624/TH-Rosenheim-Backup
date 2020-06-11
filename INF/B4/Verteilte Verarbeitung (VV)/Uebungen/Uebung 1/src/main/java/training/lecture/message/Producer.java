package training.lecture.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

  public static final String WORK_QUEUE = "work-queue";

  public static void main(String[] args) {
    ConnectionFactory factory = new ConnectionFactory();

    try {
      factory.setUri("amqp://guest:guest@192.168.99.100:5672/%2F");
      factory.setConnectionTimeout(30000);
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel();
      channel.queueDeclare(WORK_QUEUE, true, false, false, null);

      for (int i = 1; i <= 5000; i++) {
        String message = "Nachricht Nr. [" + i + "]";
        channel.basicPublish("", WORK_QUEUE, null, message.getBytes());
        System.out.println(" [x] '" + message + "' sent");
        Thread.sleep(2000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }


  }
}
