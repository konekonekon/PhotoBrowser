package Lab2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class Menu extends JMenuBar implements ActionListener {
	
	private JMenu fileMenu, viewMenu;
	private JMenuItem importFile, delete, quit;
	private JRadioButtonMenuItem photoViewer, browser, split;
	private Performer performer;
	
	public Menu(Performer performer){
		this.performer = performer;
		
		//File Menu
		fileMenu = new JMenu("File");
		this.add(fileMenu);
		
		//File menuItems
		importFile = new JMenuItem("Import");
		importFile.addActionListener(this);
		fileMenu.add(importFile);
		
		delete = new JMenuItem("Delete");
		delete.addActionListener(this);
		fileMenu.add(delete);
		
		quit = new JMenuItem("Quit");
		quit.addActionListener(this);
		fileMenu.add(quit);
		
		//View Menu
		viewMenu = new JMenu("View");
		this.add(viewMenu);
		ButtonGroup buttonGroup = new ButtonGroup();
		
		//View Menu Items
		photoViewer = new JRadioButtonMenuItem("Photo viewer");
		photoViewer.setSelected(true);
		buttonGroup.add(photoViewer);
		photoViewer.addActionListener(this);
		viewMenu.add(photoViewer);
		
		browser = new JRadioButtonMenuItem("Browser");
		buttonGroup.add(browser);
		browser.addActionListener(this);
		viewMenu.add(browser);
		
		split = new JRadioButtonMenuItem("Split mode");
		buttonGroup.add(split);
		split.addActionListener(this);
		viewMenu.add(split);
	}
	
	/*** Call Performer section in class Window ***/
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.importFile){
			this.performer.importFile();
		}
		
		if(e.getSource() == this.delete){
			this.performer.delete();
		}
		
		if(e.getSource() == this.quit){
			this.performer.quit();
		}
		
		if(e.getSource() == this.photoViewer){
			this.performer.photoViewer();
		}
		
		if(e.getSource() == this.browser){
			this.performer.browser();
		}
		
		if(e.getSource() == this.split){
			this.performer.split();			
		}		
	}
}
