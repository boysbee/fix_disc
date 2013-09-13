package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.Logger;
import play.db.jpa.GenericModel;

@Entity
@Table(name = "SUBSCRIBER")
public class Subscriber extends GenericModel {
	@Column(name = "CUSTOMER_ID")
	public int customerId;
	@Id
	@Column(name = "SUBSCRIBER_NO")
	public String subscriberNo;
	@Column(name = "COMPANY_CODE")
	public String companyCode;

	public static int findCurrentBan(String subscriberNo, String companyCode) {

		int ban = -1;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("  SELECT   CUSTOMER_ID,COMPANY_CODE,SUBSCRIBER_NO ");
			sb.append("   FROM   SUBSCRIBER ");
			sb.append("  WHERE        COMPANY_CODE =? ");
			sb.append("         AND SUBSCRIBER_NO = ? ");
			sb.append("  ORDER BY   SYS_CREATION_DATE DESC  ");
			System.out.println("SQL :: " + sb.toString());
			psmt = MyDB.getConnection().prepareStatement(sb.toString());
			psmt.setString(1, companyCode);
			psmt.setString(2, subscriberNo);
			rs = psmt.executeQuery();

			if (rs.next()) {
				ban = rs.getInt("CUSTOMER_ID");

			}
		} catch (Exception e) {
			Logger.error(e, "ERROR : [%s] , %s",
					ServiceAgreement.class.getName(), e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (psmt != null) {
					psmt.close();
				}

			} catch (SQLException e) {
				// ignore
			}
		}
		return ban;

	}

}
