package models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "SERVICE_AGREEMENT")
public class ServiceAgreement extends GenericModel {
	@EmbeddedId
	public ServiceAgreementPK pk;
	@Column(name = "SOC")
	public String soc;
	@Column(name = "SERVICE_TYPE")
	public String serviceType;
}
