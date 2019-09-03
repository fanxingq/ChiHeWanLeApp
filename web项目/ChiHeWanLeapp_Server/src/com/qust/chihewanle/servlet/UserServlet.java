package com.qust.chihewanle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.qust.bean.City;
import com.qust.bean.User;
import com.qust.dao.CityDao;
import com.qust.dao.UserDao;
import com.qust.dao.impl.CityDaoImpl;
import com.qust.dao.impl.UserDaoImpl;
import com.qust.entity.ResponseObject;
import com.qust.entity.userObject;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");   //�����ı���ʽ���ַ�����
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();  
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String flag=request.getParameter("flag");
		UserDao userDao=new UserDaoImpl();
		//��ȡ���������������ݿ��еĳ���
		ResponseObject result=null;  //��װ��������
		//��ȡ��������Ϣ�󣬽����ж�
		if ("login".equals(flag)) {
			if (StringUtils.isNotBlank(username)&& StringUtils.isNotBlank(password)) {
				System.out.println(username+"-----"+password);
				User user=userDao.login(username,password);
				if (user!=null) {
					result=new ResponseObject(1,user, "��¼�ɹ�");
				}else {
					result=new ResponseObject(0, "��¼ʧ�ܣ��û��������벻��ȷ");
				}
			}else {
				result=new ResponseObject(0,"�û��������벻��Ϊ��");
			}
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
		doGet(request, response);
	}

}
