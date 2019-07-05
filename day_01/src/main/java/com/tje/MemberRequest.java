package com.tje;

public class MemberRequest {
	private Member member;

	public MemberRequest() {
		member = new Member();
		member.setName("ABC");
		member.setAge(22);
	}

	public Member getMember() {
		return this.member;
	}
}