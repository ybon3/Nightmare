package testing;

import java.util.Calendar;
import java.util.Date;

public class DateCalcTest {


	public static void main(String[] args) {
		System.out.println("TESTING");
		Calendar start = Calendar.getInstance();
		start.add(Calendar.DATE, -1);

		Calendar end = Calendar.getInstance();
		end.add(Calendar.MILLISECOND, 1);

		System.out.println(start.getTime().getTime());
		System.out.println(end.getTime().getTime());
		System.out.println(end.getTime().getTime() - start.getTime().getTime());
		System.out.println(calcDates(start.getTime(), end.getTime()));
	}

	public static int calcDates(Date start, Date end) {
		long period = end.getTime() - start.getTime();
		return (int)((period / 86400000) + ((period % 86400000) > 0 ? 1 : 0));
	}

}
