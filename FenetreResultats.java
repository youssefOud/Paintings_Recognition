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
import java.awt.Container;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileFilter;

public class FenetreResultats extends JPanel implements ActionListener{
 
 //Initialisations des attributs
 
    private Image papierPeint;
    private Image BonnePeinture;
    private JButton monBoutonNon;
    private JButton monBoutonOui;
    private String Nom;
    private int essaie;
    private PeintureData peintureCorrespondante;
    private PeintureData peintureCorrespondante1;
	public static Fenetre FenetInconnue;
    
    JLabel Auteur;
    JLabel Titre ;
    JScrollPane sp;
    
    public Fenetre FenetSelect;
    public Fenetre FenetResult;
    public JButton monBouton;
    
    //attributs pour adapter l'affichage à la taille de l'écran
    public double width;
    public double height;
    

 //Constructeur
     
    public FenetreResultats(Image img, PeintureData[] tabPeintureCorrespondante){
		
		//On récupère les dimensions de l'écran pour adapter notre affichage
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth() +2;
        height = screenSize.getHeight() -40;
        
		this.papierPeint = img; //image de fond
		peintureCorrespondante= tabPeintureCorrespondante[0]; //tableau le plus cohérent selon notre programme
		peintureCorrespondante1= tabPeintureCorrespondante[1]; //tableau de secours le sencond plus cohérent selon notre programme
		essaie =0; //on essaies avec le premier tableau
		actualiserFenetre(peintureCorrespondante , essaie); //premier appel à la méthode actualiserFenetre afin d'essayer avec le premier tableau

        
    }
    
	//Méthode permettant d'actualiser la fenetre suite à un appui sur le bouton 

   public void actualiserFenetre(PeintureData peinture , int n){
		if (n == 1) {
			Auteur.setVisible(false);
			Titre.setVisible(false);
			sp.setVisible(false);
		}
        essaie++;
        Toolkit b = Toolkit.getDefaultToolkit();
        BonnePeinture = b.getImage(peinture.nomFichier);
        Nom= peinture.titre; 
        String Artiste = peinture.auteur; 
        String Descrip = peinture.description; 
        setLayout(null);
        
        /**Crée une nouvelle police : Times NewRoman */
        Font policeTitre = new Font(" Verdana ",Font.BOLD,50);
        Font policeDescrip = new Font(" Times NewRoman ",Font.ITALIC,20);
        Font policeAuteur = new Font(" Verdana ",Font.BOLD,30);
      
		
		JTextArea Description = new JTextArea();
		Description.append(Descrip);
		Description.setFont(policeDescrip);
		Description.setForeground(new Color (255, 138, 0)); 
		Description.setEditable(false);
		Description.setWrapStyleWord(true);
		sp = new JScrollPane (Description , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) ; 
		sp.setBounds((int)(825*(width/1368)), (int)(290*(height/728)), (int)(500*(width/1368)), (int)(370*(height/728)));
		
		Description.setLineWrap(true);
        		
		add(sp); 
	
         if (n ==0){
		//Panneau bouton
		JPanel panneauBouton = new JPanel();
		panneauBouton.setBounds((int)(200*(width/1368)),(int)(540*(height/728)),(int)(500*(width/1368)),(int)(100*(height/728)));
		panneauBouton.setBackground(new Color (207, 234, 242) );
		panneauBouton.setLayout(null);

		//Bouton Oui
        this.monBoutonOui = new JButton("Merci pour les informations ");
		monBoutonOui.setBounds((int)(10*(width/1368)),(int)(10*(height/728)),(int)(225*(width/1368)),(int)(80*(height/728)));
		monBoutonOui.setBackground(new Color(255,202,0));
		monBoutonOui.setForeground(Color.black);
		panneauBouton.add(monBoutonOui);
        monBoutonOui.addActionListener(this);
		
		//Bouton Non
		this.monBoutonNon = new JButton("Ce n'est pas le bon tableau...");
		monBoutonNon.setBounds((int)(260*(width/1368)),(int)(10*(height/728)),(int)(225*(width/1368)),(int)(80*(height/728)));
		monBoutonNon.setBackground(new	Color(255, 0, 158) );
		monBoutonNon.setForeground(Color.black);
		panneauBouton.add(monBoutonNon);
        monBoutonNon.addActionListener(this);
        add(panneauBouton);
	    }
        
        //Création du titre
			//Panneau titre
		JPanel panneauTitre = new JPanel();
		panneauTitre.setBounds((int)(1040*(width/1368)),(int)(110*(height/728)),(int)(516*(width/1368)),(int)(119*(height/728)));
		panneauTitre.setBackground(Color.white);
		panneauTitre.setLayout(null);
			//Création du JLabel 
		Titre = new JLabel();
			//Application de la police
		Titre.setFont(policeTitre);
        Titre.setText(Nom);
        Titre.setBounds((int)(850*(width/1368)),(int)(110*(height/728)),(int)(516*(width/1368)),(int)(119*(height/728)));
        //Couleur du texte
        Titre.setForeground(Color.black);
		
        add(Titre);
        
        //Création de l'auteur
			//Panneau auteur
		JPanel panneauAuteur = new JPanel();
		panneauAuteur.setBounds((int)(750*(width/1368)),(int)(180*(height/728)),(int)(516*(width/1368)),(int)(119*(height/728)));
		panneauAuteur.setBackground(Color.white);
		panneauAuteur.setLayout(null);
			//Création du JLabel 
		Auteur = new JLabel();
			//Application de la police
		Auteur.setFont(policeAuteur);
        Auteur.setText("par  "+Artiste);
        Auteur.setBounds((int)(1000*(width/1368)),(int)(180*(height/728)),(int)(516*(width/1368)),(int)(119*(height/728)));
			//Couleur du texte
        Auteur.setForeground(Color.black);
         
        add(Auteur);
        
        
        repaint();
	}
	
	
//Méthode permettant de revenir à la fenetre FenetInconnue

	public static Fenetre getFenetInconnue(){
		return FenetInconnue;
	}
    
    
//Méthode permettant de dessiner les différents composants

    public void paintComponent(Graphics g) {
		
       g.drawImage(papierPeint, 0, 0, this.getWidth(), this.getHeight(), this);
       if (peintureCorrespondante.paysage){
			g.drawImage(BonnePeinture,(int)(140*(width/1368)),(int)(130*(height/728)),(int)(650*(width/1368)),(int)(363*(height/728)), this); 
		}
		else {
			g.drawImage(BonnePeinture,(int)(200*(width/1368)),(int)(60*(height/728)),(int)(363*(width/1368)),(int)(450*(height/728)), this); 
		}
        
    }
       
       
//Méthode permettant l'interaction avec les boutons 
 
    public void actionPerformed (ActionEvent e){
			
			//cas où on clique sur le bouton OUI
			if(e.getSource()== monBoutonOui ){
				System.out.println("Oui"); //Affichage dans le terminal
				this.FenetSelect=FenetreInitialisation.getFenetSelect();
				FenetSelect.dispose();
				this.FenetResult=FenetreSelection.getFenetResult();
				FenetResult.dispose();
				this.monBouton = FenetreInitialisation.getBouton();
            }
        
			//cas où on clique sur le bouton 	NON
			if(e.getSource()== monBoutonNon ){
				System.out.println("Non"); //Affichage dans le terminal
				
					if (essaie == 2 || peintureCorrespondante1 == null){ 
						//Si la deuxième oeuvre ne correspond toujours pas, création d'une fenetre oeuvre inconnue
						this.FenetInconnue= new Fenetre(width-(int)(0.3*width),height, "fonds/cadre.jpg", 5 , null); 
						FenetInconnue.setVisible(true);
						
					} else if (essaie == 1 ){
						//Si le premier essai n'est pas concluant
						actualiserFenetre(peintureCorrespondante1 , essaie);
					}	
			}
		}
		
}
