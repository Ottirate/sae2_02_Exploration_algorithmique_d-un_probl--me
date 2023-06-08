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
//import javax.swing.border.EmptyBorder;

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

		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout ( new BorderLayout(5,5) );


		/*      Créations      */
		JPanel panelControle = new JPanel(new BorderLayout());
		JPanel panelTemp     = new JPanel(new GridLayout(1,2));
		JPanel panelTemp2    = new JPanel(new GridLayout(5,1));
		
		panelControle.setOpaque(false);
		panelTemp    .setOpaque(false);
		panelTemp2   .setOpaque(false);

		this.lblSelection = new JLabel ();
		this.panelCouleur = new JPanel();
		this.btnColorier  = new JButton("<html><h4> <u>C</u>olorier !</h4></html>");

		this.panelCouleur.setBackground(this.ctrl.getColor());
		this.btnColorier.setMnemonic( KeyEvent.VK_C );

		//Ne fonctionne pas
		//this.setLayout( new BorderLayout() );
		//this.panelCouleur.setBorder(new EmptyBorder(50,50,50,50));


		/*   Positionnement    */
		panelTemp.add(new JLabel("Selectionné : "));
		panelTemp.add(this.lblSelection);

		panelTemp2.add(this.panelCouleur);
		panelTemp2.add(panelTemp        );

		panelControle.add ( panelTemp2        , BorderLayout.CENTER );
		panelControle.add ( panelTemp         , BorderLayout.SOUTH  );

		this.add(this.btnColorier , BorderLayout.SOUTH );
		this.add(panelControle    , BorderLayout.CENTER);


		/*     Activation      */
		this.btnColorier.addActionListener(this);
	}


	/*     Modifieurs      */
	public void changerCouleur      ( Color col ) 
	{ 
		this.panelCouleur.setBackground(col);
		if (col == Color.LIGHT_GRAY)
			this.ctrl.finPartie();
	}
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