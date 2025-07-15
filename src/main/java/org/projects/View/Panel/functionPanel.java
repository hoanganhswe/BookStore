package org.projects.View.Panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.Helper.RoundedRadiusHelper;

import javax.swing.*;
import java.awt.*;

public class functionPanel extends JPanel {
    private JLabel iconLabel;
    private JLabel nameFunctionLabel;
    private String nameIcon;
    private String nameFunction;
    public functionPanel(String iconPath,String nameFunction,String namePanel) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
        setBackground(Color.lightGray);
        this.setOpaque(true);
        FlatSVGIcon icon = new FlatSVGIcon(iconPath,20,20);
        iconLabel = new JLabel(icon,JLabel.LEFT);
        this.add(iconLabel);

        nameFunctionLabel = new JLabel(nameFunction,JLabel.CENTER);
        nameFunctionLabel.setAlignmentY(JLabel.CENTER);
        nameFunctionLabel.setFont(new Font("JetBrains Mono",Font.BOLD,15));
        this.add(nameFunctionLabel);
    }

    public JLabel getIconLabel() {
        return iconLabel;
    }

    public void setIconLabel(JLabel iconLabel) {
        this.iconLabel = iconLabel;
    }

    public JLabel getNameFunctionLabel() {
        return nameFunctionLabel;
    }

    public void setNameFunctionLabel(JLabel nameFunctionLabel) {
        this.nameFunctionLabel = nameFunctionLabel;
    }
}
