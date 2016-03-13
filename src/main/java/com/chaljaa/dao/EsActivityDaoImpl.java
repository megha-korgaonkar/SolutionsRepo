package com.chaljaa.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.chaljaa.enumeration.Status;
import com.chaljaa.exception.EsDatabaseException;
import com.chaljaa.model.EsActivity;

@Repository("esActivityDao")
public class EsActivityDaoImpl extends AbstractDao<Long, EsActivity> implements EsActivityDao
{
    
    @Override
    public EsActivity findByName(String name) throws EsDatabaseException {
        
        try
        {
            Criteria criteria = getSession().createCriteria(EsActivity.class);
            criteria.add(Restrictions.ilike("name", name, MatchMode.EXACT));
            return (EsActivity) criteria.uniqueResult();
        }
        catch(Exception e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            throw new EsDatabaseException("Database Exception while fetching the Activity with name as : " + name);
        }
    }

    @Override
    public Long save(EsActivity activity) throws EsDatabaseException {
        try
        {
            return (Long) getSession().save(activity);
        }
        catch(Exception e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            throw new EsDatabaseException("Database Exception while saving the new avtivity with name : " + activity.getName());
        }
    }

    @Override
    public EsActivity findByCode(String code) throws EsDatabaseException {
        try
        {
            Criteria criteria = getSession().createCriteria(EsActivity.class);
            criteria.add(Restrictions.eq("code", code));
            return (EsActivity) criteria.uniqueResult();
        }
        catch(Exception e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            throw new EsDatabaseException("Database Exception while fetching activity with Code : " + code);
        }
    }

    @Override
    public EsActivity findById(Long id) throws EsDatabaseException {
        try
        {
            return getByKey(id);
        }
        catch(Exception e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            throw new EsDatabaseException("Database Exception while fetching activity with Id : " + id);
        }
    }

    //TODO: Add Pagination at a later Stage.
    @SuppressWarnings("unchecked")
    @Override
    public List<EsActivity> findAllByStatus(Status status) throws EsDatabaseException {
        try
        {
            Criteria criteria = getSession().createCriteria(EsActivity.class);
            if(status == Status.ACTIVE)
            {
                criteria.add(Restrictions.eq("active", true));
            }
            else if(status == Status.INACTIVE)
            {
                criteria.add(Restrictions.eq("active", false));
            }
            return (List<EsActivity>)criteria.list();
        }
        catch(Exception e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            throw new EsDatabaseException("Database Exception while fetching activities with status as : " + status.name());
        }
    }

    @Override
    public void Update(EsActivity activity) throws EsDatabaseException {
        try
        {
            getSession().update(activity);
        }
        catch(Exception e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            throw new EsDatabaseException("Database Exception while updating activity with Code : " + activity.getCode());
        }
        
    }
    
    
}
