package textproc;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> wordCounter;
	private Set<String> exception;

	public GeneralWordCounter(Set<String> exception) {
		this.exception = exception;
		wordCounter = new HashMap<String, Integer>();
	}

	public void process(String w) {
		if (!exception.contains(w)) {
			if (wordCounter.containsKey(w)) {
				wordCounter.put(w, wordCounter.get(w) + 1);
			} else {
				wordCounter.put(w, 1);
			}
		}
	}

	public void report() {
		/*
		 * for (String word : wordCounter.keySet()) { if (wordCounter.get(word) >= 200)
		 * { System.out.println(word + ": " + wordCounter.get(word)); } }
		 */
		Set<Map.Entry<String, Integer>> wordSet = wordCounter.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort((v1, v2) -> {
			if (v1.getValue().equals(v2.getValue())) {
				return v1.getKey().compareTo(v2.getKey());
			} else {
				return v2.getValue() - v1.getValue();
			}
		});

		for (int i = 0; i < 50; i++) {
			System.out.println(wordList.get(i));
		}
	}
	
	public List<Map.Entry<String, Integer>> getWordList() {
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>();
		for(String word : wordCounter.keySet()) {
			wordList.add(Map.entry(word, wordCounter.get(word)));
		}
		return wordList;
	}
}
