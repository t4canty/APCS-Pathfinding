package dataStructures;
 public class FiFo<dataType> {
	private Object data[]; //array to hold data
	private int size; 
	private boolean debug = false;
	public FiFo() {
		data = new Object[50]; // creating array of size 50 to be resized later
		size = 0;
	}
	/**
	 * @param data
	 * Takes an array of data and passes it to the stack.
	 */
	public FiFo(dataType[] data) {
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
	 * Returns the first value of the stack.
	 */
	@SuppressWarnings("unchecked")
	public dataType peek() {
		if(size > 0) {return (dataType) data[0];}
		else {return null;}
	}
	/**
	 * @param index
	 * Index of data.
	 * @return
	 * Returns data at index.
	 */
	public dataType peek(int index) {
		if(index > size-1 || index < 0) {throw new IndexOutOfBoundsException();}
		if(size > 0) {return (dataType) data[index];}
		else {return null;}
	}
	/**
	 * Gets the value at the bottom of the stack and removes it. 
	 * Returns removed value.
	 */
	@SuppressWarnings("unchecked")
	public dataType pop() {
		//return null if array size is 0
		if(size > 0) {
			if(debug) {System.out.println("Attempting to grab last value");}
			dataType tmp = (dataType) data[0]; //grab first value
			data[0] = null; //set the first value to null
			if(debug) {System.out.println("setting last value to null");}
			size--; //decrease the size
			if(debug) {System.out.println("reducing size");}
			Object[] tmpArr = new Object[size]; //create a tmp array and copy the current values over
			if(debug) {System.out.println("creating new array");}
			for(int i = 1; i < data.length-1; i++) {
				if(debug) {System.out.println("copying data over at index " + i);}
				tmpArr[i-1] = data[i];
			}
			if(debug) {System.out.println("setting data to temp");}
			data = tmpArr;
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
		size++; //increase the size of the stack
		if(size > data.length-1) {  
			Object[] tmpArr = new Object[data.length * 2]; //create bigger array if needed
			for(int i = 0; i < data.length; i++) {tmpArr[i] = data[i];}//copy over data from previous
			data = tmpArr;
		}
		data[size-1] = val; //add pushed value
	}
}

