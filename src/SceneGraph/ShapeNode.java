package SceneGraph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class ShapeNode extends Node {

	private Shape shape;
	
	public ShapeNode(Shape s){
		shape = s;
	}

	@Override
	public Rectangle getBounds() {
		return shape.getBounds();
	}

	@Override
	protected void paintNode(Graphics2D g2) {
		Rectangle rec = this.getBounds();
		g2.fillRect(0, 0, rec.width, rec.height);
		g2.draw(shape);
	}
}
