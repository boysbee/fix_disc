package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

import play.data.validation.*;
import util.DateUtil;

@Entity
@Table(name = "CSM_DISCOUNT")
public class CsmDiscount extends GenericModel {
	@EmbeddedId
	public CsmDiscountPK pk;

	public CsmDiscount() {
		this.pk = new CsmDiscountPK();
		this.businessOwner = "";
		this.keyword = "";
		this.devName = "";
		this.remark = "";
		this.pp = "";
		this.propo = "";
		this.soc = "";
		this.actvCode = "";
		this.actvRsnCode = "";
		this.accType = "";
		this.accCate = "";
		this.benefit = "";
		this.advancePayment = "";
		this.projectStartDate = null;
		this.projectEndDate = null;
		this.sysCreationDate = new Date();
		this.sysUpdateDate = new Date();

	}

	@Column(name = "BUSINESS_OWNER")
	public String businessOwner;
	@Column(name = "KEYWORD")
	public String keyword;
	@Column(name = "DEV_NAME")
	public String devName;
	@Column(name = "REMARK")
	public String remark;
	@Column(name = "PP")
	public String pp;
	@Column(name = "PROPO")
	public String propo;
	@Column(name = "SOC")
	public String soc;
	@Column(name = "ACTV_CODE")
	public String actvCode;
	@Column(name = "ACTV_RSN_CODE")
	public String actvRsnCode;
	@Column(name = "ACC_TYPE")
	public String accType;
	@Column(name = "ACC_CATE")
	public String accCate;
	@Column(name = "BENEFIT")
	public String benefit;
	@Column(name = "ADVANCE_PAYMENT")
	public String advancePayment;
	@Temporal(TemporalType.DATE)
	@Column(name = "PROJECT_START_DATE")
	private java.util.Date projectStartDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "PROJECT_END_DATE")
	private java.util.Date projectEndDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "SYS_CREATION_DATE")
	private java.util.Date sysCreationDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "SYS_UPDATE_DATE")
	private java.util.Date sysUpdateDate;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("jobName :").append(this.pk.jobName).append(",");
		sb.append("discountCode :").append(this.pk.discountCode).append(",");
		sb.append("ucrNo :").append(this.pk.ucrNo).append(",");
		sb.append("devName :").append(this.devName).append(",");
		sb.append("businessOwner :").append(this.businessOwner).append(",");
		sb.append("keyword :").append(this.keyword).append(",");
		sb.append("propo :").append(this.propo).append(",");
		sb.append("pp :").append(this.pp).append(",");
		sb.append("soc :").append(this.soc).append(",");
		sb.append("actvCode :").append(this.actvCode).append(",");
		sb.append("actvRsnCode :").append(this.actvRsnCode).append(",");
		sb.append("accType :").append(this.accType).append(",");
		sb.append("accCate :").append(this.accCate).append(",");
		sb.append("benefit :").append(this.benefit).append(",");
		sb.append("advancePayment :").append(this.advancePayment).append(",");
		if (this.projectStartDate != null) {
			sb.append("projectStartDate :")
					.append(this.projectStartDate.toString()).append(",");
		} else {
			sb.append("projectStartDate :").append("").append(",");
		}
		if (this.projectEndDate != null) {
			sb.append("projectEndDate :")
					.append(this.projectEndDate.toString());
		} else {
			sb.append("projectEndDate :").append("");
		}

		if (this.sysCreationDate != null) {
			sb.append("sysCreationDate :").append(
					this.sysCreationDate.toString());
		} else {
			sb.append("sysCreationDate :").append("");
		}

		if (this.sysUpdateDate != null) {
			sb.append("sysUpdateDate :").append(this.sysUpdateDate.toString());
		} else {
			sb.append("sysUpdateDate :").append("");
		}
		return sb.toString();
	}

	public java.util.Date getProjectStartDate() {
		String source = DateUtil.date2Str(projectStartDate, "dd/MM/yyyy");
		return DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public void setProjectStartDate(java.util.Date projectStartDate) {
		String source = DateUtil.date2Str(projectStartDate, "dd/MM/yyyy");
		this.projectStartDate = DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public java.util.Date getProjectEndDate() {
		String source = DateUtil.date2Str(projectStartDate, "dd/MM/yyyy");
		return DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public void setProjectEndDate(java.util.Date projectEndDate) {
		String source = DateUtil.date2Str(projectEndDate, "dd/MM/yyyy");
		this.projectEndDate = DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public java.util.Date getSysCreationDate() {
		String source = DateUtil.date2Str(sysCreationDate, "dd/MM/yyyy");
		return DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public void setSysCreationDate(java.util.Date sysCreationDate) {
		String source = DateUtil.date2Str(sysCreationDate, "dd/MM/yyyy");
		this.sysCreationDate = DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public java.util.Date getSysUpdateDate() {
		String source = DateUtil.date2Str(sysUpdateDate, "dd/MM/yyyy");
		return DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public void setSysUpdateDate(java.util.Date sysUpdateDate) {
		String source = DateUtil.date2Str(sysUpdateDate, "dd/MM/yyyy");
		this.sysUpdateDate = DateUtil.str2Date(source, "dd/MM/yyyy");
	}

	public static List<CsmDiscount> findList(String jobName,
			String discountCode, String ucrNo, String keyword, Integer size,
			Integer page) {
		Logger.info(
				"@@ fiindList -> job_name : %s , discount_code : %s , ucr_no : %s , keyword : %s",
				jobName, discountCode, ucrNo, keyword);
		List<CsmDiscount> csmdiscounts = null;
		page = page != null ? page : 1;
		if ((jobName != null && !"".equals(jobName))
				&& (discountCode != null && !"".equals(discountCode))
				&& (ucrNo != null && !"".equals(ucrNo))) {
			csmdiscounts = all().fetch(page, size);
		} else {
			String sql = "1=1 ";
			if (jobName != null && !"".equals(jobName)) {
				jobName = jobName.toLowerCase();
				sql += "AND lower(pk.jobName) like '%" + jobName + "%' ";
			}
			if (discountCode != null && !"".equals(discountCode)) {
				discountCode = discountCode.toLowerCase();
				sql += "AND lower(pk.discountCode) like '%" + discountCode
						+ "%' ";
			}
			if (ucrNo != null && !"".equals(ucrNo)) {
				ucrNo = ucrNo.toLowerCase();
				sql += "AND lower(pk.ucrNo) like '%" + ucrNo + "%' ";
			}

			Logger.info("@@ find with HQL : %s", sql);
			csmdiscounts = find(sql).fetch(page, size);
		}
		return csmdiscounts;
	}

}