package profile;

import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class Temps {
	private boolean enPause = true;
	private boolean plusDeTemps;
	
	private int minutesDefinies;
	private int secondesDefinies;
	
	private int minutesEcoulees;
	private int secondesEcoulees;
	
	private int minutesRestantes;
	private int secondesRestantes;
	
	private String tempsEcoule="";
	private String tempsRestant="";
	private static Label labelTemps = new Label();
	
	public Temps(int minutesDefinies, int secondesDefinies) {
		this.minutesDefinies = minutesDefinies;
		this.secondesDefinies = secondesDefinies;
		this.minutesRestantes = this.minutesDefinies;
		this.secondesRestantes = this.secondesDefinies;
		labelTemps.setText(minutesDefinies+":"+secondesDefinies);
	}

	public void start() throws InterruptedException {
		this.formatageTemps();
		while((!this.enPause)&&(!this.plusDeTemps)){
			TimeUnit.SECONDS.sleep(1);
			this.secondesRestantes--;
			this.secondesEcoulees++;
			
			if(this.secondesEcoulees==60){
		    	this.secondesEcoulees=0;    	
			    this.minutesEcoulees++;
		    }
			
		    this.formatageTemps();
		    
		    if(this.secondesRestantes==0){
		    	this.secondesRestantes=60;
		    }
			if(this.secondesRestantes==60){
		    	if(this.minutesRestantes==0) {
			    	this.plusDeTemps=true;
			    }else {
			    	this.minutesRestantes--;
			    }
		    }
			Platform.runLater(new Runnable(){
				public void run() {
					labelTemps.setText(tempsRestant);
				}
			});
		}
	}
	
	private void formatageTemps() {
		/*Calcul du temps restant au chrono*/
	    if((this.minutesRestantes<10)&&(this.secondesRestantes<10)) {
	    	tempsRestant = ("0"+this.minutesRestantes+":"+"0"+this.secondesRestantes);
		}else if(this.minutesRestantes<10) {
			tempsRestant = ("0"+this.minutesRestantes+":"+this.secondesRestantes);
		}else if(this.secondesRestantes<10) {
			tempsRestant = (this.minutesRestantes+":"+"0"+this.secondesRestantes);
		}else {
			tempsRestant = (this.minutesRestantes+":"+this.secondesRestantes);
		}
	       
		/*Calcul du temps écoulé au chrono*/
	    if((this.minutesEcoulees<10)&&(this.secondesEcoulees<10)) {
	    	tempsEcoule = ("0"+this.minutesEcoulees+":"+"0"+this.secondesEcoulees);
		}else if(this.minutesEcoulees<10) {
			tempsEcoule = ("0"+this.minutesEcoulees+":"+this.secondesEcoulees);
		}else if(this.secondesEcoulees<10) {
			tempsEcoule = (this.minutesEcoulees+":"+"0"+this.secondesEcoulees);
		}else {
			tempsEcoule = (this.minutesEcoulees+":"+this.secondesEcoulees);
		}	
	}
	
	public static Label getLabelTemps() {
		return labelTemps;
	}
	public void setEnPause(boolean enPause) {
		this.enPause = enPause;
	}
}
