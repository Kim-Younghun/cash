package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cash.vo.Hashtag;

public class HashtagDao {
	
	public List<Map<String, Object>> selectWordCountByMonth(String memberId, int targetYear, int targetMonth) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		List<Map<String, Object>> list =  new ArrayList<>();
		
		String sql = "SELECT word, COUNT(*) cnt"
				+ " FROM hashtag h INNER JOIN cashbook c "
				+ " ON h.cashbook_no = c.cashbook_no"
				+ " WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ?"
				+ " GROUP BY word "
				+ " ORDER BY COUNT(*) DESC";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			
			
			// 입력 여부 파악
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("word", rs.getString("word"));
				m.put("cnt", rs.getString("cnt"));
				list.add(m);
			}
			
			System.out.println("selectWordCountByMonth: "+stmt);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return list;
	}
	
	public int insertHashtag(Hashtag hashtag) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; // 입력후 생성된 키값 반환
		int row = 0;
		String sql = "INSERT INTO hashtag(cashbook_no, word, createdate, updatedate) "
				+ " VALUES(?,?,NOW(),NOW())";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, hashtag.getCashbookNo());
			stmt.setString(2, hashtag.getWord());
			// 입력 여부 파악
			row = stmt.executeUpdate();
			
			System.out.println(stmt);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return row;
	}
}
