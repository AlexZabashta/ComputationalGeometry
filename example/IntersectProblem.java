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

public class IntersectProblem {

	static void run(PRVisualizer nsv) throws InterruptedException {

		Color[] c = new Color[] { Color.BLUE, Color.GREEN, Color.RED };

		while (true) {
			ArrayList<IntPoint>[] tr = new ArrayList[2];
			for (int i = 0; i < 2; i++) {
				tr[i] = new ArrayList<>();
				List<IntPoint> cur = nsv.getPoints(2);

				int m = cur.size();

				if (m < 2) {
					return;
				}

				for (int j = 1; j <= 2; j++) {
					tr[i].add(cur.get(m - j));
				}

				nsv.draw(new ColorContour(tr[i], c[i], c[i]));
			}

			IntPoint ub = tr[0].get(0);
			IntPoint ue = tr[0].get(1);

			IntPoint vb = tr[1].get(0);
			IntPoint ve = tr[1].get(1);

			IntPoint t = ub.getIntersectSegmentsPoint(ue, vb, ve);
			if (t != null) {
				nsv.draw(new ColorPoint(t, c[2]));
			}

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

		// <372, 82> <292, 347>
		// <467, 348> <169, 103>
		// <326, 232>
	}

}
