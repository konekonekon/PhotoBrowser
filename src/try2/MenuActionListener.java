package try2;

import java.awt.Component;
import java.awt.event.*;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuActionListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == Menu.importMI){
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Images", "jpg", "jpeg", "png", "gif", "JPG", "JPEG", "PNG", "GIF");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.addChoosableFileFilter(filter);
			fileChooser.setMultiSelectionEnabled(true);
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); //user.dir
			Component parent = null;
			int returnVal = fileChooser.showDialog(parent, "Choose a file"); //parent??
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File[] listFiles = fileChooser.getSelectedFiles();
				System.out.println("You chose " + listFiles.length + " photos.");
			}
		}
		
		if(e.getSource() == Menu.delete){
			// to do
			
		}
		
		if(e.getSource() == Menu.quit){
			System.exit(0);
		}
		
		
	}
}
