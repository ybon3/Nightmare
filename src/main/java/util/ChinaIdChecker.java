package util;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 中國大陸身分證字號檢測
 */
public class ChinaIdChecker {

	public static void main(String[] args) {
//		String id = "110101199901013481";
//		System.out.println(check(id));

		System.out.println(isACDay("20190229"));
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			isACDay("99999999");
		}


		System.out.println((System.currentTimeMillis() - start) / 1000d);
	}

	private static final int[] key = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
	private static final char[] checkCode = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

	public static boolean check(String id) {
		// 18 碼長度檢核
		if (id.length() != 18) { return false; }

		// 17 碼數字檢核
		for (int i = 0; i < id.length() - 1; i++) {
			char c = id.charAt(i);
			if (c < '0' || c > '9') { return false; }
		}

		//生日碼檢核
		if (!isACDay(id.substring(6, 14))) { return false; }

		//計算係數
		char[] idCharArray = id.substring(0, 17).toCharArray();
		int result = 0;
		for (int i = 0; i < 17; i++) {
			result += key[i] * Integer.parseInt(String.valueOf(idCharArray[i]));
		}

		//檢查碼比對
		return id.toUpperCase().charAt(17) == checkCode[result%11];
	}

	public static boolean isACDay(String strDate) {
		int date = Integer.parseInt(strDate);

		int year = date / 10000;
		int month = (date % 10000) / 100;
		int day = date % 100;

		boolean yearOk = (year >= 1581) && (year <= 2500);
		boolean monthOk = (month >= 1) && (month <= 12);
		boolean dayOk = (day >= 1) && (day <= daysInMonth(year, month));

		return (yearOk && monthOk && dayOk);
	}

	private static int daysInMonth(int year, int month) {
		int daysInMonth;
		switch (month) {
		case 1: // fall through
		case 3: // fall through
		case 5: // fall through
		case 7: // fall through
		case 8: // fall through
		case 10: // fall through
		case 12:
			daysInMonth = 31;
			break;
		case 2:
			if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
				daysInMonth = 29;
			} else {
				daysInMonth = 28;
			}
			break;
		default:
			// returns 30 even for nonexistant months
			daysInMonth = 30;
		}
		return daysInMonth;
	}

	public static boolean isACDay2(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			sdf.setLenient(false);
			sdf.parse(date);
			return true;
		} catch (ParseException pe) {
			return false;
		}
	}
}
