package try2;

import java.awt.event.*;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class Menu extends JMenuBar {
	
	JMenu fileMenu, viewMenu;
	static JMenuItem importMI;
	static JMenuItem delete;
	static JMenuItem quit;
	JRadioButtonMenuItem pv;
	JRadioButtonMenuItem browser;
	JRadioButtonMenuItem split;
	
	public Menu(){
		//File Menu
		fileMenu = new JMenu("File");
		//menu.setMnemonic(KeyEvent.VK_A);
		this.add(fileMenu);
		
		//File menuItems
		importMI = new JMenuItem("Import");
		importMI.addActionListener(new MenuActionListener());
		fileMenu.add(importMI);
		
		delete = new JMenuItem("Delete");
		delete.addActionListener(new MenuActionListener());
		fileMenu.add(delete);
		
		quit = new JMenuItem("Quit");
		quit.addActionListener(new MenuActionListener());
		fileMenu.add(quit);
		
		//View Menu
		viewMenu = new JMenu("View");
		this.add(viewMenu);
		ButtonGroup buttonGroup = new ButtonGroup();
		
		//View Menu Items
		pv = new JRadioButtonMenuItem("Photo viewer");
		pv.setSelected(true);
		buttonGroup.add(pv);
		viewMenu.add(pv);
		
		browser = new JRadioButtonMenuItem("Browser");
		buttonGroup.add(browser);
		viewMenu.add(browser);
		
		split = new JRadioButtonMenuItem("Split mode");
		buttonGroup.add(split);
		viewMenu.add(split);
	}

}
