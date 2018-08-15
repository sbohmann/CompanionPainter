package at.yeoman.companion.painter.ui;

import javax.swing.*;
import java.awt.*;

class MainPanel {
    private JPanel mainPanel;
    private JPanel toolPanel;
    private JPanel imagePanel;

    MainPanel() {
        createMainPanel();
        createToolPanel();
        createImagePanel();
    }

    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1200, 800));
        mainPanel.setLayout(new BorderLayout());
    }

    private void createToolPanel() {
        toolPanel = new JPanel();
        toolPanel.setBackground(new Color(0xf0f9ff));
        toolPanel.setPreferredSize(new Dimension(280, 0));
        mainPanel.add(toolPanel, BorderLayout.WEST);
    }

    private void createImagePanel() {
        imagePanel = new JPanel();
        imagePanel.setBackground(new Color(0xfff9f0));
        mainPanel.add(imagePanel, BorderLayout.CENTER);
    }

    Container getView() {
        return mainPanel;
    }
}
