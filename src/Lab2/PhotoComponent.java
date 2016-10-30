package Lab2;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

import SceneGraph.*;

public class PhotoComponent extends Scene implements MouseListener, MouseMotionListener {

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
		shape.setColor(Color.WHITE);
		back.add(shape);
		
		
		repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (currentStroke == null)
			return;
		currentStroke.lineTo(e.getX(), e.getY());
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (image == null)
			return;
		
		currentStroke = new GeneralPath();
		PathNode line = new PathNode(currentStroke);
		line.setColor(Color.RED);
		front.add(line);
		currentStroke.moveTo(e.getX(), e.getY());
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		currentStroke = null;
	}

}
