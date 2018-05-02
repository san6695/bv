package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class doipassController {
	@RequestMapping("quen1")
	public String showForm() {
		return "user/guinick";
	}
//	@RequestMapping("user/login")
//	public String login(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String pw = request.getParameter("password");
//		if(id.equals("fpt") && pw.equals("poly")){
//                    request.setAttribute("uid", id);
//                    request.setAttribute("pwd", pw);
//                    return "user/info";
//		}
//		request.setAttribute("message", "Sai thông tin đăng nhập !");
//		return "user/login";
//	}
	
	@RequestMapping("user/guinick")
	public String login2(ModelMap model, HttpServletRequest request) {
		String id = request.getParameter("id");
		
		
			model.addAttribute("uid", id);
			
			return "user/guinik2";
		}
		
}
