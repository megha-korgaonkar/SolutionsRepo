package com.chaljaa.dao;

import com.chaljaa.enumeration.SequenceTitle;
import com.chaljaa.exception.EsDatabaseException;
import com.chaljaa.model.EsSequenceGenerator;

public interface EsSequenceGeneratorDao {
    
    EsSequenceGenerator fndByTitle(SequenceTitle title) throws EsDatabaseException;
    
    void edit(EsSequenceGenerator sequenceGenerator) throws EsDatabaseException;
}
