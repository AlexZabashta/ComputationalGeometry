package visualizer;

import java.awt.Color;
import java.awt.Graphics;

import lib.two_dimensional.primitives.IntPoint;

public class ColorPoint implements GraphicComponent {
	private Color color = Color.RED;
	private IntPoint point;

	public ColorPoint(IntPoint point) {
		this.point = point;
	}

	public ColorPoint(IntPoint point, Color color) {
		this.point = point;
		this.color = color;
	}

	@Override
	public synchronized void draw(Graphics g) {

		g.setColor(color);
		g.drawOval(point.getX(), point.getY(), 1, 1);
		g.drawOval(point.getX(), point.getY(), 2, 2);
		g.drawOval(point.getX(), point.getY(), 3, 3);

	}
}
