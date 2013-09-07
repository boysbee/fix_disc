package models;

import play.db.jpa.GenericModel;

public class Subscriber  extends GenericModel{
	public int customerId;
	public String subscriberNo;
	public String companyCode;
	
}
