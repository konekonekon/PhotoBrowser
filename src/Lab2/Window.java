package Lab2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Window extends JFrame implements Performer {

	private static final long serialVersionUID = 1L;
	private Menu menu;
	private ToolBar toolBar;
	private JLabel label;
	private PhotoComponent photoComponent;
	private File file;

	public Window() {
		this.setTitle("Photo Browser Scalable");
		this.setPreferredSize(new Dimension(700, 500));
		this.setMinimumSize(new Dimension(250, 200));
		this.setLayout(new BorderLayout());

		menu = new Menu(this);
		label = new JLabel();
		toolBar = new ToolBar(this);
		photoComponent = new PhotoComponent();

		this.setJMenuBar(menu);
		this.add(toolBar, BorderLayout.NORTH);
		this.add(label, BorderLayout.SOUTH);
		this.pack();
	}

	/* Performer section, called from Menu/ToolBar classes */
	@Override
	public void importFile() {
		label.setText("Clicked 'Import'.");

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images",
				"jpg", "jpeg", "png", "gif", "JPG", "JPEG", "PNG", "GIF");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setCurrentDirectory(new File(System
				.getProperty("user.home")));
		Component parent = null;
		int returnVal = fileChooser.showDialog(parent, "Choose a file");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			label.setText("Imported " + file);
			String path = file.getAbsolutePath();

			try {
				this.photoComponent.reset();
				BufferedImage img = ImageIO.read(new File(path));
				this.add(photoComponent, BorderLayout.CENTER);
				this.photoComponent.setImage(img);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void delete() {
		this.remove(photoComponent);
		label.setText("Deleted " + file);
	}

	@Override
	public void quit() {
		System.exit(0);
	}

	@Override
	public void photoViewer() {
		label.setText("Chose 'PhotoViewer mode'.");
	}

	@Override
	public void browser() {
		label.setText("Choose 'Browser mode'.");
	}

	@Override
	public void split() {
		label.setText("Choose 'Split mode'.");
	}

	@Override
	public void family() {
		label.setText("'family' photos.");
	}

	@Override
	public void vacation() {
		label.setText("'Vacation' photos.");
	}

	@Override
	public void school() {
		label.setText("'School' photos.");
	}
}