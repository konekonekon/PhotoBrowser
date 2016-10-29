package SceneGraph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.*;

public abstract class Node {

	protected List<Node> children;
	protected Node parent;
	protected boolean visible;
	protected Color color;

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
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}

	public abstract Rectangle getBounds();

	public void paint(Graphics2D g2) {
		if (!visible)
			return;
		if (color != null)
			g2.setColor(color);
		paintNode(g2);
		Rectangle bounds = getBounds();
		if (bounds != null)
			g2.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
		for (Node child : children)
			child.paint(g2);
	}

	protected abstract void paintNode(Graphics2D g2);

}
