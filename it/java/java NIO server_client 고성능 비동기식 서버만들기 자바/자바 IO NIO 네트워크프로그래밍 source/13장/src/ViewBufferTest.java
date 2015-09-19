import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ViewBufferTest {
	public static void main(String[] args) {
		// ũ�Ⱑ 10�� ByteBuffer ����
		ByteBuffer buf = ByteBuffer.allocate(10);
		
		// �� ������ IntBuffer ����
		IntBuffer ib = buf.asIntBuffer();
		// �� ������ �ʱⰪ ���
		System.out.println("position=" + ib.position() + ", limit=" + ib.limit() + ", capacity=" + ib.capacity());
		// �� ���ۿ� ������ ����
		ib.put(1024).put(2048);
		// �� ������ ������ ���
		System.out.println("index_0=" + ib.get(0) + ", index_1=" + ib.get(1));
		
		// ���� ���۵� ����Ǿ����� Ȯ�� ���� ���
		while (buf.hasRemaining()) {
			System.out.print(buf.get() + " ");
		}
	}
}
