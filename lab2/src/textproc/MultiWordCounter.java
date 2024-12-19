package textproc;

import java.util.TreeMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor {
	private Map<String, Integer> wordCounter;

	public MultiWordCounter(String[] words) {
		wordCounter = new TreeMap<String, Integer>();
		for (String word : words) {
			wordCounter.put(word, 0);
		}
	}

	public void process(String w) {
		for (String word : wordCounter.keySet()) {
			if (word.equals(w)) {
				wordCounter.replace(word, wordCounter.get(word) + 1);
			}
		}
	}

	public void report() {
		for(String word : wordCounter.keySet()) {
			System.out.println(word + ": " + wordCounter.get(word));
		}
	}

}
