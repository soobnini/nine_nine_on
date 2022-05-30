package com.sharebook.sharebook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Funding_order {
	private int funding_id; // PK
	private int member_id; // FK
	private int reward_id; // FK
}
