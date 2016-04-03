import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Helper {

	private static ArrayList<Noun> nouns = new ArrayList<Noun>();
	private static ArrayList<Verb> verbs = new ArrayList<Verb>();

	private static InputStream verbStream = null;
	private static InputStream nounStream = null;
	private static Scanner sc = null;
	private static final int GREEK_WORDS_END = 9;

	static {
		createMaps();
	}

	public static void createMaps() {
		try {
			verbStream = Helper.class.getResourceAsStream("Verb.txt");
			nounStream = Helper.class.getResourceAsStream("Nomen.txt");
			sc = new Scanner(verbStream, "UTF-8");
			createVerbMap(sc);
			sc.close();
			sc = new Scanner(nounStream, "UTF-8");
			createNounMap(sc);
			sc.close();

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

	@SuppressWarnings("unchecked")
	private static void createVerbMap(Scanner sc) {

		ArrayList<String> list = new ArrayList<String>();

		while (sc.hasNext()) {
			String[] words = sc.nextLine().split(";");

			Verb v = new Verb(words[0]);

			// Greek iteration
			for (int i = 1; i < GREEK_WORDS_END; i++) {
				if (!words[i].equals("")) {
					list.add(words[i].toLowerCase());
				}
			}

			v.setGreekList((ArrayList<String>) list.clone());
			list.clear();
			// English iteration
			for (int i = GREEK_WORDS_END; i < words.length; i++) {
				if (!words[i].equals("")) {
					list.add(words[i].toLowerCase());
				}
			}
			v.setEnglishList((ArrayList<String>) list.clone());
			list.clear();

			verbs.add(v);
		}
	}

	private static void createNounMap(Scanner sc) throws Exception {

		while (sc.hasNext()) {
			String[] tokens = sc.nextLine().split(";");

			if (tokens.length != 3) {
				throw new Exception("Error in createNounMap");
			}
			nouns.add(new Noun(tokens[0].charAt(0), tokens[1], tokens[2]));
		}
	}

	public static Noun getRandomNoun() {

		int rand = 0;
		Noun ret = null;
		do {
			rand = (int) (nouns.size() * Math.random());
			ret = nouns.get(rand);
		} while (ret.getSingular() == null);

		return ret;
	}

	public static Verb getRandomVerb() {

		int rand = (int) (verbs.size() * Math.random());
		Verb ret = verbs.get(rand);
		return ret;
	}
	
	public static Noun[] getRandomNounArray(int size){
		
		HashSet<Noun> set = new HashSet<Noun>();
		
		while (set.size() < size){
			set.add(getRandomNoun());
		}
		
		return set.toArray(new Noun[10]);
	}
	
	public static Verb[] getRandomVerbArray(int size){
		
		HashSet<Verb> set = new HashSet<Verb>();
		
		while (set.size() < size){
			set.add(getRandomVerb());
		}
		
		return set.toArray(new Verb[10]);
	}

}
