package jb.lazywork.dtc.fhir.longcare;

import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;

public class IdentifierUtil {
	public static IdentifierDt create(String system, String value) {
		IdentifierDt idf = new IdentifierDt();
		idf.setSystem(system);
		idf.setValue(value);
		return idf;
	}
}
