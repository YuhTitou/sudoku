package profile;

import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;

import javafx.scene.shape.Rectangle;

public class Pause extends CommunM {
	private Label labelReprendre = new Label("Reprendre");
	private Label labelSelection = new Label("Sélection des niveaux");
	private Label labelRetour = new Label("Menu");
	private VBox root= new VBox();
	private StackPane boutonReprendre = new StackPane();
	private StackPane boutonSelection = new StackPane();
	private StackPane boutonRetour = new StackPane();
	private static int numProfil = -1;

	public Pause() {
		definirFenetre();
		boutonReprendre.setOnMouseClicked(e -> boutonReprendreAction());
		boutonSelection.setOnMouseClicked(e -> Principale.toNiveaux(numProfil));
		boutonRetour.setOnMouseClicked(e -> Principale.toMenu());
	}

	public Rectangle affichageRectangle() {
		return new Rectangle(550,100,rouge);
	}
	
	protected Parent creerContenu() {
		root.setStyle(couleurFond);
		
		boutonReprendre.getChildren().addAll(affichageRectangle(),labelReprendre);
		boutonSelection.getChildren().addAll(affichageRectangle(),labelSelection);
		boutonRetour.getChildren().addAll(affichageRectangle(),labelRetour);
		
		labelReprendre.setFont(cooperHewitt35);
		labelSelection.setFont(cooperHewitt35);
		labelRetour.setFont(cooperHewitt35);
		
		root.setPadding(new Insets(10));
		root.setSpacing(50);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(boutonReprendre,boutonSelection,boutonRetour);

		return root;
	}
	public void boutonReprendreAction() {
		Grille.getTimer().setEnPause(false);
		Principale.toGrille();
	}
	public static void setNumProfil(int numProfil) {
		Pause.numProfil = numProfil;
	}
}
