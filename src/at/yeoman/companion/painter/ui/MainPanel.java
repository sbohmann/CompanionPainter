package at.yeoman.companion.painter.ui;

import javax.swing.*;
import java.awt.*;

class MainPanel {
    private JPanel mainPanel;
    private ToolPanel toolPanel;
    private ImagePanel imagePanel;

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
        toolPanel = new ToolPanel();
        mainPanel.add(toolPanel.getView(), BorderLayout.WEST);
    }

    private void createImagePanel() {
        imagePanel = new ImagePanel();
        mainPanel.add(imagePanel.getView(), BorderLayout.CENTER);
    }

    Container getView() {
        return mainPanel;
    }
}
