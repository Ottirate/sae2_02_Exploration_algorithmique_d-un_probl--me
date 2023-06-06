package graphe.ihm;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import graphe.Controleur;

public class FrameGraphe extends JFrame
{
	private PanelDessin panelDessin;
	private PanelAction panelAction;

	private Controleur  ctrl;

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

	public void maj() { this.panelDessin.repaint(); }
	public int getCoef() { return this.panelDessin.getCoef() ; }
	public void setAreteSelectionne (String nom) {this.panelAction.setAreteSelectionne(nom);}

}