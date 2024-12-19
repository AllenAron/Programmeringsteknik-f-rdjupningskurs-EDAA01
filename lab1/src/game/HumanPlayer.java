package game;

public class HumanPlayer extends Player {

	public HumanPlayer(String userId) {
		super(userId);
	}

	public int takePins(Board b) {
		int input = UserInterface.askForInt("Please enter how many sticks you would like to take from the pile.");
		while (input < 1 || input > 2) {
			UserInterface.printMessage("Please enter a valid amount.");
			input = UserInterface.askForInt("Please enter how many sticks you would like to take from the pile.");
		}
		b.takePins(input);
		return b.getNoPins();

	}
}
