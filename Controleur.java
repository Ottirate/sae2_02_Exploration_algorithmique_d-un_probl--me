/** Auteur : Equipe 1
  * Date   : juin 2023
*/

/*Paquetage*/
package graphe;

/*Importations*/
//paquetage
import graphe.ihm.FrameGraphe;
import graphe.metier.*;

//Valeurs et constantes IHM
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.SwingUtilities;

//Listes
import java.util.ArrayList;

public class Controleur 
{
	/*Attributs*/
	private FrameGraphe ihm;
	private Graphe      metier;

	private Dimension tailleEcran;

	/*Constructeur*/
	public Controleur()
	{
		//Sarah supprime pas c comme ça que plp il a fait je crois
		this.tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.metier = new Graphe();
		this.ihm    = new FrameGraphe(this);
	}

	/*Accesseurs - metier*/
	//Listes
	public ArrayList<Point> getSommets()  { return this.metier.getPoints() ; } 
	public ArrayList<Arete> getAretes ()  { return this.metier.getAretes() ; }
	public ArrayList<Region> getRegions() { return this.metier.getRegions(); }

	//Propriétés
	public int   getId    (Arete a)    { return this.metier.getId (a)     ; }
	public Color getColor ( int id )   { return this.metier.getColor( id ); }
	public Color getColor ()           { return this.metier.getColor()    ; }
	public Arete getArete (String nom) { return this.metier.getArete(nom) ; }

	//Chercher
	public Arete   trouverArete  (Point x, Point y)   { return this.metier.trouverArete(x, y); }
	public Point   trouverPoint  (double x, double y) { return this.metier.trouverPoint(x, y); }
	public boolean estColoriable (int ind)            { return this.metier.estColoriable(ind); }

	/*Accesseurs - IHM*/
	public Dimension getTailleEcran(){ return this.tailleEcran; }

	public int getHauteurIHM() { return this.ihm.getHeight(); }
	public int getLargeurIHM() { return this.ihm.getWidth (); }
	public int getCoef()       { return this.ihm.getCoef()  ; }


	/*Modifieurs*/
	public void setAreteSelectionne (String nom) { this.ihm.setAreteSelectionne(nom);}

	public boolean colorier ( int id ) { return this.metier.colorier( id ); }
	
	/*Methode - IHM*/
	public void majIhm () { this.ihm.maj() ;}	

	/*Main*/
	public static void main( String[] args )
	{
		SwingUtilities.invokeLater(Controleur::new);
	}
}
