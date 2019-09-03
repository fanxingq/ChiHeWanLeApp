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
		ResponseObject result=null;  //��װ��������
//		if ("admin".equals(userName)&&"123".equals(userPwd)&&checkCode.equals(saveCode)) {
//			User user=new User();
//			user.setUserame(userName);
//			user.setUserLoginPwd(userPwd);
//			
//			
//			out.println("�û�����ȷ");
//		}
//		if (StringUtils.isNotBlank(userName)&& StringUtils.isNotBlank(userPwd)) {
			System.out.println(userName+"-----"+userPwd);
			User user=userDao.login(userName,userPwd);
			if (user!=null) {
				result=new ResponseObject(1,user, "��¼�ɹ�");
				request.getSession().setAttribute("user", user);
				response.sendRedirect("/ChiHeWanLeapp_Server/IndexServlet");
				out.println("�û�����ȷ");
			}else if(checkCode.equals(saveCode)){
				pw.write("�û�����������󣬵�¼ʧ��");
				}else {
					pw.write("��֤�����");
				}
//		}else {
//			pw.write("�û��������벻��Ϊ��");
//		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
		
		//���ñ���
		
		
		
	}

}
