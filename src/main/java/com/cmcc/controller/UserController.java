package com.cmcc.controller;


import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.cmcc.entity.User;

@Controller
public class UserController {

	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request){

		//获取当前用户
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try{
			//为当前用户进行认证，授权
			subject.login(token);
			request.setAttribute("user", user);
			return "index";

		}catch(Exception e){
			e.printStackTrace();
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("errorMsg", "用户名或密码错误！");
			return "redirect:/login.jsp";
		}
	}

	@RequestMapping("/teacher")
	public String index() {
		return "index";
	}
}