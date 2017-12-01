package jb.lazywork.dtc.fhir.gwt.repo;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.dtc.fhir.gwt.ImagingStudy;
import com.dtc.fhir.gwt.ResourceContainer;
import com.dtc.fhir.gwt.util.ReferenceUtil;
import com.dtc.fhir.repository.Constant;
import com.dtc.fhir.repository.gwt.BaseGwtRepo;
import com.dtc.fhir.unmarshal.GwtMarshaller;
import com.google.common.base.Preconditions;

public class ImagingStudyGwtRepo extends BaseGwtRepo<ImagingStudy> {

	public ImagingStudyGwtRepo(String baseUrl) {
		super(baseUrl);
	}

	@Override
	protected ImagingStudy getResource(ResourceContainer resourceContainer) {
		return resourceContainer.getImagingStudy();
	}

	public List<ImagingStudy> findByPatientId(String patientId) {
		StringBuilder sb = new StringBuilder(getResourceType());
		sb.append("?").append(Constant.PARAM_COUNT).append("=").append(Constant.FHIR_COUNT_LIMIT);
		sb.append("&").append("patient=").append(patientId);
		return searchAndExpand(sb.toString());
	}

	/**
	 * @return 指定醫院、指定檢查的 {@link ImagingStudy}
	 */
	public ImagingStudy findByOrgAccessionNo(String orgId, String accessionNumber) {
		//以謳開的狀況來說，指定了 org 跟 accessionNumber 就只會對應到一個
		//至於這個 ImaingStudy 下對應幾個 instance 就是另話
		List<ImagingStudy> result = searchAndExpand(
			getResourceType() + "?accession=" + orgId + "%7C" + accessionNumber
		);

		return result == null || result.isEmpty() ?
			null : result.get(0);
	}

	public List<ImagingStudy> findByStudyId(String studyId) {
		// first time
		StringBuilder sb = new StringBuilder(getResourceType());
		sb.append("?").append(Constant.PARAM_COUNT).append("=").append(Constant.FHIR_COUNT_LIMIT);
		sb.append("&").append("accession=").append(studyId);
		return searchAndExpand(sb.toString());
	}

	//Refactory 抽去 dtc-fhir
	public ImagingStudy history(String id, int i) {
		return unmarshal(fetch(getResourceType() + "/" + id + "/_history/" + i));
	}

	//TODO 暫時的處置
	//一個是因為 BaseGwtRepo 疑似有 thread-safe 的問題
	//而是要避免 save() 的邏輯有問題（後來發現即使用 PUT method 作第一次 save()，FHIR 那邊還是會註記為 POST）
	//所以乾脆獨立出 method
	public synchronized void syncUpdate(ImagingStudy result) {
		String xml = GwtMarshaller.marshal(entityClass, result);

		Preconditions.checkNotNull(xml);

		HttpPut putRequest = new HttpPut(baseUrl + ReferenceUtil.compose(result));
		putRequest.addHeader(HttpHeaders.CONTENT_TYPE, MIME_TYPE);
		StringEntity input = new StringEntity(xml, CONTENT_TYPE);
		putRequest.setEntity(input);

		HttpClient client = HttpClientBuilder.create().build();

		HttpResponse response = null;
		try {
			response = client.execute(putRequest);

			// 如果更新的 resource 找不到（id 不存在），回傳的 StatusCode = 400
			// 根據 fhir 規格說明，修改會回傳 200 or 201
			if (response.getStatusLine().getStatusCode() != 200
				&& response.getStatusLine().getStatusCode() != 201) {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeResponse(response);
		}
	}

	private void closeResponse(HttpResponse response) {
		if (response != null && response instanceof CloseableHttpResponse) {
			try {
				((CloseableHttpResponse) response).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}