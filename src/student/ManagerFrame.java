package student;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//管理员界面
class ManagerFrame extends JFrame {
	JTabbedPane tabPane=new JTabbedPane();
	StudentInfo studentInfo=new StudentInfo();
	CourseInfo courseInfo=new CourseInfo();
	GradeInfo gradeInfo=new GradeInfo();

	JPanel logout_panel=new Logout(this);

	ManagerFrame() {// 构造方法
		super("学生信息管理系统");
		this.setBounds(200, 200, 500, 345);
		add("Center", tabPane);
		tabPane.add("学生信息管理", studentInfo);
		tabPane.add("课程信息管理", courseInfo);
		tabPane.add("成绩信息管理", gradeInfo);
		tabPane.add("退出", logout_panel);

		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}

