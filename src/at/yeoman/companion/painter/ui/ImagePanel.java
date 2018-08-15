package at.yeoman.companion.painter.ui;

import javax.swing.*;
import java.awt.*;

class ImagePanel {
    private JPanel view;

    ImagePanel() {
        view = new JPanel();
        view.setBackground(new Color(0xfff9f0));
    }

    JPanel getView() {
        return view;
    }
}
