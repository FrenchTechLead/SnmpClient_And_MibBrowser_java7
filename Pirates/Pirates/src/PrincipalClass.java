
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.Random;

import fr.umlv.lawrence.Application;
import fr.umlv.lawrence.BitmapImageProvider;
import fr.umlv.lawrence.DefaultGridModel;
import fr.umlv.lawrence.GridPane;
import fr.umlv.lawrence.InputListener;
import fr.umlv.lawrence.Key;



public class PrincipalClass {
	private static final int nbreCasesHorizontales =11;
	private static final int nbreCasesVerticales=11;
	private static final String[] boats = { "PLEIN D'OR", "PLEIN DE NOURITURE", "PUISSANT", "RESISTANT" };
	private static int deplacementMax=(nbreCasesHorizontales*nbreCasesVerticales)/2,s=0;
	public static void main(String[] args) {
		
		 Seas seaNotFinal = null;//////////////////////////////////////////////////////
		try {//////////////////////////////////////////////////////////////////////////
		seaNotFinal = new Seas(nbreCasesHorizontales, nbreCasesVerticales);////////////
		} catch (MapGenerationException e) {///////////////////////////////////////////Tout ca car atlantic doit etre final pour qu'on puisse l utiliser dans inputListener
			e.printStackTrace();///////////////////////////////////////////////////////
		}//////////////////////////////////////////////////////////////////////////////
		final Seas atlantic=seaNotFinal;/////////////////////////////////////////////// 
		
		
		JOptionPane.showMessageDialog(null, "En un temps fort reculé, naquit un héros.\n"
				+ "Faisant fit du danger comme tout héros qui se respecte,\n"
				+ "il juras de débarrasser le monde du fléau que représentait la piraterie!\n"
				+ "Il rendrait donc la justice au nom de sa majesté.\n"
				+ "Non content de débarrasser les mers des coquins,\n"
				+ "il défia quiconque de faire mieux que lui en seulement 100 déplacement!\n"
				+ "Mais quel est le nom de ce héros que l'histoire retiendra?"
				, "Histoire d'un prodige", JOptionPane.INFORMATION_MESSAGE);
				
		String boatName = JOptionPane.showInputDialog("Quel est ton nom corsaire ?", "Pirate");
		
		final BoatsFactory playerBoat ;
		JFrame frame = new JFrame("My Boat Selection");
	    String boatSelection = (String) JOptionPane.showInputDialog(frame, 
	        "Choisi ton bateau!",
	        "Un bateau de légende!",
	        JOptionPane.QUESTION_MESSAGE, 
	        null, 
	        boats, 
	        boats[0]);
		if(boatSelection==null)boatSelection="RESISTANT";
		
		switch (boatSelection) {
		case "PLEIN D'OR":          playerBoat= new MyBoats(boatName, 'B', 2, 4, 1,1, new Position(0,0),true);break;
		case "PLEIN DE NOURITURE":  playerBoat= new MyBoats(boatName, 'B', 2, 1, 4,1, new Position(0,0),true);break;
		case "PUISSANT":            playerBoat= new MyBoats(boatName, 'B', 2, 1, 1,4, new Position(0,0),true);break;
		case "RESISTANT":           playerBoat= new MyBoats(boatName, 'B', 4, 1, 1,1, new Position(0,0),true);break;
		default:                    playerBoat= new MyBoats(boatName, 'B', 4, 1, 1,1, new Position(0,0),true);break;
		}
		
		
		final BoatsFactory ennemy1 = new EnnemyBoat('E',1,1,new Position (0,0),true);
		final BoatsFactory ennemy2 = new EnnemyBoat('E',1,1,new Position (0,0),true);
		final BoatsFactory ennemy3 = new EnnemyBoat('E',1,1,new Position (0,0),true);
		final BoatsFactory ennemy4 = new EnnemyBoat('E',1,1,new Position (0,0),true);
		final BoatsFactory ennemy5 = new EnnemyBoat('E',1,1,new Position (0,0),true);
		
		
	
			atlantic.createDefaultMap(playerBoat,ennemy1,ennemy2,ennemy3,ennemy4,ennemy5);
		
		atlantic.print();
		System.out.println(((MyBoats) playerBoat).getBoatName());
		final DefaultGridModel<Sprites> world = new DefaultGridModel<Sprites>(nbreCasesHorizontales,nbreCasesVerticales);
		
		//**************Creation de L image Provider et ajout des images **********************
				BitmapImageProvider<Sprites> imProvider = new BitmapImageProvider<Sprites>();
				
				imProvider.registerImage(Sprites.SEA, PrincipalClass.class.getResource("/sea.png" ));
				
				imProvider.registerImage(Sprites.MYBOAT1_N,PrincipalClass.class.getResource("/MyBoat1/MyBoat1_1.png"));
				imProvider.registerImage(Sprites.MYBOAT1_S,PrincipalClass.class.getResource("/MyBoat1/MyBoat1_2.png"));
				imProvider.registerImage(Sprites.MYBOAT1_E,PrincipalClass.class.getResource("/MyBoat1/MyBoat1_3.png"));
				imProvider.registerImage(Sprites.MYBOAT1_W,PrincipalClass.class.getResource("/MyBoat1/MyBoat1_4.png"));
			    imProvider.registerImage(Sprites.EBOAT1,PrincipalClass.class.getResource("/EnnemiBoat1/E1.png"));
			    
				
			    imProvider.registerImage(Sprites.GOLD, PrincipalClass.class.getResource("/Options/images/gold.png" ));
				imProvider.registerImage(Sprites.FISH, PrincipalClass.class.getResource("/Options/images/fish.png" ));
				imProvider.registerImage(Sprites.LIVES, PrincipalClass.class.getResource("/Options/images/lives.png" ));
				imProvider.registerImage(Sprites.NUMBER0, PrincipalClass.class.getResource("/Options/images/0.png" ));
				imProvider.registerImage(Sprites.NUMBER1, PrincipalClass.class.getResource("/Options/images/1.png" ));
				imProvider.registerImage(Sprites.NUMBER2, PrincipalClass.class.getResource("/Options/images/2.png" ));
				imProvider.registerImage(Sprites.NUMBER3, PrincipalClass.class.getResource("/Options/images/3.png" ));
				imProvider.registerImage(Sprites.NUMBER4, PrincipalClass.class.getResource("/Options/images/4.png" ));
				imProvider.registerImage(Sprites.NUMBER5, PrincipalClass.class.getResource("/Options/images/5.png" ));
				imProvider.registerImage(Sprites.NUMBER6, PrincipalClass.class.getResource("/Options/images/6.png" ));
				imProvider.registerImage(Sprites.NUMBER7, PrincipalClass.class.getResource("/Options/images/7.png" ));
				imProvider.registerImage(Sprites.NUMBER8, PrincipalClass.class.getResource("/Options/images/8.png" ));
				imProvider.registerImage(Sprites.NUMBER9, PrincipalClass.class.getResource("/Options/images/9.png" ));
				imProvider.registerImage(Sprites.MAX, PrincipalClass.class.getResource("/Options/images/max.png" ));
				imProvider.registerImage(Sprites.BOUSSOLE1,PrincipalClass.class.getResource("/Boussole/Boussole1_01.png"));
				imProvider.registerImage(Sprites.BOUSSOLE2,PrincipalClass.class.getResource("/Boussole/Boussole1_02.png"));
				imProvider.registerImage(Sprites.BOUSSOLE3,PrincipalClass.class.getResource("/Boussole/Boussole1_03.png"));
				imProvider.registerImage(Sprites.BOUSSOLE4,PrincipalClass.class.getResource("/Boussole/Boussole1_04.png"));
				
				//*******************************Les Sprites sont stockes dans des ArrayLists*********************************
				
				final ArrayList<Sprites> arraySea = new ArrayList<Sprites>() ;
				arraySea.add(Sprites.SEA);
				final ArrayList<Sprites> arrayEnnemy = new ArrayList<Sprites>() ;
				arrayEnnemy.add(Sprites.SEA);
				arrayEnnemy.add(Sprites.EBOAT1);
				final ArrayList<Sprites> arrayOptionsBGound = new ArrayList<Sprites>() ;
				arrayOptionsBGound.add(Sprites.SEA);
				final ArrayList<Sprites> arrayBoat = new ArrayList<Sprites>() ;
				arrayBoat.add(Sprites.SEA);
				arrayBoat.add(Sprites.MYBOAT1_E);
				final ArrayList<Sprites> arrayGold = new ArrayList<Sprites>() ;
				arrayGold.add(Sprites.SEA);
				arrayGold.add(Sprites.GOLD);
				final ArrayList<Sprites> arrayFish = new ArrayList<Sprites>() ;
				arrayFish.add(Sprites.SEA);
				arrayFish.add(Sprites.FISH);
				final ArrayList<Sprites> arrayFish1 = new ArrayList<Sprites>() ;
				arrayFish1.add(Sprites.SEA);
				arrayFish1.add(Sprites.FISH);
				final ArrayList<Sprites> arrayLives = new ArrayList<Sprites>() ;
				arrayLives.add(Sprites.SEA);
				arrayLives.add(Sprites.LIVES);
				final ArrayList<Sprites> arrayNumZero = new ArrayList<Sprites>() ;
				arrayNumZero.add(Sprites.SEA);
				arrayNumZero.add(Sprites.NUMBER0);
				final ArrayList<Sprites> arrayNumOne = new ArrayList<Sprites>() ;
				arrayNumOne.add(Sprites.SEA);
				arrayNumOne.add(Sprites.NUMBER1);
				final ArrayList<Sprites> arrayNumTwo = new ArrayList<Sprites>() ;
				arrayNumTwo.add(Sprites.SEA);
				arrayNumTwo.add(Sprites.NUMBER2);
				final ArrayList<Sprites> arrayNumThree = new ArrayList<Sprites>() ;
				arrayNumThree.add(Sprites.SEA);
				arrayNumThree.add(Sprites.NUMBER3);
				final ArrayList<Sprites> arrayNumFour = new ArrayList<Sprites>() ;
				arrayNumFour.add(Sprites.SEA);
				arrayNumFour.add(Sprites.NUMBER4);
				final ArrayList<Sprites> arrayNumFive = new ArrayList<Sprites>() ;
				arrayNumFive.add(Sprites.SEA);
				arrayNumFive.add(Sprites.NUMBER5);
				final ArrayList<Sprites> arrayNumSix = new ArrayList<Sprites>() ;
				arrayNumSix.add(Sprites.SEA);
				arrayNumSix.add(Sprites.NUMBER6);
				final ArrayList<Sprites> arrayNumSeven = new ArrayList<Sprites>() ;
				arrayNumSeven.add(Sprites.SEA);
				arrayNumSeven.add(Sprites.NUMBER7);
				final ArrayList<Sprites> arrayNumEight = new ArrayList<Sprites>() ;
				arrayNumEight.add(Sprites.SEA);
				arrayNumEight.add(Sprites.NUMBER8);
				final ArrayList<Sprites> arrayNumNine = new ArrayList<Sprites>() ;
				arrayNumNine.add(Sprites.SEA);
				arrayNumNine.add(Sprites.NUMBER9);
				final ArrayList<Sprites> arrayNumMax = new ArrayList<Sprites>() ;
				arrayNumMax.add(Sprites.SEA);
				arrayNumMax.add(Sprites.MAX);
				final ArrayList<Sprites> arrayBoussole1 = new ArrayList<Sprites>();
				arrayBoussole1.add(Sprites.SEA);
				arrayBoussole1.add(Sprites.BOUSSOLE1);
				final ArrayList<Sprites> arrayBoussole2 = new ArrayList<Sprites>();
				arrayBoussole2.add(Sprites.SEA);
				arrayBoussole2.add(Sprites.BOUSSOLE2);
				final ArrayList<Sprites> arrayBoussole3 = new ArrayList<Sprites>();
				arrayBoussole3.add(Sprites.SEA);
				arrayBoussole3.add(Sprites.BOUSSOLE3);
				final ArrayList<Sprites> arrayBoussole4 = new ArrayList<Sprites>();
				arrayBoussole4.add(Sprites.SEA);
				arrayBoussole4.add(Sprites.BOUSSOLE4);
				
				//*************** Implementer un ecran aleatoire  de bienvenu ici ******************
				 ArrayList<Sprites> arrayRandom = new ArrayList<Sprites>();
						for (int i=0;i<nbreCasesHorizontales;i++ ){
							Random rand = new Random();
							int selection =rand.nextInt(4);
							switch (selection) {
							case 0:arrayRandom=arrayBoat;break;
							case 1:arrayRandom=arrayEnnemy;break;
							case 2:arrayRandom=arrayFish;break;
							case 3:arrayRandom=arrayGold;break;
							default:arrayRandom=arrayBoat;break;
												}
							
							for(int j =0;j<nbreCasesVerticales;j++){
								world.setDeffered(i, j,arrayRandom);}}
								
				
							
										
				//****************************Model contenant la grille *********************************
				
				final GridPane<Sprites> grid = new GridPane<Sprites>(world, imProvider, 50, 50);
				
				//************************************Thread InputListener pour clavier et souris**********************************
				InputListener listener = new InputListener() {
					
					@Override
					public void mouseClicked(int arg0, int arg1, int arg2) {
						
						
					}
					
					@Override
					public void keyTyped(int x, int y, Key Code) {
						
						if(Code.equals(Key.UP)){
							atlantic.moveBoat((MyBoats) playerBoat, Direction.NORTH);
							arrayBoat.remove(1);
							arrayBoat.add(Sprites.MYBOAT1_N);
							deplacementMax--;
						}else if (Code.equals(Key.DOWN)){
							atlantic.moveBoat((MyBoats) playerBoat, Direction.SOUTH);
							arrayBoat.remove(1);
							arrayBoat.add(Sprites.MYBOAT1_S);
							deplacementMax--;
						}else if(Code.equals(Key.LEFT)){
							atlantic.moveBoat((MyBoats) playerBoat, Direction.WEST);
							arrayBoat.remove(1);
							arrayBoat.add(Sprites.MYBOAT1_W);
							deplacementMax--;
						}else if(Code.equals(Key.RIGHT)){
							atlantic.moveBoat((MyBoats) playerBoat, Direction.EAST);
							arrayBoat.remove(1);
							arrayBoat.add(Sprites.MYBOAT1_E);
							deplacementMax--;
						}else if(Code.equals(Key.A)){
							playerBoat.attackable(ennemy1,ennemy2,ennemy3,ennemy4,ennemy5);
							atlantic.checkEnnemyStats(ennemy1,ennemy2,ennemy3,ennemy4,ennemy5);
						}else if(Code.equals(Key.F)){
						atlantic.testFishingPosition((MyBoats)playerBoat);
						if(atlantic.isPresenceFish()==true){
							atlantic.fishPlace(atlantic.getSardines());
							atlantic.setPresenceFish(false);
						}
						}
						
						
						atlantic.testAllBoatsPositions(playerBoat,ennemy1,ennemy2,ennemy3,ennemy4,ennemy5);

						atlantic.print();
						System.out.println("player life :"+playerBoat.getLifePoints());
						
						
						
						for (int i=0;i<nbreCasesHorizontales;i++ ){
							for(int j =0;j<nbreCasesVerticales;j++){
								char c =atlantic.getGrille()[i][j];
								switch (c) {
								
								case 'S': world.setDeffered(i, j,arraySea);break;
								case 'B': world.setDeffered(i, j,arrayBoat);break;
								case'.': world.setDeffered(i, j,arraySea);break;
								case 'u':world.setDeffered(i, j,arrayBoussole1);break;
								case 'i':world.setDeffered(i, j,arrayBoussole2);break;
								case 'j':world.setDeffered(i, j,arrayBoussole3);break;
								case 'k':world.setDeffered(i, j,arrayBoussole4);break;
								case 'E':world.setDeffered(i, j,arrayEnnemy);break;
								case 'L':world.setDeffered(i, j,arrayLives);break;
								case 'F':world.setDeffered(i, j,arrayFish);break;
								case 'f':world.setDeffered(i, j,arrayFish1);break;
								case 'G':world.setDeffered(i, j,arrayGold);break;
								case '0':world.setDeffered(i, j,arrayNumZero);break;
								case '1':world.setDeffered(i, j,arrayNumOne);break;
								case '2':world.setDeffered(i, j,arrayNumTwo);break;
								case '3':world.setDeffered(i, j,arrayNumThree);break;
								case '4':world.setDeffered(i, j,arrayNumFour);break;
								case '5':world.setDeffered(i, j,arrayNumFive);break;
								case '6':world.setDeffered(i, j,arrayNumSix);break;
								case '7':world.setDeffered(i, j,arrayNumSeven);break;
								case '8':world.setDeffered(i, j,arrayNumEight);break;
								case '9':world.setDeffered(i, j,arrayNumNine);break;
								case 'M':world.setDeffered(i, j,arrayNumMax);break;
								default : world.setDeffered(i, j,arraySea);break;
								
								}
								
								}} world.swap();
								//******************************* Si on gagne *********************************************
								
						int ennemyExistance=ennemy1.getLifePoints()+ennemy2.getLifePoints()+ennemy3.getLifePoints()+ennemy4.getLifePoints()+ennemy5.getLifePoints();
						if(ennemyExistance<=0){
							int score=(playerBoat.getLifePoints()+((MyBoats) playerBoat).getGold()+((MyBoats) playerBoat).getFish());
							JOptionPane.showMessageDialog(null, "Congrats "+((MyBoats) playerBoat).getBoatName()+" You just Won!\nVotre playTime est: "+s+"s\nVotre Score est: "+score);
							try {
								((MyBoats) playerBoat).saveScoreOnFile(score,s);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Application.close(grid);}
						//**************************************Si on perd **************************************************
						 if(deplacementMax==0||playerBoat.getLifePoints()==0){
							 int score=(playerBoat.getLifePoints()+((MyBoats) playerBoat).getGold()+((MyBoats) playerBoat).getFish());
							 JOptionPane.showMessageDialog(null, "Sorry "+((MyBoats) playerBoat).getBoatName()+" You Lost!\nVotre playTime est: "+s+"s\nVotre Score est: "+score);
							 Application.close(grid);
						 }
					}

				};
				
				//********************************* Thread pour calcul du temps de jeu *************************
				TimerTask playTimerTask = new TimerTask() {
					@Override
					public void run() {
						s++;
					}
				};
				Timer playtimer = new Timer();
				playtimer.schedule(playTimerTask, 0,1000);
				
						//**********************************************************************
						grid.addInputListener(listener);
						world.swap();
						Application.display(grid,"Pirates" , false, true);
	}
}
