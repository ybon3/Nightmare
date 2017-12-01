package testing;

import java.text.ParseException;

/**
 * 測試 {@link String#compareTo(String)}
 */
public class StringTest {
	public static void main(String[] args) throws ParseException {
		String a = "2016-06-21";
		String b = "2016-12-21";
		String c = "2016-06-21";

		System.out.println(a.compareTo(b));
		System.out.println(b.compareTo(a));
		System.out.println(c.compareTo(a));

	}
}
