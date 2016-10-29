package SceneGraph;

import java.awt.*;

public class TextNode extends Node {
	private String text;
	private Point startPoint;
	
	public TextNode(String str, Point p){
		text = str;
		startPoint = p;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.RED);
		g2d.drawString(text, startPoint.x, startPoint.y);
		
	}
}
