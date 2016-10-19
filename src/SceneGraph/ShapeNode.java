package SceneGraph;

import java.util.List;

public class ShapeNode extends Node {

	private List<Node> strokes;
	private boolean isFilled;
	
	public ShapeNode(List<Node> s){
		this.strokes = s;
		isFilled = false;
	}
	
}
