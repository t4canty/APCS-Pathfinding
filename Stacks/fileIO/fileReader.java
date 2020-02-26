import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class fileReader {
	private boolean debug = false;
	private DoublyLinkedList<String> data = new DoublyLinkedList<String>();
	File f;
	BufferedReader br;
	public fileReader(String filename) throws IOException {
		if(debug) {System.out.println("File name:" + filename);}
		f = new File(filename);
		br = new BufferedReader(new FileReader(f));
		String line;
		int i = 0;
		while((line = br.readLine()) != null) {
			if(debug) {System.out.println("Read line " + i); i++;}
			data.add(line);
		}
		br.close();
	}
	public String getData(int index) {
		String s = data.goForward(index, data.PEEK, null);
		return s;
	}
	public int getSize() {
		return data.getSize();
		// TODO Auto-generated method stub
	}
}
