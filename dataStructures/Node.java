package dataStructures;
public class Node <T> {
	public Node<T> nextNode;
	public Node<T> previousNode;
	private T data;
	/**
	 * Creates a completely empty node pointing to nothing. 
	 */
	public Node() {
		nextNode = null;
		previousNode = null;
	}
	/**
	 * Creates a node that points to two other nodes with data. 
	 * @param nextNode
	 * The next node in the linkedList.
	 * @param preNode
	 * The previous node in the linkedList.
	 * @param data
	 * Data to be passed into the node. 
	 */
	public Node(Node<T> nextNode, Node<T> preNode, T data) {
		this.nextNode = nextNode;
		previousNode = preNode;
		this.data = data;
	}
	/**
	 * Function to set the data in the node.
	 * @param data
	 * Data to put into the node.
	 */
	public void setData(T data) {
		this.data = data;
	}
	/**
	 * Returns the data in the node.
	 * @return
	 * Returns data stored in node. 
	 */
	public T getData() {return data;}
}
