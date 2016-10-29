package SceneGraph;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class ImageNode extends Node {

	private Image aImage;
	private int left, top, right, bottom;
	
	public ImageNode(File fileImage) {
		try {
			aImage = ImageIO.read(fileImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		left = 0; //?
		top = 0; //?
		right = left + aImage.getWidth(null);
		bottom = top + aImage.getHeight(null);
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(aImage, left, top, null);
	}
	
	@Override
	public Rectangle getBounds() {
		int l = this.left, t = this.top, r = this.right, b = this.bottom;
		return new Rectangle(l, t, r, b);
	}

}
