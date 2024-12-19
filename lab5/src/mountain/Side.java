package mountain;

public class Side {

	private Point p1;
	private Point p2;

	public Side(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public boolean equals(Object o) {
		if ((o instanceof Side)) {
			Side s = (Side) o;

			return (p1.equals(s.p1) && p2.equals(s.p2)) || (p1.equals(s.p2) && p2.equals(s.p1));

		}
		return false;
	}

	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}
}
