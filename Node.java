
public class Node {
	
	private String element;//cunku format Nodeun icinde tutulacak format  aix^d seklinde olmalidir.
	private Node next;
	
	public Node(String element){
		
		this.element=element;
		this.next=null;
		
	}
	
	public String getElement() {
		
		return this.element;
		
	}
	public Node getNext() {
		
		return this.next;
		
	}
	public void setElement(String element) {
		
		this.element=element;
		
	}
	public void setNext(Node n) {
		
		this.next=n;
		
	}
	
}

