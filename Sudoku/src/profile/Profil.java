package profile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Profil extends CommunM implements Commun, Serializable {
	private static final long serialVersionUID = -628982891873165068L;
	transient private final String stringProfil = "Profil";
	private String pseudo = "vide";
	
	transient private StackPane profilNiveaux = new StackPane();
	transient private StackPane profilList = new StackPane();
	transient private StackPane profil = new StackPane();
	transient private Label labelPseudoProfil = new Label();
	transient private Label labelPseudoProfilList = new Label();
	transient private Label labelPseudoProfilNiveaux = new Label();
	transient private Rectangle rectangleProfil = new Rectangle(windowWidth-275*2,60,vert);
	transient private Rectangle rectangleProfilList = new Rectangle(windowWidth-275*2,60,vert);
	transient private Rectangle rectangleProfilNiveaux = new Rectangle(windowWidth-275*2,60,vert);
	
	transient private final Label labelRetour = new Label("← Retour");
	transient private final Label labelSupprimer = new Label("Supprimer");
	transient private Label labelChangementPseudo = new Label();
	
	transient private StackPane root = new StackPane();
	transient private BorderPane bp = new BorderPane();
	transient private StackPane spLeft = new StackPane();
	transient private VBox vboxRight = new VBox();
	transient private HBox hboxBoutons = new HBox();
	
	transient private String type_difficulter;
	transient private int avancer = 0;
	transient private RadioButton facile;
	transient private RadioButton normale;
	transient private RadioButton difficile;
	transient private Boolean peutQuitter = true;
	
	transient private ArrayList<Label> choix = new ArrayList<Label>();
	
	public Profil(int i) {
		choix.add(labelSupprimer);
		choix.add(labelRetour);
		
		profil = creerAffichageProfil(i,profil,labelPseudoProfil, rectangleProfil);
		profilList = creerAffichageProfil(i,profilList,labelPseudoProfilList, rectangleProfilList);
		profilNiveaux = creerAffichageProfil(i,profilNiveaux,labelPseudoProfilNiveaux, rectangleProfilNiveaux);
		definirFenetre();
	
		
		for(int q=0;q<choix.size();q++){ int k=q; choix.get(k).setOnMouseClicked(e -> menuAction(k));}
		labelPseudoProfil.setOnMouseClicked(e -> labelPseudoProfilAction());
		this.getScene().setOnKeyPressed(e -> sceneAction(e));	
	}
	
	protected Parent creerContenu(){
		root.setStyle(couleurFond);
		
		labelRetour.setTextFill(jaune);
		labelSupprimer.setTextFill(rouge);

		labelRetour.setFont(cooperHewitt50);
		labelSupprimer.setFont(cooperHewitt60);
		
		hboxBoutons.getChildren().addAll(labelSupprimer);
		hboxBoutons.setAlignment(Pos.CENTER);
		hboxBoutons.setSpacing(windowWidth*0.27);
		
		vboxRight.getChildren().addAll(profil,hboxBoutons);
		vboxRight.setAlignment(Pos.TOP_CENTER);
		vboxRight.setSpacing(45);
		
		root.getChildren().add(bp);
		bp.setLeft(spLeft);
		bp.setRight(vboxRight);
		
		VBox.setMargin(profil, new Insets(60,0,0,0));
		BorderPane.setMargin(vboxRight, new Insets(0,0.125*windowWidth,0,0));
		
		Image decoHautGauche = null;
		Image decoBasGauche = null;
		try {
			decoHautGauche = new Image(new FileInputStream("src/img/decoHautGauche.png"));
			decoBasGauche = new Image(new FileInputStream("src/img/decoBasGauche.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé");
		}
		ImageView decoHG = new ImageView(decoHautGauche);
		ImageView decoBG = new ImageView(decoBasGauche);
		decoHG.setPreserveRatio(true);
		decoHG.setFitHeight(250);
		decoBG.setPreserveRatio(true);
		decoBG.setFitWidth(250);
		
		spLeft.getChildren().addAll(decoBG,decoHG,labelRetour);
		StackPane.setAlignment(decoHG, Pos.TOP_LEFT);
		StackPane.setAlignment(decoBG, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(labelRetour, Pos.CENTER);

		return root;
	}
	
	public void avertissement(String message) {
		peutQuitter = false;
		StackPane root = new StackPane();
		Rectangle fond = new Rectangle(0.45*windowWidth,0.45*windowHeight,Color.WHEAT);
		Text text = new Text(message);
		Label labelAnnuler = new Label("Annuler");
		Label labelOK = new Label("Ok");
		
		text.setFont(cooperHewitt30);
		labelAnnuler.setFont(cooperHewitt35);
		labelOK.setFont(cooperHewitt35);
		
		text.setFill(Color.WHITE);
		labelAnnuler.setTextFill(Color.WHITE);
		labelOK.setTextFill(Color.WHITE);
		
		root.getChildren().addAll(fond,text,labelAnnuler,labelOK);
		
		StackPane.setAlignment(text, Pos.TOP_LEFT);
		StackPane.setAlignment(labelAnnuler, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(labelOK, Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(root, Pos.CENTER);
		StackPane.setMargin(text, new Insets(30,0,0,30));
		StackPane.setMargin(labelAnnuler, new Insets(0,0,30,30));
		StackPane.setMargin(labelOK, new Insets(0,30,30,0));
		
		text.setWrappingWidth(0.45*windowWidth-30*2);
		root.setMaxWidth(0.45*windowWidth);
		root.setMaxHeight(0.45*windowHeight);
		this.root.getChildren().add(root);
		labelAnnuler.setOnMouseClicked(e -> avertissementAction(false,root));
		labelOK.setOnMouseClicked(e -> avertissementAction(true,root));
		this.root.getScene().setOnKeyPressed(e -> {
			obtenirTouches(e);
			if(!peutQuitter&&quitter) {
				avertissementAction(false,root);
			}else if(!peutQuitter&&accepter) { avertissementAction(true,root);
			}else sceneAction(e);
		});
	}
	public void avertissementAction(Boolean res,StackPane root) {
		peutQuitter = true;
		this.root.getChildren().remove(root);
		if(res) {
			supprimerProfil();
			Sauvegarder.serialization(); 
		}	
	}

	public void changerPseudo() {
		boolean estVide = ((pseudo == "vide")||pseudo.isEmpty());
		if(supprimer) {
			String p ="";
			for(int k=0;k<(labelPseudoProfil.getText().length()-1);k++){p=p+labelPseudoProfil.getText().charAt(k);}
			pseudo = p;
		}else if(accepter&&!estVide){
			peutQuitter=true;
			rectangleProfil.setFill(vert);
		}else if(!accepter&&!interdit) pseudo=labelPseudoProfil.getText()+touche;
		labelPseudoProfilNiveaux.setText(pseudo);
		labelPseudoProfilList.setText(pseudo);
		labelPseudoProfil.setText(pseudo);
	}
	public StackPane creerAffichageProfil(int i,StackPane profil, Label labelPseudoProfil, Rectangle rectangle) {
		Label labelProfil = new Label();
				
		labelProfil.setText(stringProfil+" "+i);
		labelPseudoProfil.setText(pseudo);

		profil.getChildren().addAll(rectangle = creerAffichageRectangle(rectangle),labelProfil,labelPseudoProfil);
		StackPane.setAlignment(labelProfil,Pos.CENTER_LEFT);
		StackPane.setAlignment(labelPseudoProfil, Pos.CENTER_RIGHT);
		StackPane.setMargin(labelProfil, new Insets(0,0,0,20));
		StackPane.setMargin(labelPseudoProfil, new Insets(0,20,0,0));
			
		labelProfil.setTextFill(Color.WHITE);
		labelPseudoProfil.setTextFill(Color.WHITE);
		
		labelProfil.setFont(cooperHewitt30);
		labelPseudoProfil.setFont(cooperHewitt30);
		return profil;
	}
	public Rectangle creerAffichageRectangle(Rectangle rectangle) {
		rectangle.setArcWidth(20);
		rectangle.setArcHeight(20);
		return rectangle;
	}
	public void supprimerProfil() {
		pseudo = "vide";
		labelPseudoProfilNiveaux.setText(pseudo);
		labelPseudoProfilList.setText(pseudo);
		labelPseudoProfil.setText(pseudo);
		Principale.toListProfilD();
	}
	
	public void selectionnerChoix() {
		int id = chercherChoix();
		
		if(gauche||droit){
			if(id!=-1) {
				if(id==choix.size()-1) {
					choix.get(choix.size()-1).setTextFill(jaune);
				}else {
					choix.get(id).setTextFill(rouge);
				}
			}
			if(id==-1) {survolerChoix(0);
			}else {
				if(gauche){
					if(id==0) survolerChoix(choix.size()-1);
					else survolerChoix(id-1);
				}
				if(droit){
					if(id==choix.size()-1) survolerChoix(0);
					else survolerChoix(id+1);
				}
			}
		}else if(accepter){
			if(id!=-1) {
				switch(id) {
				case 0: if(peutQuitter)avertissement("Voulez-vous supprimer le profil "+pseudo+" ?\nCette action sera définitive"); break;
				case 1: if(peutQuitter) Principale.toListProfilD();
				}
			}
		}
	}	
	public void survolerChoix(int i) {
		choix.get(i).setTextFill(bleu);
	}
	public int chercherChoix() {
		int id=-1;
		int i=0;
		while((i<choix.size())&&id==-1) {
			if(choix.get(i).getTextFill()==bleu) {
				id=i;
			}else {
				i++;
			}
		}
		return id;
	}
	
	public void menuAction(int i) {
		if(peutQuitter) {
			switch(i) {
			case 0: avertissement("Voulez-vous supprimer le profil "+pseudo+" ?\nCette action sera définitive"); break;
			case 1: Principale.toListProfilD(); break;
			}
		}
	}
	public void labelPseudoProfilAction() {
		rectangleProfil.setFill(bleu);
		peutQuitter = false;
	}
	public void sceneAction(KeyEvent e) {
		obtenirTouches(e);
		if(!peutQuitter) {
			changerPseudo();
		}else if(peutQuitter&&e.getCode().equals(KeyCode.ESCAPE)) {
			Principale.toListProfilD();
		}else if(peutQuitter) selectionnerChoix();
	}
	
	
	/*
	public void choixDifficulter(ActionEvent e) {
		System.out.println("Difficulter :");
		if (e.getSource()==facile) {
			type_difficulter = "Facile";
		} else if (e.getSource()==normale) {
			type_difficulter = "Normale";
		} else if (e.getSource()==difficile) {
			type_difficulter = "Difficile";
		}
	}*/	
	/*
	public void actualiserAvancer() {
		if ( variable "niveau fini pour la premiére fois" == false)
		Avancer++;
		//quand niveau fini, avoir une variable niveau fini pourla premiére fois qui passe en true
	}*/
	
	public StackPane getProfilList() {
		return profilList;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public Rectangle getRectangleProfilList() {
		return rectangleProfilList;
	}
	public Label getLabelPseudoProfil() {
		return labelPseudoProfil;
	}
	public Label getLabelPseudoProfilList() {
		return labelPseudoProfilList;
	}
	public Label getLabelChangementPseudo() {
		return labelChangementPseudo;
	}
	public StackPane getProfilNiveaux() {
		return profilNiveaux;
	}	
}
