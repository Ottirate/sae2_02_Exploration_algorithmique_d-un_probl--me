/*
 * Auteur : Équipe 1
 * Date   : juin 2023
 * */


/*      Paquetage      */
package graphe.metier;


/*       Imports       */
import java.util.ArrayList;


public class Region
{
	/* Attributs de Classe */
	private static int nbCoul;


	/*      Attributs      */
	private String           nom;
	private ArrayList<Point> lstSommet;
	private Couleur          coul;


	/*    Constructeur     */
	public Region (String nom)
	{
		this.nom       = nom;
		this.lstSommet = new ArrayList<>();
		this.coul      = Couleur.values()[Region.nbCoul++];
	}


	/*     Accesseurs      */
	public ArrayList<Point> getPoints ()        { return this.lstSommet ; }
	public Couleur          getCouleur()        { return this.coul      ; }


	/*      Méthodes       */
	public void ajouterSommet(Point p)
	{
		if (this.lstSommet.contains(p) || p == null) return;
		if (!p.setRegion(this)) return;

		this.lstSommet.add(p);
	}


	public String toString ()
	{
		StringBuilder sRep = new StringBuilder(this.nom);
		
		int nbNoeud = this.lstSommet.size();

		if (nbNoeud > 0)
		{
			sRep.append("(");

			for (Point point : this.lstSommet) sRep.append(" ").append(point.getId()).append(" ");

			sRep.append(")");
		}

		return sRep.toString();
	}
}