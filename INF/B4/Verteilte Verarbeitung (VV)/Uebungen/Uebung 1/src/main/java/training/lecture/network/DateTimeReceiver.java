package training.lecture.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateTimeReceiver {
  public static final Logger LOGGER = Logger.getLogger("DateTimeService");

  public static void main(String[] args) {
    LOGGER.log(Level.INFO, "Starting Date Time Receiver");

    try (DatagramSocket peer = new DatagramSocket(1026)) {


      byte buffer[] = new byte[256];
      DatagramPacket fromService = new DatagramPacket(buffer, buffer.length);

      peer.receive(fromService);

      DatagramPacket toService = new DatagramPacket("ACK".getBytes(), "ACK".length(),
          InetAddress.getLocalHost(), 1025);
      peer.send(toService);

      LOGGER.log(Level.INFO, "Received: " + new String(fromService.getData()));
    } catch (IOException e) {
      LOGGER.log(Level.INFO, e.getMessage(), e);
    }
  }
}
