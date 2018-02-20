package cc.booooodv.junit_text;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cc.booooodv.dao.StudentDao;
import cc.booooodv.domain.Student;
import cc.booooodv.excaption.StudentNotExistExcaption;
import cc.booooodv.utils.XmlUtiles;

public class StudentDaoText {

	@Test
	public void testAdd() {
		StudentDao dao = new StudentDao();
		Student s = new Student();
		s.setExmid("121");
		s.setGrade(89);
		s.setIdcard("121");
		s.setLocation("北京");
		s.setName("aaa");
		dao.add(s);
	}
	
	@Test
	public void testFind() {
		StudentDao dao = new StudentDao();
		Student s = dao.find("121");
		System.out.println(s.getName());
	}
	
	@Test
	public void testDelete() throws StudentNotExistExcaption {
		StudentDao dao = new StudentDao();
		dao.delete("aaa");
	}
}
