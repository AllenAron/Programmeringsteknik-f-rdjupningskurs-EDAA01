package game;

import javax.swing.JOptionPane;

public class UserInterface {
	/** Visar en dialogruta med texten msg. */
	public static void printMessage(String msg) {
		// din egen kod
		JOptionPane.showMessageDialog(null, msg);
	}

	/**
	 * Visar en dialogruta med texten msg och och läser in ett positivt heltal. Om
	 * användaren skriver något som inte kan tolkas som ett positivt heltal ska -1
	 * returneras. Om användaren klickar på "Avbryt" ska -2 returneras.
	 */
	public static int askForInt(String msg) {
		// din egen kod
		String input = JOptionPane.showInputDialog(msg);
		if(input == null) {
			System.exit(0);
			return -2;
		}
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException nfe) {
			return -1;
		}
		if(Integer.parseInt(input) < 0) {
			return -1;
		}
		return Integer.parseInt(input);
		}

	}

