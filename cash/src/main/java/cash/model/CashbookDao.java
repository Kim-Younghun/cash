package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	// cashbook hashtag별 조회 list
	public List<Cashbook> selectCashbookListByTag(String memberId, String word, String searchWord, String beginYear, String endYear, String col, String ascDesc, int beginRow, int rowPerPage) {
		
		List<Cashbook> tagList = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo , category, cashbook_date cashbookDate, price, memo, createdate, updatedate "
				+ " FROM cashbook c INNER JOIN hashtag h"
				+ " ON c.cashbook_no = h.cashbook_no";
		
				/*
				+ " WHERE c.member_id = ? AND h.word = ?"
				+ " ORDER BY cashbook_date ASC "
				+ " LIMIT ?, ?";
				*/
		
		// 조건을 추가할 where 절
		String whereClause = "";
		
		// 처음 조건식이므로 id값과 해시태그 단어를 입력받는다.
	  	if(memberId != null && !memberId.equals("")
	  			|| word != null && !word.equals("")) {
	  		whereClause += " WHERE c.member_id = ? AND h.word = ?";
	  	}
	  	
	  	// 검색단어가 있을 경우 WHERE절에 memo like ?을 추가한다.
	 	if(searchWord != null && !searchWord.equals("")) {
	 		if(whereClause.equals("")) {
	 			whereClause += " WHERE ";
	 		} else {
	 		whereClause += " AND "; 
	 		}
			whereClause += " memo like ?";
		}
		
	 	// 선택년도가 있을 경우 WHERE절에 YEAR(cashbook_date) between ? AND ?을 추가한다.
	 	if(beginYear != null && !beginYear.equals("") && endYear != null && !endYear.equals("")) {
	 		if(whereClause.equals("")) {
	 			whereClause += " WHERE ";
	 		} else {
	 	 		whereClause += " AND "; 
	 	 	}
	 		whereClause += "YEAR(cashbook_date) between ? AND ?";
	 	}
	 	
	 	// sql문을 완성
	  	sql += whereClause +  " ORDER BY " + col + " " + ascDesc +  " LIMIT ?,? ";
	 	
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			
			// ?에 들어갈 인덱스변수를 선언한다.
			int parameterIndex = 1;
			
			if(memberId != null && !memberId.equals("")
		  			|| word != null && !word.equals("")) {
				stmt.setString(parameterIndex++, memberId);
				stmt.setString(parameterIndex++, word);
		  	}
			
			if(searchWord != null && !searchWord.equals("")) {
				stmt.setString(parameterIndex++, "%"+searchWord+"%");
			}
			
			if(beginYear != null && !beginYear.equals("") && endYear != null && !endYear.equals("")) {
				stmt.setString(parameterIndex++, beginYear);
				stmt.setString(parameterIndex++, endYear);
			}
			
			stmt.setInt(parameterIndex, beginRow);
			stmt.setInt(parameterIndex + 1, rowPerPage);
			
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
				tagList.add(c);
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
		
		return tagList;
	}
	
	// cashbook hashtag별 조회 list count
	public int selectCashbookListCnt(String memberId, String word, String searchWord, String beginYear, String endYear) {
		
		int totalRow = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM cashbook c INNER JOIN hashtag h"
				+ " ON c.cashbook_no = h.cashbook_no";
		
		// 조건을 추가할 where 절
		String whereClause = "";
		
		// 처음 조건식이므로 id값과 해시태그 단어를 입력받는다.
	  	if(memberId != null && !memberId.equals("")
	  			|| word != null && !word.equals("")) {
	  		whereClause += " WHERE c.member_id = ? AND h.word = ?";
	  	}
	  	
	  	// 검색단어가 있을 경우 WHERE절에 memo like ?을 추가한다.
	 	if(searchWord != null && !searchWord.equals("")) {
	 		if(whereClause.equals("")) {
	 			whereClause += " WHERE ";
	 		} else {
	 		whereClause += " AND "; 
	 		}
			whereClause += " memo like ?";
		}
		
	 	// 선택년도가 있을 경우 WHERE절에 YEAR(cashbook_date) between ? AND ?을 추가한다.
	 	if(beginYear != null && !beginYear.equals("") && endYear != null && !endYear.equals("")) {
	 		if(whereClause.equals("")) {
	 			whereClause += " WHERE ";
	 		} else {
	 	 		whereClause += " AND "; 
	 	 	}
	 		whereClause += "YEAR(cashbook_date) between ? AND ?";
	 	}
	 	
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			
			// ?에 들어갈 인덱스변수를 선언한다.
			int parameterIndex = 1;
			
			if(memberId != null && !memberId.equals("")
		  			|| word != null && !word.equals("")) {
				stmt.setString(parameterIndex++, memberId);
				stmt.setString(parameterIndex++, word);
		  	}
			
			if(searchWord != null && !searchWord.equals("")) {
				stmt.setString(parameterIndex++, "%"+searchWord+"%");
			}
			
			if(beginYear != null && !beginYear.equals("") && endYear != null && !endYear.equals("")) {
				stmt.setString(parameterIndex++, beginYear);
				stmt.setString(parameterIndex++, endYear);
			}
			
			rs = stmt.executeQuery();
			System.out.println(stmt);
			
			if(rs.next()) {
				totalRow = rs.getInt(1);
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
		
		return totalRow;
		
	}
	
	
	// cashbook MONTH별 조회 list
	public List<Cashbook> selectCashbookListByMonth(String memberId, int targetYear, int targetMonth) {
		
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo , category, cashbook_date cashbookDate, price, memo, createdate, updatedate "
				+ " FROM cashbook"
				+ " WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ?"
				+ " ORDER BY cashbook_date ASC";
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
				+ " WHERE member_id = ? AND cashbook_date = ?"
				+ " ORDER BY createdate ASC";
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
