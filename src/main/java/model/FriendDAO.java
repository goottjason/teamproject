package model;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import vodto.Friend;
import vodto.FriendDTO;

public interface FriendDAO {
	
	// 친구 목록 가져오기
	List<Friend> selectFriends() throws NamingException, SQLException;
	
	// 전화번호 중복체크 (중복 : true, 유니크 : false)
	boolean selectMobile(String userInputMobile) throws NamingException, SQLException;
	
	// 친구 저장
	int insertFriend(FriendDTO friendDTO) throws NamingException, SQLException;
	
	// 친구 이름으로 검색
	List<Friend> selectFriendByName(String searchName) throws NamingException, SQLException;
}
