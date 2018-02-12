package jb.lazywork.dtc.fhir.longcare;

import java.util.Date;

import ca.uhn.fhir.model.api.ExtensionDt;
import ca.uhn.fhir.model.dstu2.composite.AddressDt;
import ca.uhn.fhir.model.dstu2.composite.ContactPointDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.resource.Patient.Contact;
import ca.uhn.fhir.model.dstu2.valueset.AddressTypeEnum;
import ca.uhn.fhir.model.dstu2.valueset.AddressUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.dstu2.valueset.ContactPointSystemEnum;
import ca.uhn.fhir.model.primitive.StringDt;


public class PatientData {
	Patient data = new Patient();

	String name = "華英雄";
	String otherId = "A123456789";
	String phone = "02-22458558";
	String contactName = "華壘";
	String contactRelationshipCode = "parent";
	String contactRelationship = "父子";
	String contactPhone = "02-22258951";
	String contactMobile = "0987654321";

	String registrationAddr = "台北市信義區信義路 456 號";
	String liveAddr = "同戶籍地";
	String ecoStatus = "土豪兼富二代";

	PatientData() {
		this(null);
	}

	PatientData(String patientId) {
		data.setId(patientId);
		data.addIdentifier(IdentifierUtil.create("otherId", otherId));
		data.getNameFirstRep().setText(name);
		data.getNameFirstRep().getGivenFirstRep().setValue(name);
		data.getTelecomFirstRep().setSystem(ContactPointSystemEnum.PHONE);
		data.getTelecomFirstRep().setValue(phone);
		data.setBirthDateWithDayPrecision(new Date());
		data.setGender(AdministrativeGenderEnum.MALE);
		data.addAddress(createAddress(registrationAddr));
		data.addAddress(createAddress(liveAddr));

		//contact
		Contact contact = data.getContactFirstRep();
		contact.getRelationshipFirstRep().getCodingFirstRep().setCode(contactRelationshipCode);
		contact.getRelationshipFirstRep().setText(contactRelationship);
		contact.getName().setText(contactName);
		contact.getName().getGivenFirstRep().setValue(contactName);
		contact.getTelecomFirstRep().setSystem(ContactPointSystemEnum.PHONE);
		contact.getTelecomFirstRep().setValue(contactPhone);
		contact.getTelecom().add(createContactPoint(ContactPointSystemEnum.PHONE, contactMobile));

		//經濟狀況
		data.addUndeclaredExtension(createExtension("經濟狀況", ecoStatus));
	}

	ContactPointDt createContactPoint(ContactPointSystemEnum system, String value) {
		ContactPointDt contact = new ContactPointDt();
		contact.setSystem(system);
		contact.setValue(value);
		return contact;
	}

	ExtensionDt createExtension(String url, String value) {
		ExtensionDt extension = new ExtensionDt();
		extension.setUrl(url);
		extension.setValue(new StringDt(value));
		return extension;
	}

	AddressDt createAddress(String text) {
		AddressDt addr = new AddressDt();
		addr.setUse(AddressUseEnum.HOME);
		addr.setType(AddressTypeEnum.PHYSICAL);
		addr.setText(text);
		return addr;
	}
}
