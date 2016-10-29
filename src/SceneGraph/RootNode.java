package SceneGraph;

import java.util.ArrayList;
import java.util.List;

public class RootNode extends Node {
	
	private List<Node> children;
	
	public RootNode() {
		this.children = new ArrayList<Node>();
		//this.children.add(n);
	}
	
	/*@Override
	public void addChidren(Node n) {
		this.children.add(n);
	}*/
	
	/*public List<Node> createTree(Node child) {
		List<Node> list = new ArrayList<Node>();
		list.add(child);
		return list;
	}*/
	
}
