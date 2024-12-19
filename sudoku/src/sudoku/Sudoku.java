package sudoku;

import java.util.Arrays;

public class Sudoku implements SudokuSolver {
	private int dimension;
	private int[][] board;
	private boolean[][] placed;

	public Sudoku(int dimension) {
		board = new int[dimension][dimension];
		placed = new boolean[dimension][dimension];
		this.dimension = dimension;
	}

	/**
	 * Solves the sudoku.
	 * 
	 * @return true if solvable, else false.
	 */
	@Override
	public boolean solve() {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (board[i][j] > 0) {
					placed[i][j] = true;
				} else {
					placed[i][j] = false;
				}

			}
		}
		if (isValid()) {
			return solve(0, 0);
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Private help method for solving the sudoku recursively
	 * 
	 * @param row
	 * @param col
	 * @return true if solvable, else false
	 */
	private boolean solve(int row, int col) {
		if (row == dimension - 1 && col == dimension - 1) {
			if (!placed[row][col]) {
				for (int digit = 1; digit <= dimension; digit++) {
					if (isCorrect(row, col, digit)) {
						add(row, col, digit);
						return true;
					}
				}
			} else {
				return isCorrect(row, col, get(row, col));
			}
			return false;
		}
		if (placed[row][col]) {
			if (col < dimension - 1) {
				if (solve(row, col + 1)) {
					return true;
				}
			} else if (row < dimension - 1) {
				if (solve(row + 1, 0)) {
					return true;
				}
			} else {
				return true;
			}
		} else {
			for (int digit = 1; digit <= dimension; digit++) {
				if (isCorrect(row, col, digit)) {
					add(row, col, digit);
					if (col < dimension - 1) {
						if (solve(row, col + 1)) {
							return true;
						}
					} else if (row < dimension - 1) {
						if (solve(row + 1, 0)) {
							return true;
						}
					}
				}
			}
			remove(row, col);
		}
		return false;

	}

	private boolean isCorrect(int row, int col, int digit) {
		for (int i = 0; i < dimension; i++) {
			if ((get(i, col) == digit && i != row) || (get(row, i) == digit && i != col)) {
				return false;
			}
		}

		int cc = (col / (int) Math.sqrt(dimension)) * (int) Math.sqrt(dimension);
		int rr = (row / (int) Math.sqrt(dimension)) * (int) Math.sqrt(dimension);

		for (int r = rr; r <= rr + 2; r++) {
			for (int c = cc; c <= cc + 2; c++) {
				if (get(r, c) == digit && r != row && c != col) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	@Override
	public void add(int row, int col, int digit) {
		// TODO Auto-generated method stub
		
		if (digit > dimension || digit < 1 || row > dimension || row < 0 || col > dimension || col < 0) {
			throw new IllegalArgumentException();
		}
		board[row][col] = digit;

	}

	/**
	 * removes the number in the box row, col.
	 * 
	 * @param row The row
	 * @param col The column
	 * 
	 */
	@Override
	public void remove(int row, int col) {
		// TODO Auto-generated method stub
		board[row][col] = 0;
	}

	/**
	 * Returns the number in the box row, col.
	 * 
	 * @param row The row
	 * @param col The column
	 * @return The number in the box.
	 * 
	 */
	@Override
	public int get(int row, int col) {
		// TODO Auto-generated method stub
		return board[row][col];
	}

	/**
	 * Checks that all filled in digits follows the the sudoku rules.
	 * 
	 * @return true if the digits follow the sudoku rules, else false.
	 */
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (!isCorrect(i, j, get(i, j)) && get(i, j) != 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Clears the whole sudoku grid.
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				remove(i, j);
			}
		}
	}

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	@Override
	public void setMatrix(int[][] m) {
		// TODO Auto-generated method stub
		if (m.length != dimension || m[0].length != dimension) {
			throw new IllegalArgumentException();
		}
		for (int r = 0; r < dimension; r++) {
			for (int c = 0; c < dimension; c++) {
				if (board[r][c] > dimension || board[r][c] < 0) {
					throw new IllegalArgumentException();
				}
			}
		}
		board = m;
	}

	/**
	 * Gets the matrix.
	 * 
	 * @return The matrix.
	 */
	@Override
	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		return Arrays.copyOf(board, dimension);
	}

}
