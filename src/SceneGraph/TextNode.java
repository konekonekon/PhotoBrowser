package SceneGraph;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import javax.swing.UIManager;
import javax.xml.soap.Text;

public class TextNode extends Node {
	//private final static String placeholder = "Insert text";
	private String text;
	private Point startPoint;
	private ArrayList<TextNode> texts;
	
	public TextNode(String str, Point p){
		text = str;
		startPoint = p;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawString(text, startPoint.x, startPoint.y);
		
		
		//ArrayList<TextNode> textsToDiaplay;
		
		/*if (isFlipped == true)
			texts = textsInBack;
		else
			texts = textsInFront;*/
		/*textsToDiaplay = texts;
		
		for (TextNode aText : textsToDiaplay) {
			Point p = logicToPhysicPoint(aText.startPoint);
			if (aText.text.length() > 0) {
				AttributedString attributedString = new AttributedString(aText.text);
				attributedString.addAttribute(TextAttribute.FONT, (Font) UIManager.get("Label.font"));
				Color color = (Color) UIManager.get("Label.foreground");
				attributedString.addAttribute(TextAttribute.FOREGROUND, color);
				
				float drawPosY = (float) p.y;
				float breakWidth = (float) (right - p.x);
				
				AttributedCharacterIterator paragraph = attributedString.getIterator();
				FontRenderContext frc = g2d.getFontRenderContext();
				LineBreakMeasurer measurer = new LineBreakMeasurer(paragraph, frc);
					if (breakWidth > 0){
						while (measurer.getPosition() < paragraph.getEndIndex() && drawPosY < bottom) {
						
							TextLayout textLayout = measurer.nextLayout(breakWidth);
							float drawPosX = textLayout.isLeftToRight() ? p.x
									: breakWidth - textLayout.getAdvance();
							textLayout.draw(g2d, drawPosX, drawPosY);
							drawPosY += textLayout.getAscent();
							drawPosY += textLayout.getDescent() + textLayout.getLeading();
						}
					}
				}
				
		} //Simple one click
		if (currentText != null) {
			Point p2 = logicToPhysicPoint(currentText.p);
			g2d.setColor(Color.red);
			if (currentText.text == "") {
				g2d.drawString(placeholder, p2.x, p2.y);
			}
		}*/
	}
}
