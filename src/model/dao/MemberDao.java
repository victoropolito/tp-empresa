package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Member;

public interface MemberDao {

	void insert(Member obj);
	void update(Member obj);
	void deleteById(Integer id);
	Member findById(Integer id);
	List<Member> findAll();
	List<Member> findByDepartment(Department department);
}
