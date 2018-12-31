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


public class FenetreSelection extends JPanel implements MouseListener{
	
	//élément affichage
	private Image papierPeint;
	
	//élément de la photo chargée par l'utilisateur
	public PeintureAnalysee peintureAnalysee;
	public boolean paysage;
	public BufferedImage bufferedImageAReconnaitre;
	public Image imageAReconnaitre;
	public int [][] posPointsAnalysesTest; //on affiche les points analysés sous formes de points colorés pour vérifier que l'analyse s'est bien faite
	public int [][] ColorPointsAnalysesTest; ////on affiche les points analysés sous formes de points colorés pour vérifier que l'analyse s'est bien faite
	public int cmptCoin=0; //nombre de coins sélectionnés par l'utilisateur
	public int [][] tabPositionCoin;
	
	//Nouvelles fenêtres suceptibles d'êtres créées
	public static Fenetre FenetInconnue;
	public static Fenetre FenetResult;
	
	//tableau de deux peintures qui à l'issu de la comparaison semblent être les bonnes (on en prend deux pour avoir plus de chances de trouver la bonne)
	PeintureData[] peintureCorrespondante;
	
	//base de données de peintures data
	public BaseDeDonnees BD;
	
	
	//dimensions de l'écran pour s'adapter à ceux-ci lors de nos affichages
	public double width;
	public double height;
		
	//Constructeur
	public FenetreSelection(Image img)  {
		
		paysage = true;
        this.papierPeint = img;
        setLayout(null);
        
        //On récupère la photo chargée par l'utilisateur depuis FenetreInitialisation
        this.bufferedImageAReconnaitre = FenetreInitialisation.bufferedImageAReconnaitre(); 
        this.imageAReconnaitre = FenetreInitialisation.imageAReconnaitre();
        
        //on prend les dimensions de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
		
		
		//Panneau consignes si la photo chargée par l'utilisateur est en format portrait
		JPanel consignePortrait = new JPanel();
		consignePortrait.setBounds((int)(width/2.1),(int)(height*0.70)/2,(int)(700*width/1368),(int)(230*height/728));
		consignePortrait.setBackground(Color.black);
		consignePortrait.setLayout(null);
		
		//Panneau consignes si la photo chargée par l'utilisateur est en format paysage
		JPanel consignePaysage = new JPanel();
		consignePaysage.setBounds(10,20+(int)(width*0.7*0.5625),(int)(width*0.7),100);
		consignePaysage.setBackground(Color.black);
		consignePaysage.setLayout(null); 
		
		
		//On crée le texte de consignePortrait		
		Font police = new Font(" Verdana ",Font.ITALIC,25);		
		JTextArea Consigne1 = new JTextArea();
        Consigne1.setFont(police);
        Consigne1.setEditable(false);
		Consigne1.setText("Selectionne les 4 coins de ton image dans l'ordre suivant : \n \n 1) haut gauche \n 2) haut droit \n 3) bas gauche \n 4) bas droit ");
        Consigne1.setBounds(7,7,consignePortrait.getWidth() -15,consignePortrait.getHeight() - 15);
        Consigne1.setForeground(Color.black);
        
        //Ajout du texte à consignePortait
        consignePortrait.add(Consigne1);
        
        //On crée le texte de consignePaysage				
		JTextArea Consigne2 = new JTextArea();
        Consigne2.setFont(police);
        Consigne2.setEditable(false);
		Consigne2.setText("Selectionne les 4 coins de ton image dans l'ordre suivant : \n 1) haut gauche 2) haut droit 3) bas gauche 4) bas droit ");
        Consigne2.setBounds(7,7,consignePaysage.getWidth() -15,consignePaysage.getHeight() - 15);
        Consigne2.setForeground(Color.black);
        
        //Ajout du texte à consignePaysage
        consignePaysage.add(Consigne2);
        
        //on instancie notre base de données (et donc on instancie aussi toutes peintures de cette base)
		BD = new BaseDeDonnees();
		
		//on dit si l'image à reconnaitre est en format paysage ou en format portrait
        if ((bufferedImageAReconnaitre.getWidth(null)) < (bufferedImageAReconnaitre.getHeight(null))){
			paysage = false;
		}
		
        //On ajoute l'écouteur de type Mouse
		this.addMouseListener(this);
		
		//On redimensionne l'image à reconnaitre pour que l'affichage corresponde à sa vraie taille
		if (paysage==true){
			imageAReconnaitre=imageAReconnaitre.getScaledInstance((int)(width*0.7) ,(int)(width*0.7*0.5625), Image.SCALE_DEFAULT);
			add(consignePaysage);
		}
		else{
			imageAReconnaitre=imageAReconnaitre.getScaledInstance((int)(height*0.85*0.8) ,(int)(height*0.85), Image.SCALE_DEFAULT);
			add(consignePortrait);
		}
		
		//On instancie le tableau qui contiendra les coordonnées des 4 points correspondants aux coins de l'image sélectionnés par l'utilisateur
		tabPositionCoin = new int [4][2];
		
    }
    
    
    public void paintComponent(Graphics g) {
		//affichage de l'arrière plan
		g.drawImage(papierPeint, 0, 0, this.getWidth(), this.getHeight(), this);
		
		//on adapte l'affichage de la photo en fonction de son format
		if (paysage){
			g.drawImage(imageAReconnaitre, 10,10,(int)(width*0.7) ,(int)(width*0.7*0.5625) , this);
		}
		else{
			g.drawImage(imageAReconnaitre, 10,10,(int)(height*0.85*0.8) ,(int)(height*0.85),  this);
		}
		
		//On affiche les points dont on va analyser les couleurs pour être sur que tout s'est bien passé lors de la selection des coins
		if (peintureAnalysee != null){
			for (int i = 0 ; i<384 ; i++){
				g.setColor (new Color(ColorPointsAnalysesTest[i][0], ColorPointsAnalysesTest[i][1], ColorPointsAnalysesTest[i][2]));
				g.fillOval(posPointsAnalysesTest[i][0]-5+10 , posPointsAnalysesTest[i][1]-5+10 , 10 , 10);
			}
		}
	}
	
	
	public void createPeintureAnalysee () {
		
		//on crée l'instance qui correspond à l'objet à analyser 
		peintureAnalysee = new PeintureAnalysee (tabPositionCoin , imageAReconnaitre);
		
		//on récupère et on affiche les points analysés
		posPointsAnalysesTest = peintureAnalysee.posPointsAnalyses;
		ColorPointsAnalysesTest = peintureAnalysee.rgbValues;
		repaint();
		
		//on retrouve la peinture correspondante
		peintureCorrespondante = findPeinture();
		
		//Soit on trouve un résultat et on l'affiche, sinon on dit qu'il n'y a pas de peinture correspondante
		if(peintureCorrespondante[0]!=null){
			this.FenetResult= new Fenetre(width+2,height-40, "fonds/cadre.jpg", 3 , peintureCorrespondante);
		}
		else{
			this.FenetInconnue= new Fenetre(width+2-(int)(0.3*width),height-40, "fonds/cadre.jpg", 4 , null);
		
		}
	}
	
	public PeintureData[] findPeinture (){
		//on trouve la peinture correspondante
		PeintureData[] peintureTrouvee = new PeintureData [2] ;
		int seuil = 65; // seuil en % en dessous duquel on considère qu'aucune peinture n'a été retrouvée
		int maxTemp1 = 0; //on stocke la valeur en % de la meilleure correspondace
		int maxTemp2 = 0; //on stocke la valeur en % de la deuxième meilleure correspondace
		
		//On trouve la meilleure et la deuxième meilleure correspondance
		for (int i = 0 ; i<BD.getSize() ; i++){
			if ((((BD.getPeinture(i)).compareTo(peintureAnalysee)*100)/(peintureAnalysee.nbPointsValides))>=seuil && (((BD.getPeinture(i)).compareTo(peintureAnalysee)*100)/(peintureAnalysee.nbPointsValides))>maxTemp1 && (BD.getPeinture(i)).paysage == peintureAnalysee.paysage){
				peintureTrouvee[1] = peintureTrouvee[0];
				peintureTrouvee[0] = BD.getPeinture(i) ;
				maxTemp2 = maxTemp1;
				maxTemp1 = ((BD.getPeinture(i)).compareTo(peintureAnalysee)*100)/(peintureAnalysee.nbPointsValides);
			}
			if ((((BD.getPeinture(i)).compareTo(peintureAnalysee)*100)/(peintureAnalysee.nbPointsValides))>=seuil && (((BD.getPeinture(i)).compareTo(peintureAnalysee)*100)/(peintureAnalysee.nbPointsValides))>maxTemp2 && (((BD.getPeinture(i)).compareTo(peintureAnalysee)*100)/(peintureAnalysee.nbPointsValides))<maxTemp1 && (BD.getPeinture(i)).paysage == peintureAnalysee.paysage){
				peintureTrouvee[1] = BD.getPeinture(i) ;
				maxTemp2 = ((BD.getPeinture(i)).compareTo(peintureAnalysee)*100)/(peintureAnalysee.nbPointsValides);
			}
		}
		return peintureTrouvee ;
	}
	
	//Méthode qui renvoyant FenetInconnue
	public static Fenetre getFenetInconnue(){
		return FenetInconnue;
	}
	
	//Fenetre renvoyant FenetResult
	public static Fenetre getFenetResult(){
		return FenetResult;
	}
		
	
	public void mouseClicked (MouseEvent e){
		
		//à partir d'un clic de la souris on définit les coordonnées d'un coin
		System.out.println (e.getX() + "   " + e.getY());
		tabPositionCoin[cmptCoin][0] = e.getX()-10;
		tabPositionCoin[cmptCoin][1] = e.getY()-10;
		cmptCoin++;
		
		//si les 4 coins ont été sélectionnés, on appelle la méthode createPeintureAnalysee()
		if (tabPositionCoin [3][1] !=0){
			createPeintureAnalysee();
		}
	}
	
	
	
	public void mouseEntered (MouseEvent e){}
	public void mouseExited (MouseEvent e){}
	public void mousePressed (MouseEvent e){}
	public void mouseReleased (MouseEvent e){}
	
}
