package profile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;

public class Grille extends CommunM implements Commun{
	private static String[][] niveau = {{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""}};
	private static String[][] niveauC = {{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""},
								{"","","","","","","","",""}};
	private final static int nombre = 9;
	private final int taille = 3;
	
	private final int tailleGrille = 600;
	private final int tailleBordGrille = 15;
	private final int tailleSousGrille = (tailleGrille-(tailleBordGrille*4))/3;
	private final int tailleBordSousGrille = 5;
	private final int tailleCaseGrille = (tailleSousGrille-(tailleBordSousGrille*4))/3;
	
	private static ArrayList<StackPane> cases = new ArrayList<StackPane>();
	
	private BorderPane root = new BorderPane();
	private VBox vbox = new VBox();
	private StackPane boutonPause = new StackPane();
	private Circle cerclePause = new Circle(60,jaune);
	private Label labelPause = new Label("| |");
	private Label labelValider = new Label("✓");
	private static Temps timer;

	public Grille() {
		timer = new Temps(1,30);
		Thread newThread = new Thread(() -> {
			try {
				timer.start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		newThread.start();
		
		definirFenetre();	
		labelValider.setOnMouseClicked(e-> System.out.println(verifierNiveau()));
		boutonPause.setOnMouseClicked(e -> boutonPauseAction());
		this.getScene().setOnKeyPressed(e -> sceneAction(e));
	}
	
	protected Parent creerContenu() {
		root.setStyle(couleurFond);
		StackPane grille = creerAffichageGrille();
		Label temps = Temps.getLabelTemps();
		
		labelValider.setTextFill(jaune);
		labelPause.setTextFill(Color.WHITE);
		temps.setTextFill(rouge);
		labelValider.setFont(new Font(120));
		labelPause.setFont(cooperHewitt60);
		temps.setFont(fascinate);
		
		boutonPause.getChildren().addAll(cerclePause,labelPause);
		vbox.getChildren().addAll(boutonPause,temps,labelValider);
		vbox.setPrefWidth(0.30*windowWidth);
		vbox.setAlignment(Pos.CENTER);
		
		root.setLeft(vbox);
		root.setCenter(grille);
		BorderPane.setAlignment(grille, Pos.CENTER);
		BorderPane.setMargin(vbox, new Insets(0,0,0,50));
		
		return root;
	}

	public static void lireNiveau(String niv,String nivC) {
		try (BufferedReader br = new BufferedReader(new FileReader(niv)); BufferedReader brC = new BufferedReader(new FileReader(nivC))){
		    for(int k=0;k<nombre;k++) {
		    	 String[] values = br.readLine().split(",");
		    	 String[] valuesC = brC.readLine().split(",");
		    	 for(int i=0;i<nombre;i++) {
			        if (Integer.parseInt(values[i])!=0) niveau[k][i] = values[i];	
			        niveauC[k][i] = valuesC[i];
			     }    
		    }
		} catch (FileNotFoundException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}
		for(int i=0;i<cases.size();i++) {
			((Labeled) cases.get(i).getChildren().get(cases.get(i).getChildren().size()-1)).setText(niveau[(int) (((i%9)/3)+Math.floor((i%27)/9)*3)][(int) ((i%3)+Math.floor((i%81)/27)*3)]);
		}
	}
	public boolean verifierNiveau() {
		int i=0,k=0;
		Boolean res = true;
		while(i<nombre&&res) {
			k=0;
			while(k<nombre&&res) {
				if (!(niveau[i][k].equals(niveauC[i][k]))) {
					res = false;
				}
				k++;
			}
			i++;
		}
		return res;
	}
	
	public StackPane creerAffichageSousGrille(int z, int x) {
		StackPane sousGrille = new StackPane();
		GridPane cases = new GridPane();
		Rectangle rectangleFondSousGrille = new Rectangle(tailleSousGrille,tailleSousGrille,rouge);
		
		for(int a=0;a<taille;a++) {
			for(int b=0;b<taille;b++) {
				StackPane affichageCase = new StackPane();
				Rectangle rectangleCaseGrille = new Rectangle(tailleCaseGrille,tailleCaseGrille,Color.WHITE);
				Rectangle rectangle = new Rectangle(tailleCaseGrille-tailleBordSousGrille*2,tailleCaseGrille-tailleBordSousGrille*2,Color.WHITE);
				if(niveau[(x*3)+a][(z*3)+b]!=("")) {
					rectangleCaseGrille.setFill(Color.web("#ebebeb"));
					rectangle.setFill(Color.web("#ebebeb"));
				}
				
				Label l = new Label(String.valueOf(niveau[(x*3)+a][(z*3)+b]));
				l.setFont(cooperHewitt35);
				affichageCase.getChildren().addAll(rectangleCaseGrille,rectangle,l);
				affichageCase.setOnMouseClicked(e->{
					for(int i=0;i<Grille.cases.size();i++) {
						if(((Shape) Grille.cases.get(i).getChildren().get(1)).getFill().equals(Color.web("#ebebeb"))) {
							((Shape) Grille.cases.get(i).getChildren().get(0)).setFill(Color.web("#ebebeb"));
						}else {
							((Shape) Grille.cases.get(i).getChildren().get(0)).setFill(Color.WHITE);
						}
						Grille.cases.get(i).getChildren().remove(rectangle);
					}
					rectangleCaseGrille.setFill(bleu);
					affichageCase.getChildren().remove(rectangle);
					affichageCase.getChildren().add(rectangle);
					affichageCase.getChildren().remove(l);
					affichageCase.getChildren().add(l);
				});
				cases.add(affichageCase, b, a);
				Grille.cases.add(affichageCase);
			}	
		}
		sousGrille.setMaxWidth(tailleSousGrille);
		sousGrille.setMaxHeight(tailleSousGrille);
		sousGrille.getChildren().addAll(rectangleFondSousGrille,cases);
		cases.setHgap(tailleBordSousGrille);
		cases.setVgap(tailleBordSousGrille);
		
		StackPane.setMargin(cases, new Insets(tailleBordSousGrille));
		return sousGrille;
	}
	public StackPane creerAffichageGrille(){
		StackPane grille = new StackPane();
		GridPane grilles = new GridPane();
		Rectangle rectangleFondGrille = new Rectangle(tailleGrille, tailleGrille,vert);
		
		grille.setMaxWidth(tailleGrille);
		grille.setMaxHeight(tailleGrille);
		
		grille.getChildren().addAll(rectangleFondGrille,grilles);
		for(int a=0;a<taille;a++) {
			for(int b=0;b<taille;b++) {
				StackPane sousGrille = creerAffichageSousGrille(a,b);
				grilles.add(sousGrille, a, b);
			}	
		}
		grilles.setHgap(tailleBordGrille);
		grilles.setVgap(tailleBordGrille);
		
		StackPane.setMargin(grilles, new Insets(tailleBordGrille));
		return grille;
	}

	public void boutonPauseAction() {
		timer.setEnPause(true);
		Principale.toPause();
	}
	public void sceneAction(KeyEvent e) {
		obtenirTouches(e);
		if(quitter) {
			Principale.toPause();
		}else {
			changerCase();
		}
		
	}
	public void changerCase() {
		for(int i=0;i<Grille.cases.size();i++) {
			if((((Shape) Grille.cases.get(i).getChildren().get(0)).getFill()==bleu)&&!((Shape) Grille.cases.get(i).getChildren().get(1)).getFill().equals(Color.web("#ebebeb"))) {
				for(int k=1;k<10;k++) {
					if(touche.equals(String.valueOf(k))) {
						niveau[(int) (((i%9)/3)+Math.floor((i%27)/9)*3)][(int) ((i%3)+Math.floor((i%81)/27)*3)]=touche;
						((Labeled) Grille.cases.get(i).getChildren().get(Grille.cases.get(i).getChildren().size()-1)).setText(touche);
					}
				}
			}
		}
	}

	public static Temps getTimer() {
		return timer;
	}

}
