package jb.lazywork.dtc.fhir.gwt.repo;

import com.dtc.fhir.gwt.CarePlan;
import com.dtc.fhir.gwt.ResourceContainer;
import com.dtc.fhir.repository.gwt.BaseGwtRepo;

public class CarePlanGwtRepo extends BaseGwtRepo<CarePlan> {
	public CarePlanGwtRepo(String baseUrl) {
		super(baseUrl);
	}

	@Override
	protected CarePlan getResource(ResourceContainer resourceContainer) {
		return resourceContainer.getCarePlan();
	}
}