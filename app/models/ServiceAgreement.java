package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.Logger;
import play.db.jpa.GenericModel;
import util.DateUtil;

@Entity
@Table(name = "SERVICE_AGREEMENT")
public class ServiceAgreement extends GenericModel {
	@EmbeddedId
	public ServiceAgreementPK pk;
	@Column(name = "SOC")
	public String soc;
	@Column(name = "SERVICE_TYPE")
	public String serviceType;
	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRATION_DATE")
	private Date expirationDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "EFFECTIVE_DATE")
	private Date effectiveDate;

	public ServiceAgreement() {
		super();
		pk = new ServiceAgreementPK();
	}

	public Date getExpirationDate() {
		String source = DateUtil.date2Str(expirationDate, "dd/MM/yyyy");
		return DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public void setExpirationDate(Date expirationDate) {
		String source = DateUtil.date2Str(expirationDate, "dd/MM/yyyy");
		this.expirationDate = DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public Date getEffectiveDate() {
		String source = DateUtil.date2Str(effectiveDate, "dd/MM/yyyy");
		return DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public void setEffectiveDate(Date effectiveDate) {
		String source = DateUtil.date2Str(effectiveDate, "dd/MM/yyyy");
		this.effectiveDate = DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public static List<ServiceAgreement> findSocList(int ban,
			String subscriberNo) {

		System.out.println("Find current price plan from -> " + ban + ","
				+ subscriberNo);
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<ServiceAgreement> result = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("  SELECT   BAN,SUBSCRIBER_NO,SOC,SERVICE_TYPE ");
			sb.append("   FROM   SERVICE_AGREEMENT ");
			sb.append("  WHERE        SERVICE_TYPE != 'P' ");
			sb.append("          AND EFFECTIVE_DATE <= SYSDATE ");
			sb.append("        AND (EXPIRATION_DATE >= SYSDATE OR EXPIRATION_DATE IS NULL) ");
			sb.append("         AND (BAN = ? AND SUBSCRIBER_NO = ?) ");
			sb.append("  ORDER BY   SYS_CREATION_DATE DESC  ");
			System.out.println("SQL :: " + sb.toString());
			psmt = MyDB.getConnection().prepareStatement(sb.toString());
			psmt.setInt(1, ban);
			psmt.setString(2, subscriberNo);
			rs = psmt.executeQuery();
			result = new ArrayList<ServiceAgreement>();
			ServiceAgreement item = null;
			while (rs.next()) {
				item = new ServiceAgreement();
				item.pk.ban = rs.getInt("BAN");
				item.pk.subscriberNo = rs.getString("SUBSCRIBER_NO");
				item.serviceType = rs.getString("SERVICE_TYPE");
				item.soc = rs.getString("SOC").trim();
				result.add(item);
				
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
		return result;

	}

	public static String findCurrentPricePlan(int ban, String subscriberNo) {

		System.out.println("Find current price plan from -> " + ban + ","
				+ subscriberNo);
		String pp = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("  SELECT   SOC ");
			sb.append("   FROM   SERVICE_AGREEMENT ");
			sb.append("  WHERE        SERVICE_TYPE = 'P' ");
			sb.append("          AND EFFECTIVE_DATE <= SYSDATE ");
			sb.append("        AND (EXPIRATION_DATE >= SYSDATE OR EXPIRATION_DATE IS NULL) ");
			sb.append("         AND (BAN = ? AND SUBSCRIBER_NO = ?) ");
			sb.append("  ORDER BY   SYS_CREATION_DATE DESC  ");
			System.out.println("SQL :: " + sb.toString());
			psmt = MyDB.getConnection().prepareStatement(sb.toString());
			psmt.setInt(1, ban);
			psmt.setString(2, subscriberNo);
			rs = psmt.executeQuery();

			if (rs.next()) {
				pp = rs.getString("SOC").trim();

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
		return pp;

	}
}
