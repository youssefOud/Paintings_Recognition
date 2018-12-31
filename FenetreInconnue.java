import java.awt.Graphics;
import java.awt.Image;
 
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

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileFilter;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;


public class FenetreInconnue extends JPanel implements ActionListener{
	
	//Initialisations des attributs
	
	//élément affichage
	private Image papierPeint;
	
	//élément de la photo chargée par l'utilisateur
	public Image imageAReconnaitre;//contient l’image qui n’a pas pu être reconnue
	public BufferedImage bufferedImageAReconnaitre;
	boolean paysage = true;//donne l’orientation de l’image non reconnue
	
	
	//Fenetres provenance susceptibles d'être fermées
	public Fenetre FenetSelect;
	public Fenetre FenetResult;
	public Fenetre FenetInconnue;
	
	
	//éléments boutons
    public JButton monBouton;//Le bouton de la fenêtre initialisation qui a été rendu invisible
    private JButton monBouton2;//bouton gérant le retour à la page d’accueil
	private JButton monBouton3;//bouton gérant la fermeture de l’application
    
    //dimensions de l'écran pour s'adapter à ceux-ci lors de nos affichages
    public int width;
    public int height;
    
    //entier prenant 1 si FenetreInconnue est ouverte depuis FenetreSelection, 2 si ouverte depuis FenetreResultat
    private int or;

	//Constructeur
	
	public FenetreInconnue(Image img, int or)  {
		
		//On récupère les dimensions de l'écran pour adapter notre affichage
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)screenSize.getWidth() +2;
        height = (int)screenSize.getHeight() -40;
        
        //Récupération image de fond
        this.papierPeint = img;
        //récupération origine
        this.or=or;
        
        //On place les objets nous-même
        setLayout(null);
        
        //Récupération de l'image à reconnaître depuis la méthode de fenêtre initialisation
        this.imageAReconnaitre = FenetreInitialisation.imageAReconnaitre();
        this.bufferedImageAReconnaitre = FenetreInitialisation.bufferedImageAReconnaitre();
        
        //On détermine si l'image est en format paysage ou en format portrait
        if ((bufferedImageAReconnaitre.getWidth(null)) < (bufferedImageAReconnaitre.getHeight(null))){
			paysage = false;
		}
        
		
		/**Création d'une nouvelle police : Times NewRoman */
        Font police = new Font(" Verdana ",Font.BOLD,20);
        
        
        /** Création du bouton2 */
        this.monBouton2 = new JButton("Choisir une autre photo");
        monBouton2.setBounds(180*(width/1368),620*(height/728),300*(width/1368),75*(height/728));
        //Application de la police police
        monBouton2.setFont(police);
        //Couleur du texte
		monBouton2.setForeground(Color.black);
		
		/** Création du bouton3 */
        this.monBouton3 = new JButton("Quitter l'application");
        monBouton3.setBounds(530*(width/1368),620*(height/728),300*(width/1368),75*(height/728));
		monBouton3.setBackground(Color.red);
        //Application de la police police
        monBouton3.setFont(police);
        //Couleur du texte
		monBouton3.setForeground(Color.black);
		
		/** Création du JLabel Titer*/
		JLabel Titre = new JLabel();
       //Application de la police police
		Titre.setFont(police);
        Titre.setText("Peinture Inconnue !");
        Titre.setBounds(85*(width/1368),20*(height/728),350*(width/1368),40*(height/728));
        //Couleur du texte
        Titre.setForeground(Color.black);
        
        /** Les conteneurs */
        
        // Le conteneurTitre
        JPanel conteneurTitre = new JPanel();
        conteneurTitre.setLayout(null);
        conteneurTitre.setBounds(10*(width/1368),10*(height/728),350*(width/1368),70*(height/728));
        conteneurTitre.setBackground(new Color(255, 238, 230));
        //Ajout du titre dans le conteneur titre
        conteneurTitre.add(Titre);
        
        //~ Le conteneur1
        JPanel conteneur1 = new JPanel();
		conteneur1.setLayout(null);
		conteneur1.setBounds(300*(width/1368),75*(height/728),370*(width/1368),90*(height/728));
		conteneur1.setBackground(new Color(150, 156, 186));
		//Ajout du conteneur Titre
		conteneur1.add(conteneurTitre);
        
        //Ajout des deux conteneur 1
        this.add(conteneur1);
        //Ajout des boutons au conteneur
        this.add(monBouton2);
        this.add(monBouton3);
        //Ajout d'un actionListener aux boutons
        this.monBouton2.addActionListener(this);
		this.monBouton3.addActionListener(this);
	
    }
    
    //Méthode gérant l'action sur les boutons
    
    public void actionPerformed(ActionEvent e) {
		
		//Récupération de la source du clic
		Object  source=e.getSource();
		
        if  (source==monBouton2){//Si la source du clic est le bouton2
			
			//Récupération et fermeture de FenetreSelection
			this.FenetSelect=FenetreInitialisation.getFenetSelect();
			FenetSelect.dispose();
			this.monBouton = FenetreInitialisation.getBouton();
           
			if (or==1){//Si la fenetre a été ouverte depuis FenetreSelection
				
				//Récupération et fermeture de la page
				this.FenetInconnue=FenetreSelection.getFenetInconnue();
				FenetInconnue.dispose();
			}
			
			if (or==2){//Si la fenetre a été ouverte depuis FenetreResutats
			   
				//Récupération et fermeture de FenetreSelection
				this.FenetResult=FenetreSelection.getFenetResult();
				FenetResult.dispose();
				
				//Récupération et fermeture de la page
				this.FenetInconnue=FenetreResultats.getFenetInconnue();
				FenetInconnue.dispose();
			}
		}
		
        if (source==monBouton3){//Si la source du clic est le bouton3
            System.exit(0);//Fermeture application
		}
	}
	
	//Méthode permettant de dessiner les différents composants
    public void paintComponent(Graphics g) {
		
		//dessin du fond
		g.drawImage(papierPeint, 0, 0, this.getWidth(), this.getHeight(), this);
		
		if (paysage){//Si image non reconnue format paysage
			g.drawImage(imageAReconnaitre, 210*(width/1368), 180*(height/728), this.getWidth()*3/5, this.getHeight()*3/5, this);
		}
		else{//Si image non reconnue formay portrait
			g.drawImage(imageAReconnaitre, 297*(width/1368), 180*(height/728), this.getWidth()*2/5, this.getHeight()*7/12, this);
		}
	}
}
