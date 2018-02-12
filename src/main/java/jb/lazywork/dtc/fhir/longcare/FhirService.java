package jb.lazywork.dtc.fhir.longcare;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.instance.model.api.IBaseResource;

import com.dtc.fhir.gwt.CarePlan;
import com.dtc.fhir.gwt.Condition;
import com.dtc.fhir.gwt.DomainResource;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.AuditEvent;
import ca.uhn.fhir.model.dstu2.resource.BaseResource;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.ImagingStudy;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import ca.uhn.fhir.rest.gclient.IQuery;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import jb.lazywork.dtc.fhir.gwt.repo.CarePlanGwtRepo;
import jb.lazywork.dtc.fhir.gwt.repo.ConditionGwtRepo;

public class FhirService {
	public static final String BASE_URL = "http://localhost:8013/baseDstu2";
	private ConditionGwtRepo conditionGwtRepo;
	private CarePlanGwtRepo carePlanGwtRepo;

	private IGenericClient client;

	public FhirService() {
		this(BASE_URL);
	}

	public FhirService(String connectUrl) {
		conditionGwtRepo = new ConditionGwtRepo(connectUrl);
		carePlanGwtRepo = new CarePlanGwtRepo(connectUrl);

		try {
			FhirContext ctx = FhirContext.forDstu2();
			ctx.getRestfulClientFactory().setConnectionRequestTimeout(120000);
			ctx.getRestfulClientFactory().setConnectTimeout(120000);
			ctx.getRestfulClientFactory().setSocketTimeout(120000);
			client = ctx.newRestfulGenericClient(connectUrl);
			LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
			loggingInterceptor.setLogRequestSummary(true);
			client.registerInterceptor(loggingInterceptor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MethodOutcome create(IBaseResource resource) {
		return client.create().resource(resource).execute();
	}

	public MethodOutcome save(BaseResource resource) {
		if (resource.getId().getValue() != null) {
			return client.update(resource.getId().getValue(), resource);
		} else {
			return client.create().resource(resource).execute();
		}
	}

	public boolean save(DomainResource resource) {
		if (resource instanceof Condition) {
			return conditionGwtRepo.save((Condition)resource);
		} else if (resource instanceof CarePlan) {
			return carePlanGwtRepo.save((CarePlan)resource);
		} else {
			return false;
		}
	}

	public List<AuditEvent> findAudiEvent(String institution, String patientId, String accessionNumber) {



		if (StringUtils.isBlank(institution) &&
			StringUtils.isBlank(patientId) &&
			StringUtils.isBlank(accessionNumber)) {
			return new ArrayList<AuditEvent>();
		}

		IQuery<ca.uhn.fhir.model.api.Bundle> query = client.search().forResource(AuditEvent.class);

		if (StringUtils.isNotBlank(institution)) {
			query.where(AuditEvent.SUBTYPE.exactly().systemAndCode("institution", institution));
		}

		if (StringUtils.isNotBlank(patientId)) {
			query.where(AuditEvent.SUBTYPE.exactly().systemAndCode("patientId", patientId));
		}

		if (StringUtils.isNotBlank(accessionNumber)) {
			query.where(AuditEvent.SUBTYPE.exactly().systemAndCode("accessionNumber", accessionNumber));
		}

		Bundle bundle = query.returnBundle(Bundle.class).execute();
		return bundle.getAllPopulatedChildElementsOfType(AuditEvent.class);
	}

	public Patient findPatient(String institutionCode, String patientId) {
		try {
			return client.read(Patient.class, toFhirPatientId(institutionCode, patientId));
		} catch (ResourceNotFoundException e) {
			return null; // not found
		}
	}

	public ImagingStudy findImagingStudy(String institutionCode, String patientId, String accessionNo) {
		IQuery<ca.uhn.fhir.model.api.Bundle> query = client.search().forResource(ImagingStudy.class);

		query.where(ImagingStudy.ACCESSION.exactly().code(accessionNo));
		query.where(ImagingStudy.PATIENT.hasId(toFhirPatientId(institutionCode, patientId)));

		try {
			Bundle bundle = query.returnBundle(Bundle.class).execute();
			return (ImagingStudy)bundle.getEntryFirstRep().getResource();
		} catch (ResourceNotFoundException e) {
			return null; // not found
		}
	}

	/**
	 * 轉換成 FHIR 規則的 Patient.id
	 */
	private String toFhirPatientId(String institutionCode, String patientId) {
		return "PATT." + institutionCode + "." + patientId;
	}
}

