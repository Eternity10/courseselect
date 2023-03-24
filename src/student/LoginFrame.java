package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//登陆界面  学生的初始密码为学号
class LoginFrame extends JFrame implements ActionListener, ItemListener {

    JPanel p1 = null, p2 = null, p3 = null;
    //学号输入
    JLabel userNumber = new JLabel("用户名：");
    JTextField txtUser = new JTextField();
    //密码输入
    JLabel password = new JLabel("密码：");
    JPasswordField txtPwd = new JPasswordField(6);
    //选择身份：管理员/学生
    JLabel role = new JLabel("身份：");
    JComboBox cbrole = new JComboBox();
    //登录选项
    JButton btnLogin = new JButton("登录");
    JButton btnReset = new JButton("重置");
    JButton btnCancel = new JButton("取消");
    JButton btnForget = new JButton("忘记密码");
    //上大logo
    JLabel imageLabel;
    Icon image;
    static int OK = 1;
    static int CANCEL = 0;
    static String loginName = "";
    int actionCode = 0;

    int index = 0;

    public LoginFrame() {// 构造方法
        super("登录界面");
        this.setBounds(200, 200, 500, 345);
        p1 = new JPanel();
        p1.setBounds(80, 10, 340, 120);
        p1.setLayout(new BorderLayout(0, 0));
        image = new ImageIcon("src/images/shu.png");
        imageLabel = new JLabel(image);
        p1.add(imageLabel, BorderLayout.EAST);
        p2 = new JPanel();
        p2.setBounds(150, 147, 200, 110);
        p2.setLayout(new GridLayout(4, 2));
        p2.add(userNumber);
        p2.add(txtUser);
        p2.add(password);
        p2.add(txtPwd);
        p2.add(role);
        p2.add(cbrole);
        p3 = new JPanel();
        p3.setBounds(80, 257, 340, 37);
        cbrole.addItem("管理员");
        cbrole.addItem("学生");
        p3.add(btnLogin);
        p3.add(btnReset);
        p3.add(btnCancel);
        p3.add(btnForget);
        getContentPane().setLayout(null);
        getContentPane().add(p1);
        getContentPane().add(p2);
        getContentPane().add(p3);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //添加监听事件
        btnLogin.addActionListener(this);
        cbrole.addItemListener(this);
        btnReset.addActionListener(this);
        btnCancel.addActionListener(this);
        btnForget.addActionListener(this);
    }

    //获取用户所选择身份的下标
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            JComboBox jcb = (JComboBox) e.getSource();
            index = jcb.getSelectedIndex();
        }
    }

    //登录选项按钮的监听
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String un = null;
        String pw = null;
        //监听到点击的按钮是登录按钮
        if (source == btnLogin) {
            // 判断是否输入了用户名和密码
            if (txtUser.getText().equals("") || txtPwd.getPassword().equals("")) {
                JOptionPane.showMessageDialog(null, "登录名和密码不能为空！");
            } else {
                if (index == 0) {//管理员登录
                    if (txtUser.getText().equals("admin")) {
                        if (String.valueOf(txtPwd.getPassword()).equals("admin")) {
                            new ManagerFrame();// 进入管理员界面
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "密码错误！");
                            txtPwd.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "登录名错误！");
                        txtUser.setText("");
                        txtPwd.setText("");
                    }
                } else {//学生登录
                    Connection con = null;
                    Statement stmt = null;
                    ResultSet rs = null;
                    try {
                        con = jdbcUtil.getConnection();
                        stmt = con.createStatement();
                        rs = stmt.executeQuery("select * from s");
                        boolean user = false;
                        while (rs.next()) {
                            un = rs.getString("logn").trim();
                            pw = rs.getString("pswd").trim();
                            if (txtUser.getText().equals(un)) {//登录名正确
                                user = true;
                                if (String.valueOf(txtPwd.getPassword()).equals(pw)) {//密码正确
                                    actionCode = OK;
                                    this.setVisible(false);
                                    loginName = un;
                                    System.out.println("上大");
                                    new StudentFrame();// 进入学生界面
                                    break;
                                } else {//密码错误
                                    JOptionPane.showMessageDialog(null, "密码错误！");
                                    txtPwd.setText("");
                                }
                            }
                        }
                        if (!user) {
                            JOptionPane.showMessageDialog(null, "登录名错误！");
                            txtUser.setText("");
                            txtPwd.setText("");
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    jdbcUtil.closeResources(rs, stmt, con);
                }
            }
        } else if (source == btnReset) {
            //监听到点击的按钮是重置按钮
            System.out.println("重置");
            txtUser.setText("");
            txtPwd.setText("");
        } else if (source == btnCancel) {
            //监听到点击的按钮是取消按钮
            System.exit(0);
        } else if (source == btnForget) {
            //监听到点击的按钮是忘记密码
            new ForgetPassword();
        }
    }
}

