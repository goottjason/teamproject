package friendcontrol;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FriendDAO;
import model.FriendDAOImpl;
import vodto.Friend;

@WebServlet("/getFriend.do")
public class GetFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetFriendsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 호출됨");
		
		FriendDAO dao =  FriendDAOImpl.getInstance();
		
		try {
			List<Friend> result = dao.selectFriends();
			
			for(Friend f : result) {
				System.out.println(f);
			}
			request.setAttribute("friendsList", result); // result(친구 목록)를 "friendsList" 이름으로 request 객체에 바인딩
			RequestDispatcher rd = request.getRequestDispatcher("./friend/viewFriends.jsp");
			
			rd.forward(request, response); // 전달
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
