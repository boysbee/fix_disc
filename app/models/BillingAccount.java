package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
@Entity
@Table(name = "BILLING_ACCOUNT")
public class BillingAccount  extends GenericModel{
	@Id
	@Column(name="BAN")
	public int ban;
	@Column(name="ACCOUNT_TYPE")
	public String accountType;
	@Column(name="ACCOUNT_CATEGORY")
	public String accountCate;
}
