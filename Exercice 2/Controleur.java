package graphe;

import java.util.ArrayList;
import graphe.ihm.FrameGraphe;
import graphe.metier.Graphe;
import graphe.metier.Point;
import graphe.metier.Arete;

import java.awt.Dimension;
import java.awt.Color;

import javax.swing.SwingUtilities;

public class Controleur 
{
	private FrameGraphe ihm;
	private Graphe      metier;

	private Dimension tailleEcran;

	public Controleur()
	{
		this.tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.metier = new Graphe();
		this.ihm    = new FrameGraphe(this);
	}

	public ArrayList<Point> getSommets() { return this.metier.getPoints();} 
	public ArrayList<Arete> getAretes () { return this.metier.getAretes();}

	public Color   getColor     ( int id )           { return this.metier.getColor( id );     }
	public boolean colorier     ( int id )           { return this.metier.colorier( id );     }
	public Arete   trouverArete (Point x, Point y)   { return this.metier.trouverArete(x, y); }
	public Point   trouverPoint(double x, double y)  { return this.metier.trouverPoint(x, y); }
	public int     getId        (Arete a)            { return this.metier.getId (a);          }

	public void majIhm () { this.ihm.maj() ;}

	public int getHauteurIHM() { return this.ihm.getHeight(); }
	public int getLargeurIHM() { return this.ihm.getWidth (); }

	public Dimension getTailleEcran(){ return this.tailleEcran; }
	public int getCoef() { return this.ihm.getCoef() ; }



	public static void main( String[] args )
	{
		SwingUtilities.invokeLater(Controleur::new);
	}
}
