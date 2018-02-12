package jb.lazywork.dtc.fhir.longcare;

import com.dtc.fhir.gwt.CarePlan;
import com.dtc.fhir.gwt.CarePlanActivity;
import com.dtc.fhir.gwt.CarePlanDetail;
import com.dtc.fhir.gwt.CodeableConcept;
import com.dtc.fhir.gwt.Coding;
import com.dtc.fhir.gwt.Reference;
import com.dtc.fhir.gwt.util.PromiseSetter;

import ca.uhn.fhir.model.dstu2.resource.Patient;

public class CarePlanData {
	private static String system = "http://www.datacom.com.tw/CarePlan";

	CarePlan buildCarePlan01(Patient patient) {
		String code = "careService";
		String subCode = "homeService";
		CarePlan data = new CarePlan();
		PromiseSetter.set(data, "id.value", patient.getId().getValue() + "." + code + "." + subCode);
		data.getCategory().add(createCodeable(system, code, "照顧服務"));

		CarePlanActivity carePlanActivity = new CarePlanActivity();
		CarePlanDetail detail = new CarePlanDetail();
		detail.setCategory(createCodeable(system, subCode, "居家服務"));
		carePlanActivity.setDetail(detail);

		data.getActivity().add(carePlanActivity);
		data.setSubject(toReference(patient));
		return data;
	}

	public CodeableConcept createCodeable(String system, String code, String display) {
		CodeableConcept data = new CodeableConcept();
		Coding coding = new Coding();
		PromiseSetter.set(coding, "system.value", system);
		PromiseSetter.set(coding, "code.value", code);
		PromiseSetter.set(coding, "display.value", display);

		data.getCoding().add(coding);
		data.setText(coding.getDisplay());
		return data;
	}

	public Reference toReference(Patient p) {
		Reference data = new Reference();
		PromiseSetter.set(data, "reference.value", "Patient/" + p.getId().getValue());
		return data;
	}
}
