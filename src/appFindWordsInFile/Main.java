package appFindWordsInFile;
/**
 * Program finds all unique words in txt file and count them
 */

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
