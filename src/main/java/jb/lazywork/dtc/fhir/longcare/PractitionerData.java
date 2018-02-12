package jb.lazywork.dtc.fhir.longcare;

import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.Organization;
import ca.uhn.fhir.model.dstu2.resource.Practitioner;
import ca.uhn.fhir.model.dstu2.valueset.ContactPointSystemEnum;

public class PractitionerData {
	Practitioner data = new Practitioner();

	String name = "台大的醫生";
	String otherId = "Z987654321";
	String phone = "02-22226666";
	String fax = "02-22226667";

	public PractitionerData(Organization org) {
		data.setId("PRAC." + otherId);
		data.getName().setText(name);
		data.getName().getGivenFirstRep().setValue(name);
		data.getTelecomFirstRep().setSystem(ContactPointSystemEnum.PHONE);
		data.getTelecomFirstRep().setValue(phone);
		data.getTelecomFirstRep().setSystem(ContactPointSystemEnum.FAX);
		data.getTelecomFirstRep().setValue(fax);
		data.getPractitionerRoleFirstRep().setManagingOrganization(new ResourceReferenceDt(org));
	}
}
