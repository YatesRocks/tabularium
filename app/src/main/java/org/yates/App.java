package org.yates;

import javax.swing.SwingUtilities;
import org.yates.view.MainWindow;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().start());
    }
}
