/*
 * Auteur : Équipe 1
 * Date   : juin 2023
 * */


/*      Paquetage      */
package graphe.metier;


/*       Imports       */
import java.util.*;
import graphe.metier.Region;

import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.awt.Color;
import java.awt.geom.Line2D;


public class Graphe 
{
	/* Attributs de Classe */
	/*      Constants      */
	private final static int NB_TOUR_MIN = 5;


	/* Attributs Constants */
	private static final String FICHIER = "../Graphe.data";

	private static final ArrayList<Color> COLORS = new ArrayList<>(Arrays.asList(
		Color.RED,
		Color.BLUE
	));


	/* Attributs */
	private ArrayList<Point> lstPoint;
	private ArrayList<Arete> lstArete;
	private ArrayList<Arete> lstAreteColore;
	private ArrayList<Region> lstRegion;

	private int nbColorier;
	private int cptCouleur;
	private int couleurMax;


	/* Constructeur */
	public Graphe()
	{
		// initialisation de la partie
		this.initialiser();
	}


	/* Accesseurs */
	public Point getPoint(int id)
	{
		for (Point p : this.lstPoint)
			if (p.getId() == id)
				return p;

		return null;
	}


	public Color getColor()
	{
		return this.cptCouleur == COLORS.size() ? Color.LIGHT_GRAY : this.COLORS.get(this.cptCouleur);
	}


	public ArrayList<Point> getPoints()
	{
		return this.lstPoint;
	}


	public ArrayList<Arete> getAretes()
	{
		return this.lstArete;
	}


	public ArrayList<Region> getRegions()
	{
		return this.lstRegion;
	}


	public Arete getArete(String id)
	{
		for (Arete a : this.lstArete)
			if (a.toString().equals(id))
				return a;

		return null;
	}


	public Arete trouverArete(Point a, Point b)
	{
		for (Arete areteA : a.getAretesAdjacentes())
			for (Arete areteB : b.getAretesAdjacentes())
				if (areteA.equals(areteB))
					return areteA;

		return null;
	}


	public Point trouverPoint(double x, double y)
	{
		double distance;

		for (Point p : this.lstPoint) {
			distance = Math.pow((x - p.getX()) / 5.0, 2) + Math.pow((y - p.getY()) / 5.0, 2);

			if (distance <= 1.0)
				return p;
		}

		return null;
	}


	/* Méthodes */
	public void initialiser()
	{
		// Création des listes
		this.lstPoint = new ArrayList<>();
		this.lstArete = new ArrayList<>();
		this.lstRegion = new ArrayList<>();

		// Reset le nombre de region à 0 pour les coul
		Region.reset();

		// Lecture des données
		try {
			Scanner sc = new Scanner(new FileInputStream( Graphe.FICHIER ), StandardCharsets.UTF_8);
			System.out.println("Pas prob fic");

			sc.nextLine(); // Saut de ligne

			String[] ensVal = sc.nextLine().split("\t");


			// Points
			Point p;
			while (ensVal.length == 3) {
				int id = Integer.parseInt(ensVal[0]);
				int x = Integer.parseInt(ensVal[1]);
				int y = Integer.parseInt(ensVal[2]);

				p = new Point(id, x, y);

				this.lstPoint.add(p);

				ensVal = sc.nextLine().split("\t");
			}

			sc.nextLine(); // Saut de ligne
			ensVal = sc.nextLine().split("\t");

			System.out.println("Pas prob Point");


			// Régions
			Region region;
			while (ensVal.length > 1) {
				region = new Region(ensVal[0]);
				this.lstRegion.add(region);

				for (int i = 1; i < ensVal.length; i++) {
					region.ajouterSommet(this.getPoint(Integer.parseInt(ensVal[i])));
				}

				ensVal = sc.nextLine().split("\t");
			}

			sc.nextLine(); // Saut de ligne
			ensVal = sc.nextLine().split("\t");
			System.out.println("Pas prob Reg");


			// Arêtes
			while (ensVal.length > 1) {
				for (int i = 0; i < ensVal.length - 1; i++) {
					Point p1, p2;

					p1 = this.lstPoint.get(Integer.parseInt(ensVal[i]) - 1);
					p2 = this.lstPoint.get(Integer.parseInt(ensVal[i + 1]) - 1);

					this.lstArete.add(new Arete(p2, p1));
				}

				ensVal = sc.nextLine().split("\t");
			}
			
			sc.nextLine(); // Saut de ligne
			ensVal = sc.nextLine().split("\t");
			System.out.println("Pas prob arete");


			// Bonus
			Point p1, p2;
			while (sc.hasNextLine())
			{
				p1 = this.getPoint(Integer.parseInt(ensVal[0]));
				p2 = this.getPoint(Integer.parseInt(ensVal[1]));
				this.trouverArete(p1, p2).setCout(Integer.parseInt(ensVal[2]));

				ensVal = sc.nextLine().split("\t");
			}

			System.out.println(this.trouverArete(this.getPoint(30), this.getPoint(33)).getCout());

			sc.close();
		} catch (Exception e) {
			System.out.println("Il semblerait qu'il y ait un problème de fichier");
		}


		// initialialisation des valeurs
		this.couleurMax = (int) (Math.random() * 5) + Graphe.NB_TOUR_MIN;
		this.nbColorier = 0;
		this.cptCouleur = 0;
		this.lstAreteColore = new ArrayList<>();
	}


	public void colorier(Arete a) 
	{
		if (this.cptCouleur == COLORS.size())
			return;
		if (a == null)
			return;

		// Récupération de la couleur actuelle
		a.setCouleur(COLORS.get(this.cptCouleur));

		this.nbColorier++;
		this.lstAreteColore.add(a);

		if (this.nbColorier >= this.couleurMax) 
		{
			this.cptCouleur++;
			this.couleurMax += (int) (Math.random() * 5) + Graphe.NB_TOUR_MIN;
		}
	}


	public int nbPoint() 
	{
		int nbPointRegion, nbPointRegionMax;
		int nbRegionVue = 0;

		int nbPoints = 0;
		
		Point p;
		ArrayList<Point> lstPointVue = new ArrayList<Point>();

		//On regarde le nombre max de ville visité par région :

		//Pour chaque region
		nbPointRegionMax = 0;
		for (Region r : this.lstRegion)
		{
			lstPointVue = new ArrayList<Point>();

			//On regarde les aretes
			for (Arete a : this.lstAreteColore)
			{
				p = a.getPointArrivee();
				if (p.getRegion() == r && !lstPointVue.contains(p))
					lstPointVue.add(p);

				p = a.getPointDepart();
				if (p.getRegion() == r && !lstPointVue.contains(p))
					lstPointVue.add(p);
			}

			nbPointRegion = lstPointVue.size();

			if (nbPointRegion > 0) nbRegionVue++;

			//Si il y a plus de 
			if (nbPointRegion > nbPointRegionMax) nbPointRegionMax = nbPointRegion;
		}


		//Calcul du bonus 
		for (Arete a : this.lstAreteColore)
			nbPoints += a.getCout();
			

		return nbPointRegionMax * nbRegionVue + nbPoints;
	}


	public boolean estColoriable(Arete a) 
	{
		if (this.cptCouleur == COLORS.size())
			return false;
		if (a == null)
			return false;

		// Si l'arête est deja prise
		if (a.getCouleur() != null)
			return false;

		// Si c'est le premier trait
		if (this.nbColorier == 0)
			return true;

		// Si l'arête croise une arête deja colorée
		if (this.areteCroise(a))
			return false;

		// Cycle
		if ( this.aCycle( a.getPointDepart(), a.getPointArrivee(), a.getPointArrivee(), COLORS.get( this.cptCouleur ) ) )
			return false;

		// S'il a une arête autour d'elle coloriée
		for (Arete arete : a.getPointDepart().getAretesAdjacentes())
			if (arete.getCouleur() != null)
				return true;

		for (Arete arete : a.getPointArrivee().getAretesAdjacentes())
			if (arete.getCouleur() != null)
				return true;

		return false;
	}


	private boolean aCycle(Point p1, Point p1dAvant, Point p2, Color c)
	{
		for (Arete a : p1.getAretesAdjacentes())
		{
			Point p = a.getPointArrivee() == p1 ? a.getPointDepart() : a.getPointArrivee();

			if ( c.equals(a.getCouleur()) && p != p1dAvant )
			{
				if ( p != p2 )
				{
					//return this.checkSuite(p, p1, p2, c);
					if (this.aCycle(p, p1, p2, c)) return true;
				}
				else
				{
					return true;
				}
			}
		}

		return false;
	}


	private boolean areteCroise(Arete a1) 
	{
		int x1 = a1.getPointArrivee().getX();
		int y1 = a1.getPointArrivee().getY();
		int x2 = a1.getPointDepart ().getX();
		int y2 = a1.getPointDepart ().getY();

		for (Arete a2 : this.lstArete) 
		{
			if (a2.getCouleur() != null && !a1.pointIdentique(a2)) 
			{
				int x3 = a2.getPointArrivee().getX();
				int y3 = a2.getPointArrivee().getY();
				int x4 = a2.getPointDepart ().getX();
				int y4 = a2.getPointDepart ().getY();

				if (Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4))
					return true;
			}

		}

		return false;
	}
}