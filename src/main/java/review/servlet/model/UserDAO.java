package review.servlet.model;

import java.util.*;
import java.sql.*;

import review.servlet.common.JDBCutil;

public class UserDAO {
	
	public void delUser(String userId) {
		Connection con = null;
		PreparedStatement pt = null;
		String SQL = "DELETE FROM users WHERE id = ?;";
		
		try {
			con = JDBCutil.getConnection();
			pt = con.prepareStatement(SQL);
			pt.setString(1, userId);
			pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.close(pt, con);
		}
	}
	
	public ArrayList<UserDTO> allUser() {
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		UserDTO dto = null;
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM users";
		try {
			con = JDBCutil.getConnection();
			pt = con.prepareStatement(SQL);
			rs = pt.executeQuery();
			while (rs.next()) {
				dto = new UserDTO();
				dto.setNickname(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
				dto.setRole(rs.getString("role"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.close(rs, pt, con);
		}
		
		return list;
	}
	
	public void editUser(UserDTO dto) {
		Connection con = null;
		PreparedStatement pt = null;
		String SQL = "UPDATE users SET name = ?, password = ?, role = ? WHERE id = ?;";
		
		try {
			con = JDBCutil.getConnection();
			pt = con.prepareStatement(SQL);
			pt.setString(1, dto.getNickname());
			pt.setString(2, dto.getPassword());
			pt.setString(3, dto.getRole());
			pt.setString(4, dto.getId());
			pt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.close(pt, con);
		}
		return;
	}
	
	public UserDTO selectUser(String userId) {
		UserDTO resultDTO = null;
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM users WHERE id = ?;";
		
		try {
			con = JDBCutil.getConnection();
			pt = con.prepareStatement(SQL);
			pt.setString(1, userId);
			rs = pt.executeQuery();
			
			if (rs.next()) {
				resultDTO = new UserDTO();
				resultDTO.setId(rs.getString("id"));
				resultDTO.setNickname(rs.getString("name"));
				resultDTO.setPassword(rs.getString("password"));
				resultDTO.setRole(rs.getString("role"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.close(rs, pt, con);
		}
		return resultDTO;
	}
	
	public UserDTO login(UserDTO dto) {
		Connection con = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		UserDTO resultDTO = null;
		String SQL = "SELECT * FROM users WHERE id = ? AND password = ?;";
		
		try {
			con = JDBCutil.getConnection();
			pt = con.prepareStatement(SQL);
			pt.setString(1, dto.getId());
			pt.setString(2, dto.getPassword());
			rs = pt.executeQuery();
			if (rs.next()) {
				resultDTO = new UserDTO();
				resultDTO.setId(rs.getString("id"));
				resultDTO.setPassword(rs.getString("password"));
				resultDTO.setNickname(rs.getString("name"));
				resultDTO.setRole(rs.getString("role"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.close(rs, pt, con);
		}
		return resultDTO;
	}
	
	public UserDTO signup(UserDTO dto) {
		Connection con = null;
		PreparedStatement pt = null;
		String SQL = "INSERT INTO users (id, password, name, role) VALUES ( ?, ?, ?, ? );";
		
		try {
			con = JDBCutil.getConnection();
			pt = con.prepareStatement(SQL);
			
			pt.setString(1, dto.getId());
			pt.setString(2, dto.getPassword());
			pt.setString(3, dto.getNickname());
			pt.setString(4, dto.getRole());
			
			int result = pt.executeUpdate();
			if (result > 0) {
				JDBCutil.close(pt, con);
				return dto;		
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.close(pt, con);
		}
		return null;
	}
}
