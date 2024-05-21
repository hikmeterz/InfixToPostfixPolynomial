public class LinkedList {

	private Node head;
	//private long size;
	
	
	public LinkedList() {
		
		//size=0;
		head=null;
		
		
	}
	
	public void addLast(String element) {
		
		Node gez=head;
		
		Node n = new Node(element);
		
		if(gez==null) {
			
			n.setNext(null);
			head=n;
			
		}else{
			
			while(gez.getNext()!=null){
				gez=gez.getNext();
			}	
			
			gez.setNext(n);
			
			
		}
		
	}
	
	public Node getHead() {
		
		return this.head;
	}
	
	public void print() {
		Node iterator = head;
		
		while(iterator!= null) {
			System.out.print(iterator.getElement()+" ");
			iterator = iterator.getNext();
		}
		
		System.out.println("");
	
	}
}	