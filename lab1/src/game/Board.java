package game;

public class Board {
	int noPins;

	public Board(int noPins) {
		this.noPins = noPins;
	}

	public void setUp(int noPins) {
		this.noPins = noPins;
	}

	public void takePins(int n) {
		this.noPins -= n;
	}

	public int getNoPins() {
		return noPins;
	}
}
