package jb.lazywork.dtc.fidxc;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.dtc.fhir.gwt.Code;
import com.dtc.fhir.gwt.DateTime;
import com.dtc.fhir.gwt.DiagnosticOrderEvent;
import com.dtc.fhir.gwt.DiagnosticOrderStatus;
import com.dtc.fhir.gwt.DiagnosticOrderStatusList;
import com.dtc.fhir.gwt.Reference;
import com.dtc.fhir.gwt.StringDt;

public class Constant {
	public static final String BASE_URL = "http://192.168.1.200:8000/fhir-jpaserver_v13/baseDstu2/";

	public static StringDt toStringDt(String s) {
		StringDt stringDt = new StringDt();
		stringDt.setValue(s);
		return stringDt;
	}

	public static Code toCode(String s) {
		Code code = new Code();
		code.setValue(s);
		return code;
	}

	public static DateTime toDateTime(String s) {
		DateTime dt = new DateTime();
		dt.setValue(s);
		return dt;
	}

	public static DiagnosticOrderStatus toStatus(DiagnosticOrderStatusList s) {
		DiagnosticOrderStatus status = new DiagnosticOrderStatus();
		status.setValue(s);
		return status;
	}

	public static Reference buildReference(String name, String reference) {
		Reference ref = new Reference();
		ref.setDisplay(toStringDt(name));
		ref.setReference(toStringDt(reference));
		return ref;
	}

	public static DiagnosticOrderEvent buildEvent(DiagnosticOrderStatus status, Reference actor) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		DiagnosticOrderEvent event = new DiagnosticOrderEvent();
		event.setDateTime(toDateTime(sdf.format(new Date())));
		event.setStatus(status);
		event.setActor(actor);
		return event;
	}

	public static Reference getSubject() {
		String[][] datas = new String[][] {
			{"李Ｏ","Patient/PATIENT.DC32000800.8066.320113640204203"},
			{"王Ｏ琴","Patient/PATIENT.DR731.84762774.T000000138"},
			{"蒋Ｏ兴","Patient/PAT32000800.8066.320124194012251616"},
			{"杜Ｏ凤","Patient/PATIENT.DR731.84762774.T000000141"},
			{"陈Ｏ","Patient/DR731.84762774.320826197607159214"},
			{"马Ｏ","Patient/PAT32000800.8066.320105671125022"},
			{"郭Ｏ贺","Patient/PATIENT.DR731.84762774.T000000135"},
			{"封Ｏ权","Patient/DR731.84762774.320826197507034271"},
			{"李Ｏ池","Patient/PAT32000800.8066.32010619500616282X"},
			{"张Ｏ森","Patient/PATIENT.DC32000800.8066.320113630113083"},
			{"于Ｏ民","Patient/PATIENT.DR731.84762774.210703195710232076"},
			{"何Ｏ芳","Patient/DR731.84762774.320922193202046820"},
			{"孙Ｏ","Patient/PAT32000800.8066.320104520309242"},
			{"李Ｏ华","Patient/PATIENT.DR731.84762774.T000000023"}
		};
		String[] info = datas[getRandom(0, datas.length-1)];
		return buildReference(info[0], info[1]);
	}

	public static Reference getOrderer() {
		String[][] datas = new String[][] {
			{"陶Ｏ","Practitioner/DR731.84762774.1294"},
			{"周Ｏ","Practitioner/PRAC.DC731.83929271.041"},
			{"刘Ｏ晶","Practitioner/PRAC.DR731.84762774.13054"},
			{"高Ｏ解","Practitioner/DR731.84762774.0000"},
			{"吴Ｏ亮","Practitioner/DR731.84762774.2013118"},
			{"吴Ｏ亮","Practitioner/PRAC.DR731.84762774.2013118"},
			{"朱Ｏ","Practitioner/PRAC.DR731.84762774.2384"},
			{"刘Ｏ晶","Practitioner/DR731.84762774.13054"},
			{"吴Ｏ芬","Practitioner/PRAC.DR731.84762774.1199"},
			{"蔡Ｏ丹","Practitioner/PRAC.DC731.83929271.1200"},
			{"孙Ｏ军","Practitioner/DR731.84762774.1505"},
			{"孙Ｏ军","Practitioner/PRAC.DR731.84762774.1505"}
		};
		String[] info = datas[getRandom(0, datas.length-1)];
		return buildReference(info[0], info[1]);
	}

	public static int getRandom(int s, int e) {
		if (s > e) {
			int t = s;
			s = e;
			e = t;
		}
		return (int)(Math.random() * (e - s + 1)) + s;
	}
}
