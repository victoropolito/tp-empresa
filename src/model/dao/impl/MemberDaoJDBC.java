package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.MemberDao;
import model.entities.Department;
import model.entities.Member;

public class MemberDaoJDBC implements MemberDao {

	private Connection conn;
	
	public MemberDaoJDBC(Connection connection) {
		// TODO Auto-generated constructor stub
	}

	public void MemberDaoJDCB(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Member obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Member obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Member findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"MEMBER member.*, member.Name as MemName "
					+ "FROM member INNER JOIN member "
					+ "ON member.DepartmentId = derpartmnet.Id"
					+ "WHERE member.Id = ?");
					
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				Member obj = instantiateMember(rs, dep);		
				return obj;
			}
			return null;
		}
		
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Member instantiateMember(ResultSet rs, Department dep) throws SQLException {
		Member obj = new Member();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setCourse(rs.getString("Course"));
		obj.setCategory(rs.getString("Category"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT member.*, department.Name as DepName "
					+ "FROM member INNER JOIN department "
					+ "ON member.DepartmentId = derpartmnet.Id"
					+ "WHERE DepartmentId = ?"
					+ "ORDER BY Name");
					
			st.setInt(1, department.getId());
			
			rs = st.executeQuery();
			
			List<Member> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Member obj = instantiateMember(rs, dep);		
				list.add(obj);
			}
			return list;
		}
		
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
