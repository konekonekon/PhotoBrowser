package try2;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.image.*;
import java.text.*;
import java.util.*;

import javax.swing.*;

public class PhotoComponent extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

	private final static String placeholder = "Insert text";
	private ArrayList<Point> currentLine;
	private ArrayList<ArrayList<Point>> linesInFront;
	private ArrayList<ArrayList<Point>> linesInBack;
	private BufferedImage image;
	private boolean isFlipped;
	private int viewWidth, viewHeight, imgWidth, imgHeight;
	private int left, top, right, bottom;
	private double viewRatio;
	private Text currentText;
	private ArrayList<Text> textsInFront;
	private ArrayList<Text> textsInBack;
	
	public PhotoComponent() {
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
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
		this.setFocusable(true);
		textsInFront = new ArrayList<Text>();
		textsInBack = new ArrayList<Text>();
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
				
				this.writeText(currentText, g2);
				
			}
			if (isFlipped == true){
				g2.setColor(Color.WHITE);
				g2.fillRect(left, top, viewWidth, viewHeight);
				
				for (ArrayList<Point> line : linesInBack)
					this.drawStroke(line, g2);
				
				this.writeText(currentText, g2);
			
			}
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
	
	public void writeText(Text currentT, Graphics2D g2) {
		if (isFlipped == true) {
			for (Text aText : textsInBack)
				g2.drawString(aText.text, aText.p.x, aText.p.y);
		} else {
			for (Text aText : textsInFront)
				g2.drawString(aText.text, aText.p.x, aText.p.y);
		}

		if (currentText != null) {
			g2.setColor(Color.red);
			if (currentText.text == "") {
				g2.drawString(placeholder, currentText.p.x, currentText.p.y);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (image != null){
			if (e.getClickCount() == 2){
				isFlipped = !isFlipped;
				currentText = null;
			}
			if (e.getClickCount() == 1){
				this.requestFocusInWindow();
				currentText = new Text("", e.getPoint());
				if (isFlipped == true){
		 			textsInBack.add(currentText);
				}
				else {
		 			textsInFront.add(currentText);
				}
			}
		}
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		Point physicPoint = e.getPoint();
		Point viewPoint = physicToLogicPoint(physicPoint);
		
		if (image != null){
			if (isFlipped == true){
				currentLine = new ArrayList<Point>();
				linesInBack.add(currentLine);
				currentLine.add(viewPoint);
			}
			else {
				currentLine = new ArrayList<Point>();
				linesInFront.add(currentLine);
				currentLine.add(viewPoint);
			}
			repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {
		Point physicPoint = e.getPoint();
		Point viewPoint = physicToLogicPoint(physicPoint);

		if (image != null){
			if (isFlipped == true){
				currentLine.add(viewPoint);
			}
			else{
				currentLine.add(viewPoint);
			}
			repaint();
		}
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {}
	
	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {
		if (currentText != null){
			currentText.text += e.getKeyChar();
			repaint();
		}
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