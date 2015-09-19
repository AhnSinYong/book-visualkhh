import java.net.*;			
import java.util.*;			
			
			
public class UDPTimeServer {			
			
	public static void main(String[] args) {		
		if(args.length != 1){	
			System.out.println("���� : java UDPEchoServer port");
			System.exit(1);
		}	
		int port = 0;	
		try{	
			port = Integer.parseInt(args[0]);
		}catch(Exception ex){	
			System.out.println("port ��ȣ�� ���� ������ �Է��Ͽ� �ּ���.");
			System.exit(1);		
		}			
		DatagramSocket dsock = null;			
		try{			
			System.out.println("���� �������Դϴ�.");		
			dsock = new DatagramSocket(port);		
			String line = null;		
			while(true){		
				// �ޱ�	
				byte[] buffer = new byte[1024];	
				DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);	
				dsock.receive(receivePacket);	
				java.text.SimpleDateFormat format	
					= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
				String sdate = format.format(new Date());	
				System.out.println(receivePacket.getAddress().getHostAddress() + " �� ����ð� " + sdate + " �� �����մϴ�.");	
				DatagramPacket sendPacket = new DatagramPacket(sdate.getBytes(), sdate.getBytes().length, receivePacket.getAddress(), receivePacket.getPort());	
				dsock.send(sendPacket);	
			} // while		
		}catch(Exception ex){			
			System.out.println(ex);		
		}finally{			
			if(dsock != null)		
				dsock.close();	
		}			
	} // main				
} // class					
