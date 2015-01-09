package fst.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import fst.list.LinkedList;
import fst.list.LinkedList.LLElement;

@RunWith(Theories.class)
public class LinkedListTest {

	@Test
	public void testLinkedList() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testCreateList() {
		fail("123441234123412342");
	}

	@Test
	public void testIsEmpty() {
		fail("Not yet implemented");
	}

	/**
	 * @Testdescription:
	 * + testing if the object is the same 
	 */
	private boolean[] testAddToTailCompanion(){
		
		LinkedList a = new LinkedList();
		
		LLElement oldtail = a.tail;
		Integer old_length = a.length;
		Integer valForTail = 5;
		LLElement tail = a.addToTail(valForTail);
		// TODO run from head to tail still possible AND next of tail is null
		// TODO SHITTON OF WORK >> splitting
		/*
		return ((oldtail != tail) &&
				(tail != null) && 
				((old_length+1) == a.length) && 
				(tail.value == valForTail));
		*/
		boolean[] ret = new boolean[4];
		ret[0] = (oldtail != tail);
		ret[1] = (tail != null);
		ret[2] = ( (old_length+1) == a.length);
		ret[3] = (tail.value == valForTail);
		return ret;
	}
	
	// create different LinkedList candidates
	private static LinkedList createEmptyList(){
		return new LinkedList();
	}
	private static LinkedList createListWithOneElement(){
		LinkedList a = new LinkedList();
		a.addToTail(1);
		return a;
	}
	private static LinkedList createListWithTwoElements(){
		LinkedList a = new LinkedList();
		a.addToTail(1);
		a.addToTail(2);
		return a;
	}
	
	public static @DataPoints LinkedList[] candidates = {
		createEmptyList(),
		createListWithOneElement(),
		createListWithTwoElements()
	};
	
	@Theory
	public void testAddToTail_IsTailNotNull(LinkedList a){
		// run the operation to test
		a.addToTail(42);
		// run the assertion test
		assertTrue("Tail is null", (a.tail != null) );
	}
	
	@Theory
	public void testAddToTail_PossibleToIterateOverList(LinkedList a){
		// run the operation to test
		a.addToTail(42);
		
		boolean possibleToIterate = false;
		LLElement tmp = a.head;
		LLElement last_seen = null;
		// try to iterate over the list
		while(tmp != null){
			last_seen = tmp;
			tmp = tmp.next;
		}
		// run the assertion test
		assertTrue("Could not iterate completely through the list", (last_seen == a.tail) );
	}
	
	@Theory
	public void testAddToTail_LengthIsAppropriate(LinkedList a){
		// save old length
		int old_length = a.length;
		// run the operation to test
		a.addToTail(42);
		// run the assertion test
		assertTrue("Length of list is not appropriate", ((old_length+1) == a.length) );
	}
	
	@Theory
	public void testAddToTail_TailHasCorrectValue(LinkedList a){
		int test_value = 42;
		// run the operation to test
		a.addToTail(test_value);
		// run the assertion test
		assertTrue("Tail has wrong value", (a.tail.value == test_value) );
	}
	
	@Theory
	public void testAddToTail_NextOfTailIsNull(LinkedList a){
		// run the operation to test
		a.addToTail(42);
		// run the assertion test
		assertTrue("Next of tail is not null", (a.tail.next == null) );
	}
}
