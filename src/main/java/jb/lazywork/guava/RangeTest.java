package jb.lazywork.guava;

import com.google.common.collect.BoundType;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

public class RangeTest {

	public static void main(String[] args) {


		Range<Integer> r1 = Range.open(1, 10);
		Range<Integer> r2 = Range.closed(11, 100);

		//
		int x = 5;
		int y = 10;
		int z = 20;

		// Range style
		Range<Integer> r3 = Range.closed(1, 10);
		System.out.println(r3.contains(x));
		System.out.println(r3.contains(y));
		System.out.println(r3.contains(z));

		// normal style
		int start = 1;
		int end = 10;
		System.out.println((start <= x && end >= x));
		System.out.println((start <= y && end >= y));
		System.out.println((start <= z && end >= z));

		System.out.println(r1.toString());
		System.out.println(r2.toString());
		//System.out.println(r1.canonical(DiscreteDomain.integers()).toString());

		System.out.println(Range.downTo(70, BoundType.CLOSED) + " = " + Range.atLeast(70));
		System.out.println(Range.downTo(70, BoundType.OPEN)   + " = " + Range.greaterThan(70));
		System.out.println(Range.upTo(70, BoundType.CLOSED)   + " = " + Range.atMost(70));
		System.out.println(Range.upTo(70, BoundType.OPEN)     + " = " + Range.lessThan(70));

		r1 = Range.open(1, 11);
		r2 = Range.open(11, 100);
		System.out.println("isConnected: " + r1.isConnected(r2));

		r1 = Range.open(1, 100);
		r2 = Range.closed(1, 100);
		System.out.println("encloses: " + r1.encloses(r2));

		r1 = Range.open(1, 100);
		r2 = Range.closed(50, 200);
		System.out.println("intersection: " + r1.intersection(r2));
		System.out.println("span: " + r1.span(r2));

		r1 = Range.open(1, 100);
		System.out.println("canonical: " + r1 + " > " + r1.canonical(DiscreteDomain.integers()));
		r1 = Range.openClosed(1, 100);
		System.out.println("canonical: " + r1 + " > " + r1.canonical(DiscreteDomain.integers()));
		r1 = Range.closed(1, 100);
		System.out.println("canonical: " + r1 + " > " + r1.canonical(DiscreteDomain.integers()));
		r1 = Range.closedOpen(1, 100);
		System.out.println("canonical: " + r1 + " > " + r1.canonical(DiscreteDomain.integers()));
	}

}
