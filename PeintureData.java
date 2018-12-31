import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;



public class PeintureData{
	
	//Paramètres de la peinture data
	String titre; //titre de la peinture
	String auteur; //auteur de la peinture
	String nomFichier; //nom du fichier correspondant à l'image de la peinture
	String description; //description de la peinture
	int [][] hValues; //la valeur "teinte" comprise entre 0 et 360 des pixels analysés
	boolean paysage = true; ////Caractérisation du format de la peinture (image ou portrait)
	
	//Elements BufferedImage
	static BufferedImage imageBuff;
	
	public PeintureData (String titre , String auteur , String description ,String nomFichier ) {
		
		this.titre = titre;
		this.auteur=auteur;
		this.description=description;
		this.nomFichier = nomFichier;
		File file= new File(nomFichier); // on récupère le fichier image
		
		//on convertit l'image en BufferedImage
        try {
				imageBuff = ImageIO.read(file);
			} 
			catch (IOException ae) {
              // 
			}
			
		//on dit si l'image à reconnaitre est en format paysage ou en format portrait
        if ((imageBuff.getWidth(null)) < (imageBuff.getHeight(null))){
			paysage = false;
		}
        
		//On donne une valeur de teinte à chacun des 384*400 points analysés 
		hValues = generateColorData();
	}
	
	
	public static int [][] generateColorData (){
		
		int pasY = (imageBuff.getHeight())/17; //on définit la distance suivant les y entre deux points
        int pasX = (imageBuff.getWidth())/25;  //on définit la distance suivant les x entre deux points
   
        int [][] tab = new int [384][400];
        int cmpt =0;
        int cmpt1 =0;
        float [] hsv = new float [3];
        
        //on définit les valeurs de "hue" des 384 * 400 points de l'image (384 carrés de 400 points)
        for (int i=1 ; i<=16 ; i++){
			for (int j=1 ; j<=24 ; j++){
				for (int k=-10; k<10 ; k++){
					for (int l=-10; l<10 ; l++){
						int clr=  imageBuff.getRGB((pasX*j)+l,(pasY*i)+k); 
						int  red   = (clr & 0x00ff0000) >> 16;
						int  green = (clr & 0x0000ff00) >> 8;
						int  blue  =  clr & 0x000000ff;
						Color.RGBtoHSB(red,green,blue,hsv); //on convertit les coordonnées rgb en hsv
						
						//Etant donné que les coordonnées hsv entrainent des confusions entre couleurs pour le blanc et le noir, on instaure certaines conditions
						if ((hsv[1]*100) <= 5 && hsv[2]*100 >= 95){
							tab [cmpt][cmpt1] = 500;//c'est la valeur qu'on donne au blanc
						}
						else if ((hsv[2]*100) <= 5){
							tab [cmpt][cmpt1] = 600;//c'est la valeur qu'on donne au noir
						}
						else if ((hsv[1]*100) >= 10 && hsv[2]*100 >= 30){
							tab [cmpt][cmpt1] = (int)(hsv[0]*360); //c'est la valeur d'une couleur que l'on consière "analysable" (saturation et luminosité assez élevées)
						}
						else if (1==1){
							tab [cmpt][cmpt1] = 800; //Valeur qu'on donne à une couleur "pas assez parlante" et qui pourrait entrainer une confusion. On prendra pas en compte cette couleur dans notre analyse du tableau.
						}
						cmpt1++;
					}
				}
				cmpt1=0;
				cmpt++;
			}
		}
		return tab;	
  }

	public int compareTo (PeintureAnalysee peinture){
		int cmpt1 = 0; //nombre de points correspondants entre la peinture à analyser et une peinture data

		//on crée une boucle de comparaion pour comparer chacun des 384 points de la peinture à analyser aux carrés de points de la peinture data.
		for (int i = 0 ; i<hValues.length ; i++){
			if (peinture.hValues[i] != 700){ //ça ne sert à rien de comparer une couleur non exploitable
				for (int j = 0 ; j<hValues[0].length ; j++){
					if (((this.hValues[i][j])-20) <= peinture.hValues[i] && peinture.hValues[i] <= ((this.hValues[i][j]))+20){ //la valeur du hue doit correspondre à + ou - 20 sur une échelle de 360
						cmpt1++; //si un point du carré à quasiment la même couleur que le pixel de la peinture analysée, on ajoute 1 au nombre de correspondances
						break; //on sort du carré
					}
				}
			}
		}
		System.out.println(this.titre +":  "+(cmpt1*100)/(peinture.nbPointsValides));//on affiche le pourcentage de points correspondants
		return cmpt1; 
	}	

}
