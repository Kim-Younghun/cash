package cash.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class CounterDao {
	// 오늘날짜 첫번쨰 접속 -> insert
	public void insertCounter(Connection conn) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO counter VALUES(CURDATE(), 1)";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			/*
			// 예외를 던짐
			Exception mye = new Exception();
			throw mye;
			*/
			throw new Exception(); // 강제로 예외를 발생
			
		} finally {
			try {
				stmt.close();	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	// 오늘날짜 첫번째 이후 접속 -> update
	public void updateCounter(Connection conn) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE counter SET counter_num = counter_num+1"
					+ " WHERE counter_date = CURDATE()";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(); // 강제로 예외를 발생
			
		} finally {
			try {
				stmt.close();	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	// 오늘날짜 카운터
	public int selectCounterCurdate(Connection conn) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int counter = 0;
		try {
			String sql = "SELECT counter_num counterNum FROM counter"
					+ " WHERE counter_date = CURDATE()";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				counter = rs.getInt("counterNum");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(); // 강제로 예외를 발생
			
		} finally {
			try {
				rs.close();
				stmt.close();	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return counter;
	}
	
	
	// 누적 카운터
	public int selectCounterAll(Connection conn) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalCounter = 0;
		try {
			String sql = "SELECT SUM(counter_num) totalCount FROM counter";
				
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				totalCounter = rs.getInt("totalCount");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(); // 강제로 예외를 발생
			
		} finally {
			try {
				rs.close();
				stmt.close();	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return totalCounter;
	}
}
