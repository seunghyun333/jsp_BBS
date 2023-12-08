package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BbsDAO {
	
	private Connection conn; //Connection 객체는 데이터베이스와의 연결
	private ResultSet rs; //데이터베이스에서 가져온 결과를 저장하고 조회하는 데 사용
	
	public BbsDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS";
			String dbID ="root";
			String dbPassword="";
			  Class.forName("com.mysql.jdbc.Driver");  //mysql에 접속할 수 있도록 매개체 역할을 하는 하나의 라이브러리 = 드라이바 
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword); //conn 안에 접속 정보담음 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDate() {
		//현재 시간을 가져오는 함수
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""; //db error
	}
	
	public int getNext() {
		//게시글 번호 
		String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1; // 첫 번째 게시물인 경우 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //db error
	}
	
	public int write(String bbsTitle, String userID, String bbsContent) {
		String SQL = "INSERT INTO BBS VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //db error
	}
	
}
