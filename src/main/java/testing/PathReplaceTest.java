package testing;

public class PathReplaceTest {

	public static void main(String[] args) {
		String origin = "d:\\test\\autocare\\gateway";
		System.out.println(origin + " -> " + origin.replace('\\', '/'));
	}

}
