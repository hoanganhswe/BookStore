package org.projects.Helper;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedRadiusHelper {

    public static void radiusForFrame(JFrame frame,int radius) {
        frame.setUndecorated(true);
        frame.setBackground(new Color(0,0,0,0));
        frame.setShape(new RoundRectangle2D.Double(0,0,frame.getWidth(),frame.getHeight(),radius,radius));
    }

    public static void radiusForTextField(JTextField tField,int radius) {
        tField.setOpaque(false);
        tField.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        tField.setUI(new BasicTextFieldUI() {
            @Override
            protected void paintSafely(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Vẽ nền bo góc
                g2.setColor(tField.getBackground());
                g2.fillRoundRect(0, 0, tField.getWidth(), tField.getHeight(), radius, radius);

                // Vẽ viền nếu muốn
                g2.setColor(Color.GRAY);
                g2.drawRoundRect(0, 0, tField.getWidth() - 1, tField.getHeight() - 1, radius, radius);

                g2.dispose();
                super.paintSafely(g); // Vẽ text và caret
            }
        });
    }

    public static void radiusForDialog(JDialog dialog, int radius) {
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(0, 0, 0, 0));
        dialog.setShape(new RoundRectangle2D.Double(0, 0, dialog.getWidth(), dialog.getHeight(), radius, radius));
    }

    public static void radiusForPanel(JPanel panel, int radius) {
        panel.setOpaque(false);
        panel.setBorder(null);

        panel.setLayout(new BorderLayout()) ;

        panel.setUI(new javax.swing.plaf.PanelUI() {
            @Override
            public void update(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), radius, radius);
                g2.dispose();
                paint(g, c);
            }
        });
    }

    public static void radiusForButton(JButton button, int radius) {
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(false);

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), radius, radius);
                g2.dispose();
                super.paint(g, c);
            }
        });
    }
}
