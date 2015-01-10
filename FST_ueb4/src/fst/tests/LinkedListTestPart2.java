package fst.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LinkedListTestPart2 extends LinkedListTest {

	@Test(expected = IndexOutOfBoundsException.class)
	public void testValueOfIndexElementWithEmptyList() {
		emptyList.valueOfIndexElement(0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testValueOfIndexElementWithInvalidHeadIndex() {
		oneElementList.valueOfIndexElement(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testValueOfIndexElementWithInvalidTailIndex() {
		twoElementsList.valueOfIndexElement(2);
	}

	@Test
	public void testValueOfIndexElementWithTailIndex() {
		assertEquals(3, createList(3, 5, 8).valueOfIndexElement(0));
	}

	@Test
	public void testValueOfIndexElementWithHeadIndex() {
		assertEquals(8, createList(3, 5, 8).valueOfIndexElement(2));
	}

	@Test
	public void testValueOfIndexElementWithInnerIndex() {
		assertEquals(5, createList(3, 9, 5, 22, 8).valueOfIndexElement(2));
	}

	@Test
	public void testDisplayListWithEmptyList() {
		assertEquals(createExpectedStringOutput(), emptyList.displayList());
	}

	@Test
	public void testDisplayListWithOneElement() {
		assertEquals(createExpectedStringOutput(42), createList(42)
				.displayList());
	}

	@Test
	public void testDisplayListWithSeveralElements() {
		assertEquals(createExpectedStringOutput(12, 5, 37, 0, 99),
				createList(12, 5, 37, 0, 99).displayList());
	}

	@Test
	public void testSortListWithEmptyList() {
		assertEquals(createExpectedStringOutput(), emptyList.sortList()
				.toString());
	}

	@Test
	public void testSortListWithOneElement() {
		assertEquals(createExpectedStringOutput(5), createList(5).sortList()
				.toString());
	}

	@Test
	public void testSortListWithSeveralElements() {
		assertEquals(createExpectedStringOutput(5, 9, 15, 17, 31, 48, 49, 50),
				createList(15, 48, 5, 9, 50, 49, 17, 31).sortList().toString());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testbinSearchtWithUnsortedList() {
		createList(15, 48, 5, 9, 50, 49, 17, 31).binSearch(3);
	}
	
	@Test
	public void testbinSearchtWithInvalidValue() {
		assertEquals(-1, createList(5, 9, 15, 17, 31, 48, 49, 50).binSearch(3));
	}

	@Test
	public void testbinSearchtWithHead() {
		assertEquals(0, createList(5, 9, 15, 17, 31, 48, 49, 50).binSearch(5));
	}

	@Test
	public void testbinSearchtWithTail() {
		assertEquals(7, createList(5, 9, 15, 17, 31, 48, 49, 50).binSearch(50));
	}

	@Test
	public void testbinSearchtWithInnerValue() {
		assertEquals(4, createList(5, 9, 15, 17, 31, 48, 49, 50).binSearch(31));
	}

}
