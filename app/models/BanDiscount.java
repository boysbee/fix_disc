package models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import play.Logger;
import play.db.jpa.GenericModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Entity
@Table(name = "BAN_DISCOUNT")
public class BanDiscount extends GenericModel {
	@EmbeddedId
	public BanDiscountPK pk;
	@Column(name = "DISCOUNT_PLAN")
	public String discountPlan;

	public BanDiscount() {
		super();
		pk = new BanDiscountPK();
	}

	public static List<BanDiscount> findDiscountList(int ban,
			String subscriberNo) {

		System.out.println("Find current price plan from -> " + ban + ","
				+ subscriberNo);

		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<BanDiscount> result = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("  SELECT   BAN,SUBSCRIBER_NO,DISCOUNT_PLAN ");
			sb.append("   FROM   BAN_DISCOUNT ");
			sb.append("    WHERE    BAN = ? AND SUBSCRIBER_NO = ? ");
			sb.append("  ORDER BY   SYS_CREATION_DATE DESC  ");
			System.out.println("SQL :: " + sb.toString());
			psmt = MyDB.getConnection().prepareStatement(sb.toString());
			psmt.setInt(1, ban);
			psmt.setString(2, subscriberNo);
			rs = psmt.executeQuery();
			result = new ArrayList<BanDiscount>();
			BanDiscount item = null;
			while (rs.next()) {
				item = new BanDiscount();
				item.pk.ban = rs.getInt("BAN");
				item.pk.subscriberNo = rs.getString("SUBSCRIBER_NO");
				item.discountPlan = rs.getString("DISCOUNT_PLAN").trim();
				result.add(item);
			}
		} catch (Exception e) {
			Logger.error(e, "ERROR : [%s] , %s", BanDiscount.class.getName(),
					e.getMessage());
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
}
