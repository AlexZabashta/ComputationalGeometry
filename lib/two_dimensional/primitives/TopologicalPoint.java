package lib.two_dimensional.primitives;

public interface TopologicalPoint<Point> extends Comparable<Point> {
	public GeometryOrientation orientVsLine(Point begin, Point end);

	public GeometryOrientation orientVsSegment(Point begin, Point end);

	public int compareLineCross(Point gb, Point ge, Point hb, Point he, Point le);

	public int compareWithCircle(Point a, Point b, Point c);

	public int compareDistanceToLine(Point p, Point b, Point e);

}
