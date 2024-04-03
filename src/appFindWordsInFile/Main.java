package appFindWordsInFile;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                new Frame();

            }
        };
        SwingUtilities.invokeLater(runnable);
    }
}
