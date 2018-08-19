package at.yeoman.companion.painter.ui;

import at.yeoman.companion.painter.image.LayeredImage;
import at.yeoman.companion.painter.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class ImagePanel {
    LayeredImage image;
    private BufferedImage buffer;
    private LayeredImageView view;

    ImagePanel() {
        image = new LayeredImage(new Size(800, 600));
        view = new LayeredImageView(image);
        view.setBackground(new Color(0xfff9f0));
    }

    JPanel getView() {
        return view;
    }
}
