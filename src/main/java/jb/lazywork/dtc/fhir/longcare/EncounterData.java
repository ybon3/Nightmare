package jb.lazywork.dtc.fhir.longcare;

import java.util.Date;

import ca.uhn.fhir.model.api.TemporalPrecisionEnum;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.PeriodDt;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.Encounter;
import ca.uhn.fhir.model.dstu2.resource.Organization;
import ca.uhn.fhir.model.dstu2.resource.Patient;

public class EncounterData {
	Encounter data = new Encounter();

	EncounterData(Organization org, Patient patient) {
		data.setId(org.getId() + "." + patient.getId());
		data.setServiceProvider(new ResourceReferenceDt(org));

		//period
		PeriodDt period = new PeriodDt();
		period.setStart(new Date(new Date().getTime() - (7 * 86400000)), TemporalPrecisionEnum.DAY);
		period.setEnd(new Date(), TemporalPrecisionEnum.DAY);
		data.setPeriod(period);

		//dischargeDisposition
		CodeableConceptDt dischargeDisposition = new CodeableConceptDt();
		CodingDt coding = dischargeDisposition.addCoding();
		coding.setSystem("http://www.datacom.com.tw/CodeableConcept/DischargeDisposition");
		coding.setCode("bedNumber");
		coding.setDisplay("B701");
		dischargeDisposition.setText("B701");
		data.getHospitalization().setDischargeDisposition(dischargeDisposition);
	}
}
