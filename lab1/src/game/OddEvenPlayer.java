package game;

public class OddEvenPlayer extends Player {
	public OddEvenPlayer(String userId) {
		super(userId);
	}

	public int takePins(Board b) {
		switch (b.getNoPins() % 2) {
		case 0:
			b.takePins(2);
			return b.getNoPins();

		case 1:
			b.takePins(1);
			return b.getNoPins();

		default:
			return b.getNoPins();
		}
	}

}
