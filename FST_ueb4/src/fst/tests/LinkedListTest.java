package fst.tests;

import java.util.Random;

import org.junit.BeforeClass;

import fst.list.LinkedList;

public class LinkedListTest {
	protected static LinkedList emptyList;
	protected static LinkedList oneElementList;
	protected static LinkedList twoElementsList;

	@BeforeClass
	public static void initializeLists() {
		emptyList = createList();
		oneElementList = createList(new Random().nextInt(100));
		twoElementsList = createList(new Random().nextInt(100),
				new Random().nextInt(100));
	}

	protected static LinkedList createList(int... values) {
		LinkedList linkedList = new LinkedList();

		for (int value : values) {
			linkedList.addToTail(value);
		}

		return linkedList;
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
}
