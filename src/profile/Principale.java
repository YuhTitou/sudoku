package profile;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;

public class Principale extends Application{
	static Stage primaryStage;
	
	static final Menu menu = new Menu();
	static final ListeProfil listeProfil = new ListeProfil();
	static final Regles regles = new Regles();
	static final Grille grille = new Grille();
	static final Pause pause = new Pause();
	static final Niveaux niveaux1 = new Niveaux(0);
	static final Niveaux niveaux2 = new Niveaux(1);
	static final Niveaux niveaux3 = new Niveaux(2);
	static final Niveaux niveaux4 = new Niveaux(3);
	static final Niveaux niveaux5 = new Niveaux(4);
	
	static final Scene sceneMenu = menu.getScene();
	static final Scene sceneListeProfil = listeProfil.getScene();
	static final Scene sceneRegles = regles.getScene();
	static final Scene sceneGrille = grille.getScene();
	static final Scene scenePause = pause.getScene();
	static final Scene sceneNiveaux1 = niveaux1.getScene();
	static final Scene sceneNiveaux2 = niveaux2.getScene();
	static final Scene sceneNiveaux3 = niveaux3.getScene();
	static final Scene sceneNiveaux4 = niveaux4.getScene();
	static final Scene sceneNiveaux5 = niveaux5.getScene();
	
	static final Scene sceneProfil1 = ListeProfil.getProfil1().getScene();
	static final Scene sceneProfil2 = ListeProfil.getProfil2().getScene();
	static final Scene sceneProfil3 = ListeProfil.getProfil3().getScene();
	static final Scene sceneProfil4 = ListeProfil.getProfil4().getScene();
	static final Scene sceneProfil5 = ListeProfil.getProfil5().getScene();
	
	public void start(Stage primaryStage) {
		Principale.primaryStage = new Menu();
		Principale.primaryStage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	static public void toListProfilD() {
		listeProfil.setEstDetail(true);
		primaryStage.setScene(sceneListeProfil);
	}
	static public void toListProfilJ() {
		listeProfil.setEstDetail(false);
		primaryStage.setScene(sceneListeProfil);
	}	
	static public void toMenu() {
		primaryStage.setScene(sceneMenu);
	}	
	static public void toProfil(int i) {
		switch(i) {
		case 0: primaryStage.setScene(sceneProfil1); break;
		case 1: primaryStage.setScene(sceneProfil2); break;
		case 2: primaryStage.setScene(sceneProfil3); break;
		case 3: primaryStage.setScene(sceneProfil4); break;
		case 4: primaryStage.setScene(sceneProfil5); break;
		}
	}	
	static public void toRegles() {
		primaryStage.setScene(sceneRegles);
	}
	static public void toGrille() {
		primaryStage.setScene(sceneGrille);
	}
	static public void toPause() {
		primaryStage.setScene(scenePause);
	}
	static public void toNiveaux(int i) {
		switch(i) {
		case 0: primaryStage.setScene(sceneNiveaux1); break;
		case 1: primaryStage.setScene(sceneNiveaux2); break;
		case 2: primaryStage.setScene(sceneNiveaux3); break;
		case 3: primaryStage.setScene(sceneNiveaux4); break;
		case 4: primaryStage.setScene(sceneNiveaux5); break;
		}
	}
}
