
public class Position {
	private int horizontal;
	private int vertical;

		public Position(int h, int v) {
		this.horizontal = h;
		this.vertical = v;
	}
	
	public int getHorizontal() {return horizontal;}
	
	public int getVertical(){return vertical;}
	
	public void setHorizontal(int h) {this.horizontal = h;}
	
	public void setVertical(int v) {this.vertical = v;}
}
