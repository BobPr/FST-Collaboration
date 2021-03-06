package fst.list;

public class LinkedList {
	private LLElement head = null, tail = null;
	private int length = 0;

	private LinkedList() {}
	
	/**  create an empty list
	 * @author Bob Prevos
	 * @return
	 */
	public static LinkedList createList() {
		return new LinkedList();
	}
	/**   checks if the list is empty or not
	 * @author Bob Prevos
	 * @return
	 */
	public boolean isEmpty() {
		return (length == 0);
	}

	
	/**
	 * Adds a LLElement with Value at the first position to the LinkedList
	 * @author Carlo
	 * @param  value
	 */
	public void addToHead(int value){
		LLElement newHead=new LLElement(value);
		if(length==0){
			head = newHead;
			tail = newHead;
		}else{
			LLElement oldHead=head;
			head = newHead;
			head.next=oldHead;
			}
		length++;
	}
	
	/**  add an element at the end of the list
	 * @author Bob Prevos
	 * @param int i
	 * @return
	 */
	public LLElement addToTail(Integer i) {
		LLElement prev_tail = tail;
		LLElement tmp = new LLElement(i, null);
		if (tail == null) {
			// so the list is empty
			head = tmp;
			tail = tmp;
		} else {
			tail = tmp;
			prev_tail.next = tail;
		}

		length++;
		return tail;
	}


	public class LLElement {
		private LLElement next;
		private int value;

		public LLElement(Integer i, LLElement par_next) {
			next = par_next;
			value = i;
		}

		public LLElement(Integer i) {
			next = null;
			value = i;
		}

		@Override
		public String toString() {
			return value + "";
		}
		
		public void setNext(LLElement new_next){ next = new_next; }
		public LLElement getNext(){ return next;  }
		public void setValue(int i){ value = i; }
		public int getValue(){ return value; }
	}

	/**
	 * prints a representation of the list to the console
	 * 
	 * @return string that was printed
	 */
	public String displayList() {
		String output = this.toString();
		System.out.println(output);
		return output;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder("[");
		LLElement currElem = this.head;
		while (currElem != null) {
			output.append(currElem.value);
			if (currElem.next != null) {
				output.append(", ");
			}
			currElem = currElem.next;
		}
		output.append("]");
		return output.toString();
	}

	private LLElement getElement(int index) {
		LLElement currElem = this.head;
		int currIndex = 0;
		while (currElem != null) {
			if (currIndex++ == index) {
				return currElem;
			}
			currElem = currElem.next;
		}
		return null;
	}

	/**
	 * @param index
	 * @return value in the list at the given index
	 */
	public int valueOfIndexElement(int index) {
		if (index < 0 || index >= this.length) {
			throw new IndexOutOfBoundsException();
		}
		return getElement(index).value;
	}

	/**
	 * sorts a given list in ascending order using bubblesort
	 * 
	 * @return sorted list
	 */
	public LinkedList sortList() {
		for (int n = this.length; n > 1; n--) {
			for (int i = 0; i < n - 1; i++) {
				LLElement curr = getElement(i);
				LLElement next = curr.next;

				if (curr.value > next.value) {
					// swap curr and next

					if (next != tail) {
						curr.next = next.next;
					} else {
						tail = curr;
						tail.next = null;
					}

					if (curr != head) {
						getElement(i - 1).next = next;
					} else {
						head = next;
					}
					next.next = curr;
				}

			}
		}
		return this;
	}

	/**
	 * Searches for a given value in list that contain values in ascending order
	 * 
	 * @param value
	 *            to search for
	 * @return index for the given value
	 */
	public int binSearch(int value) {
		// test if the list is sorted in ascending order
		LLElement currElem = this.head;
		if(currElem == null){
			return -1;
		}
		while (currElem.next != null) {
			if (currElem.value > currElem.next.value) {
				throw new UnsupportedOperationException("Given list is not sorted in ascending order!");
			}
			currElem = currElem.next;
		}

		int middle;
		int left = 0;
		int right = this.length - 1;

		while (left <= right) {
			middle = left + ((right - left) / 2);
			int currValue = valueOfIndexElement(middle);
			if (currValue == value) {
				return middle;
			} else {
				if (currValue > value) {
					right = middle - 1;
				} else {
					left = middle + 1;
				}
			}
		}

		return -1;
	}
	/** return the number of elements in the list
	 * @author Bob Prevos
	 * @return
	 */
	public Integer length(){
		return length;
	}
	
	/**  delete element at index i
	 * @author Bob Prevos
	 * @param int i
	 * @return
	 */
	public boolean deleteElement(int i){
		if(length > 0){
			if(length == 1){
				// special case: head == tail !!
				head = null;
				tail = null;
				length = 0;
				return true;
			}
			// case: head != tail
			LLElement tmp = head, prev_tmp = null;
			int index = 0;
			while(tmp != null){
				if(index == i){
					// element at index found
					if(i == 0){
						// element is head
						head = head.next;
					}else if(tmp == tail){
						// element is tail
						tail = prev_tmp;
						tail.next = null;
					}else{
						// element is in the middle
						prev_tmp.next = tmp.next;
					}
					length--;
					return true;
				}
				prev_tmp = tmp;
				tmp = tmp.next;
				index++;
			}
		}
		return false;
	}
	
	/**  copies an element from index1 to index2,
	 * if index2 is HEAD, the copy will be added as new HEAD
	 * if index2 is TAIL, a copy of the list will be added at the end
	 * @author Bob Prevos
	 * @param int index1, index2
	 * @return
	 */
	public void copyNode(int index1, int index2){
		/*
		kopiert ein Element von index1 nach index2. Ist index2
		HEAD, so wird die Kopie als Kopfelement eingefügt, ist index2 TAIL,
		so wird die Kopie der Liste angehangen */
		
		// index2 is head, add element as head
		if(index2 == 0 && index1 >=0 && index1 <= (length-1)){
			// find elment of index1
			LLElement tmp = head;
			for(int i=0; i<index1; i++){
				tmp = tmp.getNext();
			}
			System.out.println(tmp.getValue());
			this.addToHead(tmp.getValue());
		}
		
		// index2 is tail, add a copy of the list at the end
		if(index2 == (length-1)){
			LLElement tmp = head;
			Integer index = 0;
			Integer start_length = length;
			while(index < start_length){
				addToTail(tmp.value);
				tmp = tmp.next;
				index++;
			}
			return;
		}
		
		// index2 is not head & not tail
		if( (index1 >= 0)  && (index1 <= (length-1)) && (index2 > 0) && (index2 < length-1)) {
			LLElement tmp = head, i1_ele = null, i2_ele = null;
			Integer index = 0;
			while(tmp != null){
				// find both elements
				if(index == index1){
					i1_ele = tmp;
				}
				if(index == index2){
					i2_ele = tmp;
				}
				tmp = tmp.next;
				index++;
			}
			// elements found?
			if((i1_ele != null) && (i2_ele != null)){
				i2_ele.value = i1_ele.value;
			}
		}
	}
	
	// needed for testing the LinkedList fully, 
	// example: testing for same value but different LLElements
	public LLElement getTail(){
		return tail;
	}
	
	public LLElement getHead(){
		return head;
	}

	
	/**
	 * Searches the LinkedList for the LLElement with the lowest index that has the given value
	 * @author Carlo
	 * @param  value
	 * @return index -1 if no Element with this value is found
	 */
	public int indexOfValue(int value) throws Exception{
		if(length==0){
			return -1;
		}		
		LLElement current = head;		
		if(current.value == value){
			return 0;
		}		
		int index = 1;
		while(index<length){		
			current=current.next;
			if(current.value==value){
				return index;			
			}
			index++;
		}
		return -1;
	}
	
	/**
	 * Gives out the value of the LLElement at Index index to the console 
	 * or a Message that there is no Element with the given index
	 * @author Carlo
	 * @param  index
	 * @return result String for testing
	 */
	public String displayNode(int index){
		String result;
		if(index>=length||index<0){
			result="There is no Element with Index="+index;
			System.out.println(result);
			return result;
		}
		LLElement current=getElement(index);
		result="The Element with Index="+index+" has the value "+current.value;
		System.out.println(result);
		return result;
	}
	
	/**
	 * deletes all Elements, List is empty after this method
	 * @author Carlo
	 * @param  value
	 */
	public void deleteList(){
		head=null;
		tail=null;
		length=0;
	}
	
	
	/**
	 * Adds an LLElement with Value at the first position to the LinkedList
	 * @author Carlo
	 * @param index Index of Element that will be changed
	 * @param  value New value of the Element at position index
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public void editNode(int index, int value){
		if(index>=length || index<0){
			throw new IndexOutOfBoundsException("Trying to edit node with index out of bounds");
		}
		LLElement current=getElement(index);
		current.value=value;
	}
	
	
	
}
