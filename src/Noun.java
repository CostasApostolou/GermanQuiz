public class Noun {

	private String singular, plural;
	private char gender;

	public Noun(String p) {
		this('-', "-", p);
	}

	public Noun(String s, char g) {
		this(g, s, "-");
	}

	public Noun(char g, String s, String p) {
		if (!s.equals("-")) {
			this.singular = s;
		}
		if (!p.equals("-")) {
			this.plural = p;
		}
		this.gender = g;
	}

	public String getSingular() {
		return singular;
	}

	public String getPlural() {
		return plural;
	}

	public char getGender() {
		return gender;
	}
	
	public String getNounWithArticle(){
		switch (gender) {
		case 'r':
			return "der "+singular;
		case 'e':
			return "die "+singular;
		case 's':
			return "das "+singular;
		default:
			return null;
		}
	}

	public int getGenderAsInt() {
		switch (gender) {
		case 'r':
			return 0;
		case 'e':
			return 1;
		case 's':
			return 2;
		default:
			return 0;
		}
	}

	public boolean checkGender(char ans) {
		return this.gender == ans;
	}

	public boolean checkGender(int ans) {
		switch (ans) {
		case 0:
			return (this.gender == 'r');
		case 1:
			return (this.gender == 'e');
		case 2:
			return (this.gender == 's');
		default:
			return false;
		}
	}

}
