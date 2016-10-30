package Lab2WithSceneGraph;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

import SceneGraph.*;

public class PhotoComponent extends Scene implements MouseListener,
		MouseMotionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private final static String placeholder = "Insert text";
	private BufferedImage image;
	private ImageNode imageNode;
	private ContainerNode front;
	private ContainerNode back;
	private ShapeNode shape;
	private GeneralPath currentStroke;
	private TextNode currentText;

	public PhotoComponent() {
		reset();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		this.setFocusable(true);
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
		if (front != null)
			front.getParent().remove(front);
		if (back != null)
			back.getParent().remove(back);

		/* Create containers and add them to root */
		front = new ContainerNode();
		back = new ContainerNode();
		getRoot().add(front);
		getRoot().add(back);

		/* Add image to front */
		this.image = image;
		imageNode = new ImageNode(image, 0, 0);
		front.add(imageNode);
		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

		/* Add shape to back */
		shape = new ShapeNode(new Rectangle(0, 0, image.getWidth(),
				image.getHeight()));
		shape.setColor(Color.WHITE);
		back.add(shape);
		back.setVisible(false);

		repaint();
	}

	private void startStroke(MouseEvent e) {
		if (e.getX() < image.getWidth() && e.getY() < image.getHeight()) {
			currentStroke = new GeneralPath();
			currentStroke.moveTo(e.getX(), e.getY());
			PathNode line = new PathNode(currentStroke);
			line.setColor(Color.RED);
			if (front.isVisible())
				front.add(line);
			else if (back.isVisible())
				back.add(line);
		}
	}

	/* Cancel input */
	private void resetTextInput() {
		if (currentText != null) {
			if (currentText.text == placeholder)
				currentText.getParent().remove(currentText);
		}
		currentText = null;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (image == null)
			return;

		if (currentStroke == null)
			startStroke(e);

		if (e.getX() < image.getWidth() && e.getY() < image.getHeight())
			currentStroke.lineTo(e.getX(), e.getY());
		else
			currentStroke = null;

		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (image == null)
			return;

		if (e.getX() < image.getWidth() && e.getY() < image.getHeight()) {
			/* flipping */
			if (e.getClickCount() == 2) {
				if (front.isVisible()) {
					front.setVisible(false);
					back.setVisible(true);
				} else {
					front.setVisible(true);
					back.setVisible(false);
				}
				resetTextInput();
			}

			if (e.getClickCount() == 1) {
				this.requestFocusInWindow();
				/* Create new text */
				currentText = new TextNode(placeholder, e.getPoint());
				currentText.setColor(Color.RED);
				currentText.setMaxWidth(image.getWidth() - e.getX());
				currentText.setMaxHeight(image.getHeight() - e.getY());
				if (back.isVisible())
					back.add(currentText);
				else
					front.add(currentText);
			}
		} else
			resetTextInput();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		resetTextInput();
		if (image == null)
			return;
		startStroke(e);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		currentStroke = null;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (image == null || currentText == null)
			return;

		if (currentText.text == placeholder) {
			currentText.text = "";
		}
		currentText.text += e.getKeyChar();
		repaint();
	}
}