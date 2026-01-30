import Viewer.Window;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Window(900, 600);
        });
    }
}