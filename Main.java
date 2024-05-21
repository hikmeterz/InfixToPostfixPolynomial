import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		Scanner k = new Scanner(System.in);
		
		String polinom= k.nextLine();
		
		
		LinkedList list = new LinkedList();//Operandlari(polinomlari) linked liste ekle...Soldan saga.....
		
		int virgulindex=polinom.indexOf(',');
		
		int index_esittir=polinom.indexOf('=');
		double xDegeri;
		if(polinom.charAt(index_esittir+1)=='-') {
			xDegeri=Double.parseDouble(polinom.substring(polinom.indexOf('=')+1));
			
		}else		
			xDegeri=Double.parseDouble(polinom.substring(polinom.indexOf('=')+1));
		
		String operand="";
		for(int i=0;i<virgulindex;i++){
			
			operand+=""+polinom.charAt(i);
			if(operatorMu(operand.charAt(operand.length()-1)) || operand.charAt(operand.length()-1)=='(' ||  operand.charAt(operand.length()-1)==')' ) {
				operand=operand.substring(0,operand.length()-1);
				if(operand.length()!=0) {
					list.addLast(operand);
					operand="";
				}
			}
			
			
		}
		
		list.addLast(operand);
		
		
		
		Node iterator=list.getHead();
		
		
		
		LinkedList P = new LinkedList();//Postfix.... will not contain pharanthesis.
		Stack stack = new Stack();
		iterator=list.getHead();
		int index=0; 
		
		
		while(index<virgulindex){//bosluk_index girilen polinomun sonu.
			
			if(!(operatorMu(polinom.charAt(index)))&& polinom.charAt(index)!='(' && polinom.charAt(index)!=')' ){//an operand is found.....
				
				if(!iterator.getElement().equals("")) {
					P.addLast(iterator.getElement());//Add it to P.
					index+=iterator.getElement().length();//index operandin uzunlugu kadar articak.
					iterator=iterator.getNext();
				}
			}
			else if(polinom.charAt(index)=='(') {
				stack.push(""+polinom.charAt(index));
				index++;
			}else if(polinom.charAt(index)==')'){
				while(!(stack.isEmpty()) && !(stack.peek().equals("(") ) ) {
					String temp= stack.pop();
					P.addLast(temp);
					
				}						
				stack.pop();//Pop the left parenthesis from the stack and discard it
				index++;
			}else if(operatorMu(polinom.charAt(index))) {
				
				if(stack.isEmpty() || stack.peek().equals("(")) {
					
					stack.push(""+polinom.charAt(index));
					
				}else{
					while(!(stack.isEmpty())&& !(stack.peek().equals("(")) && oncelik(polinom.charAt(index),stack.peek().charAt(0))) {
						
						String temp=stack.pop();
						P.addLast(temp);
						
						
					}
					stack.push(""+polinom.charAt(index));
					
					
				}
				
				index++;
				
			}
			
			
		}
		
		while(!stack.isEmpty()) {
			
			String temp=stack.pop();
			P.addLast(temp);
			
		}
		
		//Postfix to evaluate the expression
		
		iterator=list.getHead();
		while(iterator!=null) {
			if(!iterator.getElement().equals("")){
				iterator.setElement(""+evaluate(iterator.getElement(),xDegeri));
				
			}	
			iterator=iterator.getNext();
		}
		
		
		iterator=P.getHead();
		Node iterator1=list.getHead();
		
		while(iterator!=null) {
			
			if(!operatorMu(iterator.getElement().charAt(0))){//operand ise
				
				iterator.setElement(""+iterator1.getElement());
				iterator1=iterator1.getNext();
				
			}
			iterator=iterator.getNext();
			
		}
		
		
		Stack stack1= new Stack();//Bu empty stacke ihtiyacimiz olucak cunku postfixe cevirdigimiz Pyi hesaplicaz.
		iterator=P.getHead();
		if(xDegeri>=0 ) {
			char a;
			while(iterator!=null){//we have not reached the end of P
				
				a=iterator.getElement().charAt(0);
				
				if(!operatorMu(a)) {//operand ise operator degil ise operanddir...
					
					stack1.push(iterator.getElement());
					
				}else if(operatorMu(a)) {//operator ise...
					String A=stack1.pop();
					String B=stack1.pop();
					double sonuc= evaluateop(Double.parseDouble(B),Double.parseDouble(A),a);
					stack1.push(""+sonuc);
					
				}	
				
				iterator=iterator.getNext();
			}
			double sonuc=Double.parseDouble(stack1.pop());
			
			System.out.printf("%.4f %n", sonuc);

		}else {
			char a;
			while(iterator!=null){//we have not reached the end of P
				
				if(iterator.getElement().length()>1)
					a=iterator.getElement().charAt(1);
				else
					a=iterator.getElement().charAt(0);
				if(!operatorMu(a)) {//operand ise operator degil ise operanddir...
					
					stack1.push(iterator.getElement());
					
				}else if(operatorMu(a)) {//operator ise...
					String A=stack1.pop();
					String B=stack1.pop();
					double sonuc= evaluateop(Double.parseDouble(B),Double.parseDouble(A),a);
					stack1.push(""+sonuc);
					
				}	
				
				iterator=iterator.getNext();
			}
			double sonuc=Double.parseDouble(stack1.pop());
			
			System.out.printf("%.4f %n", sonuc);
		}
	
	}
	public static double evaluateop(double x,double y,char a){
		
		
		if(a=='*') {
			
			return x*y;
			
		}else if(a=='-') {
			return x-y;
		
		}else if(a=='/') {
			return x/y;
			
		}else if(a=='+') {
			
			return x+y;
			
		}else if(a=='%') {
			
			return x%y;
			
		}
		
		return 0;
		
	}
	
	public static boolean operatorMu(char a){
		
		if(a=='+' ||a=='-' ||a=='*' ||a=='/' ||a=='%')
			return true;
		
		else
			return false;
		
	}
	public static boolean oncelik(char a,char b){
		
		//precedence a <= b
		
		if(( a=='+' || a=='-' ) && ( b=='-'  || b== '+')    ) //precedence is equal.
			return true;
		else if( (a=='*' || a=='/' || a=='%') && ( b=='-'  || b== '+')  )
			return false;
		else if( (a=='*' || a=='/' || a=='%') && ( b=='*' || b=='/' || b=='%')  )
			return true;
		else if( (a=='+' || a=='-' ) && ( b=='*' || b=='/'  || b=='%')  ) 
			return true;
		
		return false;
		
	}
	
	public static double evaluate(String Node,double xDegeri) {
		
		double sonuc=0;
		double katSayi=0;
		int us=0;
		
		
		if(Node.indexOf('x')>=0) {//Yani x degiskeni varsa......
			
			int indexX=Node.indexOf('x');
			int indexpower=Node.indexOf('^');//^ isaretinin indexi..
			
			if(indexX==0) {//Yani nodeun en basinda x varsa demek ki katsayisi 1.
				katSayi=1;
				if(indexpower>=0) {//Yani us isareti varsa....
					us=Integer.parseInt(Node.substring(indexpower+1));	
					return katSayi*Math.pow(xDegeri,us);
				}else {//yani us isareti yoksa...
					
					return katSayi*xDegeri;
					
				}
				
			}else{
				katSayi=Integer.parseInt(Node.substring(0,indexX));
				if(indexpower>=0) {
					us=Integer.parseInt(Node.substring(indexpower+1));
					return katSayi*Math.pow(xDegeri,us);
					
				}else {
					
					return katSayi*xDegeri;
					
					
				}
				
			}
			
		}else {
		
			int indexpower=Node.indexOf('^');
			if(indexpower>=0) {
				us=Integer.parseInt(Node.substring(indexpower+1));
				return Math.pow(Integer.parseInt(Node.substring(0,indexpower)),us);
				
			}else
				return (double)Integer.parseInt(Node);
			
			
		}
		
		
		
	}
	
	
	
	
}
