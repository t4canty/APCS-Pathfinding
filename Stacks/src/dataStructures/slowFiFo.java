package dataStructures;
public class slowFiFo<T> {
	private LiFo<T> stack1; //primary stack
	private LiFo<T> stack2; //aux stack
	public slowFiFo() {
		stack1 = new LiFo<T>();
		stack2 = new LiFo<T>();
	}
	/**
	 * @param data
	 * Takes an array of data and passes it to the stack.
	 */
	public slowFiFo(T[] data) {
		stack1 = new LiFo<T>(data);
		stack2 = new LiFo<T>();
	}
	/**
	 * Returns the size of the stack.
	 */
	public int size() {return stack1.size();}
	/**
	 * Returns string representation of the array.
	 */
	public String toString() {return stack1.toString();}
	/**
	 * Returns the top value of the stack.
	 */
	public T peek() {
		//pop removes an element each time, so push popped values over to aux stack until empty
		//while(stack1.peek() != null) {stack2.push(stack1.pop());}
		T tmp = stack1.peek(); //temporary value to return
		//copy back over the values from stack one
		//while(stack2.peek() != null) {stack1.push(stack2.pop());}
		return tmp; 
	}
	/**
	 * Gets the value at the top of the stack and removes it. 
	 * Returns removed value.
	 */
	public T pop() {
		//pop removes an element each time, so push popped values over to aux stack until empty
		T tmp = stack1.pop(); //temporary value to return
		//copy back over the values from stack one
		return tmp;
		
	}
	/**
	 * Adds a single value to the top of the stack.
	 * @param val 
	 *  The value to be added to the stack. 
	 */
	public void push(T val) {
		//pop removes an element each time, so push popped values over to aux stack until empty
		while(stack1.peek() != null) {stack2.push(stack1.pop());}
		stack2.push(val); //add the value to the stack
		//copy back over the values from stack one
		while(stack2.peek() != null) {stack1.push(stack2.pop());}
	}
}
