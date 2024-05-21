public class Stack {
	private Node head;
	
	public Stack() {
		head = new Node("");
		
	}
	
	public void push(String value) {
		Node temp = new Node(value);//Node olusturuluyor.(Nexti null.)
		
		temp.setNext(head.getNext());
		
		head.setNext(temp);
	}
	
	public String peek() {//top()
		if(head.getNext() != null)
			return head.getNext().getElement();
		else
			return "";
	}
	
	public String pop() {
		if(head.getNext() != null) {
			String temp = head.getNext().getElement();
			head.setNext(head.getNext().getNext());
			return temp;
		}else
			return "";
	}
	public boolean isEmpty() {
		if(head.getNext()==null) {
			
			return true;
			
		}else return false;
		
	}
	
}