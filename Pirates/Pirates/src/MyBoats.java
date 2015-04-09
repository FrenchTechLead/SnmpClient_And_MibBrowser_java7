import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class MyBoats extends BoatsFactory {
	private String boatName;

	private int gold,fish;
	public MyBoats(String boatName, char boatChar, int lifePoints,int gold,int fish,int strenght, Position boatPos,boolean token) {
		super(boatChar, lifePoints, strenght, boatPos,token);
		this.boatName=boatName;
		this.boatChar=boatChar;
		this.lifePoints=lifePoints;
		this.gold=gold;
		this.fish=fish;
		this.strenght=strenght;
		this.boatPos=boatPos;
	}
	
	@Override
	public void attack(BoatsFactory b) {
		 if(this.token){
			 b.setLifePoints(b.getLifePoints()-(this.strenght));// jattaque l ennemi
			 this.token=false;
			 b.token=true;
		 }
		if(b.getLifePoints()<=0){//
			this.gold=this.gold+1;//  si je detruit un ennemy je gagne de l or et du poisson
			this.fish=this.fish+1;//
		}
		if(b.getLifePoints()<0)b.setLifePoints(0);
	}
	
	@Override
	public void attackable(BoatsFactory ...args){//mon bateau verifie que le bateau ennemy est dans le champs de vision avant d attaquer
		for(BoatsFactory boatIterator : args){
            int myX=this.getBoatPos().getHorizontal();     int myY=this.getBoatPos().getVertical();
            int eX=boatIterator.boatPos.getHorizontal();     int eY=boatIterator.boatPos.getVertical();
     
            if ((myX==eX)&&((myY==eY+1)||(myY==eY-1))){
            this.attack(boatIterator);
          
            }else if ((myY==eY)&&((myX==eX+1)||(myX==eX-1))){
            this.attack(boatIterator);
            }
        }
	}
	
	//*********************************** Methode qui permet d'afficher le score et de l'enregistrer dans un fichier************************************
	public void saveScoreOnFile(int score,int timePlay) throws IOException{
		List<String> listeDeLignes = new ArrayList<String>();
		try {
			listeDeLignes=Files.readAllLines(Paths.get("Scores", "scores.scr"),StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		String str = "";
		int nbDeLigneDeScore =0;
		for(String iterator : listeDeLignes ){
			str=str+iterator+"\n";
			nbDeLigneDeScore++;
		}
		if(nbDeLigneDeScore>=15){// si on a plus de 15 lignes de scores on efface tout 
			PrintWriter pWriter = new PrintWriter("Scores/scores.scr");
			pWriter.print("");
			pWriter.close();
			str="";
		}
			String str1=str+"Player:  "+this.boatName+"    TimePlay: "+timePlay+"   Score:"+score+"\n";
			FileWriter writer = null;
			try{
			     writer = new FileWriter("Scores/scores.scr", true);
			     writer.write(str1,0,str1.length());
			}catch(IOException ex){
			    ex.printStackTrace();
			}finally{
			  if(writer != null){
			     writer.close();
			  }
			}
			
			
			
			
			
			
			JOptionPane.showMessageDialog(null,str1,"BEST SCORES",1);
	}
	
	void fishing(){
		this.fish++;
	}
	
	public String getBoatName() {
		return boatName;
	}

	public void setBoatName(String boatName) {
		this.boatName = boatName;
	}
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getFish() {
		return fish;
	}

	public void setFish(int fish) {
		this.fish = fish;
	}

	
}
