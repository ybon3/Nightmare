package jb.lazywork.dtc.dialysisQC;

import ca.uhn.fhir.model.dstu2.resource.Provenance;

public class DeleteFakeProvenance {
	public static void main(String[] args) {
		String baseUrl = Context.BASE_URL;
		ProvenanceHapiFhirRepository repo = new ProvenanceHapiFhirRepository(baseUrl);
		
		String[] idList = new String[]{
				"2591994",
				"2591992",
				"2591990",
				"2591988",
				"2591986",
				"2591984"};
		
		int count = 0;
		for (String id : idList) {
			Provenance p = repo.findOne(id);
			if (p != null) {
				repo.delete(repo.findOne(id));
				count++;
			}
		}
		
		System.out.println("count: " + count);
	}
}
