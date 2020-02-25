import java.awt.Dimension;
import java.io.IOException;

public class mapParser {
	private fileReader f;
	private int width, height;
	DoublyLinkedList<mapPoint> locations;
	public mapParser(String filename) throws IOException {
		f = new fileReader(filename);
		width = f.getData(0).length();
		height = f.getSize();
		parse(f);
	}
	private void parse(fileReader f) {
		for(int i = 0; i < height; i++) {
			String s = f.getData(i);
			for(int k = 0; k < s.length(); k++) {
				locations.add(new mapPoint(s.substring(k, k+1), k, i));
			}
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
