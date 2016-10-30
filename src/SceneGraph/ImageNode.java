package SceneGraph;

import java.awt.*;

public class ImageNode extends Node {

	private Image image;
	private int left, top;

	public ImageNode(Image img, int x, int y) {
		image = img;
		left = x;
		top = y;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(left, top, image.getWidth(null),
				image.getHeight(null));
	}

	@Override
	protected void paintNode(Graphics2D g2) {
		g2.drawImage(image, left, top, null);
	}
}