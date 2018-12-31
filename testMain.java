import java.awt.*;

public class testMain {

public static void main (String[] args) {
	   
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
		//on instancie la première fenetre de notre application
		Fenetre FenetInt= new Fenetre(width+2,height-40, "fonds/fond1.jpeg", 1 , null );
    }
}   
