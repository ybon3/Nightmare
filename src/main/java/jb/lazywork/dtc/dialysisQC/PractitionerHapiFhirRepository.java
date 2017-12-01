package jb.lazywork.dtc.dialysisQC;

import com.dtc.fhir.repository.hapi.GenericHapiRepo;

import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu2.resource.Practitioner;

public class PractitionerHapiFhirRepository extends GenericHapiRepo<Practitioner>{

	public PractitionerHapiFhirRepository(String baseUrl) {
		super(baseUrl, Practitioner.class);
	}

	@Override
	protected Practitioner transformation(IResource resource) {
		return (Practitioner) resource;
	}
}
