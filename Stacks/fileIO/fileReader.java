import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class fileReader {
	private DoublyLinkedList<String> data = new DoublyLinkedList<String>();
	File f;
	BufferedReader br;
	public fileReader(String filename) throws IOException {
		init(filename);
	}
	private void init(String filename) throws IOException {
		f = new File(filename);
		br = new BufferedReader(new FileReader(f));
		String line;
		while((line = br.readLine()) != null) {
			System.out.println("Read line");
			data.add(line);
		}
		br.close();
	}
	public String getData(int index) {
		System.out.println(data.getSize());
		String s = data.goForward(index, data.PEEK, null);
		return s;
	}
	public int getSize() {
		return data.getSize();
		// TODO Auto-generated method stub
	}
}