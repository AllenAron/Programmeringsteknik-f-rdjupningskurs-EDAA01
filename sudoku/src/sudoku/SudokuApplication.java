package sudoku;

public class SudokuApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SudokuSolver s = new Sudoku(9);
		new SudokuController(s, 9);
	}

}
