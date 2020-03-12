package fileIO;

import java.io.IOException;

public class MapParser extends ParseMethods{
	//==========Constructors==========//
	/**
	 * Parses the first room from a file.
	 * @param filename
	 * The name of the file to parse, needs to be an absolute path.
	 * @param index
	 * The index of which line to start parsing at.
	 * @param debug
	 * Sets the debug flag.
	 * @throws IOException
	 * Throws IOException on FileNotFound or other FileIO errors. 
	 * @throws IllegalCharacterException
	 * Throws IllegalCharacterException when parsing, if the map contains illegal characters. 
	 */
	public MapParser(String filename, boolean debug, int index) throws IOException, IllegalCharacterException {
		this.debug = debug;
		if(debug) {System.out.println("MapParser Constructor\nMapParser FileReader");}
		f = new MapFileReader(filename);
		if(debug) {System.out.println("MapParser Parse()");}
		parseInit(index);
		parse(f, index);
		if(debug) {System.out.println("MapParser errorCheck()");}
		errorChecking();
	}
	/**
	 * Parses the first room from a file.
	 * @param filename
	 * The name of the file to parse, needs to be an absolute path.
	 * @throws IOException
	 * Throws IOException on FileNotFound or other FileIO errors. 
	 * @throws IllegalCharacterException
	 * Throws IllegalCharacterException when parsing, if the map contains illegal characters. 
	 */
	public MapParser(String filename) throws IOException, IllegalCharacterException {
		this(filename, false, 0);
	}
	/**
	 * Parses the first room from a file.
	 * @param filename
	 * The name of the file to parse, needs to be an absolute path.
	 * @param debug
	 * Sets the debug flag.
	 * @throws IOException
	 * Throws IOException on FileNotFound or other FileIO errors. 
	 * @throws IllegalCharacterException
	 * Throws IllegalCharacterException when parsing, if the map contains illegal characters. 
	 */
	public MapParser(String filename, boolean debug) throws IOException, IllegalCharacterException {
		this(filename, true, 0);
		this.debug = debug;
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
	public MapParser(String filename, int index) throws IOException, IllegalCharacterException {
		this(filename, false, index);
	}
	//==========Parsing==========//
	protected void parse(MapFileReader f, int start) throws IllegalCharacterException {
		//Begin parsing at a given index, offset by one to account for header of map
		for(int i = start +1; i <= height + start; i++) {
			String s = f.getData(i);										//Starting tmp string to extract values from
			if(s.length() < width) {throw new IllegalCharacterException("Problem when parsing map: String was less than width of the map");}
			if(debug) {System.out.println("String at row" + i + " " + s);}
			for(int k = 0; k < width; k++) {
				String l = s.substring(k, k+1);							//Iterate over the string and extract each char
				if(debug) {System.out.println("char at row" + i + " and col " +k +" " + l);}
				if(!l.equals(".") && !l.equals("@") && !l.equals("K") && !l.equals("C") && !l.equals("|")) { throw new IllegalCharacterException("Encountered Illegal Char in file.");}
				else if(l.equals("K")) {
					startPos = new MapPoint(l, k, i-start);
					locations.add(startPos);
				}else if(l.equals("C")) {
					endPos = new MapPoint(l, k, i-start);
					locations.add(endPos);
				}
				else {locations.add(new MapPoint(l, k, i - start));} 	//If input is ok, add the char to the linkedList
			}
		}
	}
}
