package jb.lazywork.dtc.dialysisQC;

import java.util.Date;

import com.dtc.fhir.gwt.Code;
import com.dtc.fhir.gwt.CodeableConcept;
import com.dtc.fhir.gwt.Coding;
import com.dtc.fhir.gwt.DateTime;
import com.dtc.fhir.gwt.Instant;
import com.dtc.fhir.gwt.Period;
import com.dtc.fhir.gwt.Provenance;
import com.dtc.fhir.gwt.StringDt;



public class CreateFakeProvenance {

	static String[] STATUS = new String[] {
		IssueTrackStatus.assigned.name(),
		IssueTrackStatus.finished.name()
	};

	public static void main(String[] args) {
		String baseUrl = Context.BASE_URL;
		ProvenanceGwtRepo repo = new ProvenanceGwtRepo(baseUrl);

		repo.save(createBulletin("2016年度血液透析质量统计项目公告内容","受到颱風影響，籌劃多時的民進黨創黨三十周年活動被迫取消", "2016-01-25","2016-03-30"));
		repo.save(createBulletin("2015年度血液透析质量统计项目公告内容","藍色床單及門禁卡等物據為己有；另吳男涉嫌將屋內天花板戳洞、釘釘子在櫃子及地板上，且馬桶堵塞、冰箱功能喪失，遲遲未付押租金，又不賠償屋內毀損", "2016-01-25","2016-03-30"));

//		repo.save(createResource("如何用 XML schema 產生 VO"));
//		repo.save(createResource("JAVA 如何取/產生亂數 (大學程式設計 第三課"));
//		repo.save(createResource("就像是電腦自動選出的一個幸運數字"));
//		repo.save(createResource("This returns a single instance with the content specified"));
//		repo.save(createResource("避免Eclipse 有時候在對檔案 diff 時，判斷整個檔案都是變更的錯誤"));
//		repo.save(createResource("除了文件第一行之外，其餘 header 的上頭都要空兩行"));
//		repo.save(createResource("THE WORLD'S LARGEST WEB DEVELOPER SITE"));
	}

	public static Provenance createBulletin(String title, String content, String pStar, String pEnd) {
		Provenance res = new Provenance();

		// reason
		CodeableConcept cc = new CodeableConcept();
		Coding coding = new Coding();
		Code code = new Code();
		code.setValue(ProvenanceReason.bulletin.name());
		coding.setCode(code);
		cc.getCoding().add(coding);
		res.getReason().add(cc);

		// title & status & 內容
		CodeableConcept activity = new CodeableConcept();
		StringDt s = new StringDt();
		s.setValue(title);
		activity.setText(s);

		coding = new Coding();
		s = new StringDt();
		s.setValue(title);
		coding.setDisplay(s);
		activity.getCoding().add(coding);
		res.setActivity(activity);

		// recorded & period.start & period.end
		Period period = new Period();
		DateTime startDT = new DateTime();
		startDT.setValue(pStar);
		DateTime endDT = new DateTime();
		endDT.setValue(pEnd);

		period.setStart(startDT);
		period.setEnd(endDT);
		res.setPeriod(period);

		Instant instant = new Instant();
		instant.setValue(new Date());
		res.setRecorded(instant);

		return res;
	}

	public static Provenance createResource(String title) {
		Provenance res = new Provenance();

		// title & status & 內容
		CodeableConcept activity = new CodeableConcept();
		Coding coding = new Coding();
		StringDt s = new StringDt();
		s.setValue(title);
		coding.setDisplay(s);
		Code code = new Code();
		code.setValue(STATUS[getRandom(0,1)]);
		coding.setCode(code);
		activity.getCoding().add(coding);
		res.setActivity(activity);

		// recorded & period.start & period.end
		Period period = new Period();
		DateTime startDT = new DateTime();
		startDT.setValue("2016-01-01");
		DateTime endDT = new DateTime();
		endDT.setValue("2016-12-30");

		period.setStart(startDT);
		period.setEnd(endDT);
		res.setPeriod(period);
		Instant instant = new Instant();
		instant.setValue(new Date());
		res.setRecorded(instant);

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
}
