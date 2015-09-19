import java.io.*;

public class FullBuffer {
	public static void main(String[] args){
		FullBuffer io = new FullBuffer();
		try {
			long startTime = System.currentTimeMillis();
			io.copy("c:/TestFile.doc", "c:/TestFile2.doc");              
			long endTime = System.currentTimeMillis();
			System.out.println("FullBuffer ó���ð� : " + (endTime - startTime) + " milli seconds" );
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

	public static void copy(String fileFrom, String fileTo) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(fileFrom);
			out = new FileOutputStream(fileTo);
			// ���� ũ�� ���.
			int availableLength = in.available();
			// ���� ũ�� ��ŭ�� byte[] ������ ���� ����.
			byte[] buffer = new byte[availableLength];
			// ���۷� ������ �о����.
			int bytedata = in.read(buffer);
			// ���ۿ� �о���� ������ ���Ϸ� ����.
			out.write(buffer);
		} finally {
			if (in != null)
				in.close();
			if (out !=null)
				out.close();
		}
	}           
}
