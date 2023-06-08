/*
 * Auteur : Équipe 1
 * Date   : juin 2023
 * */


/*      Paquetage      */
package graphe.ihm;


/*       Imports       */
import graphe.Controleur;
import javax.swing.JOptionPane;


public class PopupFin extends JOptionPane
{
	private Controleur ctrl;

	public PopupFin( Controleur ctrl )
	{
		/*

		super( "Voulez vous rejouer ?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION )
		JOptionPane.showConfirmDialog( "Voulez vous rejouer ?", "Fin de la partie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		super("Partie terminée");
		this.ctrl = ctrl;
		this.frame = frame;

		this.frame.add( this );
		this.firePopupMenuWillBecomeVisible();
		this.setVisible(true);
		this.show( this.frame, 200, 200 );

		*/
	}
}