import javax.swing.JOptionPane;


public class MapGenerationException extends Exception {

	private static final long serialVersionUID = 1L;

	public MapGenerationException() {
		super();
		JOptionPane.showMessageDialog(null,"Map Generation issue, map size can't be less than (10x10)\nPlease reload game","ERROR", 0);
		
	}
	
}
