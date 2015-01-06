package fst.list;

public class LinkedList<Object> {
	private LLElement head,tail;
	private int length;
	
	public LinkedList(){
		head = null;
		tail = null;
		length = 0;
	}
	
	public LinkedList<Object> createList(){
		return new LinkedList<Object>();
	}
	
	public boolean isEmpty(){
		return (length==0);
	}
	
	class LLElement{
		private LLElement prev, next;
		private Object value;
		
		public LLElement(LLElement par_prev, LLElement par_next){
			prev = par_prev;
			next = par_next;
		}
	}
}
