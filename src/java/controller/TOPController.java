package controller;

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

import entity.Major;
import entity.Student;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional
@Controller
@RequestMapping("/top")
public class TOPController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping()
	public String index(ModelMap model) {
		model.addAttribute("student", new Student());
		model.addAttribute("students", getTOPStudents());
		return "top";
	}
	
	@RequestMapping(params="btnInsert")
	public String insert(ModelMap model, @ModelAttribute("student") Student student) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(student);
			t.commit();
			model.addAttribute("message", "Trạng thái : Thêm mới thành công !");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Trạng thái : Thêm mới thất bại !");
		}
		finally {
			session.close();
		}
		model.addAttribute("students", getStudents());
		return "student";
	}
	
	@RequestMapping(params="btnUpdate")
	public String update(ModelMap model, @ModelAttribute("student") Student student) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(student);
			t.commit();
			model.addAttribute("message", "Trạng thái : cập nhật thành công !");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Trạng thái : cập nhật thất bại !");
		}
		finally {
			session.close();
		}
		model.addAttribute("students", getStudents());
		return "student";
	}
	
	
	@RequestMapping("delete")
	public String delete(ModelMap model,@RequestParam("id") int id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
                   Student student = (Student) session.get(Student.class, id);
			session.delete(student);
			t.commit();
			model.addAttribute("message", "Trạng thái : Xóa thành công !");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Trạng thái : Xóa thất bại !");
		}
		finally {
			session.close();
		}
		model.addAttribute("student", new Student());
		model.addAttribute("students", getStudents());
		return "student";
	}

	
	
	@RequestMapping("{id}")
	public String edit(ModelMap model, @PathVariable("id") Integer id) {
		Session session = factory.getCurrentSession();
		Student student = (Student) session.get(Student.class, id);

		model.addAttribute("student", student);
		model.addAttribute("students", getStudents());
		return "student";
	}
	
	@SuppressWarnings("unchecked")
	public List<Student> getStudents() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Student";
		Query query = session.createQuery(hql);
		List<Student> list = query.list();
		return list;
	}

    @ModelAttribute("majors")
    @SuppressWarnings("unchecked")
    public List<Major> getMajors() {
        Session session = factory.getCurrentSession();
        String hql = "FROM Major";
        Query query = session.createQuery(hql);
        List<Major> list = query.list();
        return list;
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
	
}
