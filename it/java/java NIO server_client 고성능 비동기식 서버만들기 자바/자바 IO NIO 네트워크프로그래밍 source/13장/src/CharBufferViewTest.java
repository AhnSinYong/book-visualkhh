import java.nio.CharBuffer;

public class CharBufferViewTest {
	public static void main(String[] args) {
		CharBuffer buf = CharBuffer.wrap("hi~ ����!");
		while (buf.hasRemaining()) {
			System.out.println(buf.get());
		}
	}
}
