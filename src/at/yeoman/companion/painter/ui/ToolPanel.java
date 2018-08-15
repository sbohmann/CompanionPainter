package at.yeoman.companion.painter.ui;

import javax.swing.*;
import java.awt.*;

class ToolPanel {
    private JPanel view;

    ToolPanel() {
        view = new JPanel();
        view.setBackground(new Color(0xf0f9ff));
        view.setPreferredSize(new Dimension(280, 0));
    }

    JPanel getView() {
        return view;
    }
}
