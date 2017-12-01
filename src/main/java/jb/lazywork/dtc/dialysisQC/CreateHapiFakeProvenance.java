package jb.lazywork.dtc.dialysisQC;

import java.util.Date;

import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.PeriodDt;
import ca.uhn.fhir.model.dstu2.resource.Provenance;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.InstantDt;



public class CreateHapiFakeProvenance {

	static String[] STATUS = new String[] {
		"Published",
		"Processing",
		"Finished"
	};

	public static void main(String[] args) {
		String baseUrl = Context.BASE_URL;
		ProvenanceHapiFhirRepository repo = new ProvenanceHapiFhirRepository(baseUrl);

		repo.save(createResource("如何用 XML schema 產生 VO"));
//		repo.save(createResource("JAVA 如何取/產生亂數 (大學程式設計 第三課"));
//		repo.save(createResource("就像是電腦自動選出的一個幸運數字"));
//		repo.save(createResource("This returns a single instance with the content specified"));
//		repo.save(createResource("避免Eclipse 有時候在對檔案 diff 時，判斷整個檔案都是變更的錯誤"));
//		repo.save(createResource("除了文件第一行之外，其餘 header 的上頭都要空兩行"));
//		repo.save(createResource("THE WORLD'S LARGEST WEB DEVELOPER SITE"));
	}

	public static Provenance createResource(String title) {
		Provenance res = new Provenance();

		// title & status & 內容
		CodeableConceptDt activity = new CodeableConceptDt();
		CodingDt coding = new CodingDt();
		coding.setDisplay(title);
		coding.setCode(STATUS[getRandom(0,2)]);
		activity.addCoding(coding);
		res.setActivity(activity);

		// recorded & period.start & period.end
		res.setRecorded(new InstantDt(new Date()));
		PeriodDt period = new PeriodDt();
		period.setStart(new DateTimeDt("2016-01-01T00:00:00+08:00"));
		period.setEnd(new DateTimeDt("2016-12-30T23:59:59+08:00"));
		res.setPeriod(period);

		return res;
	}

	public static int getRandom(int s, int e) {

		if (s > e) {
			int t = s;
			s = e;
			e = t;
		}

		return (int)(Math.random() * (e - s + 1)) + s;
	}

	private static CodingDt parseCodingDt(com.dtc.fhir.gwt.Coding src) {
		CodingDt res = new CodingDt();

		// system
		if (src.getSystem() != null) {
			res.setSystem(src.getSystem().getValue());
		}

		// version
		if (src.getVersion() != null) {
			res.setVersion(src.getVersion().getValue());
		}

		// code
		if (src.getCode() != null) {
			res.setCode(src.getCode().getValue());
		}

		// display
		if (src.getDisplay() != null) {
			res.setDisplay(src.getDisplay().getValue());
		}

		// userSelected
		if (src.getUserSelected() != null) {
			res.setUserSelected(src.getUserSelected().isValue());
		}

		return res;
	}
}
