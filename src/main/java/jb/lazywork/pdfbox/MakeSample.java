package jb.lazywork.pdfbox;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;

public class MakeSample {
	public static final String output = "D:\\20170202-sample.pdf";
	public static final String imagePath = "D:\\title.png";
	private PDDocument doc = new PDDocument();

	private void run() {
		try {
			//createBlankPDF();
			//helloworld();
			report();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void createBlankPDF() throws IOException {
		PDPage page = new PDPage();
		doc.addPage(page);
		doc.save(output);
	}

	public void helloworld() throws IOException {
		PDPage page = new PDPage();
		PDFont font = PDType1Font.HELVETICA_BOLD;
		doc.addPage(page);
		PDPageContentStream contents = new PDPageContentStream(doc, page);
		contents.beginText();
		contents.setFont(font, 12);
		contents.newLineAtOffset(100, 700);
		contents.showText("Foooooooooooooooooooooooo");
		contents.endText();
		contents.close();
		doc.save(output);
	}

	public void report() throws IOException {
		PDPage page = new PDPage(PDRectangle.A4);//new PDPage();
		doc.addPage(page);
		PDFont font = Context.loadChFont(doc);

		float fontSize = 1.0f;
		PDPageContentStream contentStream = new PDPageContentStream(doc, page, AppendMode.OVERWRITE, false);
		contentStream.setFont( font, fontSize );
		contentStream.beginText();


		//放射科数字X线检查报告单
		contentStream.setTextMatrix(
			genMatrix(22, 25, 180, 750)
		);
		contentStream.showText("放射科数字X线检查报告单");

		//检查时间(摄片时间):2016-11-07 16:29:00
		contentStream.setTextMatrix(
			genMatrix(12, 12, 40, 725)
		);
		contentStream.showText("检查时间(摄片时间):2016-11-07 16:29:00");

		//RIS号：509897
		contentStream.setTextMatrix(
			genMatrix(12, 12, 320, 725)
		);
		contentStream.showText("RIS号：509897");

		//X线号：2060128
		contentStream.setTextMatrix(
			genMatrix(12, 12, 450, 725)
		);
		contentStream.showText("X线号：2060128");

		//分隔線
		addDivide(contentStream, 720);

		//诊疗卡号：


		//分隔線
		addDivide(contentStream, 670);

		//分隔線
		addDivide(contentStream, 50);
		contentStream.endText();
		//contentStream.close();


//		PDPageContentStream contents = new PDPageContentStream(doc, page);
//		contents.beginText();
//		contents.setFont(font, 12);
//		contents.newLineAtOffset(100, 700);
//		contents.showText("你好挖");
//		contents.endText();
//		contents.close();

		//image
		PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, doc);
		//contentStream = new PDPageContentStream(doc, page, AppendMode.APPEND, true);
		float scale = 0.75f;
		contentStream.drawImage(
			pdImage,
			17,
			770,
			pdImage.getWidth() * scale,
			pdImage.getHeight() * scale
		);

		contentStream.close();
		doc.save(output);
	}

	private void close() {
		try {
			doc.close();
		} catch (Exception e) {}
	}

	public static void main(String[] args) {
		new MakeSample().run();
	}

	private void addDivide(PDPageContentStream contentStream, float y) throws IOException {
		contentStream.setTextMatrix(
			genMatrix(1100, 10, 30, y)
		);
		contentStream.showText("_");
	}

	private Matrix genMatrix(float width, float height, float x, float y) {
		return new Matrix(
			width, // matrix 的寬度
			0, // 角度（第一象限仰角）
			0,// 斜體角度
			height, // matrix 的高度
			x, // x 座標
			y // y 座標
		);
	}
}
