package fst.list;

public class Main {

	public static void main(String[] args) {
		LinkedList tmp = new LinkedList();
		
		System.out.println(tmp.isEmpty());
		tmp.addToTail(1);
		tmp.addToTail(2);
		tmp.addToTail(3);
		tmp.displayList();

		tmp.copyNode(2,1);
		//System.out.println("head="+tmp.head.value+" tail="+tmp.tail.value);
		//System.out.println(tmp.length);
		tmp.displayList();
	}

}
