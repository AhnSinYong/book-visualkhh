package kr.or.javacafe.command;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Vector;

import kr.or.javacafe.AdvancedNioServer;

/**
 * @(#)MessageCommand.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.12, 02/10/25
 * 
 * MessageCommand Ŭ������ Ŭ���̾�Ʈ�� ���� ��û�� �����ϴ� ������ ó���� �Ѵ�.
 * �ܼ��� Ŭ���̾�Ʈ�� ���� �޼����� ��ε�ĳ��Ʈ �Ѵ�.
 */
public class MessageCommand extends AbstractCommand {
	
	public MessageCommand() {}
	
	public void execute(AdvancedNioServer server, 
						  ByteBuffer buffer, 
						  String msg) 
						  throws IOException {
		
		buffer.put(createResponse(msg));
						  	
		Vector users = server.getUsers();
		int size = users.size();
		for (int i = 0; i < size; i++) {
			buffer.flip();
			buffer.rewind();
			SocketChannel sc = (SocketChannel) users.get(i);
				
			sc.write(buffer);			
		}
	}

}
