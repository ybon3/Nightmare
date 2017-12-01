package jb.lazywork.dtc.fidxc;

import com.dtc.fhir.gwt.Practitioner;
import com.dtc.fhir.gwt.ResourceContainer;
import com.dtc.fhir.repository.gwt.BaseGwtRepo;

public class PractitionerGwtRepo extends BaseGwtRepo<Practitioner> {
	public PractitionerGwtRepo(String baseUrl) {
		super(baseUrl);
	}

	@Override
	protected Practitioner getResource(ResourceContainer resourceContainer) {
		return resourceContainer.getPractitioner();
	}
}
