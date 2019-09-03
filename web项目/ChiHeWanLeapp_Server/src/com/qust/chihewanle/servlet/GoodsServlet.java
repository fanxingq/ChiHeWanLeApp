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
import com.qust.bean.Goods;
import com.qust.dao.Dao;
import com.qust.dao.GoodsDao;
import com.qust.dao.impl.GoodsDaoImpl;
import com.qust.entity.ResponseObject;

/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet("/GoodsServlet")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsServlet() {
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
		//接口的实现类实例化
		GoodsDao goodsDao=new GoodsDaoImpl();
		String cityId=request.getParameter("cityId");
		String catId=request.getParameter("catId");
		List<Goods> list=goodsDao.getList(cityId);
//		double count=goodsDao.getCount(cityId, catId);
		//转换成json串
		ResponseObject result=null;
		if (list!=null && list.size()>0) {
			result=new ResponseObject(1,list);
			
		}else {
			result=new ResponseObject(0,"没有数据");
		}
		out.println(JSON.toJSONString(list) );
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
