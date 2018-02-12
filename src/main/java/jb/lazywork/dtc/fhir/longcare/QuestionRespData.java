package jb.lazywork.dtc.fhir.longcare;

import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.resource.Questionnaire;
import ca.uhn.fhir.model.dstu2.resource.QuestionnaireResponse;
import ca.uhn.fhir.model.dstu2.resource.QuestionnaireResponse.Group;
import ca.uhn.fhir.model.dstu2.resource.QuestionnaireResponse.GroupQuestion;
import ca.uhn.fhir.model.primitive.BooleanDt;
import ca.uhn.fhir.model.primitive.IntegerDt;
import ca.uhn.fhir.model.primitive.StringDt;

public class QuestionRespData {

	//看護
	public QuestionnaireResponse buildB1Resp(Patient patient, Questionnaire questionnaire) {
		QuestionnaireResponse data = buildResp(patient, questionnaire);
		Group group = data.getGroup();

		Group qB1 = genGroup("B1");
		qB1.addQuestion(genQuestionAnswer("有", true));
		qB1.addQuestion(genQuestionAnswer("B1a", "華安"));
		qB1.addQuestion(genQuestionAnswer("B1b", 20));
		group.addGroup(qB1);

		return data;
	}

	//居住狀況
	public QuestionnaireResponse buildH1Resp(Patient patient, Questionnaire questionnaire) {
		QuestionnaireResponse data = buildResp(patient, questionnaire);
		Group group = data.getGroup();

		Group qH1 = genGroup("H1");
		qH1.addQuestion(genQuestionAnswer("H1a.1", true));
		qH1.addQuestion(genQuestionAnswer("H1a.2", false));
		qH1.addQuestion(genQuestionAnswer("H1a.3", false));
		qH1.addQuestion(genQuestionAnswer("H1a.4", false));
		qH1.addQuestion(genQuestionAnswer("H1a.5", "住帝寶"));
		group.addGroup(qH1);

		return data;
	}

	//ADL
	public QuestionnaireResponse buildEResp(Patient patient, Questionnaire questionnaire) {
		QuestionnaireResponse data = buildResp(patient, questionnaire);
		Group group = data.getGroup();

		group.addGroup(genGroupQuestionAnswer("E1", "E1.1"));
		group.addGroup(genGroupQuestionAnswer("E2", "E2.1"));
		group.addGroup(genGroupQuestionAnswer("E3", "E3.2"));
		group.addGroup(genGroupQuestionAnswer("E4", "E4.2"));
		group.addGroup(genGroupQuestionAnswer("E5", "E5.1"));
		group.addGroup(genGroupQuestionAnswer("E6", "E6.1"));
		group.addGroup(genGroupQuestionAnswer("E7", "E7.3"));
		group.addGroup(genGroupQuestionAnswer("E8", "E8.4"));
		group.addGroup(genGroupQuestionAnswer("E9", "E9.4"));
		group.addGroup(genGroupQuestionAnswer("E10", "E10.3"));

		return data;
	}

	//長者衰弱評估
	public QuestionnaireResponse buildG4dResp(Patient patient, Questionnaire questionnaire) {
		QuestionnaireResponse data = buildResp(patient, questionnaire);
		Group group = data.getGroup();

		Group qG4d1 = genGroup("G4d1");
		qG4d1.addQuestion(genQuestionAnswer("是", true));
		group.addGroup(qG4d1);

		Group qG4d2 = genGroup("G4d2");
		qG4d2.addQuestion(genQuestionAnswer("否", true));
		group.addGroup(qG4d2);

		Group qG4d3 = genGroup("G4d3");
		qG4d3.addQuestion(genQuestionAnswer("是", true));
		group.addGroup(qG4d3);

		return data;
	}

	//IADL
	public QuestionnaireResponse buildFResp(Patient patient, Questionnaire questionnaire) {
		QuestionnaireResponse data = buildResp(patient, questionnaire);
		Group group = data.getGroup();

		group.addGroup(genGroupQuestionAnswer("F1", "F1.1"));
		group.addGroup(genGroupQuestionAnswer("F2", "F2.1"));
		group.addGroup(genGroupQuestionAnswer("F3", "F3.2"));
		group.addGroup(genGroupQuestionAnswer("F4", "F4.4"));
		group.addGroup(genGroupQuestionAnswer("F5", "F5.1"));
		group.addGroup(genGroupQuestionAnswer("F6", "F6.5"));
		group.addGroup(genGroupQuestionAnswer("F7", "F7.3"));
		group.addGroup(genGroupQuestionAnswer("F8", "F8.3"));

		return data;
	}

	private QuestionnaireResponse buildResp(Patient patient, Questionnaire questionnaire) {
		QuestionnaireResponse data = new QuestionnaireResponse();
		data.setId(questionnaire.getId().getValue() + "." + patient.getId().getValue());
		data.setQuestionnaire(new ResourceReferenceDt(questionnaire));
		data.setSubject(new ResourceReferenceDt(patient));
		return data;
	}

	//Utilities
	Group genGroup(String linkId) {
		Group data = new Group();
		data.setLinkId(linkId);
		return data;
	}

	GroupQuestion genQuestionAnswer(String linkId, boolean answer) {
		GroupQuestion data = new GroupQuestion();
		data.setLinkId(linkId);
		data.addAnswer().setValue(new BooleanDt(answer));
		return data;
	}

	GroupQuestion genQuestionAnswer(String linkId, String answer) {
		GroupQuestion data = new GroupQuestion();
		data.setLinkId(linkId);
		data.addAnswer().setValue(new StringDt(answer));
		return data;
	}

	GroupQuestion genQuestionAnswer(String linkId, int answer) {
		GroupQuestion data = new GroupQuestion();
		data.setLinkId(linkId);
		data.addAnswer().setValue(new IntegerDt(answer));
		return data;
	}

	Group genGroupQuestionAnswer(String groupLinkId, String questionLinkId) {
		Group group = genGroup(groupLinkId);
		group.addQuestion(genQuestionAnswer(questionLinkId, true));
		return group;
	}
}
