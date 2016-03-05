import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Main {
	
	public static final String[] EX1PROMPT = {"Write the translation", "Γράψε την μετάφραση", "Schreib die Übersetzung"};
	public static final String[] REMAINING_LIVES = {"Remaining lives", "Εναπομείνασες ζωές", "Verbleibende Leben"};
	public static final String[] CORRECT_ANS = {"The correct answer was ", "Η σωστή απάντηση ήταν ", "Die richtige Antwort war "};
	private static int LANG;
	public static int lives = 3;
	
	public static void main(String[] args){
		
		HashMap<String, ArrayList<String>> dictionary = new HashMap<>();
		File f = new File("Verb.csv");
		Scanner sc = null;
		
		try {
			sc = new Scanner(f);
			createDictionary(dictionary, sc);
			sc.close();
			sc = new Scanner(System.in);
			System.out.println("Choose instruction language: ");
			System.out.println("\t0. English");
			System.out.println("\t1. Ελληνικά");
			System.out.println("\t2. Deutsch");
			LANG = sc.nextInt();
			System.out.println(EX1PROMPT[LANG]);
			while (lives > 0){
				play(dictionary);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (sc != null){
				sc.close();
			}
		}
		
		
	}

	private static void play(HashMap<String, ArrayList<String>> dictionary) {

		Scanner sc = new Scanner(System.in);
		String question = getRandomWord(dictionary);
		System.out.print(question+" : ");
		String ans = sc.nextLine().trim();
		
		validateAns(dictionary, question, ans.trim());
	}

	private static void validateAns(
			HashMap<String, ArrayList<String>> dictionary, String question,
			String ans) {
		
		List<String> answers = dictionary.get(question);
		
		if (answers != null){
			for (String a : answers){
				if (ans.equalsIgnoreCase(a)){
					System.out.println("Richtig!! :-)");
					return;
				}
			}
		}
		
		System.out.println("Falsch :-(");
		System.out.println(CORRECT_ANS[LANG] + "\"" + answers.get(0) + "\"");
		System.out.println(REMAINING_LIVES[LANG]+" : "+(--lives));
	}

	private static String getRandomWord(
			HashMap<String, ArrayList<String>> dictionary) {

		Set<String> keys = dictionary.keySet();
		int rand = (int) (keys.size()*Math.random());
		return (String)keys.toArray()[rand];
	}

	@SuppressWarnings("unchecked")
	private static void createDictionary(
			HashMap<String, ArrayList<String>> dictionary, Scanner sc) {
		ArrayList<String> list = new ArrayList<String>(3);
		while(sc.hasNext()){
			String[] words = sc.nextLine().split(";");
			if (words.length == 2){
				list.add(words[1]);
			} else {
				for (int i = 1; i < words.length; i++){
					list.add(words[i]);
				}
			}
			dictionary.put(words[0], (ArrayList<String>)list.clone());
			list.clear();
			
		}
	}

}
