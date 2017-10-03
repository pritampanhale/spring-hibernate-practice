package com.learnspring.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name="Databasechangelog.findAll", query="SELECT d FROM Databasechangelog d")
public class Databasechangelog implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatabasechangelogPK id;

	private String comments;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateexecuted;

	private String description;

	private String exectype;

	private String liquibase;

	private String md5sum;

	private int orderexecuted;

	private String tag;

	public Databasechangelog() {
	}

	public DatabasechangelogPK getId() {
		return this.id;
	}

	public void setId(DatabasechangelogPK id) {
		this.id = id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDateexecuted() {
		return this.dateexecuted;
	}

	public void setDateexecuted(Date dateexecuted) {
		this.dateexecuted = dateexecuted;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExectype() {
		return this.exectype;
	}

	public void setExectype(String exectype) {
		this.exectype = exectype;
	}

	public String getLiquibase() {
		return this.liquibase;
	}

	public void setLiquibase(String liquibase) {
		this.liquibase = liquibase;
	}

	public String getMd5sum() {
		return this.md5sum;
	}

	public void setMd5sum(String md5sum) {
		this.md5sum = md5sum;
	}

	public int getOrderexecuted() {
		return this.orderexecuted;
	}

	public void setOrderexecuted(int orderexecuted) {
		this.orderexecuted = orderexecuted;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}