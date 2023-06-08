/*
 * Auteur : Équipe 1
 * Date   : juin 2023
 * */


/*      Paquetage      */
package graphe.metier;


/*       Imports       */
import java.util.ArrayList;
import java.util.List;


public class Point
{
	/*      Attributs      */
	private int              id;
	private int              x;
	private int              y;
	private Region           region;
	private ArrayList<Arete> ensArete;


	/*    Constructeur     */
	public Point(int id, int x, int y)
	{
		this.id       = id;
		this.x        = x;
		this.y        = y;
		this.ensArete = new ArrayList<>();
	}


	/*     Accesseurs      */
	public int getId() { return this.id ; }
	public int getX () { return this.x  ; }
	public int getY () { return this.y  ; }

	public List<Arete> getAretesAdjacentes() { return this.ensArete; }


	/*     Modifieurs      */
	public boolean setRegion(Region reg)
	{
		if ( this.region != null || reg == null ) return false;

		this.region = reg;

		return true;
	}


	/*      Méthodes       */
	public void ajouterArete(Arete a)
	{
		if ( !this.ensArete.contains(a) && a != null ) this.ensArete.add(a);
	}


	public String toString()
	{
		return String.format("x=%s, y=%s", this.x, this.y);
	}
}
