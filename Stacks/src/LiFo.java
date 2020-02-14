public class LiFo<dataType> {
	private Object data[]; //array to hold data
	private int size;
	public LiFo() {
		data = new Object[0]; // creating array of size 0 to be resized later
		size = data.length;
	}
	/**
	 * @param data
	 * Takes an array of data and passes it to the stack.
	 */
	public LiFo(dataType[] data) {
		this();
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
		for(Object t : data) {s += (dataType) t;}
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
			Object newArr[] = new Object[size-1]; //create smaller array
			for(int i = 0; i < newArr.length; i++) { newArr[i] = data[i];} //copy over data from previous array
			data = newArr;
			size = data.length;
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
		Object[] tmpArr = new Object[size+1]; //create big array
		for(int i = 0; i < data.length; i++) {tmpArr[i] = data[i];} //copy over data from previous
		tmpArr[tmpArr.length-1] = val; //add pushed value
		data = tmpArr;
		size = data.length;
	}
}
