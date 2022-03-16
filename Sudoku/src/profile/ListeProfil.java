package profile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.geometry.Insets;

public class ListeProfil extends CommunM implements Commun{
	private static Profil profil1 = new Profil(1);
	private static Profil profil2 = new Profil(2);
	private static Profil profil3 = new Profil(3);
	private static Profil profil4 = new Profil(4);
	private static Profil profil5 = new Profil(5);
	
	private final static int maxProfil = 5;
	private static ArrayList<Profil> listeProfil;
	
	private StackPane root = new StackPane();
	private BorderPane bp = new BorderPane();
	private StackPane spLeft = new StackPane();
	private VBox vboxRight = new VBox();

	private final Label labelMenu = new Label("← Menu");
	private final Label labelTitre = new Label("Sélectionner un profil");
	private boolean peutQuitter = true;
	private boolean estDetail;
	
	public ListeProfil(){
		listeProfil = new ArrayList<Profil>();
		listeProfil.add(profil1);
		listeProfil.add(profil2);
		listeProfil.add(profil3);
		listeProfil.add(profil4);
		listeProfil.add(profil5);
		Sauvegarder.deserialization();
		
		definirFenetre();
		
		for(int i=0;i<listeProfil.size();i++) {int k = i;listeProfil.get(i).getProfilList().setOnMouseClicked(e -> menuProfil(k));}
		labelMenu.setOnMouseClicked(e -> labelMenuAction());
		this.getScene().setOnKeyPressed(e -> sceneAction(e));
	}

	protected Parent creerContenu(){
		root.setStyle(couleurFond);
		labelMenu.setTextFill(jaune);
		labelTitre.setTextFill(rouge);
		
		labelMenu.setFont(cooperHewitt50);
		labelTitre.setFont(cooperHewitt60);
		
		vboxRight.getChildren().add(labelTitre);
		for(int i=0;i<maxProfil;i++) vboxRight.getChildren().add(listeProfil.get(i).getProfilList());
		vboxRight.setAlignment(Pos.TOP_CENTER);
		vboxRight.setSpacing(45);
		
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
		decoBG.setPreserveRatio(true);
		decoHG.setFitHeight(250);
		decoBG.setFitWidth(250);
		
		spLeft.getChildren().addAll(decoHG,decoBG,labelMenu);
		StackPane.setAlignment(labelMenu, Pos.CENTER);
		StackPane.setAlignment(decoHG, Pos.TOP_LEFT);
		StackPane.setAlignment(decoBG, Pos.BOTTOM_LEFT);
		
		VBox.setMargin(labelTitre, new Insets(45,0,0,0));
				
		BorderPane.setMargin(vboxRight, new Insets(0,0.125*windowWidth,0,0));
		BorderPane.setAlignment(vboxRight, Pos.CENTER_RIGHT);
		BorderPane.setAlignment(spLeft, Pos.CENTER_LEFT);
		
		bp.setLeft(spLeft);
		bp.setRight(vboxRight);
		root.getChildren().add(bp);
		return root;
	}

	public void avertissement(String message,int numProfil) {
		peutQuitter = false;
		
		StackPane root = new StackPane();
		Text text = new Text(message);
		Label labelAvertissement = new Label(message);
		Label labelAnnuler = new Label("Annuler");
		Label labelConfirmer = new Label("Confirmer");
		listeProfil.get(numProfil).getLabelChangementPseudo().setText("vide");
		
		text.setFont(cooperHewitt35);
		labelAvertissement.setFont(cooperHewitt35);
		labelAnnuler.setFont(cooperHewitt35);
		labelConfirmer.setFont(cooperHewitt35);
		listeProfil.get(numProfil).getLabelChangementPseudo().setFont(cooperHewitt35);
		
		text.setFill(Color.WHITE);
		labelAvertissement.setTextFill(Color.WHITE);
		labelAnnuler.setTextFill(Color.WHITE);
		labelConfirmer.setTextFill(Color.WHITE);
		listeProfil.get(numProfil).getLabelChangementPseudo().setTextFill(Color.WHITE);
		
		Rectangle fond = new Rectangle(0.45*windowWidth,0.45*windowHeight,Color.WHEAT);
		root.getChildren().addAll(fond,text,labelAnnuler,labelConfirmer,listeProfil.get(numProfil).getLabelChangementPseudo());
		
		StackPane.setAlignment(text, Pos.TOP_LEFT);
		StackPane.setAlignment(labelAnnuler, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(labelConfirmer, Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(listeProfil.get(numProfil).getLabelChangementPseudo(), Pos.CENTER_LEFT);

		StackPane.setMargin(text, new Insets(30,0,0,30));
		StackPane.setMargin(labelAnnuler, new Insets(0,0,30,30));
		StackPane.setMargin(labelConfirmer, new Insets(0,30,30,0));
		StackPane.setMargin(listeProfil.get(numProfil).getLabelChangementPseudo(), new Insets(0,0,20,30));
		
		text.setWrappingWidth(0.45*windowWidth-30*2);
		root.setMaxWidth(0.45*windowWidth);
		root.setMaxHeight(0.45*windowHeight);
		this.root.getChildren().add(root);
		labelAnnuler.setOnMouseClicked(e -> avertissementAction(false,root,numProfil));
		labelConfirmer.setOnMouseClicked(e -> {if(listeProfil.get(numProfil).getLabelChangementPseudo().getText()!="vide"&&!listeProfil.get(numProfil).getLabelChangementPseudo().getText().isEmpty())avertissementAction(true,root,numProfil);});
		
		this.root.getScene().setOnKeyPressed(e -> {
			obtenirTouches(e);
			if(!peutQuitter&&quitter) {
				avertissementAction(false,root,numProfil);
			}else if(!peutQuitter){ changerPseudo(numProfil,root);
			}else sceneAction(e);
		});
	}
	public void avertissementAction(boolean res,StackPane root,int numProfil) {
		peutQuitter = true;
		this.root.getChildren().remove(root);
		if(res) {
			listeProfil.get(numProfil).setPseudo(listeProfil.get(numProfil).getLabelChangementPseudo().getText());
			listeProfil.get(numProfil).getLabelPseudoProfil().setText(listeProfil.get(numProfil).getLabelChangementPseudo().getText());
			listeProfil.get(numProfil).getLabelPseudoProfilList().setText(listeProfil.get(numProfil).getLabelChangementPseudo().getText());
			Sauvegarder.serialization();
			menuProfil(numProfil);
		}	
	}
	
	public void changerPseudo(int numProfil,StackPane root) {
		String pseudo = listeProfil.get(numProfil).getLabelChangementPseudo().getText();
		Label labelPseudo = listeProfil.get(numProfil).getLabelChangementPseudo();
		
		boolean estVide = ((pseudo == "vide")||pseudo.isEmpty());
		
		if(estVide) {
			if(!accepter&&!interdit) pseudo=touche;
			else pseudo="";
		}else {
			if(supprimer) {
				String p="";
				for(int i=0;i<pseudo.length()-1;i++)p=p+pseudo.charAt(i);
				pseudo = p;
			}else if(accepter&&!estVide){ avertissementAction(true,root,numProfil);
			}else if(!accepter&&!interdit) pseudo=pseudo+touche;
		}
		labelPseudo.setText(pseudo);
	}
	
	public void selectionnerProfil() {
		int id = chercherProfil();
		
		if(quitter) Principale.toMenu();
		
		if(haut||bas){
			if(id!=-1) {
				listeProfil.get(id).getRectangleProfilList().setFill(vert);
			}
			if(id==-1) {survolerProfil(0);
			}else {
				if(haut){
					if(id==0) survolerProfil(maxProfil-1);
					else survolerProfil(id-1);
				}
				if(bas){
					if(id==maxProfil-1) survolerProfil(0);
					else survolerProfil(id+1);
				}
			}
		}else if(accepter){
			if(id!=-1) menuProfil(id);
		}
	}	
	public void survolerProfil(int i) {
		listeProfil.get(i).getRectangleProfilList().setFill(bleu);
	}
	public int chercherProfil() {
		int id=-1;
		int i=0;
		while((i<maxProfil)&&id==-1) {
			if(listeProfil.get(i).getRectangleProfilList().getFill() == bleu) {
				id=i;
			}else {
				i++;
			}
		}
		return id;
	}
	
	public void menuProfil(int i) {
		if(listeProfil.get(i).getPseudo().equals("vide")) {
			avertissement("Choisissez un pseudo :",i);
		}else if(estDetail){
			Principale.toProfil(i);
		}else if(!estDetail) {
			Principale.toNiveaux(i);
		}
	}
	public void labelMenuAction() {
		if(peutQuitter) Principale.toMenu();
	}
	public void sceneAction(KeyEvent e) {
		obtenirTouches(e);
		if(peutQuitter) selectionnerProfil();
	}
	public void setEstDetail(boolean estDetail) {
		this.estDetail = estDetail;
	}

	public static Profil getProfil1() {
		return profil1;
	}
	public static Profil getProfil2() {
		return profil2;
	}
	public static Profil getProfil3() {
		return profil3;
	}
	public static Profil getProfil4() {
		return profil4;
	}
	public static Profil getProfil5() {
		return profil5;
	}
	public static int getMaxprofil() {
		return maxProfil;
	}
	public static ArrayList<Profil> getListeProfil() {
		return listeProfil;
	}
	public static void setListeProfil(ArrayList<Profil> listeProfil) {
		ListeProfil.listeProfil = listeProfil;
	}
}
