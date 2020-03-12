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
	protected MapPoint endPos;
	//==========Parent Functions==========//
	/**
	 * A method designed to check for if there is more than one cake/Kirby.
	 * @throws IllegalCharacterException
	 * Throws IllegalChar when encounters more than one cake or Kirby.
	 */
	protected void errorChecking() throws IllegalCharacterException{
		int countK = 0, countC = 0;													//Tmp varible to check the number of cake/Kirby
		for(int i = 0; i < locations.size(); i++) {									//Check the ArrayList for Cs and Ks.
			String tmp = locations.get(i).getData();			
			if(tmp.equals("K")) {countK++;}
			else if(tmp.equals("C")) {countC++;}
		}
		if(countC != 1 || countK != 1) {throw new IllegalCharacterException("Error when parsing: erroneous number of kirbies or cake. ");}
	}
	/**
	 * Parses the first row of the map and passes the numbers into their respective variables.
	 * @param row
	 * What row to start parsing at.
	 */
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
		locations = new ArrayList<MapPoint>();						//Reset locations array
		f = new MapFileReader(filename);							//Reparse at new index.
		parseInit(index);
		parse(f, index);
		errorChecking();
	}
	/**
	 * Abstract method to parse the contents of a file into an ArrayList. Implemented by child classes.
	 * @param f
	 * Filereader to use.
	 * @param index
	 * Index to start parsing at.
	 * @throws IllegalCharacterException
	 * Throws IllegalChar when an illegal char is encountered in the file.
	 */
	protected abstract void parse(MapFileReader f, int index) throws IllegalCharacterException;
	//==========Getters==========//
	/**
	 * @return
	 * Returns the width of the parsed map.
	 */
	public int getWidth() {return width;}
	/**
	 * @return
	 * Returns the height of the parsed map.
	 */
	public int getHeight() {return height;}
	/**
	 * @return
	 * Returns the number of rooms in the parsed map.
	 */
	public int getRooms() {return numRooms;}
	/**
	 * @return
	 * Returns the map point of "K".
	 */
	public MapPoint getStartPos() {return startPos;}
	/**
	 * @return
	 * Returns the map point of "C".
	 */
	public MapPoint getEndPos() {return endPos;}
	/**
	 * @return
	 * Returns the dimensions of the parsed map.
	 */
	public Dimension getBounds() {return new Dimension(width, height);}
	/**
	 * @return
	 * Returns an array representation of the map.
	 */
	public MapPoint[] toArray() {
		MapPoint[] mA = new MapPoint[locations.size()];
		for(int i = 0; i < locations.size(); i++) { mA[i] = locations.get(i);}
		return mA;
	}
	/**
	 * Method to return data stored at a given index.
	 * @param index
	 * Index of data.
	 * @return
	 * Returns the MapPoint stored at index.
	 */
	public MapPoint get(int index) {return locations.get(index);}
	/**
	 * @return
	 * Returns a string representation of the parsed map.
	 */
	public String toString() {
		String s = "";
		for(MapPoint m : locations) {s += m.toString() + "\n";}
		return s;
	}
}
