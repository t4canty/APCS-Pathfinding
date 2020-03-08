package fileIO;

import java.awt.Point;

public class MapPoint implements Comparable<MapPoint>{
	//==========Variables==========//
	private int col;
	private int row;
	private String data;
	public boolean hasVisited = false;
	//==========Constructor==========//
	/**
	 * Constructs a MapPoint object, which is an object that contains the char at a given row and column. 
	 * @param data
	 * Character to store in the object.
	 * @param col
	 * Column index of the character
	 * @param row
	 * Row index of the character
	 */
	public MapPoint(String data, int col, int row) {
		this.col = col;
		this.row = row;
		this.data = data;
	}
	//==========Getters==========//
	/**
	 * @return
	 * Returns column index of object.
	 */
	public int getCol() {return col;}
	/**
	 * @return
	 * Returns the character stored in the object.
	 */
	public String getData() {return data;}
	/**
	 * @return
	 * Returns row index of object.
	 */
	public int getRow() {return row;}
	/**
	 * Returns string representation of the object. 
	 */
	public String toString() {return "" + col + "," + row + " '" + data + "'"; }
	/**
	 * sets data to new value.
	 * @param data
	 * Data to pass into the object.
	 */
	public void setData(String data) {
		this.data = data;
	}
	public double getDistance(Point c) {
		int y = row-c.y;
		int x = col-c.x;
		return Math.sqrt((x*x) + (y*y));
	}
	public Point toPoint() {
		return new Point(col, row);
	}

	@Override
	public int compareTo(MapPoint o) {
		int compareRow=o.getRow();
		return row-compareRow;
	}
}
