package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
@Entity
@Table(name = "SUBSCRIBER")
public class Subscriber  extends GenericModel{
	@Column(name="CUSTOMER_ID")
	public int customerId;
	@Id
	@Column(name="SUBSCRIBER_NO")
	public String subscriberNo;
	@Column(name="COMPANY_CODE")
	public String companyCode;
	
}
