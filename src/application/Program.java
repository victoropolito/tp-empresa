package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.MemberDao;
import model.entities.Department;
import model.entities.Member;

public class Program {

	/* 
	
	AS IMPLEMENTA��ES QUE EST�O EM COMENT�RIO � PQ ESTAVAM APRESENTANDO EXCE��ES AO COMUNICAR COM O BD E AINDA N�O SEI O MOTIVO.
	A L�GICA DELAS EU CREIO QUE ESTEJAM CORRETAS!
	IREI FAZER MAIS ALGUNS TESTES AT� DESCOBRIR O QUE PODE SER
	
	*/
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		MemberDao memberDao = DaoFactory.createMemberDao();
		
		System.out.println("=== TEST 1: member findById ===");
		Member member = memberDao.findById(1);
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
		Member member2 = memberDao.findById(15);
		member2.setName("Melissa");
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
