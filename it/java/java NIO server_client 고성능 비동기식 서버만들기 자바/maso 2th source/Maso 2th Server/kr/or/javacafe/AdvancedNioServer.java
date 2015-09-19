package kr.or.javacafe;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import kr.or.javacafe.command.AbstractCommand;
import kr.or.javacafe.pool.ByteBufferPool;
import kr.or.javacafe.pool.ThreadPool;
import kr.or.javacafe.pool.WorkerThread;

/**
 * @(#)AdvancedNioServer.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.34, 02/10/22
 * 
 * AdvancedNioServer Ŭ������ ���ڰ� ����� ������ ���� ���� �����ӿ�ũ�� �߽��� �Ǵ� Ŭ�������� ���� Ŭ������.
 * log �� ThreadPool ���� �ʿ��� ���ҽ����� �ʱ�ȭ�ϰ� Ŭ���̾�Ʈ�� ��� ��û�� �����ϴ� �ٽɺ��̴�.
 * 
 * ��縦 ���� ���� �ҽ��� ���� �ð��� �����Ͽ� ���� ������ ������ ����.
 * �ϴ� ��ü������ ���� ���̴� ������ �Ѱ����� ����ϸ� "&" �� "<" �� 
 * Ŭ���̾�Ʈ�� �޼����� ���Խ��Ѽ� ������ xml �Ľ̽ÿ� �ùٸ��� ���� ���� ������ �߻���Ű�� �ȴ�.
 * �̰��� �ذ��� ���ںе��� ������ ���ܵΰڴ�. �ذ� ����� ��Ʈ�� ���ڸ� ���� �� ���ڸ� �ٸ� ���·� 
 * ������Ѽ� ��û�� ������ �������� ����ÿ��� ���� �޼���(Ŭ���̾�Ʈ���� �ѷ��� ���� �޼��� ������) �κ��� 
 * �ٽ� �������ָ� �� ���̴�.
 */
public class AdvancedNioServer {
	
	private static int PORT = 4567;
	private static HashMap commandSet = new HashMap();
	private static Vector room = new Vector();
	private static FileHandler fileHandler;
	private static Logger logger = Logger.getLogger("kr.or.javacafe");

	private Selector selector;
	private ServerSocket serverSocket;
	private ServerSocketChannel serverSocketChannel;

	private ByteBufferPool bufferPool;
	private ThreadPool threadPool;

	public AdvancedNioServer() { initLog(); }
	
	public void initServer() {
		info("Server is initiate");
		
		try {
			File file = new File("C:\\temp.txt"); 
			bufferPool = new ByteBufferPool(1024*100, 1024*500, file); 
            threadPool = new ThreadPool(this);

            selector = Selector.open();
            
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false); 
			serverSocket = serverSocketChannel.socket(); 
			
			//InetAddress ia = InetAddress.getByName("218.235.126.118");
			InetAddress ia = InetAddress.getLocalHost(); 			 
			InetSocketAddress isa = new InetSocketAddress(ia, PORT);
			serverSocket.bind(isa);

			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			log(Level.WARNING, "NioServer/initServer()", e);
		} 
	}
	
	public void startServer() {
		info("Server is started..");
		try {
			while (true) {
				selector.select();
				
				Iterator it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey key = (SelectionKey) it.next();

					if (key.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel sc = server.accept();	
						registerChannel(selector, sc, SelectionKey.OP_READ);
						info("Ŭ���̾�Ʈ�� �����߽��ϴ�.");
					} else if (key.isReadable()) {
						service(key);
					}
					it.remove();
				}								
			}      
		} catch (Exception e) {
			log(Level.WARNING, "NioServer/startServer()", e);
		} 
	}
	
	private void registerChannel(Selector selector, SelectableChannel sc, int ops) 
	  throws ClosedChannelException, IOException {
		if (sc == null) {
			info("Invalid Connection");
			return;
		}
		sc.configureBlocking(false);
		sc.register(selector, ops);
		putUser(sc);
	}

	private void service(SelectionKey key) throws IOException {
		WorkerThread worker = threadPool.getThread();
		if (worker == null) {
			info("WorkerThread is null");
			do {
				threadPool.putThread(worker);
				worker = null;
				worker = threadPool.getThread();
			} while (worker == null);
		}
		worker.serviceChannel(key);
	}
	
	
	public boolean isContainCommand(String key) { return commandSet.containsValue(key); }
	public AbstractCommand getCommand(String key) { return (AbstractCommand) commandSet.get(key); }
	public void putCommand(String key, AbstractCommand command) { commandSet.put(key, command); }
	
	public Vector getUsers() { return room; }
	public void putUser(SelectableChannel sc) { room.add(sc); }
	public void removeUser(Object channel) { room.removeElement(channel); }
	
	public ByteBufferPool getByteBufferPool() { return bufferPool; }

	public int getPort() { return PORT; }
	public void setPort(int port) { this.PORT = port; }
	
	
	/////////////////////////////  log part   ///////////////////////////////////////	
    public void initLog() {
    	try {
    		fileHandler = new FileHandler("MyLog.txt");
    	} catch (IOException e) {}

        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL);    
    }

	public void log(Level level, String msg, Throwable error) {
		logger.log(level, msg, error);
	}

	public void info(String msg) {
		logger.info(msg);
	}
	
	
	//////////////////////////////   Main   /////////////////////////////////////
	public static void main(String[] args) {
		AdvancedNioServer server = new AdvancedNioServer();
		// PORT �����ϰ� ������ ���⼭ ��������.
		// setPort(4000); 
		server.initServer();
		server.startServer();
	}
}
