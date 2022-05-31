package com.sharebook.sharebook.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward {
	private int reward_id;	// PK
	private int price;
	private String prize;
	private String image;
	private int funding_id;	// FK
}
