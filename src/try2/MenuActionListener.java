package try2;

import java.awt.Component;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuActionListener implements ActionListener {

	private JLabel status;
	private Menu menu;
	ActionPerformer ap;
	
	MenuActionListener(Menu menu, ActionPerformer ap) {
		this.menu = menu;
		this.ap = ap;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == menu.importFile){
			this.ap.importFile();
			
			/*status.setText("You click 'Import'.");
			
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
				status.setText("You imported " + file);
				String path = file.getAbsolutePath();
				
				try {
					img = ImageIO.read(new File(path));
					this.pc.setImage(img);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}*/
		}
		
		if(e.getSource() == menu.delete){
			this.ap.delete();
			/*this.pc.deleteImage();
			status.setText("You delete " + file);*/
		}
		
		if(e.getSource() == menu.quit){
			this.ap.quit();
			//System.exit(0);
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
