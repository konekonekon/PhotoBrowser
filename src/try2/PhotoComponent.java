package try2;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JComponent;

public class PhotoComponent extends JComponent implements MouseListener, MouseMotionListener {

	private Color color;
	private ArrayList<Point> currentLine;
	private ArrayList<ArrayList<Point>> lines;
	private String str;
	private Point p;
	private int statusMouse = 0;

	public PhotoComponent(){
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		lines = new ArrayList<ArrayList<Point>>();
		p = new Point();
		str = "";
	}
	
	//TODO : color, thickness for line/text
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(color.RED);

		if (statusMouse == 2){
			System.out.println("Dragged");
			for (ArrayList<Point> line : lines)
	        	drawing(line, g2);
			statusMouse = 0;
		}
		if (statusMouse == 1){
			System.out.println("Clicked");
			g2.drawString(str, p.x, p.y);
			statusMouse = 0;
		}
		
		//writing(str, p, g2);

/*		if(currentLine != null)
			drawing(currentLine, g2);*/
/*        for (ArrayList<Point> line : lines)
        	drawing(line, g2);*/
	}
	
	public void writing(String string, Point p, Graphics2D g2){
		
		g2.drawString(string, p.x, p.y);
	}
	
	public void drawing(ArrayList<Point> line, Graphics2D g2){
		int i = 0;
		while(i < line.size()-1){
			Point p1 = line.get(i);
			Point p2 = line.get(i+1);
			g2.drawLine(p1.x, p1.y, p2.x, p2.y);
			i++;
		}
	}
//TODO get what user type for "str"
	public void mouseClicked(MouseEvent e) {
		statusMouse = 1;
/*		p = e.getPoint();
		str = "";
		repaint();*/
		
		p = e.getPoint();
		str = "stg";
		//System.out.println("Clicked");
		repaint();
		
	}

	public void mousePressed(MouseEvent e) {
		
		//System.out.println("Pressed");
		currentLine = new ArrayList<Point>();
		lines.add(currentLine);
	    currentLine.add(e.getPoint());
		repaint();
	}

	public void mouseReleased(MouseEvent e) {

	}
	
	public void mouseDragged(MouseEvent e) {
		statusMouse = 2;
	    currentLine.add(e.getPoint());
	    repaint();
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}


}
