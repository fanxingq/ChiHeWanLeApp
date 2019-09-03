package webServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.qust.bean.User;
import com.qust.dao.UserDao;
import com.qust.dao.impl.UserDaoImpl;
import com.qust.entity.ResponseObject;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String userName=request.getParameter("username");
		String userPwd=request.getParameter("userpwd");
		String checkCode=request.getParameter("check_code");
		String saveCode=(String) request.getSession().getAttribute("check_code");
		PrintWriter pw=response.getWriter();
		UserDao userDao=new UserDaoImpl();
		ResponseObject result=null;  //封装返回数据
//		if ("admin".equals(userName)&&"123".equals(userPwd)&&checkCode.equals(saveCode)) {
//			User user=new User();
//			user.setUserame(userName);
//			user.setUserLoginPwd(userPwd);
//			
//			
//			out.println("用户名正确");
//		}
//		if (StringUtils.isNotBlank(userName)&& StringUtils.isNotBlank(userPwd)) {
			System.out.println(userName+"-----"+userPwd);
			User user=userDao.login(userName,userPwd);
			if (user!=null) {
				result=new ResponseObject(1,user, "登录成功");
				request.getSession().setAttribute("user", user);
				response.sendRedirect("/ChiHeWanLeapp_Server/IndexServlet");
				out.println("用户名正确");
			}else if(checkCode.equals(saveCode)){
				pw.write("用户名或密码错误，登录失败");
				}else {
					pw.write("验证码错误");
				}
//		}else {
//			pw.write("用户名或密码不能为空");
//		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
		
		//设置编码
		
		
		
	}

}
