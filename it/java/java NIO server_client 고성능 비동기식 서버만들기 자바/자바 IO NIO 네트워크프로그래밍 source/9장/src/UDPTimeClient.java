import java.net.*;			
			
public class UDPTimeClient {			
			
	public static void main(String[] args) {		
		if(args.length != 2){	
			System.out.println("���� : java UDPEchoClient ip port");
			System.exit(1);
		}	
		String ip = args[0];	
		int port = 0;	
		try{	
			port = Integer.parseInt(args[1]);
		}catch(Exception ex){	
			System.out.println("port ��ȣ�� ���� ������ �Է��Ͽ� �ּ���.");
			System.exit(1);
		}		
		InetAddress inetaddr = null;		
		try {		
			inetaddr = InetAddress.getByName(ip);	
		} catch (UnknownHostException e) {		
			System.out.println("�߸��� �������̳� ip�Դϴ�.");	
			System.exit(1);	
		}		
		DatagramSocket dsock = null;		
		try{		
			dsock = new DatagramSocket();	
			String line = null;	
			// ����	
			DatagramPacket sendPacket = new DatagramPacket("".getBytes(), "".getBytes().length, inetaddr, port);	
			dsock.send(sendPacket);	
				
			byte[] buffer = new byte[200];	
			DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);	
			dsock.receive(receivePacket);	
			// ���� ��� ���.	
			String msg = new String(receivePacket.getData(), 0, receivePacket.getData().length);	
			System.out.println("������ ���� ���޹��� �ð� :" + msg.trim());	
		}catch(Exception ex){		
			System.out.println(ex);	
		}finally{		
			if(dsock != null)	
				dsock.close();
		}		
	} // main			
} // class				
