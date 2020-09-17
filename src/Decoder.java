import java.util.*;
import java.io.*;

public class Decoder {
	public static HashMap<String, Integer> dictionary = new HashMap<String, Integer>(512);
	public HashMap<String, Integer> initializeDictionary() {
		//512 for 9 bit encoding
		
		
		//adds ASCII table (all characters w/ decimal values from 0-255)
		for(int i = 0; i < 256; i++) {
			dictionary.put(Character.toString((char) i), i);
		}
		
		return dictionary;
	}
	public void decodeFile(File encoded) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(encoded));
		ArrayList<Integer> codes=new ArrayList<Integer>();
		int temp;
		int c=0;
		String nums;
		while ((temp=br.read())!=-1){
			if (temp==32){
				codes.add(c=Integer.parseInt(nums));
				nums="";		
			}else{
				nums+=(char)temp;
			}
		}
		codes.add(c=Integer.parseInt(nums));
		br.close();
		String output="";
		initializeDictionary();
		for (int i=0;i<codes.size();i++){
			output+=dictionary.get(codes.get(i));
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter("decodedFile.txt"));
		bw.append(output);
		bw.close();
	}
}

