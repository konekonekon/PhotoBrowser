package SceneGraph;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ContainerNode extends Node {
	
	public ContainerNode() {
	}
	
	@Override
	public Rectangle getBounds() {
		Rectangle b = new Rectangle(0, 0, -1, -1);
		for(Node child : this.children)
			b = b.union(child.getBounds());
		return b;
	}

	@Override
	protected void paintNode(Graphics2D g2) {
	}
}
