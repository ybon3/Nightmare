package jb.lazywork.dtc.dialysisQC;

import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.Practitioner;



public class CreateHapiPractitioner {
	private static PractitionerHapiFhirRepository repo = new PractitionerHapiFhirRepository(Context.BASE_URL);

	public static void main(String[] args) {
		repo.save(createResource("user2", "陳Ｏ生"));
		// updateResource("admin", "李Ｏ中" );

	}

	public static Practitioner createResource(String id, String name) {
		Practitioner res = new Practitioner();
		res.setId(id);

		HumanNameDt humanNameDt = new HumanNameDt();
		humanNameDt.setText(name);
		humanNameDt.addGiven().setValue(name);
		res.setName(humanNameDt);
		ResourceReferenceDt resourceReferenceDt = new ResourceReferenceDt();
		resourceReferenceDt.setReference("Organization/SQC430000");
		resourceReferenceDt.setDisplay("湖南省血透质控中心");
		res.addPractitionerRole().setManagingOrganization(resourceReferenceDt);

		return res;
	}

	public static void updateResource(String id, String name) {
		Practitioner p = repo.findOne(id);

		HumanNameDt humanNameDt = new HumanNameDt();
		humanNameDt.setText(name);
		humanNameDt.addGiven().setValue(name);
		p.setName(humanNameDt);

		ResourceReferenceDt resourceReferenceDt = new ResourceReferenceDt();
		resourceReferenceDt.setReference("Organization/SQC430000");
		resourceReferenceDt.setDisplay("湖南省血透质控中心");
		p.getPractitionerRole().clear();
		p.addPractitionerRole().setManagingOrganization(resourceReferenceDt);

		repo.save(p);
	}
}
