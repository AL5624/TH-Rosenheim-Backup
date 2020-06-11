import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.*;

public class AccountingService {
  private static Common common;
  private final Logger accountingServiceErrorExceptions = Logger.getLogger(AccountingService.class.getName() + "_ErrorsExceptions");
  private final Logger accountingServiceTestLogger = Logger.getLogger(AccountingService.class.getName() + "_TestLogger");
  private final MyConnection myConnection;

  private void LoggerInitialization() {
    try {
      //file handler for error and exceptions
      Handler fileHandler_ErrorExceptions = new FileHandler("AccountingService_ErrorExceptions.log");
      fileHandler_ErrorExceptions.setFormatter(new SimpleFormatter());
      accountingServiceErrorExceptions.addHandler(fileHandler_ErrorExceptions);

      Handler fileHandler_TestLogger = new FileHandler("AccountingService_TestLogger");
      fileHandler_TestLogger.setFormatter(new SimpleFormatter());
      accountingServiceTestLogger.addHandler(fileHandler_TestLogger);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public AccountingService(Common common) {
    AccountingService.common = common;
    LoggerInitialization();
    myConnection = readConfigurationFile();
  }

  private MyConnection readConfigurationFile() {
    try {
      Path path = Paths.get("D:\\Eigene Dokumente\\Fh-Rosenheim\\TH-Rosenheim-Backup\\INF\\B4\\Verteilte Verarbeitung (VV)\\PStAs\\bertramanton\\messaging\\accountingservice\\configuration.txt");
      List<String> list = Files.readAllLines(path, common.getENCODING());
      return common.myConnectionFromJson(list.get(0));
    } catch (IOException e) {
      accountingServiceErrorExceptions.log(Level.INFO, "Can't read Configuration File. No Connection.");
      System.exit(1);
    }
    return null;
  }

  public void pushOrders() {
    Order order;
    try {
      order = common.getPullBlockingQueue().take();
      accountingServiceTestLogger.log(Level.INFO, "Pulled order from OpernOrders: \n" + order);
      if (order.getAmount() < 500) {
        order.setApprovedBy("Buchhaltung");
        myConnection.pushOrder(common.getAPPROVED_ORDERS(), order);
        accountingServiceTestLogger.log(Level.INFO, "Pushed order in ApprovedOrders: \n" + order);
      } else {
        myConnection.pushOrder(common.getNEEDS_APPROVAL(), order);
        accountingServiceTestLogger.log(Level.INFO, "Pushed order in NeedsApproval: \n" + order);
      }
    } catch (InterruptedException e) {
      accountingServiceErrorExceptions.log(Level.INFO, "take failed");
    }
  }

  public static void main(String[] args) {
    Common common = new Common();
    AccountingService accountingService = new AccountingService(common);
    accountingService.myConnection.pullOrder();
    while (true) {
      accountingService.pushOrders();
    }
  }
}
