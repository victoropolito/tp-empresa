package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ProjectDao;
import model.entities.Project;

public class Program3 {

	/*
	 * 
	 * ESSE PROGRAMA É PARA POVOAR OS PROJETOS
	 * 
	 */

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ProjectDao projectDao = DaoFactory.createProjectDao();

		System.out.println("=== TEST 1: findById =======");
		Project prj = projectDao.findById(20);
		System.out.println(prj);

		System.out.println("\n=== TEST 2: findAll =======");
		List<Project> list = projectDao.findAll();
		for (Project d : list) {
			System.out.println(d);
		}

		System.out.println("\n=== TEST 3: insert =======");
		Project newProject = new Project(null, "DB", "CollegeWork");
		projectDao.insert(newProject);
		System.out.println("Inserted! New id: " + newProject.getId());

		/*
		 * System.out.println("\n=== TEST 4: update ======="); Project prj2 =
		 * projectDao.findById(17); prj2.setName("BD"); projectDao.update(prj2);
		 * System.out.println("Update completed");
		 */

		System.out.println("\n=== TEST 4: delete =======");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		projectDao.deleteById(id);
		System.out.println("Delete completed");

		sc.close();
	}
}