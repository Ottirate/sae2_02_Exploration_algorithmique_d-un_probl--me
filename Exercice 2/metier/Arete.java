package graphe.metier;

import java.awt.Color;

public class Arete
{
	private Point   depart;
	private Point   arrivee;
	private int     cout;
	private Color   couleur;

	public Arete(Point depart, Point arrivee, int cout)
	{
		this.depart  = depart;
		this.arrivee = arrivee;
		this.cout    = cout;

		this.depart .ajouterArete(this);
		this.arrivee.ajouterArete(this);
	}

	public boolean setCouleur(Color c)
	{
		if (c == null) return false;

		this.couleur = c;
		return true;
	}

	public Point getPointDepart () { return this.depart ; }
	public Point getPointArrivee() { return this.arrivee; }
	public int   getCout        () { return this.cout   ; }
	public Color getCouleur     () { return this.couleur; }

	public String toString()
	{
		return String.format("%s:%s", this.depart.getId(), this.arrivee.getId());
	}
}
