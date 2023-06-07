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
		int hauteur = this.ctrl.getHauteurIHM() - 90;
		int largeur = this.ctrl.getLargeurIHM() - 10;

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
				g2.setStroke(new BasicStroke(1f));
				g2.setColor(r.getCouleur().getValue());
				
				Ellipse2D.Double circle = new Ellipse2D.Double(p.getX() * this.coef + this.margeGraphe - 6,
						                                       p.getY() * this.coef + this.margeGraphe - 6,
						                                       12, 12);

				g2.fill(circle);

				if (p == this.point1 || p == this.point2)
				{
					g2.setColor(Color.BLACK);
					g2.setStroke(new BasicStroke(3f));
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
	
	public boolean hoverShape(Point p) {
		return this.ctrl.getSommets().stream().anyMatch(t -> {
		    int hauteur = this.ctrl.getHauteurIHM() - 90;
		    int largeur = this.ctrl.getLargeurIHM() - 10;

		    int coef, coef1, coef2;

		    coef1 = hauteur / (this.maxY);
		    coef2 = largeur / (this.maxX);

		    if (coef1 < coef2)
			coef = coef1;
		    else
			coef = coef2;

		    if (coef < 1)
			coef = 1;

		    int x = t.getX() * coef - 5;
		    int y = t.getY() * coef - 5;

		    return (p.getX() >= x && p.getY() >= y) && (p.getX() <= (x + 10) && p.getY() <= (y + 10));
		});
	    }

	/*Classe GereSouris*/
	private class GereSouris extends MouseAdapter 
	{
		int posX, posY;
		Point point1, point2;
		Controleur ctrl;
		
		@Override
		public void mouseMoved(MouseEvent e) {
		    this.ctrl = PanelDessin.this.ctrl;

		    PanelDessin panel = PanelDessin.this;

		    // List<Point> lstPoints = this.ctrl.getSommets();

		    this.ctrl.getSommets().stream().forEach(p -> {
			if (panel.hoverShape(new Point(-1, e.getPoint().x, e.getPoint().y)))
			    panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			else
			    panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		    });

		    if (panel.hoverShape(new Point(-1, e.getPoint().x, e.getPoint().y)))
			panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    else
			panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		public void mousePressed(MouseEvent e) 
		{
			this.ctrl = PanelDessin.this.ctrl; // raccourci inhumain de l'argumentation
			try 
			{
				this.posX = e.getX();
				this.posY = e.getY();

				Point p = this.ctrl.trouverPoint(this.posX * 1.0 / PanelDessin.this.coef,
						this.posY * 1.0 / PanelDessin.this.coef);

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
					Arete a = this.ctrl.trouverArete(this.point1, this.point2);

					if (this.ctrl.estColoriable(this.ctrl.getId(a)))
						this.ctrl.setAreteSelectionne(a.toString());
					else
						this.point2 = null;

					/* System.out.println( this.arete.getCouleur() ); */
				}

				PanelDessin.this.repaint();
				PanelDessin.this.point1 = this.point1;
				PanelDessin.this.point2 = this.point2;
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
	}
}
