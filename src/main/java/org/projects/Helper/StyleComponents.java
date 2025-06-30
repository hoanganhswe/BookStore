package org.projects.Helper;

import javax.swing.*;
import java.awt.*;

public class StyleComponents {
    public static JLabel formLabel(String txt,Font font,int w,int h) {
        JLabel lb = new JLabel(txt);
        lb.setFont(font);
        lb.setForeground(Color.black);
        lb.setPreferredSize(new Dimension(w,h));
        return lb;
    }

    public static JButton styleButton(String txt,Font font,Color bg,Color fg ,int w,int h) {
        JButton btn = new JButton(txt);
        btn.setPreferredSize(new Dimension(w,h));
        btn.setFont(font);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
