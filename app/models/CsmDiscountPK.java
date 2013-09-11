package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import play.data.validation.*;
@Embeddable
public class CsmDiscountPK implements Serializable{
	@Required
	@Min(5)
	@Column(name = "JOB_NAME")
	public String jobName;
	@Required
	@Min(1)
	@Column(name = "DISCOUNT_CODE")
	public String discountCode;
	@Required
	@Min(5)
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
