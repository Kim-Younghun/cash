package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cash.vo.*;

public class CashbookDao {
	// 반환값 : cashbook_no 키값
	public int insertCashbbok(Cashbook cashbook) {
		int cashbookNo = 0;
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; // 입력후 생성된 키값 반환
		String sql = "INSERT INTO cashbook(member_id, category, cashbook_date, price, memo, createdate, updatedate) "
				+ " VALUES(?,?,?,?,?,NOW(),NOW())";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cashbook.getMemberId() );
			stmt.setString(2, cashbook.getCategory() );
			stmt.setString(3, cashbook.getCashbookDate() );
			stmt.setInt(4, cashbook.getPrice() );
			stmt.setString(5, cashbook.getMemo());
			// 입력 여부 파악
			row = stmt.executeUpdate();
			// 자동 생성된 키(auto-generated key) 값을 가져오기 위해 사용하는 메소드
			rs = stmt.getGeneratedKeys();
			System.out.println(stmt);
			
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return cashbookNo;
	}
	
	// cashbook MONTH별 조회 list
	public List<Cashbook> selectCashbookListByMonth(String memberId, int targetYear, int targetMonth) {
		
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo , category, cashbook_date cashbookDate, price, memo, createdate, updatedate "
				+ " FROM cashbook"
				+ " WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? ORDER BY cashbook_date ASC";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId );
			stmt.setInt(2, targetYear );
			stmt.setInt(3, targetMonth);
			rs = stmt.executeQuery();
			System.out.println(stmt);
			
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				c.setPrice(rs.getInt("price"));
				c.setMemo(rs.getString("memo"));
				c.setCreatedate(rs.getString("createdate"));
				c.setUpdatedate(rs.getString("updatedate"));
				list.add(c);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return list;
	}
	
	// cashbook Day 조회 list
	public ArrayList<HashMap<String, Object>> selectCashbookListByDay(String memberId, int targetYear, String strM, String strD) {
		
		ArrayList<HashMap<String, Object>> dayList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo , member_id memberId, category, cashbook_date cashbookDate, price, memo, createdate, updatedate "
				+ " FROM cashbook"
				+ " WHERE member_id = ? AND cashbook_date = ? ORDER BY createdate ASC";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId );
			stmt.setString(2, targetYear+"-"+strM+"-"+strD );
			rs = stmt.executeQuery();
			System.out.println(stmt);
			
			while(rs.next()) {
				HashMap<String, Object> m = new HashMap<>();
				m.put("cashbookNo", rs.getInt("cashbookNo"));
				m.put("memberId", rs.getString("memberId"));
				m.put("category", rs.getString("category"));
				m.put("cashbookDate", rs.getString("cashbookDate"));
				m.put("price", rs.getInt("price"));
				m.put("memo", rs.getString("memo"));
				m.put("createdate", rs.getString("createdate"));
				m.put("updatedate", rs.getString("updatedate"));
				dayList.add(m);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		System.out.println("Dao dayList : "+dayList);
		
		return dayList;
	}

}
