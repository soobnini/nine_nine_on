
package com.sharebook.sharebook.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Member implements Serializable{
	@Id
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
	
	/*커뮤니티 리스트 JOIN
	 * @OneToMany(mappedBy="member") private List<Community> communityList;
	 */
	/*댓글 리스트
	 * @OneToMany(mappedBy="member") private List<Comment> commentList;
	 */
}
