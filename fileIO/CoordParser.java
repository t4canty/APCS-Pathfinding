package fileIO;

import java.io.IOException;
import java.util.StringTokenizer;

public class CoordParser extends ParseMethods {
	public CoordParser(String fileName, boolean debug, int index) throws IOException, IllegalCharacterException {
		if(debug) {System.out.println("CoordParser Constructor\nCoordParser FileReader");}
		this.debug = debug;
		f = new MapFileReader(fileName);
		if(debug) {System.out.println("CoordParser Parse()");}
		parseInit(index);
		parse(f, index);
		errorChecking();
	}
	protected void parse(MapFileReader f, int start) throws IllegalCharacterException {
		for(int i = start + 1; i <= height * width + start; i++) {
			StringTokenizer st = new StringTokenizer(f.getData(i), " ");
			String d = st.nextToken();
			if(!d.equals(".") && !d.equals("@") && !d.equals("K") && !d.equals("C") && !d.equals("|")) { throw new IllegalCharacterException("Encountered Illegal Char in file.");}
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(r < 0 || r > height || c < 0 || c > width) { throw new IllegalCharacterException("Error: Coordinates out of bounds");}
			MapPoint tmp = new MapPoint(d, c, r);
			if(debug) {System.out.println("Parsed " + tmp.toString() + "at line " + i);}
			locations.add(tmp);
			if(d.equals("K")) {startPos = tmp;}
		}
	}	
}
