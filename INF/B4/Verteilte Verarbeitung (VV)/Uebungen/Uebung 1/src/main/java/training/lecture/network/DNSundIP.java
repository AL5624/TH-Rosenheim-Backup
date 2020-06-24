package training.lecture.network;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DNSundIP {
  public static void main(String[] args) {
    try {
      InetAddress me = InetAddress.getLocalHost();
      System.out.println(me.getHostName());
      System.out.println(me.getHostAddress());
      InetAddress[] allMe = InetAddress.getAllByName(me.getHostName());
      for (InetAddress me2 : allMe) {
        System.out.println(me2.getHostAddress());
      }

      InetAddress th = InetAddress.getByName("www.th-rosenheim.de");
      System.out.println(th.getHostName());
      System.out.println(th.getHostAddress());
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }

  }
}
