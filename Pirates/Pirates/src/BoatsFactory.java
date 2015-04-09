
public abstract class BoatsFactory {
	protected char boatChar;
	protected int  lifePoints;
	protected int strenght;
	protected boolean token;
	protected Position boatPos= new Position(0, 0);
	
	//************************Constructeurs du bateau****************************************************

	public BoatsFactory(char boatChar, int lifePoints,int strenght, Position boatPos,boolean token) {
		
		this.boatChar=boatChar;
		this.lifePoints=lifePoints;
		this.strenght=strenght;
		this.boatPos=boatPos;
		this.token =token;
	}
	
	abstract public void attack(BoatsFactory b);
	public abstract void attackable(BoatsFactory ...args);//this method figures out if its possible to attack or not.
	
	
	
	public char getBoatChar() {
		return boatChar;
	}

	public void setBoatChar(char boatChar) {
		this.boatChar = boatChar;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public int getStrenght() {
		return strenght;
	}

	public void setStrenght(int strenght) {
		this.strenght = strenght;
	}

	

	public Position getBoatPos() {
		return boatPos;
	}

	public void setBoatPos(Position boatPos) {
		this.boatPos = boatPos;
	}

}
