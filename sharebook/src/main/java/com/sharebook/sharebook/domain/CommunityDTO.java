package com.sharebook.sharebook.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommunityDTO {
	private int community_id;
	private String title;
	public int category;
	private String content;
	private int views;
	private Date upload_date;
	private int member_id;
}
