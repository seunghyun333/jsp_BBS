package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	

	private Connection conn; //Connection 객체는 데이터베이스와의 연결
	private PreparedStatement pstmt; //SQL 문을 미리 컴파일하여 실행 속도를 향상
	private ResultSet rs; //이터베이스에서 가져온 결과를 저장하고 조회하는 데 사용
	
	public UserDAO() {
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
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID); // 쿼리의 ? 부분에 userID 바인딩 
			rs = pstmt.executeQuery(); // 쿼리 실행 
			if (rs.next()) {  // 결과 값이 있으면 
				if(rs.getString(1).equals(userPassword)) {
						return 1; // 로그인 성공 
				} else
						return 0; // 비밀번호 불일치 
			}
			return -1; // 아이디가 없음 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;  //DB 오류 
	}

}
