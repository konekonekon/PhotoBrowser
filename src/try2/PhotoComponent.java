package try2;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

import javax.swing.*;

public class PhotoComponent extends JComponent implements MouseListener, MouseMotionListener {

	private ArrayList<Point> currentLineInFront;
	private ArrayList<Point> currentLineInBack;
	private ArrayList<ArrayList<Point>> linesInFront;
	private ArrayList<ArrayList<Point>> linesInBack;
	private BufferedImage image;
	private boolean isFlipped;
	private int viewWidth, viewHeight, imgWidth, imgHeight;
	private int left, top, right, bottom;
	private double viewRatio;

	public PhotoComponent() {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		linesInFront = new ArrayList<ArrayList<Point>>();
		linesInBack = new ArrayList<ArrayList<Point>>();
		isFlipped = false;
		viewWidth = 0;
		viewHeight = 0;
		left = 0;
		top = 0;
		right = 0;
		bottom = 0;
		viewRatio = 1;
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
		
		if (image != null){

			this.updateView();
			
			if (isFlipped == false){
				//imgWidth = image.getWidth();
				//imgHeight = image.getHeight();
				
				g2.drawImage(image, left, top, right, bottom, 0, 0, imgWidth, imgHeight, null);
				//g2.drawImage(image, null, 0, 0);
				
				for (ArrayList<Point> line : linesInFront)
					this.drawStroke(line, g2);
				//put lines in transparent rectangle and resize it?
				//
			}
			if (isFlipped == true){
				g2.setColor(Color.WHITE);
				g2.fillRect(left, top, viewWidth, viewHeight);
				
				
				for (ArrayList<Point> line : linesInBack)
					this.drawStroke(line, g2);
			}			

		// text
		/*if (statusMouse == 1) {
			//System.out.println("Clicked");
			g2.drawString(str, p.x, p.y);
			statusMouse = 0;
		}*/
		}
	}

	private void updateView() {
		imgWidth = image.getWidth();
		imgHeight = image.getHeight();
		double imgRatio = (double) imgHeight / (double) imgWidth;

		int cvsWidth = this.getWidth();
		int cvsHeight = this.getHeight();
		double cvsRatio = (double) cvsHeight / (double) cvsWidth;
		
		if (imgWidth < cvsWidth && imgHeight < cvsHeight) {
			viewWidth = imgWidth;
			viewHeight = imgHeight;
			left = (cvsWidth - imgWidth) / 2;
			top = (cvsHeight - imgHeight) / 2;
			right = left + viewWidth;
			bottom = top + viewHeight;
		} 
		else {
			if (cvsRatio > imgRatio) {
				viewWidth = cvsWidth;
				viewHeight = (int) (viewWidth * imgRatio);
				left = 0;
				right = cvsWidth;
				top = (cvsHeight - viewHeight) / 2;
				bottom = top + viewHeight;
			} 
			else {
				viewHeight = cvsHeight;
				viewWidth = (int) (viewHeight / imgRatio);
				top = 0;
				bottom = cvsHeight;
				left = (cvsWidth - viewWidth) / 2;
				right = left + viewWidth;
			}
		}
		viewRatio = (double) viewHeight / (double) imgHeight;
	}
	 
	public void writeText(String string, Point p, Graphics2D g2) {

		g2.drawString(string, p.x, p.y);
	}

	public void drawStroke(ArrayList<Point> line, Graphics2D g2) {
		
		g2.setColor(Color.RED);
		int i = 0;
		while (i < line.size() - 1) {
			Point p1 = logicToPhysicPoint(line.get(i));
			Point p2 = logicToPhysicPoint(line.get(i + 1));
			
			if(p1.x > left && p1.x < right && p1.y > top && p1.y < bottom && p2.x > left && p2.x < right && p2.y > top && p2.y < bottom ){
				g2.drawLine(p1.x, p1.y, p2.x, p2.y);
				
			}
			i++;
		}
	}

	// TODO get what user type for "str"
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2){
			isFlipped = !isFlipped;
			repaint();
		}
		/*if (e.getClickCount() == 3){
			//statusMouse = 1;		 

			p = e.getPoint();
			str = "stg";
			// System.out.println("Clicked");
			repaint();
		}*/
	}

	public void mousePressed(MouseEvent e) {
		Point physicPoint = e.getPoint();
		Point viewPoint = physicToLogicPoint(physicPoint);
		
		if (image != null){
		// System.out.println("Pressed");
			if (isFlipped == true){
				currentLineInBack = new ArrayList<Point>();
				linesInBack.add(currentLineInBack);
				currentLineInBack.add(viewPoint);
			}
			else {
				currentLineInFront = new ArrayList<Point>();
				linesInFront.add(currentLineInFront);
				currentLineInFront.add(viewPoint);
			}
			repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		Point physicPoint = e.getPoint();
		Point viewPoint = physicToLogicPoint(physicPoint);

		if (image != null){
			if (isFlipped == true){
				currentLineInBack.add(viewPoint);
			}
			else{
				currentLineInFront.add(viewPoint);
			}
			repaint();
		}
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
		updateView();
		repaint();
	}
	
	public Point physicToLogicPoint(Point p){
		int logicX = (int) ((p.x - left) / viewRatio);
		int logicY = (int) ((p.y - top) / viewRatio);
		Point logicPoint = new Point(logicX, logicY);
		return logicPoint;
	}
	
	public Point logicToPhysicPoint(Point p){
		int physicX = (int) (p.x * viewRatio) + left;
		int physicY = (int) (p.y * viewRatio) + top;
		Point physicPoint = new Point(physicX, physicY);
		return physicPoint;
	}

}
