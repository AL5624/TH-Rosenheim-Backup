package training.lecture.network;

import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateTimeSender {
  public static final Logger LOGGER = Logger.getLogger("DateTimeService");

  public static void main(String[] args) {
    try (DatagramSocket peer = new DatagramSocket(1025);) {
      LOGGER.log(Level.INFO, "Starting Date Time Service");

      LocalDateTime time = LocalDateTime.now();
      DatagramPacket timePacket = new DatagramPacket(
          time.toString().getBytes(),
          time.toString().length(),
          InetAddress.getLocalHost(), 1026);

      peer.send(timePacket);
    } catch (IOException e) {
      LOGGER.log(Level.INFO, e.getMessage(), e);
    }

  }
}
