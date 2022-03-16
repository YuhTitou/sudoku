package profile;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public interface Commun{
	static final int windowWidth = 1250;
	static final int windowHeight = 750;
	
	static final Font cooperHewitt22 = Font.loadFont("file:src/fonts/CooperHewitt-Book.otf", 22);
	static final Font cooperHewittRegles = Font.font("cooperHewitt25 ", FontWeight.BOLD,22);
	
	static final Font cooperHewitt30 = Font.loadFont("file:src/fonts/CooperHewitt-Book.otf", 30);
	static final Font cooperHewitt35 = Font.loadFont("file:src/fonts/CooperHewitt-Book.otf", 35);
	static final Font cooperHewitt50 = Font.loadFont("file:src/fonts/CooperHewitt-Book.otf", 50);
	static final Font cooperHewitt60 = Font.loadFont("file:src/fonts/CooperHewitt-Book.otf", 60);
	static final Font fascinate = Font.loadFont("file:src/fonts/Fascinate-Regular.ttf", 120);

	static final Color vert = Color.web("#CBDF7A");
	static final Color jaune = Color.web("#FFE57C");
	static final Color rouge = Color.web("#FFA4A4");
	static final Color bleu = Color.web("#83ECF1");
	static final String couleurFond = " -fx-background : #F5F5F5 ";
}
