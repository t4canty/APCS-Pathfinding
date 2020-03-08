package fileIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import dataStructures.DoublyLinkedList;
public class fileReader {
	//==========Variables==========//
	private ArrayList<String> data = new ArrayList<String>(); 
	private File f;
	private BufferedReader br;
	//==========Constructor==========//
	/**
	 * Creates a FileReader object, and read each line of the file into an ArrayList.
	 * @param debug
	 * Sets debug flag.
	 * @param filename
	 * Name of the file, must be an absolute path.
	 * @throws IOException
	 * Throws IOException when there is a problem reading the file.
	 */
	public fileReader(String filename, boolean debug) throws IOException {
		if(debug) {System.out.println("File name:" + filename);}
		f = new File(filename); 					//open a file object
		br = new BufferedReader(new FileReader(f)); //read the file to BufferedReader
		String line;
		int i = 0;
		while((line = br.readLine()) != null) {
			if(debug) {System.out.println("Read line " + i); i++;}
			data.add(line);							//read each line and put it into the DLL
		}
		br.close();									//Close the BR
	}
	/**
	 * Creates a FileReader object, and read each line of the file into an ArrayList.
	 * @param filename
	 * Name of the file, must be an absolute path.
	 * @throws IOException
	 * Throws IOException when there is a problem reading the file.
	 */
	public fileReader(String filename) throws IOException {
		this(filename, false);
	}
	/**
	 * Gets the data at an index, and returns the object.
	 * @param index
	 * Index of the line you want to read.
	 * @return
	 * Returns the string of the entire line.
	 */
	public String getData(int index) {
		String s = data.get(index); //get the data at index
		return s;
	}
	/**
	 * @return
	 * Returns size of the file.
	 */
	public int getSize() {
		return data.size();
	}
}
