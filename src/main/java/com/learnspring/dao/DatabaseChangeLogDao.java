package com.learnspring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.learnspring.model.Databasechangelog;
import com.learnspring.model.DatabasechangelogPK;
import com.learnspring.model.Tag;

@Repository
public class DatabaseChangeLogDao extends AbstractDao {

	public List<Databasechangelog> getDatabaseChangeLogDetails() {
		Criteria createCriteria = getSession().createCriteria(Databasechangelog.class);
		List<Databasechangelog> list = createCriteria.list();
		return list;
	}

	public Databasechangelog getChangeLogDetails(DatabasechangelogPK pk) {
		Criteria createCriteria = getSession().createCriteria(Databasechangelog.class);
		createCriteria.add(Restrictions.eq("id", pk));

		Databasechangelog uniqueResult = (Databasechangelog) createCriteria.uniqueResult();
		return uniqueResult;

	}
	
	public void setTag(Tag tag){
		
		Criteria createCriteria = getSession().createCriteria(Databasechangelog.class);
		createCriteria.add(Restrictions.eq("id", tag.getPk()));

		Databasechangelog uniqueResult = (Databasechangelog) createCriteria.uniqueResult();
		uniqueResult.setTag(tag.getTag());
		
	}
}
