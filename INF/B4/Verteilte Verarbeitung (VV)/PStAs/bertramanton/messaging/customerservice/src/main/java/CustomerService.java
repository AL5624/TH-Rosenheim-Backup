import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.*;


public class CustomerService implements Runnable {
  private final FirstName[] firstNameList = FirstName.values();
  private final LastName[] lastNameArrayList = LastName.values();
  private final Email[] emailArrayList = Email.values();
  private final TopLevelDomain[] topLevelDomainArrayList = TopLevelDomain.values();
  private int waitinigTimeBetweenOrders = 5;
  private final MyConnection myConnection;
  private final Logger customerServiceErrorsExceptions = Logger.getLogger(CustomerService.class.getName() + "_ErrorsExceptions");
  private final Logger customerServiceTestLogger = Logger.getLogger(CustomerService.class.getName() + "_TestLogger");
  private static Common common;


  public CustomerService(Common common) {
    loggerInitialization();
    CustomerService.common = common;
    myConnection = readConfigurationFile();
  }

  private MyConnection readConfigurationFile() {
    try {
      Path path = Paths.get("D:\\Eigene Dokumente\\Fh-Rosenheim\\TH-Rosenheim-Backup\\INF\\B4\\Verteilte Verarbeitung (VV)\\PStAs\\bertramanton\\messaging\\customerservice\\configuration.txt");
      List<String> list = Files.readAllLines(path, common.getENCODING());
      return common.myConnectionFromJson(list.get(0));
    } catch (IOException e) {
      customerServiceErrorsExceptions.log(Level.INFO, "Can't read Configuration File. No Connection.");
      System.exit(1);
    }
    return null;
  }

  private void loggerInitialization() {
    try {
      //file handler for error and exceptions
      Handler fileHandlerErrorExceptions = new FileHandler("CustomerService_ErrorExceptions.log");
      fileHandlerErrorExceptions.setFormatter(new SimpleFormatter());
      customerServiceErrorsExceptions.addHandler(fileHandlerErrorExceptions);

      Handler fileHandler_TestLogger = new FileHandler("CustomerService_TestLogger");
      fileHandler_TestLogger.setFormatter(new SimpleFormatter());
      customerServiceTestLogger.addHandler(fileHandler_TestLogger);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public Order randomOrderGenerator() {
    Random random = new Random();
    int firstNameOrdinal = Math.abs(random.nextInt() % 20);
    String firstName = firstNameList[(firstNameOrdinal)].toString();
    String salutation;
    if (firstNameOrdinal < 10) {
      salutation = "Herr";
    } else {
      salutation = "Frau";
    }

    String lastName = lastNameArrayList[Math.abs(random.nextInt() % 20)].toString();
    String firstLastName = firstName + " " + lastName;
    String email = firstName + lastName + Math.abs(random.nextInt() % 1000) + "@" + emailArrayList[Math.abs(random.nextInt() % 9)] + "."
        + topLevelDomainArrayList[Math.abs(random.nextInt() % 4)];

    Customer customer = new Customer(salutation, firstLastName, email);
    int orderAmount = 0;
    while (orderAmount == 0) {
      orderAmount = Math.abs(random.nextInt() % 2001);
    }

    return new Order(orderAmount, customer);
  }



  public Logger getCustomerServiceErrorsExceptions() {
    return customerServiceErrorsExceptions;
  }

  private enum FirstName {
    JONAS, LEON, LUCA, FINN, NOAH, LIAM, LIO, ELIAS, PAUL, JULIAN,
    MIA, EMMA, SOPHIE, LEONIE, EMILIA, MARIE, LINA, ANNA, LIA, LENA;

    @Override
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(super.toString().charAt(0));
      String result = super.toString().toLowerCase();
      stringBuilder.append(result);
      return stringBuilder.deleteCharAt(1).toString();
    }
  }

  private enum LastName {
    MUELLER, SCHMIDT, SCHNEIDER, FISCHER, MEYER, WEBER, HOFMANN, WAGNER, BECKER, SCHULZ,
    SCHAEFER, KOCH, BAUER, RICHTER, KLEIN, SCHROEDER, WOLF, NEUMANN, SCHWARZ, SCHMITZ;

    @Override
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(super.toString().charAt(0));
      String result = super.toString().toLowerCase();
      stringBuilder.append(result);
      return stringBuilder.deleteCharAt(1).toString();
    }
  }

  private enum Email {
    GMX, WEB, TONLINE, MAIL, YAHOO, GMAIL, OUTLOOK, AOL, FREENET;

    @Override
    public String toString() {
      return super.toString().toLowerCase();
    }
  }

  private enum TopLevelDomain {
    DE, ORG, NET, COM;

    @Override
    public String toString() {
      return super.toString().toLowerCase();
    }
  }

  public int startInterval(String[] args) {
    if (args != null && args.length > 0) {
      int i = common.parseInt(args[0]);
      if(i != 0) {
        return i;
      }
    }
    return waitinigTimeBetweenOrders;
  }

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      sendOrder();
      common.sleepThread(waitinigTimeBetweenOrders);
    }
  }

  public void sendOrder() {
    Order order = randomOrderGenerator();
    customerServiceTestLogger.log(Level.INFO, "Pushed order in OpenOrders: \n" + order);
    myConnection.pushOrder(common.getOPEN_ORDERS(), order);
  }

  public int whileLoop(ExecutorService exe, BufferedReader test) {
    String msg;
    int result = 0;
    try (InputStreamReader in = new InputStreamReader(System.in)) {
      BufferedReader stdin;
      if (test != null) {
        stdin = test;
      } else {
        stdin = new BufferedReader(in);
      }

      while ((msg = stdin.readLine()) != null) {
        if ("stop".equals(msg)) {
          common.sleepThread(1000);
          exe.shutdownNow();
          break;
        }
        result = common.parseInt(msg);
        if(result >= 500) {
          setWaitingTimeBetweenOrders(result);
          common.sleepThread(500);
        } else {
          customerServiceErrorsExceptions.log(Level.INFO, "Interval must be at least 500 ms");
        }
      }
    } catch (IOException e) {
      getCustomerServiceErrorsExceptions().log(Level.INFO, "InputStream failed", e);
    }
    return waitinigTimeBetweenOrders;
  }

  public void setWaitingTimeBetweenOrders(int waitinigTimeBetweenOrders) {
    this.waitinigTimeBetweenOrders = waitinigTimeBetweenOrders;
  }

  public int getWaitinigTimeBetweenOrders() {
    return waitinigTimeBetweenOrders;
  }

  public static void main(String[] args) {
    Common common = new Common();
    CustomerService customerService = new CustomerService(common);

    customerService.setWaitingTimeBetweenOrders(customerService.startInterval(args));

    Thread.setDefaultUncaughtExceptionHandler((t, e) ->
    {
      customerService.getCustomerServiceErrorsExceptions().log(Level.INFO, "Exception in Thread. Closing program.");
      System.exit(1);
    });

    ExecutorService exe = Executors.newCachedThreadPool();

    exe.execute(customerService);

    customerService.whileLoop(exe, null);

    System.exit(0);
  }
}

