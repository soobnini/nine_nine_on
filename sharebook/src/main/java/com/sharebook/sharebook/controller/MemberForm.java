package com.sharebook.sharebook.controller;

import java.io.Serializable;

import javax.persistence.Entity;

import com.sharebook.sharebook.domain.Member;

@SuppressWarnings("serial")
public class MemberForm implements Serializable {

	private Member member;

	private boolean newMember;

	private String repeatedPassword;

	public MemberForm(Member member) {
		this.member = member;
		this.newMember = false;
	}

	public MemberForm() {
		this.member = new Member();
		this.newMember = true;
	}

	public Member getAccount() {
		return member;
	}

	public boolean isNewMember() {
		return newMember;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}
}
