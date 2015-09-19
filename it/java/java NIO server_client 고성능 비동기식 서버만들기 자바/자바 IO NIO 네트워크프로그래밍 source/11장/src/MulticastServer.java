import java.io.*; 	
import java.net.*; 	
	
public class MulticastServer extends Thread { 	
  DatagramSocket socket = null; 	
  DatagramPacket packet = null;	
  InetAddress channel =null;	
  int port = 20001; 	
  String address = "239.0.0.1";	
  boolean onAir = true; 	
	
  public MulticastServer() throws IOException { 	
    super("��Ƽ�ɽ�Ʈ ��۱�"); 	
    socket = new DatagramSocket(port); 	
  } 	
	
  public void run() { 	
  	String msg = "��Ƽ �ɽ�Ʈ ����� �� �鸮�ó���?";
    byte[] b = new byte[100]; 	
    while (onAir) { 	
      try { 	
        b = msg.getBytes(); // ����Ʈ �迭�� ����	
        channel = InetAddress.getByName(address); 	
        packet = new DatagramPacket(b, b.length, channel, port); 	
        socket.send(packet);	
        try { 	
          sleep(500);	
          System.out.println("������Դϴ�.");	
        } catch (InterruptedException e) { } 	
      } catch (IOException e) { 	
          e.printStackTrace(); 	
      }  	
    } 	
    socket.close(); 	
  } 	
	
  public static void main(String[] args) throws java.io.IOException { 	
    new MulticastServer().start(); 	
  } 	
}	
