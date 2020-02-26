 public class LiFo<dataType> {
	private Object data[]; //array to hold data
	private int size;
	public LiFo() {
		data = new Object[50]; // creating array of size 50 to be resized later
		size = 0;
	}
	/**
	 * @param data
	 * Takes an array of data and passes it to the stack.
	 */
	public LiFo(dataType[] data) {
		this.data = data;
		size = data.length;
	}
	/**
	 * Returns the size of the stack.
	 */
	public int size() {return size;}
	/**
	 * Returns string representation of the array.
	 */
	@SuppressWarnings("unchecked")
	public String toString() {
		String s = "";
		for(int i = 0; i < size; i++) {s += (dataType) data[i];}
		return s;
	}
	/**
	 * Returns the top value of the stack.
	 */
	@SuppressWarnings("unchecked")
	public dataType peek() {
		if(size > 0) {return (dataType) data[size-1];}
		else {return null;}
	}
	/**
	 * Gets the value at the top of the stack and removes it. 
	 * Returns removed value.
	 */
	@SuppressWarnings("unchecked")
	public dataType pop() {
		//return null if array size is 0
		if(size > 0) {
			dataType tmp = (dataType) data[size-1]; //grab last value
			data[size-1] = null;
			size--;
			return tmp;
		}
		else {return null;}
	}
	/**
	 * Adds a single value to the top of the stack.
	 * @param val 
	 *  The value to be added to the stack. 
	 */
	public void push(dataType val) {
		size++;
		if(size > data.length-1) {  //copy over data from previous
			Object[] tmpArr = new Object[data.length * 2]; //create big array
			for(int i = 0; i < data.length; i++) {tmpArr[i] = data[i];}
			data = tmpArr;
		}
		data[size-1] = val; //add pushed value
	}
	@SuppressWarnings("unchecked")
	public void printString() {
		for(int i = 0; i < size; i++) {
			System.out.print((dataType) data[i]);
		}
	}
}
