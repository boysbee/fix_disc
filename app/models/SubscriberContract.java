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
@Table(name = "SUBSCRIBER_CONTRACT")
public class SubscriberContract extends GenericModel {
	@EmbeddedId
	public SubscriberContractPK pk;
	@Column(name = "PROPOSITION")
	public String proposition;

	@Temporal(TemporalType.DATE)
	@Column(name = "TERMINATE_DATE")
	private Date terminateDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	private Date endDate;

	public Date getTerminateDate() {
		String source = DateUtil.date2Str(terminateDate, "dd/MM/yyyy");
		return DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public void setTerminateDate(Date terminateDate) {
		String source = DateUtil.date2Str(terminateDate, "dd/MM/yyyy");
		this.terminateDate = DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public Date getEffectiveDate() {
		String source = DateUtil.date2Str(endDate, "dd/MM/yyyy");
		return DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public void setEndDate(Date endDate) {
		String source = DateUtil.date2Str(endDate, "dd/MM/yyyy");
		this.endDate = DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public SubscriberContract() {
		super();
		pk = new SubscriberContractPK();
	}

	public static List<SubscriberContract> findListSubscriberContract(int ban,
			String subscriberNo) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String propostion = null;
		List<SubscriberContract> result = null;
		try {
			StringBuffer sb = new StringBuffer();

			sb.append("     SELECT   PROPOSITION ,BAN,SUBSCRIBER_NO");
			sb.append("  FROM   CAPPO.SUBSCRIBER_CONTRACT ");
			sb.append("  WHERE       BAN = ? ");
			sb.append("  AND SUBSCRIBER_NO = ? ");
			// sb.append("  AND START_DATE >= TO_DATE ('18/03/2013', 'DD/MM/YYYY') ");
			sb.append("  AND (END_DATE IS NULL OR END_DATE > TRUNC (SYSDATE)) ");
			sb.append("  AND (TERMINATE_DATE IS NULL OR TERMINATE_DATE > TRUNC (SYSDATE))								");

			ps = MyDB.getConnection().prepareStatement(sb.toString());
			ps.setInt(1, ban);
			ps.setString(2, subscriberNo);
			rs = ps.executeQuery();
			result = new ArrayList<SubscriberContract>();
			SubscriberContract item = null;
			while (rs.next()) {
				item = new SubscriberContract();
				item.proposition = rs.getString("PROPOSITION").trim();
				item.pk.subscriberNo = rs.getString("SUBSCRIBER_NO");
				item.pk.ban = rs.getInt("BAN");
				result.add(item);

			}
		} catch (SQLException e) {
			Logger.error(e, "ERROR : [%s] , %s",
					SubscriberContract.class.getName(), e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				//
			}
		}

		return result;

	}
}
