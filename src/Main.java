import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static final String[] EX1PROMPT = { "Write the translation",
			"Γράψε την μετάφραση", "Schreib die Übersetzung" };
	public static final String[] REMAINING_LIVES = { "Remaining lives",
			"Εναπομείνασες ζωές", "Verbleibende Leben" };
	public static final String[] CORRECT_ANS = { "The correct answer was ",
			"Η σωστή απάντηση ήταν ", "Die richtige Antwort war " };
	public static final String[] RIGHT = { "Right!", "Σωστό!", "Richtig!" };
	public static final String[] WRONG = { "Wrong", "Λάθος", "Falsch" };
	
	private static int LANG;
	public static int lives = 3;
	public static HashMap<String, ArrayList<String>> ENG_DICTIONARY = new HashMap<>();
	public static HashMap<String, ArrayList<String>> GRE_DICTIONARY = new HashMap<>();
	public static final int GREEK_WORDS_END = 9;

	public static void main(String[] args) {

		// File f = new File("Verb.csv");
		File f = new File("Verb.txt");
		Scanner sc = null;

		try {
			sc = new Scanner(f);
			createDictionary(sc);
			sc.close();
			sc = new Scanner(System.in);
			System.out.println("Choose instruction language: ");
			System.out.println("\t1. English");
			System.out.println("\t2. Ελληνικά");
			System.out.println("\t3. Deutsch");
			LANG = sc.nextInt() - 1;
			System.out.println(EX1PROMPT[LANG]);
			while (lives > 0) {
				play();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}

	}

	@SuppressWarnings("resource")
	private static void play() {

		Scanner sc = new Scanner(System.in);
		String question = getRandomWord();
		System.out.print(question + " : ");
		String ans = sc.nextLine().trim();

		validateAns(question, ans.trim());
	}

	private static void validateAns(String question, String ans) {
		
		int defaultLang;
		if (LANG == 2){
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
		System.out.println(CORRECT_ANS[LANG] + "\"" + answers.get(defaultLang).get(0)
				+ "\"");
		System.out.println(REMAINING_LIVES[LANG] + " : " + (--lives));
	}

	private static String getRandomWord() {

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
