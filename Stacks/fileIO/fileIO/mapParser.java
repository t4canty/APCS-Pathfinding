package fileIO;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import dataStructures.DoublyLinkedList;

public class mapParser {
//==========Variables==========//
	private fileReader f; 														//File handler to read from map
	private boolean debug = false; 												//debug flag
	private int width, height, numRooms;
	ArrayList<mapPoint> locations = new ArrayList<mapPoint>(); 	//data structure to contain the coordinate objects
//==========Constructors==========//
	/**
	 * Parses the first room from a file.
	 * @param filename
	 * The name of the file to parse, needs to be an absolute path.
	 * @throws IOException
	 * Throws IOException on FileNotFound or other FileIO errors. 
	 * @throws IllegalCharacterException
	 * Throws IllegalCharacterException when parsing, if the map contains illegal characters. 
	 */
	public mapParser(String filename) throws IOException, IllegalCharacterException {
		this(filename, 0);
	}
	/**
	 * Parses the room at a given line, will throw an error if the passed in line is not the starting line. 
	 * @param filename 
	 * The name of the file to parse, needs to be an absolute path.
	 * @param index
	 * The index of which line to start parsing at.
	 * @throws IOException
	 * Throws IOException on FileNotFound or other FileIO errors.
	 * @throws IllegalCharacterException
	 * Throws IllegalCharacterException when parsing, if the map contains illegal characters. 
	 */
	public mapParser(String filename, int index) throws IOException, IllegalCharacterException {
		f = new fileReader(filename);
		parseInit(index);
		parse(f, index);
		errorChecking();
	}
//==========Parsing==========//
	private void parse(fileReader f, int start) throws IllegalCharacterException {
		//Begin parsing at a given index, offset by one to account for header of map
		for(int i = start +1; i <= height + start; i++) {
			String s = f.getData(i);										//Starting tmp string to extract values from
			if(debug) {System.out.println("String at row" + i + " " + s);}
				for(int k = 0; k < width; k++) {
					String l = s.substring(k, k+1);							//Iterate over the string and extract each char
					if(debug) {System.out.println("char at row" + i + " and col " +k +" " + l);}
					if(!l.equals(".") && !l.equals("@") && !l.equals("K") && !l.equals("C") && !l.equals("|")) { throw new IllegalCharacterException("Encountered Illegal Char in file.");}
					else {locations.add(new mapPoint(l, k, i - start));} 	//If input is ok, add the char to the linkedList
			}
		}
	}
	private void parseInit(int row) {
		StringTokenizer st = new StringTokenizer(f.getData(row), " "); 												//Tokenize the starting head
		try {																										//Parse the head into varibles, with error handling
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		numRooms = Integer.parseInt(st.nextToken());
		if(debug) {System.out.println("Width: " + width + " Height: " + height + " NumRooms: " + numRooms);}
		if(width <= 0 || width > f.getData(row+1).length() || height <= 0 || height > f.getSize() || numRooms < 0) //Error handling on the length of the string
			throw new IllegalArgumentException("Error parsing file: Illegal start line");
		}catch (Exception e) {
			System.err.println("Error in parsing init, error parsing width and height to memory");
			e.printStackTrace();
		}
	}
	private void errorChecking() throws IllegalCharacterException{
		int countK = 0, countC = 0;
		for(int i = 0; i < locations.size(); i++) {								//Check the DLL for Cs and Ks.
			String tmp = locations.get(i).getData();
			if(tmp.equals("K")) {countK++;}
			else if(tmp.equals("C")) {countC++;}
		}
		if(countC != 1 || countK != 1) {throw new IllegalCharacterException("Error when parsing: erroneous number of kirbies or cake. ");}
	}
	/**
	 * Re-parse the file at a different index.
	 * @param filename 
	 * The name of the file to parse, needs to be an absolute path.
	 * @param index
	 * The index of which line to start parsing at.
	 * @throws IOException
	 * Throws IOException on FileNotFound or other FileIO errors.
	 * @throws IllegalCharacterException
	 * Throws IllegalCharacterException when parsing, if the map contains illegal characters
	 */
	public void reParse(String filename, int index) throws IOException, IllegalCharacterException {
		locations = new ArrayList<mapPoint>();
		f = new fileReader(filename);
		parseInit(index);
		parse(f, index);
		errorChecking();
	}
//==========Getters==========//
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getRooms() {return numRooms;}
	public Dimension getBounds() {return new Dimension(width, height);}
	public mapPoint[] toArray() {
		mapPoint[] mA = new mapPoint[locations.size()];
		for(int i = 0; i < locations.size(); i++) { mA[i] = locations.get(i);}
		return mA;
	}
	public mapPoint get(int index) {return locations.get(index);}
}
