package org.projects.Controller;

import org.projects.View.LoginView;
import org.projects.View.MainView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginController implements ActionListener, MouseInputListener {
    private LoginView lgView;
    public LoginController(LoginView lgView) {
        this.lgView = lgView;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == lgView.getLoginButton()) {
                //TODO: Sau này sẽ quản lí thêm việc check đăng nhập -> loại tài khoản -> MainView của tài khoản đó dự trên phân quyền
                int chosse = JOptionPane.showConfirmDialog(null,"ĐĂNG NHẬP THÀNH CÔNG","THÔNG BÁO",JOptionPane.YES_OPTION);
                if(chosse == JOptionPane.YES_OPTION) {
                    lgView.dispose();
                    new MainView();
                }
            }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getSource().equals(lgView.getIconCancelLabel())) {
            lgView.dispose();
            System.exit(0);
        } else if(mouseEvent.getSource().equals(lgView.getIconMinusLabel())) {
            lgView.setExtendedState(Frame.ICONIFIED);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
