package jb.lazywork.database.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	protected Connection con;

	public static void main(String[] args) throws Exception {
		Test test = new Test();
		test.init();

		try {
			// test.create();
			test.insert();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			test.close();
		}

		System.out.println("Done.");
	}

	public void create() throws SQLException {
		String sql = "create table contacts (name varchar(45),email varchar(45),phone varchar(45))";
		con.createStatement().execute(sql);
	}

	public void insert() throws SQLException {
		String sql1 = "insert into contacts values ('Dante', 'dante@datacom.com.tw', '22250891')";
		String sql2 = "insert into contacts values ('Monty', 'monty@datacom.com.tw', '22250891')";
		String sql3 = "insert into contacts values ('shlee', 'shlee@datacom.com.tw', '22250891')";
		con.createStatement().execute(sql1);
		con.createStatement().execute(sql2);
		con.createStatement().execute(sql3);
	}

	public void showTable(String tableName) throws SQLException {
		ResultSet res = con.createStatement().executeQuery("select * from " + tableName);

		while (res.next()) {
			System.out.println(res.getString(1) + " | " + res.getString(2) + " | " + res.getString(3));
		}
	}

	public void init() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:file:./data/sample");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.out);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public void close() {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {}
		}
	}
}
