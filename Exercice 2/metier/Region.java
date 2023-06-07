/** Auteur : Equipe 1
  * Date   : juin 2023
*/

package graphe.metier;

import java.util.ArrayList;

public class Region
{
	/* ATTRIBUTS DE CLASSE */
	private static int nbCoul;

	/* ATTRIBUTS */
	private String           nom;
	private ArrayList<Point> lstSommet;
	private Couleur          coul;

	/* CONSTRUCTEUR */
	public Region (String nom)
	{
		this.nom       = nom;
		this.lstSommet = new ArrayList<Point>();
		this.coul      = Couleur.values()[Region.nbCoul++];
	}

	/* SOMMETS */
	public boolean ajouterSommet(Point p)
	{
		if (this.lstSommet.contains(p) || p == null) 
			return false;

		if (!p.setRegion(this)) return false;

		this.lstSommet.add(p);
		return true;
	}

	/* ACCESSEURS */
	public Point            getPoint  (int ind) { return this.lstSommet.get(ind); }
	public ArrayList<Point> getPoints ()        { return this.lstSommet         ; }
	public Couleur          getCouleur()        { return this.coul              ; }

	/* TO STRING */
	public String toString ()
	{
		String sRep = this.nom;
		
		int nbNoeud = this.lstSommet.size();

		if (nbNoeud > 0)
		{
			sRep += "(";
			for (int i = 0; i < nbNoeud; i++)
				sRep += " " + this.lstSommet.get(i).getId() + " ";
			sRep += ")";
		}

		return sRep;
	}

}