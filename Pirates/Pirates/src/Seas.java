import java.util.Random;


public class Seas {
    private int casesHorizontal;
    private int casesVertical;
    private char[][] grille ;
    private boolean presenceFish = false;
    public Seas(int casesHorizontal, int casesVertical)throws MapGenerationException {
        if(casesHorizontal<10 || casesVertical<10)throw new MapGenerationException();
        else{
        this.casesHorizontal = casesHorizontal;
        this.casesVertical = casesVertical;
        grille = new char[casesHorizontal][casesVertical];}
    }
    Fish sardines = new Fish(2, 2);


    //Methode Pour creer un ocean et le remplir avec mon bateau et des bateaux ennemies
    void createDefaultMap (BoatsFactory ...args){
        for (int i=0;i<this.casesHorizontal-1;i++){// creates sea everywhere
            for(int j=0;j<this.casesVertical;j++){
                this.grille[i][j]='S';
            }
        }
        this.grille[this.casesHorizontal-3][this.casesVertical-2]='u';//
        this.grille[this.casesHorizontal-2][this.casesVertical-2]='i';//
        this.grille[this.casesHorizontal-3][this.casesVertical-1]='j';//      u, i j , k  representent la boussole 
        this.grille[this.casesHorizontal-2][this.casesVertical-1]='k';//
        
        
        for(BoatsFactory boatIterator : args){// la mise de mon bateau et des bateaux ennemies dans des pos completement aleatoires
            Random rand = new Random();

            int x = rand.nextInt(casesHorizontal-4); int y = rand.nextInt(casesVertical-3);
            if(grille[x][y]!='S')continue;// si on a autre chose que du sea dans la position x,y on lance l exeption
            else boatIterator.setBoatPos(new Position (x,y));
            this.grille[boatIterator.getBoatPos().getHorizontal()][boatIterator.getBoatPos().getVertical()] = boatIterator.getBoatChar();
        }
        
        
        for(int j=0;j<this.casesVertical;j++){
            this.grille[this.casesHorizontal-1][j]='.';//Barres des statistiques du jeux
        }
        
       this.fishPlace(sardines);
        
        this.grille[this.casesHorizontal-1][0]='G';// icone Gold
        this.grille[this.casesHorizontal-1][3]='F';//icone Fish
        this.grille[this.casesHorizontal-1][6]='L';//icone vies
        
        this.updateScoresOnGrid((MyBoats)args[0]);// on promet le compilateur que arg 0 est de type MyBoat

    }

    private void updateScoresOnGrid(MyBoats b){
        switch(b.getGold()){
       /* case 9 :this.grille[this.casesHorizontal-1][1]='9';break;
        case 8 :this.grille[this.casesHorizontal-1][1]='8';break;
        case 7 :this.grille[this.casesHorizontal-1][1]='7';break;
        case 6 :this.grille[this.casesHorizontal-1][1]='6';break;
        case 5 :this.grille[this.casesHorizontal-1][1]='5';break;
        case 4 :this.grille[this.casesHorizontal-1][1]='4';break;*/
        case 3 :this.grille[this.casesHorizontal-1][1]='3';break;
        case 2 :this.grille[this.casesHorizontal-1][1]='2';break;
        case 1 :this.grille[this.casesHorizontal-1][1]='1';break;
        case 0 :this.grille[this.casesHorizontal-1][1]='0';break;
        default:this.grille[this.casesHorizontal-1][1]='M';
        if(b.getGold()<0)this.grille[this.casesHorizontal-1][1]='0';
        }
        switch(b.getFish()){
        /*case 10 :this.grille[this.casesHorizontal-1][4]='10';break;*/
      /* case 9 :this.grille[this.casesHorizontal-1][4]='9';break;
        case 8 :this.grille[this.casesHorizontal-1][4]='8';break;
        case 7 :this.grille[this.casesHorizontal-1][4]='7';break;
        case 6 :this.grille[this.casesHorizontal-1][4]='6';break;
        case 5 :this.grille[this.casesHorizontal-1][4]='5';break;
        case 4 :this.grille[this.casesHorizontal-1][4]='4';break;*/
        case 3 :this.grille[this.casesHorizontal-1][4]='3';break;
        case 2 :this.grille[this.casesHorizontal-1][4]='2';break;
        case 1 :this.grille[this.casesHorizontal-1][4]='1';break;
        case 0 :this.grille[this.casesHorizontal-1][4]='0';break;
        default:this.grille[this.casesHorizontal-1][4]='M';
        if(b.getFish()<0)this.grille[this.casesHorizontal-1][4]='0';
        }
        switch(b.getLifePoints()){
        case 9 :this.grille[this.casesHorizontal-1][7]='9';break;
        case 8 :this.grille[this.casesHorizontal-1][7]='8';break;
        case 7 :this.grille[this.casesHorizontal-1][7]='7';break;
        case 6 :this.grille[this.casesHorizontal-1][7]='6';break;
        case 5 :this.grille[this.casesHorizontal-1][7]='5';break;
        case 4 :this.grille[this.casesHorizontal-1][7]='4';break;
        case 3 :this.grille[this.casesHorizontal-1][7]='3';break;
        case 2 :this.grille[this.casesHorizontal-1][7]='2';break;
        case 1 :this.grille[this.casesHorizontal-1][7]='1';break;
        case 0 :this.grille[this.casesHorizontal-1][7]='0';
        		b.setBoatPos(new Position (casesHorizontal-1,casesVertical-1));
                break;//si myBoat meurt je le met a la corbeille
        default:this.grille[this.casesHorizontal-1][7]='M';
        if(b.getLifePoints()<0)this.grille[this.casesHorizontal-1][7]='0';
        }
    }

    
    // Methode pour mettre aleatoirement un fish en grille
    
    void fishPlace(Fish f ) {
    	 for(int i=0;i<this.casesHorizontal;i++){
             for(int j=0;j<this.casesVertical;j++){
            	 if(this.grille[i][j]=='f')this.grille[i][j]='S';// on cherche l ancien fish et on le supprime
             }
    	 }
    	Random rand = new Random();
        f.setHorizontal(rand.nextInt(casesHorizontal-4));  ; f.setVertical(rand.nextInt(casesVertical-3)); 
        
        while(grille[f.getHorizontal()][f.getVertical()]!='S'){
        	f.setHorizontal(rand.nextInt(casesHorizontal-4));  ; f.setVertical(rand.nextInt(casesVertical-3));
        }
        
        this.grille[f.getHorizontal()][f.getVertical()]=f.getFishChar();
    }
    
    
    public Fish getSardines() {
		return sardines;
	}

	//Methode Pour imprimer l'ocean cree sur console
    void print (){
        System.out.println("\n\n\n\n\n");
        for(int i=0;i<this.casesVertical;i++){
            for(int j=0;j<this.casesHorizontal;j++){

                System.out.print(this.grille[j][i]);
            }System.out.println();
        }
    }

    //Methode Qui permet de deplacer un boat.
    public void moveBoat(MyBoats boat ,Direction d ) {
        int x =boat.getBoatPos().getHorizontal();
        int y =boat.getBoatPos().getVertical();
        if(boat.getFish()>=3){
        	boat.setLifePoints(boat.getLifePoints()+1);// si j'ai plus de 3 poissons je gagne une vie
        	boat.setFish(0);
        }
        if(boat.getGold()>=5){
        	boat.setStrenght(boat.getStrenght()+1);//si j'ai plus de 5 pieces d or, je gagne de la force 
        	boat.setGold(0);
        }
        this.updateScoresOnGrid(boat);

        switch (d ) {
        case NORTH:
            if(y!=0 && this.grille[x][y-1]=='S'){
                boat.setBoatPos(new Position (x,y-1));
                this.grille[x][y]='S';
                this.grille[x][y-1]=boat.getBoatChar();
            }else {
                this.grille[x][y]=boat.getBoatChar();
                boat.setBoatPos(new Position(x,y));
            }
            break;

        case SOUTH:
            if(y!=this.casesVertical-1 && this.grille[x][y+1]=='S'  ){

                this.grille[x][y]='S';
                this.grille[x][y+1]=boat.getBoatChar();
                boat.setBoatPos(new Position (x,y+1));
            }else {
                this.grille[x][y]=boat.getBoatChar();
                boat.setBoatPos(new Position(x,y));
            }
            break;
        case EAST:
            if(x!=this.casesHorizontal-1 && this.grille[x+1][y]=='S'  ){
                boat.setBoatPos(new Position (x+1,y));
                this.grille[x][y]='S';
                this.grille[x+1][y]=boat.getBoatChar();
            }else {
                this.grille[x][y]=boat.getBoatChar();
                boat.setBoatPos(new Position(x,y));
            }
            break;
        case WEST:
            if(x!=0  && this.grille[x-1][y]=='S' ){
                boat.setBoatPos(new Position (x-1,y));
                this.grille[x][y]='S';
                this.grille[x-1][y]=boat.getBoatChar();
            }else {
                this.grille[x][y]=boat.getBoatChar();
                boat.setBoatPos(new Position(x,y));
            }
            break;
        }

    }
    public char[][] getGrille() {
        return grille;
    }

    //tester les positions de tous les bateaux ennemies par rapport a la mienne, je suis args[0]
    //si je suis dans le champ de vision, on m attaque
    
    
    public void testAllBoatsPositions (BoatsFactory ...args){
        for(BoatsFactory boatIterator : args){
            int myX=args[0].boatPos.getHorizontal();     int myY=args[0].boatPos.getVertical();
            int eX=boatIterator.boatPos.getHorizontal();     int eY=boatIterator.boatPos.getVertical();
            
            if ((myX==eX)&&((myY==eY+1)||(myY==eY-1))){
            	
            	boatIterator.attack(args[0]); //si je suis dans le champ de vision, on m attaque
            	
            	this.updateScoresOnGrid((MyBoats)args[0]);}
            
            else if ((myY==eY)&&((myX==eX+1)||(myX==eX-1))){
            	boatIterator.attack(args[0]);
            	this.updateScoresOnGrid((MyBoats)args[0]); //si je suis dans le champ de vision, on m attaque
            }
        }
    }
    
    //Methode qui permet de voir si on se trouve 
    public void testFishingPosition(MyBoats b){
    	int x=b.boatPos.getHorizontal();     int y=b.boatPos.getVertical();
    	
    	
    	if(x!=0 && grille[x-1][y]=='f'){((MyBoats) b).fishing();
    	presenceFish = true;
    	}
    	else if (grille[x+1][y]=='f'){
    		((MyBoats) b).fishing();
    		presenceFish = true;
    	}
    	else if (y!=0 && grille[x][y-1]=='f'){
    		((MyBoats) b).fishing();
    		presenceFish = true;
    	}
    	else if (y!=casesVertical-1 && grille[x][y+1]=='f'){
    		((MyBoats) b).fishing();
    		presenceFish = true;
    	}
    	if(b.getFish()>=4){
    		
        	b.setLifePoints(b.getLifePoints()+1);// si j'ai plus de 3 poissons je gagne une vie
        	if(b.getLifePoints()==11)b.setLifePoints(10);
        	b.setFish(0);
        }
    	this.updateScoresOnGrid((MyBoats)b);
    }
    
    // Methode qui permet de voir si les boats sont tjr en vie s'il sont mort on les met a la corbeille
    public void checkEnnemyStats(BoatsFactory ... args){
    	for(BoatsFactory boatItterator : args){
    	int x=boatItterator.getBoatPos().getHorizontal();
    	int y=boatItterator.getBoatPos().getVertical();
    	if(boatItterator.getLifePoints()<=0){
    		boatItterator.setBoatPos(new Position (casesHorizontal-1,casesVertical-1));
    		this.grille[x][y]='S';
    		
    		
    	}}
    }

	public boolean isPresenceFish() {
		return presenceFish;
	}

	public void setPresenceFish(boolean presenceFish) {
		this.presenceFish = presenceFish;
	}


}