import java.net.*;			
			
public class URLDecoderTest {			
			
	public static void main(String[] args) {		
		if(args.length != 1){	
			System.out.println("���� : jvaa URLDecoderTest ���ڵ��� ���ڿ�");
			System.exit(1);
		}	
		String decodeStr = URLDecoder.decode(args[0]);	
		System.out.println(args[0] + "===>" + decodeStr);	
	}		
}			
