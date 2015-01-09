package fst.list;

public class LinkedList{
	public LLElement head,tail;
	public int length;
	
	public LinkedList(){
		head = null;
		tail = null;
		length = 0;
	}
	
	public LinkedList createList(){
		return new LinkedList();
	}
	
	public boolean isEmpty(){
		return (length==0);
	}
	
	public LLElement addToTail(Integer i){
		/*if(tail == null){
			// null, so nth in list yet
			LLElement tmp = new LLElement(i, null);
			head = tmp;
			tail = tmp;
		}else{
			// not null
			tail = new LLElement(i,null);
		}
		 == > Refactor
		*/
		
		LLElement prev_tail = tail;
		LLElement tmp = new LLElement(i, null);
		if(tail == null){
			// so the list is empty
			head = tmp;
			tail = tmp;
		}else{
			tail = tmp;
			prev_tail.next = tail;
		}
		
		length++;
		return tail;
	}
	
	public class LLElement{
		public LLElement next;
		public Integer value;
		
		public LLElement(Integer i, LLElement par_next){
			next = par_next;
			value = i;
		}
		
		// für carlo
		public LLElement(Integer i){
			next = null;
			value = i;
		}
	}
}
