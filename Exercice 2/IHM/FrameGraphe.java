/** Auteur : Equipe 1
  * Date   : juin 2023
*/

/*Paquetage*/
package graphe.ihm;

/*Importations*/
//Paquetage
import graphe.Controleur;

//IHM
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.*;

public class FrameGraphe extends JFrame 
{
	/*Attributs*/
	private Controleur  ctrl;
	private PanelDessin panelDessin;
	private PanelAction panelAction;

	/*Constructeur*/
	public FrameGraphe (Controleur ctrl)
	{
		this.ctrl = ctrl;

		// Paramètre
		this.setTitle   ("Graphe SAE 2.02");
		this.setSize    ( (int) this.ctrl.getTailleEcran().getHeight()-200, (int) this.ctrl.getTailleEcran().getHeight() ); //1000,800
		this.setLocationRelativeTo(null)   ;
		
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		// Création des composants
		this.panelDessin = new PanelDessin(this.ctrl);
		this.panelAction = new PanelAction(this.ctrl);

		this.add(this.panelDessin, BorderLayout.CENTER);
		this.add(this.panelAction, BorderLayout.SOUTH );

		this.setVisible(true);
	}

	/*Acceseur*/
	public int getCoef() { return this.panelDessin.getCoef(); }

	/*Modifieur*/
	public void setAreteSelectionne (String nom) { this.panelAction.setAreteSelectionne(nom); }

	/*Méthodes*/
	public void maj() 
	{ 
		this.panelDessin.repaint();
		this.panelAction.changerCouleur(this.ctrl.getColor()); 
	}
}