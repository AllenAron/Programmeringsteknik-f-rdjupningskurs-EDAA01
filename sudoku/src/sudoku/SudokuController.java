package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuController {
	private JTextField[][] grid;
	private JPanel gridPanel;
	private JPanel buttonPanel;
	private JButton clearButton;
	private JButton solveButton;

	public SudokuController(SudokuSolver s, int dimension) {
		SwingUtilities.invokeLater(() -> createWindow(s, "Sudokusolver", dimension));
	}

	private void createWindow(SudokuSolver s, String title, int dimension) {
		this.grid = new JTextField[dimension][dimension];
		this.gridPanel = new JPanel();
		this.buttonPanel = new JPanel();
		this.clearButton = new JButton("Clear");
		this.solveButton = new JButton("Solve");
		gridPanel.setLayout(new GridLayout(dimension, dimension));
		JOptionPane optPane = new JOptionPane();
		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {
				JTextField field = new JTextField(1);
				field.setHorizontalAlignment(JTextField.CENTER);
				field.setFont(new Font("SansSerif", Font.BOLD, 30));
				gridPanel.add(field);
				grid[x][y] = field;
			}
		}

		JFrame frame = new JFrame("Sudokusolver");
		frame.getContentPane().add(gridPanel);
		clearButton.addActionListener(event -> {
			s.clear();
			for (int i = 0; i < dimension; i++) {
				for (int j = 0; j < dimension; j++) {
					grid[i][j].setText("");
					grid[i][j].setBackground(Color.WHITE);
				}
			}
		});
		solveButton.addActionListener(event -> {
			for (int i = 0; i < dimension; i++) {
				for (int j = 0; j < dimension; j++) {
					if (!grid[i][j].getText().equals("")) {
						grid[i][j].setBackground(new Color(235, 113, 37));
						try {
							s.add(i, j, Integer.valueOf(grid[i][j].getText()));
						} catch (IllegalArgumentException e) {
							// TODO: handle exception
							optPane.showMessageDialog(frame, "Illegal input argument");
							return;
						}
					} else {
						s.remove(i, j);
						grid[i][j].setBackground(new Color(255, 255, 255));

					}
				}
			}
			if (!s.solve()) {
				optPane.showMessageDialog(frame, "Sudoku was not solved");
			} else {
				int[][] solved = s.getMatrix();
				for (int i = 0; i < dimension; i++) {
					for (int j = 0; j < dimension; j++) {
						grid[i][j].setText(String.valueOf(solved[i][j]));
					}
				}
			}

		});
		buttonPanel.add(clearButton);
		buttonPanel.add(solveButton);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(gridPanel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 500));
		frame.pack();
		frame.setVisible(true);

	}

}
