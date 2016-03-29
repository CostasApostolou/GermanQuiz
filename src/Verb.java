import java.util.ArrayList;
import java.util.Random;

public class Verb {

	private String verb;
	private ArrayList<String> greekList= new  ArrayList<String>();
	private ArrayList<String> engList= new  ArrayList<String>();
	
	public Verb(String word){
		this.verb = word;
	}

	public void setEnglishList(ArrayList<String> list){
		this.engList = list;
	}
	
	public void setGreekList(ArrayList<String> list) {
		this.greekList = list;
	}
	
	public String getVerb(){
		return verb;
	}
	
	public boolean checkTranslation(String trans){
		return greekList.contains(trans.toLowerCase()) || engList.contains(trans.toLowerCase());
	}
	
	public String getCorrectAns(int lang){
			
		if (lang == 1){
			return greekList.get(new Random().nextInt(greekList.size()));
		} else {
			return engList.get(new Random().nextInt(engList.size()));
		}
	}
	
	
	
	
	


}
