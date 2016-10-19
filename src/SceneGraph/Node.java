package SceneGraph;

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
	
	public void addChidren(){
		
	}
	
	public void removeChildren(){
		
	}
	
	public void cascadePaint(){
		for (Node child : children) {
			child = parent;
		}
	}
	
	//exterieur de points
	public void getBounds(){
		
	}
	
}
