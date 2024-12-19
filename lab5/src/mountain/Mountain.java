package mountain;

import fractal.*;
import java.util.HashMap;

public class Mountain extends Fractal {
	private Point p1, p2, p3;
	private Side r, s, t;
	private double dev;
	private HashMap<Side, Point> sidePoint;

	public Mountain(Point p1, Point p2, Point p3, double dev) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.dev = dev;
		sidePoint = new HashMap<Side, Point>();
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Bergsfraktal";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(p1.getX(), p1.getY());
		fractalTriangle(turtle, order, p1, p2, p3, dev);

	}

	private void fractalTriangle(TurtleGraphics turtle, int order, Point p1, Point p2, Point p3, double dev) {
		Point a, b, c;
		if (order == 0) {
			turtle.moveTo(p1.getX(), p1.getY());
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());
		} else {
			r = new Side(p2, p3);
			if (sidePoint.containsKey(r)) {
				a = sidePoint.get(r);
				sidePoint.remove(r, a);
			} else {
				a = new Point((p2.getX() + p3.getX()) / 2,
						(p2.getY() + p3.getY()) / 2 + (int) RandomUtilities.randFunc(dev));
				sidePoint.put(r, a);
			}

			s = new Side(p3, p1);
			if (sidePoint.containsKey(s)) {
				b = sidePoint.get(s);
				sidePoint.remove(s, b);
			} else {
				b = new Point((p3.getX() + p1.getX()) / 2,
						(p3.getY() + p1.getY()) / 2 + (int) RandomUtilities.randFunc(dev));
				sidePoint.put(s, b);
			}

			t = new Side(p1, p2);
			if (sidePoint.containsKey(t)) {
				c = sidePoint.get(t);
				sidePoint.remove(t, c);
			} else {
				c = new Point((p1.getX() + p2.getX()) / 2,
						(p1.getY() + p2.getY()) / 2 + (int) RandomUtilities.randFunc(dev));
				sidePoint.put(t, c);
			}

			fractalTriangle(turtle, order - 1, p1, b, c, dev / 2);
			fractalTriangle(turtle, order - 1, b, p3, a, dev / 2);
			fractalTriangle(turtle, order - 1, c, b, a, dev / 2);
			fractalTriangle(turtle, order - 1, c, a, p2, dev / 2);
		}

	}
}
