package SceneGraph;

import java.awt.*;
import java.awt.geom.GeneralPath;

//stroke
public class PathNode extends Node {
	
	private GeneralPath aPath;
	
	public PathNode() {
		aPath = new GeneralPath();		
	}
	
	@Override
	public Rectangle getBounds() {
		return this.aPath.getBounds();
	}
}
