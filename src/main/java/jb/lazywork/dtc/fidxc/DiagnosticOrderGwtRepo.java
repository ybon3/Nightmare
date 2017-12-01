package jb.lazywork.dtc.fidxc;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.dtc.fhir.gwt.DiagnosticOrder;
import com.dtc.fhir.gwt.DiagnosticOrderEvent;
import com.dtc.fhir.gwt.DiagnosticOrderStatusList;
import com.dtc.fhir.gwt.Id;
import com.dtc.fhir.gwt.ResourceContainer;
import com.dtc.fhir.repository.Constant;
import com.dtc.fhir.repository.gwt.BaseGwtRepo;

public class DiagnosticOrderGwtRepo extends BaseGwtRepo<DiagnosticOrder> {

	private static Comparator<DiagnosticOrderEvent> datetimeDescComparator =
		new Comparator<DiagnosticOrderEvent>() {
			@Override
			public int compare(DiagnosticOrderEvent o1, DiagnosticOrderEvent o2) {
				return o1.getDateTime().getValue().compareTo(o2.getDateTime().getValue());
			}
		};

	public DiagnosticOrderGwtRepo(String baseUrl) {
		super(baseUrl);
	}

	@Override
	protected DiagnosticOrder getResource(ResourceContainer resourceContainer) {
		return resourceContainer.getDiagnosticOrder();
	}

	/**
	 * 已經對取出的 DiagnosticOrder.event 以 event.datetime 進行升冪排序
	 * @return null(when error occur)
	 */
	public List<DiagnosticOrder> findByStatus(DiagnosticOrderStatusList status) {
		// first time
		StringBuilder sb = new StringBuilder(getResourceType());
		sb.append("?").append(Constant.PARAM_COUNT).append("=").append(Constant.FHIR_COUNT_LIMIT);
		sb.append("&").append("status=").append(status.value());
		return searchAndExpand(sb.toString());
	}

	/**
	 * 已經對取出的 DiagnosticOrder.event 以 event.datetime 進行升冪排序
	 */
	@Override
	public DiagnosticOrder findOne(String id) {
		DiagnosticOrder dxOrder = super.findOne(id);
		Collections.sort(dxOrder.getEvent(), datetimeDescComparator);
		return dxOrder;
	}

	/**
	 * 已經對取出的 DiagnosticOrder.event 以 event.datetime 進行升冪排序
	 */
	@Override
	public DiagnosticOrder findOne(Id id) {
		DiagnosticOrder dxOrder = super.findOne(id);
		Collections.sort(dxOrder.getEvent(), datetimeDescComparator);
		return dxOrder;
	}
}