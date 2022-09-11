package com.sharebook.sharebook.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sharebook.sharebook.controller.MemberCommand;
import com.sharebook.sharebook.domain.Member;

@Component
public class MemberFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Member.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object obj, Errors errors) {
		validateMemberCommand((MemberCommand) obj, errors);
	}

	public void validateMemberCommand(MemberCommand memberCommand, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "EMAIL_REQUIRED", "이메일은 필수 항목입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "PASSWORD_REQUIRED", "비밀번호는 필수 항목입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordCheck", "PASSWORDCHECK_REQUIRED", "비밀번호 확인은 필수 항목입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NAME_REQUIRED", "이름은 필수 항목입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "NICKNAME_REQUIRED", "닉네임은 필수 항목입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "PHONE_REQUIRED", "전화번호는 필수 항목입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address1", "ADDRESS1_REQUIRED", "주소1는 필수 항목입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address2", "ADDRESS2_REQUIRED", "주소2는 필수 항목입니다");
		
		if (!memberCommand.getPassword().equals(memberCommand.getPasswordCheck())) {
			errors.reject("PASSWORD_MISMATCH", "비밀번호가 다릅니다. 비밀번호를 확인해주세요.");
			
		}
	}

}