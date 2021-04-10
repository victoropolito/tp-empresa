package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.MemberDao;
import model.entities.Department;
import model.entities.Member;

public class Program {

	public static void main(String[] args) {

		MemberDao memberDao = DaoFactory.createMemberDao();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("=== TEST 1: member findById ===");
		Member member = memberDao.findById(0);
		System.out.println(member);
		
		System.out.println("=== TEST 2: member findByDepartment ===");
		Department department = new Department(2, null);
		List<Member> list = memberDao.findByDepartment(department);
		for(Member obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("=== TEST 3: member findAll ===");
		list = memberDao.findAll();
		for(Member obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("=== TEST 4: member insert ===");
		Member newMember = new Member(null, "Victor", "SI", "Dev.", department);
		memberDao.insert(newMember);
		System.out.println("Inserted! New id = " + newMember.getId());
		
		System.out.println("=== TEST 5: member update ===");
		member = memberDao.findById(1);
		member.setName("Melissa");
		memberDao.update(member);
		System.out.println("Updated completed!");
		
		System.out.println("=== TEST 6: member delete ===");
		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();
		memberDao.deleteById(id);
		System.out.println("Delete completed!");
		
		sc.close();
	}
}
