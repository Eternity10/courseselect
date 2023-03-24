package student;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

//学生个人信息查看
public class StudentDetail extends JPanel implements ActionListener,Listener{
		
	JTable table; //用于显示学生详细信息
	JScrollPane scroll; //用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看
	JButton btnAlter = new JButton("信息修改");
	JPanel panel=new JPanel();

	Object[][] columnValues;
	String[] columnNames;
	DefaultTableModel tableModel;
	
	StudentDetail() {// 构造方法
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		btnAlter.addActionListener(this);
		setLayout(new BorderLayout(0, 0));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(btnAlter);
		add(panel, BorderLayout.NORTH);
		columnValues = new Object[1][5];
		columnNames = new String []{ "学号", "姓名", "性别","年龄",  "院系" };
		try {
			con = jdbcUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from s where logn='" + LoginFrame.loginName
					+ "'"); //查询数据库中与登录名一致的学生信息
			rs.next();
			columnValues[0][0] = rs.getString("sno");
			columnValues[0][1] = rs.getString("sname");
			columnValues[0][2] = rs.getString("sex");
			columnValues[0][3] = rs.getInt("age");
			columnValues[0][4] = rs.getString("sdept");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tableModel = new DefaultTableModel(columnValues,columnNames);
		table = new JTable(tableModel);
		scroll = new JScrollPane(table);
		add(scroll);
		jdbcUtil.closeResources(rs,stmt,con);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StudentAdd sadd = new StudentAdd(this);
		sadd.setTitle("修改");
		sadd.tsno.setText(columnValues[0][0].toString());
		sadd.tsname.setText(columnValues[0][1].toString());
		sadd.cbssex.setSelectedItem(columnValues[0][2].toString());
		sadd.tsage.setText(columnValues[0][3].toString());
		sadd.cbsdept.setSelectedItem(columnValues[0][4].toString());
		sadd.tsno.setEnabled(false);
	}

	@Override
	public void refreshUI() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = jdbcUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from s where logn='" + LoginFrame.loginName
					+ "'"); //查询数据库中与登录名一致的学生信息
			rs.next();
			columnValues[0][0] = rs.getString("sno");
			columnValues[0][1] = rs.getString("sname");
			columnValues[0][2] = rs.getString("sex");
			columnValues[0][3] = rs.getInt("age");
			columnValues[0][4] = rs.getString("sdept");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tableModel.setDataVector(columnValues, columnNames);
		tableModel.fireTableDataChanged();
		jdbcUtil.closeResources(rs,stmt,con);
	}

	@Override
	public void getMessage(String message) {
	}
}