import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;

public class Main extends JFrame {

	private static final long serialVersionUID = -6523370605519160263L;

	private JPanel contentPane;
	private JButton[] numButtons = new JButton[10],
			genderButtons = new JButton[3];
	private JButton check;
	private JLabel word, yourAns = new JLabel(), score, ansValidity,
			correctAnsReveal;
	private JTextField ans;
	private int index = 0, LANG = 0, gameChosen = -1, overallCounter,
			correctAnsCounter;
	private ImageIcon[] langIcons = {
			new ImageIcon(Main.class.getResource("engIcon.png")),
			new ImageIcon(Main.class.getResource("greekIcon.png")),
			new ImageIcon(Main.class.getResource("deutschIcon.png")) };
	private ImageIcon[] genderIcons = {
			new ImageIcon(Main.class.getResource("male.png")),
			new ImageIcon(Main.class.getResource("female.png")),
			new ImageIcon(Main.class.getResource("neutral.png")) };
	private ImageIcon checkIcon = new ImageIcon(
			Main.class.getResource("checkmark.png"));
	private ImageIcon xIcon = new ImageIcon(Main.class.getResource("xmark.png"));
	private ImageIcon restartIcon = new ImageIcon(
			Main.class.getResource("restart.png"));
	private Icon[] defaultDisabledGenderIcons = new Icon[3];

	private String[][] games = {
			{ "Verb Translation (free mode)", "Noun Gender (free mode)",
					"Verb Translation (test mode)", "Noun Gender (test mode)" },
			{ "Μετάφραση ρημάτων (απεριόριστο)",
					"Το γένος των ουσιαστικών (απεριόριστο)",
					"Μετάφραση ρημάτων (test)",
					"Το γένος των ουσιαστικών (test)" },
			{ "Verben Übersetzung (endlos)", "Nomen Geschlecht (endlos)",
					"Verben Übersetzung (Test)", "Nomen Geschlecht (Test)" } };

	private static int WINDOW_STARTING_X = 400;

	private static int WINDOW_STARTING_Y = 200;

	private static final int X_LANG_BUTTON_SIZE = 125;

	private static final int Y_LANG_BUTTON_SIZE = 100;

	private static final int X_GENDER_BUTTON_SIZE = 100;

	private static final int Y_GENDER_BUTTON_SIZE = 100;

	private static final int X_NUM_BUTTON_SIZE = 45;

	private static final int Y_NUM_BUTTON_SIZE = 40;

	private static final int X_GAP = 10;

	private static final int Y_GAP = 10;

	private static final int X_BEZEL = 6;

	private static final int Y_BEZEL = 40;

	private static final int SIMPLE_BUTTON_WIDTH = 70;

	private static final int SIMPLE_BUTTON_HEIGHT = 30;

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

	private Verb verb;
	private Verb[] verbs = new Verb[10];
	private Noun noun;
	private Noun[] nouns = new Noun[10];

	private JButton revealAns;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// WINDOW_STARTING_X =
		// (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2;
		// WINDOW_STARTING_Y =
		// (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight())/
		// 2;
		setLocation(WINDOW_STARTING_X, WINDOW_STARTING_Y);
		// setLocationRelativeTo(null);

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

		verb = Helper.getRandomVerb();
		word = new JLabel(verb.getVerb());
		word.setFont(new Font("Tahoma", Font.PLAIN, 16));
		word.setBounds(X_GAP, 5 * Y_GAP, 2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(word);

		ans = new JTextField();
		ans.setBounds(2 * X_GAP + word.getWidth(), 5 * Y_GAP,
				2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(ans);
		
		final JButton next = new JButton(new ImageIcon(
				Main.class.getResource("next.png")));
		next.setBounds(getWidth() - SIMPLE_BUTTON_HEIGHT - X_GAP, 21 * Y_GAP,
				SIMPLE_BUTTON_HEIGHT, SIMPLE_BUTTON_HEIGHT);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verb = Helper.getRandomVerb();
				removeOldAnsAndRepaint(verb.getVerb());
				check.setEnabled(true);
				revealAns.setEnabled(true);
				getRootPane().setDefaultButton(check);
			}
		});
		contentPane.add(next);
		
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ans.setText("");
				revealAns(word.getLocation().x, word.getLocation().y + 4
						* Y_GAP, "", verb.getCorrectAns(LANG),
						verb.checkTranslation(""));
				check.setEnabled(false);
				((JButton)arg0.getSource()).setEnabled(false);
				getRootPane().setDefaultButton(next);
			}
		};

		addRevealButton(X_GAP + ans.getWidth() + ans.getLocation().x,
				ans.getLocation().y, al);

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
							* Y_GAP, strAns, verb.getCorrectAns(LANG),
							verb.checkTranslation(strAns));
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
		boolean isAnsCorrect = verbs[this.index].checkTranslation(strAns);
		if (isAnsCorrect) {
			numButtons[index].setBackground(Color.green);
		} else {
			numButtons[index].setBackground(Color.RED);
		}
		numButtons[index].setEnabled(false);

		return isAnsCorrect;
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

		initializeRandomNouns();

		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = Integer
						.parseInt(((JButton) arg0.getSource()).getText()) - 1;
				removeOldAnsAndRepaint(nouns[index].getSingular());
			}
		};

		createAndAddNumButtons(al);

		JLabel prompt = new JLabel(NOUN_PROMPT[LANG] + " : ");
		prompt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prompt.setBounds(X_GAP, Y_NUM_BUTTON_SIZE + 2 * Y_GAP,
				2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(prompt);

		al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeOldAnsAndRepaint(null);
				playGenderTest();
			}
		};

		addResetButton(getWidth() - X_GAP - SIMPLE_BUTTON_HEIGHT - X_BEZEL,
				prompt.getLocation().y, al);

		word = new JLabel(nouns[index].getSingular());
		word.setFont(new Font("Tahoma", Font.PLAIN, 16));
		word.setBounds(2 * X_GAP + prompt.getWidth(), prompt.getLocation().y,
				2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(word);

		al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				disableGenderButtons();
				validateGenderAndDisableNumButton((JButton) arg0.getSource());

			}
		};

		createAndAddGenderButtons(getWidth() / 2
				- ((int) (1.5 * X_GENDER_BUTTON_SIZE) + X_GAP),
				prompt.getLocation().y + prompt.getHeight() + Y_GAP, al);

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
				* X_GAP + X_BEZEL, 7 * Y_GAP + Y_GENDER_BUTTON_SIZE
				+ SIMPLE_BUTTON_HEIGHT + Y_BEZEL);

		contentPane.removeAll();

		// reset
		index = 0;
		correctAnsCounter = 0;
		overallCounter = 0;

		JLabel prompt = new JLabel(NOUN_PROMPT[LANG] + " : ");
		prompt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prompt.setBounds(X_GAP, Y_GAP, 2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(prompt);

		score = new JLabel(formScoreString());
		score.setFont(new Font("Tahoma", Font.PLAIN, 16));
		score.setBounds(getWidth() - TEXT_SPACE[LANG] - X_GAP, Y_GAP,
				TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(score);

		noun = Helper.getRandomNoun();
		word = new JLabel(noun.getSingular());
		word.setFont(new Font("Tahoma", Font.PLAIN, 16));
		word.setBounds(2 * X_GAP + prompt.getWidth(), prompt.getLocation().y,
				2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(word);

		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int genderPicked = Integer
						.parseInt(((JButton) arg0.getSource()).getName());
				disableGenderButtons();
				if (noun.checkGender(genderPicked)) {
					((JButton) arg0.getSource()).setDisabledIcon(checkIcon);
					correctAnsCounter++;
				} else {
					((JButton) arg0.getSource()).setDisabledIcon(xIcon);
					genderButtons[noun.getGenderAsInt()]
							.setDisabledIcon(checkIcon);
				}
				overallCounter++;

				if (score != null) {
					score.setText(formScoreString());
				}

				contentPane.repaint();

			}
		};

		createAndAddGenderButtons(getWidth() / 2
				- ((int) (1.5 * X_GENDER_BUTTON_SIZE) + X_GAP),
				prompt.getLocation().y + prompt.getHeight() + Y_GAP, al);

		JButton next = new JButton(new ImageIcon(
				Main.class.getResource("next.png")));
		next.setBounds(getWidth() - SIMPLE_BUTTON_HEIGHT - X_GAP,
				prompt.getLocation().y + prompt.getHeight()
						+ Y_GENDER_BUTTON_SIZE + 2 * Y_GAP,
				SIMPLE_BUTTON_HEIGHT, SIMPLE_BUTTON_HEIGHT);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				noun = Helper.getRandomNoun();
				removeOldAnsAndRepaint(noun.getSingular());
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

		getRootPane().setDefaultButton(next);
	}

	private void playTranslationTest() {

		setBounds(getLocation().x, getLocation().y, 10 * X_NUM_BUTTON_SIZE + 11
				* X_GAP + X_BEZEL, 300);

		contentPane.removeAll();

		// reset index
		index = 0;

		initializeRandomVerbs();

		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = Integer
						.parseInt(((JButton) arg0.getSource()).getText()) - 1;
				removeOldAnsAndRepaint(verbs[index].getVerb());
			}
		};

		createAndAddNumButtons(al);

		JLabel prompt = new JLabel(VERB_PROMPT[LANG]);
		prompt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prompt.setBounds(X_GAP, Y_NUM_BUTTON_SIZE + 2 * Y_GAP,
				2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(prompt);

		al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeOldAnsAndRepaint(null);
				playTranslationTest();
			}
		};

		addResetButton(getWidth() - X_GAP - SIMPLE_BUTTON_HEIGHT - X_BEZEL,
				prompt.getLocation().y, al);

		word = new JLabel(verbs[index].getVerb());
		word.setFont(new Font("Tahoma", Font.PLAIN, 16));
		word.setBounds(X_GAP, Y_NUM_BUTTON_SIZE + 6 * Y_GAP,
				2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(word);

		ans = new JTextField();
		ans.setBounds(2 * X_GAP + word.getWidth(), Y_NUM_BUTTON_SIZE + 6
				* Y_GAP, 2 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(ans);

		al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ans.setText("");
				revealAns(word.getLocation().x, word.getLocation().y + 4
						* Y_GAP, "", verbs[index].getCorrectAns(LANG),
						validateAnsAndDisableButton(index, ""));
				check.setEnabled(false);
			}
		};

		addRevealButton(X_GAP + ans.getWidth() + ans.getLocation().x,
				ans.getLocation().y, al);

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
							* Y_GAP, strAns, verbs[index].getCorrectAns(LANG),
							validateAnsAndDisableButton(index, strAns));
					check.setEnabled(false);
				}

			}
		});
		getRootPane().setDefaultButton(check);
	}

	private void addRevealButton(int x, int y, ActionListener al) {
		revealAns = new JButton("Reveal");
		revealAns.setFont(new Font("Tahoma", Font.PLAIN, 10));
		revealAns.setBounds(x, y, SIMPLE_BUTTON_WIDTH, SIMPLE_BUTTON_HEIGHT);
		revealAns.addActionListener(al);
		contentPane.add(revealAns);
	}

	private void addResetButton(int x, int y, ActionListener al) {
		JButton restart = new JButton(restartIcon);
		restart.setBounds(x, y, SIMPLE_BUTTON_HEIGHT, SIMPLE_BUTTON_HEIGHT);
		restart.setToolTipText("Resets this game");
		restart.addActionListener(al);
		contentPane.add(restart);
	}

	private void initializeRandomVerbs() {

		for (int i = 0; i < 10; i++) {
			verbs[i] = Helper.getRandomVerb();
		}
	}

	private void initializeRandomNouns() {

		for (int i = 0; i < 10; i++) {
			nouns[i] = Helper.getRandomNoun();
		}
	}

	private void addHomeButton(int x, int y, int width, int height) {
		JButton home = new JButton(new ImageIcon(
				Main.class.getResource("home.png")));
		home.setBounds(x, y, width, height);
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createLangChoose();
			}
		});
		contentPane.add(home);
	}

	private void createAndAddNumButtons(ActionListener al) {

		for (int i = 0; i < 10; i++) {
			numButtons[i] = new JButton(String.valueOf(i + 1));
			numButtons[i].setFont(new Font("Tahoma", Font.PLAIN, 10));
			numButtons[i].setBounds((i + 1) * X_GAP + i * (X_NUM_BUTTON_SIZE),
					Y_GAP, X_NUM_BUTTON_SIZE, Y_NUM_BUTTON_SIZE);
			numButtons[i].addActionListener(al);
			contentPane.add(numButtons[i]);
		}
	}

	private void createAndAddGenderButtons(int x, int y, ActionListener al) {

		for (int i = 0; i < 3; i++) {
			genderButtons[i] = new JButton(genderIcons[i]);
			genderButtons[i].setName(String.valueOf(i));
			genderButtons[i].setBounds(x + i * (X_GENDER_BUTTON_SIZE + X_GAP),
					y, X_GENDER_BUTTON_SIZE, Y_GENDER_BUTTON_SIZE);
			genderButtons[i].addActionListener(al);
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
			genderButtons[i].setDisabledIcon(defaultDisabledGenderIcons[i]);
		}
	}

	private void validateGenderAndDisableNumButton(JButton button) {

		int genderPicked = Integer.parseInt(button.getName());
		boolean isAnsCorrect = nouns[index].checkGender(genderPicked);
		if (isAnsCorrect) {
			numButtons[index].setBackground(Color.green);
			button.setDisabledIcon(checkIcon);
		} else {
			numButtons[index].setBackground(Color.red);
			button.setDisabledIcon(xIcon);
			genderButtons[nouns[index].getGenderAsInt()]
					.setDisabledIcon(checkIcon);
		}
		numButtons[index].setEnabled(false);
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

	private void removeOldAnsAndRepaint(String newWord) {

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
			word.setText(newWord);
		}
		if (genderButtons[0] != null) {
			enableGenderButtons();
		}
		contentPane.repaint();
	}
}
