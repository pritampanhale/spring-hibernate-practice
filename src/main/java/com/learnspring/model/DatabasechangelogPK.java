package com.learnspring.model;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class DatabasechangelogPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String id;

	private String author;

	private String filename;

	public DatabasechangelogPK() {
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getFilename() {
		return this.filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatabasechangelogPK)) {
			return false;
		}
		DatabasechangelogPK castOther = (DatabasechangelogPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.author.equals(castOther.author)
			&& this.filename.equals(castOther.filename);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.author.hashCode();
		hash = hash * prime + this.filename.hashCode();
		
		return hash;
	}
}