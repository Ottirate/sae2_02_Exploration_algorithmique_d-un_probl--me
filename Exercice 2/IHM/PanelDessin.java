package graphe.ihm;

import graphe.Controleur;
import graphe.metier.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class PanelDessin extends JPanel
{
	//Position 
	private int    margeGraphe;
	private int    coef;
	private int    maxX;
	private int    maxY;


	private Controleur ctrl;
	private Graphics2D g2;

	private Integer posX;
	private Integer posY;

	public PanelDessin( Controleur ctrl )
	{
		// définition du panel
		this.ctrl = ctrl;
		this.setLayout ( new BorderLayout() );
		this.setOpaque ( false );
		
		// activation des composants
		GereSouris gereSouris = new GereSouris();

		this.addMouseListener      ( gereSouris );
		this.addMouseMotionListener( gereSouris );


		this.maxX = this.maxY = 0;
		for (Point p : this.ctrl.getSommets())
		{
			if (p.getX() > this.maxX) this.maxX = p.getX();
			if (p.getY() > this.maxY) this.maxY = p.getY();
		}
	}
	
	
	public void paintComponent(Graphics g)
	{
		int hauteur = this.ctrl.getHauteurIHM();
		int largeur = this.ctrl.getLargeurIHM();

		super.paintComponent(g);

		g2 = (Graphics2D) g;

		int coef1, coef2;

		coef1 = hauteur/(this.maxY);
		coef2 = largeur/(this.maxX);

		if (coef1 < coef2) this.coef = coef1;
		else               this.coef = coef2; 

		if (this.coef < 1) this.coef = 1;

		this.margeGraphe = 10 * this.coef;

		for (Arete a : this.ctrl.getAretes())
		{
			Point p1 = a.getPointDepart();
			Point p2 = a.getPointArrivee();
			g2.drawLine((p1.getX()*this.coef) + this.margeGraphe, (p1.getY()*this.coef) + this.margeGraphe, 
			            (p2.getX()*this.coef) + this.margeGraphe, (p2.getY()*this.coef) + this.margeGraphe);
		}


		int d = 10 * this.coef;
		for (Point p : this.ctrl.getSommets())
		{
			Ellipse2D.Double circle = new Ellipse2D.Double(p.getX() * this.coef + this.margeGraphe - d/2,
			                                               p.getY() * this.coef + this.margeGraphe - d/2, 
														   d,d);
   			g2.fill(circle);
		}
		 

	}

	private class GereSouris extends MouseAdapter
	{
		int posX, posY;
		Arete arete;
		Controleur ctrl;

		public void mousePressed (MouseEvent e)
		{
			this.ctrl = PanelDessin.this.ctrl;
			try
			{
				this.posX = e.getX();
				this.posY = e.getY();
				this.arete = this.ctrl.trouverArete( this.posX, this.posY );
				if( this.arete != null )
				{
					System.out.println( this.ctrl.colorier( this.ctrl.getId( this.arete ) ) );
					System.out.println( this.arete.getCouleur() );
					//mettre aussi la couleur dnas l'ihm
				}
			}
			catch( Exception ex )
			{
			}
		}
	}
}