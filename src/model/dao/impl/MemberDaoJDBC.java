package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public MemberDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Member obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO member " 
					+ "(Name, Course, Category, DepartmentId) " 
				    + "VALUES " 
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setString(2, obj.getCourse());
			st.setString(3, obj.getCategory());
			st.setInt(4, obj.getDepartment().getId());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}

				DB.closeResultSet(rs);
			}

			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

		finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Member obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE member "
					+ "SET Name = ?, Course = ?, Category = ?, DepartmentId = ? "
					+ "WHERE Id = ?"
					);

			st.setString(1, obj.getName());
			st.setString(2, obj.getCourse());
			st.setString(3, obj.getCategory());
			st.setInt(4, obj.getDepartment().getId());
			st.setInt(5, obj.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM member WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Member findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					//"SELECT * FROM member WHERE Id = ?" teste pro bd
					
					 "SELECT member.*,department.Name as DepName "
					+ "FROM member INNER JOIN department "
					+ "ON member.DepartmentId = department.Id "
					+ "WHERE member.Id = ?"

					
					);

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				Member obj = instantiateMember(rs, dep);
				return obj;
			}
			return null;
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
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

	@Override // reaproveitamento de codigo do findByDepartment
	public List<Member> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT member.*,department.Name as DepName " 
			        + "FROM member INNER JOIN department "
					+ "ON member.DepartmentId = department.Id ORDER BY Name"
					);

			rs = st.executeQuery();

			List<Member> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

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

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Member> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT member.*,department.Name as DepName "
			        + "FROM member INNER JOIN department "
					+ "ON member.DepartmentId = department.Id "
			        + "WHERE DepartmentId = ? "
					+ "ORDER BY Name");

			st.setInt(1, department.getId());

			rs = st.executeQuery();

			List<Member> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

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

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}