import java.nio.ByteBuffer;

public class DuplicateTest {
	public static void main(String[] args) {
		// ũ�Ⱑ 10�� ByteBuffer ����
		ByteBuffer buf = ByteBuffer.allocate(10);
		// ������ position �ε����� ���� ���� ����
		buf.put((byte) 0).put((byte) 1).put((byte) 2).put((byte) 3).put((byte) 4)
		.put((byte) 5).put((byte) 6).put((byte) 7).put((byte) 8).put((byte) 9);
		// position �� 3���� ����
		buf.position(3);
		// limit �� 9�� ����
		buf.limit(9);
		// ���� position 3 �� ��ũ�ص�
		buf.mark();
		
		// ���� ������ ���纻 ����
		ByteBuffer buf2 = buf.duplicate();
		// ����� ������ position, limit, capacity�� ���
		System.out.println(
				"1) Position: " + buf2.position() 
				+ ", Limit: " + buf2.limit() 
				+ ", Capacity: " + buf2.capacity()
		);
		
		// position �� 7�� ����
		buf2.position(7);
		buf2.reset();
		System.out.println("reset() ȣ�� �� Position: " + buf2.position());
		
		// buf2 �� clear �� 
		buf2.clear();
		// clear �� ���� ����� ������ position, limit, capacity�� ���
		System.out.println(
				"2) Position: " + buf2.position() 
				+ ", Limit: " + buf2.limit() 
				+ ", Capacity: " + buf2.capacity());
		
		// ����� ������ ������ ���
		while (buf2.hasRemaining()) {
			System.out.print(buf2.get() + " ");
		}
		
		// ���� ���ۿ� 0��° ���� �ٲ�
		buf.put(0, (byte) 10);
		System.out.println("\n" + "=> buf �� 0 ���� 10 ���� �ٲ�");

		// ���� ���ۿ� ����� ���ۿ��� ���� ����Ǿ����� Ȯ�� ���� ��
		System.out.println("Original Buffer Value : " + buf.get(0));
		System.out.println("DuplicateBuffer Value : " + buf2.get(0));
		
		// ����� ���ۿ� 1��° ���� �ٲ�
		buf.put(1, (byte) 11);
		System.out.println("=> buf2 �� 1 ���� 12 �� �ٲ�");

		// ���� ���ۿ� ����� ���ۿ��� ���� ����Ǿ����� Ȯ�� ���� ��
		System.out.println("Original Buffer Value : " + buf.get(1));
		System.out.println("DuplicateBuffer Value : " + buf2.get(1));
	}
}
