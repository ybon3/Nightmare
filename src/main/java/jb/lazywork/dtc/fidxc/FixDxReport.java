package jb.lazywork.dtc.fidxc;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;

/**
 * 用來修正 DxReport 因為沒有設定 code.text 導致無法正確產生  text.div 的內容
 */
public class FixDxReport {
	private String url = "http://localhost:8013/baseDstu2/";
    private static IGenericClient client;

	public static void main(String[] args) {
		new FixDxReport().run();
	}

	public FixDxReport() {
		try {
            FhirContext ctx = new FhirContext().forDstu2();
            ctx.getRestfulClientFactory().setConnectionRequestTimeout(120000);
            ctx.getRestfulClientFactory().setConnectTimeout(120000);
            ctx.getRestfulClientFactory().setSocketTimeout(120000);
            client = ctx.newRestfulGenericClient(url);
            LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
            loggingInterceptor.setLogRequestSummary(true);
            // loggingInterceptor.setLogRequestBody(true);
            client.registerInterceptor(loggingInterceptor);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}


	public void run() {

		Bundle bundle = client.search().forResource(DiagnosticReport.class).execute();

		for (BundleEntry entity : bundle.getEntries()) {
			DiagnosticReport r = (DiagnosticReport)entity.getResource();
			System.out.println(r.getId().getValue());
			System.out.println(r.getText().getDiv().toString());

			//r.setText(null);
			r.setCode(null);
			//r.getCode().setText("AFKKKKKKKKKKKKKKKKK");

			//client.update(r.getId(), r);
		}
	}
}
