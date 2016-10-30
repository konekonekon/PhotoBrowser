package SceneGraph;

import java.awt.*;

public class ImageNode extends Node {

	private Image image;
	//private int left, top;
	private int left, top, right, bottom, imgWidth, imgHeight;
	
	public ImageNode(Image img, int x, int y) {
		image = img;
		left = x;
		top = y;
		imgWidth = image.getWidth(null);
		imgHeight = image.getHeight(null);
		right = left + imgWidth;
		bottom = top + imgHeight;

	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(left, top, image.getWidth(null), image.getHeight(null));
	}
	
	@Override
	protected void paintNode(Graphics2D g2) {
		//g2.drawImage(image, left, top, null);
		g2.drawImage(image, left, top, right, bottom, 0, 0, imgWidth, imgHeight, null);

	}

}
