package application;

import model.dao.DaoFactory;
import model.dao.MemberDao;
import model.entities.Member;

public class Program {

	public static void main(String[] args) {

		MemberDao memberDao = DaoFactory.createMemberDao();
		
		Member member = memberDao.findById(1);
		
		System.out.println(member);
	}
}
