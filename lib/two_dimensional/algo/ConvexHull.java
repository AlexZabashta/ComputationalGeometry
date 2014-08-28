package lib.two_dimensional.algo;

import java.util.List;

import lib.two_dimensional.primitives.TopologicalPoint;

public class ConvexHull {

	public static <Point extends TopologicalPoint<Point>> void quickConvexHull(List<Point> contour, List<Point> convexHull) {
		if (contour.size() < 4) {
			convexHull.addAll(contour);
			return;
		}
	}

}
