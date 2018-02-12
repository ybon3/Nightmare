package jb.lazywork.dtc.fhir.longcare;

import com.dtc.fhir.gwt.CarePlan;
import com.dtc.fhir.gwt.Condition;
import com.dtc.fhir.gwt.DomainResource;

import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.BaseResource;
import ca.uhn.fhir.model.dstu2.resource.Encounter;
import ca.uhn.fhir.model.dstu2.resource.ListResource;
import ca.uhn.fhir.model.dstu2.resource.Organization;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.resource.Person;
import ca.uhn.fhir.model.dstu2.resource.Practitioner;
import ca.uhn.fhir.model.dstu2.resource.Questionnaire;
import ca.uhn.fhir.model.dstu2.resource.QuestionnaireResponse;

public class BuildData {
	private static FhirService service = new FhirService("http://192.168.111.5:5013/baseDstu2");
	private static String listId = "LIST.SAMPLE.20180212X123456789";


	public static void main(String[] args) {
		//Organization
		Organization organization = new OrganizationData().data;
		save(organization);

		//Practitioner
		Practitioner practitioner = new PractitionerData(organization).data;
		save(practitioner);

		//Patent
		Patient patient = new PatientData("XXXXX12345").data;
		save(patient);

		//Person
		Person person = new PersonData(patient).data;
		save(person);

		//Questionnaire
		QuestionnaireData questionnaire = new QuestionnaireData();
		Questionnaire qB1 = questionnaire.buildB1();
		Questionnaire qE = questionnaire.buildE();
		Questionnaire qH1 = questionnaire.buildH1();
		Questionnaire qG4d = questionnaire.buildG4d();
		Questionnaire qF = questionnaire.buildF();
		save(qB1);
		save(qE);
		save(qH1);
		save(qG4d);
		save(qF);

		//QuestionnaireResponse
		QuestionRespData questionnaireResponse = new QuestionRespData();
		QuestionnaireResponse qrB1 = questionnaireResponse.buildB1Resp(patient, qB1);
		QuestionnaireResponse qrE = questionnaireResponse.buildEResp(patient, qE);
		QuestionnaireResponse qrH1 = questionnaireResponse.buildH1Resp(patient, qH1);
		QuestionnaireResponse qrG4d = questionnaireResponse.buildG4dResp(patient, qG4d);
		QuestionnaireResponse qrF = questionnaireResponse.buildFResp(patient, qF);
		save(qrB1);
		save(qrE);
		save(qrH1);
		save(qrG4d);
		save(qrF);

		//Condition
		ConditionData condition = new ConditionData();
		Condition condition01 = condition.buildCondition01(patient);
		Condition condition02 = condition.buildCondition02(patient);
		Condition condition03 = condition.buildCondition03(patient);
		Condition condition04 = condition.buildCondition04(patient);
		Condition condition05 = condition.buildCondition05(patient);
		save(condition01);
		save(condition02);
		save(condition03);
		save(condition04);
		save(condition05);

		//CarePlan
		CarePlanData carePlan = new CarePlanData();
		CarePlan carePlan01 = carePlan.buildCarePlan01(patient);
		save(carePlan01);

		//EncounterData
		Encounter encounter = new EncounterData(organization, patient).data;
		save(encounter);

		ListResource listResource = new ListResource();
		listResource.setId(listId);
		listResource.addIdentifier(IdentifierUtil.create("Creator", "DTC"));
		listResource.setSubject(new ResourceReferenceDt(patient));
		listResource.setSource(new ResourceReferenceDt(practitioner));
		listResource.addEntry().setItem(new ResourceReferenceDt(person));
		listResource.addEntry().setItem(new ResourceReferenceDt(qrB1));
		listResource.addEntry().setItem(new ResourceReferenceDt(qrE));
		listResource.addEntry().setItem(new ResourceReferenceDt(qrH1));
		listResource.addEntry().setItem(new ResourceReferenceDt(qrG4d));
		listResource.addEntry().setItem(new ResourceReferenceDt(qrF));
		listResource.addEntry().setItem(new ResourceReferenceDt("Condition/" + condition01.getId().getValue()));
		listResource.addEntry().setItem(new ResourceReferenceDt("Condition/" + condition02.getId().getValue()));
		listResource.addEntry().setItem(new ResourceReferenceDt("Condition/" + condition03.getId().getValue()));
		listResource.addEntry().setItem(new ResourceReferenceDt("Condition/" + condition04.getId().getValue()));
		listResource.addEntry().setItem(new ResourceReferenceDt("Condition/" + condition05.getId().getValue()));
		listResource.addEntry().setItem(new ResourceReferenceDt("CarePlan/" + carePlan01.getId().getValue()));
		listResource.addEntry().setItem(new ResourceReferenceDt(encounter));
		save(listResource);
	}

	private static void save(BaseResource resource) {
		System.out.println(resource.getResourceName() + ": " + service.save(resource).getOperationOutcome().toString());
	}

	private static void save(DomainResource resource) {
		System.out.println(resource.getClass().getSimpleName() + ": " + service.save(resource));
	}
}
