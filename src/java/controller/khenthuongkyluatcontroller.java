package controller;

import entity.Student;
import java.util.List;

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
import entity.Thuong;

@Transactional
@Controller
@RequestMapping("/khenthuongkyluat")
public class khenthuongkyluatcontroller {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping()
	public String index(ModelMap model) {
		model.addAttribute("khenthuong", new Thuong());
		model.addAttribute("khenthuongs", getthuongs());
		return "khenthuongkyluat";
	}
@RequestMapping("showdanhgia")
	public String index2(ModelMap model) {
		model.addAttribute("khenthuong", new Thuong());
		model.addAttribute("khenthuongs", getthuongs1());
		return "showdanhgia";
	}
	@RequestMapping(params="btnInsert")
	public String insert(ModelMap model, @ModelAttribute("khenthuong") Thuong khenthuong) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(khenthuong);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công !");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại !");
		}
		finally {
			session.close();
		}
		model.addAttribute("khenthuongs", getthuongs());
		return "khenthuongkyluat";
	}
	
	@RequestMapping(params="btnUpdate")
	public String update(ModelMap model, @ModelAttribute("khenthuong") Thuong khenthuong) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(khenthuong);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công !");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Cập nhật thất bại !");
		}
		finally {
			session.close();
		}
		model.addAttribute("khenthuongss", getthuongs());
		return "khenthuongkyluat";
	}
	
	@RequestMapping(params="btnDelete")
	public String delete(ModelMap model, Thuong khenthuong) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(khenthuong);
			t.commit();
			model.addAttribute("message", "Xóa thành công !");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại !");
		}
		finally {
			session.close();
		}
		model.addAttribute("khenthuong", new Thuong());
		model.addAttribute("khenthuongs", getthuongs());
		return "khenthuongkyluat";
	}
	
	@RequestMapping("{id}")
	public String edit(ModelMap model, @PathVariable("id") String id) {
		Session session = factory.getCurrentSession();
		Thuong khenthuong = (Thuong) session.get(Thuong.class, id);

		model.addAttribute("khenthuong", khenthuong);
		model.addAttribute("khenthuongs", getthuongs());
		return "khenthuongkyluat";
	}

	@SuppressWarnings("unchecked")
	public List<Thuong> getthuongs() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Thuong";
		Query query = session.createQuery(hql);
		List<Thuong> list = query.list();
		return list;
	}
        @SuppressWarnings("unchecked")
	public List<Thuong> getthuongs1() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Thuong t,Student s WHERE Thuong.idstudent = Student.id";
		Query query = session.createQuery(hql);
		List<Thuong> list = query.list();
		return list;
	}

        
        
}
