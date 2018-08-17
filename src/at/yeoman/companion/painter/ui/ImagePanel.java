package at.yeoman.companion.painter.ui;

import at.yeoman.companion.painter.image.LayeredImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class ImagePanel {
    LayeredImage image;
    private BufferedImage buffer;
    private JPanel view;

    ImagePanel() {
        //image = new LayeredImage();
        view = new JPanel();
        view.setBackground(new Color(0xfff9f0));
    }

    JPanel getView() {
        return view;
    }
}
