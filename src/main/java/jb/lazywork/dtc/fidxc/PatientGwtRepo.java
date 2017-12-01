package jb.lazywork.dtc.fidxc;

import com.dtc.fhir.gwt.Patient;
import com.dtc.fhir.gwt.ResourceContainer;
import com.dtc.fhir.repository.gwt.BaseGwtRepo;

public class PatientGwtRepo extends BaseGwtRepo<Patient> {
	public PatientGwtRepo(String baseUrl) {
		super(baseUrl);
	}

	@Override
	protected Patient getResource(ResourceContainer resourceContainer) {
		return resourceContainer.getPatient();
	}
}
