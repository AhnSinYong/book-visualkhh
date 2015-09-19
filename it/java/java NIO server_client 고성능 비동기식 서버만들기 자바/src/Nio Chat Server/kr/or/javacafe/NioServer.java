package kr.or.javacafe;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Song Ji-Hoon.
 *
 * e-mail: johnleen@hanmail.net
 * JavaCafe: www.JavaCafe.or.kr
 * 
 * ���� �ּ��� �̷������� ����ϴ°� �ƴ����� ���ںе��� ���ظ� ���� ����
 * �ּ��� �ڼ��ϰ� �޾ҽ��ϴ�.
 */
public class NioServer {
	
	private static int PORT = 4567;
	
	private static Vector room = new Vector();
	
	// non-blocking �� ���� Selector, ServerSocketChannel
	private Selector selector;
	private ServerSocket serverSocket;
	private ServerSocketChannel serverSocketChannel;
	
	// log �� �ʿ��� ��ü��
	private static FileHandler fileHandler;
	private static Logger logger = Logger.getLogger("kr.or.javacafe");
	
	
	public NioServer() { initLog(); }
	
	public void initServer() {
		info("Server is initiate");
		
		try {	
			selector = Selector.open();
			
			serverSocketChannel = ServerSocketChannel.open(); 
			serverSocketChannel.configureBlocking(false);
			serverSocket = serverSocketChannel.socket(); 

			InetAddress ia = InetAddress.getLocalHost();
			InetSocketAddress isa = new InetSocketAddress(ia, PORT);
			serverSocket.bind(isa);

			// ServerSocketChannel �� Selector �� OP_ACCEPT �� �����.
			// OP_ACCEPT �� ��������Ƿ� ServerSocketChannel �� ��û�ϸ� SelectionKey �� OP_ACCEPT ���� ���� �ǰ�
			// ���� �Ʒ��� ���� key.isAcceptable() ���ǹ����� �����ؼ� ó���ϰ� ��.
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			log(Level.WARNING, "NioServer/initServer()", e);
		} 
	}
	
	public void startServer() {
		info("Server is start");
		try {
			while (true) {
				// �̺�Ʈ�� �߻��� SelectableChannel ���� �����ؼ� Selector ���� Selected key set �� ����. 
				int n = selector.select();			
				
				// ������ ����� Selected key set ���� SelectionKey ���� Iterator �� ���ʷ� �������.
				// ���⼭ ���������� �̷��� ������ key set �� Selector ������ private key ���� ���� �����ϰ� 
				// �����Ƿ� �ٸ� �����忡 ���� �� key set �� ������� �ʵ��� �ؾ��Ѵٴ� ���̴�.
				// �׷��� �ʴٸ� ��ġ �ʴ� ����� ���� �� ���̴�.
				Iterator it = selector.selectedKeys().iterator();
				
				// ���� key �� ���������� ������ ���� ��û�� ó���ϴ� �κ�.
				while (it.hasNext()) {
					SelectionKey key = (SelectionKey) it.next();
										
					// key �� OP_ACCEPT ��� true �� ��.
					// initServer() ���� ServerSocketChannel ������ Selector �� OP_ACCEPT �� ��������Ƿ�.
                    // ServerSocketChannel �� ������ Ŭ���̾�Ʈ��� �̰����� ó����.
					if (key.isAcceptable()) {
						// �־��� key �� ServerSocketChannel �� ����.
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel sc = server.accept();	

						// ������ Ŭ���̾�Ʈ SocketChannel �� read ���� �����.
						// ������ �� SocketChannel �� OP_READ �� key ���� ���� ��.
						boolean isRegist = registerChannel(selector, sc, SelectionKey.OP_READ);
						
						// chatting �� ���� static ���� ���� �濡 ������ ����ڸ� �߰���.
						if (isRegist) 
							room.addElement(sc);

						// �ܼ�â�� ���ڷ� �־��� ��Ʈ���� �ð��� �Բ� ������.
						info("Key is Acceptable");

					// OP_READ �� key ��� �̰����� ó����.
					} else if (key.isReadable()) {
						service(key);

						// �ܼ�â�� ���ڷ� �־��� ��Ʈ���� �ð��� �Բ� ������.
						info("Key is Readable");
					}
					// ��û�� ������ �����Ƿ� Iterator ���� Ű�� ������. 
					it.remove();
				}
			}      
		} catch (Exception e) {
			log(Level.WARNING, "NioServer/startServer()", e);
		} 
	}
	
	// ServerSocketChannel �� ����� Ŭ���̾�Ʈ�� SocketChannel �� read ���(OP_READ)�� ��Ͻ�Ű�� �޼ҵ�.
	// read ���θ� ��� ��Ų ������ ä�õ��� ���񽺿��� ������ ���� Ŭ���̾�Ʈ�� ��û�� �а� �׿� ���� �����
	// �м����� ������ ������ ���ָ� �ǹǷ� �ٸ� ���� Ŭ���̾�Ʈ�� ��� ��ų �ʿ䰡 ��� �̷��� ����.
	// ���� ��κ��� C/S ������ Ŭ���̾�Ʈ�� read ���θ� ����ϸ� ��.
	private boolean registerChannel(Selector selector, SelectableChannel sc, int ops) 
	  throws ClosedChannelException, IOException {
		boolean isSuccess = false;	
		// �־��� SocketChannel �� null �̸� �ַܼ� �˸��� �׳� ������. 
		// ���� ���� ���̰����� Ȥ�� �������� �𸣹Ƿ� ó������.
		if (sc == null) {
			info("Invalid Connection");
			return isSuccess;
		}
		// �־��� SocketChannel �� non-blocking ���� ����.
		sc.configureBlocking(false);

		// ���ڷ� �־��� selector �� pos �� SocketChannel �� �����.
		// ���� OP_READ �� OP_WRITE �ΰ��� ���۷��̼��� ����ϰ� �ʹٸ�
		// OP_READ | OP_WIRTE �� ops �κп� ������ ��.
		sc.register(selector, ops);
		
		isSuccess = true;
		return isSuccess;
	}
	
	// �־��� key �� SocketChannel �� �����ؼ� �޼����� �а� ������ ������ ���ִ� ���� �޼ҵ�.
	// ȿ���� ���ؼ� ���� �� �޼ҵ嵵 ���� ó�� ������ ������Ǯ�� �����ؾ���.
	// �̰��� ����ȣ���� ������ ����.
	private void service(SelectionKey key) throws IOException {
		// key �κ��� SocketChannel ����.
		SocketChannel sc = (SocketChannel) key.channel();

        int readCount = 0;

		try {
			// ByteBuffer �� ����.
			ByteBuffer buf = ByteBuffer.allocateDirect(4096);

			// ������ ���� ���۷� SocketChannel �� ���� ���� �����͸� ������.
			readCount = sc.read(buf);
			
			// ���� ���� ���� 0 ���� �۴ٸ� EOF �� ���޵� ���̹Ƿ� ������ ������.
			if (readCount < 0) {
				room.removeElement(sc);
				sc.close();
			}

			buf.flip();
			
			broadcast(buf);
			
			buf.clear();
						
        } catch (IOException e) {
        	info("Ŭ���̾�Ʈ�� ��ȭ���� �������ϴ�.");
        	// Error �޼����� �Ⱥ����ֱ� ���� �ּ�ó�� ����.
            //log(Level.WARNING, "NioServer/service()", e);
            try {
            	room.removeElement(sc);
                sc.close();
            } catch (IOException ignored) {
            }
        }		
	}
	
	// room �� �ִ� ��� Ŭ���̾�Ʈ���� ��ε�ĳ��Ʈ.
	private void broadcast(ByteBuffer buffer) throws IOException {		
		int size = room.size();
		for (int i = 0; i < size; i++) {
			// ���۸� �ٽ� �б� ����.
			buffer.rewind();
			SocketChannel sc = (SocketChannel) room.get(i);			
			sc.write(buffer);			
		}
	}	

	
	// PORT�� �����ϰ� ���� PORT �˾Ƴ��� setter/getter
	public int getPort() { return PORT; }
	public void setPort(int port) { this.PORT = port; }
		
    
	// �α� ���õ� ��ü���� �ʱ�ȭ�ϴ� �޼ҵ�.
    public void initLog() {
    	try {
			// �α׸� ���Ͽ� ����� ���� FileHandler �� ����.
    		fileHandler = new FileHandler("MyLog.txt");
    	} catch (IOException e) {}
    	
		// FileHandler �� �����.
        logger.addHandler(fileHandler);
		// Log Level �� ��� ����� �� �ְ� ��.
        logger.setLevel(Level.ALL);    
    }
    
	// �α׸� ����� �޼ҵ�.	
	public void log(Level level, String msg, Throwable error) {
		logger.log(level, msg, error);
	}
	
	// ���α׷� ���� �� ������ ��Ÿ�� �� �ִ� �޼ҵ�. 
	public void info(String msg) {
		logger.info(msg);
	}
	
	public static void main(String[] args) {
		NioServer server = new NioServer();
		// PORT �����ϰ� ������ ���⼭ ��������.
		// setPort(4000); 
		server.initServer();
		server.startServer();
	}
}
