import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.*;


public class MyConnection {
  private Channel channel;
  private static Common common;
  private final List<String> pushQueue;
  private final List<String> pullQueue;

  public MyConnection(List<String> pushQueueString, List<String> pullQueueString) {
    this.pushQueue = pushQueueString;
    this.pullQueue = pullQueueString;
  }

  public void configuration(Common common) {
    MyConnection.common = common;

    channelInitialisation();
    declareInitialisation();
  }

  public void channelInitialisation() {
    ConnectionFactory factory = new ConnectionFactory();
    try {
      factory.setUri("amqp://guest:guest@192.168.99.100:5672/%2F");
      factory.setConnectionTimeout(30000);
      Connection connection = factory.newConnection();
      channel = connection.createChannel();

      Thread.sleep(2000);
    } catch (Exception e) {
      common.getCommonErrorsExceptions().log(Level.INFO, "Failed to Connect to channel", e);
    }
  }

  public void declareInitialisation() {
    try {
      for (String myQueue : pushQueue) {
        if (common.getMyQueues().get(myQueue).equals(common.getFANOUT())) {
          channel.exchangeDeclare(myQueue, common.getFANOUT());
        } else if (common.getMyQueues().get(myQueue).equals(common.getQUEUE())) {
          channel.queueDeclare(myQueue, true, false, false, null);
        }
      }
      for (String myQueue : pullQueue) {
        if (common.getMyQueues().get(myQueue).equals(common.getFANOUT())) {
          channel.exchangeDeclare(myQueue, common.getFANOUT());
        } else if (common.getMyQueues().get(myQueue).equals(common.getQUEUE())) {
          channel.queueDeclare(myQueue, true, false, false, null);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void pushOrder(String myQueue, Order order) {
    if(!pushQueue.contains(myQueue)) {
      common.getCommonErrorsExceptions().log(Level.INFO, "You are not allowed to push into this queue");
      return;
    }
    if (channel == null) {
      channelInitialisation();
      declareInitialisation();
    }
    if (channel == null) {
      common.getCommonErrorsExceptions().log(Level.INFO, "Can't connect to queue.");
    } else {
      try {
        String message = common.orderToJason(order);
            if (common.getMyQueues().get(myQueue).equals(common.getFANOUT())) {
              channel.basicPublish(myQueue, "", null, message.getBytes());
            } else if (common.getMyQueues().get(myQueue).equals(common.getQUEUE())) {
              channel.basicPublish("", myQueue, null, message.getBytes());
            }
          common.sleepThread(2000);
      } catch (Exception e) {
        common.getCommonErrorsExceptions().log(Level.INFO, "channel.queueDeclare or channel.basicPublish failed", e);
      }
    }
  }

  public void pullOrder() {
    DeliverCallback deliverCallback = (consumerTag, message) -> {
      String orderJason = new String(message.getBody());
      try {
        Order order = common.orderFromJason(orderJason);
        common.getPullBlockingQueue().put(order);
        if(pullQueue.get(0).equals(common.getAPPROVED_ORDERS())) {
          common.getCommonErrorsExceptions().log(Level.INFO, "Pulled order from ApprovedOrders: \n" + order);
        }
      } catch (Exception e) {
        common.getCommonErrorsExceptions().log(Level.INFO, "channelOpenOrders.basicConsume failed");
      }
    };

    if (channel == null) {
      channelInitialisation();
      declareInitialisation();
    }
    if (channel == null) {
      common.getCommonErrorsExceptions().log(Level.INFO, "Can't connect to queue.");
    } else {
      try {
        for (String myQueue : pullQueue) {
          if (common.getMyQueues().get(myQueue).equals(common.getFANOUT())) {
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, myQueue, "");
            channel.basicConsume(queueName, true, deliverCallback, (consumerTag -> { }));
          } else if (common.getMyQueues().get(myQueue).equals(common.getQUEUE())) {
            channel.basicConsume(myQueue, true, deliverCallback, (consumerTag -> {
            }));
          }
          common.sleepThread(2000);
        }
      } catch (Exception e) {
        common.getCommonErrorsExceptions().log(Level.INFO, "channel.basicConsume or channel.queueBind failed");
      }
    }
  }


  @Override
  public String toString() {
    return "MyConnection{" +
        "channel=" + channel +
        ", pushQueueString=" + pushQueue +
        ", pullQueueString=" + pullQueue +
        ", pushQueue=" + pushQueue +
        ", pullQueue=" + pullQueue +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MyConnection that = (MyConnection) o;
    return Objects.equals(channel, that.channel) &&
        Objects.equals(pushQueue, that.pushQueue) &&
        Objects.equals(pullQueue, that.pullQueue) &&
        Objects.equals(pushQueue, that.pushQueue) &&
        Objects.equals(pullQueue, that.pullQueue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(channel, pushQueue, pullQueue, pushQueue, pullQueue);
  }

  public List<String> getPushQueue() {
    return pushQueue;
  }

  public List<String> getPullQueue() {
    return pullQueue;
  }
}
