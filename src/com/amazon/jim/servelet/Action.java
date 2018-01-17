package com.amazon.jim.servelet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.entity.User;
import com.hwua.service.IUserService;
import com.hwua.service.impl.UserServiceImpl;

/**
 * Servlet implementation class Action
 */
@WebServlet("/doAction")
public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Action() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取对应的请求参数
        String method = request.getParameter("action");
        //根据请求参数去调用对应的方法。
        if ("register".equals(method)) {
            register(request, response);

        } else if ("shoppingCart".equals(method)) {
        	shoppingCart(request, response);
        }
        else if ("checkName".equals(method)) {
        	checkName(request, response);
        }
        response.getWriter().append("Served at: ").append(request.getContextPath());
	}

    private void checkName(HttpServletRequest request, HttpServletResponse response) {
    	//1.获取请求数据
    			String name = request.getParameter("name");
    			//2.调用业务逻辑验证用户名是否存在
    			IUserService ius = new UserServiceImpl();
    			User user = ius.checkName(name);
		
	}

	private void shoppingCart(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}



    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
