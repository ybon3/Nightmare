package jb.lazywork.dtc.fhir.longcare;

import com.dtc.fhir.gwt.CodeableConcept;
import com.dtc.fhir.gwt.Coding;
import com.dtc.fhir.gwt.Condition;
import com.dtc.fhir.gwt.Reference;
import com.dtc.fhir.gwt.util.PromiseSetter;

import ca.uhn.fhir.model.dstu2.resource.Patient;

public class ConditionData {
	private static String system = "http://www.datacom.com.tw/Condition";

	//管路
	Condition buildCondition01(Patient patient) {
		String code = "custom01";
		Condition data = new Condition();
		PromiseSetter.set(data, "id.value", patient.getId().getValue() + "." + code);
		data.setCategory(createCodeable(system, code, "管路"));
		data.setCode(createCodeable(system, code + "_02", "鼻胃管"));
		data.setPatient(toReference(patient));
		return data;
	}

	//身心障礙
	Condition buildCondition02(Patient patient) {
		String code = "custom02";
		Condition data = new Condition();
		PromiseSetter.set(data, "id.value", patient.getId().getValue() + "." + code);
		data.setCategory(createCodeable(system, code, "身心障礙"));
		data.setCode(createCodeable(system, code + "_y", "有"));
		data.getBodySite().add(createCodeable(system, code + "_lowerBody", "下半身"));
		data.setSeverity(createCodeable(system, code + "_critical", "無法自理"));
		data.setPatient(toReference(patient));
		return data;
	}

	//壓傷
	Condition buildCondition03(Patient patient) {
		String code = "custom03";
		Condition data = new Condition();
		PromiseSetter.set(data, "id.value", patient.getId().getValue() + "." + code);
		data.setCategory(createCodeable(system, code, "壓傷"));
		data.setCode(createCodeable(system, code + "_y", "有"));
		data.getBodySite().add(createCodeable(system, code + "_lowerBody", "下半身"));
		data.setSeverity(createCodeable(system, code + "_critical", "嚴重"));
		PromiseSetter.set(data, "notes.value", "180 平方公分");
		data.setPatient(toReference(patient));
		return data;
	}

	//疾病狀況
	Condition buildCondition04(Patient patient) {
		String code = "custom04";
		Condition data = new Condition();
		PromiseSetter.set(data, "id.value", patient.getId().getValue() + "." + code);
		data.setCategory(createCodeable(system, code, "疾病狀況"));
		data.setCode(createCodeable(system, code + "_01", "高血壓"));
		data.setPatient(toReference(patient));
		return data;
	}

	//個案主要問題與需求
	Condition buildCondition05(Patient patient) {
		String code = "custom05";
		Condition data = new Condition();
		PromiseSetter.set(data, "id.value", patient.getId().getValue() + "." + code);
		data.setCategory(createCodeable(system, code, "個案主要問題與需求"));
		PromiseSetter.set(data, "notes.value", "覺得無法動彈非常沮喪 ...");
		data.setPatient(toReference(patient));
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

