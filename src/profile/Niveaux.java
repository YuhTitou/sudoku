package profile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Niveaux extends CommunM implements Commun{
	
	private BorderPane root = new BorderPane();
	private StackPane spLeft = new StackPane();
	private StackPane spRight = new StackPane();
	private GridPane niveaux = new GridPane();
	private VBox vbox = new VBox();
	private int numProfil;
	private final Label labelRetour = new Label("← Retour");
	
	public Niveaux(int i) {
		this.numProfil = i;
		definirFenetre();
		labelRetour.setOnMouseClicked(e -> Principale.toListProfilJ());
		this.getScene().setOnKeyPressed(e -> sceneAction(e));	
	}
	protected Parent creerContenu() {
		root.setStyle(couleurFond);
	
		labelRetour.setTextFill(jaune);
		labelRetour.setFont(cooperHewitt50);
		
		vbox.getChildren().addAll(ListeProfil.getListeProfil().get(numProfil).getProfilNiveaux(),niveaux);
		vbox.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(ListeProfil.getListeProfil().get(numProfil).getProfilNiveaux(), new Insets(15,0,15,0));
		vbox.setPrefWidth(windowWidth-275*2);
		int k=1;
		for(int a=0;a<2;a++) {
			for(int b=0;b<3;b++) {
				niveaux.add(creationBoutonNiveau(k), b, a);
				k++;
			}
			
		}
		niveaux.setAlignment(Pos.CENTER);
		niveaux.setHgap(15);
		niveaux.setVgap(15);
		
		Image decoHautDroit = null;
		Image decoHautGauche = null;
		try {
			decoHautDroit = new Image(new FileInputStream("src/img/decoHautDroit.png"));
			decoHautGauche = new Image(new FileInputStream("src/img/decoHautGauche.png"));
		} catch (FileNotFoundException e) {System.out.println("Fichier non trouvé");}
		ImageView decoHD = new ImageView(decoHautDroit);
		ImageView decoHG = new ImageView(decoHautGauche);
		decoHD.setPreserveRatio(true);
		decoHG.setPreserveRatio(true);
		decoHD.setFitHeight(250);
		decoHG.setFitWidth(250);
		
		spLeft.getChildren().addAll(decoHG,labelRetour);
		spRight.getChildren().add(decoHD);
		StackPane.setAlignment(decoHD, Pos.TOP_RIGHT);
		StackPane.setAlignment(decoHG, Pos.TOP_LEFT);
		StackPane.setAlignment(labelRetour, Pos.CENTER);
		
		BorderPane.setAlignment(niveaux,Pos.CENTER);
		
		root.setLeft(spLeft);
		root.setRight(spRight);
		root.setCenter(vbox);

		return root;
	}
	
	public StackPane creationBoutonNiveau(int i) {
		StackPane bouton = new StackPane();
		Circle c = new Circle(80,rouge);
		Label l = new Label("Niveau "+i);
		l.setTextFill(Color.WHITE);
		l.setFont(cooperHewitt35);
		bouton.getChildren().addAll(c,l);
		bouton.setOnMouseClicked(e -> {
			Grille.lireNiveau("niv"+i+".csv", "niv"+i+"c.csv");
			Pause.setNumProfil(numProfil);
			Principale.toGrille();
		});
		return bouton;
	}
	public void sceneAction(KeyEvent e) {
		obtenirTouches(e);
		if(quitter) {
			Principale.toListProfilJ();
		}
	}
}