package com.chaljaa.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.chaljaa.model.ContactType;
import com.chaljaa.model.ESContact;
import com.chaljaa.model.ESStudent;
import com.chaljaa.model.User;
import com.chaljaa.model.UserProfile;
import com.chaljaa.service.StudentService;
import com.chaljaa.service.UserProfileService;
import com.chaljaa.service.UserService;
import com.chaljaa.tos.ESStudentTO;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	UserService userService;// test

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	StudentService studentService;

	@Autowired
	MessageSource messageSource;

	/**
	 * This method will list all existing users.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAutoGrowNestedPaths(false);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "userslist";
	}

	@RequestMapping(value = { "/", "/studentlist" }, method = RequestMethod.GET)
	public String listStudents(ModelMap model) {

		List<ESStudent> esstudents = studentService.findAllStudents();
		model.addAttribute("esstudents", esstudents);
		return "studentslist";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be
		 * implementing custom @Unique annotation and applying it on field [sso]
		 * of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you
		 * can fill custom errors outside the validation framework as well while
		 * still using internationalized messages.
		 */
		if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
			FieldError ssoError = new FieldError("user", "ssoId",
					messageSource.getMessage("non.unique.ssoId",
							new String[] { user.getSsoId() },
							Locale.getDefault()));
			result.addError(ssoError);
			return "registration";
		}

		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "
				+ user.getLastName() + " registered successfully");
		// return "success";
		return "registrationsuccess";
	}

	@RequestMapping(value = { "/newstudent" }, method = RequestMethod.GET)
	public String newStudent(ModelMap model) {

		ESStudentTO esstudent = new ESStudentTO();
		model.addAttribute("esstudent", esstudent);
		model.addAttribute("edit", false);
		return "studentregistration";
	}

	@RequestMapping(value = { "/newstudent" }, method = RequestMethod.POST)
	public String saveStudent(@Valid ESStudentTO esstudent,
			BindingResult result, ModelMap model) {
		System.out.println("binding" + result);
		if (result.hasErrors()) {
			model.addAttribute("esstudent", esstudent);
			return "studentregistration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be
		 * implementing custom @Unique annotation and applying it on field [sso]
		 * of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you
		 * can fill custom errors outside the validation framework as well while
		 * still using internationalized messages.
		 */
		if (!studentService.isUserCodeUnique(esstudent.getId(),
				esstudent.getUserCode())) {
			FieldError codeError = new FieldError("user", "user Code",
					messageSource.getMessage("non.unique.ssoId",
							new String[] { esstudent.getUserCode() },
							Locale.getDefault()));
			result.addError(codeError);
			return "studentregistration";
		}
		ESStudent esStudentEntity = new ESStudent();
		esStudentEntity.setFirstName(esstudent.getFirstName());
		esStudentEntity.setLastName(esstudent.getLastName());
		esStudentEntity.setMiddleName(esstudent.getMiddleName());
		esStudentEntity.setAddress(esstudent.getAddress());
		esStudentEntity.setMedical(esstudent.getMedical());
		esStudentEntity.setStudentInfo(esstudent.getStudentInfo());
		List<ESContact> esContacts = new ArrayList<ESContact>();
		ESContact ePhone = new ESContact();
		ePhone.setData(esstudent.getPhone());
		ePhone.setType(ContactType.MOBILE);
		ePhone.setModifiedBy("admin");
		ePhone.setActive(true);
		ePhone.setCreated(new Date());
		ePhone.setCreatedBy("admin");
		esContacts.add(ePhone);
		ESContact esEmail = new ESContact();
		esEmail.setData(esstudent.getEmail());
		esEmail.setType(ContactType.EMAIL);
		esEmail.setModifiedBy("admin");
		esEmail.setActive(true);
		esEmail.setCreated(new Date());
		esEmail.setCreatedBy("admin");
		esContacts.add(esEmail);
		ESContact esEmergency = new ESContact();
		esEmergency.setData(esstudent.getEmergencyContact());
		esEmergency.setType(ContactType.EMERGENCY);
		esEmergency.setModifiedBy("admin");
		esEmergency.setActive(true);
		esEmergency.setCreated(new Date());
		esEmergency.setCreatedBy("admin");
		esContacts.add(esEmergency);
		esStudentEntity.setContacts(esContacts);
		studentService.saveStudent(esStudentEntity);

		model.addAttribute("success", "User " + esstudent.getFirstName() + " "
				+ esstudent.getLastName() + " registered successfully");
		// return "success";
		return "studentsuccess";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * //Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in
		 * UI which is a unique key to a User.
		 * if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
		 * FieldError ssoError =new
		 * FieldError("user","ssoId",messageSource.getMessage
		 * ("non.unique.ssoId", new String[]{user.getSsoId()},
		 * Locale.getDefault())); result.addError(ssoError); return
		 * "registration"; }
		 */

		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "
				+ user.getLastName() + " updated successfully");
		return "registrationsuccess";
	}

	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

	@RequestMapping(value = { "/edit-esstudent-{userCode}" }, method = RequestMethod.GET)
	public String editStudent(@PathVariable String userCode, ModelMap model) {
		ESStudent esstudent = studentService.findByUserCode(userCode);
		model.addAttribute("esstudent", esstudent);
		model.addAttribute("edit", true);
		return "studentregistration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-esstudent-{userCode}" }, method = RequestMethod.POST)
	public String updateStudent(@Valid ESStudent esstudent,
			BindingResult result, ModelMap model, @PathVariable String userCode) {

		if (result.hasErrors()) {
			return "studentregistration";
		}

		/*
		 * //Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in
		 * UI which is a unique key to a User.
		 * if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
		 * FieldError ssoError =new
		 * FieldError("user","ssoId",messageSource.getMessage
		 * ("non.unique.ssoId", new String[]{user.getSsoId()},
		 * Locale.getDefault())); result.addError(ssoError); return
		 * "registration"; }
		 */

		// userService.updateUser(user);
		studentService.updateStudent(esstudent);

		model.addAttribute("success", "User " + esstudent.getFirstName() + " "
				+ esstudent.getLastName() + " updated successfully");
		return "studentsuccess";
	}

	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-esstudent-{userCode}" }, method = RequestMethod.GET)
	public String deleteStudent(@PathVariable String userCode) {
		// userService.deleteUserBySSO(ssoId);
		studentService.deleteStudentByUserCode(userCode);
		return "redirect:/studentlist";
	}

}
