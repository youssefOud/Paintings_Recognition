import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PeintureAnalysee{
	
	//Paramètres de la peinture analysée
	public int[][] posSommets ; //Position des coins du tableau sur la photo
	public int [][] posPointsAnalyses; //Position des points (x,y) dont la couleur des pixels va être sélectionnée
	public int [] hValues ; //la valeur "teinte" comprise entre 0 et 360 des pixels analysés
	public int [][] rgbValues ; //tableau des valeurs rgb des points analysés
	public static int nbPointsValides=1; //Nombre de points dont la couleur est considérée "assez marquée" (valeur de saturation supérieur à 35 %) pour pouvoir être anlysés 
	static Image imageAnalysee; //photo chargée par l'utilisateur
	boolean paysage = true; //Caractérisation du format de la peinture (image ou portrait)
	
	//Elements bufferedImage
	static BufferedImage imageBuff;
	Graphics buffer;
	
	
	public PeintureAnalysee (int [][] tabSommets, Image imageAnalysee) {
		
		this.imageAnalysee = imageAnalysee;
		
		posSommets = new int [4][2];
		for (int i = 0 ; i<posSommets.length ; i++){
			for (int j = 0 ; j<posSommets[i].length ; j++){
				posSommets[i][j] = tabSommets [i][j];
			}
		}
		
		//On détermine si la peinture à analyser est en format paysage ou portrait
		if (Math.pow((Math.pow((tabSommets [1][0] - tabSommets [0][0]),2)+Math.pow((tabSommets [1][1] - tabSommets [0][1]),2)),0.5) < Math.pow((Math.pow((tabSommets [2][0] - tabSommets [0][0]),2)+Math.pow((tabSommets [2][1] - tabSommets [0][1]),2)),0.5) ){
			paysage = false;
		}
		
		//On convertit notre image en BufferedImage pour qu'on puisse réupérer la couleur de ses pixels
		imageBuff = new BufferedImage(imageAnalysee.getWidth(null), imageAnalysee.getHeight(null) , BufferedImage.TYPE_INT_RGB);
        buffer = imageBuff.getGraphics(); 
        buffer.drawImage(imageAnalysee,0,0,null);
        
		//On calcule la position des points qui seront analysés grâce à la méthode: calcPosPointsAnalyses () 
		posPointsAnalyses = calcPosPointsAnalyses (posSommets);
		
		//On donne une valeur de teinte et de rgb à chacun des 384 points analysés
		int[][] tab = new int [384][4];
		hValues = new int [384];
		rgbValues = new int [384][3];
		tab = generateColorData(posPointsAnalyses); 
		
		for (int i = 0 ; i< 384 ; i++){
			rgbValues[i][0] = tab[i][0];
			rgbValues[i][1] = tab[i][1];
			rgbValues[i][2] = tab[i][2];
			hValues[i] = tab[i][3];
		}
	}
	
	public int [][] calcPosPointsAnalyses (int [][] posSommets){
		//On commence par définir les positions des bords latéraux de notre image (16 points par coté)
		
		//Valeurs qui serviront à définir le décalage entre deux coins pour pouvoir bien définir nos points latéraux même si le tableau n'est pas parfaitement rectangulaire
		int h1 = posSommets[2][1]-posSommets[0][1]; 
		int l1 = posSommets[2][0]-posSommets[0][0];
		int h2 = posSommets[3][1]-posSommets[1][1];
		int l2 = posSommets[3][0]-posSommets[1][0];
		
		//On définit les positions des points latéraux
		int [][] posBords = new int [32][2];
		for (int i = 0 ; i<posBords.length ; i=i+2){
			posBords[i][0] = posSommets[0][0] + ((i+2)/2)*l1/17; 
			posBords[i][1] = posSommets[0][1] + ((i+2)/2)*h1/17;
		}
		for (int i = 1 ; i<posBords.length ; i=i+2){
			posBords[i][0] = posSommets[1][0] + ((i+1)/2)*l2/17;
			posBords[i][1] = posSommets[1][1] + ((i+1)/2)*h2/17;
		}
		
		//on relie les points latéraux qui sont face à face avec 24 points (cela donne 24*16 points au total, soit 384 points)
		posPointsAnalyses = new int [384][2];
		for (int i = 0 ; i<32 ; i=i+2){
			int h3 = posBords[i+1][1]-posBords[i][1];
			int l3 = posBords[i+1][0]-posBords[i][0];
			posPointsAnalyses [(12*i)][0] = posBords [i][0]+l3/25;
			posPointsAnalyses [(12*i)][1] = posBords [i][1]+h3/25;
			posPointsAnalyses [(12*i)+1][0] = posBords [i][0]+2*l3/25;
			posPointsAnalyses [(12*i)+1][1] = posBords [i][1]+2*h3/25;
			posPointsAnalyses [(12*i)+2][0] = posBords [i][0]+3*l3/25;
			posPointsAnalyses [(12*i)+2][1] = posBords [i][1]+3*h3/25;
			posPointsAnalyses [(12*i)+3][0] = posBords [i][0]+4*l3/25;
			posPointsAnalyses [(12*i)+3][1] = posBords [i][1]+4*h3/25;
			posPointsAnalyses [(12*i)+4][0] = posBords [i][0]+5*l3/25;
			posPointsAnalyses [(12*i)+4][1] = posBords [i][1]+5*h3/25;
			posPointsAnalyses [(12*i)+5][0] = posBords [i][0]+6*l3/25;
			posPointsAnalyses [(12*i)+5][1] = posBords [i][1]+6*h3/25;
			posPointsAnalyses [(12*i)+6][0] = posBords [i][0]+7*l3/25;
			posPointsAnalyses [(12*i)+6][1] = posBords [i][1]+7*h3/25;
			posPointsAnalyses [(12*i)+7][0] = posBords [i][0]+8*l3/25;
			posPointsAnalyses [(12*i)+7][1] = posBords [i][1]+8*h3/25;
			posPointsAnalyses [(12*i)+8][0] = posBords [i][0]+9*l3/25;
			posPointsAnalyses [(12*i)+8][1] = posBords [i][1]+9*h3/25;
			posPointsAnalyses [(12*i)+9][0] = posBords [i][0]+10*l3/25;
			posPointsAnalyses [(12*i)+9][1] = posBords [i][1]+10*h3/25;
			posPointsAnalyses [(12*i)+10][0] = posBords [i][0]+11*l3/25;
			posPointsAnalyses [(12*i)+10][1] = posBords [i][1]+11*h3/25;
			posPointsAnalyses [(12*i)+11][0] = posBords [i][0]+12*l3/25;
			posPointsAnalyses [(12*i)+11][1] = posBords [i][1]+12*h3/25;
			posPointsAnalyses [(12*i)+12][0] = posBords [i][0]+13*l3/25;
			posPointsAnalyses [(12*i)+12][1] = posBords [i][1]+13*h3/25;
			posPointsAnalyses [(12*i)+13][0] = posBords [i][0]+14*l3/25;
			posPointsAnalyses [(12*i)+13][1] = posBords [i][1]+14*h3/25;
			posPointsAnalyses [(12*i)+14][0] = posBords [i][0]+15*l3/25;
			posPointsAnalyses [(12*i)+14][1] = posBords [i][1]+15*h3/25;
			posPointsAnalyses [(12*i)+15][0] = posBords [i][0]+16*l3/25;
			posPointsAnalyses [(12*i)+15][1] = posBords [i][1]+16*h3/25;
			posPointsAnalyses [(12*i)+16][0] = posBords [i][0]+17*l3/25;
			posPointsAnalyses [(12*i)+16][1] = posBords [i][1]+17*h3/25;
			posPointsAnalyses [(12*i)+17][0] = posBords [i][0]+18*l3/25;
			posPointsAnalyses [(12*i)+17][1] = posBords [i][1]+18*h3/25;
			posPointsAnalyses [(12*i)+18][0] = posBords [i][0]+19*l3/25;
			posPointsAnalyses [(12*i)+18][1] = posBords [i][1]+19*h3/25;
			posPointsAnalyses [(12*i)+19][0] = posBords [i][0]+20*l3/25;
			posPointsAnalyses [(12*i)+19][1] = posBords [i][1]+20*h3/25;
			posPointsAnalyses [(12*i)+20][0] = posBords [i][0]+21*l3/25;
			posPointsAnalyses [(12*i)+20][1] = posBords [i][1]+21*h3/25;
			posPointsAnalyses [(12*i)+21][0] = posBords [i][0]+22*l3/25;
			posPointsAnalyses [(12*i)+21][1] = posBords [i][1]+22*h3/25;
			posPointsAnalyses [(12*i)+22][0] = posBords [i][0]+23*l3/25;
			posPointsAnalyses [(12*i)+22][1] = posBords [i][1]+23*h3/25;
			posPointsAnalyses [(12*i)+23][0] = posBords [i][0]+24*l3/25;
			posPointsAnalyses [(12*i)+23][1] = posBords [i][1]+24*h3/25;
		}
		return posPointsAnalyses;
	}
	
	public static int [][] generateColorData (int [][] tab){
		
        nbPointsValides=1;
        int [][] res = new int [384][4]; //tableau contenant les coordonnées red , green , blue , et hue des 384 points
        float [] hsv = new float [3]; //tableau contenant les trois coordonnées hue , saturation et value des points
        
        //on récupère les couleurs des points
        for (int i=0 ; i<384 ; i++){
				
			int clr=  imageBuff.getRGB(tab[i][0],tab[i][1]); 
			int  red   = (clr & 0x00ff0000) >> 16;
			int  green = (clr & 0x0000ff00) >> 8;
			int  blue  =  clr & 0x000000ff;
			res [i][0]= red;
			res [i][1] = green;
			res [i][2] = blue;
			Color.RGBtoHSB(red,green,blue,hsv);//on convertit les coordonnées rgb en hsv
			
			//Etant donné que les coordonnées hsv entrainent des confusions entre couleurs pour le blanc et le noir, on instaure certaines conditions
			if ((hsv[1]*100) <= 5 && hsv[2]*100 >= 95){
				res[i][3] = 500; //c'est la valeur qu'on donne au blanc
				nbPointsValides++;
			}
			else if ((hsv[2]*100) <= 5){
				res[i][3] = 600;//c'est la valeur qu'on donne au noir
				nbPointsValides++;
			}
			else if ((hsv[1]*100) >= 35 && hsv[2]*100 >= 35){
				res[i][3] = (int)(hsv[0]*360); //c'est la valeur d'une couleur que l'on consière "analysable" (saturation et luminosité assez élevées)
				nbPointsValides++;
			}
			else if(1==1){
				res[i][3] = 700; //Valeur qu'on donne à une couleur "pas assez parlante" et qui pourrait entrainer une confusion. On prendra pas en compte cette couleur dans notre analyse du tableau.
			}
						
		}
		return res;	
	}
}


