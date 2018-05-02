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
@RequestMapping("/phongban/")
public class phongbanController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("nhansu")
	public String index(ModelMap model) {
		model.addAttribute("student", new Student());
		model.addAttribute("students", getStudents());
                model.addAttribute("students2", getTOPStudents());
		return "phongban/nhansu";
	}
      @RequestMapping("ketoan")
	public String index1(ModelMap model) {
		model.addAttribute("student", new Student());
		model.addAttribute("students", getStudents1());
                model.addAttribute("students2", getTOPStudents1());
		return "phongban/ketoan";
	}
         @RequestMapping("it")
	public String index2(ModelMap model) {
		model.addAttribute("student", new Student());
		model.addAttribute("students", getStudents2());
                model.addAttribute("students2", getTOPStudents2());
		return "phongban/it";
	}
          @RequestMapping("truyenthong")
	public String index3(ModelMap model) {
		model.addAttribute("student", new Student());
		model.addAttribute("students", getStudents3());
                model.addAttribute("students2", getTOPStudents3());
		return "phongban/truyenthong";
	}
	  @RequestMapping("kinhdoanh")
	public String index4(ModelMap model) {
		model.addAttribute("student", new Student());
		model.addAttribute("students", getStudents4());
                model.addAttribute("students2", getTOPStudents4());
		return "phongban/kinhdoanh";
	}
	
	
	
	@RequestMapping("{id}")
	public String edit(ModelMap model, @PathVariable("id") Integer id) {
		Session session = factory.getCurrentSession();
		Student student = (Student) session.get(Student.class, id);

		model.addAttribute("student", student);
		model.addAttribute("students", getStudents());
		return "student_edit";
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
    
    //phong nhan su
	@SuppressWarnings("unchecked")
	public List<Student> getStudents() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Student where major='NS' ";
		Query query = session.createQuery(hql);
		List<Student> list = query.list();
		return list;
	}

    @SuppressWarnings("unchecked")
	public List<Student> getTOPStudents() {
		Session session = factory.getCurrentSession();
                Query query = session.createQuery("from Student as o order by o.mark desc");
                query.setFirstResult(0);
                query.setMaxResults(1); 
		List<Student> list = query.list();
		return list;
	}
        //phong ke toan
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
        //phong it
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
        //phong truyen thong
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
        //phong kinh doanh
        @SuppressWarnings("unchecked")
	public List<Student> getStudents4() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Student where major='KD' ";
		Query query = session.createQuery(hql);
		List<Student> list = query.list();
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
}
