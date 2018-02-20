package cc.booooodv.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cc.booooodv.dao.StudentDao;
import cc.booooodv.domain.Student;
import cc.booooodv.excaption.StudentNotExistExcaption;

public class main {

	public static void main(String[] args) {
		
		
		try {
			System.out.println("添加学生（a）  删除学生（b）  查找学生（c）");
			System.out.print("请输入操作指令：");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String type = br.readLine();
			
			if("a".equals(type)){
				System.out.print("请输入学生名字：");
				String name = br.readLine();
				System.out.print("请输入学生的准考证号：");
				String examid = br.readLine();
				System.out.print("请输入学生的身份证号：");
				String idcard = br.readLine();
				System.out.print("请输入学生的所在地：");
				String location = br.readLine();
				System.out.print("请输入学生的成绩：");
				String grade = br.readLine();
				
				//封装成一个学生对象
				Student s = new Student();
				s.setExmid(examid);
				s.setGrade(Double.parseDouble(grade));
				s.setIdcard(idcard);
				s.setLocation(location);
				s.setName(name);
				
				StudentDao dao = new StudentDao();
				dao.add(s);
				
				System.out.println("添加成功~");
			}else if("b".equals(type)) {//删除学生
				
				System.out.print("请输入要删除学生的姓名：");
				String name = br.readLine();
				StudentDao dao = new StudentDao();
				try {
					dao.delete(name);
					System.out.println("删除成功！");
				} catch (StudentNotExistExcaption e) {
					System.out.println("您要删除的用户不存在！");
				}
				
				
			}else if("c".equals(type)) { //查找学生
				System.out.print("请输入学生准考证号：");
				String examid = br.readLine();
				StudentDao dao = new StudentDao();
				Student s = dao.find(examid);
				if(s != null) {
					System.out.println("姓名：" + s.getName());
					System.out.println("准考证号：" + s.getExmid());
					System.out.println("身份证号：" + s.getIdcard());
					System.out.println("所在地：" + s.getLocation());
					System.out.println("成绩：" + s.getGrade());
				}else {
					System.out.println("查找的学生不存在！！");
				}
				
			}else {
				System.out.println("不支持，这个操作！！");
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("对不起，出错了~~");
		}
	}

}
