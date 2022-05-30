package com.sharebook.sharebook.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommentDTO {
	private int comment_id;
	private String content;
	private Date upload_date;
	private int community_id;
	private int member_id;
}
