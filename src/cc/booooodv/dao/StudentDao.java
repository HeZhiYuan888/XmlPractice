package cc.booooodv.dao;

import java.rmi.StubNotFoundException;

import javax.management.RuntimeErrorException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cc.booooodv.domain.Student;
import cc.booooodv.excaption.StudentNotExistExcaption;
import cc.booooodv.utils.XmlUtiles;

public class StudentDao {

	//添加一个学生
	public void add(Student student) {
		try {
			//先获得文件document实例
			Document document = XmlUtiles.getDocument();
			
			//创建出封装学生的标签
			Element student_tag = document.createElement("student");
			student_tag.setAttribute("idcard", student.getIdcard());
			student_tag.setAttribute("examid", student.getExmid());
			
			//创建用来封装学生信息的标签
			Element name = document.createElement("name");
			Element location = document.createElement("location");
			Element grade = document.createElement("grade");
			name.setTextContent(student.getName());
			location.setTextContent(student.getLocation());
			grade.setTextContent(student.getGrade()+"");
			
			//把信息标签和学生标签建立关系
			student_tag.appendChild(name);
			student_tag.appendChild(location);
			student_tag.appendChild(grade);
			
			//把学生标签挂到文档上
			document.getElementsByTagName("exam").item(0).appendChild(student_tag);
			
			//更新内存
			XmlUtiles.write2Xml(document);
			
		} catch (Exception e) {
			//把编码时异常转为运行时异常抛给上一层
			throw new RuntimeException(e);
		}
	}
	
	//删除一个学生
	public void delete(String name) throws StudentNotExistExcaption {
		try {
			Document document = XmlUtiles.getDocument();
			NodeList list = document.getElementsByTagName("name");
			for(int i = 0;i < list.getLength();i++) {
				if(list.item(i).getTextContent().equals(name)) {
					list.item(i).getParentNode().getParentNode().removeChild(list.item(i).getParentNode());
					XmlUtiles.write2Xml(document);
					return;
				}
			}
			throw new StudentNotExistExcaption(name + "不存在！！");
		}
		catch(StudentNotExistExcaption s) {
			throw s;
		}
		catch (Exception e) {
			//把编码时异常转为运行时异常抛给上一层
			throw new RuntimeException(e);
		}
	}
	
	//找一个学生
	public Student find(String examid) {
		
		try {
			Document document = XmlUtiles.getDocument();
			NodeList list = document.getElementsByTagName("student");
			for(int i = 0;i < list.getLength();i++) {
				Element student_tag = (Element) list.item(i);
				if(student_tag.getAttribute("examid").equals(examid)) {
					Student s = new Student();
					s.setExmid(examid);
					s.setIdcard(student_tag.getAttribute("idcard"));
					s.setName(student_tag.getElementsByTagName("name").item(0).getTextContent());
					s.setLocation(student_tag.getElementsByTagName("location").item(0).getTextContent());
					s.setGrade(Double.parseDouble(student_tag.getElementsByTagName("grade").item(0).getTextContent()));
					return s;
				}
			}
			return null;
		} catch (Exception e) {
			//把编码时异常转为运行时异常抛给上一层
			throw new RuntimeException(e);
		}
		
	}
}
