package graphe.ihm;

import javax.swing.*;

import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

import graphe.Controleur;

public class PanelAction extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JLabel lblSelection;
	private JLabel lblCouleur;

	private JButton btnColorier;

	public PanelAction(Controleur ctrl)
	{
		// Définition du panel
		this.ctrl = ctrl;
		this.setLayout ( new BorderLayout(5,5) );

		// Création des composants
		JPanel panelControle = new JPanel(new GridLayout(2,1));

		this.lblSelection = new JLabel ();
		this.lblCouleur   = new JLabel ();

		this.btnColorier = new JButton("Colorier !");

		// Positionnement des composants
		panelControle.add ( new JLabel("Arête selectionné : ")   );
		panelControle.add ( this.lblSelection );

		this.add(this.lblCouleur , BorderLayout.WEST  );
		this.add(panelControle   , BorderLayout.CENTER);
		this.add(this.btnColorier, BorderLayout.SOUTH );

		// Activation des composants
		this.btnColorier.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		// this.ctrl.ajouterPoint();
	}

	public void changerCouleur ( Color col ) { this.lblCouleur.setBackground(col); }
}