package kr.or.javacafe.client;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @(#)SaxHandler.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.14, 02/10/29
 * 
 * SaxHandler Ŭ������ NioClient Ŭ������ inner Ŭ������ ReceiveThread Ŭ��������
 * ������ ���� ���� ������ xml ����� �Ľ��ϴµ� ���ȴ�.
 * �̱��� ������ ����Ͽ� �� �Ѱ��� ��ü�� �����ؼ� �����ϵ��� �Ͽ���.
 */
public class SaxHandler extends DefaultHandler {
	
	private ArrayList list;
	
	private static SaxHandler instance = null;

	// *********** Singleton pattern ************
	public static SaxHandler getInstance() {
        if (instance == null) {
            instance = new SaxHandler();
        }

        return instance;
    }
    
	private SaxHandler() {
		list = new ArrayList();
	}
/*	
	public void startElement(String uri, 
	                           String localName, 
	                           String qName,
                               Attributes attributes)
                               throws SAXException {
                               
    	System.out.println("Element start = [" + qName + "]");	
    }
*/	
	public void characters(char[] ch, int start, int length) {
		String content = new String(ch, start, length);
		list.add(content);
		//System.out.println("Element content = [" + content + "]");
	}
/*	
	public void endElement(String uri,
	                         String localName,
	                         String qName)
	                         throws SAXException {
	                        
		System.out.println("Element end = [" + qName + "]");
	}
*/	
	public void clearSaxHandler() {
		list.clear();
	}
	
	public ArrayList getContents() {
		return list;
	}
	
}
