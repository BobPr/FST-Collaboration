package fst.list;

public class LinkedList {
	public LLElement head, tail;
	public int length;

	public LinkedList() {
		head = null;
		tail = null;
		length = 0;
	}

	public LinkedList createList() {
		return new LinkedList();
	}

	public boolean isEmpty() {
		return (length == 0);
	}

	public LLElement addToTail(Integer i) {
		/*
		 * if(tail == null){ // null, so nth in list yet LLElement tmp = new
		 * LLElement(i, null); head = tmp; tail = tmp; }else{ // not null tail =
		 * new LLElement(i,null); } == > Refactor
		 */

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
		public LLElement next;
		public Integer value;

		public LLElement(Integer i, LLElement par_next) {
			next = par_next;
			value = i;
		}

		// für carlo
		public LLElement(Integer i) {
			next = null;
			value = i;
		}

		@Override
		public String toString() {
			return value + "";
		}

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
}
