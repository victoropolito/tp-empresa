package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.MemberDaoJDBC;
import model.dao.impl.ProjectDaoJDBC;

public class DaoFactory {

	public static MemberDao createMemberDao() {
		return new MemberDaoJDBC(DB.getConnection());
	}

	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}

	public static ProjectDao createProjectDao() {
		return new ProjectDaoJDBC(DB.getConnection());
	}
}