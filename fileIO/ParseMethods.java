package fileIO;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public abstract class ParseMethods {
	//==========Variables==========//
	protected MapFileReader f; 														//File handler to read from map
	protected boolean debug = false; 												//debug flag
	protected int width, height, numRooms;											//First 3 numbers at the start of the map
	protected MapPoint startPos;													//Stores the starting "K"
	protected ArrayList<MapPoint> locations = new ArrayList<MapPoint>(); 			//data structure to contain the coordinate objects
	//==========Parent Functions==========//
	protected void errorChecking() throws IllegalCharacterException{
		int countK = 0, countC = 0;
		for(int i = 0; i < locations.size(); i++) {								//Check the DLL for Cs and Ks.
			String tmp = locations.get(i).getData();
			if(tmp.equals("K")) {countK++;}
			else if(tmp.equals("C")) {countC++;}
		}
		if(countC != 1 || countK != 1) {throw new IllegalCharacterException("Error when parsing: erroneous number of kirbies or cake. ");}
	}
	protected void parseInit(int row) {
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
		locations = new ArrayList<MapPoint>();
		f = new MapFileReader(filename);
		parseInit(index);
		parse(f, index);
		errorChecking();
	}
	protected abstract void parse(MapFileReader f, int index) throws IllegalCharacterException;
	//==========Getters==========//
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getRooms() {return numRooms;}
	public MapPoint getStartPos() {return startPos;}
	public Dimension getBounds() {return new Dimension(width, height);}
	public MapPoint[] toArray() {
		MapPoint[] mA = new MapPoint[locations.size()];
		for(int i = 0; i < locations.size(); i++) { mA[i] = locations.get(i);}
		return mA;
	}
	public MapPoint get(int index) {return locations.get(index);}
	public String toString() {
		String s = "";
		for(MapPoint m : locations) {
			s += m.toString() + "\n";
		}
		return s;
	}
}
