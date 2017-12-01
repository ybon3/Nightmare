package jb.lazywork.pdfbox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class Context {

	public static final String[] ARGS = new String[]{"D:\\outputfile.pdf"};
	public static final String[] ARGS_2 = new String[]{
			"D:\\outputfile.pdf",
			"D:\\outputfile.pdf"};
	public static final String[] ARGS_3 = new String[]{
			"D:\\outputfile.pdf",
			"C:\\Users\\Administrator\\Desktop\\icons\\uknow2much.jpg",
			"D:\\outputfile.pdf"};
	public static PDFont loadChFont(PDDocument doc) throws IOException {
		return PDType0Font.load(doc, new File("c:\\windows\\fonts\\kaiu.ttf"));
	}
}
