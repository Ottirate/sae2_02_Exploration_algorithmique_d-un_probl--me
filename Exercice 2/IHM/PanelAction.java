/** Auteur : Equipe 1
  * Date   : juin 2023
*/

/*Paquetage*/
package graphe.ihm;

/*Importation*/

//Paquetage
import graphe.Controleur;
import graphe.metier.Arete;

//IHM
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.*;

public class PanelAction extends JPanel implements ActionListener
{
	/*Attributs*/
	private Controleur ctrl;

	private JLabel lblSelection;
	private JPanel panelCouleur;

	private JButton btnColorier;

	/*Constructeur*/
	public PanelAction(Controleur ctrl)
	{
		// Définition du panel
		this.ctrl = ctrl;
		this.setLayout ( new BorderLayout(5,5) );

		// Création des composants
		JPanel panelControle = new JPanel(new GridLayout(1,3));

		this.lblSelection = new JLabel ();
		this.panelCouleur   = new JPanel();
		this.panelCouleur.setBackground(this.ctrl.getColor());

		this.btnColorier = new JButton("<html><u>C</u>olorier !</html>");
		this.btnColorier.setMnemonic( KeyEvent.VK_C );
	

		// Positionnement des composants
		panelControle.add ( this.panelCouleur );
		panelControle.add ( new JLabel("Arête selectionné : ")   );
		panelControle.add ( this.lblSelection );

		this.add(panelControle   , BorderLayout.CENTER);
		this.add(this.btnColorier, BorderLayout.SOUTH );

		// Activation des composants
		this.btnColorier.addActionListener(this);
	}

	/*Modifieurs*/
	public void changerCouleur      ( Color col ) { this.panelCouleur.setBackground(col); }
	public void setAreteSelectionne (String nom)  { this.lblSelection.setText(nom)      ; }

	/*Actions sur le panel*/
	public void actionPerformed(ActionEvent e)
	{
		this.colorier();
	}

	public void colorier()
	{
		Arete a = this.ctrl.getArete(this.lblSelection.getText());

		if (a != null) this.ctrl.colorier( this.ctrl.getId( a ) );

		this.lblSelection.setText("");
		this.ctrl.majIhm();
	}
}