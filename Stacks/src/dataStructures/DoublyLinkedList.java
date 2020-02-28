package dataStructures;
public class DoublyLinkedList<T> {
	/**
	 * @author Screencap
	 * https://github.com/t4canty
	 */
	public final int PEEK = 1;
	public final int REMOVE = 2;
	public final int TOSTRING = 3;
	public final int ADDBEFORE = 4;
	public final int ADDAFTER = 5;
	private Node<T> startNode;
	private Node<T> endNode;
	private Node<T> head;
	private int size;
	/**
	 * Constructs a doubly linked list with a single data object.  
	 * @param data
	 * Data to be passed into the first element. 
	 */
	public DoublyLinkedList(T data) {
		head = new Node<T>(null, null, data);
		startNode = head;
		endNode = head;
		size = 1;
	}
	/**
	 * Constructs an empty doubly linked list.
	 */ 
	public DoublyLinkedList(){
		head = new Node<T>();
		startNode = null;
		endNode = null;
		size = 0;
	}
	/**
	 * Returns size.
	 * @return
	 * Returns size of Linked List. 
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Adds data to the beginning of the list, moving head backward.
	 * @param data
	 * Data to add to the list.
	 */
	public void addFront(T data) {
		if(head.getData() == null) {
			head.setData(data);
			startNode = head;
			endNode = head;
			size++;
		}
		else {
			head = startNode;
			Node<T> tmp = new Node<T>(head, null, data);
			head.previousNode = tmp;
			head = tmp;
			startNode = head;
		}
	}
	/**
	 * Adds data to the end of the list, moving head forward.
	 * @param data
	 * Data to add to the list.
	 */
	public void add(T data) {
		if(head.getData() == null) {
			head.setData(data);
			startNode = head;
			endNode = head;
			size++;
		}else {
		head = endNode;
		Node<T> tmp = new Node<T>(null, head, data);
		head.nextNode = tmp;
		head = tmp;
		endNode = head;
		size++;
		}
	}
	/**
	 * Adds data before the head node.
	 * @param data
	 * Data to be added. 
	 */
	public void addBefore(T data) {
		Node<T> newNode = new Node<T>(head, head.previousNode, data);
		if(head.previousNode != null){head.previousNode.nextNode = newNode;}
		head.previousNode = newNode;
		head = newNode;
		if(head.previousNode == null) {startNode = head;}
		size++;
	}
	/**
	 * Adds data after the head node. 
	 * @param data
	 * Data to be added.
	 */
	public void addAfter(T data) {
		if(head.getData() == null) {
			head.setData(data);
			startNode = head;
			endNode = head;
			size++;
		}else {
		Node<T> newNode = new Node<T>(head.nextNode, head, data);
		if(head.nextNode != null){head.nextNode.previousNode = newNode;}
		head.nextNode = newNode;
		head = newNode;
		if(head.nextNode == null) {endNode = head;}
		size++;
		}
	}
	/**
	 * Iterates over the data until Index, starting from the first node.
	 * <p>
	 * Valid commands:
	 * <p>
	 * PEEK: Reads element at index.
	 * <p>
	 * REMOVE: removes element at index and returns it.
	 * <p>
	 * TOSTRING: prints out all elements.
	 * <p>
	 * ADDBEFORE: Adds data before head node (1 before index).
	 * <p>
	 * ADDAFTER: Adds data after head node (1 after index).
	 * <p>
	 * @param index
	 * What index to stop execution at. 
	 * @param cmd
	 * What command to execute.
	 * @param data
	 * If adding data, the data to add to the list. 
	 * @return
	 * PEEK: returns the value at index.
	 * <p>
	 * REMOVE: returns the value removed at index.
	 * <p>
	 * TOSTRING: returns null.
	 * <p>
	 * ADDBEFORE: returns null.
	 * <p>
	 * ADDAFTER: returns null. 
	 * <p>
	 * @throws NullPointerException
	 * Throws NullPointer when head is null.
	 * @throws IndexOutOfBoundsException
	 * Throws IndexOutOfBoundsException if index is less than or greater than size of the list. 
	 */
	public T goForward(int index, int cmd, T data) throws NullPointerException,IndexOutOfBoundsException {
		if(head == null || startNode == null) {throw new NullPointerException();}
		head = startNode;
		if(index >= size || index < 0) {throw new IndexOutOfBoundsException();}
		if(cmd == TOSTRING) {
			for(int i = 0; i < index; i++) {
				System.out.println(head.getData().toString());
				head = head.nextNode;
			}
		}else {
			for(int i = 0; i < index; i++) {
				head = head.nextNode;
			}
		}
		T s = null;
		switch(cmd) {
		case PEEK:
			s = head.getData();
			break;
		case REMOVE:
			s = remove();
			break;
		case TOSTRING:
			break;
		case ADDAFTER:
			addAfter(data);
			break;
		case ADDBEFORE:
			addBefore(data);
			break;
		default:
			System.err.println("Invalid command");
		}	
		return s;
	}	
	/**
	 * Iterates over the data until Index, starting from the last node.
	 * <p>
	 * Valid commands:
	 * <p>
	 * PEEK: Reads element at index.
	 * <p>
	 * REMOVE: removes element at index and returns it.
	 * <p>
	 * TOSTRING: prints out all elements.
	 * <p>
	 * ADDBEFORE: Adds data before head node (1 before index).
	 * <p>
	 * ADDAFTER: Adds data after head node (1 after index).
	 * <p>
	 * @param index
	 * What index to stop execution at. 
	 * @param cmd
	 * What command to execute.
	 * @param data
	 * If adding data, the data to add to the list. 
	 * @return
	 * PEEK: returns the value at index.
	 * <p>
	 * REMOVE: returns the value removed at index.
	 * <p>
	 * TOSTRING: returns null.
	 * <p>
	 * ADDBEFORE: returns null.
	 * <p>
	 * ADDAFTER: returns null. 
	 * <p>
	 * @throws NullPointerException
	 * Throws NullPointer when head is null.
	 * @throws IndexOutOfBoundsException
	 * Throws IndexOutOfBoundsException if index is less than or greater than size of the list. 
	 */
	public T goBackward(int index, int cmd, T data) throws NullPointerException, IndexOutOfBoundsException {
		if(head == null || endNode == null) {throw new NullPointerException();}
		head = endNode;
		if(index >= size || index < 0) {throw new IndexOutOfBoundsException();}
		if(cmd == TOSTRING) {
			for(int i = 0; i <= index; i++) {
				System.out.println(head.getData().toString());
				head = head.previousNode;
			}
		}else {
			for(int i = 0; i <= index; i++) {
				head = head.previousNode;
			}
		}
		T s = null;
		switch(cmd) {
		case PEEK:
			s = head.getData();
			break;
		case REMOVE:
			s = remove();
			break;
		case TOSTRING:
			break;
		case ADDAFTER:
			addAfter(data);
			break;
		case ADDBEFORE:
			addBefore(data);
			break;
		default:
			System.err.println("Invalid command");
		}	
		return s;
	}
	/**
	 * Removes the element at head by unlinking it from the list, and Java garbage collection removes it from memory. Moves head forward.
	 * Returns removed data.
	 * @return
	 * Data contained at previous head element.
	 */
	public T remove() {
		if(size <= 0) {throw new IndexOutOfBoundsException();}
		if(head == endNode) {endNode = head.previousNode;}
		if(head == startNode) {startNode = head.nextNode;}
		if(head.nextNode != null) {head.nextNode.previousNode = head.previousNode;}
		if(head.previousNode != null) {head.previousNode.nextNode = head.nextNode;}
		T tmp = head.getData();
		head.setData(null);
		head = head.nextNode;
		size--;
		return tmp;
	}
	/**
	 * Removes element at index, and returns data contained within element. 
	 * @param index
	 * Index of node to remove.
	 * @return
	 * Data contained in node. 
	 */
	public T remove(int index) {
		if(index >= size/2) { return goBackward(index, REMOVE, null);}
		else { return goForward(index, REMOVE, null);}
	}
}