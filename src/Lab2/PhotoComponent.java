package Lab2;

import java.awt.image.BufferedImage;

import SceneGraph.Scene;

public class PhotoComponent extends Scene {

	private BufferedImage image;

	public PhotoComponent() {	
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
