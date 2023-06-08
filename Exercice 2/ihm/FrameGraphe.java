/*
 * Auteur : Équipe 1
 * Date   : juin 2023
 * */


/*      Paquetage      */
package graphe.ihm;


/*       Imports       */
import graphe.Controleur;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;


public class FrameGraphe extends JFrame 
{
	/*      Attributs      */
	private Controleur  ctrl;
	private PanelDessin panelDessin;
	private PanelAction panelAction;


	/*    Constructeur     */
	public FrameGraphe (Controleur ctrl)
	{
		/*      Attributs      */
		this.ctrl = ctrl;


		/*     Paramétrage     */
		Dimension d = new Dimension(645, 485);
		//Taille opti
		this.setSize       (d); 
		this.setMinimumSize(d);

		this.setTitle   ("Graphe SAE 2.02");
		this.setLocationRelativeTo(null)   ;

		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);


		/*      Créations      */
		this.panelDessin = new PanelDessin(this.ctrl);
		this.panelAction = new PanelAction(this.ctrl);


		/*   Positionnement    */
		this.add(this.panelDessin, BorderLayout.CENTER);
		this.add(this.panelAction, BorderLayout.EAST  );


		/*      Affichage      */
		this.setVisible(true);
	}


	/*     Modifieurs      */
	public void setAreteSelectionne (String nom) { this.panelAction.setAreteSelectionne(nom); }


	/*      Méthodes       */
	public void maj() 
	{ 
		this.panelDessin.repaint();
		this.panelAction.changerCouleur(this.ctrl.getColor()); 
	}
}