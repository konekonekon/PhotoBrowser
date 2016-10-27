package SceneGraph;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class ImageNode extends Node {

	private Image aImage;
	private int left, top;
	
	public ImageNode(File fileImage) {
		try {
			aImage = ImageIO.read(fileImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(aImage, left, top, null);
	}

}
