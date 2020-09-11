import java.util.*;
import java.io.*;

public class Decoder {
	public void decodeFile(File encoded) throws IOException {
		BufferedReader br=new BufferedReader(encoded);
		ArrayList<Integer> codes=new ArrayList<Integer>;
		int temp;
		String nums;
		while ((temp=br.read())!=-1){
			if (!br.read().equals(" ")){
				nums+=(String)temp;
			}else{
				codes.add((int)nums);
				nums="";
			}
		}
		codes.add((int)nums);
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
