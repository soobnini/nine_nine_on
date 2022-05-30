package com.sharebook.sharebook.domain;

import lombok.Data;

@Data
public class MemberDTO {
	private int member_id;
	private String email;
	private String password;
	private String name;
	private String nickname;
	private String phone;
	private String address1;
	private String address2;
	private float temperature;
	
}
