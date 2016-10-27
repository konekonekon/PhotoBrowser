package SceneGraph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;

public abstract class Node {

	private Node parent;
	private List<Node> children;
	private boolean visibility;

	/*private Node paint;
	private List<Node> paintChildren;*/
	
	public Node(){
		parent = null;
		children = new ArrayList<Node>();
		visibility = false;
		
	}
	
	public void addChidren(Node n){
		children.add(n);
	}
	
	public void removeChildren(Node n){
		children.remove(n);
	}
	
	//How to cascade paint ?? //TODO
	public void cascadePaint(){
		for (Node child : children) {
			//all elements of parent is cascaded to childre??
			//child = parent;
			
		}
	}
	//correct?
	public int getColor(Color c) {
		return c.getRGB();
	}
	//what is this??
	public void transformation() {
		
	}
	
	public void getBounds(){
		
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		
		
	}
	
}
