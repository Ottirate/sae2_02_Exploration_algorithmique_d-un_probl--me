/*
 * Auteur : Équipe 1
 * Date   : juin 2023
 * */


/*      Paquetage      */
package graphe.metier;


/*       Imports       */
import java.awt.Color;


public class Arete
{
	/*      Attributs      */
	private Point   depart;
	private Point   arrivee;
	private int     cout;
	private Color   couleur;


	/*    Constructeur     */
	public Arete(Point depart, Point arrivee)
	{
		this.depart  = depart;
		this.arrivee = arrivee;
		this.cout    = 0;

		this.depart .ajouterArete(this);
		this.arrivee.ajouterArete(this);
	}


	/*     Accesseurs      */
	public Point getPointDepart () { return this.depart   ; }
	public Point getPointArrivee() { return this.arrivee  ; }
	public int   getCout        () { return this.cout     ; }
	public Color getCouleur     () { return this.couleur  ; }


	/*     Modifieurs      */
	public void setCout  ( int c ) {        this.cout = c ; }

	public void setCouleur(Color c)
	{
		if ( c != null ) this.couleur = c;
	}


	/*      Méthodes       */
	public boolean pointIdentique (Arete a)
	{
		return a.getPointArrivee() == this.getPointArrivee() ||
		       a.getPointArrivee() == this.getPointDepart () ||
		       a.getPointDepart()  == this.getPointArrivee() ||
		       a.getPointDepart()  == this.getPointDepart () ;
	}


	public String toString()
	{
		return String.format("%s:%s", this.arrivee.getId(), this.depart.getId());
	}
}
