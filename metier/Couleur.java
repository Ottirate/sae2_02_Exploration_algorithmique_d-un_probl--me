/*
 * Auteur : Équipe 1
 * Date   : juin 2023
 * */


/*      Paquetage      */
package graphe.metier;


/*       Imports       */
import java.awt.Color;


public enum Couleur
{
	BLEU_PALE   ( Color.decode("#3D74FF") ),
	ROUGE_PALE  ( Color.decode("#FF644C") ),
	JAUNE_PALE  ( Color.decode("#F5DD14") ),
	VERT_PALE   ( Color.decode("#35E11D") ),
	VIOLET_PALE ( Color.decode("#FF42B3") ),
	CYAN_PALE   ( Color.decode("#26DCA5") ),
	MARRON_PALE ( Color.decode("#FF7E42") ),
	ORANGE_PALE ( Color.decode("#FFCA44") ),
	GRIS_PALE   ( Color.decode("#ADADAD") ),
	BLEU_FONCE  ( Color.decode("#3854AA") );


	/* Attributs Constants */
	private final Color color;


	/*    Constructeur     */
	Couleur(Color c) {
		this.color = c;
	}


	/*     Accesseurs      */
	public Color getValue () { return this.color; }
}
