import java.util.*;
import java.io.*;

public class Decoder {
	public void decodeFile(File encoded) throws IOException {
		BufferedReader br=new BufferedReader(encoded);
		ArrayList<Integer> codes=new ArrayList<Integer>;
		int temp;
		while ((temp=br.read())!=-1){
			codes.add(temp);
		}
		br.close();
		String output="";
		for (int i=0;i<codes.size();i++){
			output+=dictionary.get(codes.get(i));
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter());
		bw.append(output);
		bw.close();
	}
}
