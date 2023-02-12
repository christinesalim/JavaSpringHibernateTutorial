package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public boolean addAccount() {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING MEMBERSHIP ACCOUNT");
	
		return true;
	}
}
