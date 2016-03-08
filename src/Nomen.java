
public class Nomen {
	
	private String singular, plural;
	private char gender;
	
	public Nomen(String p){
		this('-', "-", p);
	}
	
	public Nomen(String s, char g){
		this(g, s, "-");
	}
	
	public Nomen(char g, String s, String p){
		if (!s.equals("-")){
			this.singular = s;
		}
		if (!p.equals("-")){
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
				
	public boolean checkGender(char ans){
		return this.gender == ans;
	}

}
