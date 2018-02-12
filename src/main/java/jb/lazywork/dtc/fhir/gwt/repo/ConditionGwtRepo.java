package jb.lazywork.dtc.fhir.gwt.repo;

import com.dtc.fhir.gwt.Condition;
import com.dtc.fhir.gwt.ResourceContainer;
import com.dtc.fhir.repository.gwt.BaseGwtRepo;

public class ConditionGwtRepo extends BaseGwtRepo<Condition> {
	public ConditionGwtRepo(String baseUrl) {
		super(baseUrl);
	}

	@Override
	protected Condition getResource(ResourceContainer resourceContainer) {
		return resourceContainer.getCondition();
	}
}