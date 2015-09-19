package kr.or.javacafe.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import kr.or.javacafe.AdvancedNioServer;

/**
 * @(#)AbstractCommand.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.02, 02/10/25
 * 
 * AbstractCommand Ŭ������ Ŭ���̾�Ʈ�� ����� ó���ϴ� ���� Ÿ���� �߻�ȭ�� Ŭ�����̴�.
 * �� Ŭ�������� Ŭ���̾�Ʈ�� ���� �޼����� ����� �޼ҵ�� �� �޼����� ����� ���� �ʵ常 ���ǵǾ� �ִ�.
 * Ŭ���̾�Ʈ���� �������� ����ó���� �� Ŭ������ ����� ���� Ŭ�������� �Ѵ�.
 */
public abstract class AbstractCommand {
	// data setting..
	private static final String HttpResponseHeader = "HTTP/1.1 200 OK\r\n\r\n";
	private static final String XmlStart = "<?xml version='1.0' encoding='UTF-8'?><response><message>";
	private static final String XmlEnd = "</message></response>";
	
	public AbstractCommand() {}
	
	public byte[] createResponse(String msg) throws UnsupportedEncodingException {
		StringBuffer message = new StringBuffer();
		message.append(HttpResponseHeader);
		message.append(XmlStart);
		message.append(msg);
		message.append(XmlEnd);
		return message.toString().getBytes("UTF-8");
	}
	
	public abstract void execute(AdvancedNioServer server, 
								    ByteBuffer writeBuffer, 
								    String msg) 
								    throws IOException;
	 
}
