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
	/*private String str;
	private Point p;
	private int statusMouse = 0;*/
	private BufferedImage image;
	private boolean isFlipped;
	private int viewWidth, viewHeight, imgWidth, imgHeight;
	private int x1, y1, x2, y2;
	JLabel canvasToDraw;


	public PhotoComponent() {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		linesInFront = new ArrayList<ArrayList<Point>>();
		linesInBack = new ArrayList<ArrayList<Point>>();
		/*p = new Point();
		str = "";*/
		isFlipped = false;
		viewWidth = 0;
		viewHeight = 0;
		x1 = 0;
		y1 = 0;
		x2 = 0;
		y2 = 0;
		canvasToDraw = new JLabel();
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
		

/*		if (image != null){
			this.displayImage(g2);
		}*/
		
		if (image != null){

			this.updateView();
			
			if (isFlipped == false){
				//imgWidth = image.getWidth();
				//imgHeight = image.getHeight();
				g2.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
				//g2.drawImage(image, null, 0, 0);
				
				for (ArrayList<Point> line : linesInFront)
					this.drawStroke(line, g2);
				//put lines in transparent rectangle and resize it?
				//
			}
			if (isFlipped == true){
				g2.setColor(Color.WHITE);
				g2.fillRect(x1, y1, viewWidth, viewHeight);
				
				
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
		
		double viewRatio = 0;
		
		if (imgWidth < cvsWidth && imgHeight < cvsHeight) {
			viewWidth = imgWidth;
			viewHeight = imgHeight;
			x1 = (cvsWidth - imgWidth) / 2;
			y1 = (cvsHeight - imgHeight) / 2;
			x2 = x1 + viewWidth;
			y2 = y1 + viewHeight;
		} 
		else {
			if (cvsRatio > imgRatio) {
				viewWidth = cvsWidth;
				viewHeight = (int) (viewWidth * imgRatio);
				x1 = 0;
				x2 = cvsWidth;
				y1 = (cvsHeight - viewHeight) / 2;
				y2 = y1 + viewHeight;
				
				viewRatio = (double)viewHeight / (double)viewWidth;
			} 
			else {
				viewHeight = cvsHeight;
				viewWidth = (int) (viewHeight / imgRatio);
				y1 = 0;
				y2 = cvsHeight;
				x1 = (cvsWidth - viewWidth) / 2;
				x2 = x1 + viewWidth;
				
				viewRatio = (double)viewHeight / (double)viewWidth;
			}
		}
	}
	 
	public void writeText(String string, Point p, Graphics2D g2) {

		g2.drawString(string, p.x, p.y);
	}

	public void drawStroke(ArrayList<Point> line, Graphics2D g2) {
		
		g2.setColor(Color.RED);
		int i = 0;
		while (i < line.size() - 1) {
			Point p1 = line.get(i);
			Point p2 = line.get(i + 1);
			//to do??
			if(p1.x > x1 && p1.x < x2 && p1.y > y1 && p1.y < y2 && p2.x > x1 && p2.x < x2 && p2.y > y1 && p2.y < y2 ){
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
		if (image != null){
		// System.out.println("Pressed");
			if (isFlipped == true){
				currentLineInBack = new ArrayList<Point>();
				linesInBack.add(currentLineInBack);
				currentLineInBack.add(e.getPoint());
			}
			else {
				currentLineInFront = new ArrayList<Point>();
				linesInFront.add(currentLineInFront);
				currentLineInFront.add(e.getPoint());
			}
			repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		//statusMouse = 2;
		if (image != null){
			if (isFlipped == true){
				currentLineInBack.add(e.getPoint());
			}
			else{
				currentLineInFront.add(e.getPoint());
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
		repaint();
	}

}
