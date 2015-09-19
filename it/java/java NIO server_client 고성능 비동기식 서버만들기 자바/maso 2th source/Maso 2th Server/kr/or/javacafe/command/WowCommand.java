package kr.or.javacafe.command;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Vector;

import kr.or.javacafe.AdvancedNioServer;

/**
 * @(#)WowCommand.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.12, 02/10/25
 * 
 * WowCommand Ŭ������ Ŭ���̾�Ʈ�� ���� ��û�� �����ϴ� ������ ó���� �Ѵ�.
 * ����� �ܼ��� ��� �Ұ��� ���� Ŭ���̾�Ʈ�� ���� �޼��� �տ� "Wow~! o(^^o)(o^^)o :: " �� 
 * �ٿ��� ��ε�ĳ��Ʈ �ϵ��� �����س���. 
 */
public class WowCommand extends AbstractCommand {

	public WowCommand() {}
	
	public void execute(AdvancedNioServer server,
		                  ByteBuffer buffer,
		                  String msg)
		                  throws IOException {
		
		buffer.put(createResponse("Wow~! o(^^o)(o^^)o :: " + msg));
						  	
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
