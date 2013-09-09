package models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import java.util.*;

@Entity
@Table(name = "BAN_DISCOUNT")
public class BanDiscount extends GenericModel{
	@EmbeddedId
	public BanDiscountPK pk;
	@Column(name="DISCOUNT_PLAN")
	public String discountPlan;

	public static List<BanDiscount> findDiscountList(int ban , String subscirberNo) {
		List<BanDiscount> list = BanDiscount.find("pk.ban = ?1 and pk.subscriberNo = ?2 ",ban,subscirberNo).fetch();

		return list;
	}
}
