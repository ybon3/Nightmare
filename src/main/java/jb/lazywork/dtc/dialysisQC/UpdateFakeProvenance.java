package jb.lazywork.dtc.dialysisQC;

import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.Provenance;
import ca.uhn.fhir.model.dstu2.resource.Provenance.Agent;

public class UpdateFakeProvenance {

	private static String baseUrl = Context.BASE_URL;
	public static void main(String[] args) {

		ProvenanceGwtRepo repo = new ProvenanceGwtRepo(baseUrl);

		updateBulletin();
//
//		List<com.dtc.fhir.gwt.Provenance> provenancelist = repo.findAll();
//		System.out.println("Size: " + provenancelist.size());
//		for (com.dtc.fhir.gwt.Provenance p : provenancelist) {
//			if (p.getReason().isEmpty()) updateIssuTrack(p.getId().getValue());
//		}

//		List<com.dtc.fhir.gwt.Provenance> provenancelist = repo.findAll();
//		System.out.println("Size: " + provenancelist.size());
//		for (com.dtc.fhir.gwt.Provenance p : provenancelist) {
//			if (p.getAgent().isEmpty()) updateAgent(p.getId().getValue());
//		}
	}

	public static void updateIssuTrack(String id) {
		ProvenanceHapiFhirRepository repo = new ProvenanceHapiFhirRepository(baseUrl);

		// Update to ISSUE
		Provenance p = repo.findOne(id);
		CodeableConceptDt reason = p.getReasonFirstRep();
		CodingDt coding = reason.getCodingFirstRep();
		coding.setCode(ProvenanceReason.issue.name());

		repo.save(p);
	}

	public static void updateBulletin() {
		ProvenanceHapiFhirRepository repo = new ProvenanceHapiFhirRepository(baseUrl);

		// Update to BULLETIN
		Provenance p = repo.findOne("SQC430000.20160913124022");
		CodeableConceptDt reason = p.getReasonFirstRep();
		CodingDt coding = reason.getCodingFirstRep();
		coding.setCode(ProvenanceReason.bulletin.name());
		p.getAgent().clear();

		repo.save(p);
	}

	public static void updateAgent(String id) {
		ProvenanceHapiFhirRepository repo = new ProvenanceHapiFhirRepository(baseUrl);

		// Update
		Provenance p = repo.findOne(id);
		Agent agent = new Agent();
		ResourceReferenceDt ref = new ResourceReferenceDt("Organization/SQC430000");
		agent.setActor(ref);
		CodingDt codingDt = new CodingDt();
		codingDt.setCode("publisher");
		agent.setRole(codingDt);
		p.getAgent().add(agent);

		agent = new Agent();
		ref = new ResourceReferenceDt("Organization/DC731.85369039");
		agent.setActor(ref);
		codingDt = new CodingDt();
		codingDt.setCode("receiver");
		agent.setRole(codingDt);
		p.getAgent().add(agent);

		repo.save(p);
	}
}
