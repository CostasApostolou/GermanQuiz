import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Main {
	
	public static final String[] EX1PROMPT = {"Write the translation", "Γράψε την μετάφραση"};
	public static final String[] REMAINING_LIVES = {"Remaining lives", "Εναπομείνασες ζωές‚"};
	private static int LANG;
	
	public static void main(String[] args){
		
		HashMap<String, ArrayList<String>> dictionary = new HashMap<>();
		File f = new File("Verb.csv");
		Scanner sc = null;
		int lives = 3;
		try {
			sc = new Scanner(f);
			createDictionary(dictionary, sc);
			sc.close();
			sc = new Scanner(System.in);
			System.out.println("Choose instruction language: ");
			System.out.println("\t0. English");
			System.out.println("\t1. Ελληνικά");
			LANG = sc.nextInt();
			System.out.println(EX1PROMPT[LANG]);
			while (lives > 0){
				boolean res = play(dictionary);
				if (!res){
					System.out.println("Falsch :-(");
					System.out.println(REMAINING_LIVES[LANG]+" : "+(--lives));
				} else {
					System.out.println("Richtig!! :-)");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (sc != null){
				sc.close();
			}
		}
		
		
	}

	private static boolean play(HashMap<String, ArrayList<String>> dictionary) {

		Scanner sc = new Scanner(System.in);
		String question = getRandomWord(dictionary);
		System.out.print(question+" : ");
		String ans = sc.nextLine().trim();
		
		return isAnsCorrect(dictionary, question, ans.trim());
	}

	private static boolean isAnsCorrect(
			HashMap<String, ArrayList<String>> dictionary, String question,
			String ans) {
		
		List<String> answers = dictionary.get(question);
		
		if (answers != null){
			for (String a : answers){
				if (ans.equalsIgnoreCase(a)){
					return true;
				}
			}
		}
		
		return false;
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
