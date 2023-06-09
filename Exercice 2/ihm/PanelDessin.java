/*
 * Auteur : Équipe 1
 * Date   : juin 2023
 * */


/*      Paquetage      */
package graphe.ihm;


/*       Imports       */
import graphe.Controleur;
import graphe.metier.*;
import graphe.metier.Point;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public class PanelDessin extends JPanel
{
	/*      Attributs      */
	private Controleur ctrl;

	private int coef;
	private int maxX;
	private int maxY;

	private Point point1, point2;

	private Graphics2D g2;


	/*    Constructeur     */
	public PanelDessin(Controleur ctrl)
	{
		/*      Attributs      */
		this.ctrl = ctrl;


		/*     Paramétrage     */
		this.setLayout(new BorderLayout());
		this.setOpaque(false);


		/*     Activation      */
		GereSouris gereSouris = new GereSouris();

		this.addMouseListener(gereSouris);
		this.addMouseMotionListener(gereSouris);


		this.maxX = 0;
		this.maxY = 0;

		for (Point p : this.ctrl.getSommets())
		{
			if ( p.getX() > this.maxX )
				this.maxX = p.getX() + 1; // pour éviter le /0
			if ( p.getY() > this.maxY )
				this.maxY = p.getY() + 1; // pour éviter le /0
		}
	}


	/*      Méthodes       */
	public void paintComponent(Graphics g)
	{
		int hauteur = this.ctrl.getHauteurIHM() - 50 ;
		int largeur = this.ctrl.getLargeurIHM() - 200;

		super.paintComponent(g);

		g2 = (Graphics2D) g;

		int coef1, coef2;

		coef1 = hauteur / (this.maxY);
		coef2 = largeur / (this.maxX);

		this.coef = Math.min(coef1, coef2);

		if ( this.coef < 1 ) this.coef = 1;


		//Dessine les arêtes - On commence par les arêtes pour ne pas superposer les arêtes et les nœuds
		for (Arete a : this.ctrl.getAretes())
		{
			if ( a.getCouleur() != null )
			{
				g2.setColor(a.getCouleur());
				g2.setStroke(new BasicStroke(2f));
			}
			else
			{
				if (a.getCout() > 0)
					g2.setStroke(new BasicStroke(2f));
				else
					g2.setStroke(new BasicStroke(1f));
				g2.setColor(Color.GRAY);
			}

			Point p1 = a.getPointDepart();
			Point p2 = a.getPointArrivee();

			int x1 = p1.getX() * this.coef ;
			int y1 = p1.getY() * this.coef ;
			int x2 = p2.getX() * this.coef ;
			int y2 = p2.getY() * this.coef ;

			g2.drawLine( x1, y1, x2, y2 );
		}


		// Dessine les régions et leur point
		for (Region r : this.ctrl.getRegions())
		{
			// Régions
			Polygon poly = new Polygon();

			this.trierPointsPolygone(r.getPoints(), poly);

			Color c = r.getCouleur().getValue();

			g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 50));
			g2.fill(poly);

			// Dessine les points
			for (Point p : r.getPoints())
			{
				int coordX = p.getX() * this.coef - 10;
				int coordY = p.getY() * this.coef - 10;

				g2.setColor(r.getCouleur().getValue());
				g2.setStroke(new BasicStroke(1f));

				Ellipse2D.Double circle = new Ellipse2D.Double( coordX, coordY, 20, 20);

				g2.fill(circle);

				g2.setColor(Color.WHITE);
				g2.drawString(String.format("%2d",p.getId()), coordX + 4, coordY + 15);

				if ( p == this.point1 || p == this.point2 )
				{
					g2.setColor(Color.BLACK);
					g2.setStroke(new BasicStroke(2f));
					g2.draw(circle);
				}
			}
		}
	}


	public void trierPointsPolygone(ArrayList<Point> points, Polygon poly)
	{
		int n = points.size();
		if (n < 3)
		{
			for ( Point p : points ) poly.addPoint( p.getX() * this.coef, p.getY() * this.coef );

			return;
		}

		ArrayList<Point> polygoneTri = new ArrayList<>();

		// Trouver le point le plus à gauche
		int pointGaucheIndex = 0;
		for (int i = 1; i < n; i++)
		{
			if ( points.get(i).getX() < points.get(pointGaucheIndex).getX() )
			{
				pointGaucheIndex = i;
			}
		}

		int pointCourantIndex = pointGaucheIndex;
		int pointSuivantIndex;

		do
		{
			polygoneTri.add( points.get(pointCourantIndex) );
			pointSuivantIndex = (pointCourantIndex + 1) % n;

			for ( int i = 0; i < n; i++ )
			{
				// Vérifier si le point i est plus à gauche que le pointSuivant
				if ( orientation(points.get(pointCourantIndex), points.get(i), points.get(pointSuivantIndex)) == 2 )
				{
					pointSuivantIndex = i;
				}
			}

			pointCourantIndex = pointSuivantIndex;

		}
		while ( pointCourantIndex != pointGaucheIndex );

		for ( Point p : polygoneTri ) poly.addPoint(p.getX() * this.coef, p.getY() * this.coef);
	}


	private static int orientation(Point p, Point q, Point r)
	{
		int val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());

		if (val == 0) return 0;  // Les points sont colinéaires

		return (val > 0) ? 1 : 2; // 1 pour sens horaire, 2 pour sens anti-horaire
	}


	//Ca marche pas + c'est lourd
	// public boolean hoverShape(Point p) {
	// 	return this.ctrl.getSommets().stream().anyMatch(t -> {
	// 	    int hauteur = this.ctrl.getHauteurIHM() - 90;
	// 	    int largeur = this.ctrl.getLargeurIHM() - 10;

	// 	    int coef, coef1, coef2;

	// 	    coef1 = hauteur / (this.maxY);
	// 	    coef2 = largeur / (this.maxX);

	// 	    if (coef1 < coef2)
	// 		coef = coef1;
	// 	    else
	// 		coef = coef2;

	// 	    if (coef < 1)
	// 		coef = 1;

	// 	    int x = t.getX() * coef - 5;
	// 	    int y = t.getY() * coef - 5;

	// 	    return (p.getX() >= x && p.getY() >= y) && (p.getX() <= (x + 10) && p.getY() <= (y + 10));
	// 	});
	//     }


	/*       Classe        */
	/*     GereSouris      */
	private class GereSouris extends MouseAdapter
	{
		/*      Attributs      */
		private Point point1;
		private Point point2;


		/*      Méthodes       */
		// Même truc : lourd + inutile
		// public void mouseMoved(MouseEvent e) {
		//     this.ctrl = PanelDessin.this.ctrl;

		//     PanelDessin panel = PanelDessin.this;

		//     this.ctrl.getSommets().stream().forEach(p -> {
		// 	if (panel.hoverShape(new Point(-1, e.getPoint().x, e.getPoint().y)))
		// 	    panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		// 	else
		// 	    panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		//     });
		// }


		public void mousePressed(MouseEvent e)
		{
			int posX, posY;
			Controleur ctrl;

			ctrl = PanelDessin.this.ctrl; // raccourci inhumain de l'argumentation

			posX = e.getX();
			posY = e.getY();

			Point p = ctrl.trouverPoint(posX * 1.0 / PanelDessin.this.coef,
			                            posY * 1.0 / PanelDessin.this.coef);

			if ( p == null )
			{
				this.point1 = this.point2 = null;
			}
			else
			{
				this.point2 = this.point1;
				this.point1 = p;
			}

			if ( this.point1 != null && this.point2 != null )
			{
				Arete a = ctrl.trouverArete(this.point1, this.point2);

				if ( ctrl.estColoriable(a) )
				{
					ctrl.setAreteSelectionne(a.toString());
				}
				else
				{
					ctrl.setAreteSelectionne("");
					this.point2 = null;
				}
			}
			else
			{
				ctrl.setAreteSelectionne("");
			}

			PanelDessin.this.repaint();
			PanelDessin.this.point1 = this.point1;
			PanelDessin.this.point2 = this.point2;
		}
	}
}
