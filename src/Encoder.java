import java.io.*;
import java.util.*;

public class Encoder {

	public static HashMap<String, Integer> dictionary = new HashMap<String, Integer>(512);
	
	public ArrayList<Integer> encodeFile(String fileName) {
		return generateCodestream(fileName, initializeDictionary());
	}
	private HashMap<String, Integer> initializeDictionary() {
		//512 for 9 bit encoding
		
		
		//adds ASCII table (all characters w/ decimal values from 0-255)
		for(int i = 0; i < 256; i++) {
			dictionary.put(Character.toString((char) i), i);
		}
		
		return dictionary;
	}
	
	public ArrayList<Integer> generateCodestream(String fileToEncode, HashMap<String, Integer> dictionary) {
		ArrayList<Integer> codestream = new ArrayList<Integer>();
		String p = "";
		//change to 257 for EOF marker
		int dictionarySize = 256;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileToEncode)));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("encodedFile.txt")));
			
			while(br.ready()) {
				char c = (char) br.read();
				
				//if present in dictionary:
				if(dictionary.containsKey(p + c)) {
					//P = concat(P,C)
					p += c;
					
					//to cover last read
					if(br.ready() == false) {
						codestream.add(dictionary.get(p));
						bw.write(dictionary.get(p));
					}
				}
				//if not present in dictionary:
				else {
					//output the code word which denotes P to the codestream
					codestream.add(dictionary.get(p));
					bw.write(dictionary.get(p)+" ");

					//add the string concat(P,C) to the dictionary
					dictionary.put(p + c, dictionarySize++);
					
					//P = C
					p = Character.toString(c);
				}
				//ends the encoded values with indicated z, starts the dictionary,separates them with spaces
				bw.write("z");
				//writes dictionary into file, with : to separate the index from the string
				for (String key : dictionary.keySet())
				{
					bw.write(dictionary.get(key) + "$" + key.length() + "#"+key);
					
				}
			}
			
			
				
				
			
			br.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return codestream;
	}
	//tester
	public static void main(String[]args) throws IOException //runs it
	{
		Encoder en = new Encoder();
		BufferedReader br = new BufferedReader(new FileReader("LZW.txt"));
		int temp;
		String input="";
		while ((temp=br.read())!=-1){
			input+=(char)temp;
		}
		en.encodeFile(input);
		
	}
	
}
