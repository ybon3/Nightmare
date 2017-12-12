package jb.lazywork.dtc.fidxc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * 這是為了取得 log 中，只有出現一次的 Dicom 檔案名稱所寫的比對程式
 */
public class LogChecker {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String pattern1 = "com.dtc.autocare.assist.PrintDicomDetailTask - ";
		String pattern2 = "ERROR com.dtc.autocare.archive.ArchiveService - ";

		String fname = "D:\\LAB\\archive.log.2017-11-21";

		ArrayList<String> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(fname), "MS950"))) {
		    String line;
		    while ((line = br.readLine()) != null) {

		    	int index = line.indexOf(pattern1);
		    	if (index != -1) {
		    		//System.out.println(line.substring(index + pattern1.length()));
		    		list.add(line.substring(index + pattern1.length()));
		    		continue;
		    	}

		    	index = line.indexOf(pattern2);
		    	if (index != -1) {
		    		list.remove(line.substring(index + pattern2.length()));
		    	}
		    }

		    for (String id : list) {
		    	System.out.println(id);
		    }
		}
	}
}
