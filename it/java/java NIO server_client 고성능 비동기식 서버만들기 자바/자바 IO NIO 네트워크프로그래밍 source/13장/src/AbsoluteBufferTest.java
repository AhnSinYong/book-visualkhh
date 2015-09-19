import java.nio.ByteBuffer;

public class AbsoluteBufferTest {
	
	public static void main(String[] args) throws Exception {
		// ũ�Ⱑ 10�� ByteBuffer �� ����. 
		ByteBuffer buf = ByteBuffer.allocate(10);
		System.out.print("Init Position : " + buf.position());
		System.out.print(", Init Limit : " + buf.limit());
		System.out.println(", Init Capacity : " + buf.capacity());
		
		buf.put(3, (byte) 3);
		buf.put(4, (byte) 4);
		buf.put(5, (byte) 5);
		// ��ġ�� �����ؼ� �� ��쿡�� position �� ������ �ʴ´�.
		System.out.println("Position : " + buf.position());
		
		// ���ۿ� �ִ� �����͸� �����. ���� position �� ������ �ʴ´�.
		System.out.println("Value : " + buf.get(3) + ", Position : " + buf.position());
		System.out.println("Value : " + buf.get(4) + ", Position : " + buf.position());
		System.out.println("Value : " + buf.get(5) + ", Position : " + buf.position());
		// position 9 ���� �ƹ����� ���� �ʾ����� �⺻������ 0�� �Էµ��� �� �� �ִ�.
		System.out.println("Value : " + buf.get(9) + ", Position : " + buf.position());
	} 
	
}
