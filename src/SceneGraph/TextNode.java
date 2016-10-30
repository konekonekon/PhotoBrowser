package SceneGraph;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;


public class TextNode extends Node {

	public String text;
	private Point startPoint;
	private int maxWidth;
	private int maxHeight;

	public TextNode(String str, Point p) {
		text = str;
		startPoint = p;
	}

	@Override
	public Rectangle getBounds() {
		//TODO width, height
		return new Rectangle(startPoint.x, startPoint.y - 10, 10, 10);
	}
	
	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}
	
	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	@Override
	protected void paintNode(Graphics2D g2) {
		AttributedString attributedString = new AttributedString(this.text);

		float drawPosY = (float) startPoint.y;
		float breakWidth = (float) maxWidth;

		AttributedCharacterIterator paragraph = attributedString.getIterator();
		FontRenderContext frc = g2.getFontRenderContext();
		LineBreakMeasurer measurer = new LineBreakMeasurer(paragraph, frc);
		if (breakWidth > 0) {
			while (measurer.getPosition() < paragraph.getEndIndex()
					&& drawPosY < startPoint.y + maxHeight) {

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
