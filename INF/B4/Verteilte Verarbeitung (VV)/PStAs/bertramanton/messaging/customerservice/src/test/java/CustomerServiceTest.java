import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class CustomerServiceTest {
/*  public static Common common = new Common();
  public static CustomerService customerService = new CustomerService(common);
  public Order actualOrder;*/

/*  @Test
  public void randomOrderGenerator() {
    Order order = customerService.randomOrderGenerator();
    for (int i = 0; i < 20; i++) {
      order = customerService.randomOrderGenerator();
    }
    boolean isCorrect = false;
    if (order.getApprovedBy() == null && order.getCustomer() != null && order.getAmount() > 0 && order.getAmount() <= 2000 && order.getCreateDate() != null
        && order.getOderId() == order.hashCode()) {
      isCorrect = true;
    }
    assertThat(isCorrect, equalTo(true));
  }*/

/*
  @Test
  public void sendOrders() {
    MyConnection myConnection = Mockito.mock(MyConnection.class);
    try {
      Mockito.doNothing().when(myConnection).pushOrder(any(Order.class));
    } catch (Exception e) {
      customerService.getCustomerServiceErrorsExceptions().log(Level.INFO, "myConnection.pushOrder() failed to mock", e);
    }

    customerService.sendOrders();
  }
*/

/*  @Test
  public void whileLoop() {
    ExecutorService exe = Executors.newCachedThreadPool();

    BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
    try {
      Mockito.when(bufferedReader.readLine()).thenReturn("abc", "6000", "stop");
    } catch (IOException e) {
      customerService.getCustomerServiceErrorsExceptions().log(Level.INFO, "bufferedReader.readLine() failed to mock", e);
    }

    int result = customerService.whileLoop(exe, bufferedReader);

    assertThat(result, equalTo(6000));
  }*/
/*
  @Test
  public void whileLoopNull() {
    ExecutorService exe = Executors.newCachedThreadPool();

    BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
    try {
      Mockito.when(bufferedReader.readLine()).thenReturn(null);
    } catch (IOException e) {
      customerService.getCustomerServiceErrorsExceptions().log(Level.INFO, "bufferedReader.readLine() failed", e);
    }

    int result = customerService.whileLoop(exe, bufferedReader);

    assertThat(result, equalTo(customerService.getWaitinigTimeBetweenOrders()));
  }*/

/*  @Test
  public void whileLoopWithoutBufferedReader() {
    ExecutorService exe = Executors.newCachedThreadPool();

    int result = customerService.whileLoop(exe, null);

    assertThat(result, equalTo(customerService.getWaitinigTimeBetweenOrders()));
  }

  @Test
  public void startTestWithStartTime() {
    String[] args = {"1"};

    assertThat(customerService.startTest(args), equalTo(1));
  }

  @Test
  public void startTestWithoutStartTime() {
    String[] args = null;

    assertThat(customerService.startTest(args), equalTo(5000));
  }

  @Test
  public void startTestWithStartTimeButWithNoNumber() {
    String[] args = {"test"};

    assertThat(customerService.startTest(args), equalTo(5000));
  }

  @Test
  public void startTestWithEmptyStartTime() {
    String[] args = {};

    assertThat(customerService.startTest(args), equalTo(0));
  }*/
}
