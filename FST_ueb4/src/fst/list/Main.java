package fst.list;

public class Main {


	public static void main(String[] args) {
		LinkedList tmp = LinkedList.createList();
		
		System.out.println(tmp.isEmpty());
		tmp.addToTail(1);
		tmp.addToTail(2);
		tmp.addToTail(3);
		tmp.displayList();

		tmp.copyNode(2,0);
		
		tmp.displayList();

	}

}
