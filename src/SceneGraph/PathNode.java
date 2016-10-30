package SceneGraph;

import java.awt.*;
import java.awt.geom.GeneralPath;

//stroke
public class PathNode extends Node {

	private GeneralPath path;

	public PathNode(GeneralPath path) {
		this.path = path;
	}

	@Override
	public Rectangle getBounds() {
		return path.getBounds();
	}

	@Override
	protected void paintNode(Graphics2D g2) {
		g2.draw(path);
	}
}