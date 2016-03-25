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

import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class GeneratedGUI extends JFrame {

	private JPanel contentPane;
	private JButton[] buttons = new JButton[10];
	private JButton check;
	private JLabel word, yourAns = new JLabel();
	private JTextField ans;
	private String[] words = { "word1", "word2", "word3", "word4", "word5",
			"word6", "word7", "word8", "word9", "word10" };
	private int index = 1, LANG = 0, gameChosen = -1;
	private ImageIcon[] langIcons = {
			new ImageIcon(GeneratedGUI.class.getResource("engIcon.png")),
			new ImageIcon(Main.class.getResource("greekIcon.png")),
			new ImageIcon(Main.class.getResource("deutschIcon.png")) };

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

	/**
	 * Create the frame.
	 */
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

		// for (int i = 2; i < 4; i++) {
		// JRadioButton radioButton = new JRadioButton(games[i]);
		// radioButton.setName(String.valueOf(i));
		// int multi = i % 2 == 0 ? 5 : 8;
		// radioButton.setBounds(2 * TEXT_SPACE[lang], multi * Y_GAP, 2 *
		// TEXT_SPACE[lang]
		// - X_GAP, 3 * Y_GAP);
		// radioButton.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		// gameChosen = Integer.parseInt(((JRadioButton) arg0
		// .getSource()).getName());
		// }
		// });
		// group.add(radioButton);
		// contentPane.add(radioButton);
		// }
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
		back.setBounds(9 * X_GAP + 6 * X_NUM_BUTTON_SIZE + SIMPLE_BUTTON_HEIGHT, 210,
				SIMPLE_BUTTON_WIDTH, SIMPLE_BUTTON_HEIGHT);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createGameChoose();
			}
		});
		contentPane.add(back);

		check = new JButton("Check");
		// check.setBounds(9 * X_GAP + 8 * X_NUM_BUTTON_SIZE, 210,
		// SIMPLE_BUTTON_WIDTH, SIMPLE_BUTTON_HEIGHT);
		check.setBounds(X_GAP + back.getLocation().x + back.getWidth(), 210,
				SIMPLE_BUTTON_WIDTH, SIMPLE_BUTTON_HEIGHT);
		contentPane.add(check);

		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String strAns = ans.getText();

				if (!strAns.equals("")) {
					validateAnsAndDisableButton(index, strAns);
					ans.setText("");
					revealAns(strAns);
				}

			}

			private void validateAnsAndDisableButton(int index, String strAns) {
				if (strAns.equals("word" + String.valueOf(index))) {
					buttons[index - 1].setBackground(Color.green);
				} else {
					buttons[index - 1].setBackground(Color.RED);
				}
				buttons[index - 1].setEnabled(false);
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
					onQuestionSelected((JButton) arg0.getSource());
				}
			});
			contentPane.add(buttons[i]);
		}

	}

	private void revealAns(String ans) {

		yourAns.setText("Your answer : " + ans);
		yourAns.setFont(new Font("Tahoma", Font.PLAIN, 16));
		yourAns.setBounds(X_GAP, Y_NUM_BUTTON_SIZE + 10 * Y_GAP,
				3 * TEXT_SPACE[LANG], 3 * Y_GAP);
		contentPane.add(yourAns);
		contentPane.repaint();
	}

	private void onQuestionSelected(JButton button) {

		index = Integer.parseInt(button.getText());
		word.setText(words[index - 1]);
		contentPane.remove(yourAns);
		contentPane.repaint();
	}
}
