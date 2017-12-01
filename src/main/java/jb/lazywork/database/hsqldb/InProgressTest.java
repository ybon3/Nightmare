package jb.lazywork.database.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;

public class InProgressTest {

	Connection con;

	public static void main(String[] args) throws Exception {
		InProgressTest test = new InProgressTest();

		test.init();
		String sql = "create table contacts (name varchar(45),email varchar(45),phone varchar(45))";

		test.close();
		System.out.println("Done.");
	}

	public void init() {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:mydatabase","SA","");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.out);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	private void close() {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {}
		}
	}
}
