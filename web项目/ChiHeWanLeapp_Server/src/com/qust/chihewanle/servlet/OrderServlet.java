package com.qust.chihewanle.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.qust.bean.Order;
import com.qust.bean.User;
import com.qust.dao.OrderDao;
import com.qust.dao.UserDao;
import com.qust.dao.impl.OrderDaoImpl;
import com.qust.dao.impl.UserDaoImpl;

/**
 * Servlet implementation class OrderSeverlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
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
		String json=request.getParameter("order");
		String flag=request.getParameter("flag");
		OrderDao orderDao=new OrderDaoImpl();
		int result=0;
		System.out.println(json);
		//获取到城市信息后，进行判断
		if ("orderNow".equals(flag)) {
		
			Order order=JSON.parseObject(json, Order.class);
			result=orderDao.orderNow(order);
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
