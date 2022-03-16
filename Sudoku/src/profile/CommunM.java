 package profile;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

abstract class CommunM extends Stage implements Commun{
	static boolean haut = false;
	static boolean bas = false;
	static boolean gauche = false;
	static boolean droit = false;
	static boolean accepter = false;
	static boolean quitter = false;
	static boolean interdit = false;
	static boolean supprimer = false;
	static String touche;
	
	public void definirFenetre() {
		setTitle("Sudoku"); 
		setResizable(false);
		setWidth(windowWidth);
		setHeight(windowHeight);
		Scene laScene = new Scene(creerContenu());
		setScene(laScene);
	}
	
	public static void obtenirTouches(KeyEvent e) {
		haut = e.getCode().equals(KeyCode.UP)||e.getCode().equals(KeyCode.Z);
		bas = e.getCode().equals(KeyCode.DOWN)||e.getCode().equals(KeyCode.S);
		gauche = e.getCode().equals(KeyCode.LEFT)||e.getCode().equals(KeyCode.Q);
		droit = e.getCode().equals(KeyCode.RIGHT)||e.getCode().equals(KeyCode.D);
		accepter = e.getCode().equals(KeyCode.ENTER);
		quitter = e.getCode().equals(KeyCode.ESCAPE);
		interdit = e.getCode().equals(KeyCode.TAB);
		supprimer = e.getCode().equals(KeyCode.BACK_SPACE);
		touche = e.getText();
	}
	
	protected abstract Parent creerContenu();
}
