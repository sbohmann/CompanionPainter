package at.yeoman.companion.painter.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

class ToolPanel {
    private JPanel view;
    private List<JButton> buttons;

    ToolPanel() {
        createView();
        createButtons();
        setupResizing();
    }

    private void createView() {
        view = new JPanel();
        view.setBackground(new Color(0xf0f9ff));
        view.setPreferredSize(new Dimension(160, 0));
        view.setLayout(null);
    }

    private void createButtons() {
        buttons = new ArrayList<>();
        addButton("Load");
        addButton("Save");
        layoutButtons();
    }

    private void addButton(String label) {
        JButton button = new JButton(label);
        view.add(button);
        buttons.add(button);
    }

    private void setupResizing() {
        view.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                layoutButtons();
            }
        });
    }

    private void layoutButtons() {
        int y = 5;
        for (JButton button : buttons) {
            button.setBounds(5, y, view.getWidth() - 10, 30);
            y += 35;
        }
    }

    JPanel getView() {
        return view;
    }
}
