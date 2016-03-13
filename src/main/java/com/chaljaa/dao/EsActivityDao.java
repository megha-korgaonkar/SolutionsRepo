package com.chaljaa.dao;

import java.util.List;

import com.chaljaa.enumeration.Status;
import com.chaljaa.exception.EsDatabaseException;
import com.chaljaa.model.EsActivity;

public interface EsActivityDao {
    
    EsActivity findByName(String name) throws EsDatabaseException;
    
    Long save(EsActivity activity) throws EsDatabaseException;
    
    EsActivity findByCode(String code) throws EsDatabaseException;    
   
    EsActivity findById(Long id) throws EsDatabaseException;
    
    List<EsActivity> findAllByStatus(Status status) throws EsDatabaseException;
    
    void Update(EsActivity activity) throws EsDatabaseException;
}
