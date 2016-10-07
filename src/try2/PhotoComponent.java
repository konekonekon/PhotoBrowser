package try2;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.*;

import javax.swing.*;

public class PhotoComponent extends JComponent implements MouseListener,
		MouseMotionListener {

	private Color color;
	private ArrayList<Point> currentLine;
	private ArrayList<ArrayList<Point>> lines;
	private String str;
	private Point p;
	private int statusMouse = 0;
	private BufferedImage image;

	public PhotoComponent() {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		lines = new ArrayList<ArrayList<Point>>();
		p = new Point();
		str = "";
	}

	// TODO : color, thickness for line/text
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.gray);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(color.RED);

		if (image != null) {
			this.displayImage(g2);
		}		

		if (statusMouse == 2) {
			System.out.println("Dragged");
			for (ArrayList<Point> line : lines)
				drawStroke(line, g2);
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
		double imgRatio = (double) imgHeight / imgWidth;
		System.out.println("Image size : " + imgWidth + ", " + imgHeight);

		int cvsWidth = this.getWidth();
		int cvsHeight = this.getHeight();
		double cvsRatio = (double) cvsHeight / cvsWidth;
		System.out.println("Canvas size : " + cvsWidth + ", " + cvsHeight);

		//start(x1,y1), end(x2,y2)
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		
		if (imgWidth < cvsWidth && imgHeight < cvsHeight) {
			x1 = (cvsWidth - imgWidth) / 2;
			y1 = (cvsHeight - imgHeight) / 2;
			x2 = imgWidth + x1;
			y2 = imgHeight + y1;
		} 
		else {
			if (cvsRatio > imgRatio) {
				y1 = cvsHeight;
				cvsHeight = (int) (cvsWidth * imgRatio);
				y1 = (y1 - cvsHeight) / 2;
			} 
			else {
				x1 = cvsWidth;
				cvsWidth = (int) (cvsHeight / imgRatio);
				x1 = (x1 - cvsWidth) / 2;
			}
			x2 = cvsWidth + x1;
			y2 = cvsHeight + y1;
		}
		g2.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
	}
	 

	public void typingText(String string, Point p, Graphics2D g2) {

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
		statusMouse = 1;
		/*
		 * p = e.getPoint(); str = ""; repaint();
		 */

		p = e.getPoint();
		str = "stg";
		// System.out.println("Clicked");
		repaint();

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
