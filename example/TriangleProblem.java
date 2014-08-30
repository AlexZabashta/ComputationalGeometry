package example;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import lib.two_dimensional.algo.ConvexHull;
import lib.two_dimensional.primitives.IntPoint;
import visualizer.ColorContour;
import visualizer.ColorPoint;
import visualizer.PRVisualizer;

public class TriangleProblem {

	static boolean eqo(int a, int b) {
		if (a == 0 || b == 0) {
			return true;
		}
		return a == b;
	}

	static void run(PRVisualizer nsv) throws InterruptedException {

		Color[] c = new Color[] { Color.BLUE, Color.GREEN, Color.RED };

		while (true) {
			ArrayList<IntPoint>[] tr = new ArrayList[2];
			for (int i = 0; i < 2; i++) {
				tr[i] = new ArrayList<>();
				List<IntPoint> cur = nsv.getPoints(3);

				int m = cur.size();

				if (m < 3) {
					return;
				}

				for (int j = 1; j <= 3; j++) {
					tr[i].add(cur.get(m - j));
				}

				nsv.draw(new ColorContour(tr[i], c[i], c[i]));
			}

			Collection<IntPoint> points = new ArrayList<>(), ans = new ArrayList<>();

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					IntPoint ub = tr[0].get(i);
					IntPoint ue = tr[0].get((i + 1) % 3);

					IntPoint vb = tr[1].get(j);
					IntPoint ve = tr[1].get((j + 1) % 3);

					IntPoint t = ub.getIntersectSegmentsPoint(ue, vb, ve);
					if (t != null) {
						// nsv.draw(new ColorPoint(t, c[2]));
						points.add(t);
					}
				}
			}

			for (int s = 0; s < 2; s++) {
				for (IntPoint u : tr[s]) {
					int[] cmp = new int[3];
					for (int j = 0; j < 3; j++) {
						IntPoint vb = tr[(s + 1) % 2].get(j);
						IntPoint ve = tr[(s + 1) % 2].get((j + 1) % 3);
						cmp[j] = u.compareVsLine(vb, ve);
					}
					if (eqo(cmp[0], cmp[1]) && eqo(cmp[1], cmp[2]) && eqo(cmp[2], cmp[0])) {
						points.add(u);
						// nsv.draw(new ColorPoint(u, c[2]));
					}

				}
			}

			ConvexHull.quickHull(points, ans);
			nsv.draw(new ColorContour(ans, c[2], c[2]));

			nsv.getPoints(0);
			nsv.clearCanvas();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		PRVisualizer nsv = new PRVisualizer(true);
		while (true) {
			run(nsv);
			nsv.clearCanvas();
		}
	}

}
