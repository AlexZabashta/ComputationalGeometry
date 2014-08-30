package lib.two_dimensional.primitives;

public interface TopologicalPoint<Point> extends Comparable<Point> {
	public int compareVsLine(Point begin, Point end);

	public GeometryOrientation orientVsSegment(Point begin, Point end);

	public int compareLineCross(Point gb, Point ge, Point hb, Point he, Point le);

	public int compareWithCircle(Point a, Point b, Point c);

	public int compareDistanceToLine(Point p, Point b, Point e);

	public boolean isSegmentsIntersect(Point ue, Point vb, Point ve);

}
