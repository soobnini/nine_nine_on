package com.sharebook.sharebook.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sharebook.sharebook.controller.MemberForm;
import com.sharebook.sharebook.domain.Member;

@Component
public class MemberFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		MemberForm memberForm = (MemberForm)obj; 
		Member member = memberForm.getAccount();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.member_id", "FIRST_NAME_REQUIRED", "First name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.email", "LAST_NAME_REQUIRED", "Last name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.password", "EMAIL_REQUIRED", "Email address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.name", "PHONE_REQUIRED", "Phone number is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.phone", "ADDRESS_REQUIRED", "Address (1) is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.address1", "CITY_REQUIRED", "City is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.address2", "STATE_REQUIRED", "State is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.temperature", "ZIP_REQUIRED", "ZIP is required.");
		
		if (memberForm.isNewMember()) {
//			member.setStatus("OK");
			// 여기서부터 다시 보기
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.username", "USER_ID_REQUIRED", "User ID is required.");
			if (member.getPassword() == null || member.getPassword().length() < 1 ||
					!member.getPassword().equals(memberForm.getRepeatedPassword())) {
				errors.reject("PASSWORD_MISMATCH",
					 "Passwords did not match or were not provided. Matching passwords are required.");
			}
		}
		else if (member.getPassword() != null && member.getPassword().length() > 0) {
			if (!member.getPassword().equals(memberForm.getRepeatedPassword())) {
				errors.reject("PASSWORD_MISMATCH", "Passwords did not match. Matching passwords are required.");
			}
		}
	}
}