package game;

import javax.swing.JOptionPane;

public class TakePinsGame {

	public static void main(String[] args) {
		int noPins = UserInterface.askForInt("How many pins would you like to play the game with?");
		while (noPins < 1) {
			noPins = UserInterface.askForInt("Please enter a valid amount.");
		}
		Board b = new Board(noPins);
		UserInterface.printMessage("The game starts with " + noPins + " pins.");
		Player hp = new HumanPlayer(JOptionPane.showInputDialog("Please enter the userId for the human player"));
		Player cp = new ComputerPlayer(JOptionPane.showInputDialog("Please enter the userId for the computer player"));
		while (b.getNoPins() > 0) {
			hp.takePins(b);
			UserInterface.printMessage("Number of pins left: " + b.getNoPins());
			if (b.getNoPins() <= 0) {
				UserInterface.printMessage("The game is finished. " + cp.getUserId() + " won.");
				System.exit(0);
			}
			cp.takePins(b);
			if (b.getNoPins() <= 0) {
				UserInterface.printMessage("The game is finished. " + hp.getUserId() + " won.");
				System.exit(0);
			}
			UserInterface.printMessage("Please wait for the computer to make it's move.");
			UserInterface.printMessage("Number of pins left: " + b.getNoPins());
		}

	}
}
