package com.sharebook.sharebook.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebook.sharebook.domain.Book;
import com.sharebook.sharebook.domain.Member;
import com.sharebook.sharebook.domain.Member_genre;
import com.sharebook.sharebook.repository.GenreRepository;
import com.sharebook.sharebook.repository.MemberRepository;
import com.sharebook.sharebook.repository.Member_genreRepository;

@Service
public class MemberService {
	@Autowired
	public MemberRepository memberRepository;
	
	@Autowired
	public Member_genreRepository member_genreRepository;

	public void setMemberRepository(MemberRepository memberRepository, Member_genreRepository member_genreRepository) {
		this.memberRepository = memberRepository;
		this.member_genreRepository = member_genreRepository;
	}

	@Transactional
	public int createMember(Member member) {
		return memberRepository.save(member).getMember_id();// pk없으면 persistance 실행 후 id 반환(create)
	}

	@Transactional
	public void updateMember(Member member) {
		memberRepository.save(member);// pk 설정시 자동으로 merge 실행(update)
	}
	
	@Transactional
	public void deleteMember(Member member) {
		memberRepository.delete(member);// delete
	}

	public Member getMember(int member_id) {
		Optional<Member> result = memberRepository.findById(member_id);
		if (result.isPresent())
			return result.get();
		return null;
	}//id로 member 찾기

	public List<Member> getNearMembers(String address1, String address2) {
		return memberRepository.findByAddress1AndAddress2(address1, address2);
	}//가까운 유저 리스트
	
	public Member findByEmailAndPassword(String email, String password) {
		return memberRepository.findByEmailAndPassword(email, password);
	}
	
	public List<Member_genre> findMember_genreList(){
		return (List<Member_genre>)member_genreRepository.findAll();
	}
	
	public List<Member_genre> findMember_genreListByMember(Member member) {
		return member_genreRepository.findByMember(member);
	}

	public List<Member_genre> findMember_genreListByGenre(Genre genre) {
		return member_genreRepository.findByGenre(genre);
	}
}
