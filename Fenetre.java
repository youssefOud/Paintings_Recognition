import java.awt.Dimension;
 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;

public class Fenetre extends JFrame implements WindowListener{
    
    //dimensions de l'écran
    private double LARGEUR_FENETRE;
    private double HAUTEUR_FENETRE;
    
    //élément affichage
    private Image papierPeint;
    private String NomPapierPeint;
    
    //numéro classe à ouvrir
    private int numClasse;
    
    //élément bouton
    public JButton monBouton;
    
    //Constructeur
    public Fenetre(double LARGEUR_FENETRE, double HAUTEUR_FENETRE, String NomPapierPeint, int numClass, PeintureData[] peintureCorrespondante ){
		
		this.NomPapierPeint=NomPapierPeint;
		this.LARGEUR_FENETRE=LARGEUR_FENETRE;
		this.HAUTEUR_FENETRE=HAUTEUR_FENETRE;
		this.numClasse = numClasse;
		
		//Titre de toutes les fenêtres
		setTitle("APN");
		
		// dimensionnement et positionnement de la fenêtre
        setSize((int)LARGEUR_FENETRE, (int)HAUTEUR_FENETRE);
        setLocation(100,200);
        
        setResizable(false); // fenêtre non redimensionnable
        setLocationRelativeTo(null); // centrer la fenêtre sur l'écran
		
		//Ouverture FenetreInitialisation
		if(numClass==1){this.setContentPane(new FenetreInitialisation(new ImageIcon(NomPapierPeint).getImage()));}
			
		//Ouverture FenetreSelection
        if (numClass==2){
			
			this.setContentPane(new FenetreSelection(new ImageIcon(NomPapierPeint).getImage()));
			//On ajoute l'écouteur de type Window à la fenêtre
			this.addWindowListener(this);
		}
		
		//Ouverture FenetreIResultats
        if (numClass==3){this.setContentPane(new FenetreResultats(new ImageIcon(NomPapierPeint).getImage(), peintureCorrespondante ));}
        
        //Ouverture FenetreInconnue depuis FenetreSlection
        if (numClass==4){this.setContentPane(new FenetreInconnue(new ImageIcon(NomPapierPeint).getImage(),1));}
        
        //Ouverture FenetreInconnue depuis FenetreResultats
        if (numClass==5){this.setContentPane(new FenetreInconnue(new ImageIcon(NomPapierPeint).getImage(),2));}
        
        
        
       this.setVisible(true);  
    }
    
    //Méthodes du windowListener
    public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	
	public void windowClosing(WindowEvent e) {//Au clic sur fermer FenetreSelction
		
		//Demenda de confirmation
		int reponse = JOptionPane.showConfirmDialog(this,
                                        " Votre photo ne sera pas sauvegard\u00e9e, voulez-vous vraiment quitter ?",
                                        "Confirmation",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE);
                                        
        //Si confirmation                                
        if (reponse==JOptionPane.YES_OPTION){
			//Récupération du bouton, l'utilisateur peut rechoisir une nouvelle photo une fois la fenêtre sélection fermée
			this.monBouton = FenetreInitialisation.getBouton();
			
			//Fermeture de la fenêtre sélection
			this.dispose();
			
		}
		else{//Sinon
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//on n efiat rien
			return;
		}
		
   }
	
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(java.awt.event.WindowEvent e) {}
	public void windowIconified(java.awt.event.WindowEvent e) {}
	public void windowOpened(java.awt.event.WindowEvent e) {}
    
}
