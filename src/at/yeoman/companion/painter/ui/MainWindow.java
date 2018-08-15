package at.yeoman.companion.painter.ui;

import javax.swing.*;

public class MainWindow {
    private final JFrame jframe;

    public MainWindow() {
        jframe = new JFrame("Companion Painter");
        jframe.setContentPane(new MainPanel().getView());
    }

    public void show() {
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }
}
