import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import models.MyDB;

import org.junit.Test;

import play.test.UnitTest;

public class MyDBTest extends UnitTest {

	@Test
	public void testMyJPA() {
		boolean check = false;
		try {
			Connection conn = MyDB.getConnection();
			Statement stmt = conn.createStatement();
			int ban = 200068764;
			String sql = "select * from billing_account where ban = "
							+ ban;
			ResultSet rs = stmt
					.executeQuery(sql);
			System.out.println(sql);
			if (rs.next()) {
				System.out.println(rs.getString("ACCOUNT_TYPE"));
				check = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertTrue(check);
	}

}
