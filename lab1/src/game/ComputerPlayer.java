package game;

import java.util.Random;

public class ComputerPlayer extends Player {
	Random rand = new Random();

	public ComputerPlayer(String userId) {
		super(userId);
	}

	public int takePins(Board b) {
		b.takePins(rand.nextInt(2) + 1);
		return b.getNoPins();
	}
}
