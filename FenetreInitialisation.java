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
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;



public class FenetreInitialisation extends JPanel implements ActionListener{
 
	//Initialisations des attributs
	
	//élément affichage
    private Image papierPeint;
    
    //élément de la photo chargée par l'utilisateur
    public static Image imageAReconnaitre;//implémentée si l’utilisateur choisit de faire traiter une photo
    public static BufferedImage BufferedImageAReconnaitre;
	
	//éléments fileChooser
	private String fileName="";//récupère le chemin absolu de l'image sélectionnée en vue d’un traitement, ou de celle à ajouter dans la base
	public static File fichier;//récupère le file sélectionné par un JFileChooser grâce aux boutons d’actions
	
	
	//éléments boutons
	public static JButton monBouton;
	public static JButton monBouton1;
	
	//Fenêtre susceptible d'être créée
    public static Fenetre FenetSelect=null;
    
    //Constructeur
    
    public FenetreInitialisation(Image img){
		
		
        this.papierPeint = img;//image de fond
        setLayout(null);//On place les objets nous-même
    }
    
    
    //Méthode gérant l'action sur les boutons 
    
    public void actionPerformed(ActionEvent e) {
		
		
		FileSystemView vueSysteme = FileSystemView.getFileSystemView();  
		//Récupération des répertoires de Mes documents (getDefaultDirectory)
		File defaut = vueSysteme.getDefaultDirectory();
		
		//Filtration des extensions
		FileFilter imageFormats = new FileNameExtensionFilter("Image Files","jpg","jpeg", "png","bmp","gif","tif" );

		//Création du JFileChooser
		JFileChooser defautChooser = new JFileChooser(defaut);
		defautChooser.setDialogTitle("Choisir une image");
		
		//Restriction des files à notre JFileChooser 
		defautChooser.setAcceptAllFileFilterUsed(false);
		//Ajout des filtres à notre JFileChooser
		defautChooser.addChoosableFileFilter(imageFormats);
		
		if (e.getSource() == monBouton){//Si clic sur le premier bouton
		
			
			if (defautChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {//Si le chooser attend une sélection, il s'affiche
				
				//Récupération du chemin absolu de l'image sélectionnée
				this.fileName = defautChooser.getSelectedFile().getAbsolutePath();
				//Récupération du file sélectionné
				this.fichier = defautChooser.getSelectedFile();
				
				//Chargement de l'image à reconnaitre en format image
				Toolkit t = Toolkit.getDefaultToolkit();
				this.imageAReconnaitre = t.getImage(fileName);
				
				// Chargement de l'image à reconnaitre en format bufferedImage bufferedImage
				try {
					BufferedImageAReconnaitre = ImageIO.read(fichier);
				} 
				catch (IOException ae) {
				  // Do something here
				}
				
				//On récupère les dimensions de l'écran pour adapter notre affichage lors de l'affichage de la classe FentreSelection
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				double width = screenSize.getWidth();
				double height = screenSize.getHeight();
				
				//Création de la fenêtre sélection qui utilisera l'ImageAReconnaitre
				this.FenetSelect= new Fenetre(width+2,height-40, "fonds/fond-selec.jpg", 2 , null);
				
				/*Tant que la fenêtre sélection est ouverte, on ne peut plus faire triater de nouvelles photos par l'appli
				 * On rend invisible et inactif le premier bouton, mais la fenetre reste ouverte afin qu'on puisse ajouter une image à la base si besoin*/
				this.monBouton.setEnabled(false);
				this.monBouton.setVisible(false);
				
			}   
		}
		
		//Si clic sur le deuxième bouton
		if (e.getSource() == monBouton1){
		
			if (defautChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {//Si le chooser attend une sélection, il s'affiche
				
				//Récupération du chemin absolu de l'image sélectionnée
				this.fileName = defautChooser.getSelectedFile().getName();
				//Récupération du file sélectionné
				this.fichier = defautChooser.getSelectedFile();
            
				
				try {
					// Chargement de l'image à reconnaitre en format bufferedImage bufferedImage
					BufferedImage bufferedImage = ImageIO.read(fichier);
					//Ajout de l'image à la base de données
					File outputfile = new File("BDImagesOpenSource/"+fileName);
					ImageIO.write(bufferedImage, "jpg", outputfile);	
                }
                catch (IOException ae) {
					// Do something here
				}	
			}
		}
	 }  
	
	
	//Méthode permettant de renvoyer l'attibut BufferedImageAReconnaitre
	public static BufferedImage bufferedImageAReconnaitre(){
		return BufferedImageAReconnaitre;
	}
	
	//Méthode permettant de renvoyer l'attibut imageAReconnaitre
	public static Image imageAReconnaitre(){
		return imageAReconnaitre;
	}
	
	//Méthode permettant de renvoyer l'attibut fichier
	public static File Fichier(){
		return fichier;
	}

	//Méthode permettant de réinitialise et renvoie le premier bouton (alors actif et visible)
	public static JButton getBouton(){
		
		monBouton.setEnabled(true);//Le premier bouton redevient actif
		monBouton.setVisible(true);//Le premier bouton redevient visible
		return monBouton;
	}
	
	//Méthode permettant de renvoyer l'attibut FenetSelect
	public static  Fenetre getFenetSelect(){
		return FenetSelect;
	}
	
	//Méthode permettant l’affichage du papier peint ainsi que du titre et des boutons de sélections 
    public void paintComponent(Graphics g) {
		
		
		/**Création de nouvelles polices*/
        Font police = new Font(" Arial ",Font.BOLD,30);
        Font police1 = new Font(" Arial ",Font.BOLD,45);
        Font police2 = new Font(" Arial ",Font.BOLD,23);
        
        
        /** Création du premier bouton */
        this.monBouton = new JButton("Importe la peinture que tu souhaites identifier ! ");
        monBouton.setBounds(15,15,775,70);
        //Application de la police police
        monBouton.setFont(police);
        //Couleur du texte
		monBouton.setForeground(Color.black);
		
		/** Création du deuxième bouton */
        this.monBouton1 = new JButton("Ajoute ta propre oeuvre en la renommant nom-oeuvre_nom-auteur ");
        monBouton1.setBounds(15,95,775,70);
        //Application de police2
        monBouton1.setFont(police2);
        //Couleur du texte
		monBouton1.setForeground(Color.black);
		
		
		/** Création du JLabel Titre */
		JLabel Titre = new JLabel();
        //Application de police1
		Titre.setFont(police1);
        Titre.setText("Bienvenue dans APN ! ");
        Titre.setBounds(85,30,800,40);
        //Couleur du texte
        Titre.setForeground(Color.black);
        
        
        /** Les conteneurs */
        
        // Le conteneurTitre
        JPanel conteneurTitre = new JPanel();
        conteneurTitre.setLayout(null);
        conteneurTitre.setBounds(10,10,680,110);
        conteneurTitre.setBackground(new Color(255, 166, 0));
        //Ajout du titre dans le conteneur titre
        conteneurTitre.add(Titre);
        
        
        //~ Le conteneurOeuvre
        JPanel conteneurOeuvre = new JPanel();
        conteneurOeuvre.setLayout(null);
        conteneurOeuvre.setBounds(10,10,800,180);
        conteneurOeuvre.setBackground(Color.yellow); // couleur du conteneur du texte 
        //Ajout des boutons au conteneur
        conteneurOeuvre.add(monBouton);
        conteneurOeuvre.add(monBouton1);
        
        //~ Le conteneur1
        JPanel conteneur1 = new JPanel();
		conteneur1.setLayout(null);
		conteneur1.setBounds(this.getWidth()/2-700/2,55,700,130);
		conteneur1.setBackground(Color.black);
		//Ajout du conteneur Titre
		conteneur1.add(conteneurTitre);
        
        //~ Le conteneur2
        JPanel conteneur2 = new JPanel();
		conteneur2.setLayout(null);
		conteneur2.setBounds(this.getWidth()/2-820/2,400,820,200);
		conteneur2.setBackground(Color.black);
		//Ajout du conteneur oeuvre
		conteneur2.add(conteneurOeuvre);
		
		//Ajout des deux conteneurs principaux
        add(conteneur1);
        add(conteneur2);
        //Ajout d'un actionListener aux boutons
        monBouton.addActionListener(this);
        monBouton1.addActionListener(this);
    
        //dessin du fond
        g.drawImage(papierPeint, 0, 0, this.getWidth(), this.getHeight(), this);
        
        
    }
}
