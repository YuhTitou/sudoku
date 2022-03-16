package profile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Sauvegarder {
	public static void serialization() {
		try {
	        FileOutputStream fos = new FileOutputStream("save.dat");
	        ObjectOutputStream oos= new ObjectOutputStream(fos);
	        try {
	        	for(int i=0;i<ListeProfil.getMaxprofil();i++) oos.writeObject(ListeProfil.getListeProfil().get(i));
	            oos.flush();
	        } finally {
	            try {
	                oos.close();
	            } finally {
	                fos.close();
	            }
	        }
	    } catch(IOException ioe) {
	    	ioe.printStackTrace();
	    }
	}
	public static void deserialization() {
		Profil profil = null;
        try {
            FileInputStream fis = new FileInputStream("save.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {    
                for(int i=0;i<ListeProfil.getMaxprofil();i++) {
                	profil = (Profil) ois.readObject(); 
                	ListeProfil.getListeProfil().get(i).setPseudo(profil.getPseudo());
                	ListeProfil.getListeProfil().get(i).getLabelPseudoProfil().setText(profil.getPseudo());
                	ListeProfil.getListeProfil().get(i).getLabelPseudoProfilList().setText(profil.getPseudo());
                }
                
            } finally {
                try {
                    ois.close();
                } finally {
                    fis.close();
                }
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
	}
}
