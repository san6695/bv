package controller;

import entity.Student;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import entity.User;
import java.io.File;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	SessionFactory factory;
        JavaMailSender mailer;
	
	@RequestMapping("list")
	public String list(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM User";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		model.addAttribute("users", list);
		return "user/list";
	}
        	@RequestMapping("index")
	public String index(ModelMap model) {
            
            model.addAttribute("student", new Student());
		model.addAttribute("students", getTOPStudents());
                model.addAttribute("students2", getStudents2());
                model.addAttribute("students2", getTOPStudents2());
                 model.addAttribute("students1", getStudents1());
                model.addAttribute("students1", getTOPStudents1());
                 model.addAttribute("students3", getStudents3());
                model.addAttribute("students3", getTOPStudents3());
                 model.addAttribute("students4", getStudents4());
                model.addAttribute("students4", getTOPStudents4());
                 model.addAttribute("students5", getStudents5());
                model.addAttribute("students5", getTOPStudents5());
		return "user/index";
	}
	@RequestMapping("detail/{id}")
	public String detail(ModelMap model, @PathVariable("id") String id) {
		Session session = factory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		model.addAttribute("user", user);
		return "user/detail";
	}
	@RequestMapping("form/{id}")
	public String form(ModelMap model, @PathVariable("id") String id) {
		Session session = factory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		model.addAttribute("user", user);
		return "user/form";
	}
        
	@RequestMapping("delete/{id}")
	public String delete(ModelMap model, @PathVariable("id") String id) {
		Session session = factory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		session.delete(user);
		return "redirect:/user/list.htm";
	}

@RequestMapping("register")
public String register(ModelMap model) {
	model.addAttribute("user", new User());
	return "user/register";
}
@RequestMapping("bl")
public String baocao(ModelMap model) {
	
	return "user/baocao";
}


@RequestMapping(value="register", method=RequestMethod.POST)
public String register(ModelMap model, @ModelAttribute("user") User user) {
	Session session = factory.openSession();
	Transaction t = session.beginTransaction();
	try {
		session.save(user);
		t.commit();
		model.addAttribute("message", "Đăng ký thành công !");
	} 
	catch (Exception e) {
		t.rollback();
		model.addAttribute("message", "Không thể đăng ký !");
	}
	finally {
		session.close();
	}
	return "user/register";
}

	@RequestMapping("form")
	public String index() {
		return "user/form";
	}
	@RequestMapping("form2")
	public String index2() {
		return "user/form2";
	}
	@RequestMapping("send")
	public String send(ModelMap model, 
			@RequestParam("from") String from,
			@RequestParam("to") String to,
			@RequestParam("subject") String subject,
			@RequestParam("body") String body) {
		try{
			// Tạo mail
			MimeMessage mail =mailer.createMimeMessage();
			// Sử dụng lớp trợ giúp
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);

			// Gửi mail
			mailer.send(mail);
			
			model.addAttribute("message", "Gửi email thành công !");
		}
		catch(Exception ex){
			model.addAttribute("message", "Gửi email thất bại !");
		}
		return "user/form";
	}
	
	//==========ATTACH FILE==========//
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("send2")
	public String send2(ModelMap model, 
			@RequestParam("from") String from,
			@RequestParam("to") String to,
			@RequestParam("subject") String subject,
			@RequestParam("body") String body,
			@RequestParam("attach") MultipartFile attach) {
		try{
			// Tạo mail
			MimeMessage mail =mailer.createMimeMessage();
			// Sử dụng lớp trợ giúp
			//MimeMessageHelper helper = new MimeMessageHelper(mail);
                        MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);

			if(!attach.isEmpty()){
				String path = context.getRealPath("/images/"+attach.getOriginalFilename());
                                attach.transferTo(new File(path));
				helper.addAttachment(attach.getOriginalFilename(), new File(path));                       
			}
			// Gửi mail
			mailer.send(mail);			
			model.addAttribute("message", "Gửi email thành công !");
		}
		catch(Exception ex){
			model.addAttribute("message", "Gửi email thất bại !");
		}
		return "user/form2";
	}




	@RequestMapping("login")
	public String login() {
		return "user/login";
	}
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(ModelMap model, 
			@RequestParam("id") String id,
			@RequestParam("password") String password,
			HttpSession httpSession) {
		Session session = factory.getCurrentSession();
                model.addAttribute("student", new Student());
		model.addAttribute("students", getTOPStudents());
		try {
			User user = (User) session.get(User.class, id);
			if(!user.getPassword().equals(password)){
				model.addAttribute("message", "Sai tên mật khẩu !");
			}
			else{
				httpSession.setAttribute("user", user);
				return "user/index";
			}
		} 
		catch (Exception e) {
			model.addAttribute("message", "Sai tên đăng nhập !");
		}
		
		return "user/login";
	}

	@RequestMapping("logoff")
	public String logoff(HttpSession httpSession) {
		httpSession.removeAttribute("user");
		return "redirect:/user/login.htm";
	}
	
	@RequestMapping("change")
	public String change() {
		return "user/change";
	}
	@RequestMapping(value="change", method=RequestMethod.POST)
	public String change(ModelMap model, 
			@RequestParam("id") String id, 
			@RequestParam("password") String password, 
			@RequestParam("newpass1") String newpass1, 
			@RequestParam("newpass2") String newpass2) {
		if(!newpass1.equals(newpass2)){
			model.addAttribute("message", "Xác nhận mật khẩu mới không đúng !");
		}
		else{
			Session session = factory.getCurrentSession();
			try {
				User user = (User) session.get(User.class, id);
				if(!user.getPassword().equals(password)){
					model.addAttribute("message", "Sai mật khẩu !");
				}
				else{
					model.addAttribute("message", "Mật khẩu đã được đỏi !");
					user.setPassword(newpass2);
				}
			} 
			catch (Exception e) {
				model.addAttribute("message", "Sai tên đăng nhập !");
			}
		}
		return "user/change";
	}
         @SuppressWarnings("unchecked")
	public List<Student> getTOPStudents() {
		Session session = factory.getCurrentSession();
                Query query = session.createQuery("from Student as o order by o.mark desc");
                query.setFirstResult(0);
                query.setMaxResults(5); 
		List<Student> list = query.list();
		return list;
	}
        
       
       
     
         @SuppressWarnings("unchecked")
	public List<Student> getStudents2() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Student where major='IT' ";
		Query query = session.createQuery(hql);
		List<Student> list = query.list();
		return list;
	}
         @SuppressWarnings("unchecked")
	public List<Student> getTOPStudents2() {
		Session session = factory.getCurrentSession();
                Query query = session.createQuery("from Student where major='IT' order by mark desc");
                query.setFirstResult(0);
                query.setMaxResults(1); 
		List<Student> list = query.list();
		return list;
	}
        
         @SuppressWarnings("unchecked")
	public List<Student> getStudents1() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Student where major='KT' ";
		Query query = session.createQuery(hql);
		List<Student> list = query.list();
		return list;
	}

    @SuppressWarnings("unchecked")
	public List<Student> getTOPStudents1() {
		Session session = factory.getCurrentSession();
                Query query = session.createQuery("from Student where major='KT' order by mark desc");
                query.setFirstResult(0);
                query.setMaxResults(1); 
		List<Student> list = query.list();
		return list;
	}
        
          @SuppressWarnings("unchecked")
	public List<Student> getStudents3() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Student where major='TT' ";
		Query query = session.createQuery(hql);
		List<Student> list = query.list();
		return list;
	}

    @SuppressWarnings("unchecked")
	public List<Student> getTOPStudents3() {
		Session session = factory.getCurrentSession();
                Query query = session.createQuery("from Student where major='TT' order by mark desc");
                query.setFirstResult(0);
                query.setMaxResults(1); 
		List<Student> list = query.list();
		return list;
	}
        
        @SuppressWarnings("unchecked")
	public List<Student> getStudents4() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Student where major='KD' ";
		Query query = session.createQuery(hql);
		List<Student> list = query.list();
		return list;
	}
  @SuppressWarnings("unchecked")
	public List<User> getTOPuser() {
		Session session = factory.getCurrentSession();
		String hql = "FROM User ";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		return list;
	}

    @SuppressWarnings("unchecked")
	public List<Student> getTOPStudents4() {
		Session session = factory.getCurrentSession();
                Query query = session.createQuery("from Student where major='KD' order by mark desc");
                query.setFirstResult(0);
                query.setMaxResults(1); 
		List<Student> list = query.list();
		return list;
	}
        
        @SuppressWarnings("unchecked")
	public List<Student> getStudents5() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Student where major='NS' ";
		Query query = session.createQuery(hql);
		List<Student> list = query.list();
		return list;
	}

    @SuppressWarnings("unchecked")
	public List<Student> getTOPStudents5() {
		Session session = factory.getCurrentSession();
                Query query = session.createQuery("from Student as o order by o.mark desc");
                query.setFirstResult(0);
                query.setMaxResults(1); 
		List<Student> list = query.list();
		return list;
	}
}
