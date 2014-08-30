package lib.two_dimensional.algo;

import java.util.ArrayList;
import java.util.Collection;

import lib.two_dimensional.primitives.TopologicalPoint;

public class ConvexHull {

	static <Point extends TopologicalPoint<Point>> void quickHull(Collection<Point> in, Collection<Point> o, Point b, Point e) {

		if (in.isEmpty()) {
			return;
		}
		Collection<Point> r = new ArrayList<Point>();

		Point m = b;
		for (Point p : in) {
			int c = p.compareVsLine(b, e);

			if (c > 0) {
				r.add(p);
			}

			if (p.compareDistanceToLine(m, b, e) > 0) {
				m = p;
			}
		}
		if (m.equals(b)) {
			return;
		}
		quickHull(r, o, b, m);
		o.add(m);
		quickHull(r, o, m, e);

	}

	public static <Point extends TopologicalPoint<Point>> void quickHull(Collection<Point> in, Collection<Point> out) {
		if (in.size() < 4) {
			out.addAll(in);
			return;
		}

		Point l = in.iterator().next(), r = l;

		for (Point p : in) {
			if (l.compareTo(p) > 0) {
				l = p;
			}
			if (r.compareTo(p) < 0) {
				r = p;
			}
		}

		if (l.compareTo(r) == 0) {
			out.add(l);
			return;
		}
		out.add(l);
		quickHull(in, out, l, r);

		out.add(r);
		quickHull(in, out, r, l);
	}
}
