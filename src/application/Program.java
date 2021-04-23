package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.MemberDao;
import model.entities.Department;
import model.entities.Member;

public class Program {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		MemberDao memberDao = DaoFactory.createMemberDao();
		
		System.out.println("=== TEST 1: member findById ===");
		Member member = memberDao.findById(24);
		System.out.println(member);
		
		System.out.println("\n=== TEST 2: member findByDepartment ===");
		Department department = new Department(1, null);
		List<Member> list = memberDao.findByDepartment(department);
		for(Member obj : list) {
			System.out.println(obj);
		} 
		
		System.out.println("\n=== TEST 3: member findAll ===");
		list = memberDao.findAll();
		for(Member obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 4: member insert ===");
		Member newMember = new Member(null, "Victor", "SI", "Dev.", department);
		memberDao.insert(newMember);
		System.out.println("Inserted! New id = " + newMember.getId());
	
		System.out.println("=== TEST 5: member update ===");
		Member member2 = memberDao.findById(24);
		member2.setName("Melissa");
		//member2.setCategory("Outra categoria");
		//member2.setCourse("Outro curso");
		memberDao.update(member2);
		System.out.println("Updated completed!");
		
		System.out.println("=== TEST 6: member delete ===");
		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();
		memberDao.deleteById(id);
		System.out.println("Delete completed!");
		
		sc.close();
	}
}
