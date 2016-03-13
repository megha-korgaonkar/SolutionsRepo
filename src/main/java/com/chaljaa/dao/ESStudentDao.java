package com.chaljaa.dao;

import java.util.List;

import com.chaljaa.model.ESStudent;

public interface ESStudentDao {

	ESStudent findById(int id);

	ESStudent findByCode(String code);

	void save(ESStudent user);

	void deleteByUserCode(String sso);

	List<ESStudent> findAllUsers();

}
