package friendcontrol;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FriendDAO;
import model.FriendDAOImpl;
import vodto.FriendDTO;

@WebServlet("/save.do")
public class SaveFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SaveFriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("여기 세이브두");
		
		FriendDTO newFriend = new FriendDTO(
				request.getParameter("friendName"),
				request.getParameter("mobile"),
				request.getParameter("addr"));
		
		System.out.println(newFriend);
		
		FriendDAO dao = FriendDAOImpl.getInstance();
		try {
			int result = dao.insertFriend(newFriend);
			if (result == 1) {
				// 저장 성공
				System.out.println("저장성공?");
				response.sendRedirect(request.getContextPath() + "/getFriend.do?isSaved=true");
			}
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/getFriend.do?isSaved=false");
		}
		
	}

}
