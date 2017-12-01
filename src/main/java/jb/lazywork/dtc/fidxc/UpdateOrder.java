package jb.lazywork.dtc.fidxc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dtc.fhir.gwt.DateTime;
import com.dtc.fhir.gwt.DiagnosticOrder;
import com.dtc.fhir.gwt.DiagnosticOrderEvent;
import com.dtc.fhir.gwt.DiagnosticOrderStatus;
import com.dtc.fhir.gwt.DiagnosticOrderStatusList;
import com.dtc.fhir.gwt.Practitioner;
import com.dtc.fhir.gwt.StringDt;

public class UpdateOrder {
	private DiagnosticOrderGwtRepo dxOrderGwtRepo = new DiagnosticOrderGwtRepo(Constant.BASE_URL);
	private PractitionerGwtRepo practitionerGwtRepo = new PractitionerGwtRepo(Constant.BASE_URL);
	private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

	public static void main(String[] args) {
		UpdateOrder t = new UpdateOrder();
		// t.updateNoStatusOrder();
		// t.updateNoEventOrder();
		// t.removeLatestEvent("2508140");
		// t.updateNoReferenceEvent();

		//t.updateNoOrdererDxOrder();
		//t.updateNoSubjectDxOrder();
		//t.manualUpdateDxOrder("2509041");
		//t.updateNoSubjectDisplayDxOrder();
		t.showDxOrderWhichHasEventWithoutStatus();
	}


	//
	public void showDxOrderWhichHasEventWithoutStatus() {
		List<DiagnosticOrder> orderList = dxOrderGwtRepo.findAll();
		System.out.println("======== " + orderList.size());
		for (DiagnosticOrder dxOrder : orderList) {
			if (!dxOrder.getEvent().isEmpty()) {
				for (DiagnosticOrderEvent event : dxOrder.getEvent()) {
					if (event.getStatus() == null) {
						System.out.println(dxOrder.getId().getValue());
						break;
					}
				}
			}
		}
		System.out.println("Done.");
	}

	// 用來修正 Subject 沒有 display 的資料
	public void updateNoSubjectDisplayDxOrder() {
		List<DiagnosticOrder> orderList = dxOrderGwtRepo.findAll();
		for (DiagnosticOrder dxOrder : orderList) {
			if (dxOrder.getSubject() != null &&
					dxOrder.getSubject().getDisplay() == null) {
				dxOrder.setSubject(Constant.getSubject());
				dxOrderGwtRepo.save(dxOrder);
			}
		}
	}

	// 自己決定要修改甚麼
	public void manualUpdateDxOrder(String id) {
		DiagnosticOrder dxOrder = dxOrderGwtRepo.findOne(id);
		dxOrder.setSubject(Constant.getSubject());
		dxOrderGwtRepo.save(dxOrder);
	}

	// 用來修正沒有 Subject 的資料
	public void updateNoSubjectDxOrder() {
		List<DiagnosticOrder> orderList = dxOrderGwtRepo.findAll();
		for (DiagnosticOrder dxOrder : orderList) {
			if (dxOrder.getSubject() == null) {
				dxOrder.setSubject(Constant.getSubject());
				dxOrderGwtRepo.save(dxOrder);
			}
		}
	}

	// 用來修正沒有 Orderer 的資料
	public void updateNoOrdererDxOrder() {
		List<DiagnosticOrder> orderList = dxOrderGwtRepo.findAll();
		for (DiagnosticOrder dxOrder : orderList) {
			if (dxOrder.getOrderer() == null) {
				dxOrder.setOrderer(Constant.getOrderer());
				dxOrderGwtRepo.save(dxOrder);
			}
		}
	}

	// 用來修正 event.actor 有 display 但沒有 reference 的資料
	public void updateNoReferenceEvent() {
		int updateCount = 0;
		int successCount = 0;
		List<Practitioner> practitionerList = practitionerGwtRepo.findAll();
		List<DiagnosticOrder> orderList = dxOrderGwtRepo.findAll();

		for (DiagnosticOrder dxOrder : orderList) {
			if (!dxOrder.getEvent().isEmpty()) {
				boolean isChanged = false;
				List<DiagnosticOrderEvent> eventList = dxOrder.getEvent();

				for (DiagnosticOrderEvent event : eventList) {
					if (event.getActor() != null
						&& event.getActor().getDisplay() != null
						&& event.getActor().getReference() == null) {

						event.getActor().setReference(
							findActorReference(
								practitionerList, event.getActor().getDisplay().getValue()));

						isChanged = true;
						System.out.println(
								event.getActor().getDisplay().getValue() +
								" |" +
								event.getActor().getReference().getValue());
					}
				}

				if (isChanged) {
					updateCount++;
					if (dxOrderGwtRepo.save(dxOrder)) successCount++;
				}
			}
		}

		System.out.println(successCount + "/" + updateCount);
	}

	// 用來修正那些沒有 event 的資料
	public void updateNoEventOrder() {
		List<DiagnosticOrder> list = dxOrderGwtRepo.findAll();

		for (DiagnosticOrder dxOrder : list) {
			if (dxOrder.getEvent().isEmpty()) {
				addInitEvent(dxOrder);
				dxOrderGwtRepo.save(dxOrder);
			}
		}
	}

	// 用來修正那些沒有 status 的資料
	public void updateNoStatusOrder() {
		DiagnosticOrderStatus status = Constant.toStatus(DiagnosticOrderStatusList.DRAFT);
		List<DiagnosticOrder> list = dxOrderGwtRepo.findAll();
		for (DiagnosticOrder dxOrder : list) {
			if (dxOrder.getStatus() == null) {
				dxOrder.setStatus(status);

				addInitEvent(dxOrder);

				dxOrderGwtRepo.save(dxOrder);
			}
		}
	}

	// 移除最後一個 event
	public void removeLatestEvent(String id) {
		DiagnosticOrder order = dxOrderGwtRepo.findOne(id);
		order.getEvent().remove(order.getEvent().size()-1);
		dxOrderGwtRepo.save(order);
	}

	private void addInitEvent(DiagnosticOrder dxOrder) {
		DiagnosticOrderEvent event = new DiagnosticOrderEvent();
		event.setStatus(dxOrder.getStatus());
		DateTime datetime = new DateTime();
		datetime.setValue(SDF.format(new Date()));
		event.setDateTime(datetime);
		dxOrder.getEvent().add(event);
	}

	private StringDt findActorReference(List<Practitioner> list, String name) {
		for (Practitioner p : list) {
			if (p.getName().getText().getValue().equals(name)) {
				StringDt stringDt = new StringDt();
				stringDt.setValue(Practitioner.class.getSimpleName() + "/" + p.getId().getValue());
				return stringDt;
			}
		}

		return null;
	}
}
