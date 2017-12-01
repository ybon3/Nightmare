package jb.lazywork.dtc.fhir.gwt.repo;

import java.util.List;

import com.dtc.fhir.gwt.Organization;
import com.dtc.fhir.gwt.ResourceContainer;
import com.dtc.fhir.repository.Constant;
import com.dtc.fhir.repository.gwt.BaseGwtRepo;

public class OrganizationGwtRepo extends BaseGwtRepo<Organization> {
	public OrganizationGwtRepo(String baseUrl) {
		super(baseUrl);
	}

	@Override
	protected Organization getResource(ResourceContainer resourceContainer) {
		return resourceContainer.getOrganization();
	}

	public List<Organization> getVassal(Organization org) {
		return searchAndExpand(
			getResourceType() + "?partof=Organization%2F" + org.getId().getValue() +
			"&_count=" + Constant.FHIR_COUNT_LIMIT
		);
	}
}
