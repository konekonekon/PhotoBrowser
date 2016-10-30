package SceneGraph;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.swing.UIManager;

public class TextNode extends Node {

	public String text;
	private Point startPoint;
	private int maxWidth;

	public TextNode(String str, Point p) {
		text = str;
		startPoint = p;
	}

	@Override
	public Rectangle getBounds() {
		//TODO width, height
		return new Rectangle(startPoint.x, startPoint.y - 10, 10, 10);
	}
	
	@Override
	protected void paintNode(Graphics2D g2) {
		// g2.drawString(text, startPoint.x, startPoint.y);
		
		AttributedString attributedString = new AttributedString(this.text);

		float drawPosY = (float) startPoint.y;
		float breakWidth = (float) (this.getParent().getBounds().width - startPoint.x);

		AttributedCharacterIterator paragraph = attributedString.getIterator();
		FontRenderContext frc = g2.getFontRenderContext();
		LineBreakMeasurer measurer = new LineBreakMeasurer(paragraph, frc);
		if (breakWidth > 0) {
			while (measurer.getPosition() < paragraph.getEndIndex()
					&& drawPosY < this.getParent().getBounds().height) {

				TextLayout textLayout = measurer.nextLayout(breakWidth);
				float drawPosX = textLayout.isLeftToRight() ? startPoint.x : breakWidth
						- textLayout.getAdvance();
				textLayout.draw(g2, drawPosX, drawPosY);
				drawPosY += textLayout.getAscent();
				drawPosY += textLayout.getDescent() + textLayout.getLeading();
			}
		}
	}
}
