package Main;

import GUI.Frame;

import javax.swing.*;

//labirynt, lewym tworzymy ścieżkę
public class Main {
    private static Frame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new Frame();
            }
        });
    }


    public static void changePanel() {
        frame.changePanel();
    }

    public static void refresh() {
        frame.refresh();
    }
}
