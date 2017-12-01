package jb.lazywork.dtc.fhir.gwt.repo;

import java.util.List;

import com.dtc.fhir.gwt.DiagnosticReport;
import com.dtc.fhir.gwt.ResourceContainer;
import com.dtc.fhir.repository.Constant;
import com.dtc.fhir.repository.gwt.BaseGwtRepo;

public class DxReportGwtRepo extends BaseGwtRepo<DiagnosticReport> {

	public DxReportGwtRepo(String baseUrl) {
		super(baseUrl);
	}

	@Override
	protected DiagnosticReport getResource(ResourceContainer resourceContainer) {
		return resourceContainer.getDiagnosticReport();
	}

	public List<DiagnosticReport> findByPatientId(String patientId) {
		// first time
		StringBuilder sb = new StringBuilder(getResourceType());
		sb.append("?").append(Constant.PARAM_COUNT).append("=").append(Constant.FHIR_COUNT_LIMIT);
		sb.append("&").append("subject=").append(patientId);
		return searchAndExpand(sb.toString());
	}

	/**
	 * @return 指定醫院、指定檢查的所有報告
	 */
	public List<DiagnosticReport> findByOrgStudy(String orgId, String studyId) {
		return searchAndExpand(
			getResourceType() +
			"?identifier=accessionNumber%7C" + studyId +
			"&identifier=organization%7C" + orgId
		);
	}

	public List<DiagnosticReport> findByStudyId(String studyId) {
		// first time
		StringBuilder sb = new StringBuilder(getResourceType());
		sb.append("?").append(Constant.PARAM_COUNT).append("=").append(Constant.FHIR_COUNT_LIMIT);
		sb.append("&").append("identifier=").append("accessionNumber%7C").append(studyId);
		return searchAndExpand(sb.toString());
	}
}