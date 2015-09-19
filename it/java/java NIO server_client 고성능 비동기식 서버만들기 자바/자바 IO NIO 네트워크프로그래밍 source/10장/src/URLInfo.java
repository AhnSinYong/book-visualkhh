import java.net.*;			
			
public class URLInfo {			
			
	public static void main(String[] args) {		
		if(args.length != 1){	
			System.out.println("���� : java WebSpider URL");
			System.exit(1);
		}	
		URL url = null;	
		try {	
			url = new URL(args[0]);
		} catch (MalformedURLException e1) {	
			System.out.println("�߸��� URL �����Դϴ�.");
			System.out.println(e1);
			System.exit(1);
		}	
			
		System.out.println("�������� : " + url.getProtocol());	
		System.out.println("ȣ��Ʈ �̸� : " + url.getHost());	
		System.out.println("��Ʈ��ȣ : " + url.getPort());	
		System.out.println("�����̸� : " + url.getPath());	
		System.out.println("����� ���� : "+ url.getQuery());	
	} // main		
}			
