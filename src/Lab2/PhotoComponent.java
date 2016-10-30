package Lab2;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import SceneGraph.*;

public class PhotoComponent extends Scene {

	private BufferedImage image;
	private ImageNode backgroundNode;

	public PhotoComponent() {
		reset();
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
		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		repaint();
	}

}
