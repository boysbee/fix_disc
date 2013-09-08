package models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
@Entity
@Table(name = "SUBSCRIBER_CONTRACT")
public class SubscriberContract  extends GenericModel{
	@EmbeddedId
	public SubscriberContractPK pk;
	@Column(name="PROPOSITION")
	public String proposition;
}
