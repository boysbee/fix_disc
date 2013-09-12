package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	public static List<ServiceAgreement> findSocList(int ban, String subscriberNo) {
		List<ServiceAgreement> listOfServiceAgreement = ServiceAgreement.find("pk.ban = ?1 and pk.subscriberNo = ?2 and serviceType != 'P'",ban,subscriberNo).fetch();
		return listOfServiceAgreement;
	}

	public static String findCurrentPricePlan(int ban , String subscriberNo) {
		ServiceAgreement obj = ServiceAgreement.find("pk.ban = ?1 and pk.subscriberNo = ?2 and serviceType = 'P' and effectiveDate <= sysdate and ( expirationDate >= sysdate or expirationDate is NULL) ",ban,subscriberNo).first();
		
		return obj == null ? "" :  obj.soc == null ? "" : obj.soc ;
	}
}
