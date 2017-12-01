package jb.lazywork.dtc.dialysisQC;

import com.dtc.fhir.gwt.Provenance;
import com.dtc.fhir.gwt.ResourceContainer;
import com.dtc.fhir.repository.gwt.BaseGwtRepo;

public class ProvenanceGwtRepo extends BaseGwtRepo<Provenance> {
	public ProvenanceGwtRepo(String baseUrl) {
		super(baseUrl);
	}

	@Override
	protected String getResourceType() {
		return "Provenance";
	}

	@Override
	protected Provenance getResource(ResourceContainer resourceContainer) {
		return resourceContainer.getProvenance();
	}
}