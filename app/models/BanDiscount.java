package models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
@Entity
@Table(name = "BAN_DISCOUNT")
public class BanDiscount extends GenericModel{
	@EmbeddedId
	public BanDiscountPK pk;
	@Column(name="DISCOUNT_PLAN")
	public String discountPlan;
}
