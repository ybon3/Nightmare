package jb.lazywork.dtc.fhir.longcare;

import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.resource.Person;
import ca.uhn.fhir.model.dstu2.valueset.IdentityAssuranceLevelEnum;

public class PersonData {
	Person data = new Person();

	String otherId = "A123456789";
	String name = "華英雄";
	String ecoStatus = "土豪、富二代";

	PersonData(Patient patient) {
		data.setId(otherId);
		data.getNameFirstRep().setText(name);
		data.getLinkFirstRep().getTarget().setResource(patient);
		data.getLinkFirstRep().setAssurance(IdentityAssuranceLevelEnum.LEVEL_4);
	}
}
