package lib.two_dimensional.primitives;

import java.io.Serializable;

public class Segment<Point extends TopologicalPoint> implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	Point begin, end;

	public Segment(Point[] p) {
		this(p[0], p[1]);
	}

	public Segment(Point begin, Point end) {
		if (begin == null) {
			throw new NullPointerException("Begin point is null.");
		}
		if (end == null) {
			throw new NullPointerException("End point is null.");
		}

		if (begin.equals(end)) {
			throw new IllegalArgumentException("Start equals finish.");
		}

		this.begin = begin;
		this.end = end;
	}

	public Point getBeginPoint() {
		return begin;
	}

	public Point getEndPoint() {
		return end;
	}

	public TopologicalPoint[] getVertices() {
		return new TopologicalPoint[] { begin, end };
	}

	@Override
	public String toString() {
		return "[" + begin + " " + end + "]";
	}

	@Override
	public int hashCode() {
		return begin.hashCode() ^ end.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Segment<Point> seg = (Segment<Point>) obj;

		if (seg.begin.equals(begin) && seg.end.equals(end)) {
			return true;
		}

		if (seg.begin.equals(end) && seg.end.equals(begin)) {
			return true;
		}
		return false;

	}

}
