package example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import lib.two_dimensional.algo.ConvexHull;
import lib.two_dimensional.primitives.IntPoint;
import lib.two_dimensional.primitives.TopologicalPoint;

public class ConvexHullProblem {

	public static void main(String[] args) {
		IntPoint a = new IntPoint(3, 3);
		IntPoint b = new IntPoint(4, 4);
		IntPoint c = new IntPoint(7, 7);

		IntPoint s = new IntPoint(3, 7);
		IntPoint f = new IntPoint(6, 3);

		System.out.println(b.compareDistanceToLine(c, f, s));

		// IntPoint p = new IntPoint(0, 0);
		// System.out.println(p.compareWithCircle(b, c, a));

		// Random rnd = new Random();
		// TopologicalPoint<?>[] tp = new TopologicalPoint[10];
		// for (int i = 0; i < 10; i++) {
		// tp[i] = new IntPoint(rnd.nextInt(100), rnd.nextInt(100));
		// }
		//
		// for (TopologicalPoint<?> p : tp) {
		// System.out.print(p + " ");
		// }
		// System.out.println();
		//
		// Arrays.sort(tp);
		//
		// for (TopologicalPoint<?> p : tp) {
		// System.out.print(p + " ");
		// }
		//
		// List<IntPoint> cnt = new ArrayList<>();
		// List<IntPoint> hull = new ArrayList<>();
		//
		// ConvexHull.quickConvexHull(cnt, hull);
	}
}
