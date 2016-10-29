package Lab2;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import SceneGraph.*;

public class PhotoComponent extends Scene {

	private BufferedImage image;
	private ImageNode backgroundNode;
	
	// View
	private int viewWidth, viewHeight, imgWidth, imgHeight;
	private int left, top, right, bottom;
	private double viewRatio;
	private AffineTransform view;

	public PhotoComponent() {
		reset();
		viewWidth = 0; viewHeight = 0;
		left = 0; top = 0; right = 0; bottom = 0;
		viewRatio = 1;
	}
	
	public void reset() {
		image = null;
		backgroundNode = null;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		if (backgroundNode != null)
			backgroundNode.getParent().remove(backgroundNode);

		this.image = image;
		backgroundNode = new ImageNode(image, 0, 0);
		getRoot().add(backgroundNode);

		updateView();
		repaint();
	}

	/* Calculate view elements */
	private void updateView() {
		imgWidth = image.getWidth();
		imgHeight = image.getHeight();
		double imgRatio = (double) imgHeight / (double) imgWidth;

		int cvsWidth = this.getWidth();
		int cvsHeight = this.getHeight();
		double cvsRatio = (double) cvsHeight / (double) cvsWidth;
		
		if (imgWidth < cvsWidth && imgHeight < cvsHeight) {
			viewWidth = imgWidth;
			viewHeight = imgHeight;
			left = (cvsWidth - imgWidth) / 2;
			top = (cvsHeight - imgHeight) / 2;
			right = left + viewWidth;
			bottom = top + viewHeight;
		} 
		else {
			if (cvsRatio > imgRatio) {
				viewWidth = cvsWidth;
				viewHeight = (int) (viewWidth * imgRatio);
				left = 0;
				right = cvsWidth;
				top = (cvsHeight - viewHeight) / 2;
				bottom = top + viewHeight;
			} 
			else {
				viewHeight = cvsHeight;
				viewWidth = (int) (viewHeight / imgRatio);
				top = 0;
				bottom = cvsHeight;
				left = (cvsWidth - viewWidth) / 2;
				right = left + viewWidth;
			}
		}
		viewRatio = (double) viewHeight / (double) imgHeight;
		
		view = new AffineTransform();
		view.translate(left,  top);
		view.scale(viewRatio, viewRatio);
		getRoot().setTransform(view);
	}

	/* Scale point: move & resize */
	public Point physicToLogicPoint(Point p){
		int logicX = (int) ((p.x - left) / viewRatio);
		int logicY = (int) ((p.y - top) / viewRatio);
		Point logicPoint = new Point(logicX, logicY);
		return logicPoint;
	}
	
	public Point logicToPhysicPoint(Point p){
		int physicX = (int) (p.x * viewRatio) + left;
		int physicY = (int) (p.y * viewRatio) + top;
		Point physicPoint = new Point(physicX, physicY);
		return physicPoint;
	}

}
