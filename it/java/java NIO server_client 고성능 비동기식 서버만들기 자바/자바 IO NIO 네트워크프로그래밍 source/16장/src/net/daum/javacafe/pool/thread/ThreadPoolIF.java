package net.daum.javacafe.pool.thread;

public interface ThreadPoolIF {
	
	public void addThread();
	public void removeThread();
	public void startAll();	
	public void stopAll();

}
