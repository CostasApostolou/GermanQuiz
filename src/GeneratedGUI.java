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
	private JButton[] numButtons = new JButton[10],
			genderButtons = new JButton[3];
	private JButton check;
	private JLabel word, yourAns = new JLabel(), score, ansValidity,
			correctAnsReveal;
	private JTextField ans;
	private String[] words = { "word0", "word1", "word2", "word3", "word4",
			"word5", "word6", "word7", "word8", "word9" };
	private int index = 0, LANG = 0, gameChosen = -1, overallCounter,
			correctAnsCounter, rand;
	private ImageIcon[] langIcons = {
			new ImageIcon(GeneratedGUI.class.getResource("engIcon.png")),
			new ImageIcon(GeneratedGUI.class.getResource("greekIcon.png")),
			new ImageIcon(GeneratedGUI.class.getResource("deutschIcon.png")) };
	private ImageIcon[] genderIcons = {
			new ImageIcon(Main.class.getResource("male.png")),
			new ImageIcon(Main.class.getResource("female.png")),
			new ImageIcon(Main.class.getResource("neutral.png")) };
	private ImageIcon checkIcon = new ImageIcon(Main.class.getResource("checkmark.png"));
	private ImageIcon xIcon =new ImageIcon(Main.class.getResource("xmark.png"));


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
			X_GENDER_BUTTON_SIZE = 100, Y_GENDER_BUTTON_SIZE = 100,
			X_NUM_BUTTON_SIZE = 45, Y_NUM_BUTTON_SIZE = 40, X_GAP = 10,
			Y_GAP = 10, X_BEZEL = 6, Y_BEZEL = 40, SIMPLE_BUTTON_WIDTH = 70,
			SIMPLE_BUTTON_HEIGHT = 30;

	private static final int[] TEXT_SPACE = { 100, 130, 110 };

	private static final String[] CHOOSE_GAME_PROMPT = {
			"Please choose a game", "Παρακαλώ διαλέξτε ένα παιχνίδι",
			"Wählen Sie bitte ein Spiel" };
	private static final String[] VERB_PROMPT = { "Write the translation",
			"Γράψε την μετάφραση", "Schreib die Übersetzung" };
	private static final String[] NOUN_PROMPT = { "Find the noun's gender",
			"Βρες το γένος του ουσιαστικού", "Finde die Nomen Geschlecht" };
	private static final String[] CORRECT_ANS = { "The correct answer was : ",
			"Η σωστή απάντηση ήταν : ", "Die richtige Antwort war : " };
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
					playGenderFree();
					break;
				case 2:
					playTranslationTest();
					break;
				case 3:
					playGenderTest();
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

		JLabel prompt = new JLabel(VERB_PROMPT[LANG]);
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
							validateAns(rand, strAns));
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
			numButtons[index].setBackground(Color.green);
		} else {
			numButtons[index].setBackground(Color.RED);
		}
		numButtons[index].setEnabled(false);

		return isAnsCorrect;
	}

	private boolean validateAns(int index, String strAns) {
		return strAns.equals("word" + String.valueOf(index));
	}

	private String formScoreString() {
		return correctAnsCounter + " / " + overallCounter;
	}

	private void playGenderTest() {

		setBounds(getLocation().x, getLocation().y, 10 * X_NUM_BUTTON_SIZE + 11
				* X_GAP + X_BEZEL, 8 * Y_GAP + Y_NUM_BUTTON_SIZE
				+ Y_GENDER_BUTTON_SIZE + SIMPLE_BUTTON_HEIGHT + Y_BEZEL);

		contentPane.removeAll();

		// reset index
		index = 0;

		createAndAddNumButtons();

		JLabel prompt = new JLabel(NOUN_PROMPT[LANG] + " : ");
		prompt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prompt.setBounds(X_GAP, Y_NUM_BUTTON_SIZE + 2 * Y_GAP,
				2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(prompt);

		word = new JLabel(words[index]);
		word.setFont(new Font("Tahoma", Font.PLAIN, 16));
		word.setBounds(2 * X_GAP + prompt.getWidth(), prompt.getLocation().y,
				2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(word);

		createAndAddGenderButtons(getWidth() / 2
				- ((int) (1.5 * X_GENDER_BUTTON_SIZE) + X_GAP),
				prompt.getLocation().y + prompt.getHeight() + Y_GAP);

		JButton back = new JButton("Back");
		back.setBounds(getWidth() - SIMPLE_BUTTON_WIDTH - X_GAP, 7 * Y_GAP
				+ Y_NUM_BUTTON_SIZE + Y_GENDER_BUTTON_SIZE,
				SIMPLE_BUTTON_WIDTH, SIMPLE_BUTTON_HEIGHT);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createGameChoose();
			}
		});
		contentPane.add(back);

		addHomeButton(back.getLocation().x - X_GAP - SIMPLE_BUTTON_HEIGHT,
				back.getLocation().y, SIMPLE_BUTTON_HEIGHT,
				SIMPLE_BUTTON_HEIGHT);
	}

	private void playGenderFree() {

		setBounds(getLocation().x, getLocation().y, 10 * X_NUM_BUTTON_SIZE + 11
				* X_GAP + X_BEZEL, 300);

		contentPane.removeAll();

		// reset index
		index = 0;

		JLabel prompt = new JLabel(NOUN_PROMPT[LANG] + " : ");
		prompt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prompt.setBounds(X_GAP, Y_GAP, 2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(prompt);

		word = new JLabel(words[index]);
		word.setFont(new Font("Tahoma", Font.PLAIN, 16));
		word.setBounds(2 * X_GAP + prompt.getWidth(), prompt.getLocation().y,
				2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(word);

		createAndAddGenderButtons(getWidth() / 2
				- ((int) (1.5 * X_GENDER_BUTTON_SIZE) + X_GAP),
				prompt.getLocation().y + prompt.getHeight() + Y_GAP);

		JButton next = new JButton(new ImageIcon(
				GeneratedGUI.class.getResource("next.png")));
		next.setBounds(getWidth() - SIMPLE_BUTTON_HEIGHT - X_GAP,
				prompt.getLocation().y + prompt.getHeight()
						+ Y_GENDER_BUTTON_SIZE + 2 * Y_GAP,
				SIMPLE_BUTTON_HEIGHT, SIMPLE_BUTTON_HEIGHT);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rand = new Random().nextInt(words.length);
				removeOldAnsAndRepaint(rand);
				enableGenderButtons();
			}
		});
		contentPane.add(next);

		JButton back = new JButton("Back");
		back.setBounds(next.getLocation().x - SIMPLE_BUTTON_WIDTH - X_GAP,
				next.getLocation().y, SIMPLE_BUTTON_WIDTH, SIMPLE_BUTTON_HEIGHT);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createGameChoose();
			}
		});
		contentPane.add(back);

		addHomeButton(back.getLocation().x - X_GAP - SIMPLE_BUTTON_HEIGHT,
				back.getLocation().y, SIMPLE_BUTTON_HEIGHT,
				SIMPLE_BUTTON_HEIGHT);
		
		getRootPane().setDefaultButton(check);	
	}

	private void playTranslationTest() {

		setBounds(getLocation().x, getLocation().y, 10 * X_NUM_BUTTON_SIZE + 11
				* X_GAP + X_BEZEL, 300);

		contentPane.removeAll();

		// reset index
		index = 0;

		createAndAddNumButtons();

		JLabel prompt = new JLabel(VERB_PROMPT[LANG]);
		prompt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prompt.setBounds(X_GAP, Y_NUM_BUTTON_SIZE + 2 * Y_GAP,
				2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(prompt);

		word = new JLabel(words[index]);
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
							* Y_GAP, strAns, words[index],
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
			numButtons[i] = new JButton(String.valueOf(i + 1));
			numButtons[i].setFont(new Font("Tahoma", Font.PLAIN, 10));
			numButtons[i].setBounds((i + 1) * X_GAP + i * (X_NUM_BUTTON_SIZE),
					Y_GAP, X_NUM_BUTTON_SIZE, Y_NUM_BUTTON_SIZE);
			numButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					index = Integer.parseInt(((JButton) arg0.getSource())
							.getText()) - 1;
					removeOldAnsAndRepaint(index);
				}
			});
			contentPane.add(numButtons[i]);
		}
	}

	private void createAndAddGenderButtons(int x, int y) {

		for (int i = 0; i < 3; i++) {
			genderButtons[i] = new JButton(genderIcons[i]);
			genderButtons[i].setName(String.valueOf(i));
			genderButtons[i].setBounds(x + i * (X_GENDER_BUTTON_SIZE + X_GAP),
					y, X_GENDER_BUTTON_SIZE, Y_GENDER_BUTTON_SIZE);
			genderButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					disableGenderButtons();
					validateGenderAndDisableNumButton(Integer.parseInt(((JButton) arg0.getSource())
							.getName()));

				}
			});
			contentPane.add(genderButtons[i]);
		}
	}

	private void disableGenderButtons() {
		for (int i = 0; i < 3; i++) {
			genderButtons[i].setEnabled(false);
		}
	}

	private void enableGenderButtons() {
		for (int i = 0; i < 3; i++) {
			genderButtons[i].setEnabled(true);
		}
	}

	private void validateGenderAndDisableNumButton(int parseInt) {

		boolean isAnsCorrect = validateGenderAns();
		if (isAnsCorrect) {
			numButtons[index].setBackground(Color.green);
		} else {
			numButtons[index].setBackground(Color.red);
		}
		numButtons[index].setEnabled(false);
	}

	private boolean validateGenderAns() {
		return words[index].equals("");
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
		if (contentPane != null) {
			contentPane.remove(yourAns);
		}
		if (check != null) {
			check.setEnabled(true);
		}
		if (word != null) {
			word.setText(words[index]);
		}
		if (genderButtons[0] != null) {
			enableGenderButtons();
		}
		contentPane.repaint();
	}
}
