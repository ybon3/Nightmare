package jb.lazywork.dtc.fidxc;

import java.io.File;

import org.dcm4che3.data.Tag;

import com.dtc.dicom.operation.Storer;
import com.dtc.dicom.vo.AeInfo;

public class StoreDicom {

	public static void main(String[] args) {
		store(new File("D:\\Autocare\\portal\\archive\\1.2.392.200036.9107.500.220.1.20171129.83207.6864.1.dcm"));
		store(new File("D:\\Autocare\\portal\\archive\\1.2.392.200036.9107.500.220.1.20171129.83318.6865.2.dcm"));
	}

	public static void store(File dicom) {
		Storer storer = new Storer()
			.configScp(
				new AeInfo("DTC_STORE", "localhost", 55688)
			)
			.configScu(
				new AeInfo("DANTE_PC", "localhost", 55677)
			);

		//指定 UTF-8 編碼
		storer.specify(Tag.SpecificCharacterSet, "ISO_IR 192");

		storer.send(dicom);
		storer.release();
	}
}
