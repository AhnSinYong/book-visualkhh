import java.io.*;

public class LineWriter {

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("���� : java LineWriter ���ϸ�");
			System.exit(0);
		}//if	
		
		BufferedReader br = null;
		PrintWriter pw = null;
		try{
			br = new BufferedReader(new InputStreamReader(System.in));
			pw = new PrintWriter(new BufferedWriter(new FileWriter(args[0])));
			String line = null;
			while((line = br.readLine()) != null){
			 	System.out.println("�о���� ���ڿ� :" + line);
				pw.println(line);	
			}
		}catch(Exception ex){
			System.out.println(ex);
		}finally{
			try{
				pw.close();
			}catch(Exception e){}
		}
	} // main
}
