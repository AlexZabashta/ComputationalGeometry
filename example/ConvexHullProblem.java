package example;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import lib.two_dimensional.algo.ConvexHull;
import lib.two_dimensional.primitives.IntPoint;
import visualizer.ColorContour;
import visualizer.ColorPoint;
import visualizer.PRVisualizer;

public class ConvexHullProblem {

	public static void main(String[] args) throws InterruptedException {
		PRVisualizer nsv = new PRVisualizer(true);
		while (true) {
			List<IntPoint> points = new ArrayList<>(), cnt = new ArrayList<>();
			nsv.clearCanvas();
			while (true) {
				List<IntPoint> cur = nsv.getPoints(1);
				if (cur.isEmpty()) {
					break;
				}
				IntPoint q = cur.get(cur.size() - 1);
				points.add(q);
				cnt.clear();
				ConvexHull.quickHull(points, cnt);
				nsv.clearCanvas();
				for (IntPoint p : points) {
					nsv.draw(new ColorPoint(p, Color.BLUE));
				}
				nsv.draw(new ColorContour(cnt));
			}
		}
	}
}
