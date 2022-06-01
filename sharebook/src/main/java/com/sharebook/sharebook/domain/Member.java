
package com.sharebook.sharebook.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
@SuppressWarnings("serial")
@Entity
@Table(name="Member")
@Data
public class Member implements Serializable{
	@Id
	private int member_id;
	private String email;
	private String password;
	private String name;
	private String nickname;
	private String phone;
	private String address1;
	private String address2;
	private float temperature;
	
	/*커뮤니티 리스트 JOIN
	 * @OneToMany(mappedBy="member") private List<Community> communityList;
	 */
	/*댓글 리스트
	 * @OneToMany(mappedBy="member") private List<Comment> commentList;
	 */
	public Member() {}//기본 생성자
}
