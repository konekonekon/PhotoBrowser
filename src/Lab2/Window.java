package Lab2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Window extends JFrame implements Performer {
	
	private Menu menu;
	private ToolBar toolBar;
	private JLabel label;
	private PhotoComponent photoComponent;
	private JScrollPane scrollPane;
	private File file;
	
	public Window(){
		this.setTitle("Photo Browser");
		this.setMinimumSize(new Dimension(220,150));
		/* Center this window in screen */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screenSize.getWidth();
		int screenHeight = (int)screenSize.getHeight();
		int preferredWidth = (int)(screenWidth *0.7);
		int preferredHeight = (int)(screenHeight *0.7);
		this.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
		int preferredX = (screenWidth - preferredWidth) /2;
		int preferredY = (screenHeight - preferredHeight) /2;
		this.setLocation(preferredX, preferredY);
		/* Set maximum size to screen size */
		this.setMaximizedBounds(new Rectangle(screenWidth, screenHeight));
		this.setResizable(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menu = new Menu(this);
		label = new JLabel();
		toolBar = new ToolBar(label);
		photoComponent = new PhotoComponent();
		scrollPane = new JScrollPane(photoComponent);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		
		this.setJMenuBar(menu);	
		this.add(toolBar, BorderLayout.NORTH);
		this.add(label, BorderLayout.SOUTH);

		this.pack();
		this.setVisible(true);
	}

	/* Performer section, called from class Menu */
	@Override
	public void importFile() {
		label.setText("Clicked 'Import'.");
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Images", "jpg", "jpeg", "png", "gif", "JPG", "JPEG", "PNG", "GIF");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(filter);
		//fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		Component parent = null;
		int returnVal = fileChooser.showDialog(parent, "Choose a file");
		if(returnVal == JFileChooser.APPROVE_OPTION){
			file = fileChooser.getSelectedFile();
			label.setText("Imported " + file);
			String path = file.getAbsolutePath();
			
			try {
				this.photoComponent.initializeComponent();
				BufferedImage img = ImageIO.read(new File(path));
				this.add(scrollPane, BorderLayout.CENTER);
				this.photoComponent.setImage(img);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void delete() {
		this.remove(scrollPane);
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
	public void undo() {
		
	}

	@Override
	public void redo() {
		
	}
	
}
