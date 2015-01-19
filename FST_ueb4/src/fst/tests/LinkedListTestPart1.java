package fst.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import fst.list.LinkedList;
import fst.list.LinkedList.LLElement;

@RunWith(Theories.class)
public class LinkedListTestPart1 extends LinkedListTest {

	// fix to test each function with an empty, one element and two element list
	// without making the changes on the lists persistent after tests
	// so every method with @Theory and int x as param, will get called 3 times
	public static @DataPoints
	int[] candidates = { 1, 2, 3 };

	/*
	 * ##############################################################################
	 * 								addToTail() Testing
	 * @author Bob Prevos
	 * @description testing if after addToTail call: 
	 * - the tail is not null 
	 * - length is appropriate
	 * - possible to iterate over the list
	 * - tail has the correct value - next of tail is null
	 * ##############################################################################
	 */
	@Theory
	public void testAddToTail_IsTailNotNull(int i) {
		LinkedList a = getList(i);
		// run the operation to test
		a.addToTail(42);

		// run the assertion test
		assertTrue("[L#" + i + "] Tail is null", (a.getTail() != null));
	}

	@Theory
	public void testAddToTail_LengthIsAppropriate(int i) {
		LinkedList a = getList(i);
		// save old length
		int old_length = a.length();
		// run the operation to test
		a.addToTail(42);
		// run the assertion test
		assertTrue("[L#" + i + "] Length of list is not appropriate",
				((old_length + 1) == a.length()));
	}

	@Theory
	public void testAddToTail_PossibleToIterateOverList(int i) {
		LinkedList a = getList(i);
		// run the operation to test
		a.addToTail(42);

		LLElement tmp = a.getHead();
		LLElement last_seen = null;
		// try to iterate over the list
		while (tmp != null) {
			last_seen = tmp;
			tmp = tmp.getNext();
		}
		// run the assertion test
		assertTrue("[L#" + i
				+ "] Could not iterate completely through the list",
				(last_seen == a.getTail()));
	}

	@Theory
	public void testAddToTail_TailHasCorrectValue(int i) {
		LinkedList a = getList(i);
		int test_value = 42;
		// run the operation to test
		a.addToTail(test_value);
		// run the assertion test
		assertTrue("[L#" + i + "] Tail has wrong value", (a.getTail()
				.getValue() == test_value));
	}

	@Theory
	public void testAddToTail_NextOfTailIsNull(int i) {
		LinkedList a = getList(i);
		// run the operation to test
		a.addToTail(42);
		// run the assertion test
		assertTrue("[L#" + i + "] Next of tail is not null", (a.getTail()
				.getNext() == null));
	}

	/*
	 * ############################################################################## 
	 * 								length() Testing
	 * @author Bob Prevos
	 * @description testing if length: 
	 * - after adding an element increased 
	 * - after deleting an element decreased 
	 * - length of empty list is zero
	 * ##############################################################################
	 */
	@Test
	public void testLength_EmptyListHasLengthZero() {
		LinkedList a = getList(1);
		// run the assertion test
		assertTrue("Empty list has not length zero", (a.length() == 0));
	}

	@Theory
	public void testLength_AddingElementIncreasesLengthByOne(int i) {
		LinkedList a = getList(i);
		int old_length = a.length();
		// add element
		a.addToTail(42);
		// run the assertion test
		assertTrue("Adding Element increases the length not by one",
				((old_length + 1) == a.length()));
	}

	@Theory
	public void testLength_DeletingElementDecreasesLengthByOne(int i) {
		LinkedList a = getList(i);
		int old_length = a.length();
		// delete element
		boolean sthGotDeleted = a.deleteElement(0);
		String errorMsg = "Deleting an element decreases the length not by one";

		if (sthGotDeleted) {
			assertTrue(errorMsg, ((old_length - 1) == a.length()));
		} else {
			assertTrue(errorMsg, (old_length == a.length()));
		}
	}

	/*
	 * ############################################################################## 
	 * 							deleteElement() Testing
	 * @author Bob Prevos
	 * @description testing if after deleteElement(): 
	 * - delete head 
	 * - delete tail - iterating over list after deleting still possible 
	 * - length is decreased after deleting an element 
	 * 		(in testLength_DeletingElementDecreasesLengthByOne)
	 * ##############################################################################
	 */
	@Theory
	public void testDeleteElement_DeleteHead(int i) {
		LinkedList a = getList(i);
		LLElement old_head = a.getHead();
		// delete the head
		boolean sthGotDeleted = a.deleteElement(0);

		if (sthGotDeleted) {
			assertTrue("Head is the same as before", (old_head != a.getHead()));
		} else {
			assertTrue("Head is not the same as before",
					(old_head == a.getHead()));
		}
	}

	@Theory
	public void testDeleteElement_DeleteTail(int i) {
		LinkedList a = getList(i);
		LLElement old_tail = a.getTail();
		// delete the tail
		boolean sthGotDeleted = a.deleteElement(a.length() - 1);

		if (sthGotDeleted) {
			assertTrue("Tail is the same as before", (old_tail != a.getTail()));
		} else {
			assertTrue("Tail is not the same as before",
					(old_tail == a.getTail()));
		}
	}

	@Theory
	public void testDeleteElement_PossibleToIterateThroughListAfterDeleting(
			int i) {
		LinkedList a = getList(i);
		// delete an element
		a.deleteElement(0);
		// test to iterate through the list
		LLElement tmp = a.getHead();
		boolean reachedTail = false;
		while (tmp != null) {
			if (tmp == a.getTail()) {
				reachedTail = true;
			}
			tmp = tmp.getNext();
		}

		if (a.getHead() != null && a.getTail() != null) {
			assertTrue("Tail couldnt be reached", reachedTail);
		}
	}

	/*
	 * ############################################################################## 
	 * 								copyNode() Testing
	 * @author Bob Prevos
	 * @description testing if after copyNode(): 
	 * - index2 == tail, so length is duplicated 
	 * - index2 == head, so value of index1 is new head 
	 * - index2 is not head and not tail, so element is doublicated
	 * ##############################################################################
	 */
	@Theory
	public void testCopyNode_Index2IsTailSoDoubleLengthAfterCopy(int i) {
		LinkedList a = getList(i);
		int old_length = a.length();
		// copy with index2 = tail
		a.copyNode(0, (a.length() - 1));
		assertTrue("Number of elements didnt doubled",
				(a.length() == (old_length * 2)));
	}
	
	@Theory
	public void testCopyNode_Index2IsHeadSoValOfNewHeadIsOfIndex1(int i){
		LinkedList a = getList(3);
		int old_val = a.getTail().getValue();
		// calc index of tail of the list
		int tail_index = a.length()-1;
		// copy last element as new head
		a.copyNode(tail_index, 0);
		assertTrue("New Head has wrong value", (a.getHead().getValue() == old_val));
	}

	@Test
	public void testCopyNode_Index2IsNotHeadNorTailSoElementDoublicated() {
		LinkedList a = getList(4);
		// set value of sec pos to 42
		a.getHead().getNext().setValue(42);
		// set value of head to 21
		a.getHead().setValue(21);
		// copy head to second position
		a.copyNode(0, 1);
		assertTrue("Copy of Node failed", (a.getHead().getValue() == a
				.getHead().getNext().getValue()));
	}
	/*
	 * ############################################################################## 
	 * 								isEmpty() Testing
	 * @author Bob Prevos
	 * @description testing if isEmpty():
	 * 	- after adding not empty anymore
	 *  - after deleting last element is empty
	 *  - is empty for empty list
	 * ##############################################################################
	 */
	@Test
	public void testIsEmpty_AfterAddingNotEmpty(){
		LinkedList a = getList(1);
		//add an element
		a.addToTail(42);
		assertTrue("Still empty after adding an element", (!a.isEmpty()));
	}
	@Test
	public void testIsEmpty_AfterDeletingLastElement(){
		LinkedList a = getList(2);
		// delete last element
		a.deleteElement(0);
		assertTrue("Not empty after deleting last element", (a.isEmpty()));
	}
	@Test
	public void testIsEmpty_ForEmptyList(){
		LinkedList a = getList(1);
		assertTrue("Not empty for empty list", (a.isEmpty()));
	}
	/*
	 * ############################################################################## 
	 * 								createList() Testing
	 * @author Bob Prevos
	 * @description testing if createList():
	 * 	- creates a LinkedList with length = 0
	 * ##############################################################################
	 */
	@Test
	public void testCreateList_ListWithLengthZero(){
		LinkedList a = LinkedList.createList();
		assertTrue("Not a list with length zero", (a.isEmpty()));
	}
}
