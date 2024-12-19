package textproc;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class BookReaderController {
	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
	}

	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// pane är en behållarkomponent till vilken de övriga komponenterna (listvy,
		// knappar etc.) ska läggas till.

		SortedListModel<Map.Entry<String, Integer>> model = new SortedListModel(counter.getWordList());
		JList<Map.Entry<String, Integer>> sortedList = new JList<Map.Entry<String, Integer>>(model);
		JScrollPane scrollPane = new JScrollPane(sortedList);

		JPanel panel = new JPanel();
		Container pane = frame.getContentPane();
		JRadioButton alphabetic = new JRadioButton("Alphabetic");
		JRadioButton frequency = new JRadioButton("Frequency");
		ButtonGroup bg = new ButtonGroup();
		bg.add(alphabetic);
		bg.add(frequency);
		alphabetic.addActionListener(event -> model.sort((v1, v2) -> {
			sortedList.clearSelection();
			sortedList.ensureIndexIsVisible(0);
			return v1.getKey().compareTo(v2.getKey());
		}));
		frequency.addActionListener(event -> model.sort((v1, v2) -> {
			sortedList.clearSelection();
			sortedList.ensureIndexIsVisible(0);
			if (v1.getValue().equals(v2.getValue())) {
				return v1.getKey().compareTo(v2.getKey());
			} else {
				return v2.getValue() - v1.getValue();
			}
		}));
		JTextField searchBox = new JTextField(30);
		JButton find = new JButton("Find");
		find.addActionListener(event -> {
			boolean found = false;
			for (int i = 0; i < model.getSize(); i++) {
				if (model.getElementAt(i).getKey().equals(searchBox.getText().toLowerCase().trim())) {
					sortedList.ensureIndexIsVisible(i);
					sortedList.setSelectedIndex(i);
					found = true;

				}
			}
			if (!found) {
				JOptionPane optPane = new JOptionPane();
				optPane.showMessageDialog(frame, searchBox.getText().trim() + " not found.");
			}
		});
		// frame.addKeyListener(new KeyListener{

		// });
		panel.add(alphabetic);
		panel.add(frequency);
		panel.add(searchBox);
		panel.add(find);

		pane.add(scrollPane);
		pane.add(panel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}

}
