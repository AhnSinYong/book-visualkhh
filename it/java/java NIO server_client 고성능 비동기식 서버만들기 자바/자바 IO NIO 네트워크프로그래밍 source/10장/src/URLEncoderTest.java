import java.net.*;			
			
public class URLEncoderTest {			
			
	public static void main(String[] args) {		
		if(args.length != 1){	
			System.out.println("���� : jvaa URLEncoderTest ���ڵ��� ���ڿ�");
			System.exit(1);
		}	
		String encodeStr = URLEncoder.encode(args[0]);	
		System.out.println(args[0] + "===>" + encodeStr);	
	}		
}			
