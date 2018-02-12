package jb.lazywork.dtc.fhir.longcare;

import ca.uhn.fhir.model.dstu2.resource.Organization;

public class OrganizationData {
	Organization data = new Organization();

	String name = "台大醫院";

	public OrganizationData() {
		data.setId("ORGN.TaiwanUniversity");
		data.setName(name);
	}
}
