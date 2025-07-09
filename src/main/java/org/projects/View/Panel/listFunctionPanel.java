package org.projects.View.Panel;

import org.projects.Main;
import org.projects.View.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class listFunctionPanel extends JPanel {
        private List<functionPanel> lst;
        private HashMap<String,JPanel> hm;
        private MainView mv;
        String[][] listFunc = {
                {"icon/home.svg","Trang chủ","HomePanel"},
                {"icon/bookmark.svg","Sách","BookPanel"},
                {"icon/category.svg","Danh mục","CategoryPanel"},
                {"icon/entryform.svg","Phiếu nhập","EntryFormPanel"},
                {"icon/customer.svg","Khách hàng","CustomerPanel"},
                {"icon/employee.svg","Nhân viên","EmployeePanel"},
                {"icon/order.svg","Đơn hàng","OrderPanel"},
                {"icon/account.svg","Tài khoản","AccountPanel"},
                {"icon/role.svg","Phân quyền","RolePanel"},
                {"icon/supplier.svg","Nhà cung cấp","SupplierPanel"},
                {"icon/statistics.svg","Thống kê","StatisticsPanel"}
        };
        public listFunctionPanel(MainView mv) {
            this.mv = mv;
            this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            lst = new ArrayList<>();
            hm = new HashMap<>();
            init();
        }

        private void init() {
            hm.put("HomePanel",new HomePanel());
            hm.put("BookPanel",new BookPanel());
            hm.put("CategoryPanel",new CategoryPanel());
            hm.put("EntryFormPanel",new EntryFormPanel());
            hm.put("CustomerPanel",new CustomerPanel());
            hm.put("EmployeePanel",new EmployeePanel());
            hm.put("OrderPanel",new OrderPanel());
            hm.put("AccountPanel",new AccountPanel());
            hm.put("RolePanel",new RolePanel());
            hm.put("SupplierPanel",new SupplierPanel());
            hm.put("StatisticsPanel",new StatisticsPanel());

            for(String[] it : listFunc) {
                functionPanel f = new functionPanel(it[0],it[1],it[2]);
                f.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        clickChangeColor(e);
                        showPanel(it[2]);
                    }
                });
                lst.add(f);
                this.add(f);
            }
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

    public void clickChangeColor(MouseEvent e) {
        for(functionPanel it : lst) {
            if(e.getSource() == it) {
                it.getNameFunctionLabel().setForeground(Color.WHITE);
                it.setBackground(Color.GRAY);
            } else {
                it.getNameFunctionLabel().setForeground(Color.BLACK);
                it.setBackground(Color.WHITE);
            }
        }
    }
    public void showPanel(String name) {
         JPanel panel = hm.get(name);
         mv.addPanelCenter(panel);
    }
}
