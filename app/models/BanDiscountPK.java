package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class BanDiscountPK implements Serializable{
	@Column(name="BAN")
	public int ban;
	@Column(name="SUBSCRIBER_NO")
	public String subscriberNo;
}
