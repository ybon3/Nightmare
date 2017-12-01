package jb.lazywork.database.orm;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.StatementBuilder.StatementType;
import com.j256.ormlite.support.CompiledStatement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.DatabaseTable;

import jb.lazywork.database.h2.Test;

public class TestOrmLite extends Test {

	public static void main(String[] args) throws Exception {
		ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:h2:file:./data/sample");
		Dao<Contacts,String> dao =
				DaoManager.createDao(connectionSource, Contacts.class);


		// show one
		Contacts con = dao.queryForId("Dante");
		System.out.println("Contacts: " + con.getEmail());


		// show all
		List<Contacts> res = dao.queryForAll();
		for(Contacts c : res) {
			System.out.println("Contacts: " + c.getEmail());
		}

		// show by condition
		List<Contacts> res2 = dao.query(new PreparedQuery<Contacts>(){
			@Override
			public CompiledStatement compile(DatabaseConnection arg0, StatementType arg1) throws SQLException {
				return null;
			}

			@Override
			public CompiledStatement compile(DatabaseConnection arg0, StatementType arg1, int arg2)
					throws SQLException {
				return null;
			}

			@Override
			public String getStatement() throws SQLException {
				return null;
			}

			@Override
			public StatementType getType() {
				return null;
			}

			@Override
			public void setArgumentHolderValue(int arg0, Object arg1) throws SQLException {}

			@Override
			public Contacts mapRow(DatabaseResults arg0) throws SQLException {
				return null;
			}
		});
		for(Contacts c : res2) {
			System.out.println("Contacts: " + c.getEmail());
		}

		System.out.println("Done.");
	}
}

@DatabaseTable(tableName = "contacts")
class Contacts {
	@DatabaseField(id = true)
	private String name;

	@DatabaseField()
	private String email;

	@DatabaseField()
	private String phone;

	Contacts() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}