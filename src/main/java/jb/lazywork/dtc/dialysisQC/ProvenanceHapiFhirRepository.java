package jb.lazywork.dtc.dialysisQC;

import com.dtc.fhir.repository.hapi.GenericHapiRepo;

import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu2.resource.Provenance;

public class ProvenanceHapiFhirRepository extends GenericHapiRepo<Provenance>{

	public ProvenanceHapiFhirRepository(String baseUrl) {
		super(baseUrl, Provenance.class);
	}

	@Override
	protected Provenance transformation(IResource resource) {
		return (Provenance) resource;
	}
}
