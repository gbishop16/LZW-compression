import java.util.*;
import java.io.*;

public class Decoder {
	public void decodeFile(File encoded) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(encoded));
		ArrayList<Integer> codes=new ArrayList<Integer>();
		int temp;
		int c=0;
		String nums;
		while ((temp=br.read())!=-1){
			if (temp=32){
				codes.add(c=Integer.parseInt(nums));
				nums="";		
			}else{
				nums+=(char)temp;
			}
		}
		codes.add(c=Integer.parseInt(nums));
		br.close();
		String output="";
		for (int i=0;i<codes.size();i++){
			output+=dictionary.get(codes.get(i));
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter("decodedFile.txt"));
		bw.append(output);
		bw.close();
	}
}
