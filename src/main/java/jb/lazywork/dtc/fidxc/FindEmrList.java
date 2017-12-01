package jb.lazywork.dtc.fidxc;

import java.util.List;

import com.dtc.fhir.gwt.Identifier;
import com.dtc.fhir.gwt.ListDt;
import com.dtc.fhir.gwt.util.IdentifierUtil;

import jb.lazywork.dtc.fhir.gwt.repo.ListGwtRepo;

public class FindEmrList {
	private static final String FHIR_URL = "http://192.168.111.3:8013/baseDstu2/";
	private ListGwtRepo listGwtRepo = new ListGwtRepo(FHIR_URL);

	public static void main(String[] args) {
		new FindEmrList().run01();
	}

	public void run01() {
		List<ListDt> result = listGwtRepo.findAll();

		for (ListDt item : result) {
			Identifier identifier = IdentifierUtil.findBySystem(item.getIdentifier(), "Creator");

			if (identifier != null && "EMR".equals(identifier.getValue().getValue())) {
				System.out.println("[EMR] " + item.getId().getValue());
			}
		}
	}
}
