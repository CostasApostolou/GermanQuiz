import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.MULTIANEWARRAY;

public class Main {

	private static final String[] VERB_PROMPT = { "Write the translation",
			"Γράψε την μετάφραση", "Schreib die Übersetzung" };
	private static final String[] NOUN_PROMPT = { "Find the noun's gender.",
			"Βρες το γένος του ουσιαστικού.", "Finde die Nomen Geschlecht." };
	private static final String[] NOUN_INSTRUCTIONS = {
			"Give:\n\tm for masculine\n\tf for feminine or\n\tn for neuter",
			"Δώσε:\n\tα για αρσενικό\n\tθ για θυληκό ή\n\tο για ουδέτερο",
			"Gebe:\n\tr für Maskulinum\n\te für Femininum aber\n\ts für Neutrum" };
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
	private static final String[] FEMININE = { "feminine", "θυληκό",
			"Femininum" };
	private static final String[] NEUTER = { "neuter", "ουδέτερο", "Neutrum" };
	private static HashMap<Character, String[]> MULTILINGUAL_GENDERS = new HashMap<>();

	private static int LANG;
	private static int lives = 3;
	private static HashMap<String, ArrayList<String>> ENG_DICTIONARY = new HashMap<>();
	private static HashMap<String, ArrayList<String>> GRE_DICTIONARY = new HashMap<>();
	private static ArrayList<Nomen> nouns = new ArrayList<Nomen>();
	private static final int GREEK_WORDS_END = 9;

	public static void main(String[] args) {

		File verbs = new File("Verb.txt");
		File nounFile = new File("Nomen.txt");
		Scanner sc = null;

		try {
			sc = new Scanner(verbs);
			createDictionary(sc);
			sc.close();
			sc = new Scanner(nounFile);
			createNounCollection(sc);
			sc.close();
			initializeMultiLangGenderMap();
			sc = new Scanner(System.in);
			System.out.println("Choose instruction language: ");
			System.out.println("\t1. English");
			System.out.println("\t2. Ελληνικά");
			System.out.println("\t3. Deutsch");
			LANG = sc.nextInt() - 1;
			System.out.println(CHOOSE_GAME[LANG]);
			System.out.println("\t1. " + VERB_GAME[LANG]);
			System.out.println("\t2. " + NOUN_GAME[LANG]);
			int option = sc.nextInt();
			switch (option) {
			case 1:
				System.out.println(VERB_PROMPT[LANG]);
				while (lives > 0) {
					playVerbGame();
				}
				break;
			case 2:
				System.out.println(NOUN_PROMPT[LANG]);
				System.out.println(NOUN_INSTRUCTIONS[LANG]);
				while (lives > 0) {
					playNounGame();
				}
				break;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}

	}

	private static void initializeMultiLangGenderMap() {
		MULTILINGUAL_GENDERS.put('r', MASCULINE);
		MULTILINGUAL_GENDERS.put('e', FEMININE);
		MULTILINGUAL_GENDERS.put('s', NEUTER);
	}

	private static void createNounCollection(Scanner sc) {

		while (sc.hasNext()) {
			String[] tokens = sc.nextLine().split(";");

			if (tokens.length != 3) {
				System.err.println("Holy fuck!!");
			}
			nouns.add(new Nomen(tokens[0].charAt(0), tokens[1], tokens[2]));
		}
	}

	@SuppressWarnings("resource")
	private static void playVerbGame() {

		Scanner sc = new Scanner(System.in);
		String question = getRandomVerb();
		System.out.print(question + " : ");
		String ans = sc.nextLine().trim();

		validateAns(question, ans.trim());
	}

	@SuppressWarnings("resource")
	private static void playNounGame() {

		Scanner sc = new Scanner(System.in);
		Nomen noun = getRandomNoun();
		System.out.print(noun.getSingular() + " : ");
		char ans = sc.nextLine().trim().charAt(0);

		switch (LANG) {
		case 0:
		case 1:
			ans = convertAnsToGerman(ans);
			break;
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

	private static char convertAnsToGerman(char ans) {

		if (ans == 'm' || ans == 'α') {
			return 'r';
		} else if (ans == 'f' || ans == 'θ') {
			return 'e';
		} else if (ans == 'n' || ans == 'ο') {
			return 's';
		} else {
			return ans;
		}
	}

	private static Nomen getRandomNoun() {

		int rand = (int) (nouns.size() * Math.random());
		return nouns.get(rand);
	}

	private static void validateAns(String question, String ans) {

		int defaultLang;
		if (LANG == 2) {
			defaultLang = 0;
		} else {
			defaultLang = LANG;
		}

		ArrayList<String> greekAnswers = GRE_DICTIONARY.get(question);
		ArrayList<String> engAnswers = ENG_DICTIONARY.get(question);
		ArrayList<ArrayList<String>> answers = new ArrayList<ArrayList<String>>(
				greekAnswers.size() + engAnswers.size());

		answers.add(ENG_DICTIONARY.get(question));
		answers.add(GRE_DICTIONARY.get(question));

		if (answers != null) {
			for (List<String> listAnswers : answers) {
				for (String a : listAnswers) {
					if (ans.equalsIgnoreCase(a)) {
						System.out.println(RIGHT[LANG]);
						return;
					}
				}
			}
		}

		System.out.println(WRONG[LANG]);
		System.out.println(CORRECT_ANS[LANG] + "\""
				+ answers.get(defaultLang).get(0) + "\"");
		System.out.println(REMAINING_LIVES[LANG] + " : " + (--lives));
	}

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
