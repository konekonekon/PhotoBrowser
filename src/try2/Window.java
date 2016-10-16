package try2;

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

public class Window extends JFrame implements ActionPerformer {
	
	private Menu menu;
	private ToolBar toolBar;
	private JLabel label;
	private PhotoComponent pc;
	private JScrollPane sp;
	private File file;
	
	
	public Window(){
		this.setTitle("Photo Browser");
		this.setMinimumSize(new Dimension(220,150));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screenSize.getWidth();
		int screenHeight = (int)screenSize.getHeight();
		int preferredWidth = (int)(screenWidth *0.7);
		int preferredHeight = (int)(screenHeight *0.7);
		this.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
		int preferredX = (screenWidth - preferredWidth) /2;
		int preferredY = (screenHeight - preferredHeight) /2;
		this.setLocation(preferredX, preferredY);
		this.setMaximizedBounds(new Rectangle(screenWidth, screenHeight));
		this.setResizable(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		label = new JLabel();
		label.setText("Status bar");
		pc = new PhotoComponent();
		toolBar = new ToolBar(label);
		sp = new JScrollPane(pc);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.getVerticalScrollBar().setUnitIncrement(10);
		sp.getHorizontalScrollBar().setUnitIncrement(10);
		menu = new Menu(this);
		
		this.setJMenuBar(menu);	
		this.add(toolBar, BorderLayout.NORTH);
		this.add(label, BorderLayout.SOUTH);
		//this.add(pc, BorderLayout.CENTER);
		//this.add(sp, BorderLayout.CENTER);

		this.pack();
		this.setVisible(true);
	}

	public void importFile() {
		label.setText("You click 'Import'.");
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Images", "jpg", "jpeg", "png", "gif", "JPG", "JPEG", "PNG", "GIF");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		Component parent = null;
		int returnVal = fileChooser.showDialog(parent, "Choose a file");
		if(returnVal == JFileChooser.APPROVE_OPTION){
			file = fileChooser.getSelectedFile();
			label.setText("You imported " + file);
			String path = file.getAbsolutePath();
			
			try {
				BufferedImage img = ImageIO.read(new File(path));
				this.add(sp, BorderLayout.CENTER);
				this.pc.setImage(img);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void delete() {
		this.pc.deleteImage();
		label.setText("You delete " + file);
	}

	public void quit() {
		System.exit(0);
	}
	
	
	
}
