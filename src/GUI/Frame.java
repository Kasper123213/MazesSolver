package GUI;

import GUI.Panels.EditionPanel;
import GUI.Panels.StartPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Frame extends JFrame {
    private static StartPanel startPanel;
    private static EditionPanel editionPanel;

    ImageIcon image;

    public Frame() {
        image = new ImageIcon("Resources/labirynt.png");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
//        setSize(500, 400);
//        setAlwaysOnTop(true);


        setVisible(true);
        setLayout(new GridLayout());

        startPanel = new StartPanel(getWidth(), getHeight(), image);
        editionPanel = new EditionPanel(getWidth(), getHeight(), image);

        setContentPane(startPanel);

    }


    public void changePanel() {
        if (getContentPane() == startPanel) setContentPane(editionPanel);
        else setContentPane(startPanel);
        validate();
    }

    public void refresh() {
        getContentPane().repaint();
    }
}
