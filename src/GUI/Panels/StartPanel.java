package GUI.Panels;


import Backend.save.MazeReader;
import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {
    private JButton edit = new JButton("Edytuj nową mapę");
    private JButton readMap = new JButton("Wczytaj mapę");
    private final JButton exit = new JButton("Wyjście");
    private final Dimension buttonsSize = new Dimension(200, 50);
    private final ImageIcon image;

    public StartPanel(int width, int height, ImageIcon image) {
        this.image = image;
        setLayout(null);
        //    private JComboBox algoritms =new JComboBox();
        edit.setBounds((width - buttonsSize.width / 2) / 2, height * 1 / 5, buttonsSize.width, buttonsSize.height);
        readMap.setBounds((width - buttonsSize.width / 2) / 2, height * 2 / 5, buttonsSize.width, buttonsSize.height);
        exit.setBounds((width - buttonsSize.width / 2) / 2, height * 3 / 5, buttonsSize.width, buttonsSize.height);

        edit.setBackground(new Color(255, 255, 230));
        readMap.setBackground(new Color(255, 255, 230));
        exit.setBackground(new Color(255, 255, 230));


        add(edit);
        add(readMap);
        add(exit);


        edit.addActionListener(e -> Main.changePanel());

        readMap.addActionListener(e -> new MazeReader());

        exit.addActionListener(e -> System.exit(0));


    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
