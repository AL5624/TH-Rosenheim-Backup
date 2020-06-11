import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.*;

public class TeamLeadService {
  private static Common common;
  private final Logger teamLeadServiceErrorExceptions = Logger.getLogger(TeamLeadService.class.getName() + "_ErrorsExceptions");
  private final Logger teamLeadServiceTestLogger = Logger.getLogger(TeamLeadService.class.getName() + "_TestLogger");
  private final MyConnection myConnection;

  public TeamLeadService(Common common) {
    TeamLeadService.common = common;
    LoggerInitialization();
    myConnection = readConfigurationFile();
  }

  private MyConnection readConfigurationFile() {
    try {
      Path path = Paths.get("D:\\Eigene Dokumente\\Fh-Rosenheim\\TH-Rosenheim-Backup\\INF\\B4\\Verteilte Verarbeitung (VV)\\PStAs\\bertramanton\\messaging\\teamleadservice\\configuration.txt");
      List<String> list = Files.readAllLines(path, common.getENCODING());
      return common.myConnectionFromJson(list.get(0));
    } catch (IOException e) {
      teamLeadServiceErrorExceptions.log(Level.INFO, "Can't read Configuration File. No Connection.");
      System.exit(1);
    }
    return null;
  }

  private void LoggerInitialization() {
    try {
      //file handler for error and exceptions
      Handler fileHandler_ErrorExceptions = new FileHandler("TeamLeadService_ErrorExceptions.log");
      fileHandler_ErrorExceptions.setFormatter(new SimpleFormatter());
      teamLeadServiceErrorExceptions.addHandler(fileHandler_ErrorExceptions);

      Handler fileHandler_TestLogger = new FileHandler("TeamLeadService_TestLogger");
      fileHandler_TestLogger.setFormatter(new SimpleFormatter());
      teamLeadServiceTestLogger.addHandler(fileHandler_TestLogger);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public boolean isApproved() {
    return Math.random() > 0.5;
  }

  public void pushOrders() {
    Order order;
    try {
      order = common.getPullBlockingQueue().take();
      teamLeadServiceTestLogger.log(Level.INFO, "Pulled order from NeedsApproval: \n" + order);
      if (isApproved()) {
        order.setApprovedBy("Teamleitung");
        myConnection.pushOrder(common.getAPPROVED_ORDERS(), order);
        teamLeadServiceTestLogger.log(Level.INFO, "Pushed Order in ApprovedOrders: \n" + order);
      } else {
        myConnection.pushOrder(common.getDECLINED_ORDERS(), order);
        teamLeadServiceTestLogger.log(Level.INFO, "Pushed Order in DeclinedOrders: \n" + order);
      }
    } catch (InterruptedException e) {
      teamLeadServiceErrorExceptions.log(Level.INFO, "take failed");
    }
  }

  public static void main(String[] args) {
    Common common = new Common();
    TeamLeadService teamLeadService = new TeamLeadService(common);
    teamLeadService.myConnection.pullOrder();
    while (true) {
      teamLeadService.pushOrders();
    }
  }
}
