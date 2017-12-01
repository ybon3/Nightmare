package testing;

public class EnumTest {

	public static void main(String[] args) {
		System.out.println(Day.MONDAY);
		System.out.println(Day.SUNDAY);
		System.out.println(Day.SATURDAY);

		for (Day d : Day.values()) {
			System.out.println("=== " + d);
		}

		System.out.println(Day.valueOf("SUNDAY"));

		System.out.println(Day.MONDAY.name().equals("MONDAY"));
	}
}

enum Day {
	SUNDAY,
	MONDAY,
	SATURDAY
}
