package graphe.ihm;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * @author Gabriel Roche
 * @since  
 */
public class FancyShape {
	
	private Shape shape;
	
	private Color color;
	
	private Color bkGrdColor;
	
	private double strokeWidth;
	
	public FancyShape() {
		this(null, null);
	}
	
	public FancyShape(Shape sh, Color c) {
		this(sh, c, null);
	}
	
	public FancyShape(Shape sh, Color c, Color bkGrdColor) {
		this.shape      = sh;
		this.color      = c;
		this.bkGrdColor = bkGrdColor;
		this.verify();
	}
	
	public Shape getShape() {
		return this.shape;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public Color getBackgroundColor() {
		return this.bkGrdColor;
	}
	
	public double getStrokeWidth() {
		return this.strokeWidth;
	}
	
	public void setShape(Shape newShape) {
		this.shape = newShape;
	}
	
	public void setColor(Color newColor) {
		this.color = newColor;
	}
	
	public void setBackgroundColor(Color bkGrdColor) {
		this.bkGrdColor = bkGrdColor;
	}
	
	public void setStrokeWidth(double width) {
		this.strokeWidth = width;
	}
	
	private void verify() {
		if (this.shape instanceof Line2D)
			this.bkGrdColor = null;
	}
	
	public class Builder {
		
		private Shape shape;
		private Color color;
		private Color bkGrdColor;
		private double strokeWidth;
		
		public Builder() { }
		
		public Builder setShape(Shape sh) {
			this.shape = sh;
			return this;
		}
		
		public Builder setColor(Color c) {
			this.color = c;
			return this;
		}
		
		public Builder setBackgroundColor(Color c) {
			this.bkGrdColor = c;
			return this;
		}
		
		public Builder setStrokeWidth(double width) {
			this.strokeWidth = width;
			return this;
		}
		
		public FancyShape build() {
			var shape = new FancyShape(this.shape, this.color, this.bkGrdColor);
			shape.setStrokeWidth(this.strokeWidth);
			return shape;
		}
		
	}
	
}