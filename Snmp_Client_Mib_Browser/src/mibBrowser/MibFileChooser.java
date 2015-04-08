package mibBrowser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

public class MibFileChooser  {

	public MibFileChooser(){
	}
	
	
	static public File getFile() throws IOException{
	JFileChooser dialogue = new JFileChooser(new File("./src/mibs"));
	PrintWriter sortie;
	File fichier=null;
	
	if (dialogue.showOpenDialog(null)== 
	    JFileChooser.APPROVE_OPTION) {
	    fichier = dialogue.getSelectedFile();
	    sortie = new PrintWriter
		(new FileWriter(fichier.getPath(), true));
	    
	    sortie.close();
	    
	}
	return fichier;}
}
