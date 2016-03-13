package com.chaljaa.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaljaa.dao.EsActivityDao;
import com.chaljaa.dao.EsSequenceGeneratorDao;
import com.chaljaa.enumeration.SequenceTitle;
import com.chaljaa.enumeration.Status;
import com.chaljaa.exception.EsBadDataException;
import com.chaljaa.exception.EsBusinessException;
import com.chaljaa.exception.EsDataValidationException;
import com.chaljaa.exception.EsDatabaseException;
import com.chaljaa.model.EsActivity;
import com.chaljaa.model.EsSequenceGenerator;
import com.chaljaa.util.ErrorDetail;
import com.chaljaa.util.FieldError;

@Service("esActivityService")
@Transactional
public class EsActivityServiceImpl implements ESActivityService {
    
    @Autowired
    private EsActivityDao esActivityDao;
    
    private EsSequenceGeneratorDao esSequenceGeneratorDao;

    private List<FieldError> validate(EsActivity activity, List<FieldError> fieldErrors) throws EsDatabaseException
    {
        if(activity == null)
        {
            fieldErrors.add(new FieldError(1, "Activity", "Acitivty Data not sent"));
        }
        else
        {
            if(activity.getId() != null && activity.getId() <= 0)
            {
                fieldErrors.add(new FieldError(2, "Activity Id", "Activity Id can not be less than or equal to zero"));
            }
            
            if(activity.getCode() != null && activity.getCode().equals(""))
            {
                fieldErrors.add(new FieldError(3, "Acctivity Code", "Activity Code can not be an empty string"));
            }
            
            if(activity.getName() == null || activity.getName().equals(""))
            {
                fieldErrors.add(new FieldError(4, "Activity Name", "Activity Name can not be null or empty"));
            }
            else
            {
                if(!activity.getName().matches("^[a-zA-Z0-9]+( [a-zA-Z0-9]+)*"))
                {
                    fieldErrors.add(new FieldError(5, "Activity name", "Activity name can not start or end with a space. It can have only a single space between 2 words"));
                }
                else
                {
                    if(activity.getCode() == null)
                    {
                        if(esActivityDao.findByName(activity.getName()) != null)
                        {
                            fieldErrors.add(new FieldError(6, "Activity name", "Activity with name " + activity.getName() + " already present in the datastore"));
                        }
                    }
                }
            }
        }
        
        return fieldErrors;
    }
    
    @Override
    public Long create(EsActivity activity, String username) throws EsDataValidationException, EsBusinessException {
        
        try
        {
            List<FieldError> fieldErrors = new ArrayList<FieldError>();
            fieldErrors = validate(activity, fieldErrors);
            
            if(!fieldErrors.isEmpty())
            {
                for (FieldError fieldError : fieldErrors) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, fieldError.getMessage());
                }
                
                ErrorDetail errorDetail = new ErrorDetail();
                errorDetail.setErrorCode(1);
                errorDetail.setErrorMessage("Data validation error at the time of creating the Activity");
                errorDetail.setFieldErrors(fieldErrors);
                errorDetail.setHumanizedMessage("Data validations failed at the time of creating the Activity. Please check field errors for more information");
                
                throw new EsDataValidationException("Unable to create the Activity. Please provide a valid Data", errorDetail);
            }
            
            activity.setActive(true);
            
            EsSequenceGenerator sequenceGenerator = esSequenceGeneratorDao.fndByTitle(SequenceTitle.EsActivity);
            
            activity.setCode(sequenceGenerator.getCodePreffix() + (sequenceGenerator.getValue() + 1));
            activity.setCreatedBy(username);
            activity.setDateTimeModified(Calendar.getInstance().getTime());
            activity.setModifiedBy(username);
            
            sequenceGenerator.setValue(sequenceGenerator.getValue() + 1);
            esSequenceGeneratorDao.edit(sequenceGenerator);
            
            Long id = esActivityDao.save(activity);
            
            return id;
        }
        catch(EsDatabaseException e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            throw new EsBusinessException("General Error while creating a new Activity. Please try again later");
        }
        
    }

    @Override
    public EsActivity findByCode(String code) throws EsBadDataException, EsBusinessException {
        if(code  == null || code.equals(""))
            throw new EsBadDataException("Activity code can not be null or empty");
        
        try
        {
            EsActivity activity = esActivityDao.findByCode(code);
            return activity;
        }
        catch (EsDatabaseException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            throw new EsBusinessException("General Exception while fetching the activity with code : " + code);
        }
    }

    @Override
    public EsActivity findById(Long id) throws EsBadDataException, EsBusinessException {
        
        if(id == null || id.longValue() <= 0)
            throw new EsBadDataException("Id of the activity can not be less than or equal to zero");
        
        try
        {
            EsActivity activity = esActivityDao.findById(id);
            return activity;
        }
        catch(EsDatabaseException e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            throw new EsBusinessException("General Exception while fetching the activity with id : " + id);
        }
    }

    @Override
    public List<EsActivity> findAllByStatus(Status status) throws EsBusinessException {
        try
        {
            List<EsActivity> activities = esActivityDao.findAllByStatus(status);
            return activities;
        }
        catch(EsDatabaseException e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            throw new EsBusinessException("General error while fetching activities that are : " + status.name());
        }
    }

    @Override
    public EsActivity edit(EsActivity activity, String username) throws EsBusinessException, EsDataValidationException, EsBadDataException {
        
        try
        {
            List<FieldError> fieldErrors = new ArrayList<FieldError>();
            fieldErrors = validate(activity, fieldErrors);
            
            if(!fieldErrors.isEmpty())
            {
                for (FieldError fieldError : fieldErrors) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, fieldError.getMessage());
                }
                
                ErrorDetail errorDetail = new ErrorDetail();
                errorDetail.setErrorCode(1);
                errorDetail.setErrorMessage("Data validation error at the time of updating the Activity");
                errorDetail.setFieldErrors(fieldErrors);
                errorDetail.setHumanizedMessage("Data validations failed at the time of updating the Activity. Please check field errors for more information");
                
                throw new EsDataValidationException("Unable to update the Activity. Please provide a valid Data", errorDetail);
            }
            
            EsActivity esActivity = esActivityDao.findByCode(activity.getCode());
            if(!esActivity.getActive())
            {
                throw new EsBadDataException("Activity with code : " + activity.getCode() + " is not active in the system");
            }
            
            esActivity.setDateTimeModified(Calendar.getInstance().getTime());
            esActivity.setModifiedBy(username);
            esActivity.setName(activity.getName());
            
            esActivityDao.Update(esActivity);
            
            return esActivity;
        }
        catch(EsDatabaseException e)        
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            throw new EsBusinessException("General Error while updating the activity wiht code : " + activity.getCode());
        }
    }

    @Override
    public EsActivity InactivateByCode(String code, String username) throws EsBadDataException, EsBusinessException {
        if(code == null || code.equals(""))
            throw new EsBadDataException("Code of an activity can not be null or empty");
        try
        {
            EsActivity activity = esActivityDao.findByCode(code);
            
            if(!activity.getActive())
            {
                throw new EsBadDataException("Activity with code : " + code + " is inactive in the system");
            }
            
            activity.setActive(false);
            activity.setDateTimeModified(Calendar.getInstance().getTime());
            activity.setModifiedBy(username);
            
            esActivityDao.Update(activity);
            
            return activity;
        }
        catch(EsDatabaseException e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            throw new EsBusinessException("General Error while inactivating the activity with code : " + code);
        }
    }

    @Override
    public EsActivity AcitvateByCode(String code, String username) throws EsBadDataException, EsBusinessException {
        if(code == null || code.equals(""))
            throw new EsBadDataException("Code of an activity can not be null or empty");
        try
        {
            EsActivity activity = esActivityDao.findByCode(code);
            
            if(activity.getActive())
            {
                throw new EsBadDataException("Activity with code : " + code + " is active in the system");
            }
            
            activity.setActive(true);
            activity.setDateTimeModified(Calendar.getInstance().getTime());
            activity.setModifiedBy(username);
            
            esActivityDao.Update(activity);
            
            return activity;
        }
        catch(EsDatabaseException e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            throw new EsBusinessException("General Error while inactivating the activity with code : " + code);
        }
        
    }

    @Override
    public void DeleteByCode(String code) {
        // TODO Auto-generated method stub
        
    }
}
