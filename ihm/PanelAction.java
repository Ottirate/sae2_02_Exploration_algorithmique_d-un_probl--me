/*
 * Auteur : Équipe 1
 * Date   : juin 2023
 * */


/*      Paquetage      */
package graphe.ihm;


/*       Imports       */
import graphe.Controleur;
import graphe.metier.Arete;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.*;


public class PanelAction extends JPanel implements ActionListener
{
	/*      Attributs      */
	private Controleur ctrl;

	private JLabel    lblSelection;
	private JTextArea txaHistorique;
	private JPanel    panelCouleur;

	private JButton btnColorier;


	/*    Constructeur     */
	public PanelAction(Controleur ctrl)
	{
		/*     Paramétrage     */
		this.ctrl = ctrl;

		this.setBackground(new Color(180,180,255));
		this.setLayout ( new BorderLayout(5,5) );


		/*      Créations      */
		JPanel panelControle = new JPanel(new BorderLayout());
		panelControle.setOpaque(false);

		JPanel panelTemp     = new JPanel(new GridLayout(1,2));
		panelTemp.setOpaque(false);

		this.lblSelection = new JLabel ();
		this.panelCouleur   = new JPanel();

		this.txaHistorique = new JTextArea();
		this.txaHistorique.setOpaque(false);

		this.panelCouleur.setBackground(this.ctrl.getColor());

		this.btnColorier = new JButton("<html><h4> Colorier !</h4></html>");
		this.btnColorier.setMnemonic( KeyEvent.VK_C );


		/*   Positionnement    */
		panelTemp.add(new JLabel("Selectionné :"));
		panelTemp.add(this.lblSelection);

		panelControle.add ( this.txaHistorique, BorderLayout.CENTER );
		panelControle.add ( panelTemp         , BorderLayout.SOUTH  );

		this.add(this.panelCouleur, BorderLayout.NORTH );
		this.add(this.btnColorier , BorderLayout.SOUTH );
		this.add(panelControle    , BorderLayout.CENTER);


		/*     Activation      */
		this.btnColorier.addActionListener(this);
	}


	/*     Modifieurs      */
	public void changerCouleur      ( Color col ) { this.panelCouleur.setBackground(col); }
	public void setAreteSelectionne (String nom)  { this.lblSelection.setText(nom)      ; }


	/*      Méthodes       */
	public void actionPerformed(ActionEvent e)
	{
		this.colorier();
	}


	public void colorier()
	{
		Arete a = this.ctrl.getArete(this.lblSelection.getText());

		if ( a != null ) this.ctrl.colorier( a );

		this.lblSelection.setText("");
		this.ctrl.majIhm();
	}
}