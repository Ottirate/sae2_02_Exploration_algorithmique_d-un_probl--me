/** Auteur : Equipe 1
  * Date   : juin 2023
*/

/*Paquetage*/
package graphe.metier;

/*Importations*/
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.Line2D;

public class Graphe
{
	/*Attributs*/
	private final String            FICHIER = "./Graphe.data";
	private final ArrayList<Color>  COLORS  = new ArrayList<>(Arrays.asList(
		Color.RED,
		Color.BLUE
	));

	private ArrayList<Point>  lstPoint;
	private ArrayList<Arete>  lstArete;
	private ArrayList<Region> lstRegion;

	private int               nbColorier;
	private int               cptCouleur;
	private int               couleurMax;

	/*Constructeur*/
	public Graphe()
	{
		// initialisation des données
		this.initialiser();
		this.initialiserTour();

		// Mélanger les couleurs
		

		// Test
	}

	/*Méthodes - Initialisations*/
	private void initialiser()
	{
		// Création des listes
		this.lstPoint  = new ArrayList<>();
		this.lstArete  = new ArrayList<>();
		this.lstRegion = new ArrayList<>();


		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( FICHIER ), StandardCharsets.UTF_8);

			sc.nextLine(); // Saut de ligne

			String[] ensVal = sc.nextLine().split("\t");

			// Points
			Point p;
			while (ensVal.length == 3)
			{
				int id = Integer.parseInt(ensVal[0]);
				int x  = Integer.parseInt(ensVal[1]);
				int y  = Integer.parseInt(ensVal[2]);

				p = new Point(id, x, y);

				this.lstPoint.add( p );

				ensVal = sc.nextLine().split("\t");
			}

			sc.nextLine(); // Saut de ligne
			ensVal = sc.nextLine().split("\t");

			// Régions
			Region region;
			while (ensVal.length > 1)
			{
				region = new Region(ensVal[0]);
				this.lstRegion.add(region);
				
				for (int i = 1 ; i < ensVal.length ; i++)
				{
					region.ajouterSommet(this.getPoint(Integer.parseInt(ensVal[i])));
				}

				ensVal = sc.nextLine().split("\t");
			}

			sc.nextLine(); // Saut de ligne
			ensVal = sc.nextLine().split("\t");

			// Arêtes
			
			while (ensVal.length > 1)
			{
				for (int i = 0; i < ensVal.length - 1; i ++)
				{
					Point p1, p2;

					p1 = this.lstPoint.get(Integer.parseInt(ensVal[i]    ) - 1);
					p2 = this.lstPoint.get(Integer.parseInt(ensVal[i + 1]) - 1);

					this.lstArete.add( new Arete (p2, p1, 0) );
				}
			
				ensVal = sc.nextLine().split("\t");
			}

			sc.close();
		}
		catch (Exception e)
		{
			System.out.println("Il semblerait qu'il y ait un problème de fichier");
		}
	}

	// Jeux
	public void initialiserTour()
	{
		this.couleurMax      = (int) ( Math.random() * 5 ) + 5;
		this.nbColorier      = 0;
		this.cptCouleur      = 0;
	}


	/*Accesseurs*/
	public Point getPoint(int id)
	{
		for (Point p : this.lstPoint)
		{
			if (p.getId() == id) return p;
		}

		return null;
	}

	public Color getColor() { return this.cptCouleur == COLORS.size() ? Color.WHITE : this.COLORS.get(this.cptCouleur);}

	public Color getColor(int id) { return this.lstArete.get(id).getCouleur();
	}

	public ArrayList<Point>  getPoints()  { return this.lstPoint ; }
	public ArrayList<Arete>  getAretes()  { return this.lstArete ; }
	public ArrayList<Region> getRegions() { return this.lstRegion; }

	public Arete getArete(String id)
	{
		for (Arete a : this.lstArete)
			if (a.toString().equals(id))
				return a;
		return null;
	}

	public int getId(Arete a)
	{
		return this.lstArete.indexOf(a);
	}

	public Arete trouverArete(Point a, Point b)
	{
		for (Arete areteA : a.getAretesAdjacentes())
			for (Arete areteB : b.getAretesAdjacentes())
				if (areteA.equals(areteB)) return areteA;

		return null;
	}

	/*Méthodes*/

	public boolean colorier(int id)
	{
		if ( this.cptCouleur == COLORS.size()   ) return false;
		//if (!this.estColoriable(id)) return false;

		Arete a = this.lstArete.get(id);
		Color couleur = COLORS.get( this.cptCouleur ); //Et une couleur
		a.setCouleur( couleur );

		this.nbColorier++;

		if ( this.nbColorier >= this.couleurMax ) { this.cptCouleur++; this.couleurMax += (int) ( Math.random() * 5 ) + 5; }

		return true;
	}

	public boolean estColoriable (int id)
	{
		if ( this.cptCouleur == COLORS.size()   ) return false;
		if (id < 0 || id > this.lstArete.size() ) return false;

		// On récupérer l'arete
		Arete a = this.lstArete.get(id);
		Color couleur = COLORS.get( this.cptCouleur ); //Et une couleur

		// Si le trait est déja pris
		if (a.getCouleur() != null) return false;

		// Si c'est le premier trait
		if (nbColorier == 0) return true;

		// Si l'arrete croise une arret déja colorée
		if (this.arreteCroise(a)) return false;

		// Cycle
		if (this.aCycle(a)) return false;

		// Si il a une arete autour d'elle coloriée
		for (Arete arete : a.getPointDepart().getAretesAdjacentes())
			if (arete.getCouleur() != null) return true;
		

		for (Arete arete : a.getPointArrivee().getAretesAdjacentes())
			if (arete.getCouleur() != null) return true;

		return false;
	}


	private boolean aCycle(Arete a1)
	{
		Color c = COLORS.get( this.cptCouleur );
	
		for (Arete a : a1.getPointDepart().getAretesAdjacentes())
			if (c.equals(a.getCouleur()))
				for (Arete a2 : a1.getPointArrivee().getAretesAdjacentes())
					if (c.equals(a2.getCouleur())) return true;

		return false;
	}


	private boolean arreteCroise (Arete a1)
	{
		int x1 = a1.getPointArrivee().getX();
		int y1 = a1.getPointArrivee().getY();
		int x2 = a1.getPointDepart ().getX();
		int y2 = a1.getPointDepart ().getY();

		// boolean aPointIdentique = false;

		for (Arete a2 : this.lstArete )
		{
			if (a2.getCouleur() != null && !a1.pointIdentique(a2))
			{

				int x3 = a2.getPointArrivee().getX();
				int y3 = a2.getPointArrivee().getY();
				int x4 = a2.getPointDepart ().getX();
				int y4 = a2.getPointDepart ().getY();

				if ( Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4) ) return true;
			}
		}

		return false;
	}

	public Point trouverPoint(double x, double y)
	{
		// Diamètre : 10px

		//double distance = Math.sqrt(  );
		Double distance = null;
		Point  point    = null;

		for (Point p : this.lstPoint)
		{
			distance = Math.pow((x - p.getX()) / (double) 5.0, 2) + Math.pow((y - p.getY()) / (double) 5.0, 2);

			if (distance <= 1.0) return p;
		}

		return point;
	}
	
	

}