package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import vodto.Friend;
import vodto.FriendDTO;

public class FriendDAOImpl implements FriendDAO {
	
	private static FriendDAOImpl instance = null;
	private FriendDAOImpl() {	}
	
	public static FriendDAOImpl getInstance() {
		if (instance == null) {
			instance = new FriendDAOImpl();
		}
		
		return instance;
	}
	
	
	@Override
	public List<Friend> selectFriends() throws NamingException, SQLException {
		
		List<Friend> result = new ArrayList<Friend>();
		
		Connection con = DBConn.getConnection();
		
		if (con != null) {
			
			// 1. 쿼리문
			
			String query = "select * from friends";
			
			// 2. Statement 준비
			PreparedStatement pstmt = con.prepareStatement(query);
			
			// 3. 쿼리 실행
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 친구 객체 생성하고 list에 추가
				result.add(new Friend(
						rs.getInt("friendNo"), 
						rs.getString("friendName"), 
						rs.getString("mobile"), 
						rs.getString("addr")));
			}
			
			DBConn.closeDB(rs, pstmt, con);
			
		}
		
		return result;
	}

	@Override
	public boolean selectMobile(String userInputMobile) throws NamingException, SQLException {
		boolean result = false;
		
		Connection con = DBConn.getConnection();
		
		if (con != null) {
			String query = "select mobile from friends where mobile = ?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, userInputMobile);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = true;
			}
			
			DBConn.closeDB(rs, pstmt, con);
			
			
		}
		
		return result;
	}

	@Override
	public int insertFriend(FriendDTO friendDTO) throws NamingException, SQLException {
		
		int result = 0;
		
		Connection con = DBConn.getConnection();
		
		if (con != null) {
			String query = "insert into friends(friendName, mobile, addr)"
					+ "values(?, ?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			
			// 매개변수 세팅
			pstmt.setString(1, friendDTO.getFriendName());
			pstmt.setString(2, friendDTO.getMobile());
			pstmt.setString(3, friendDTO.getAddr());
			
			// 실행
			result = pstmt.executeUpdate();
			
			DBConn.closeDB(pstmt, con);
		}
		
		return result;
	}

	@Override
	public List<Friend> selectFriendByName(String searchName) throws NamingException, SQLException {

		List<Friend> result = new ArrayList<Friend>();
		
		Connection con = DBConn.getConnection();
		
		if(con != null) {
			
			String query = "select * from friends where friendName like ?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + searchName + "%");
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result.add(new Friend(
						rs.getInt("friendNo"), 
						rs.getString("friendName"), 
						rs.getString("mobile"), 
						rs.getString("addr")));
			}
			DBConn.closeDB(rs, pstmt, con);
			
		}
		
		return result;
	}

}
