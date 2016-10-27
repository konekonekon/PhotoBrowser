package SceneGraph;

import java.util.List;

public class ShapeNode extends Node {

	private List<PathNode> strokes;
	private boolean isFilled;
	
	public ShapeNode(List<PathNode> s){
		this.strokes = s;
		isFilled = false;
	}
	
}
