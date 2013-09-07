package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import play.data.validation.*;
@Embeddable
public class CsmDiscountPK implements Serializable{
	@Required
	@Column(name = "JOB_NAME")
	public String jobName;
	@Required
	@Column(name = "DISCOUNT_CODE")
	public String discountCode;
	@Required
	@Column(name = "UCR_NO")
	public String ucrNo;
	
	
	public CsmDiscountPK() {
		super();
		this.jobName = "";
		this.discountCode = "";
		this.ucrNo = "";
	}


	public CsmDiscountPK(String jobName, String discountCode, String ucrNo) {
		super();
		this.jobName = jobName;
		this.discountCode = discountCode;
		this.ucrNo = ucrNo;
	}
		
}
