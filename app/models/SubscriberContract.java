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
@Table(name = "SUBSCRIBER_CONTRACT")
public class SubscriberContract  extends GenericModel{
	@EmbeddedId
	public SubscriberContractPK pk;
	@Column(name="PROPOSITION")
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

	
	public static List findListSubscriberContract(int ban, String subscriberNo) {
		List<SubscriberContract> list = SubscriberContract.find("pk.ban = ?1 and pk.subscriberNo = ?2 AND (endDate IS NULL OR endDate > TRUNC (sysdate)) AND (terminateDate IS NULL OR terminateDate > TRUNC (SYSDATE))",ban,subscriberNo).fetch(); 
		return list;
	}
}
