/*
 * Auteur : Équipe 1
 * Date   : juin 2023
 * */


/*      Paquetage      */
package graphe;


/*       Imports       */
import graphe.ihm.FrameGraphe;
import graphe.ihm.PopupFin;
import graphe.metier.*;

import java.awt.Color;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

import java.util.ArrayList;


public class Controleur 
{
	/*      Attributs      */
	private FrameGraphe ihm;
	private Graphe      metier;


	/*    Constructeur     */
	public Controleur()
	{
		this.metier = new Graphe();
		this.ihm    = new FrameGraphe(this);
	}


	/* ------------------- */
	/*     Accesseurs      */
	/* ------------------- */

	/*       Metier        */
	public ArrayList<Point> getSommets()  { return this.metier.getPoints() ; } 
	public ArrayList<Arete> getAretes ()  { return this.metier.getAretes() ; }
	public ArrayList<Region> getRegions() { return this.metier.getRegions(); }

	public Color getColor ()           { return this.metier.getColor()    ; }
	public Arete getArete (String nom) { return this.metier.getArete(nom) ; }

	public Arete   trouverArete  (Point x, Point y)   { return this.metier.trouverArete(x, y) ; }
	public Point   trouverPoint  (double x, double y) { return this.metier.trouverPoint(x, y) ; }
	public boolean estColoriable (Arete a)            { return this.metier.estColoriable(a)   ; }

	/*         IHM         */
	public int getHauteurIHM() { return this.ihm.getHeight(); }
	public int getLargeurIHM() { return this.ihm.getWidth (); }


	/* ------------------- */
	/*     Modifieurs      */
	/* ------------------- */

	/*         IHM         */
	public void setAreteSelectionne (String nom) { this.ihm.setAreteSelectionne(nom); }

	/*       Metier        */
	public void colorier ( Arete a ) { this.metier.colorier( a ); }


	/* ------------------- */
	/*      Méthodes       */
	/* ------------------- */

	/*         IHM         */
	public void majIhm ()   { this.ihm.maj(); }
	public void finPartie()
	{
		int valeur = ((Integer)JOptionPane.showConfirmDialog( this.ihm, "Points : " + this.metier.nbPoint() + "\n Voulez vous rejouer ?", "Fin de la partie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE));
		if( valeur == JOptionPane.YES_OPTION ) 
		{
			this.metier.initialiser();
			this.ihm.maj();
		}
		else
			this.ihm.dispose();
	}


	/*      Lancement      */
	public static void main( String[] args )
	{
		SwingUtilities.invokeLater( Controleur::new );
	}
}
