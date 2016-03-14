<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Registration Form</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>

	<div class="generic-container">
		<div class="well lead">User Registration Form</div>
		<form:form method="POST" modelAttribute="esstudent"
			class="form-horizontal">
			<form:input type="hidden" path="id" id="id" />

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="firstName">First
						Name</label>
					<div class="col-md-7">
						<form:input type="text" path="firstName" id="firstName"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="firstName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="middleName">Middle
						Name</label>
					<div class="col-md-7">
						<form:input type="text" path="middleName" id="middleName"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="middleName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="lastName">Last
						Name</label>
					<div class="col-md-7">
						<form:input type="text" path="lastName" id="lastName"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="lastName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="guardianName">Parent
						/ Guardian Name :</label>
					<div class="col-md-7">
						<form:input type="text" path="studentInfo.guardianName"
							id="guardianName" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="studentInfo.guardianName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="DOB">Date Of
						Birth</label>
					<div class="col-md-7">
						<fmt:formatDate value="${now}"  pattern="MM-dd-yyyy" var="formattedDate" />
						<form:input type="text" path="studentInfo.DOB" id="DOB"
							value="${formattedDate}" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="studentInfo.DOB" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="Mobile">Mobile no.</label>
							<div class="col-md-7">
								<form:input type="text" path="phone" id="phone" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="phone" class="help-inline"/>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="email">Email ID</label>
							<div class="col-md-7">
								<form:input type="text" path="email" id="email" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="email" class="help-inline"/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="emergencyContact">Emergency Contact </label>
							<div class="col-md-7">
								<form:input type="text" path="emergencyContact" id="emergencyContact" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="emergencyContact" class="help-inline"/>
								</div>
							</div>
						</div>
					</div>
					
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="AddressLine1">Address
						Line1</label>
					<div class="col-md-7">
						<form:input type="text" path="address.addressLine1"
							id="addressLine1" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="address.addressLine1" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="AddressLine2">Address
						Line2</label>
					<div class="col-md-7">
						<form:input type="text" path="address.addressLine2"
							id="addressLine2" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="address.addressLine2" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="city">City</label>
					<div class="col-md-7">
						<form:input type="text" path="address.city" id="city"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="address.city" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="state">State</label>
					<div class="col-md-7">
						<form:input type="text" path="address.state" id="State"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="address.state" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="country">Country</label>
					<div class="col-md-7">
						<form:input type="text" path="address.country" id="Country"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="address.country" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="pinCode">Pin
						Code</label>
					<div class="col-md-7">
						<form:input type="text" path="address.pincode" id="Country"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="address.pincode" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="otherDetails">Other
						Details</label>
					<div class="col-md-7">
						<form:input type="text" path="address.otherDetails"
							id="otherDetails" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="address.otherDetails" class="help-inline" />
						</div>
					</div>
				</div>
			</div>`	
		
		<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="bloodGroup">Blood
						Group</label>
					<div class="col-md-7">
						<form:input type="text" path="medical.bloodGroup" id="bloodGroup"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="medical.bloodGroup" class="help-inline" />
						</div>
					</div>
				</div>
			</div>`	
		<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="medicines">Medicines
						Required</label>
					<div class="col-md-7">
						<form:input type="text" path="medical.medicinesRequired"
							id="medicinesRequired" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="medical.medicinesRequired" class="help-inline" />
						</div>
					</div>
				</div>
			</div>`
		<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="otherSpec">Other
						Allergies or Medical Specifications</label>
					<div class="col-md-7">
						<form:input type="text" path="medical.otherSpec" id="bloodGroup"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="medical.otherSpec" class="help-inline" />
						</div>
					</div>
				</div>
			</div>`	
		<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/studentlist' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/studentlist' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>