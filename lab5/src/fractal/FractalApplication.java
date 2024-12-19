package fractal;

import koch.Koch;
import mountain.*;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[0] = new Mountain(new Point(50, 550), new Point(550, 550), new Point(450, 50), 70);
		fractals[1] = new Koch(300);
		new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
