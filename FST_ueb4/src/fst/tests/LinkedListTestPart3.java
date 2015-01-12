package fst.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import fst.list.LinkedList.LLElement;

public class LinkedListTestPart3 extends LinkedListTest {

	@Test public void testEmptyList(){
		assertEquals("Head of Empty List is null",null,emptyList.getHead());
		assertEquals("Tail of Empty List is null",null, emptyList.getTail());
	}


	@Test
	public void testAddToHeadListLength() throws Exception {
		int length=fullList.length();
		fullList.addToHead(15);
		assertEquals("length should be +1", length+1, (int)fullList.length());

	}
	
	@Test
	public void testAddToHeadHeadNewNode() throws Exception {
		LLElement firstHead=fullList.getHead();
		fullList.addToHead(15);
		assertNotEquals("New Head should differ from old Head", fullList.getHead(), firstHead);
	}
	
	@Test
	public void testAddToHeadRightOrder() throws Exception {
		LLElement firstHead=fullList.getHead();
		fullList.addToHead(15);
		assertEquals("old head should follow new head", firstHead, fullList.getHead().getNext());
	}
	
	@Test
	public void testAddToHeadHeadValue() throws Exception {
		fullList.addToHead(15);
		assertEquals("Tail unchanged", 15, fullList.getHead().getValue());
	}
	
	@Test
	public void testAddToHeadTailUnchanged() throws Exception {
		LLElement firstTail=fullList.getTail();
		fullList.addToHead(15);
		assertEquals("Tail unchanged", firstTail, fullList.getTail());
	}

	@Test
	public void testIndexOfValueOutOfBounds() throws Exception {
		int index = fullList.indexOfValue(100);
		assertEquals("OutOfBounds should result in -1", -1, index);
	}
	
	@Test
	public void testIndexOfValueEmptyList() throws Exception {
		int index = emptyList.indexOfValue(1);
		assertEquals("EmptyList should result in -1", -1, index);
	}
	
	@Test
	public void testIndexOfValueInBounds() throws Exception {
		int index = fullList.indexOfValue(3);
		assertEquals("First 3 in List is at index 3", 3, index);
	}

	@Test
	public void testDisplayNodeOutOfBounds() throws Exception {
		int index= 100;
		String result=fullList.displayNode(index);
		assertEquals("Result shoul be Message with warning","There is no Element with Index="+index, result);
	}
	
	@Test
	public void testDisplayNodeEmptyList() throws Exception {
		int index= 1;
		String result=emptyList.displayNode(index);
		assertEquals("Result shoul be Message with warning","There is no Element with Index="+index, result);
	}
	
	@Test
	public void testDisplayNodeInBounds() throws Exception {
		int index= 2;
		String result=fullList.displayNode(index);
		assertEquals("Result shoul be Strind with Result","The Element with Index="+index+" has the value "+2, result);
	}
	
	@Test
	public void testDeleteListEmptyList() throws Exception {
		emptyList.deleteList();
		assertEquals("Head should be null",null,emptyList.getHead());
		assertEquals("Tail should be null",null,emptyList.getTail());
		assertEquals("length should be 0", 0, (int)emptyList.length());
	}
	
	@Test
	public void testDeleteListFullList() throws Exception {
		fullList.deleteList();
		assertEquals("Head should be null",null,fullList.getHead());
		assertEquals("Tail should be null",null,fullList.getTail());
		assertEquals("length should be 0", 0, (int)fullList.length());
	}

	@Test 
	public void testEditNodeOutOfBounds() throws Exception {
		try{
			fullList.editNode(100, 10);
			fail("Should not be possible to editNode out of bounds");
		} catch(IndexOutOfBoundsException e){
		}
	}
	
	@Test
	public void testEditNodeEmptyList() throws Exception {
		try{
			emptyList.editNode(100, 10);
			fail("Should not be possible to editNode in Emtpy List");
		} catch(IndexOutOfBoundsException e){
		}
	}
	
	@Test
	public void testEditNodeInBounds() throws Exception {
		fullList.editNode(2, 100);
		assertEquals("Node should hold the new value", 100, fullList.valueOfIndexElement(2));
	}

}
