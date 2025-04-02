package friendcontrol;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.FriendDAO;
import model.FriendDAOImpl;
import vodto.Friend;

@WebServlet("/findFriendByname.do")
public class GetFriendByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetFriendByName() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchName = request.getParameter("searchName");
		
		System.out.println(searchName + "친구 검색 호출됨");
		
		FriendDAO dao = FriendDAOImpl.getInstance();
		
		try {
			List<Friend> friendList = dao.selectFriendByName(searchName);
			
			Gson gson = new Gson();
			
			Type type = new TypeToken<List<Friend>>() {}.getType();
			
			
			String json = gson.toJson(friendList, type);
			System.out.println(json);
			
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(json);
			
			out.flush();
			out.close();
			
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
