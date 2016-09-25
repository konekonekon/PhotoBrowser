package try2;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JComponent;

public class PhotoComponent extends JComponent implements MouseListener, MouseMotionListener {

	private Color color;
	private ArrayList<Point> currentLine;
	private ArrayList<ArrayList<Point>> lines;

	public PhotoComponent(){
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		lines = new ArrayList<ArrayList<Point>>();
	}
	
	//draw stg, write text
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(color.RED);

/*		if(currentLine != null)
			drawLine(currentLine, g2);*/
		
        for (ArrayList<Point> line : lines)
        	drawLine(line, g2);
	}
	
	public void drawLine(ArrayList<Point> line, Graphics2D g2){
		int i = 0;
		while(i < line.size()-1){
			Point p1 = line.get(i);
			Point p2 = line.get(i+1);
			g2.drawLine(p1.x, p1.y, p2.x, p2.y);
			i++;
		}
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		currentLine = new ArrayList<Point>();
		lines.add(currentLine);
	    currentLine.add(e.getPoint());
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
	    currentLine.add(e.getPoint());
	    //lines.add(currentLine);
		repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
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
