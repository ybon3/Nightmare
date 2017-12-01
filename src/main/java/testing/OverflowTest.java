package testing;

public class OverflowTest {

	public static void main(String[] args) {
		
		System.out.println("byte: " + (byte)(Byte.MIN_VALUE+1));
		System.out.println("short: " + (short)(Short.MAX_VALUE+1));
		System.out.println("int: " + (int)(Integer.MAX_VALUE+1));
		System.out.println("long: " + (long)(Long.MAX_VALUE+1));
		System.out.println("float: " + (float)(Float.MAX_VALUE+1));
		System.out.println("double: " + (double)(Double.MAX_VALUE+1));
		
		short a = 30000;
		a = (short) (a+a);
		System.out.println("a: " + a);
		
	}
}
