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
import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Controller
@RequestMapping("/student_edit")
public class StueditController {
	@Autowired
       SessionFactory factory;
        @Autowired
	ServletContext context;
	
	@RequestMapping()
	public String index(ModelMap model) {
		model.addAttribute("student", new Student());
		model.addAttribute("students", getStudents());
		return "student_edit";
	}
	
	
	@RequestMapping(params="insert",method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("student") Student student,@RequestParam("img") MultipartFile img) throws Exception
        {
            
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
                String photo;
		try {
                if(img.isEmpty() || img.getOriginalFilename().equalsIgnoreCase("nv.png")){
                    photo="img/nv1.png";
                }
                else{
                    if(student.getMark() >10){
                        model.addAttribute("mark1", "Không được nhập quá 10 điểm");
                        return "student_edit";
                    }
                    else{
                String photoPath = context.getRealPath("/img/"+img.getOriginalFilename());
                File file= new File(photoPath+img.getOriginalFilename());
                File renameFile=new File(photoPath);
                img.transferTo(renameFile);
                if(file.exists()){
                    file.delete();
                }
                photo=renameFile.getName();
                }
                student.setPhoto(photo);
                        //Insert Object Data
			session.save(student);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công !");
                        
                }
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại !"+e);
		}
		finally {
			session.close();
		}
		model.addAttribute("students", getStudents());
              
		return "student_edit";
	}
	@RequestMapping(params="btnUpdate")
	public String update(ModelMap model, @ModelAttribute("student") Student student,@RequestParam("img") MultipartFile img,HttpServletRequest request) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
                String imga = request.getParameter("txtimg");
               
		try {
                  student.setPhoto(imga);
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
		return "student_edit";
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
		return "student_edit";
	}
  
	
	@RequestMapping("{id}")
	public String edit(ModelMap model, @PathVariable("id") Integer id) {
		Session session = factory.getCurrentSession();
		Student student = (Student) session.get(Student.class, id);

		model.addAttribute("student", student);
		model.addAttribute("students", getStudents());
		return "student_edit";
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
