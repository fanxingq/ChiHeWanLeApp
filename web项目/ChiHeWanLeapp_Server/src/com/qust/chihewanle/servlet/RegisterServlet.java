package com.qust.chihewanle.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qust.bean.User;
import com.qust.dao.UserDao;
import com.qust.dao.impl.UserDaoImpl;
import com.qust.entity.ResponseObject;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");   //设置文本形式和字符编码
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();  
//		String username=request.getParameter("username");
//		String password=request.getParameter("password");
//		String payPwd=request.getParameter("payPwd");
//		String userTel=request.getParameter("userTel");
		String json=request.getParameter("user");
		String flag=request.getParameter("flag");
		UserDao userDao=new UserDaoImpl();
		int result=0;
		System.out.println(json);
		//获取到城市信息后，进行判断
		if ("register".equals(flag)) {
		
			User user=JSON.parseObject(json, User.class);
			result=userDao.Register(user);
		}
		out.println(JSON.toJSONString(result));   
//		out.println(lCities.size());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
