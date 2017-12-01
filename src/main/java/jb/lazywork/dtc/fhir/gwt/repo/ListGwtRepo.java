package jb.lazywork.dtc.fhir.gwt.repo;

import com.dtc.fhir.gwt.ListDt;
import com.dtc.fhir.gwt.ResourceContainer;
import com.dtc.fhir.repository.gwt.BaseGwtRepo;

public class ListGwtRepo extends BaseGwtRepo<ListDt> {
	public ListGwtRepo(String baseUrl) {
		super(baseUrl);
	}

	@Override
	protected ListDt getResource(ResourceContainer resourceContainer) {
		return resourceContainer.getList();
	}
}
