import java.awt.Dimension;
import java.io.IOException;
import java.util.StringTokenizer;

public class mapParser {
	private fileReader f;
	private boolean debug = false;
	private int width, height, numRooms;
	DoublyLinkedList<mapPoint> locations = new DoublyLinkedList<mapPoint>();
	public mapParser(String filename) throws IOException, IllegalCharacterException {
		this(filename, 0);
	}
	public mapParser(String filename, int index) throws IOException, IllegalCharacterException {
		f = new fileReader(filename);
		parseInit(index);
		parse(f, index);
	}
	private void parse(fileReader f, int start) throws IllegalCharacterException {
		for(int i = start +1; i <= height + start; i++) {
			String s = f.getData(i);
			if(debug) {System.out.println("String at row" + i + " " + s);}
				for(int k = 0; k < width; k++) {
					String l = s.substring(k, k+1);
					if(debug) {System.out.println("char at row" + i + " and col " +k +" " + l);}
					if(!l.equals(".") && !l.equals("@") && !l.equals("K") && !l.equals("C") && !l.equals("|")) { throw new IllegalCharacterException("Encountered Illegal Char in file.");}
					else {locations.add(new mapPoint(l, k, i - start));}
			}
		}
	}

	private void parseInit(int row) {
		StringTokenizer st = new StringTokenizer(f.getData(row), " ");
		try {
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		numRooms = Integer.parseInt(st.nextToken());
		if(debug) {System.out.println("Width: " + width + " Height: " + height + " NumRooms: " + numRooms);}
		if(width <= 0 || width > f.getData(row+1).length() || height <= 0 || height > f.getSize() || numRooms < 0)
			throw new IllegalArgumentException("Error parsing file: Illegal start line");
		}catch (Exception e) {
			System.err.println("Error in parsing init, error parsing width and height to memory");
			e.printStackTrace();
		}
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public Dimension getBounds() {
		return new Dimension(width, height);
	}
}
