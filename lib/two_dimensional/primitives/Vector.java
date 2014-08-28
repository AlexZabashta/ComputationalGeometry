package lib.two_dimensional.primitives;

public class Vector<Point extends TopologicalPoint> extends Segment<Point> {

	private static final long serialVersionUID = 1L;

	public Vector(Point start, Point end) {
		super(start, end);
	}

	public Vector(Point[] p) {
		super(p[0], p[1]);
	}

	@Override
	public int hashCode() {
		return begin.hashCode() - end.hashCode();
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
		Vector<Point> vec = (Vector<Point>) obj;
		return vec.begin.equals(begin) && vec.end.equals(end);
	}

}
