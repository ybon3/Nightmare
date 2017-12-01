package jb.lazywork.guava;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.ComparisonChain;

/**
 * Reference:
 * https://github.com/google/guava/wiki/OrderingExplained
 * https://google.github.io/guava/releases/19.0/api/docs/com/google/common/collect/Ordering.html
 */
public class OrderingTest {
	public static List<Foobar> list = Arrays.asList(new Foobar[]{
		new Foobar("A Beautiful Song","NieR:Automata Original Soundtrack(DISC2)", 8),
		new Foobar("Grandma - Destruction","NieR:Automata Original Soundtrack(DISC1)", 1),
		new Foobar("Amusement Park","NieR:Automata Original Soundtrack(DISC1)", 7),
		new Foobar("Alien Manifestation","NieR:Automata Original Soundtrack(DISC3)", 11),
	});

	public static void main(String[] args) {

		step1();
		System.out.println();
		step2();
		System.out.println();
		step3();
	}


	public static void step1() {
		Collections.sort(list);
		show(list);
	}

	public static void step2() {
		Collections.sort(list, new Comparator<Foobar>() {
			@Override
			public int compare(Foobar o1, Foobar o2) {
				return ComparisonChain.start()
					.compare(o1.getArtist(), o2.getArtist())
					.compare(o1.getTitle(), o2.getTitle())
					.compare(o1.getTrack(), o2.getTrack())
					.result();
			}
		});
		show(list);
	}

	public static void step3() {
//		Ordering<Foobar> ordering = Ordering
//			.natural().onResultOf(new Function<Foo, String>() {
//			@Override
//			public String apply(Foo foo) {
//				return foo.sortedBy;
//			}
//		});
	}

	public static void show(List<Foobar> list) {
		for (Foobar foobar : list) {
			System.out.println(foobar);
		}
	}
}

class Foobar implements Comparable<Foobar>{
	protected String title;
	protected String artist;
	protected int track;

	public Foobar(String title, String artist, int track) {
		super();
		this.title = title;
		this.artist = artist;
		this.track = track;
	}

	public String getTitle() { return title; }
	public String getArtist() { return artist; }
	public int getTrack() { return track; }

	@Override
	public String toString() {
		return "Foobar [title=" + title + ", artist=" + artist + ", track=" + track + "]";
	}

	// 依序由 title、artist、track 進行排序
	// 如果 title 排不出輸贏就看 artist
	// 如果 artist 也排不出輸贏就看 track
	@Override
	public int compareTo(Foobar other) {
		// bad
//		int cmp = title.compareTo(other.title);
//		if (cmp != 0) { return cmp; }
//
//		cmp = artist.compareTo(other.artist);
//		if (cmp != 0) { return cmp; }
//
//		return Integer.compare(track, other.track);

		// better
		return ComparisonChain.start()
			.compare(title, other.title)
			.compare(artist, other.artist)
			.compare(track, other.track)
			.result();
	}
}

