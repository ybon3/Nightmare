package jb.lazywork.dtc.fidxc;

import java.util.List;

import com.dtc.fhir.gwt.DiagnosticReport;
import com.dtc.fhir.gwt.Identifier;
import com.dtc.fhir.gwt.ImagingStudy;
import com.dtc.fhir.gwt.Reference;
import com.dtc.fhir.gwt.util.IdentifierUtil;
import com.dtc.fhir.gwt.util.PromiseSetter;

import jb.lazywork.dtc.fhir.gwt.repo.DxReportGwtRepo;
import jb.lazywork.dtc.fhir.gwt.repo.ImagingStudyGwtRepo;

/**
 * 用來修正 DxReport 因為沒有設定 code.text 導致無法正確產生  text.div 的內容
 */
public class FixDxReportByGwtRepo {
	public static void main(String[] args) {
//		new FixDxReportByGwtRepo("http://192.168.111.1:8013/baseDstu2/").runNT(); //NT
//		new FixDxReportByGwtRepo("http://192.168.111.2:8013/baseDstu2/").runFIDXCnTWR(); //FIDXC
		new FixDxReportByGwtRepo("http://192.168.111.5:8013/baseDstu2/").runFIDXCnTWR(); //TWR
	}

	private DxReportGwtRepo dxReportRepo;
	private ImagingStudyGwtRepo imgStudyRepo;

	public FixDxReportByGwtRepo (String url) {
		dxReportRepo = new DxReportGwtRepo(url);
		imgStudyRepo = new ImagingStudyGwtRepo(url);
	}


	//FIDXC & TWR
	public void runFIDXCnTWR() {
		List<DiagnosticReport> list = dxReportRepo.findAll();

		for (DiagnosticReport dxReport : list) {
			System.out.println("[PROCESSING] ID: " + dxReport.getId().getValue());


			Identifier identidfier = IdentifierUtil.findBySystem(dxReport.getIdentifier(), "accessionNumber");
			String accessionNumber = identidfier == null ? "" : identidfier.getValue().getValue();

			// Organization
			identidfier = IdentifierUtil.findBySystem(dxReport.getIdentifier(), "organization");
			String orgId = identidfier == null ? "" : identidfier.getValue().getValue();

			//find ImagingStudy
			ImagingStudy imgStudy = imgStudyRepo.findByOrgAccessionNo(orgId, accessionNumber);

			if (imgStudy == null) {
				System.out.println("ImagingStudy not found.");
			}

			setDxReportCodeText(dxReport, imgStudy);
			System.out.println("Done with successed: " + dxReportRepo.save(dxReport));
		}
	}

	//NT
	public void runNT() {
		List<DiagnosticReport> list = dxReportRepo.findAll();

		for (DiagnosticReport dxReport : list) {
			System.out.println("[PROCESSING] ID: " + dxReport.getId().getValue());


			Identifier identidfier = IdentifierUtil.findBySystem(dxReport.getIdentifier(), "accessionNumber");
			String accessionNumber = identidfier == null ? "" : identidfier.getValue().getValue();

			// Organization
			Reference orgReference = dxReport.getPerformer();
			String orgId = orgReference == null ? "" : orgReference.getReference().getValue();

			//find ImagingStudy
			ImagingStudy imgStudy = imgStudyRepo.findByOrgAccessionNo(orgId, accessionNumber);

			if (imgStudy == null) {
				System.out.println("ImagingStudy not found.");
			}

			setDxReportCodeText(dxReport, imgStudy);
			System.out.println("Done with successed: " + dxReportRepo.save(dxReport));
		}
	}

	/**
	 * 將 Tag.StudyId 及 Tag.StudyDescription 提供的值寫入 DiagnosticReport.code.text
	 */
	public static void setDxReportCodeText(DiagnosticReport dxReport, ImagingStudy imgStudy) {
		String codeText = "ImagingStudy not exist or incomplete";

		if (imgStudy != null) {
			String studyId = "";

			if (!imgStudy.getIdentifier().isEmpty() &&
				imgStudy.getIdentifier().get(0).getValue() != null) {
				studyId = imgStudy.getIdentifier().get(0).getValue().getValue();
			}

			String studyDescription = imgStudy.getDescription() == null ? "" : imgStudy.getDescription().getValue();

			if (!studyId.isEmpty() || !studyDescription.isEmpty()) {
				codeText = studyId + " " + studyDescription;
				codeText = codeText.trim();
			}
		}

		PromiseSetter.set(dxReport, "code.text.value", codeText);
	}
}
