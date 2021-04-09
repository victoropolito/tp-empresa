package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.MemberDao;
import model.entities.Department;
import model.entities.Member;

public class Program {

	public static void main(String[] args) {

		MemberDao memberDao = DaoFactory.createMemberDao();
		
		System.out.println("=== TEST 1: member findById ===");
		Member member = memberDao.findById(0);
		System.out.println(member);
		
		System.out.println("=== TEST 2: member findByDepartment ===");
		Department department = new Department(2, null);
		List<Member> list = memberDao.findByDepartment(department);
		for(Member obj : list) {
			System.out.println(obj);
		}
	}
}
