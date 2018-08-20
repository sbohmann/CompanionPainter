package at.yeoman.companion.painter.ui;

import at.yeoman.companion.painter.image.LayerView;
import at.yeoman.companion.painter.image.LayeredImage;
import at.yeoman.companion.painter.Position;
import at.yeoman.companion.painter.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

class LayeredImageView extends JPanel {
    private LayerView view;
    private LayeredImage image;
    private BufferedImage buffer;
    private boolean bufferInvalidated;

    private Position lastPosition = null;

    LayeredImageView(LayeredImage image) {
        this.image = image;
        this.view = image.getLayerView(0);
        createBuffer(image);
        fillBuffer();
        setupMouseHandling();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (bufferInvalidated) {
            fillBuffer();
        }
        g.drawImage(buffer, 0, 0, null);
    }

    private void createBuffer(LayeredImage image) {
        Size size = image.getSize();
        buffer = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
    }

    private void fillBuffer() {
        image.writeToBufferedImage(buffer, Position.Zero);
    }

    private void setupMouseHandling() {
        MouseAdapter mouseAdapter = createMouseAdapter();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    private MouseAdapter createMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                handleMouseDown(event.getX(), event.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseUp(e.getX(), e.getY());
            }

            @Override
            public void mouseMoved(MouseEvent event) {
                handleMouseMove(event.getX(), event.getY());
            }

            @Override
            public void mouseDragged(MouseEvent event) {
                handleMouseMove(event.getX(), event.getY());
            }
        };
    }

    private void handleMouseDown(int x, int y) {
        lastPosition = new Position(x, y);
        put(x, y, 0xff000000);
        invalidateBuffer();
    }

    private void handleMouseUp(int x, int y) {
        lastPosition = null;
    }

    private void handleMouseMove(int x, int y) {
        if (lastPosition != null) {
            Position from = lastPosition;
            Position to = new Position(x, y);
            lastPosition = to;
            line(from, to, 0xff000000);
            invalidateBuffer();
        }
    }

    private void line(Position from, Position to, int color) {
        int x1 = from.x;
        int y1 = from.y;
        int x2 = to.x;
        int y2 = to.y;
        put(x1, y1, color);
        put(x2, y2, color);
        int adx = Math.abs(x2 - x1);
        int sx = sign(x2 - x1);
        int ady = Math.abs(y2 - y1);
        int sy = sign(y2 - y1);
        if (adx > 1 || ady > 1) {
            if (adx > ady) {
                for (int n = 1; n < adx; ++n) {
                    double rel = (double) n / adx;
                    int x = x1 + sx * n;
                    int y = y1 + sy * (int) (rel * ady + 0.5);
                    put(x, y, color);
                }
            } else {
                for (int n = 1; n < ady; ++n) {
                    double rel = (double) n / ady;
                    int y = y1 + sy * n;
                    int x = x1 + sx * (int) (rel * adx + 0.5);
                    put(x, y, color);
                }
            }
        }
    }

    private int sign(int n) {
        return Integer.compare(n, 0);
    }

    private void put(int x, int y, int color) {
        Size size = image.getSize();
        if (size.contains(x, y)) {
            view.put(x, y, color);
        }
    }

    private void invalidateBuffer() {
        bufferInvalidated = true;
        repaint();
    }
}
