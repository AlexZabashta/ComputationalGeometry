package visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import lib.two_dimensional.primitives.IntPoint;

public class ColorChain {
	private Color edgeColor = Color.green, vertexColor = Color.RED;
	private ArrayList<IntPoint> chain = new ArrayList<IntPoint>();

	public ColorChain(Collection<IntPoint> chain) {
		this.chain.addAll(chain);
	}

	public ColorChain(Collection<IntPoint> chain, Color vertexColor, Color edgeColor) {
		this.chain.addAll(chain);
		if (vertexColor == null || edgeColor == null) {
			throw new NullPointerException("Color is null");
		}
		this.vertexColor = vertexColor;
		this.edgeColor = edgeColor;
	}

	public void draw(Graphics g) {
		if (chain.isEmpty()) {
			return;
		}

		g.setColor(edgeColor);
		IntPoint last = chain.get(0);
		for (int i = 1; i < chain.size(); i++) {
			IntPoint cur = chain.get(i);
			g.drawLine(last.getX(), last.getY(), cur.getX(), cur.getY());
			last = cur;
		}

		g.setColor(vertexColor);
		for (IntPoint p : chain) {
			g.drawOval(p.getX(), p.getY(), 3, 3);

		}
	}
}
