package bst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBinarySearchTree {
	BinarySearchTree<Integer> intST;
	BinarySearchTree<Character> charST;

	@BeforeEach
	void setUp() throws Exception {
		//intST = new BinarySearchTree<Integer>((n1, n2) -> Integer.compare(n1, n2));
		intST = new BinarySearchTree<Integer>();
		charST = new BinarySearchTree<Character>();
	}

	@AfterEach
	void tearDown() throws Exception {
		intST = null;
		charST = null;
	}

	@Test
	void testHeight() {
		intST.add(5);
		intST.add(3);
		intST.add(6);
		intST.add(7);
		intST.printTree();
		System.out.println(intST.height());
		assertFalse(intST.add(7), "");
		assertTrue(intST.add(10), "");
		assertEquals(intST.height(), 4, "Wrong height of tree");
		
		charST.add('a');
		charST.add('d');
		charST.add('b');
		charST.add('j');
		charST.add('e');
		charST.printTree();
		System.out.println(charST.height());
		assertEquals(charST.height(), 4, "Wrong height of tree");
		
	}
	
	@Test
	void testHeightEmptyTree() {
		assertEquals(intST.height(), 0, "Wrong height of empty tree");
		assertEquals(charST.height(), 0, "Wrong height of empty tree");
	}

	@Test
	void testSize() {
		for (int i = 0; i < 5; i++) {
			intST.add(i);
		}
		
		charST.add('a');
		charST.add('d');
		charST.add('b');
		charST.add('j');
		charST.add('e');
		
		assertEquals(intST.size(), 5, "Wrong size of tree");
		assertEquals(charST.size(), 5, "Wrong size of tree");
	}
	
	@Test
	void testSizeEmptyTree() {
		assertEquals(intST.size(), 0, "Wrong size of empty tree");
		assertEquals(intST.size(), 0, "Wrong size of empty tree");
	}
	
	@Test
	void testClear() {
		intST.add(1);
		intST.add(2);
		intST.clear();
		charST.add('a');
		charST.add('k');
		charST.clear();
		assertEquals(intST.size(), 0, "Wrong size of cleared tree");
		assertEquals(charST.size(), 0, "Wrong size of cleared tree");
	}

}
