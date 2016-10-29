package SceneGraph;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

		scene = new Scene();
		this.add(scene, BorderLayout.CENTER);

		this.pack();
	}

	public static void main(String[] args) {
		SceneGraphTest test = new SceneGraphTest();
		test.setVisible(true);
	}

}
