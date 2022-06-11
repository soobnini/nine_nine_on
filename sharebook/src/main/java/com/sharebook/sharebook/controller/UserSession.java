package com.sharebook.sharebook.controller;

import java.io.Serializable;
import org.springframework.beans.support.PagedListHolder;

import com.sharebook.sharebook.domain.Member;

@SuppressWarnings("serial")
public class UserSession implements Serializable {

	private Member member;

//	private PagedListHolder<Product> myList;

	public UserSession(Member member) {
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	/*
	public void setMyList(PagedListHolder<Product> myList) {
		this.myList = myList;
	}

	public PagedListHolder<Product> getMyList() {
		return myList;
	}
	*/
}
