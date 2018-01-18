package com.amazon.jim.servelet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazon.jim.entity.User;
import com.amazon.jim.service.IUserService;
import com.amazon.jim.serviceImpl.UserServiceImpl;


/**
 * Servlet implementation class Action
 */
@WebServlet("/doAction")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService ius = new UserServiceImpl();
	boolean regRes=false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    private String feedback="";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取对应的请求参数
        String method = request.getParameter("action");
        //2.根据请求参数去调用对应的方法。

        if ("register".equals(method)) {
            register(request, response);
        }else if ("login".equals(method)) {
        	login(request, response);
        }else if ("shoppingCart".equals(method)) {
        	shoppingCart(request, response);
        }else if ("checkName".equals(method)) {
        	checkName(request, response);
        }else if ("checkEmail".equals(method)) {
        	checkEmail(request, response);
        }else if ("checkCode".equals(method)) {
        	checkCode(request, response);
        }
        
        response.getWriter().write(feedback);

	}


	private void login(HttpServletRequest request, HttpServletResponse response) {
		//1.获取请求携带的数据
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				//将数据封装成对象
				User user = new User(username, password);
				//2.业务处理
				IUserService ius = new UserServiceImpl();
				User result = ius.login(user);
				//3.根据结果进一步操作
				if(result==null){//登录失败
					feedback="0";
				}else{//登录成功
					//将用户对象存放在session域中
					request.getSession().setAttribute("user",result);
					feedback="1";
				}
		
	}


	private void checkName(HttpServletRequest request, HttpServletResponse response) {
    			//1.获取请求数据
    			String name = request.getParameter("userName");
    			//2.调用业务逻辑验证用户名是否存在
    			User user = ius.checkName(name);
    			//3.根据结果进一步操作
    			if(user==null){//用户名不存在
    				feedback="";
    			}else{//用户名存在
    				feedback="1";
    			}
	} 
    private void checkEmail(HttpServletRequest request, HttpServletResponse response) {
    	//1.获取请求数据
    	String email = request.getParameter("userEmail");
		//2.调用业务逻辑验证email是否存在
		User user = ius.checEmail(email);
		//3.根据结果进一步操作
		if(user==null){//email不存在
			feedback="";
		}else{//email存在
			feedback="1";
		}
	}
	private void checkCode(HttpServletRequest request, HttpServletResponse response) {
		//1.通过会话拿到后台验证码
		Object vc = request.getSession().getAttribute("validateCode");
		System.out.println("vc:"+vc);
		//2.获取请求数据
		String inputVc = request.getParameter("veryCode");
		System.out.println("inputVc"+inputVc);
		if(inputVc.equals(vc.toString())){
			feedback="1";
		}else{
			feedback="";
		}
	}
	private void shoppingCart(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void register(HttpServletRequest request, HttpServletResponse response) {
		//1.获取请求携带的数据
				String username = request.getParameter("userName");
				String password = request.getParameter("rePassWord");
				String sex = request.getParameter("sex");
				
				SimpleDateFormat sdf=new SimpleDateFormat();
				String strBirthday = request.getParameter("birthday");
				java.util.Date ubirthday=null;
				
				try {
					ubirthday = sdf.parse(strBirthday);
				} catch (ParseException e) {
					System.out.println("日期转换");
					e.printStackTrace();
				}
				Date birthday=(Date)ubirthday;
				
				String identity = request.getParameter("identity");
				String email = request.getParameter("email");
				String mobil = request.getParameter("mobil");
				String address = request.getParameter("address");
		//2.将数据封装成对象
				User user = new User(username, password, sex, birthday, identity, email, mobil, address);
		
		int result = ius.register(user);
		//3.根据结果进一步操作
		if(result == 1){
			feedback="1";
		}else if(result == -1){
			feedback="ERROR_FOR_NAME";
		}else{
			feedback="ERROR";
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
