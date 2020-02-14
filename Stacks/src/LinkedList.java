public class LinkedList<T> {
	/**
	 * @author Screencap
	 * https://github.com/t4canty
	 * Thanks to TJ178 for comment style
	 * https://github.com/TJ178
	 */
	private Node<T> head; 		//First node in list
	private Node<T> endNode; 	//Ending node of the list to save execution time. 
	private int size; 			//Size of list.
	/**
	 * Creates an empty linkedList. 
	 */
	public LinkedList() {
		head = new Node<T>(); 	//Empty head node
		endNode = head; 		//Sets end node to head
		size = 0;				//Sets size to 0
	}
	/**
	 * Creates a linkedList with data as the first element. 
	 * @param data
	 * Data to be passed into the list. 
	 */
	public LinkedList(T data) {
		head = new Node<T>(null, null, data); 	//Creates node with data 
		endNode = head;							//Sets end node to head
		size = 1;								//Sets size to 1
	}
	/**
	 * Returns the current size of the linkedList. 
	 * @return
	 * Size of list.
	 */
	public int size() {return size;}
	/**
	 * Adds data to the end of the list.
	 * @param data
	 * Data to add to the end of the list. 
	 */
	public void add(T data) {
		//If head is empty, don't bother creating a new node. 
		
		if(head.getData() == null) { 
			head.setData(data);
			endNode = head;
			size++;
		}
		//Create a new node, and link ending node to the new node. 
		else {
			Node<T> tmp = new Node<T>(null, null, data);
			endNode.nextNode = tmp;
			endNode = tmp;
			size++;
		}
	}
	/**
	 * Adds data to the front of the list. 
	 * @param data
	 */
	public void addFront(T data) {
		//If head is empty, don't bother creating a new node. 
		if(head.getData() == null) {
			head.setData(data); 
			size++;
			endNode = head;
		}
		//Create a node and link new node to head. 
		else {
			Node<T> tmp = new Node<T>(null, null, data);
			tmp.nextNode = head;
			head = tmp;
			size++;	
		}
	}
	/**
	 * Removes the last element in the list.
	 * @return
	 * Returns removed element's data.	
	 */
	public Node<T> remove() {
		if(size<= 0) {throw new IndexOutOfBoundsException();}
		Node<T> temp = head.nextNode; 	//Node in front of head
		Node<T> tmp = head; 			//Head will be moving, so tmp preserves head for the return.
		head = null;					//Destroy head
		head = temp; 					//Set head to node in front of head. 
		size--;  
		return tmp;
	}
	/**
	 * Removes element at index i, starting from the beginning of the list. 
	 * @param i
	 * Index of element to remove.
	 * @return
	 * Returns removed element's data.
	 */
	public Node<T> remove(int i) {
		if(i == 0) { return remove();}
		else if(size>0){
			Node<T> temp = head; 													//Set scrolling node to the beginning of the list. 
			if(i >= size || i < 0) { throw new IndexOutOfBoundsException();} 		//Throw exception if index is out of bounds
			//Scrolling function
			for(int k = 1; k < i; k++) {temp = temp.nextNode; }
			Node<T> tmp = temp.nextNode; 											//Temporary node to return
			temp.nextNode = temp.nextNode.nextNode; 								//Remove the element by unlinking it from the list, and relinking previous node to the node in front.
			size--;
			return tmp;
		}else {
			throw new IndexOutOfBoundsException();
		}
	}
	/**
	 * Returns current data stored at the end of the list.
	 * @return
	 */
	public Node<T> peek() {return head;}
	/**
	 * Returns a string representation of the list. 
	 */
	public String toString() {
		String s = "";
		Node<T> temp = head; 			//Scrolling node
		while(temp != null) { 			//Scroll through data and print it out. 
			s += temp.getData() + " ";
			temp = temp.nextNode;
		}
		return s;
	}
}