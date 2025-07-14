package org.projects.View;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.Helper.RefreshComponent;
import org.projects.Helper.StyleComponents;
import org.projects.Main;
import org.projects.View.Panel.listFunctionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.jar.JarEntry;

public class MainView extends JFrame {
    //TOP: CHỨA ICON,TÊN STORE,...
    private JPanel NorthPanel;
    //LEFT:CHỨC NĂNG CỦA CỬA HÀNG
    private JPanel WestPanel;
    private JLabel usernameLabel;
    private JLabel positionLabel;
    //CENTER: NỘI DUNG CHỨC NĂNG
    private JPanel CenterPanel;
    private CardLayout card;
    public MainView() {
        setTitle("BOOK STORE");
        setUndecorated(true);
        setSize(1200,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
        setVisible(true);
    }

    public JPanel top() {
        JPanel topPanel = new JPanel(new GridLayout(1,2));
        topPanel.setBackground(Color.GRAY);
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT,3,0));
        left.setOpaque(false);
        JLabel lb = new JLabel("Hệ thống quản lí cửa hàng sách",JLabel.CENTER);
        left.add(lb);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT,15,5));
        right.setOpaque(false);
        FlatSVGIcon iconMinus = new FlatSVGIcon("icon/minus.svg",25,25);
        FlatSVGIcon iconCancel = new FlatSVGIcon("icon/cancel.svg",15,15);
        JLabel minusLabel = new JLabel(iconMinus);
        JLabel cancelLabel = new JLabel(iconCancel);
        right.add(minusLabel);
        right.add(cancelLabel);

        topPanel.add(left);
        topPanel.add(right);

        minusLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setExtendedState(Frame.ICONIFIED);
            }
        });
        cancelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                System.exit(0);
            }
        });
        return topPanel;
    }

    public JPanel west() {
        JPanel westPanel = new JPanel(new GridBagLayout());
        westPanel.setPreferredSize(new Dimension(200, 900));

        // ---------- INFO PANEL ----------
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.LIGHT_GRAY);
        infoPanel.setPreferredSize(new Dimension(200, 20));  // cố định chiều cao

        SpringLayout layout = new SpringLayout();
        infoPanel.setLayout(layout);

        FlatSVGIcon iconInfo = new FlatSVGIcon("icon/user.svg", 80, 80);
        JLabel lbicon = new JLabel(iconInfo);

        usernameLabel = new JLabel("Nguyễn Hoàng Anh", JLabel.LEFT);
        usernameLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 10));

        positionLabel = new JLabel("Quản lí", JLabel.LEFT);
        positionLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 9));

        infoPanel.add(lbicon);
        infoPanel.add(usernameLabel);
        infoPanel.add(positionLabel);

        layout.putConstraint(SpringLayout.WEST, lbicon, 6, SpringLayout.WEST, infoPanel);
        layout.putConstraint(SpringLayout.NORTH, lbicon, 6, SpringLayout.NORTH, infoPanel);
        layout.putConstraint(SpringLayout.WEST, usernameLabel, 0, SpringLayout.EAST, lbicon);
        layout.putConstraint(SpringLayout.NORTH, usernameLabel, 25, SpringLayout.NORTH, infoPanel);
        layout.putConstraint(SpringLayout.WEST, positionLabel, 6, SpringLayout.WEST, usernameLabel);
        layout.putConstraint(SpringLayout.NORTH, positionLabel, 6, SpringLayout.SOUTH, usernameLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        westPanel.add(infoPanel, gbc);

        // ---------- FUNCTION PANEL ----------
        JPanel functionPanel = new listFunctionPanel(this);
        functionPanel.setOpaque(false);

        gbc = new GridBagConstraints(); // reset lại
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.7;
        gbc.fill = GridBagConstraints.BOTH;
        westPanel.add(functionPanel, gbc);

        // ---------- LOGOUT PANEL ----------
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
        logoutPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutPanel.setBackground(Color.LIGHT_GRAY);
        logoutPanel.setPreferredSize(new Dimension(200, 40)); // cố định chiều cao

        FlatSVGIcon icon = new FlatSVGIcon("icon/logout.svg", 20, 20);
        JLabel jlbicon = new JLabel(icon);
        JLabel txtlogout = new JLabel("Đăng xuất", JLabel.CENTER);

        logoutPanel.add(jlbicon);
        logoutPanel.add(txtlogout);

        txtlogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
                new LoginView();
            }
        });

        gbc = new GridBagConstraints(); // reset lại
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        westPanel.add(logoutPanel, gbc);

        return westPanel;
    }



    private void init() {
        this.setLayout(new BorderLayout());
        NorthPanel = top();
        this.add(NorthPanel,BorderLayout.NORTH);

        WestPanel = west();
        this.add(WestPanel,BorderLayout.WEST);

        card = new CardLayout();
        CenterPanel = new JPanel(card);
        this.add(CenterPanel,BorderLayout.CENTER);
    }

    public void addPanelCenter(JPanel panel) {
            String namePanel = panel.getClass().getSimpleName();
            CenterPanel.add(panel,namePanel);
            card.show(CenterPanel,namePanel);
            RefreshComponent.refreshComponent(CenterPanel);
    }
}
