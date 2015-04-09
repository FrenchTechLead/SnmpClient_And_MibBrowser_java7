


public class EnnemyBoat extends BoatsFactory {

	public EnnemyBoat(char boatChar, int lifePoints,int strenght, Position boatPos,boolean token) {
		super( boatChar, lifePoints, strenght, boatPos,token);
		
		this.boatChar=boatChar;
		this.lifePoints=lifePoints;
		this.strenght=strenght;
		this.boatPos=boatPos;
	}
	
	@Override
	public void attack(BoatsFactory b) {
	
    if(this.token){
    	b.setLifePoints(b.getLifePoints()-(this.strenght));
    	this.token=false;
    	b.token=true;
    }
    if(b.getLifePoints()<0)b.setLifePoints(0);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void attackable(BoatsFactory... args) {
		// TODO Auto-generated method stub
		
	}

}
