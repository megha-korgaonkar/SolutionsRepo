package com.chaljaa.service;

import java.util.List;

import com.chaljaa.model.ESStudent;


public interface StudentService {

	ESStudent findById(int id);
	
	ESStudent findByUserCode(String code);
	
	void saveStudent(ESStudent user);
	
	void updateStudent(ESStudent user);
	
	void deleteStudentByUserCode(String sso);

	List<ESStudent> findAllStudents(); 
	
	boolean isUserCodeUnique(Integer id, String sso);

}
