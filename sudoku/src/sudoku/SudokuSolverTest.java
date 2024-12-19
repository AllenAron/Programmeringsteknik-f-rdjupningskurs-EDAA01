package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuSolverTest {
	Sudoku s;

	@BeforeEach
	void setUp() throws Exception {
		s = new Sudoku(9);
	}

	@AfterEach
	void tearDown() throws Exception {
		s = null;
	}

	@Test
	void testSolveEmpty() {
		assertEquals(true, s.solve(), "Failed to be solved");
	}

	@Test
	void testSolveFigure1() {
		s.add(0, 2, 8);
		s.add(0, 5, 9);
		s.add(0, 7, 6);
		s.add(0, 8, 2);
		s.add(1, 8, 5);
		s.add(2, 0, 1);
		s.add(2, 2, 2);
		s.add(2, 3, 5);
		s.add(3, 3, 2);
		s.add(3, 4, 1);
		s.add(3, 7, 9);
		s.add(4, 1, 5);
		s.add(4, 6, 6);
		s.add(5, 0, 6);
		s.add(5, 7, 2);
		s.add(5, 8, 8);
		s.add(6, 0, 4);
		s.add(6, 1, 1);
		s.add(6, 3, 6);
		s.add(6, 5, 8);
		s.add(7, 0, 8);
		s.add(7, 1, 6);
		s.add(7, 4, 3);
		s.add(7, 6, 1);
		s.add(8, 6, 4);
		assertEquals(true, s.solve(), "Failed to be solved");
	}

	@Test
	void testSolveUnsolvable() {
		s.add(0, 0, 1);
		s.add(8, 0, 1);
		assertEquals(false, s.solve(), "Solved unsolvable");
	}

	@Test
	void testSolveUnsolvable1() {
		s.add(4, 0, 1);
		s.add(4, 7, 1);
		assertEquals(false, s.solve(), "Solved unsolvable");
	}

	@Test
	void testSolveUnsolvable2() {
		s.add(3, 3, 3);
		s.add(5, 5, 3);
		assertEquals(false, s.solve(), "Solved unsolvable");
	}

	@Test
	void testAdd() {
		s.add(0, 0, 1);
		assertEquals(1, s.get(0, 0), "int not added");
	}

	@Test
	void testGet() {
		s.add(0, 0, 1);
		assertEquals(1, s.get(0, 0), "wrong int");
	}

	@Test
	void testRemove() {
		s.add(0, 0, 1);
		s.remove(0, 0);
		assertEquals(0, s.get(0, 0), "int not removed");
	}

	@Test
	void testIsValid() {
		s.solve();
		assertEquals(true, s.isValid(), "Did not work");
	}

	@Test
	void testClear() {
		s.solve();
		s.clear();
		boolean f = true;
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (s.get(r, c) != 0) {
					f = false;
				}
			}
		}
		assertEquals(true, f, "Matrix not cleared");
	}

	@Test
	void testSetMatrix() {
		int[][] m = new int[9][9];
		m[0][0] = 1;
		m[0][3] = 1;
		m[5][7] = 2;
		m[5][8] = 8;
		m[6][0] = 4;
		m[6][1] = 1;
		m[6][3] = 6;
		m[7][0] = 8;
		m[7][1] = 6;
		m[7][4] = 3;
		m[7][6] = 1;
		s.setMatrix(m);
		boolean b = true;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (s.get(i, j) != m[i][j]) {
					b = false;
				}
			}
		}
		assertTrue(b, "Wrong matrix returned");
	}

	@Test
	void testGetMatrix() {
		int[][] m = new int[9][9];
		m[0][0] = 1;
		m[0][3] = 1;
		m[5][7] = 2;
		m[5][8] = 8;
		m[6][0] = 4;
		m[6][1] = 1;
		m[6][3] = 6;
		s.add(0, 0, 1);
		s.add(0, 3, 1);
		s.add(5, 7, 2);
		s.add(5, 8, 8);
		s.add(6, 0, 4);
		s.add(6, 1, 1);
		s.add(6, 3, 6);
		boolean b = true;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(s.get(i, j) != m[i][j]) {
					b = false;
				}
			}
		}
		assertTrue(b, "Wrong matrix returned");
	}

}