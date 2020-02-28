public class mapPoint {
	//==========Variables==========//
	private int col;
	private int row;
	private String data;
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
	public mapPoint(String data, int col, int row) {
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
}
