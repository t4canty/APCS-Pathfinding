package fileIO;

import java.io.IOException;
import java.util.StringTokenizer;

public class CoordParser extends ParseMethods {
	//==========Constructors==========//
	/**
	 * Constructs a coordinate-based parser for a coordinate-based map.
	 * @param fileName
	 * Name of the file to pass in.
	 * @param debug
	 * Sets the debug flag.
	 * @param index
	 * Index at witch to start parsing at.
	 * @throws IOException
	 * Throws IOException when there is a problem with the file.
	 * @throws IllegalCharacterException
	 * Throws IllegalChar when an illegal char is found in the map.
	 */
	public CoordParser(String fileName, boolean debug, int index) throws IOException, IllegalCharacterException {
		if(debug) {System.out.println("CoordParser Constructor\nCoordParser FileReader");}
		this.debug = debug;
		f = new MapFileReader(fileName);
		if(debug) {System.out.println("CoordParser Parse()");}
		parseInit(index);
		parse(f, index);
		errorChecking();
	}
	/**
	 * Constructs a coordinate-based parser for a coordinate-based map.
	 * @param fileName
	 * Name of the file to pass in.
	 * @throws IOException
	 * Throws IOException when there is a problem with the file.
	 * @throws IllegalCharacterException
	 * Throws IllegalChar when an illegal char is found in the map.
	 */
	public CoordParser(String fileName) throws IOException, IllegalCharacterException {
		this(fileName, false, 0);
	}
	/**
	 * Constructs a coordinate-based parser for a coordinate-based map.
	 * @param fileName
	 * Name of the file to pass in.
	 * @param debug
	 * Sets the debug flag.
	 * @throws IOException
	 * Throws IOException when there is a problem with the file.
	 * @throws IllegalCharacterException
	 * Throws IllegalChar when an illegal char is found in the map.
	 */
	public CoordParser(String fileName, boolean debug) throws IOException, IllegalCharacterException {
		this(fileName, debug, 0);
	}
	/**
	 * Constructs a coordinate-based parser for a coordinate-based map.
	 * @param fileName
	 * Name of the file to pass in.
	 * @param index
	 * Index at witch to start parsing at.
	 * @throws IOException
	 * Throws IOException when there is a problem with the file.
	 * @throws IllegalCharacterException
	 * Throws IllegalChar when an illegal char is found in the map.
	 */
	public CoordParser(String fileName, int index) throws IOException, IllegalCharacterException {
		this(fileName, false, index);
	}
	//==========Parsing==========//
	protected void parse(MapFileReader f, int start) throws IllegalCharacterException {
		for(int i = start + 1; i <= height * width + start; i++) {
			StringTokenizer st = new StringTokenizer(f.getData(i), " ");	//Sets up a stringTokenizer for the line in a file, and reads it char by char.
			try {															//Error Handling.
				String d = st.nextToken();
				if(!d.equals(".") && !d.equals("@") && !d.equals("K") && !d.equals("C") && !d.equals("|")) { throw new IllegalCharacterException("Encountered Illegal Char in file.");}
				int r = Integer.parseInt(st.nextToken());					
				int c = Integer.parseInt(st.nextToken());
				if(r < 0 || r > height || c < 0 || c > width) { throw new IllegalCharacterException("Error: Coordinates out of bounds");}
				MapPoint tmp = new MapPoint(d, c, r);						//create a mapPoint to be added to the arrayList
				if(debug) {System.out.println("Parsed " + tmp.toString() + "at line " + i);}
				locations.add(tmp);											
				if(d.equals("K")) {startPos = tmp;}							//if the point is the start, set startPos to it.
			} catch (Exception e) {
				System.err.println("Error when parsing, see below.");
				e.printStackTrace();
			}
		}
	}	
}
