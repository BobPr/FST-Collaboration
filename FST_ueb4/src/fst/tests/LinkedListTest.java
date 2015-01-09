package fst.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fst.list.LinkedList;
import fst.list.LinkedList.LLElement;

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
	
	@Test
	public void testAddToTail() {
		boolean[] test_result = testAddToTailCompanion();
		assertTrue("Not the same object", (test_result[0]==true));
		assertTrue("Tail iss not null", (test_result[1]==false));
		assertTrue("Length not ", (test_result[2]==true));
		
	}

}
