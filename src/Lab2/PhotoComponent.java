package Lab2;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import SceneGraph.*;

public class PhotoComponent extends Scene implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private ImageNode imageNode;
	private ContainerNode front;
	private ContainerNode back;
	private ShapeNode shape;
	private GeneralPath currentStroke;

	public PhotoComponent() {
		reset();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void reset() {
		image = null;
		imageNode = null;
		front = null;
		back = null;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		//clear root's children
		if(front != null)
			front.getParent().remove(front);
		if(back != null)
			back.getParent().remove(back);
		
		//create new and add to root
		front = new ContainerNode();
		back = new ContainerNode();
		getRoot().add(front);
		getRoot().add(back);
		
		//add image to front
		this.image = image;
		imageNode = new ImageNode(image, 0, 0);
		front.add(imageNode);
		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		
		//add shape to back
		shape = new ShapeNode(new Rectangle(0, 0, image.getWidth(), image.getHeight()));
		back.add(shape);
		back.setVisible(false);
		
		repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (currentStroke == null)
			return;
		
		/*if (e.getX() < image.getWidth() && e.getY() < image.getHeight())
			currentStroke.lineTo(e.getX(), e.getY());*/
		if (currentStroke.getCurrentPoint().getX() < image.getWidth() && 
				currentStroke.getCurrentPoint().getY() < image.getHeight())
			currentStroke.lineTo(e.getX(), e.getY());
		else
			
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (image != null) {
			if (e.getClickCount() == 2) {
				if (front.isVisible()){
					front.setVisible(false);
					back.setVisible(true);
				}
				else {
					front.setVisible(true);
					back.setVisible(false);
				}
					
				// currentText = null;
			}
			/*
			 * if (e.getClickCount() == 1) { this.requestFocusInWindow(); Create
			 * new text //currentText = new Text("", viewPoint); if (isFlipped
			 * == true) { textsInBack.add(currentText); } else {
			 * textsInFront.add(currentText); } }
			 */
		}
		repaint();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (image == null)
			return;
		if (e.getX() < image.getWidth() && e.getY() < image.getHeight()) {
			if (front.isVisible()) {
				currentStroke = new GeneralPath();
				PathNode lineInFront = new PathNode(currentStroke);
				lineInFront.setColor(Color.RED);
				front.add(lineInFront);
				currentStroke.moveTo(e.getX(), e.getY());
			}
			if (back.isVisible()) {
				currentStroke = new GeneralPath();
				PathNode lineInBack = new PathNode(currentStroke);
				lineInBack.setColor(Color.RED);
				back.add(lineInBack);
				currentStroke.moveTo(e.getX(), e.getY());
			}
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		currentStroke = null;
	}

}
