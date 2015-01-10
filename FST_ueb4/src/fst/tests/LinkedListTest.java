package fst.tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import fst.list.LinkedList;
import fst.list.LinkedList.LLElement;
import fst.list.LinkedList;

@RunWith(Theories.class)
public class LinkedListTest {

	protected static LinkedList createList(int... values) {
		LinkedList linkedList = new LinkedList();

		for (int value : values) {
			linkedList.addToTail(value);
		}

		return linkedList;
	}
	
	private LinkedList getList(int i){
		switch(i){
			// empty list
			case 1: return createList();
			// one element list
			case 2: return createList(new Random().nextInt(100));
			// two element list
			case 3: return createList(new Random().nextInt(100), new Random().nextInt(100));
		}
		return createList();
	}
	
	protected static String createExpectedStringOutput(int... values) {
		StringBuilder tenElementsListContentString = new StringBuilder("[");
		for (int i = 0; i < values.length; i++) {
			tenElementsListContentString.append(values[i]);
			if (i < values.length - 1) {
				tenElementsListContentString.append(", ");
			}
		}
		tenElementsListContentString.append("]");
		return tenElementsListContentString.toString();
	}
	
	// fix to test each function with an empty, one element and two element list
	// without making the changes on the lists persistent after tests
	// so every method with @Theory and int x as param, will get called 3 times
	public static @DataPoints int[] candidates = {1, 2, 3};
	
	/* ##############################################################################
	                              addToTail() Testing
	     @author Bob Prevos
	     @description testing if after addToTail call:
	     				- the tail is not null
	     				- length is appropriate
	     				- possible to iterate over the list
	     				- tail has the correct value
	     				- next of tail is null
	   ############################################################################## */
	@Theory 
	public void testAddToTail_IsTailNotNull(int i){
		LinkedList a = getList(i);
		// run the operation to test
		a.addToTail(42);
		
		// run the assertion test
		assertTrue("[L#"+i+"] Tail is null", (a.tail != null) );
	}
	@Theory
	public void testAddToTail_LengthIsAppropriate(int i){
		LinkedList a = getList(i);
		// save old length
		int old_length = a.length();
		// run the operation to test
		a.addToTail(42);
		// run the assertion test
		assertTrue("[L#"+i+"] Length of list is not appropriate", ((old_length+1) == a.length()) );
	}
	@Theory
	public void testAddToTail_PossibleToIterateOverList(int i){
		LinkedList a = getList(i);
		// run the operation to test
		a.addToTail(42);
		
		LLElement tmp = a.head;
		LLElement last_seen = null;
		// try to iterate over the list
		while(tmp != null){
			last_seen = tmp;
			tmp = tmp.next;
		}
		// run the assertion test
		assertTrue("[L#"+i+"] Could not iterate completely through the list", (last_seen == a.tail) );
	}
	@Theory
	public void testAddToTail_TailHasCorrectValue(int i){
		LinkedList a = getList(i);
		int test_value = 42;
		// run the operation to test
		a.addToTail(test_value);
		// run the assertion test
		assertTrue("[L#"+i+"] Tail has wrong value", (a.tail.value == test_value) );
	}
	@Theory
	public void testAddToTail_NextOfTailIsNull(int i){
		LinkedList a = getList(i);
		// run the operation to test
		a.addToTail(42);
		// run the assertion test
		assertTrue("[L#"+i+"] Next of tail is not null", (a.tail.next == null) );
	}
	/* ##############################################################################
								    length() Testing
		@author Bob Prevos
		@description testing if length:
			- after adding an element increased
			- after deleting an element decreased
			- length of empty list is zero
	############################################################################## */
	@Test
	public void testLength_EmptyListHasLengthZero(){
		LinkedList a = getList(1);
		// run the assertion test
		assertTrue("Empty list has not length zero", (a.length() == 0));
	}
	@Theory
	public void testLength_AddingElementIncreasesLengthByOne(int i){
		LinkedList a = getList(i);
		int old_length = a.length();
		// add element
		a.addToTail(42);
		// run the assertion test
		assertTrue("Adding Element increases the length not by one", ( (old_length+1) == a.length() ));
	}
	@Theory
	public void testLength_DeletingElementDecreasesLengthByOne(int i){
		LinkedList a = getList(i);
		int old_length = a.length();
		// delete element
		boolean sthGotDeleted = a.deleteElement(0);
		String errorMsg = "Deleting an element decreases the length not by one";
		
		if(sthGotDeleted){
			assertTrue(errorMsg, ( (old_length-1) == a.length() ) );
		}else{
			assertTrue(errorMsg, (old_length == a.length()) );
		}
	}
	/* ##############################################################################
    								deleteElement() Testing
		@author Bob Prevos
		@description testing if after deleteElement():
			- delete head
			- delete tail
			- iterating over list after deleting still possible
			- length is decreased after deleting an element (in testLength_DeletingElementDecreasesLengthByOne)
	############################################################################## */
	@Theory
	public void testDeleteElement_DeleteHead(int i){
		LinkedList a = getList(i);
		LLElement old_head = a.head;
		// delete the head
		boolean sthGotDeleted = a.deleteElement(0);
		
		if(sthGotDeleted){
			assertTrue("Head is the same as before", (old_head != a.head) );
		}else{
			assertTrue("Head is not the same as before", (old_head == a.head) );
		}
	}
	@Theory
	public void testDeleteElement_DeleteTail(int i){
		LinkedList a = getList(i);
		LLElement old_tail = a.tail;
		// delete the tail
		boolean sthGotDeleted = a.deleteElement(a.length()-1);
		
		if(sthGotDeleted){
			assertTrue("Tail is the same as before", (old_tail != a.tail) );
		}else{
			assertTrue("Tail is not the same as before", (old_tail == a.tail) );
		}
	}
	@Theory
	public void testDeleteElement_PossibleToIterateThroughListAfterDeleting(int i){
		LinkedList a = getList(i);
		// delete an element
		a.deleteElement(0);
		// test to iterate through the list
		LLElement tmp = a.head;
		boolean reachedTail = false;
		while(tmp != null){
			System.out.println(tmp.value);
			if(tmp == a.tail){
				reachedTail = true;
			}
			tmp = tmp.next;
		}
		
		if(a.head != null  && a.tail != null){
			assertTrue("Tail couldnt be reached", reachedTail);
		}
	}
	/* ##############################################################################
										copyNode() Testing
		@author Bob Prevos
		@description testing if after copyNode():
			- index2 == tail, so length is dublicated
			- index2 == head, so value of index1 is new head
			- index2 is not head and not tail, so element is doublicated
	############################################################################## */
}
