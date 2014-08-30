package visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

import lib.two_dimensional.primitives.IntPoint;

public class ColorContour implements GraphicComponent {
	private Color edgeColor = Color.green, vertexColor = Color.RED;
	private ArrayList<IntPoint> contour = new ArrayList<IntPoint>();

	public ColorContour(Collection<IntPoint> contour) {
		this.contour.addAll(contour);
	}

	public ColorContour(Collection<IntPoint> contour, Color vertexColor, Color edgeColor) {
		this.contour.addAll(contour);
		if (vertexColor == null || edgeColor == null) {
			throw new NullPointerException("Color is null");
		}
		this.vertexColor = vertexColor;
		this.edgeColor = edgeColor;
	}

	@Override
	public void draw(Graphics g) {

		if (contour.isEmpty()) {
			return;
		}

		g.setColor(edgeColor);
		IntPoint last = contour.get(0);
		for (int i = 1; i < contour.size(); i++) {
			IntPoint cur = contour.get(i);
			g.drawLine(last.getX(), last.getY(), cur.getX(), cur.getY());
			last = cur;
		}

		if (contour.size() > 2) {
			IntPoint cur = contour.get(0);
			g.drawLine(last.getX(), last.getY(), cur.getX(), cur.getY());
		}

		g.setColor(vertexColor);
		for (IntPoint p : contour) {
			g.drawOval(p.getX(), p.getY(), 1, 1);
			g.drawOval(p.getX(), p.getY(), 2, 2);
			g.drawOval(p.getX(), p.getY(), 3, 3);
		}
	}

}
