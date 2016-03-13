import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Main {

	private static final String[] VERB_PROMPT = { "Write the translation",
			"Γράψε την μετάφραση", "Schreib die Übersetzung" };
	private static final String[] NOUN_PROMPT = { "Find the noun's gender.",
			"Βρες το γένος του ουσιαστικού.", "Finde die Nomen Geschlecht." };
	private static final String[] REMAINING_LIVES = { "Remaining lives",
			"Εναπομείνασες ζωές", "Verbleibende Leben" };
	private static final String[] CORRECT_ANS = { "The correct answer was ",
			"Η σωστή απάντηση ήταν ", "Die richtige Antwort war " };
	private static final String[] RIGHT = { "Right!", "Σωστό!", "Richtig!" };
	private static final String[] WRONG = { "Wrong", "Λάθος", "Falsch" };
	private static final String[] CHOOSE_GAME = { "Please choose a game",
			"Παρακαλώ διάλεξε ένα παιχνίδι", "Wählen Sie bitte ein Spiel" };
	private static final String[] VERB_GAME = { "Verb translation",
			"Μετάφραση ρημάτων", "Verben Übersetzung" };
	private static final String[] NOUN_GAME = { "Noun gender",
			"Το γένος των ουσιαστικών", "Nomen Geschlecht" };
	private static final String[] MASCULINE = { "masculine", "αρσενικό",
			"Maskulinum" };
	private static final String[] FEMININE = { "feminine", "θηλυκό",
			"Femininum" };
	private static final String[] NEUTER = { "neuter", "ουδέτερο", "Neutrum" };
	private static HashMap<Character, String[]> MULTILINGUAL_GENDERS = new HashMap<>();

	private static int LANG;
	private static int lives = 3;
	private static HashMap<String, ArrayList<String>> ENG_DICTIONARY = new HashMap<>();
	private static HashMap<String, ArrayList<String>> GRE_DICTIONARY = new HashMap<>();
	private static ArrayList<Nomen> nouns = new ArrayList<Nomen>();
	private static final int GREEK_WORDS_END = 9;

	private static Object[] langIcons = {
			new ImageIcon(Main.class.getResource("engIcon.png")),
			new ImageIcon(Main.class.getResource("greekIcon.png")),
			new ImageIcon(Main.class.getResource("deutschIcon.png")) };

	private static Object[] genderIcons = {
			new ImageIcon(Main.class.getResource("male.png")),
			new ImageIcon(Main.class.getResource("female.png")),
			new ImageIcon(Main.class.getResource("neutral.png")) };

	public static void main(String[] args) {

		InputStream verbStream = null, nounStream = null;
		Scanner sc = null;

		try {
			verbStream = Main.class.getResourceAsStream("Verb.txt");
			nounStream = Main.class.getResourceAsStream("Nomen.txt");
			sc = new Scanner(verbStream);
			createDictionary(sc);
			sc.close();
			sc = new Scanner(nounStream);
			createNounCollection(sc);
			sc.close();
			initializeMultiLangGenderMap();

			LANG = chooseLangBox();
			if (LANG == -1) {
				return;
			}

			while (true) {
				int option = chooseGameBox();

				switch (option) {
				case -1:
					return;
				case 0:
					playVerbGame();
					break;
				case 1:
					playNounGame();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"An error occured while trying to initialize the games");
		} finally {
			if (sc != null) {
				sc.close();
			}
		}

	}

	private static int chooseGameBox() {
		return JOptionPane.showOptionDialog(null, CHOOSE_GAME[LANG], null,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { VERB_GAME[LANG], NOUN_GAME[LANG] }, null);
	}

	private static int chooseLangBox() {

		return JOptionPane.showOptionDialog(null,
				"Choose instruction language:", null,
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, langIcons, null);

	}

	private static void initializeMultiLangGenderMap() {
		MULTILINGUAL_GENDERS.put('r', MASCULINE);
		MULTILINGUAL_GENDERS.put('e', FEMININE);
		MULTILINGUAL_GENDERS.put('s', NEUTER);
	}

	private static void createNounCollection(Scanner sc) throws Exception {

		while (sc.hasNext()) {
			String[] tokens = sc.nextLine().split(";");

			if (tokens.length != 3) {
				throw new Exception("Error in createNounCollection");
			}
			nouns.add(new Nomen(tokens[0].charAt(0), tokens[1], tokens[2]));
		}
	}

	private static void playVerbGame() {

		String question = getRandomVerb();
		JOptionPane
				.showMessageDialog(null, VERB_PROMPT[LANG] + "\n" + question);

		// validateAns(question, ans.trim());
	}

	private static void playNounGame() {

		Nomen noun = getRandomNoun();

		int ans = JOptionPane.showOptionDialog(null, NOUN_PROMPT[LANG] + "\n"
				+ noun.getSingular(), null, JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, genderIcons, null);

		if (ans == -1) {
			System.exit(0);
		}

		if (noun.checkGender(ans)) {
			System.out.println(RIGHT[LANG]);
		} else {
			System.out.println(WRONG[LANG]);
			System.out.println(CORRECT_ANS[LANG] + "\""
					+ MULTILINGUAL_GENDERS.get(noun.getGender())[LANG] + "\"");
			System.out.println(REMAINING_LIVES[LANG] + " : " + (--lives));
		}
	}

	private static Nomen getRandomNoun() {

		int rand = 0;
		Nomen ret = null;
		do {
			rand = (int) (nouns.size() * Math.random());
			ret = nouns.get(rand);
		} while (ret.getSingular() == null);

		return ret;
	}

	// private static void validateAns(String question, String ans) {
	//
	// int defaultLang;
	// if (LANG == 2) {
	// defaultLang = 0;
	// } else {
	// defaultLang = LANG;
	// }
	//
	// ArrayList<String> greekAnswers = GRE_DICTIONARY.get(question);
	// ArrayList<String> engAnswers = ENG_DICTIONARY.get(question);
	// ArrayList<ArrayList<String>> answers = new ArrayList<ArrayList<String>>(
	// greekAnswers.size() + engAnswers.size());
	//
	// answers.add(ENG_DICTIONARY.get(question));
	// answers.add(GRE_DICTIONARY.get(question));
	//
	// if (answers != null) {
	// for (List<String> listAnswers : answers) {
	// for (String a : listAnswers) {
	// if (ans.equalsIgnoreCase(a)) {
	// System.out.println(RIGHT[LANG]);
	// return;
	// }
	// }
	// }
	// }
	//
	// System.out.println(WRONG[LANG]);
	// System.out.println(CORRECT_ANS[LANG] + "\""
	// + answers.get(defaultLang).get(0) + "\"");
	// System.out.println(REMAINING_LIVES[LANG] + " : " + (--lives));
	// }

	private static String getRandomVerb() {

		Set<String> keys = ENG_DICTIONARY.keySet();
		int rand = (int) (keys.size() * Math.random());
		return (String) keys.toArray()[rand];
	}

	@SuppressWarnings("unchecked")
	private static void createDictionary(Scanner sc) {

		ArrayList<String> list = new ArrayList<String>();

		while (sc.hasNext()) {
			String[] words = sc.nextLine().split(";");

			// Greek iteration
			for (int i = 1; i < GREEK_WORDS_END; i++) {
				if (!words[i].equals("")) {
					list.add(words[i]);
				}
			}

			GRE_DICTIONARY.put(words[0], (ArrayList<String>) list.clone());
			list.clear();
			// English iteration
			for (int i = GREEK_WORDS_END; i < words.length; i++) {
				list.add(words[i]);
			}
			ENG_DICTIONARY.put(words[0], (ArrayList<String>) list.clone());
			list.clear();
		}
	}

}
