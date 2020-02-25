
public class mapPoint {
	private int col;
	private int row;
	private String data;
	public mapPoint(String data, int col, int row) {
		this.col = col;
		this.row = row;
		this.data = data;
	}
	public int getCol() {
		return col;
	}
	public String getData() {
		return data;
	}
	public int getRow() {
		return row;
	}
	public String toString() {
		return "" + col + "," + row + " '" + data + "'"; 
	}
}
