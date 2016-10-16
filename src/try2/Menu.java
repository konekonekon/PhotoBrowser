package try2;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;

public class Menu extends JMenuBar {
	
	JMenu fileMenu, viewMenu;
	JMenuItem importFile;
	JMenuItem delete;
	JMenuItem quit;
	JRadioButtonMenuItem pv;
	JRadioButtonMenuItem browser;
	JRadioButtonMenuItem split;
	//ActionPerformer ap;
	
	public Menu(ActionPerformer ap){
		//File Menu
		fileMenu = new JMenu("File");
		this.add(fileMenu);
		MenuActionListener listener = new MenuActionListener(this, ap);
		
		//File menuItems
		importFile = new JMenuItem("Import");
		importFile.addActionListener(listener);
		fileMenu.add(importFile);
		
		delete = new JMenuItem("Delete");
		delete.addActionListener(listener);
		fileMenu.add(delete);
		
		quit = new JMenuItem("Quit");
		quit.addActionListener(listener);
		fileMenu.add(quit);
		
		//View Menu
		viewMenu = new JMenu("View");
		this.add(viewMenu);
		ButtonGroup buttonGroup = new ButtonGroup();
		
		//View Menu Items
		pv = new JRadioButtonMenuItem("Photo viewer");
		pv.setSelected(true);
		buttonGroup.add(pv);
		pv.addActionListener(listener);
		viewMenu.add(pv);
		
		browser = new JRadioButtonMenuItem("Browser");
		buttonGroup.add(browser);
		browser.addActionListener(listener);
		viewMenu.add(browser);
		
		split = new JRadioButtonMenuItem("Split mode");
		buttonGroup.add(split);
		split.addActionListener(listener);
		viewMenu.add(split);
	}

}
