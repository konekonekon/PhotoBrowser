package SceneGraph;

import java.awt.*;

public class TextNode extends Node {

	private String text;
	private Point startPoint;

	public TextNode(String str, Point p) {
		text = str;
		startPoint = p;
	}

	@Override
	public Rectangle getBounds() {
		//TODO width, height
		return new Rectangle(startPoint.x, startPoint.y - 10, 10, 10);
	}
	
	@Override
	protected void paintNode(Graphics2D g2) {
		g2.drawString(text, startPoint.x, startPoint.y);
	}
}
