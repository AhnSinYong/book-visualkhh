import java.nio.ByteBuffer;

public class SliceTest {
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
		
		// ���� ������ �Ϻκ��� Slice�� ���� ����
		ByteBuffer buf2 = buf.slice();
		// Slice�� ������ position, limit, capacity�� ���
		System.out.println(
				"1) Position: " + buf2.position() 
				+ ", Limit: " + buf2.limit() 
				+ ", Capacity: " + buf2.capacity()
		);
		
		// Slice�� ������ ������ ���
		while (buf2.hasRemaining()) {
			System.out.print(buf2.get() + " ");
		}
		
		// ���� ���ۿ� 3��° ���� �ٲ�
		buf.put(3, (byte) 10);
		System.out.println("\n" + "=> buf �� 3 ���� 10 ���� �ٲ�");

		// ���� ���ۿ� Slice�� ���ۿ��� ���� ����Ǿ����� Ȯ�� ���� ��
		System.out.println("Original Buffer Value : " + buf.get(3));
		System.out.println("SliceBuffer Value : " + buf2.get(0));
		
		// ����� ���ۿ� 4��° ���� �ٲ�
		buf.put(4, (byte) 11);
		System.out.println("=> buf2 �� 4 ���� 12 �� �ٲ�");

		// ���� ���ۿ� Slice�� ���ۿ��� ���� ����Ǿ����� Ȯ�� ���� ��
		System.out.println("Original Buffer Value : " + buf.get(4));
		System.out.println("SliceBuffer Value : " + buf2.get(1));
	}
}
