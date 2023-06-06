package graphe.metier;

import java.util.*;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.awt.Color;

public class Graphe
{
	private final String            FICHIER = "./Graphe.data";
	private final ArrayList<Color>  COLORS  = new ArrayList<>(Arrays.asList(
		Color.RED,
		Color.ORANGE,
		Color.YELLOW,
		Color.GREEN,
		new Color(0,255,0), // Lime
		Color.BLUE,
		Color.PINK,
		Color.MAGENTA
	));

	private ArrayList<Point>  lstPoint;
	private ArrayList<Arete>  lstArete;
	private ArrayList<Region> lstRegion;

	private int               nbColorier;
	private int               cptCouleur;
	private int               couleurMax;


	public Graphe()
	{
		// initialisation des données
		this.initialiser();
		this.initialiserTour();

		// Mélanger les couleurs
		

		// Test
	}

	private void initialiser()
	{
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

			// Arêtes
			
			while (sc.hasNextLine())
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

	public void initialiserTour()
	{
		this.couleurMax      = (int) ( Math.random() * 5 ) + 50;
		this.nbColorier      = 0;
		this.cptCouleur      = 0;
	}

	public Point getPoint(int id)
	{
		for (Point p : this.lstPoint)
		{
			if (p.getId() == id) return p;
		}

		return null;
	}

	public Color getColor(int id)
	{
		return this.lstArete.get(id).getCouleur();
	}

	public boolean colorier(int id)
	{
		if (id <= -1) return false;

		Arete a = this.lstArete.get(id);
		Color couleur = COLORS.get( this.cptCouleur );

		if (a.getCouleur() != null) return false;

		for (Arete arete : a.getPointDepart().getAretesAdjacentes())
			if (couleur.equals( arete.getCouleur() )) return false;

		for (Arete arete : a.getPointArrivee().getAretesAdjacentes())
			if (couleur.equals( arete.getCouleur() )) return false;

		a.setCouleur( couleur );

		this.nbColorier++;

		if ( this.nbColorier >= this.couleurMax ) this.cptCouleur++;
		if ( this.cptCouleur == COLORS.size()   ) this.cptCouleur = 0;

		return true;
	}

	public ArrayList<Point> getPoints() {return this.lstPoint;}
	public ArrayList<Arete> getAretes() {return this.lstArete;}

	public int              getId(Arete a)
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

	public Point trouverPoint(double x, double y)
	{
		// Diamètre : 10px

		//double distance = Math.sqrt(  );
		Double distance = null;
		Point  point    = null;

		for (Point p : this.lstPoint)
		{
			/*Double d = Math.sqrt( (p.getX() - x) * (p.getX() - x) + (p.getY() - y) * (p.getY() - y) );

			if (distance == null)
			{
				distance = d;
				point = p;
			}

			if ( ( point == null || distance >= d ) && distance <= 5 )
			{
				point    = p;
				distance = d;
			}*/

			distance = Math.pow((x - p.getX()) / (double) 5.0, 2) + Math.pow((y - p.getY()) / (double) 5.0, 2);

			if (distance <= 1.0) return p;
		}

		return point;
	}
}