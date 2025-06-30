package org.projects.View;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.Helper.RoundedRadiusHelper;
import org.projects.Helper.StyleComponents;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
        private JPanel topPanel;
        private JLabel iconMinusLabel;
        private JLabel iconCancelLabel;
        private JLabel iconZoomLabel;

        private JPanel leftPanel;
        private JLabel iconBookStoreLabel;

        private JPanel rightPanel;
        private JPanel formPanel;
        private JLabel titleLabel;
        private JLabel usernameLabel;
        private JTextField usernameField;
        private JLabel passwordLabel;
        private JPasswordField passwordField;
        private JLabel registerNowLabel; // dang ki ngay
        private JButton  loginButton;
        public LoginView() {
            this.setTitle("Login");
            this.setSize(1000,600);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            RoundedRadiusHelper.radiusForFrame(this,25);
            this.setLayout(new BorderLayout());
            this.init();
            this.setVisible(true);
        }
        private void init() {
            topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
            topPanel.setPreferredSize(new Dimension(1000,50));
            FlatSVGIcon iconMinus = new FlatSVGIcon("icon/minus.svg",30,30);
            FlatSVGIcon iconZoom = new FlatSVGIcon("icon/zoom.svg",30,30);
            FlatSVGIcon iconCancel = new FlatSVGIcon("icon/cancel.svg",20,20);
            iconMinusLabel = new JLabel(iconMinus,JLabel.CENTER);
            iconZoomLabel = new JLabel(iconZoom,JLabel.CENTER);
            iconCancelLabel = new JLabel(iconCancel,JLabel.CENTER);
            topPanel.add(iconMinusLabel);
            topPanel.add(iconZoomLabel);
            topPanel.add(iconCancelLabel);

            leftPanel = new JPanel(new BorderLayout());
            leftPanel.setPreferredSize(new Dimension(400,500));
            FlatSVGIcon iconBookStore = new FlatSVGIcon("icon/store.svg",200,300);
            iconBookStoreLabel = new JLabel(iconBookStore);
            leftPanel.add(iconBookStoreLabel,BorderLayout.CENTER);

            rightPanel = new JPanel(new BorderLayout());

            titleLabel = StyleComponents.formLabel("Đăng nhập", new Font("Jetbrains Mono", Font.PLAIN, 40), 50, 60);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(10,0,30,0));
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            rightPanel.add(titleLabel, BorderLayout.NORTH);

            formPanel = new JPanel();
            formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
            formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            formPanel.setAlignmentY(Component.CENTER_ALIGNMENT);


            usernameLabel = StyleComponents.formLabel("Tên đăng nhập", new Font("Jetbrains Mono", Font.PLAIN, 25), 40, 10);
            usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            usernameField = new JTextField(10);
            usernameField.setPreferredSize(new Dimension(500, 50));
            usernameField.setMaximumSize(new Dimension(550, 50));
            RoundedRadiusHelper.radiusForTextField(usernameField, 20);
            usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);

            passwordLabel = StyleComponents.formLabel("Mật khẩu", new Font("Jetbrains Mono", Font.PLAIN, 25), 40, 10);
            passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            passwordField = new JPasswordField(10);
            passwordField.setPreferredSize(new Dimension(500, 50));
            passwordField.setMaximumSize(new Dimension(550, 50));
            RoundedRadiusHelper.radiusForTextField((JTextField) passwordField, 20);
            passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);

            formPanel.add(Box.createVerticalStrut(20));
            formPanel.add(usernameLabel);
            formPanel.add(Box.createVerticalStrut(5));
            formPanel.add(usernameField);
            formPanel.add(Box.createVerticalStrut(20));
            formPanel.add(passwordLabel);
            formPanel.add(Box.createVerticalStrut(5));
            formPanel.add(passwordField);

            JPanel bottomForm = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            bottomForm.setOpaque(false);
            bottomForm.setMaximumSize(new Dimension(550, 50));
            bottomForm.setAlignmentX(Component.LEFT_ALIGNMENT);

            registerNowLabel = StyleComponents.formLabel("Chưa có tài khoản? Đăng ký ngay", new Font("JetBrains Mono", Font.ITALIC, 12), 230, 20);
            registerNowLabel.setForeground(Color.BLACK);
            registerNowLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            registerNowLabel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
            bottomForm.add(registerNowLabel);

            formPanel.add(Box.createVerticalStrut(10));
            formPanel.add(bottomForm);

            rightPanel.add(formPanel,BorderLayout.CENTER);

            loginButton = StyleComponents.styleButton("Đăng nhập",new Font("Jetbrains Mono",Font.PLAIN,20),Color.BLACK,Color.WHITE,250,50);
            loginButton.setMaximumSize(new Dimension(550,50));
            RoundedRadiusHelper.radiusForButton(loginButton,20);
            formPanel.add(Box.createVerticalStrut(10));
            formPanel.add(loginButton);

            this.add(rightPanel,BorderLayout.CENTER);
            this.add(topPanel,BorderLayout.NORTH);
            this.add(leftPanel,BorderLayout.WEST);
        }

    public JLabel getIconMinusLabel() {
        return iconMinusLabel;
    }

    public void setIconMinusLabel(JLabel iconMinusLabel) {
        this.iconMinusLabel = iconMinusLabel;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public JLabel getIconCancelLabel() {
        return iconCancelLabel;
    }

    public void setIconCancelLabel(JLabel iconCancelLabel) {
        this.iconCancelLabel = iconCancelLabel;
    }

    public JLabel getIconZoomLabel() {
        return iconZoomLabel;
    }

    public void setIconZoomLabel(JLabel iconZoomLabel) {
        this.iconZoomLabel = iconZoomLabel;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public void setLeftPanel(JPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public JLabel getIconBookStoreLabel() {
        return iconBookStoreLabel;
    }

    public void setIconBookStoreLabel(JLabel iconBookStoreLabel) {
        this.iconBookStoreLabel = iconBookStoreLabel;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public void setRightPanel(JPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public JPanel getFormPanel() {
        return formPanel;
    }

    public void setFormPanel(JPanel formPanel) {
        this.formPanel = formPanel;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JLabel getRegisterNowLabel() {
        return registerNowLabel;
    }

    public void setRegisterNowLabel(JLabel registerNowLabel) {
        this.registerNowLabel = registerNowLabel;
    }
}
