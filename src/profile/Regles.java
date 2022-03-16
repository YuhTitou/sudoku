package profile;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Regles extends CommunM implements Commun{
	private Text regle1 = new Text("1 - Pour gagner, il suffit simplement de remplir entièrement le Sudoku dans le temps imparti !\n");
    private Text regle2 = new Text("2 - Pour compléter une grille, il faut que tous les chiffres entre 1 et 9 compris soient présents dans chaque case.\n");
    private Text regle3 = new Text("3 - Attention ! Il est impossible de placer un chiffre sur une ligne ou une colonne où il est déjà  présent !\nPareil s'il se trouve déjà  dans la grille sélectionnée !");
    private TextFlow regles = new TextFlow(regle1,regle2,regle3);
    
    private BorderPane root = new BorderPane();
    private StackPane spLeft = new StackPane();
	private StackPane spRight = new StackPane();
    private VBox vbox = new VBox();
    private final Label labelMenu = new Label("← Menu ");

    public Regles(){
    	definirFenetre();
    	labelMenu.setOnMouseClicked(e -> Principale.toMenu());
    	this.getScene().setOnKeyPressed(e -> sceneAction(e));
    }

    protected Parent creerContenu() {
    	root.setStyle(couleurFond);
        labelMenu.setTextFill(jaune);
        regle1.setFill(vert);
        regle2.setFill(vert);
        regle3.setFill(vert);
        
        labelMenu.setFont(cooperHewitt50);
        regle1.setFont(cooperHewittRegles);
        regle2.setFont(cooperHewittRegles);
        regle3.setFont(cooperHewittRegles);
        
        vbox.getChildren().addAll(regles);
        vbox.setAlignment(Pos.CENTER);
        regles.setLineSpacing(15);

        Image decoHautDroit = null;
		Image decoBasGauche = null;
		try {
			decoHautDroit = new Image(new FileInputStream("src/img/decoHautDroit.png"));
			decoBasGauche = new Image(new FileInputStream("src/img/decoBasGauche.png"));
		} catch (FileNotFoundException e) {System.out.println("Fichier non trouvé");}
		ImageView decoHD = new ImageView(decoHautDroit);
		ImageView decoBG = new ImageView(decoBasGauche);
		decoHD.setPreserveRatio(true);
		decoBG.setPreserveRatio(true);
		decoHD.setFitHeight(250);
		decoBG.setFitWidth(250);
		
		spLeft.getChildren().addAll(decoBG,labelMenu);
		spRight.getChildren().add(decoHD);
		StackPane.setAlignment(decoHD, Pos.TOP_RIGHT);
		StackPane.setAlignment(decoBG, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(labelMenu, Pos.CENTER);
		
		root.setLeft(spLeft);
		root.setRight(spRight);
        root.setCenter(vbox);
        
        return root;
    }
    public void sceneAction(KeyEvent e) {
    	obtenirTouches(e);
		if(quitter) Principale.toMenu();
	}
}