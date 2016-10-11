package try2;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

import javax.swing.*;

public class PhotoComponent extends JComponent implements MouseListener, MouseMotionListener {

	private ArrayList<Point> currentLine;
	private ArrayList<ArrayList<Point>> lines;
	private String str;
	private Point p;
	private int statusMouse = 0;
	private BufferedImage image;
	private boolean isFlipped;


	public PhotoComponent() {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		lines = new ArrayList<ArrayList<Point>>();
		p = new Point();
		str = "";
		isFlipped = false;
	}

	// TODO : color, thickness for line/text
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		RenderingHints rh = g2.getRenderingHints ();
	    rh.put (RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHints (rh);
	    
		g2.setColor(Color.gray);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.RED);

		if (image != null){
			this.displayImage(g2);
		}
		
		//to do
		if (statusMouse == 2) {
			System.out.println("Dragged");
			for (ArrayList<Point> line : lines)
				this.drawStroke(line, g2);
			statusMouse = 0;
		}// text
		if (statusMouse == 1) {
			System.out.println("Clicked");
			g2.drawString(str, p.x, p.y);
			statusMouse = 0;
		}

		// writing(str, p, g2);

		/*
		 * if(currentLine != null) 
		 * 	drawing(currentLine, g2);
		 *
		 * for (ArrayList<Point> line : lines) drawing(line, g2);
		 */
	}

	
	private void displayImage(Graphics2D g2) {
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		double imgRatio = (double) imgHeight / (double) imgWidth;

		int cvsWidth = this.getWidth();
		int cvsHeight = this.getHeight();
		double cvsRatio = (double) cvsHeight / (double) cvsWidth;
		//System.out.println("Canvas size : " + cvsWidth + ", " + cvsHeight);
		
		int viewWidth = 0;
		int viewHeight = 0;
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		
		if (imgWidth < cvsWidth && imgHeight < cvsHeight) {
			x1 = (cvsWidth - imgWidth) / 2;
			y1 = (cvsHeight - imgHeight) / 2;
			x2 = x1 + imgWidth;
			y2 = y1 + imgHeight;
		} 
		else {
			if (cvsRatio > imgRatio) {
				viewWidth = cvsWidth;
				viewHeight = (int) (viewWidth * imgRatio);
				x1 = 0;
				x2 = cvsWidth;
				y1 = (cvsHeight - viewHeight) / 2;
				y2 = y1 + viewHeight;
			} 
			else {
				viewHeight = cvsHeight;
				viewWidth = (int) (viewHeight / imgRatio);
				y1 = 0;
				y2 = cvsHeight;
				x1 = (cvsWidth - viewWidth) / 2;
				x2 = x1 + viewWidth;
			}
		}
		if (isFlipped == false){
			g2.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
		}
		if (isFlipped == true){
			g2.setColor(Color.white);
			g2.fillRect(x1, y1, viewWidth, viewHeight);
		}
	}
	 
	public void writeText(String string, Point p, Graphics2D g2) {

		g2.drawString(string, p.x, p.y);
	}

	public void drawStroke(ArrayList<Point> line, Graphics2D g2) {
		int i = 0;
		while (i < line.size() - 1) {
			Point p1 = line.get(i);
			Point p2 = line.get(i + 1);
			g2.drawLine(p1.x, p1.y, p2.x, p2.y);
			i++;
		}
	}

	// TODO get what user type for "str"
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2){
			isFlipped = !isFlipped;
			repaint();
		}
		if (e.getClickCount() == 1){
			statusMouse = 1;
		/*
		 * p = e.getPoint(); str = ""; repaint();
		 */

		p = e.getPoint();
		str = "stg";
		// System.out.println("Clicked");
		repaint();
		}
	}

	public void mousePressed(MouseEvent e) {

		// System.out.println("Pressed");
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

	public Image getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}

}
