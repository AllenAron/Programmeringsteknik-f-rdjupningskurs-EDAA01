package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while (scan.hasNext()) {
			stopwords.add(scan.next().toLowerCase());
		}

		TextProcessor p = new SingleWordCounter("nils");
		TextProcessor q = new SingleWordCounter("norge");
		TextProcessor m = new MultiWordCounter(REGIONS);
		TextProcessor g = new GeneralWordCounter(stopwords);

		ArrayList<TextProcessor> processList = new ArrayList<TextProcessor>();

		processList.add(p);
		processList.add(q);
		processList.add(m);
		processList.add(g);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for (TextProcessor tp : processList) {
				tp.process(word);
			}
		}

		s.close();
		for (TextProcessor tp : processList) {
			tp.report();
		}
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
		// Median för HashMap: 429ms
		// Median för TreeMap: 503ms
	}
}