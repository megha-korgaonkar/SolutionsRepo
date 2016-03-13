package com.chaljaa.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.chaljaa.model.ESStudent;

@Repository("studentDao")
public class ESStudentDaoImpl extends AbstractDao<Integer, ESStudent> implements ESStudentDao {

	@Override
	public ESStudent findById(int id) {
		ESStudent user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getAddress());
			Hibernate.initialize(user.getStudentInfo());
			Hibernate.initialize(user.getMedical());
		}
		return user;
	}

	@Override
	public ESStudent findByCode(String code) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userCode", code));
		ESStudent user = (ESStudent)crit.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getAddress());
			Hibernate.initialize(user.getStudentInfo());
			Hibernate.initialize(user.getMedical());		}
		return user;
	}

	@Override
	public void save(ESStudent user) {
		persist(user);
	}

	@Override
	public void deleteByUserCode(String userCode) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userCode", userCode));
		ESStudent user = (ESStudent)crit.uniqueResult();
		delete(user);
	}

	@Override
	public List<ESStudent> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<ESStudent> users = (List<ESStudent>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return users;
	}

}
