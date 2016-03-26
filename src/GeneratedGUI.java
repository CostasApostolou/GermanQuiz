import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class GeneratedGUI extends JFrame {

	private JPanel contentPane;
	private JButton[] buttons = new JButton[10];
	private JButton check;
	private JLabel word, yourAns = new JLabel(), score, ansValidity,
			correctAnsReveal;
	private JTextField ans;
	private String[] words = { "word1", "word2", "word3", "word4", "word5",
			"word6", "word7", "word8", "word9", "word10" };
	private int index = 1, LANG = 0, gameChosen = -1, overallCounter,
			correctAnsCounter, rand;
	private ImageIcon[] langIcons = {
			new ImageIcon(GeneratedGUI.class.getResource("engIcon.png")),
			new ImageIcon(GeneratedGUI.class.getResource("greekIcon.png")),
			new ImageIcon(GeneratedGUI.class.getResource("deutschIcon.png")) };

	private String[][] games = {
			{ "Verb Translation (free mode)", "Noun Gender (free mode)",
					"Verb Translation (test mode)", "Noun Gender (test mode)" },
			{ "Μετάφραση ρημάτων (απεριόριστο)",
					"Το γένος των ουσιαστικών (απεριόριστο)",
					"Μετάφραση ρημάτων (test)",
					"Το γένος των ουσιαστικών (test)" },
			{ "Verben Übersetzung (endlos)", "Nomen Geschlecht (endlos)",
					"Verben Übersetzung (Test)", "Nomen Geschlecht (Test)" } };

	private static final int WINDOW_STARTING_X = 400, WINDOW_STARTING_Y = 200,
			X_LANG_BUTTON_SIZE = 125, Y_LANG_BUTTON_SIZE = 100,
			X_NUM_BUTTON_SIZE = 45, Y_NUM_BUTTON_SIZE = 40, X_GAP = 10,
			Y_GAP = 10, X_BEZEL = 6, Y_BEZEL = 40, SIMPLE_BUTTON_WIDTH = 70,
			SIMPLE_BUTTON_HEIGHT = 30;

	private static final int[] TEXT_SPACE = { 100, 130, 100 };

	private static final String[] CHOOSE_GAME_PROMPT = {
			"Please choose a game", "Παρακαλώ διαλέξτε ένα παιχνίδι",
			"Wählen Sie bitte ein Spiel" };
	private static final String[] CORRECT_ANS = { "The correct answer was ",
			"Η σωστή απάντηση ήταν ", "Die richtige Antwort war " };
	private static final String[] RIGHT = { "Right!", "Σωστό!", "Richtig!" };
	private static final String[] WRONG = { "Wrong", "Λάθος", "Falsch" };

	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// GeneratedGUI frame = new GeneratedGUI();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	public static void render() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneratedGUI frame = new GeneratedGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GeneratedGUI() {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(WINDOW_STARTING_X, WINDOW_STARTING_Y);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		createLangChoose();
	}

	private void createLangChoose() {

		setBounds(getLocation().x, getLocation().y, 3 * X_LANG_BUTTON_SIZE + 4
				* X_GAP + X_BEZEL, Y_LANG_BUTTON_SIZE + 5 * Y_GAP + Y_BEZEL);

		contentPane.removeAll();

		JLabel prompt = new JLabel("Choose instruction language");
		prompt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prompt.setBounds(X_GAP, Y_GAP, 3 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(prompt);

		createAndAddLangButtons();
	}

	private void createAndAddLangButtons() {

		for (int i = 0; i < 3; i++) {
			JButton button = new JButton(langIcons[i]);
			button.setBounds((i + 1) * X_GAP + i * X_LANG_BUTTON_SIZE,
					5 * Y_GAP, X_LANG_BUTTON_SIZE, Y_LANG_BUTTON_SIZE);
			button.setHorizontalTextPosition(SwingConstants.CENTER);
			button.setName(String.valueOf(i));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					LANG = Integer.parseInt(((JButton) arg0.getSource())
							.getName());
					createGameChoose();
				}
			});
			contentPane.add(button);
		}
	}

	private void createGameChoose() {

		setBounds(getLocation().x, getLocation().y, 4 * TEXT_SPACE[LANG] + 3
				* X_GAP + X_BEZEL, 16 * Y_GAP + Y_BEZEL);

		contentPane.removeAll();
		// reset gameChosen
		gameChosen = -1;

		JLabel prompt = new JLabel(CHOOSE_GAME_PROMPT[LANG]);
		prompt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prompt.setBounds(X_GAP, Y_GAP, 3 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(prompt);

		createAndAddGrouppedRadioButtons();

		JButton back = new JButton("Back");
		back.setBounds(2 * TEXT_SPACE[LANG] + 3 * X_GAP, 12 * Y_GAP,
				SIMPLE_BUTTON_WIDTH, SIMPLE_BUTTON_HEIGHT);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createLangChoose();
			}
		});
		contentPane.add(back);

		JButton play = new JButton("Play!");
		play.setBounds(2 * TEXT_SPACE[LANG] + SIMPLE_BUTTON_WIDTH + 4 * X_GAP,
				12 * Y_GAP, SIMPLE_BUTTON_WIDTH, SIMPLE_BUTTON_HEIGHT);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (gameChosen) {
				case 0:
					playTranslationFree();
					break;
				case 1:
					break;
				case 2:
					playTranslationTest();
					break;
				case 3:
					break;
				}
			}
		});

		contentPane.add(play);
		getRootPane().setDefaultButton(play);
	}

	private void createAndAddGrouppedRadioButtons() {

		ButtonGroup group = new ButtonGroup();

		for (int i = 0; i < 4; i++) {
			JRadioButton radioButton = new JRadioButton(games[LANG][i]);
			radioButton.setName(String.valueOf(i));
			int y_multi = i % 2 == 0 ? 5 : 8;
			int x_multi = i < 2 ? 0 : 1;
			radioButton.setBounds(X_GAP + x_multi * (2 * TEXT_SPACE[LANG]),
					y_multi * Y_GAP, 2 * TEXT_SPACE[LANG], 3 * Y_GAP);
			radioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					gameChosen = Integer.parseInt(((JRadioButton) arg0
							.getSource()).getName());
				}
			});
			group.add(radioButton);
			contentPane.add(radioButton);
		}
	}

	private void playTranslationFree() {
		setBounds(getLocation().x, getLocation().y, 10 * X_NUM_BUTTON_SIZE + 11
				* X_GAP + X_BEZEL, 300);

		contentPane.removeAll();
		// reset counters
		overallCounter = 0;
		correctAnsCounter = 0;

		JLabel prompt = new JLabel("Write the translation");
		prompt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prompt.setBounds(X_GAP, Y_GAP, 2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(prompt);

		score = new JLabel(formScoreString());
		score.setFont(new Font("Tahoma", Font.PLAIN, 16));
		score.setBounds(getWidth() - TEXT_SPACE[LANG] - X_GAP, Y_GAP,
				TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(score);

		// random generator
		rand = new Random().nextInt(words.length);
		word = new JLabel(words[rand]);
		word.setFont(new Font("Tahoma", Font.PLAIN, 16));
		word.setBounds(X_GAP, 5 * Y_GAP, TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(word);

		ans = new JTextField();
		ans.setBounds(2 * X_GAP + word.getWidth(), 5 * Y_GAP,
				2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(ans);

		final JButton next = new JButton(new ImageIcon(
				GeneratedGUI.class.getResource("next.png")));
		next.setBounds(getWidth() - SIMPLE_BUTTON_HEIGHT - X_GAP, 21 * Y_GAP,
				SIMPLE_BUTTON_HEIGHT, SIMPLE_BUTTON_HEIGHT);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rand = new Random().nextInt(words.length);
				removeOldAnsAndRepaint(rand);
				check.setEnabled(true);
				getRootPane().setDefaultButton(check);
			}
		});
		contentPane.add(next);

		check = new JButton("Check");
		check.setBounds(next.getLocation().x - X_GAP - SIMPLE_BUTTON_WIDTH,
				21 * Y_GAP, SIMPLE_BUTTON_WIDTH, SIMPLE_BUTTON_HEIGHT);
		contentPane.add(check);

		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String strAns = ans.getText();

				if (!strAns.equals("")) {

					ans.setText("");
					revealAns(word.getLocation().x, word.getLocation().y + 4
							* Y_GAP, strAns, words[rand],
							validateAns(rand + 1, strAns));
					check.setEnabled(false);
					getRootPane().setDefaultButton(next);
				}

			}
		});

		JButton back = new JButton("Back");
		back.setBounds(check.getLocation().x - X_GAP - SIMPLE_BUTTON_WIDTH,
				21 * Y_GAP, SIMPLE_BUTTON_WIDTH, SIMPLE_BUTTON_HEIGHT);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createGameChoose();
			}
		});
		contentPane.add(back);

		addHomeButton(back.getLocation().x - X_GAP - SIMPLE_BUTTON_HEIGHT,
				21 * Y_GAP, SIMPLE_BUTTON_HEIGHT, SIMPLE_BUTTON_HEIGHT);

		getRootPane().setDefaultButton(check);
	}

	private boolean validateAnsAndDisableButton(int index, String strAns) {
		boolean isAnsCorrect = validateAns(index, strAns);
		if (isAnsCorrect) {
			buttons[index - 1].setBackground(Color.green);
		} else {
			buttons[index - 1].setBackground(Color.RED);
		}
		buttons[index - 1].setEnabled(false);

		return isAnsCorrect;
	}

	private boolean validateAns(int index, String strAns) {
		return strAns.equals("word" + String.valueOf(index));
	}

	private String formScoreString() {
		return correctAnsCounter + " / " + overallCounter;
	}

	private void playTranslationTest() {

		setBounds(getLocation().x, getLocation().y, 10 * X_NUM_BUTTON_SIZE + 11
				* X_GAP + X_BEZEL, 300);

		contentPane.removeAll();

		createAndAddNumButtons();

		JLabel prompt = new JLabel("Write the translation");
		prompt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prompt.setBounds(X_GAP, Y_NUM_BUTTON_SIZE + 2 * Y_GAP,
				2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(prompt);

		word = new JLabel(words[index - 1]);
		word.setFont(new Font("Tahoma", Font.PLAIN, 16));
		word.setBounds(X_GAP, Y_NUM_BUTTON_SIZE + 6 * Y_GAP, TEXT_SPACE[LANG],
				3 * Y_GAP);
		contentPane.add(word);

		ans = new JTextField();
		ans.setBounds(2 * X_GAP + word.getWidth(), Y_NUM_BUTTON_SIZE + 6
				* Y_GAP, 2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(ans);

		addHomeButton(8 * X_GAP + 6 * X_NUM_BUTTON_SIZE, 210,
				SIMPLE_BUTTON_HEIGHT, SIMPLE_BUTTON_HEIGHT);

		JButton back = new JButton("Back");
		back.setBounds(
				9 * X_GAP + 6 * X_NUM_BUTTON_SIZE + SIMPLE_BUTTON_HEIGHT, 210,
				SIMPLE_BUTTON_WIDTH, SIMPLE_BUTTON_HEIGHT);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createGameChoose();
			}
		});
		contentPane.add(back);

		check = new JButton("Check");
		check.setBounds(X_GAP + back.getLocation().x + back.getWidth(), 210,
				SIMPLE_BUTTON_WIDTH, SIMPLE_BUTTON_HEIGHT);
		contentPane.add(check);

		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String strAns = ans.getText();

				if (!strAns.equals("")) {

					ans.setText("");
					revealAns(word.getLocation().x, word.getLocation().y + 4
							* Y_GAP, strAns, words[index - 1],
							validateAnsAndDisableButton(index, strAns));
					check.setEnabled(false);
				}

			}
		});
		getRootPane().setDefaultButton(check);
	}

	private void addHomeButton(int x, int y, int width, int height) {
		JButton home = new JButton(new ImageIcon(
				GeneratedGUI.class.getResource("home.png")));
		home.setBounds(x, y, width, height);
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createLangChoose();
			}
		});
		contentPane.add(home);
	}

	private void createAndAddNumButtons() {

		for (int i = 0; i < 10; i++) {
			buttons[i] = new JButton(String.valueOf(i + 1));
			buttons[i].setFont(new Font("Tahoma", Font.PLAIN, 10));
			buttons[i].setBounds((i + 1) * X_GAP + i * (X_NUM_BUTTON_SIZE),
					Y_GAP, X_NUM_BUTTON_SIZE, Y_NUM_BUTTON_SIZE);
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					index = Integer.parseInt(((JButton) arg0.getSource())
							.getText());
					removeOldAnsAndRepaint(index - 1);
				}
			});
			contentPane.add(buttons[i]);
		}

	}

	private void revealAns(int x, int y, String givenAns, String correctAns,
			boolean isAnsCorrect) {

		yourAns.setText("Your answer : " + givenAns);
		yourAns.setFont(new Font("Tahoma", Font.PLAIN, 16));
		yourAns.setBounds(x, y, 3 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(yourAns);

		ansValidity = new JLabel();
		ansValidity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ansValidity
				.setBounds(x, y + 4 * Y_GAP, 3 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(ansValidity);

		if (isAnsCorrect) {
			ansValidity.setText(RIGHT[LANG]);
			correctAnsCounter++;
		} else {
			ansValidity.setText(WRONG[LANG]);
			correctAnsReveal = new JLabel(CORRECT_ANS[LANG] + correctAns);
			correctAnsReveal.setFont(new Font("Tahoma", Font.PLAIN, 16));
			correctAnsReveal.setBounds(x, y + 8 * Y_GAP, 3 * TEXT_SPACE[LANG],
					3 * Y_GAP);
			contentPane.add(correctAnsReveal);
		}

		overallCounter++;
		if (score != null) {
			score.setText(formScoreString());
		}

		contentPane.repaint();
	}

	private void removeOldAnsAndRepaint(int index) {

		if (ans != null) {
			ans.grabFocus();
			ans.setText("");
		}

		if (ansValidity != null) {
			contentPane.remove(ansValidity);
		}

		if (correctAnsReveal != null) {
			contentPane.remove(correctAnsReveal);
		}
		word.setText(words[index]);
		contentPane.remove(yourAns);
		if (check != null) {
			check.setEnabled(true);
		}
		contentPane.repaint();
	}
}
