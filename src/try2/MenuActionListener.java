package try2;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuActionListener implements ActionListener {

	private JLabel status;
	private Menu menu;
	private BufferedImage img;
	private PhotoComponent pc;
	
	MenuActionListener(Menu menu, JLabel status, PhotoComponent pc) {
		this.status = status;
		this.menu = menu;
		this.pc = pc;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == menu.importMI){
			status.setText("You click 'Import'.");
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Images", "jpg", "jpeg", "png", "gif", "JPG", "JPEG", "PNG", "GIF");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.addChoosableFileFilter(filter);
			fileChooser.setMultiSelectionEnabled(true);
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); //user.dir
			Component parent = null;
			int returnVal = fileChooser.showDialog(parent, "Choose a file"); //parent??
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File file = fileChooser.getSelectedFile();
				//System.out.println("You chose " + listFiles.length + " photos.");
				String path = file.getAbsolutePath();
				System.out.println(path);
				
				try {
					img = ImageIO.read(new File(path));
					this.pc.setImage(img);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		if(e.getSource() == menu.delete){
			status.setText("You click 'Delete'.");
		}
		
		if(e.getSource() == menu.quit){
			System.exit(0);
		}
		
		if(e.getSource() == menu.pv){
			status.setText("You choose 'Photo Viewer'.");
			
		}
		
		if(e.getSource() == menu.browser){
			status.setText("You choose 'Browser'.");
		}
		
		if(e.getSource() == menu.split){
			status.setText("You choose 'Split mode'.");
			
		}		
	}
}
