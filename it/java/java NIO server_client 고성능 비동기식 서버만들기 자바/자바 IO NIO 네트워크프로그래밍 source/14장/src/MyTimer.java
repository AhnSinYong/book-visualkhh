public class MyTimer {
	protected  static final String filePath = "C:/src.zip";
	
	private static long startTime;
	private static long endTime;
	// ���� �ð��� ����
	protected static void start() {
		startTime = System.currentTimeMillis();
	}
        // ���� �ð��� ������ �� �Һ�� �ð��� ���
	protected static void end(String name) {
		endTime = System.currentTimeMillis();
		System.out.println("[ " + name + " Time : " + (endTime - startTime) + " ] ");
	}	
}
