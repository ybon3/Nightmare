package jb.lazywork.dtc.fhir.longcare;

import ca.uhn.fhir.model.dstu2.resource.Questionnaire;
import ca.uhn.fhir.model.dstu2.resource.Questionnaire.Group;
import ca.uhn.fhir.model.dstu2.resource.Questionnaire.GroupQuestion;
import ca.uhn.fhir.model.dstu2.valueset.AnswerFormatEnum;

public class QuestionnaireData {
	public static final String B1 = "eform-care";
	public static final String H1 = "eform-occupancy";
	public static final String E = "eform-adl";
	public static final String G4d = "eform-sof";
	public static final String F = "eform-iadl";

	//看護
	public Questionnaire buildB1() {
		Questionnaire data = genQuestionnaire(B1, "主要及次要照顧者基本資料");
		Group group = data.getGroup();

		Group qB1 = genGroup("B1", "個案是否有主要照顧者？【本題以照顧失能者最多的家人或親友為主要照顧者，若無上述主要照顧者，才含聘僱關係之照顧者】");
		qB1.addQuestion(genQuestion("無", AnswerFormatEnum.BOOLEAN));
		qB1.addQuestion(genQuestion("有", AnswerFormatEnum.BOOLEAN));
		qB1.addQuestion(genQuestion("B1a", "主要照顧者姓名", AnswerFormatEnum.STRING));
		qB1.addQuestion(genQuestion("B1b",
				"與個案之關係：\n01.配偶\t02.兄弟\t03.姊妹\t04.兒子\t05.媳婦\t06.女兒\t07.女婿" +
				"\t08.孫子\t09.孫女\t10.孫媳婦\t11.孫女婿\t12.父親\t13.母親\t14.岳父母\t15.公婆" +
				"\t16.祖父\t17.祖母\t18.外祖父\t19.外祖母\t20.聘用看護-本國籍\t21.聘用看護-外國籍",
				AnswerFormatEnum.INTEGER)
		);
		qB1.addQuestion(genQuestion("B1b.22","其他", AnswerFormatEnum.STRING));
		group.addGroup(qB1);

		return data;
	}

	//居住狀況
	public Questionnaire buildH1() {
		Questionnaire data = genQuestionnaire(H1, "居家環境與社會參與");
		Group group = data.getGroup();

		Group qH1 = genGroup("H1", "居家環境與居住狀況");
		qH1.addQuestion(genQuestion("H1a.1","獨居", AnswerFormatEnum.BOOLEAN));
		qH1.addQuestion(genQuestion("H1a.2","與家人或其他人同住", AnswerFormatEnum.BOOLEAN));
		qH1.addQuestion(genQuestion("H1a.3","住在機構", AnswerFormatEnum.BOOLEAN));
		qH1.addQuestion(genQuestion("H1a.4","政府補助居住服務（例如社區居住）", AnswerFormatEnum.BOOLEAN));
		qH1.addQuestion(genQuestion("H1a.5","其他", AnswerFormatEnum.STRING));
		group.addGroup(qH1);

		return data;
	}

	//ADL
	public Questionnaire buildE() {
		Questionnaire data = genQuestionnaire(E, "個案日常活動功能量表（ADLs）【以最近一個月能力為主】");
		Group group = data.getGroup();

		//E1
		Group qE1 = genGroup("E1", "吃飯（不包含自行準備食物、餐具或盛裝食物等）");
		qE1.addQuestion(genQuestion("E1.1","在合理時間（一小時）內，自行或用輔具進食餐盤食物", AnswerFormatEnum.BOOLEAN));
		qE1.addQuestion(genQuestion("E1.2","需要一些協助", AnswerFormatEnum.BOOLEAN));
		qE1.addQuestion(genQuestion("E1.3","需完全協助（完全依賴）", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qE1);

		//E2
		Group qE2 = genGroup("E2", "洗澡");
		qE2.addQuestion(genQuestion("E2.1", "能自行完成", AnswerFormatEnum.BOOLEAN));
		qE2.addQuestion(genQuestion("E2.2", "協助下完成", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qE2);

		//E3
		Group qE3 = genGroup("E3", "個人修飾（包括自行洗臉、洗手、刷牙、梳頭、刮鬍子）");
		qE3.addQuestion(genQuestion("E3.1", "可自行洗臉、洗手、刷牙、梳頭、刮鬍子修飾", AnswerFormatEnum.BOOLEAN));
		qE3.addQuestion(genQuestion("E3.2", "需協助", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qE3);

		//E4
		Group qE4 = genGroup("E4", "穿脫衣物（包括穿脫衣、褲、鞋、襪）");
		qE4.addQuestion(genQuestion("E4.1", "自行穿脫衣褲及鞋襪", AnswerFormatEnum.BOOLEAN));
		qE4.addQuestion(genQuestion("E4.2", "需協助", AnswerFormatEnum.BOOLEAN));
		qE4.addQuestion(genQuestion("E4.3", "需完全協助（完全依賴）", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qE4);

		//E5
		Group qE5 = genGroup("E5", "大便控制");
		qE5.addQuestion(genQuestion("E5.1", "無失禁（控），或當便秘時，能自行用塞劑、甘油球", AnswerFormatEnum.BOOLEAN));
		qE5.addQuestion(genQuestion("E5.2", "偶爾失禁（控），或當便秘時需協助用塞劑", AnswerFormatEnum.BOOLEAN));
		qE5.addQuestion(genQuestion("E5.3", "需完全協助（完全依賴）", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qE5);

		//E6
		Group qE6 = genGroup("E6", "小便控制");
		qE6.addQuestion(genQuestion("E6.1", "無失禁（控）", AnswerFormatEnum.BOOLEAN));
		qE6.addQuestion(genQuestion("E6.2", "偶爾失禁（控）", AnswerFormatEnum.BOOLEAN));
		qE6.addQuestion(genQuestion("E6.3", "需完全協助（完全依賴）", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qE6);

		//E7
		Group qE7 = genGroup("E7", "上廁所");
		qE7.addQuestion(genQuestion("E7.1", "可自行上下馬桶、整理衣褲、使用衛生紙、沖馬桶或清理便盆（尿壺）", AnswerFormatEnum.BOOLEAN));
		qE7.addQuestion(genQuestion("E7.2", "需協助整理衣物或使用衛生紙或需協助清理便盆（尿壺）", AnswerFormatEnum.BOOLEAN));
		qE7.addQuestion(genQuestion("E7.3", "需完全協助（完全依賴）", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qE7);

		//E8
		Group qE8 = genGroup("E8", "移位");
		qE8.addQuestion(genQuestion("E8.1", "可自行坐起，移至椅子或用輪椅", AnswerFormatEnum.BOOLEAN));
		qE8.addQuestion(genQuestion("E8.2", "移位時需少部分協助或提醒", AnswerFormatEnum.BOOLEAN));
		qE8.addQuestion(genQuestion("E8.3", "可自行坐起，離床需大部分協助", AnswerFormatEnum.BOOLEAN));
		qE8.addQuestion(genQuestion("E8.4", "需完全協助（完全依賴）", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qE8);

		//E9
		Group qE9 = genGroup("E9", "走路");
		qE9.addQuestion(genQuestion("E9.1", "獨立走50 公尺以上（可用輔具）", AnswerFormatEnum.BOOLEAN));
		qE9.addQuestion(genQuestion("E9.2", "需協助扶持走50 公尺以上", AnswerFormatEnum.BOOLEAN));
		qE9.addQuestion(genQuestion("E9.3", "不能步行50 公尺，但能操縱輪椅50 公尺", AnswerFormatEnum.BOOLEAN));
		qE9.addQuestion(genQuestion("E9.4", "不能步行50 公尺，且無法操縱輪椅", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qE9);

		//E10
		Group qE10 = genGroup("E10", "上下樓梯");
		qE10.addQuestion(genQuestion("E10.1", "安全上下樓梯，可用扶手、拐杖", AnswerFormatEnum.BOOLEAN));
		qE10.addQuestion(genQuestion("E10.2", "需協助、監督或持續敦促", AnswerFormatEnum.BOOLEAN));
		qE10.addQuestion(genQuestion("E10.3", "無法上下樓", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qE10);

		return data;
	}

	//長者衰弱評估
	public Questionnaire buildG4d() {
		Questionnaire data = genQuestionnaire(G4d, "衰弱評估（SOF）");
		Group group = data.getGroup();

		Group qG4d1 = genGroup("G4d1", "您是否在未刻意減重的情況下，過去一年中體重減少了5%以上？");
		qG4d1.addQuestion(genQuestion("是", AnswerFormatEnum.BOOLEAN));
		qG4d1.addQuestion(genQuestion("否", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qG4d1);

		Group qG4d2 = genGroup("G4d2", "您是否可以在不用手支撐的狀況下，從椅子上站起來5 次？（請個案實際做）");
		qG4d2.addQuestion(genQuestion("是", AnswerFormatEnum.BOOLEAN));
		qG4d2.addQuestion(genQuestion("否", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qG4d2);

		Group qG4d3 = genGroup("G4d3", "在過去一週內，您是否經常（一個禮拜內有3 天以上）有提不起勁來做事的感覺？");
		qG4d3.addQuestion(genQuestion("是", AnswerFormatEnum.BOOLEAN));
		qG4d3.addQuestion(genQuestion("否", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qG4d3);

		return data;
	}

	//IADL
	public Questionnaire buildF() {
		Questionnaire data = genQuestionnaire(F, "個案工具性日常活動功能量表（IADLs）【以最近一個月能力為主】");
		Group group = data.getGroup();

		//F1
		Group qF1 = genGroup("F1", "使用電話，問法：請問您當需要聯絡他人時，您能不能自己打電話？");
		qF1.addQuestion(genQuestion("F1.1", "能獨立使用電話，含查電話簿、撥號等", AnswerFormatEnum.BOOLEAN));
		qF1.addQuestion(genQuestion("F1.2", "僅能撥熟悉的電話號碼", AnswerFormatEnum.BOOLEAN));
		qF1.addQuestion(genQuestion("F1.3", "僅能接電話，但不能撥電話", AnswerFormatEnum.BOOLEAN));
		qF1.addQuestion(genQuestion("F1.4", "完全不能使用電話", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qF1);

		//F2
		Group qF2 = genGroup("F2", "購物，問法：請問您能不能自己一個人購物（買東西）？");
		qF2.addQuestion(genQuestion("F2.1", "能獨立完成所有購物需求", AnswerFormatEnum.BOOLEAN));
		qF2.addQuestion(genQuestion("F2.2", "只能獨立購買日常生活用品", AnswerFormatEnum.BOOLEAN));
		qF2.addQuestion(genQuestion("F2.3", "每一次購物都需要有人陪", AnswerFormatEnum.BOOLEAN));
		qF2.addQuestion(genQuestion("F2.4", "完全不能獨自購物", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qF2);

		//F3
		Group qF3 = genGroup("F3", "備餐，問法：請問您能不能自己一個人準備餐食？");
		qF3.addQuestion(genQuestion("F3.1", "能獨立計畫、準備食材及佐料、烹煮和擺設一頓飯菜", AnswerFormatEnum.BOOLEAN));
		qF3.addQuestion(genQuestion("F3.2", "如果準備好一切食材及佐料，能做一頓飯菜", AnswerFormatEnum.BOOLEAN));
		qF3.addQuestion(genQuestion("F3.3", "能將已做好的飯菜加熱", AnswerFormatEnum.BOOLEAN));
		qF3.addQuestion(genQuestion("F3.4", "需要別人把飯菜煮好、擺好", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qF3);

		//F4
		Group qF4 = genGroup("F4", "處理家務，問法：請問您能不能自己一個人做家事？");
		qF4.addQuestion(genQuestion("F4.1", "能單獨處理家事，或偶爾需要協助較繁重的家事（例如：搬動家具、清理廚房且完成歸位等）", AnswerFormatEnum.BOOLEAN));
		qF4.addQuestion(genQuestion("F4.2", "能做較簡單的家事，如洗碗、擦桌子", AnswerFormatEnum.BOOLEAN));
		qF4.addQuestion(genQuestion("F4.3", "能做較簡單的家事，但不能達到可接受的清潔程度", AnswerFormatEnum.BOOLEAN));
		qF4.addQuestion(genQuestion("F4.4", "所有的家事都需要別人協助方能完成", AnswerFormatEnum.BOOLEAN));
		qF4.addQuestion(genQuestion("F4.5", "完全不能做家事", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qF4);

		//F5
		Group qF5 = genGroup("F5", "洗衣服，問法：請問您能不能自己一個人洗衣服（含晾曬衣服）？");
		qF5.addQuestion(genQuestion("F5.1", "自己清洗所有衣物", AnswerFormatEnum.BOOLEAN));
		qF5.addQuestion(genQuestion("F5.2", "需部份協助（例如需協助晾曬衣物或洗滌厚重衣物）", AnswerFormatEnum.BOOLEAN));
		qF5.addQuestion(genQuestion("F5.3", "需完全協助（完全依賴）", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qF5);

		//F6
		Group qF6 = genGroup("F6", "外出，問法：請問您能不能自己一個人外出活動？");
		qF6.addQuestion(genQuestion("F6.1", "能夠自己開車、騎車或自己搭乘大眾運輸工具", AnswerFormatEnum.BOOLEAN));
		qF6.addQuestion(genQuestion("F6.2", "能夠自己搭乘計程車，但不能搭乘大眾運輸工具", AnswerFormatEnum.BOOLEAN));
		qF6.addQuestion(genQuestion("F6.3", "當有人陪同時，可搭乘大眾運輸工具", AnswerFormatEnum.BOOLEAN));
		qF6.addQuestion(genQuestion("F6.4", "只能在有人協助或陪同時，可搭乘計程車或自用車", AnswerFormatEnum.BOOLEAN));
		qF6.addQuestion(genQuestion("F6.5", "完全不能出門", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qF6);

		//F7
		Group qF7 = genGroup("F7", "服用藥物，問法：請問您能不能自己一個人服用藥物？");
		qF7.addQuestion(genQuestion("F7.1", "能自己負責在正確的時間用正確的藥物（含正確藥量）", AnswerFormatEnum.BOOLEAN));
		qF7.addQuestion(genQuestion("F7.2", "如果事先準備好服用的藥物份量，可自行服用", AnswerFormatEnum.BOOLEAN));
		qF7.addQuestion(genQuestion("F7.3", "完全不能自己服用藥物", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qF7);

		//F8
		Group qF8 = genGroup("F8", "處理財務的能力，問法：請問您能不能自己一個人處理財務？");
		qF8.addQuestion(genQuestion("F8.1", "可以獨立處理財務", AnswerFormatEnum.BOOLEAN));
		qF8.addQuestion(genQuestion("F8.2", "可以處理日常的購買，但需別人協助與銀行往來或大宗買賣", AnswerFormatEnum.BOOLEAN));
		qF8.addQuestion(genQuestion("F8.3", "完全不能處理錢財", AnswerFormatEnum.BOOLEAN));
		group.addGroup(qF8);

		return data;
	}

	//Utilities
	Group genGroup(String linkId, String title) {
		Group data = new Group();
		data.setLinkId(linkId);
		data.setTitle(title);
		return data;
	}

	GroupQuestion genQuestion(String linkId, AnswerFormatEnum type) {
		return genQuestion(linkId, linkId, type);
	}

	GroupQuestion genQuestion(String linkId, String text, AnswerFormatEnum type) {
		GroupQuestion data = new GroupQuestion();
		data.setLinkId(linkId);
		data.setText(text);
		data.setType(type);
		return data;
	}
	Questionnaire genQuestionnaire(String id, String title) {
		Questionnaire data = new Questionnaire();
		data.setId(id);
		data.addIdentifier(IdentifierUtil.create("urn:system", id));
		Group group = data.getGroup();
		group.setTitle(title);
		return data;
	}

}
