package org.projects.Helper;

import javax.swing.*;

public class RefreshComponent {
    public static void refreshComponent(JComponent component) {
        if (component != null) {
            component.revalidate();
            component.repaint();
        }
    }
}
