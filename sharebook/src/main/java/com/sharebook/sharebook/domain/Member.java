
package com.sharebook.sharebook.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Entity
@Table(name="MEMBER")
@Data
@AllArgsConstructor 
@NoArgsConstructor
@SequenceGenerator(name="MEMBER_SEQ_GENERATOR", sequenceName="MEMBER_SEQ", initialValue=1, allocationSize=1)
public class Member implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MEMBER_SEQ_GENERATOR")
	@Column(name="MEMBER_ID")
	private int member_id;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="NICKNAME")
	private String nickname;
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="ADDRESS1")
	private String address1;
	
	@Column(name="ADDRESS2")
	private String address2;
	
	@Column(name="TEMPERATURE")
	private float temperature;
	
	@Column(name="IMAGE")
	private String image;
	
	@Column(name="ADMIN")
	private Integer admin;
	
	public Member getMember() {
		return getMember();
	}
}
