package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

//忘记密码
class ForgetPassword extends JFrame implements ActionListener, ItemListener {


    JPanel infPane = null;
    JPanel OkPane = null;
    JLabel number = new JLabel("学号：");
    JLabel name = new JLabel("姓名：");
    JLabel newPassword = new JLabel("新密码：");
    JLabel repeatNewPassword = new JLabel("重复新密码：");
    JTextField txtnumber = new JTextField();
    JTextField txtName = new JTextField();
    JPasswordField txtNewPassword = new JPasswordField();
    JPasswordField txtRepeatNewPassword = new JPasswordField();
    JButton certain = new JButton("确定");
    JButton cancel = new JButton("取消");



    int index = 0;
    ForgetPassword() {
        super("忘记密码");
        this.setBounds(250, 220, 300, 345);
        infPane = new JPanel();
        OkPane = new JPanel();
        //add("Center", tabPane);
        infPane.setBounds(55, 80, 200, 110);
        OkPane.setBounds(55, 190, 200, 30);
        infPane.setLayout(new GridLayout(4, 2));
        //信息输入界面
        infPane.add(name);
        infPane.add(txtName);
        infPane.add(number);
        infPane.add(txtnumber);
        infPane.add(newPassword);
        infPane.add(txtNewPassword);
        infPane.add(repeatNewPassword);
        infPane.add(txtRepeatNewPassword);
        //确定按钮
        OkPane.add(certain);
        OkPane.add(cancel);
        getContentPane().setLayout(null);
        getContentPane().add(infPane);
        getContentPane().add(OkPane);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        certain.addActionListener(this);
        cancel.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == certain) {
            //监听到点击的按钮是确认
            if (txtnumber.getText().equals("") | txtName.getText().equals("") |
                    txtNewPassword.getPassword().equals("") | txtRepeatNewPassword.getPassword().equals("")) { //输入信息不完整并提示相关信息
                if (txtName.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入姓名!");
                } else if (txtnumber.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入学号!");
                } else if (txtNewPassword.getPassword().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入新密码!");
                } else if (txtRepeatNewPassword.getPassword().equals("")) {
                    JOptionPane.showMessageDialog(null, "请重复密码!");
                }
            } else if (!txtNewPassword.getPassword().equals(txtRepeatNewPassword.getPassword())) {
                JOptionPane.showMessageDialog(null, "密码不一致!");
            } else {
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                try {
                    con = jdbcUtil.getConnection();
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("select * from s");
                    String userName = null;
                    String userNumber = null;
                    while(rs.next()) {
                        userName = rs.getString("sname").trim();
                        userNumber = rs.getString("sno").trim();
                        if (userName.equals(txtName.getText()) && userNumber.equals(txtnumber.getText())) { //学生姓名与学号相一致
                            Object [][] columnValues = new Object[1][2];
                            try{                                                                     //修改密码
                                stmt.executeUpdate("update s set pswd='"+txtNewPassword.getPassword()+
                                        "' where logn='"+txtnumber.getText()+"'");
                                columnValues[0][1]=txtNewPassword.getPassword();
                                JOptionPane.showMessageDialog(null, "密码修改成功！");
                                jdbcUtil.closeResources(rs,stmt,con);

                                this.dispose();
                                break;
                            }catch (SQLException e2) {
                                e2.printStackTrace();
                            }
                        } else if ((userName.equals(txtName.getText()) && !userNumber.equals(txtnumber.getText())) ||
                                (!userName.equals(txtName.getText()) && userNumber.equals(txtnumber.getText()))) {   //姓名与学号不符
                            JOptionPane.showMessageDialog(null, "姓名与学号不匹配!");
                            break;
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                jdbcUtil.closeResources(rs,stmt,con);
            }


        } else if (source == cancel) {
            //监听到点击的按钮是取消
            this.dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            JComboBox jcb = (JComboBox) e.getSource();
            index = jcb.getSelectedIndex();
        }
    }
}
