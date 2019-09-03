package webServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qust.bean.User;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if (user==null) {
			response.getWriter().print("Äú»¹Ã»ÓÐµÇÂ¼£¬Çë<a href='/ChiHeWanLeapp_Server/index.jsp'>µÇÂ¼</a>");
		}else {
			response.getWriter().print("ÄúÒÑµÇÂ¼£¬»¶Ó­Äã£¬"+user.getUserame()+"!");
			response.sendRedirect("/ChiHeWanLeapp_Server/index.html");
//			response.getWriter().print("<a href='/ChiHeWanLeapp_Server/index.html'>ÍË³ö</a>");
			Cookie cookie=new Cookie("JSESSIONID", session.getId());
			cookie.setMaxAge(60*30);
			cookie.setPath("/ChiHeWanLeapp_Server");
			response.addCookie(cookie);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
