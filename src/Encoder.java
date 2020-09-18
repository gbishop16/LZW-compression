//imported all io and util assets rather than having several lines importing one at a time
import java.io.*;
import java.util.*;

public class Encoder {
	
	//made dictionary a public static variable within the Encoder class so that it can be accessed in the Decoder class to make decoding easier and more efficient
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
	
	private ArrayList<Integer> generateCodestream(String fileToEncode, HashMap<String, Integer> dictionary) {
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
			}
			
			br.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return codestream;
	}
}
