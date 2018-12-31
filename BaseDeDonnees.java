import java.util.*;
import java.io.*;

public class BaseDeDonnees {
	
	List<PeintureData> l ;
	
	public BaseDeDonnees (){
		//On instancie notre liste de peintures de référence
		l = new ArrayList<PeintureData> ();
		l.add (new PeintureData ("LA CHAMBRE BLEUE", "PICASSO", "Cette peinture (huile sur toile), datant de 1901,  est une des premi\u00e8res peintures de la ''p\u00e9riode bleue'' de Picasso. \n Les teintes de bleu expriment la m\u00e9lancolie et la mort. \n En 2014, les scientifiques ont d\u00e9couvert sous rayons X une peinture cach\u00e9e derri\u00e8re  La chambre Bleue : le visage d\u0027un barbu... Affaire \u00e0 suivre...","BDImages/laChambreBleue.jpg"));
		l.add (new PeintureData ("LA JOCONDE", "LEONARD DE VINCI", "Cette peinture, datant du XVIe si\u00e8cle, est le portrait le plus c\u00e9l\u00e8bre au monde. \n L\u0027identit\u00e9 du mod\u00e8le est r\u00e9guli\u00e8rement remise en question, mais on admet g\u00e9n\u00e9ralement qu\u0027il s\u0027agit d\u0027une dame florentine, pr\u00e9nomm\u00e9e Lisa, \u00e9pouse de Francesco del Giocondo. \n Le nom Giocondo a \u00e9t\u00e9 tr\u00e9s tot francis\u00e9 en Joconde, mais le tableau est aussi connu sous le titre de Portrait de Monna Lisa, monna signifiant dame ou madame en italien ancien. \n Avec son regard p\u00e9n\u00e9trant et son l\u00e9ger sourire, Monna Lisa semble d\u00e9fier le spectateur et s\u0027en amuser. \n L\u00e9onard a su capter une expression fugace pass\u00e9e sur le visage de la jeune femme. \n Il repr\u00e9sente avec pr\u00e9cision les muscles de son visage et tous leurs mouvements, notamment aux contours des yeux et aux commissures des l\u00e8vres. \n Son habilet\u00e9 r\u00e9side surtout dans la mani\u00e8re dont il travaille le volume des carnations, en estompant de mani\u00e8re tr\u00e9s subtile les passages de l\u0027ombre  la lumi\u00e8re \n Il invente ainsi un nouvel effet, le sfumato, qui lui permet de mieux inscrire la figure dans l\u0027espace. \n C\u0027est principalement grace  cet effet, caract\u00e9ristique de la peinture de L\u00e9onard, que la Joconde apparait si pr\u00e9sente au spectateur." ,"BDImages/joconde.jpg")) ;
		l.add (new PeintureData ("LE LIT VOLANT", "FRIDA KAHLO", "Peinte en 1932, cette peinture traduit la souffrance de Frida Kahlo lorsqu\u0027elle apprend qu\u0027elle ne pourra jamais avoir d\u0027enfant, suite \u00e0 un accident de bus qui lui transperce le vagin. \n Elle peint Le Lit Volant la semaine suivant sa premi\u00e8re fausse-couche. Frida Kahlo s\u0027est repr\u00e9sent\u00e9e elle meme sur un lit d\u0027hopital qui semble flotter dans les airs, enti\u00e8rement nue, gisant dans des draps taches de sang. Elle est le seul personnage vivant du tableau et est peinte beaucoup plus petite que l\u0027espace disponible, c\u0027est pour elle une mani\u00e8re de se repr\u00e9senter seule dans sa douleur. Sur sa joue, on distingue une larme blanche : elle semble souffrir en silence, dignement. Derri\u00e8re elle s\u0027\u00e9tendent des usines, univers inhospitalier d\u0027Amerique, loin de son Mexique natal qui lui manque. \n \n Au centre, se trouve son foetus mort, qu\u0027elle appellera ''Dieguito'' (soit ''Petit Diego'' en espagnol, en r\u00e9f\u00e9rence  son mari Diego Rivera). \n \n A gauche, Frida Kahlo a peint une ''coupe'' de corps de femme. Elle dit que c\u0027\u00e9tait un moyen pour elle d\u0027expliquer comment marchait un accouchement, et en l\u0027occurrence, de montrer pourquoi cela n\u0027avait pas \u00e9t\u00e9 possible. \n  " ,"BDImages/leLitVolant.jpg")) ;
		l.add (new PeintureData ("LA NUIT ETOILEE", "VAN GOGH", "Cette peinture  l\u0027huile date de 1889. Elle s\u0027apparente au mouvement post impressionnisme. \n Vincent Van Gogh est interne \u00e0 l\u0027asile de Saint-Remy lorsqu\u0027il peint La Nuit \u00e9toilee.  \n Ses couleurs et son trait ont alors atteint le summum de leur puissance expressive.  \n On remarque l\u0027utilisation des couleurs compl\u00e9mentaires (Bleu Orange, Jaune Violet) qui renforce leur luminosit\u00e9 respective.  \n Ce jeu de l\u0027artiste avec l\u0027intensit\u00e9 lumineuse de l\u0027obscurit\u00e9 est une nouveaut\u00e9 totale \u00e0  l \u00e9poque.  \n  \n La Nuit etoil\u00e9e pr\u00e9sente un paysage  la fois idyllique et menacant. Mena\u00e7ant car des forces primitives semblent enserrer le village. Le ciel agite rappelle une mer dechain\u00e9e, des vagues pretes  se briser. Le vent violent froisse le cypres au premier plan. Les montagnes barrent l\u0027horizon.  \n  \n On comprend ais\u00e9ment que Vincent a fait le deuil de ses reves (paysage clos) et se sent menace par la folie qui d\u00e9forme la realit\u00e9 (comme au sein du tableau).  \n  \n La seule issue possible semble le ciel \u00e9toil\u00e9. ''Tout comme nous prenons le train pour nous rendre  Tarascon ou  Rouen, nous prenons la mort pour atteindre les \u00e9toiles'', \u00e9crit-il.  \n C\u0027est transparent: pour Vincent, le ciel \u00e9toil\u00e9e, c\u0027est la mort, seule issue possible face  la menace de la folie et la fin de tous ses espoirs.  \n   \n  Plusieurs \u00e9l\u00e9ments de la toile viennent renforcer ce sentiment. D\u0027abord, la pointe du clocher de l\u0027\u00e9glise s’\u00e9lance \u00e9xagerement vers le ciel. \n  Or, l\u0027\u00e9glise \u0027est-elle pas dans l\u0027esprit des chr\u00e9tiens le lien entre la terre et le ciel, le ''train'' reliant l\u0027Homme  Dieu pour reprendre le terme de Van Gogh? \n De meme, le cypres, arbre de cimeti\u00e8re, a, dans ce cadre, la meme fonction : de la terre ou ses racines cotoient le corps du d\u00e9funt, il s\u0027\u00e9lance vers le ciel qu\u0027 rejoint l\u0027ame.  \n Ainsi est-il un lien \u00e9vident, dans l\u0027imaginaire collectif, entre la terre et le ciel.  \n Rien d\u0027\u00e9tonnant alors  ce que la fleche de l\u0027\u00e9glise et le cypres soient les deux seuls \u00e9l\u00e9ments  relier le premier plan (le village ou vivent les Hommes) et l\u0027arri\u00e8re-plan, le ciel etoil\u00e9e (symbole de la mort).  \n   \n D\u0027une certaine mani\u00e8re, ce tableau annonce les projets fun\u00e8bres de Van Gogh qui finira par se suicider en 1890. " ,"BDImages/laNuitEtoilee.jpg")) ;
		l.add (new PeintureData ("LE SOMMEIL", "SALVADOR DALI", " Cette peinture  l\u0027huile sur un support de bois a \u00e9t\u00e9 r\u00e9alis\u00e9e en 1937.   \n  \n Dali s\u0027est inspire d\u0027un v\u00e9ritable rocher du sud de l\u0027Espagne appel\u00e9 ''le rocher dormant''. \n  Ce rocher, qui symbolise le sommeil, prend ici la forme d\u0027une lourde tete g\u00e9ante endormie et soutenue par de freles b\u00e9quilles.  \n Si ces b\u00e9quilles venaient \u00e0 se briser, la tete tomberait et le monstre se r\u00e9veillerait.  \n A droite, on peut apercevoir une ville estivale.  \n A gauche, un chien appui\u00e9 sur une b\u00e9quille ne dort que d un oeil comme s\u0027il surveillait le monstre m\u00e9lancolique endormi. \n  \n ''J\u0027ai souvent imagin\u00e9 et repr\u00e9sente le monstre du sommeil comme une lourde tete g\u00e9ante avec un corps filiforme soutenu en \u00e9quilibre par les b\u00e9quilles de la realit\u00e9. Lorsque ces b\u00e9quilles se brisent, nous avons la sensation de tomber. La plupart de mes lecteurs ont exp\u00e9rimente cette sensation de tomber brusquement dans le vide, juste  la minute o\u00f9 le sommeil va les gagner compl\u00e8tement. Reveill\u00e9s en sursaut, le coeur agit\u00e9 par un tremblement convulsif, vous ne vous doutez pas toujours que cette sensation est une r\u00e9miniscence de l\u0027expulsion de l\u0027accouchement. \n Ces paroles ne sont autres que celles de Salvador expliquant la signification de sa peinture." ,"BDImages/leSommeil.jpg")) ;
		l.add (new PeintureData ("LES BARQUES", "MONET CLAUDE", " Cette peinture  l\u0027huile de 1840 appartient au mouvement des impressionnistes.   \n   \n Tout le secret de cette toile est dans le reflet. \n  Ce tableau contient une v\u00e9ritable illumination par la simple lumi\u00e8re d\u0027 \u00e9t\u00e9.  \n D\u0027ou vient cette sensation: du double aspect du visible, image et reflet. Le reflet donne son statut de r\u00e9alite  l\u0027image, la boucle se referme sur la peinture qui r\u00e9sout la dualit\u00e9, le tableau peut envisager sans raconter d\u0027histoire, de restituer le r\u00e9el et la perception que nous en avons confondus, Monet fonde l\u0027une des bases de la peinture moderne, apr\u00e8s Manet.  \n  \n La raison de cette composition est donc dans le dialogue du r\u00e9el avec son reflet, au del\u00e0 de cette d\u00e9couverte c\u0027est le probl\u00e8me de la lumi\u00e8re pure qui commence mais \u00e7a Monet le savait depuis le d\u00e9but de sa peinture, et peut etre le savait-il enfant quand il regardait les choses.  \n  \n Une dominante de bleus ponctu\u00e9s de blanc (lumi\u00e8res), de verts et de rouges oranges. \n  L\u0027ivoire des voiles de ces barques sont l\u0027\u00e9blouissement de la lumi\u00e8re de ce tableau, leur mouvement lent et doux s\u00e9pare visuellement l\u0027eau et le ciel et les r\u00e9unit par l\u0027\u00e9clat de leur blancheur, voguer sur l\u0027eau unit ciel et eau, au d\u00e9but du XXe si\u00e8cle Monet embarquera sa peinture  pour une \u00e9tonnante exp\u00e9rience flottante, mais d\u00e9j\u00e0 le th\u00e8me est la:l\u0027eau et les reves.  \n  \n La lumi\u00e8re est ici \u00e9blouissement, le soleil devient extase, mais  la mani\u00e8re d\u0027une enfance retrouv\u00e9e, c\u0027est un bonheur sans ombre, un absolu de la lumi\u00e8re.  \n A droite du tableau une maison rouge qu\u0027on peut imaginer comme un de ces lieux de bord de Seine o\u00f9 on allait boire et manger, canoter et danser la nuit tomb\u00e9e.  " ,"BDImages/lesBarques.jpg")) ;
		
		//On rend notre base de données OpenSource
		File folder = new File("BDImagesOpenSource"); 
		File[] listOfFiles = folder.listFiles(); //on liste les fichiers images qui se trouvent dans notre répertoire BDImagesOpenSource

		//code permettant d'instancier des objets PeintureData à partir des images se trouvant dans le répertoire
		for (int i = 0; i < listOfFiles.length; i++) { //On parcourt notre répertoire 
			//Pour chaque fichier on récupère son nom, et l'image qui lui correspond
			String nomFichier =  listOfFiles[i].getName(); 
			System.out.println (nomFichier);
			String nomOeuvre = "";
			String nomPeintre = "";
			char tiretBas = '_';
			char tiretHaut = '-';
			char espace = ' ';
			char point = '.';
			int j = 0;
			//grâce au format de l'appellation des images imposé lors de l'ajout d'une oeuvre en Open Source, on crée des instances de Peinture Data
			while((int)(nomFichier.charAt(j)) != (int)tiretBas){
				if ((int)nomFichier.charAt(j) != (int)tiretHaut){
					nomOeuvre += nomFichier.charAt(j);
				}
				else {
					nomOeuvre += espace;
				}
				j++;
			}
			j++;
			while((int)(nomFichier.charAt(j)) != (int)point){
				if ((int)nomFichier.charAt(j) != (int)tiretHaut){
					nomPeintre += nomFichier.charAt(j);
				}
				else {
					nomPeintre += ' ';
				}
				j++;
			}
			
			l.add (new PeintureData (nomOeuvre, nomPeintre, "Cette oeuvre a \u00e9t\u00e9 ajout\u00e9e en OpenSource, il n\u0027y a pas de description la concernant.", "BDImagesOpenSource/"+nomFichier));
					
      }
    }
	//les différents accesseurs
	public PeintureData getPeinture (int index){
		return l.get(index);
	}
	
	public int getSize (){
		return l.size();
	}



}
