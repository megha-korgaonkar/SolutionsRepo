package com.chaljaa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chaljaa.dao.ESStudentDao;
import com.chaljaa.model.ESStudent;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {
	@Autowired
	private ESStudentDao dao;

	@Override
	public ESStudent findById(int id) {
		return dao.findById(id);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 */
	@Override
	public ESStudent findByUserCode(String code) {
		ESStudent user = dao.findByCode(code);
		return user;
	}

	@Override
	public void saveStudent(ESStudent user) {

		user.getStudentInfo().setActive(true);
		user.getStudentInfo().setActive(true);
		user.getStudentInfo().setCreated(new Date());
		user.getStudentInfo().setDateTimeModified(new Date());
		user.getStudentInfo().setCreatedBy("Admin");
		user.getStudentInfo().setModifiedBy("admin");
		user.getMedical().setActive(true);
		user.getMedical().setActive(true);
		user.getMedical().setCreated(new Date());
		user.getMedical().setDateTimeModified(new Date());
		user.getMedical().setCreatedBy("Admin");
		user.getMedical().setModifiedBy("admin");
		user.getAddress().setActive(true);
		user.getAddress().setCreated(new Date());
		user.getAddress().setDateTimeModified(new Date());
		user.getAddress().setCreatedBy("Admin");
		user.getAddress().setModifiedBy("admin");
		ESStudent es = new ESStudent(true, new Date(),
				user.getDateTimeModified(), "admin", "admin", 1,
				user.getMedical(), user.getFirstName(), user.getMiddleName(),
				user.getLastName(), user.getStudentInfo(), user.getAddress(),
				user.getUserCode());
		es.setContacts(user.getContacts());
		dao.save(es);

	}

	@Override
	public void updateStudent(ESStudent user) {
		ESStudent entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setUserCode(user.getUserCode());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setMiddleName(user.getMiddleName());
			entity.setStudentInfo(user.getStudentInfo());
			entity.setAddress(user.getAddress());
			entity.setMedical(user.getMedical());
		}
	}

	@Override
	public void deleteStudentByUserCode(String sso) {
		dao.deleteByUserCode(sso);

	}

	@Override
	public List<ESStudent> findAllStudents() {
		return dao.findAllUsers();
	}

	@Override
	public boolean isUserCodeUnique(Integer id, String code) {
		ESStudent user = findByUserCode(code);
		return (user == null || ((id != null) && (user.getId() == id)));
	}
}
