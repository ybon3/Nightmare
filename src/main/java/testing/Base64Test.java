package testing;

import org.apache.commons.codec.binary.Base64;

public class Base64Test {

	public static void main(String[] args) throws Exception {

		final Base64 base64 = new Base64();
		final String encodedText = "eyJhbGciOiJSUzI1NiIsImtpZCI6InJzYTEifQ";
		final String encodedText2 = "eyJleHAiOjE0NzgxNTcwNzIsInN1YiI6IjkwMzQyLkFTREZKV0ZBIiwibm9uY2UiOiIyMTU1YzQwYjU5MjUzIiwiYXVkIjoiOWVjODVmMTMtYjAxOS00MTIxLTk5OTMtMzFkYTMxZTUxNWJlIiwiaXNzIjoiaHR0cDpcL1wvMTkyLjE2OC4xLjIxOjgwMDhcL29wZW5pZC1jb25uZWN0LXNlcnZlci13ZWJhcHBcLyIsImp0aSI6IjRiOWEzNjdhLWMxYWEtNDBjMy1iMzFkLTIyMWMxYmFlMWY2ZSIsImlhdCI6MTQ3ODE1NjQ3Miwia2lkIjoicnNhMSJ9";


		//解碼
		System.out.println(new String(base64.decode(encodedText), "UTF-8"));
		System.out.println(new String(base64.decode(encodedText2), "UTF-8"));

		//編碼
		byte[] a = base64.decode(encodedText);
		System.out.println(base64.encodeToString(a));
		System.out.println(base64.encodeAsString(a));
		System.out.println(base64.encodeAsString(new String(a).getBytes()));

		byte[] b = base64.decode(encodedText2);
		System.out.println(base64.encodeAsString(b));
		System.out.println(base64.encodeAsString(new String(b).getBytes()));

	}
}
