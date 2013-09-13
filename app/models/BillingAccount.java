package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.Logger;
import play.db.jpa.GenericModel;
import util.ObjectUtils;

@Entity
@Table(name = "BILLING_ACCOUNT")

public class BillingAccount extends GenericModel{
	@Id
	@Column(name="BAN")
	public int ban;
	@Column(name="ACCOUNT_TYPE")
	public String accountType;
	@Column(name="ACCOUNT_CATEGORY")
	public String accountCate;
	@Column(name="COMPANY_CODE")
	public String companyCode;
	@Column(name="BILL_CYCLE")
	public String billCycle;

	public static BillingAccount findWithBan(int ban) {
		Logger.info("find billing_account detail with > ban : " + ban);
		// String billCycle = null;
		Statement stmt = null;
		ResultSet rs = null;
		BillingAccount result = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT BAN,ACCOUNT_CATEGORY,ACCOUNT_TYPE,BILL_CYCLE,COMPANY_CODE");
			sql.append(" FROM BILLING_ACCOUNT  ");
			sql.append(" WHERE BAN=" + ban);
			stmt = MyDB.getConnection().createStatement();
			rs = stmt.executeQuery(sql.toString());
			if (rs.next()) {
				result = new BillingAccount();
				result.accountType = rs.getString("ACCOUNT_TYPE");
				result.accountCate = rs.getString("ACCOUNT_CATEGORY");
				result.companyCode = rs.getString("COMPANY_CODE");
				result.ban = rs.getInt("BAN");
				result.billCycle = rs.getString("BILL_CYCLE");

			}
			Logger.info("BillingAccount -> %s",
					ObjectUtils.reflectionToString(result));
		} catch (Exception e) {
			Logger.error(e, "ERROR : [%s] , %s",
					BillingAccount.class.getName(), e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				// ignore
			}
		}

		return result;
	}
}
