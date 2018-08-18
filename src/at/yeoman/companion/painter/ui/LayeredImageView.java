package at.yeoman.companion.painter.ui;

import at.yeoman.companion.painter.image.LayeredImage;
import at.yeoman.companion.painter.image.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class LayeredImageView extends JPanel {
    private LayeredImage image;
    private BufferedImage buffer;

    public LayeredImageView(LayeredImage image) {
        this.image = image;
        createBuffer(image);
        fillBuffer();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, null);
    }

    private void createBuffer(LayeredImage image) {
        Size size = image.getSize();
        buffer = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
    }

    private void fillBuffer() {
        image.writeToRaster(buffer.getRaster());
    }
}
