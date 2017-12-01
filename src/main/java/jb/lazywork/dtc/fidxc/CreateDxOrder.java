package jb.lazywork.dtc.fidxc;

import java.util.List;

import com.dtc.fhir.gwt.DiagnosticOrder;
import com.dtc.fhir.gwt.DiagnosticOrderStatus;
import com.dtc.fhir.gwt.DiagnosticOrderStatusList;
import com.dtc.fhir.gwt.Patient;
import com.dtc.fhir.gwt.Practitioner;
import com.dtc.fhir.gwt.Reference;

public class CreateDxOrder {
	private DiagnosticOrderGwtRepo dxOrderGwtRepo = new DiagnosticOrderGwtRepo(Constant.BASE_URL);
	private PractitionerGwtRepo practitionerGwtRepo = new PractitionerGwtRepo(Constant.BASE_URL);
	private PatientGwtRepo patientGwtRepo = new PatientGwtRepo(Constant.BASE_URL);

	public static void main(String[] args) {
		CreateDxOrder ins = new CreateDxOrder();

		// ins.printPatient();
		 ins.printPractitioner();

		//ins.build();
	}

	// 建立標準初始化的 dxOrder
	public boolean build() {
		Reference subject = Constant.getSubject();
		Reference orderer = Constant.getOrderer();
		DiagnosticOrder resource = new DiagnosticOrder();
		DiagnosticOrderStatus defaultStatus = Constant.toStatus(DiagnosticOrderStatusList.DRAFT);

		resource.setStatus(defaultStatus);
		resource.setSubject(subject);
		resource.setOrderer(orderer);
		resource.getEvent().add(Constant.buildEvent(defaultStatus, null));

		return dxOrderGwtRepo.save(resource);
	}

	// 印出 Patient，方便 copy-paste
	public void printPatient() {
		List<Patient> list = patientGwtRepo.findAll();
		for (Patient p : list) {
			String name = p.getName().get(0).getText().getValue();
			String referenceId = Patient.class.getSimpleName() + "/" + p.getId().getValue();
			System.out.println("{\"" + name + "\",\"" + referenceId + "\"},");
		}
	}

	// 印出 Practitioner，方便 copy-paste
	public void printPractitioner() {
		List<Practitioner> list = practitionerGwtRepo.findAll();
		for (Practitioner p : list) {
			String name = p.getName().getText().getValue();
			String referenceId = Practitioner.class.getSimpleName() + "/" + p.getId().getValue();
			System.out.println("{\"" + name + "\",\"" + referenceId + "\"},");
			// System.out.println("new FooBar(\""+p.getId().getValue()+"\",\""+name+"\"),");
		}
	}
}
