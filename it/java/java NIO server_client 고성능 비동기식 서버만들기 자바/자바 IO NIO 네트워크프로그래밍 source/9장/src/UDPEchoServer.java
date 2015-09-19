import java.net.*;			
			
			
public class UDPEchoServer {			
			
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
				String msg = new String(receivePacket.getData(),0, receivePacket.getLength());	
				System.out.println("���۹��� ���ڿ� : " + msg );	
				if(msg.equals("quit"))	
					break;
				// ����	
				DatagramPacket sendPacket = new DatagramPacket(receivePacket.getData(), receivePacket.getData().length, receivePacket.getAddress(), receivePacket.getPort());	
				dsock.send(sendPacket);	
			} // while		
			System.out.println("UPDEchoServer�� �����մϴ�.");		
		}catch(Exception ex){			
			System.out.println(ex);		
		}finally{			
			if(dsock != null)		
				dsock.close();	
		}			
	} // main				
} // class					
