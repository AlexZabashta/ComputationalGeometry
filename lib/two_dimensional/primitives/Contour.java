package lib.two_dimensional.primitives;

import java.util.ArrayList;

public class Contour<Point extends TopologicalPoint> {
	ArrayList<Point> points = new ArrayList<Point>();

	public int size() {
		return points.size();
	}

	// private quickHull(Point)

	public Contour<Point> getConvexHull() {
		Contour<Point> hull = new Contour<Point>();

		Point l = points.get(0), r = l;

		for (Point p : points) {

		}

		return hull;
	}
}
