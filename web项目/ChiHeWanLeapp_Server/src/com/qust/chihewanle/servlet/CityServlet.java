package com.qust.chihewanle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import com.alibaba.fastjson.JSON;
import com.qust.bean.City;
import com.qust.dao.CityDao;
import com.qust.dao.impl.CityDaoImpl;
import com.qust.entity.ResponseObject;

/**
 * 获取城市列表
 * Servlet implementation class CityServlet
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityServlet() {
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
		CityDao cityDao=new CityDaoImpl();
		//获取服务器关联的数据库中的城市
		List<City> lCities=cityDao.getCity();
		ResponseObject result=null;  //封装返回数据
		//获取到城市信息后，进行判断
		if (lCities!=null && lCities.size()>0) {
			//正确返回获取数据
			result=new ResponseObject(1,lCities);
		}else {
			result=new ResponseObject(0,"没有城市数据");
		}
		out.println(JSON.toJSONString(lCities));   
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
