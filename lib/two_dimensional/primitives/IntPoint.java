package lib.two_dimensional.primitives;

import java.io.Serializable;

public class IntPoint implements TopologicalPoint<IntPoint>, Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private int x, y;

	static long det(long m11, long m12, long m21, long m22) {
		return m11 * m22 - m12 * m21;
	}

	static long det(long m11, long m12, long m13, long m21, long m22, long m23, long m31, long m32, long m33) {
		return m11 * det(m22, m23, m32, m33) - m12 * det(m21, m23, m31, m33) + m13 * det(m21, m22, m31, m32);
	}

	public IntPoint() {
		x = y = 0;
	}

	public IntPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "<" + x + ", " + y + ">";
	}

	@Override
	public int hashCode() {
		return x ^ y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntPoint other = (IntPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public int compareTo(IntPoint ip) {
		if (x == ip.x) {
			return Integer.compare(y, ip.y);
		}
		return Integer.compare(x, ip.x);
	}

	@Override
	public int compareVsLine(IntPoint b, IntPoint e) {
		long s = det((long) x - b.x, (long) y - b.y, (long) e.x - b.x, (long) e.y - b.y);
		return Long.compare(s, 0);
	}

	@Override
	public GeometryOrientation orientVsSegment(IntPoint b, IntPoint e) {

		long s = det((long) x - b.x, (long) y - b.y, (long) e.x - b.x, (long) e.y - b.y);

		if (s != 0) {
			return GeometryOrientation.OUTSIDE;
		}

		if (equals(b) || equals(e)) {
			return GeometryOrientation.VERTEX;
		}

		if (Math.min(b.x, e.x) <= x && x <= Math.max(b.x, e.x) && Math.min(b.y, e.y) <= y && y <= Math.max(b.y, e.y)) {
			return GeometryOrientation.EDGE;
		}

		return GeometryOrientation.OUTSIDE;

	}

	@Override
	public int compareLineCross(IntPoint gb, IntPoint ge, IntPoint hb, IntPoint he, IntPoint le) {

		long gbx = gb.x, gby = gb.y;
		long gex = ge.x, gey = ge.y;
		long hbx = hb.x, hby = hb.y;
		long hex = he.x, hey = he.y;
		long lbx = x, lby = y;
		long lex = le.x, ley = le.y;

		long ag = gey - gby, bg = gbx - gex, cg = gby * (gex - gbx) - gbx * (gey - gby);
		long ah = hey - hby, bh = hbx - hex, ch = hby * (hex - hbx) - hbx * (hey - hby);
		long al = ley - lby, bl = lbx - lex, cl = lby * (lex - lbx) - lbx * (ley - lby);

		long ux = cg * bl - cl * bg, uy = ag * cl - al * cg, ud = al * bg - ag * bl;
		long vx = ch * bl - cl * bh, vy = ah * cl - al * ch, vd = al * bh - ah * bl;

		return Long.compare((ley - lby) * (vy * ud - uy * vd), (lex - lbx) * (vx * ud - ux * vd));

	}

	@Override
	public int compareWithCircle(IntPoint a, IntPoint b, IntPoint c) {

		long ax = a.x, ay = a.y, as = ax * ax + ay * ay;
		long bx = b.x, by = b.y, bs = bx * bx + by * by;
		long cx = c.x, cy = c.y, cs = cx * cx + cy * cy;

		long px = x, py = y, ps = px * px + py * py;

		long d = 0;
		d -= det(ax, ay, as, bx, by, bs, cx, cy, cs);
		d += det(px, py, ps, bx, by, bs, cx, cy, cs);
		d -= det(px, py, ps, ax, ay, as, cx, cy, cs);
		d += det(px, py, ps, ax, ay, as, bx, by, bs);

		return Long.compare(0, d);

	}

	@Override
	public int compareDistanceToLine(IntPoint p, IntPoint b, IntPoint e) {

		long dt = det((long) x - b.x, (long) y - b.y, (long) e.x - b.x, (long) e.y - b.y);
		long dp = det((long) p.x - b.x, (long) p.y - b.y, (long) e.x - b.x, (long) e.y - b.y);

		return Long.compare(dt, dp);

	}

}
