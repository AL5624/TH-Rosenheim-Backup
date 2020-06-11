import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class TestForApprovedOrdersQueue {
  public static void main(String[] args) {
    Common common = new Common();
    List<String> list = new ArrayList<>();
    list.add(common.getAPPROVED_ORDERS());
    MyConnection myConnection = new MyConnection(new ArrayList<>(), list);
    myConnection.configuration(common);
    myConnection.pullOrder();
  }
}

