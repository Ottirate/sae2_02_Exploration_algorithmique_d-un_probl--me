package graphe.ihm;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
// import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Point2D;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author Gabriel Roche
 * @since  
 */
public class JDrawPanel extends JPanel {
	
	@Serial
	private static final long serialVersionUID = 1116509950345559466L;
	
	private List<FancyShape> shapes;

	private List<FancyString> strings;
	
	public JDrawPanel() {
		this(new FlowLayout(FlowLayout.TRAILING));
	}
	
	public JDrawPanel(LayoutManager lm) {
		super(lm);
		this.shapes = new ArrayList<>();
		this.strings = new ArrayList<>();
	}
	
	public void addShape(FancyShape fs) {
		this.shapes.add(fs);
		this.updateUI();
	}
	
	public void addShapes(FancyShape[] fs) {
		this.shapes.addAll(Arrays.asList(fs));
		this.updateUI();
	}
	
	public void addShapes(Collection<? extends FancyShape> col) {
		this.shapes.addAll(col);
		this.updateUI();
	}
	
	public void removeShape(FancyShape fs) {
		this.shapes.remove(fs);
		this.updateUI();
	}
	
	public void removeShapes(FancyShape[] fs) {
		this.shapes.removeAll(Arrays.asList(fs));
		this.updateUI();
	}
	
	public void removeShapes(Collection<? extends FancyShape> col) {
		this.shapes.removeAll(col);
		this.updateUI();
	}
	
	public void drawLine(Line2D.Double line, Color color) {
		this.shapes.add(new FancyShape(line, color));
		this.updateUI();
	}
	
	public void drawLine(double x1, double y1, double x2, double y2, Color color) {
		this.shapes.add(new FancyShape(new Line2D.Double(x1, y1, x2, y2), color));
		this.updateUI();
	}
	
	public void drawLine(Point2D p1, Point2D p2, Color color) {
		this.shapes.add(new FancyShape(new Line2D.Double(p1, p2), color));
		this.updateUI();
	}
	
	public void drawRect(Rectangle rect, Color color) {
		this.shapes.add(new FancyShape(rect, color));
		this.updateUI();
	}
	
	public void drawRect(int x, int y, int width, int height, Color color) {
		this.shapes.add(new FancyShape(new Rectangle(x, y, width, height), color));
		this.updateUI();
	}
	
	public void drawCircle(Ellipse2D.Double circle, Color color) {
		this.shapes.add(new FancyShape(circle, color));
		this.updateUI();
	}
	
	public void drawCircle(int x, int y, int diameter, Color color) {
		this.shapes.add(new FancyShape(new Ellipse2D.Double(x, y, diameter, diameter), color));
		this.updateUI();
	}
	
	public void drawEllipse(Ellipse2D.Double ellipse, Color color) {
		this.shapes.add(new FancyShape(ellipse, color));
		this.updateUI();
	}

	public void drawString(String s, int x, int y) {
		this.strings.add(new FancyString(s, x, y));
		this.updateUI();
	}
	
	@Override
	protected final void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
        // if (this.shapes != null)
        //     for (FancyShape fs : this.shapes) {
        //         if (fs != null)
        //             if (fs.getShape() instanceof Ellipse2D.Double)
        //                 fs.setBackgroundColor(Color.BLACK);
        //     }

		for (FancyShape fs : this.shapes) {
			/*  Background  */
			// if (fs.getBackgroundColor() != null)
			// 	g2d.setBackground(fs.getBackgroundColor());
			g2d.setBackground(Color.BLACK);

			/*  Stroke color  */
			if (fs.getColor() != null)
				g2d.setColor(fs.getColor());
			else
				g2d.setColor(Color.BLACK);
			
			/*  Shape  */
			if (fs.getShape() == null)
				continue;
			else
				g2d.draw(fs.getShape());
		}

		for (FancyString fs : this.strings) {
			g2d.setColor(Color.BLACK);
			g2d.drawString(fs.s(), fs.x(), fs.y());
		}
	}
	
	@Override
	public void updateUI() {
		super.updateUI();
		this.repaint();
	}

	private record FancyString(String s, int x, int y) { }
	
}