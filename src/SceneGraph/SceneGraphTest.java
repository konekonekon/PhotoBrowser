package SceneGraph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SceneGraphTest extends JFrame {

	private static final long serialVersionUID = -8862184576856785540L;

	private Scene scene;

	public SceneGraphTest() {

		this.setTitle("Scene Graph Test");
		this.setMinimumSize(new Dimension(220, 150));
		this.setPreferredSize(new Dimension(600, 400));
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* test nodes */
		scene = new Scene();
		Node root = scene.getRoot();
		Node hello = new TextNode("Hello", new Point(10, 10));
		Node bye = new TextNode("Bye", new Point(10, 30));
		Node shape = new ShapeNode(new Ellipse2D.Double(50, 50, 50, 50));
		GeneralPath aPath = new GeneralPath();
		aPath.moveTo(20, 300);
		aPath.lineTo(100, 280);
		aPath.lineTo(150, 250);
		Node path = new PathNode(aPath);
		
		Node image = null;
		try {
			image = new ImageNode(ImageIO.read(new File("./photo.jpg")), 50, 50);
		} catch (IOException e) {
		}
		
		Node container = new ContainerNode();
		container.add(path);
		container.add(image);
		container.add(shape);
		container.add(hello);
		container.add(bye);
		
		root.setColor(Color.BLUE);
		root.add(container);
		/*root.add(path);
		if (image != null)
			root.add(image);
		root.add(shape);
		root.add(hello);
		root.add(bye);*/
		
		this.add(scene, BorderLayout.CENTER);
		this.pack();
	}

	public static void main(String[] args) {
		SceneGraphTest test = new SceneGraphTest();
		test.setVisible(true);
	}

}
