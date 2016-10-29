package SceneGraph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.*;

public abstract class Node {

	protected List<Node> children;
	protected Node parent;
	protected boolean visible;
	protected Color color;
	protected AffineTransform transform;

	protected Node() {
		children = new ArrayList<Node>();
		visible = true;
	}

	public void add(Node n) {
		if (n.parent != null)
			n.parent.remove(n);
		children.add(n);
		n.parent = this;
	}

	public void remove(Node n) {
		children.remove(n);
		n.parent = null;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public AffineTransform getTransform() {
		return transform;
	}

	public void setTransform(AffineTransform transform) {
		this.transform = transform;
	}
	
	public void scale(double sx, double sy) {
		transform.scale(sx, sy);
	}
	
	public void translate(double tx, double ty) {
		transform.translate(tx, ty);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}

	public abstract Rectangle getBounds();

	public void paint(Graphics2D g2) {
		// Graphical context
		if (!visible)
			return;
		if (color != null)
			g2.setColor(color);
		if (transform != null)
			g2.setTransform(transform);
		// Cascade paint
		paintNode(g2);
		for (Node child : children)
			child.paint(g2);
	}

	protected abstract void paintNode(Graphics2D g2);

	public List<Node> getChildren() {
		return children;
	}

	public Node getParent() {
		return parent;
	}

}
