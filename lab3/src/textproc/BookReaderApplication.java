package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JList;

public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while (scan.hasNext()) {
			stopwords.add(scan.next().toLowerCase());
		}

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		GeneralWordCounter g = new GeneralWordCounter(stopwords);

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			g.process(word);
		}
		
		s.close();
		
		BookReaderController cont = new BookReaderController(g);
		
	}
}
