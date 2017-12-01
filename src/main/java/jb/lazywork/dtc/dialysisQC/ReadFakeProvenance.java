package jb.lazywork.dtc.dialysisQC;

import java.util.ArrayList;
import java.util.List;

import com.dtc.fhir.gwt.Provenance;
import com.dtc.fhir.gwt.ProvenanceAgent;

public class ReadFakeProvenance {
	String baseUrl = "http://192.168.1.200:8000/fhir-jpaserver_v13/baseDstu2/";
	ProvenanceGwtRepo repo = new ProvenanceGwtRepo(baseUrl);


	public static void main(String[] args) {
		new ReadFakeProvenance().run();
	}

	public void run() {
//		List<com.dtc.fhir.gwt.Provenance> list = findByTitle("");
//		System.out.println(list == null?"null":list.size());
//		for (com.dtc.fhir.gwt.Provenance p : list) {
//			System.out.println(p.getId().getValue());
//		}

		List<com.dtc.fhir.gwt.Provenance> list = findNoActor();
		System.out.println(list == null?"null":list.size());
		for (com.dtc.fhir.gwt.Provenance p : list) {
			System.out.println(p.getId().getValue());
		}
	}

	// fail
	public List<Provenance> findByTitle(String title) {
		List<Provenance> result = new ArrayList<>();
		List<Provenance> list = repo.findAll();

		for (com.dtc.fhir.gwt.Provenance p : list) {
			if (p.getActivity().getCoding().get(0).getDisplay().getValue().indexOf(title) != -1) {
				result.add(p);
			}
		}

		return result;
	}

	// fail
	public List<Provenance> findNoActor() {
		List<Provenance> result = new ArrayList<>();
		List<Provenance> list = repo.findAll();

		for (com.dtc.fhir.gwt.Provenance p : list) {
			if (p.getAgent().isEmpty()) {
				result.add(p);
				continue;
			} else {
				boolean isNotFind = true;
				for (ProvenanceAgent agent : p.getAgent()) {
					if (agent.getRole().getCode().getValue().equals("receiver")) {
						isNotFind = false;
					}
				}

				if (isNotFind) result.add(p);
			}
		}

		return result;
	}
}
