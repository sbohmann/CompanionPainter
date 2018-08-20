package at.yeoman.companion.painter;

import at.yeoman.companion.painter.ui.MainWindow;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class CompanionPainter {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        new CompanionPainter().run();
    }

    private void run() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(this::runInEventLoop);
    }

    private void runInEventLoop() {
        setLookAndFeel();
        showMainWindow();
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void showMainWindow() {
        new MainWindow().show();
    }
}
