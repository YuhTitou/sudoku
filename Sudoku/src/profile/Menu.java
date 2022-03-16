package profile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Menu extends CommunM implements Commun{
	private BorderPane root = new BorderPane();
	private VBox vboxMenu = new VBox();
	private HBox hboxSudoku = new HBox();
	private HBox hboxCenter = new HBox();
	private StackPane spLeft = new StackPane();
	private StackPane spRight = new StackPane();

	private final Label labelTitreSU = new Label("SU");
	private final Label labelTitreDO = new Label("DO");
	private final Label labelTitreKU = new Label("KU");
	private final Label labelJouer = new Label("Jouer");
	private final Label labelRegles = new Label("Règles");
	private final Label labelQuitter = new Label("Quitter");
	private final Label labelProfil = new Label("Profil");
	private ArrayList<Label> menu = new ArrayList<Label>();
	
	public Menu() {
		menu.add(labelJouer);
		menu.add(labelProfil);
		menu.add(labelRegles);
		menu.add(labelQuitter);
		
		definirFenetre();
		
		for(int i=0;i<menu.size();i++) {int k = i; menu.get(i).setOnMouseClicked(e -> menuAction(k));}
		this.getScene().setOnKeyPressed(e -> sceneAction(e));
	}

	public void selectionnerChoix() {
		int id;
		if(haut||bas){
			id = chercherChoix();
			if(id==-1) {
				survolerChoix(0);
			}else {
				if(haut){
					if(id==0) survolerChoix(menu.size()-1);
					else survolerChoix(id-1);
				}
				if(bas){
					if(id==menu.size()-1) survolerChoix(0);
					else survolerChoix(id+1);
				}
			}
		}else if(accepter){
			id = chercherChoix();
			if(id!=-1) menuAction(id);
		}
	}	
	public void survolerChoix(int i) {
		menu.get(i).setTextFill(bleu);
	}
	public int chercherChoix() {
		int id=-1;
		int i=0;
		while((i<menu.size())&&id==-1) {
			if(menu.get(i).getTextFill()==bleu) {
				id=i;
				menu.get(id).setTextFill(rouge);
				if(id==menu.size()-1) menu.get(menu.size()-1).setTextFill(jaune);
			}else {
				i++;
			}
		}
		return id;
	}
	
	public void menuAction(int i) {
		switch(i) {
		case 0: Principale.toListProfilJ(); break;
		case 1: Principale.toListProfilD(); break;
		case 2: Principale.toRegles(); break;
		case 3: Platform.exit(); break;
		}
	}
	public void sceneAction(KeyEvent e) {
		obtenirTouches(e);
		selectionnerChoix();
	}
	protected Parent creerContenu(){
		root.setStyle(couleurFond);
		labelJouer.setTextFill(rouge);
		labelRegles.setTextFill(rouge);
		labelQuitter.setTextFill(jaune);
		labelProfil.setTextFill(rouge);
		
		labelTitreSU.setTextFill(rouge);
		labelTitreDO.setTextFill(jaune);
		labelTitreKU.setTextFill(vert);
		
		labelJouer.setFont(cooperHewitt60);
		labelRegles.setFont(cooperHewitt60);
		labelProfil.setFont(cooperHewitt60);
		labelQuitter.setFont(cooperHewitt60);
		
		labelTitreSU.setFont(fascinate);
		labelTitreDO.setFont(fascinate);
		labelTitreKU.setFont(fascinate);
		
		hboxSudoku.getChildren().addAll(labelTitreSU,labelTitreDO,labelTitreKU);
		hboxSudoku.setAlignment(Pos.CENTER);
		
		vboxMenu.getChildren().addAll(labelJouer,labelProfil,labelRegles,labelQuitter);
		vboxMenu.setAlignment(Pos.CENTER);
		vboxMenu.setSpacing(45);
		
		hboxCenter.getChildren().addAll(hboxSudoku,vboxMenu);
		hboxCenter.setAlignment(Pos.CENTER);
		hboxCenter.setSpacing(60);
		
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
		
		spLeft.getChildren().add(decoBG);
		spRight.getChildren().add(decoHD);
		StackPane.setAlignment(decoHD, Pos.TOP_RIGHT);
		StackPane.setAlignment(decoBG, Pos.BOTTOM_LEFT);
		
		root.setLeft(spLeft);
		root.setRight(spRight);
		root.setCenter(hboxCenter);
		return root;
	}
}
