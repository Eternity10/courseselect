package student;

import javax.swing.*;
import java.awt.event.*;


class Logout extends JPanel implements ActionListener,Listener {
    JFrame main_windows;
    public Logout(JFrame Main_Windows) {
        main_windows = Main_Windows;
        JButton logout_button = new JButton("退出");
        logout_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(main_windows, "确定要退出么", "退出提示", JOptionPane.YES_NO_OPTION);

                if (result == 0)
                {
                    main_windows.dispose();
                    new LoginFrame();
                }
            }
        });
        this.add(logout_button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    @Override
    public void refreshUI() {
        JButton logout_button = new JButton("退出");
        logout_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(main_windows, "确定要退出么", "退出提示", JOptionPane.YES_NO_OPTION);
                if (result == 0)
                {
                    main_windows.dispose();
                    new LoginFrame();
                }
            }
        });
        this.add(logout_button);
    }
    @Override
    public void getMessage(String message) {

    }


}


