package SceneGraph;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class ContainerNode extends Node {
	
	List<Node> children;
	
	public ContainerNode() {
		this.children = new ArrayList<Node>();
	}
	
	@Override
	public Rectangle getBounds() {
		boolean first = true;
		int left = 0, top = 0, right = 0, bottom = 0; //what is in left, top?
		for(Node child : this.children) {
			if(first) {
				/* Initialization */
				left = child.getBounds().x;
				top = child.getBounds().y;
				first = false;
			}
			left = Math.min(child.getBounds().x, left);
			top = Math.min(child.getBounds().y, top);
			right = Math.min(child.getBounds().width + child.getBounds().x, right);
			bottom = Math.min(child.getBounds().height + child.getBounds().y, bottom);
		}
		return new Rectangle(left, top, right-left, bottom-top);
	}
}
