package friendcontrol;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model.FriendDAO;
import model.FriendDAOImpl;

@WebServlet("/mobileCheck.do")
public class MobileDuplicatedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MobileDuplicatedServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("전화번호 중복체크 호출됨");
		String userInputMobile = request.getParameter("userInputMobile");
		System.out.println(userInputMobile);
		FriendDAO dao = FriendDAOImpl.getInstance();
		
		// 응답을 json으로 해주기 위해서 json-simple 라이브러리를 사용해서 구현해보자.
		JSONObject json = new JSONObject();
		try {
			if(dao.selectMobile(userInputMobile)) {
				// 중복됨(다시 입력해!)
				json.put("isDuplicated", "true");
				System.out.println("중복됨");
			} else {
				// 유니크함(정상)
				json.put("isDuplicated", "false");
				System.out.println("유니크함");
				
			}
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String jsonStr = json.toJSONString(); // json문자열로 변환
		
		response.setContentType("application/json; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
//		System.out.println(jsonStr);
		
	}

}
