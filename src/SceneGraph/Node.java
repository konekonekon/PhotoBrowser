package SceneGraph;

import java.awt.Graphics2D;
import java.util.*;

public abstract class Node {

	private List<Node> children;
	private Node parent;
	private boolean visible;

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

	//public abstract Rectangle getBounds();

	public void paint(Graphics2D g2) {
		if (!visible)
			return;
		paintNode(g2);
		for (Node child : children)
			child.paint(g2);
	}

	protected abstract void paintNode(Graphics2D g2);

}
