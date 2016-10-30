package SceneGraph;

import java.awt.*;

public class ContainerNode extends Node {

	public ContainerNode() {
	}

	@Override
	public Rectangle getBounds() {
		/* create a inexistent rectangle */
		Rectangle rec = new Rectangle(0, 0, -1, -1);
		for (Node child : this.children)
			rec = rec.union(child.getBounds());
		return rec;
	}

	@Override
	protected void paintNode(Graphics2D g2) {
	}
}
