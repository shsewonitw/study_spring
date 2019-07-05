package com.tje.services;

import java.sql.*;

public class MemberDAO implements DAO {
	public boolean select(Connection conn, Member obj) {
		boolean result = false;
		
		String sql = "select * from member where id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getId());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		
		
		return result;
	}
	
	public boolean insert(Connection conn, Member obj) {
		boolean result = false;
		
		String sql = "insert into member values (?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getId());
			pstmt.setString(2, obj.getName());
			pstmt.setInt(3, obj.getAge());

			if( pstmt.executeUpdate() > 0 ) {
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		
		
		return result;
	}
}
