package util;

public class CamelParser {
	public static void main(String[] args) {
		String src = "RIS_ROUTE_QUEUE_CKEY\n" +
"EVENT_TYPE_ID\n" +
"EXAM_CKEY\n" +
"PATIENT_ID\n" +
"ACCESSION_NO\n" +
"STUDY_INSTANCE_UID\n" +
"STUDY_DATE_TIME\n" +
"IMAGE_CNT\n" +
"CREATE_ON\n" +
"Q_STAT_ID\n" +
"MESSAGE";

		String[] arr = src.split("\n");
		for (String str : arr) {
			System.out.println(parse(str));
		}
	}

	public static String parse(String str) {
		str = str.trim().toLowerCase();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			String w = str.substring(i, i+1);
			if (w.equals("_") && (i+1 < str.length())) {
				i++;
				w = str.substring(i, i+1).toUpperCase();
			}

			result.append(w);
		}

		return result.toString();
	}
}
