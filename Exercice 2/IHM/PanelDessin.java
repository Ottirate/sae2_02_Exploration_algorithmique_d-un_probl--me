/** Auteur : Equipe 1
  * Date   : juin 2023
*/

/*Paquetage*/
package graphe.ihm;

/*Importations*/
//Paquetages
import graphe.Controleur;
import graphe.metier.*;

//Listes
import java.util.ArrayList;
import java.util.List;

//IHM
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;

//Dessin
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.Ellipse2D;

public class PanelDessin extends JPanel
{
	/*Attributs*/
	//Controleur
	private Controleur ctrl;

	//Propriétés
	private int margeGraphe;
	private int coef;
	private int maxX;
	private int maxY;

	//Valeurs
	private Point point1, point2;
	private Integer posX;
	private Integer posY;

	//IHM
	private Graphics2D g2;

	public PanelDessin(Controleur ctrl) 
	{
		/*Attribut*/
		this.ctrl = ctrl;
		
		// Définition du panel
		this.setLayout(new BorderLayout());
		this.setOpaque(false);

		// Activation des composants
		GereSouris gereSouris = new GereSouris();
		this.addMouseListener(gereSouris);
		this.addMouseMotionListener(gereSouris);

		this.maxX = 0;
		this.maxY = 0;
		for (Point p : this.ctrl.getSommets()) 
		{
			if (p.getX() > this.maxX)
				this.maxX = p.getX() + 1; // pour éviter le /0
			if (p.getY() > this.maxY)
				this.maxY = p.getY() + 1; // pour éviter le /0
		}
	}

	/*Accesseurs*/
	public int getCoef() 
	{
		return this.coef;
	}

	/*Methode - dessiner*/
	public void paintComponent(Graphics g) 
	{
		int hauteur = this.ctrl.getHauteurIHM() - 20;
		int largeur = this.ctrl.getLargeurIHM() - 170;

		super.paintComponent(g);

		g2 = (Graphics2D) g;

		int coef1, coef2;

		coef1 = hauteur / (this.maxY);
		coef2 = largeur / (this.maxX);

		if (coef1 < coef2)
			this.coef = coef1;
		else
			this.coef = coef2;

		if (this.coef < 1)
			this.coef = 1;

		//Dessine les arêtes - On commence par les aretes pour ne pas superposer les aretes et les noeud
		for (Arete a : this.ctrl.getAretes()) 
		{
			if (a.getCouleur() != null)
			{
				g2.setColor(a.getCouleur());
				g2.setStroke(new BasicStroke(2f));
			}
			else
			{
				g2.setStroke(new BasicStroke(1f));
				g2.setColor(Color.GRAY);
			}

			Point p1 = a.getPointDepart();
			Point p2 = a.getPointArrivee();
			g2.drawLine((p1.getX() * this.coef) , (p1.getY() * this.coef) ,
					    (p2.getX() * this.coef) , (p2.getY() * this.coef) );
		}

		//Dessine les régions
		for (Region r : this.ctrl.getRegions())
		{
			//Dessine les points
			for (Point p : r.getPoints())
			{
				int coordX = p.getX() * this.coef - 10;
				int coordY = p.getY() * this.coef - 10;

				g2.setColor(r.getCouleur().getValue());
				g2.setStroke(new BasicStroke(1f));
				
				Ellipse2D.Double circle = new Ellipse2D.Double( coordX,
				                                                coordY,
																20, 20);
				
				g2.fill(circle);
				
				
				g2.setColor(Color.WHITE);
				g2.drawString(String.format("%2d",p.getId()), coordX + 4, coordY + 15);

				if (p == this.point1 || p == this.point2)
				{
					g2.setColor(Color.BLACK);
					g2.setStroke(new BasicStroke(2f));
					g2.draw(circle);
				}
			}
		}

		//Dessine un message de fin
		boolean estFini = false; //mettre un accesseur sur le controleur
		if( estFini )
		{
			
		}

	}

	/*Classe GereSouris*/
	private class GereSouris extends MouseAdapter 
	{
		
		private Point point1;
		private Point point2;
		
		public void mousePressed(MouseEvent e) 
		{
			int posX, posY;
			Controleur ctrl;

			ctrl = PanelDessin.this.ctrl; // raccourci inhumain de l'argumentation
			
			posX = e.getX();
			posY = e.getY();

			Point p = ctrl.trouverPoint(posX * 1.0 / PanelDessin.this.coef,
			                            posY * 1.0 / PanelDessin.this.coef);

			if (p == null) 
			{
				this.point1 = this.point2 = null;
			} 
			else 
			{
				this.point2 = this.point1;
				this.point1 = p;
			}

			if (this.point1 != null && this.point2 != null) 
			{
				Arete a = ctrl.trouverArete(this.point1, this.point2);

				if (ctrl.estColoriable(ctrl.getId(a)))
				{	
					ctrl.setAreteSelectionne(a.toString());
				}
				else
				{
					ctrl.setAreteSelectionne("");
					this.point2 = null;
				}
			}

			PanelDessin.this.repaint();
			PanelDessin.this.point1 = this.point1;
			PanelDessin.this.point2 = this.point2;
		}
	}
}
