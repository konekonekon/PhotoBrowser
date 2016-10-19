package SceneGraph;

import java.awt.*;

public class TextNode extends Node {
	String text;
	Point startPoint;
	
	public TextNode(String s, Point p){
		this.text = s;
		this.startPoint = p;
	}
}
