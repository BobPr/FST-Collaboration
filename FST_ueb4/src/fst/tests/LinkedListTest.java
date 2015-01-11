package fst.tests;

import java.util.Random;

import org.junit.Before;

import fst.list.LinkedList;

/**
 * Superclass for all test classes. Executing this class leads to the execution
 * of all derived test classes.
 * 
 */
public class LinkedListTest {

	protected LinkedList emptyList;
	protected LinkedList oneElementList;
	protected LinkedList twoElementsList;

	@Before
	public void initializeLists() {
		emptyList = createList();
		oneElementList = createList(new Random().nextInt(100));
		twoElementsList = createList(new Random().nextInt(100),
				new Random().nextInt(100));
	}

	protected LinkedList createList(int... values) {
		LinkedList linkedList = LinkedList.createList();

		for (int value : values) {
			linkedList.addToTail(value);
		}

		return linkedList;
	}

	protected LinkedList getList(int i) {
		switch (i) {
		// empty list
		case 1:
			return createList();
			// one element list
		case 2:
			return createList(new Random().nextInt(100));
			// two element list
		case 3:
			return createList(new Random().nextInt(100),
					new Random().nextInt(100));
			// three element list
		case 4:
			return createList(new Random().nextInt(100),
					new Random().nextInt(100), new Random().nextInt(100));
		}
		return createList();
	}

	protected String createExpectedStringOutput(int... values) {
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
}
