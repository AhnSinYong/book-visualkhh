import java.nio.ByteBuffer;

public class RelativeBufferTest {
	
	public static void main(String[] args) throws Exception {
		// ũ�Ⱑ 10�� ByteBuffer �� ����. 
		ByteBuffer buf = ByteBuffer.allocate(10);
		System.out.print("Init Position : " + buf.position());
		System.out.print(", Init Limit : " + buf.limit());
		System.out.println(", Init Capacity : " + buf.capacity());
		
		// ���� position �� 0 �ε� �̰��� mark �ص�.
		buf.mark();
		// a, b c �� ������� ���ۿ� �ִ´�.
		buf.put((byte) 10).put((byte) 11).put((byte) 12);
		// mark �ص� 0 �ε����� position �� �ǵ���.
		buf.reset();
		
		// ���� position �� ���ۿ� �ִ� �����͸� �����.
		System.out.println("Value : " + buf.get() + ", Position : " + buf.position());
		System.out.println("Value : " + buf.get() + ", Position : " + buf.position());
		System.out.println("Value : " + buf.get() + ", Position : " + buf.position());
		// position 4 ���� �ƹ����� ���� �ʾ����� �⺻������ 0�� �Էµ��� �� �� �ִ�.
		System.out.println("Value : " + buf.get() + ", Position : " + buf.position());
	} 
	
}
