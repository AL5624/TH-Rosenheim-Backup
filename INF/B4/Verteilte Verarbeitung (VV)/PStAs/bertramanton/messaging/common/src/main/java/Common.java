import com.google.gson.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.*;


public class Common {
  private final Charset ENCODING = StandardCharsets.UTF_8;
  private static Gson gson;
  private static final Logger commonErrorsExceptions = Logger.getLogger(Common.class.getName() + "_ErrorsExceptions");

  private final String FANOUT = "fanout";
  private final String QUEUE = "queue";

  private final String OPEN_ORDERS = "OpenOrders";
  private final String APPROVED_ORDERS = "ApprovedOrders";
  private final String NEEDS_APPROVAL = "NeedsApproval";
  private final String DECLINED_ORDERS = "DeclinedOrders";

  private final Map<String, String> myQueues = new HashMap<>();
  private static BlockingQueue<Order> pullBlockingQueue = new ArrayBlockingQueue<>(1000);

  public Common() {
    gsonInitialization();
    loggerInitialization();
    mapInitialisation();
  }

  //Initialization:

  private void loggerInitialization() {
    try {
      //file handler for error and exceptions
      Handler fileHandlerErrorExceptions = new FileHandler("Common_ErrorExceptions.log");
      fileHandlerErrorExceptions.setFormatter(new SimpleFormatter());
      commonErrorsExceptions.addHandler(fileHandlerErrorExceptions);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  private void gsonInitialization() {
    JsonSerializer<Path> pathJsonSerializer = (src, typeOfSrc, context) ->
    {
      if (src != null) {
        return new JsonPrimitive(src.toString());
      }
      return null;
    };

    JsonDeserializer<Path> pathJsonDeserializer = (json, typeOfT, context) ->
    {
      if (json != null) {
        return Paths.get(json.getAsString());
      }
      return null;
    };

    JsonSerializer<LocalDate> localDateJsonSerializer = (src, typeOfSrc, context) ->
    {
      if (src != null) {
        return new JsonPrimitive(src.toString());
      }
      return null;
    };

    JsonDeserializer<LocalDate> localDateJsonDeserializer = (json, typeOfT, context) ->
    {
      if (json != null) {
        return LocalDate.parse(json.getAsString());
      }
      return null;
    };

    gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, localDateJsonSerializer)
        .registerTypeHierarchyAdapter(Path.class, pathJsonSerializer)
        .registerTypeAdapter(LocalDate.class, localDateJsonDeserializer)
        .registerTypeHierarchyAdapter(Path.class, pathJsonDeserializer)
        .create();
  }

  private void mapInitialisation() {
    myQueues.put(OPEN_ORDERS, QUEUE);
    myQueues.put(APPROVED_ORDERS, FANOUT);
    myQueues.put(NEEDS_APPROVAL, QUEUE);
    myQueues.put(DECLINED_ORDERS, QUEUE);
  }

  //methods u can use:

  public void sleepThread(int sleep) {
    try {
      Thread.sleep(sleep);
    } catch (Exception e) {
      commonErrorsExceptions.log(Level.INFO, "Thread.sleep() failed", e);
    }
  }

  public int parseInt(String string) {
    int result = 0;

    try {
      result = Integer.parseInt(string);
    } catch (NumberFormatException e) {
      commonErrorsExceptions.log(Level.INFO, "please only numbers", e);
    }

    return result;
  }

  //fromJason & toJason

  public String orderToJason(Order order) {
    return gson.toJson(order, Order.class);
  }

  public Order orderFromJason(String json) {
    return gson.fromJson(json, Order.class);
  }

  public String myConnectionToJason(MyConnection myConnection) {
    return gson.toJson(myConnection, MyConnection.class);
  }

  public MyConnection myConnectionFromJson(String json) {
    MyConnection myConnection = gson.fromJson(json, MyConnection.class);
    myConnection.configuration(this);
    return myConnection;
  }

  //Getter & Setter:


  public Logger getCommonErrorsExceptions() {
    return commonErrorsExceptions;
  }

  public Charset getENCODING() {
    return ENCODING;
  }

  public BlockingQueue<Order> getPullBlockingQueue() {
    return pullBlockingQueue;
  }

  public String getFANOUT() {
    return FANOUT;
  }

  public String getQUEUE() {
    return QUEUE;
  }

  public String getOPEN_ORDERS() {
    return OPEN_ORDERS;
  }

  public String getAPPROVED_ORDERS() {
    return APPROVED_ORDERS;
  }

  public String getNEEDS_APPROVAL() {
    return NEEDS_APPROVAL;
  }

  public String getDECLINED_ORDERS() {
    return DECLINED_ORDERS;
  }

  public Map<String, String> getMyQueues() {
    return myQueues;
  }

  public static void main(String[] args) {
    //creating the configuration files:

    String path1 = "D:\\Eigene Dokumente\\Fh-Rosenheim\\TH-Rosenheim-Backup\\INF\\B4\\Verteilte Verarbeitung (VV)\\PStAs\\bertramanton\\messaging\\";
    String path2;
    String path3 = "\\configuration.txt";
    Common common = new Common();
    String json;
    List<String> pushQueue = new ArrayList<>();
    List<String> pullQueue = new ArrayList<>();
    File file;

    //Configuration data for CustomerService:

    path2 = "customerservice";
    pushQueue.add(common.getOPEN_ORDERS());
    MyConnection customerServiceConnection = new MyConnection(pushQueue, pullQueue);
    json = common.myConnectionToJason(customerServiceConnection);
    file = new File(path1 + path2 + path3);
    try {
      file.createNewFile();
      Thread.sleep(500);
      FileWriter myWriter = new FileWriter(path1 + path2 + path3);
      myWriter.write(json);
      myWriter.close();
      Thread.sleep(500);
    } catch (Exception e) {
      commonErrorsExceptions.log(Level.INFO, "\"file.createNewFile() error\"", e);
    }

    pushQueue.clear();
    pullQueue.clear();

    //Configuration data for AccountingService:

    path2 = "accountingservice";
    pullQueue.add(common.getOPEN_ORDERS());
    pushQueue.add(common.getNEEDS_APPROVAL());
    pushQueue.add(common.getAPPROVED_ORDERS());
    MyConnection accountingServiceMyConnection = new MyConnection(pushQueue, pullQueue);
    json = common.myConnectionToJason(accountingServiceMyConnection);
    file = new File(path1 + path2 + path3);
    try {
      file.createNewFile();
      Thread.sleep(500);
      FileWriter myWriter = new FileWriter(path1 + path2 + path3);
      myWriter.write(json);
      myWriter.close();
      Thread.sleep(500);
    } catch (Exception e) {
      commonErrorsExceptions.log(Level.INFO, "\"file.createNewFile() error\"", e);
    }

    pushQueue.clear();
    pullQueue.clear();

    //Configuration data for TeamLeadService:

    path2 = "teamleadservice";
    pullQueue.add(common.getNEEDS_APPROVAL());
    pushQueue.add(common.getDECLINED_ORDERS());
    pushQueue.add(common.getAPPROVED_ORDERS());
    MyConnection teamLeadServiceMyConnection = new MyConnection(pushQueue, pullQueue);
    json = common.myConnectionToJason(teamLeadServiceMyConnection);
    file = new File(path1 + path2 + path3);
    try {
      file.createNewFile();
      Thread.sleep(500);
      FileWriter myWriter = new FileWriter(path1 + path2 + path3);
      myWriter.write(json);
      myWriter.close();
      Thread.sleep(500);
    } catch (Exception e) {
      commonErrorsExceptions.log(Level.INFO, "\"file.createNewFile() error\"", e);
    }

    pushQueue.clear();
    pullQueue.clear();
  }
}
