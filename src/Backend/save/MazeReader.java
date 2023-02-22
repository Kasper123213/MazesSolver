package Backend.save;

import Backend.Maze;
import GUI.Panels.EditionPanel;
import Main.Main;
import GUI.Panels.StartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MazeReader {
    public MazeReader(){
        JFrame frame = new JFrame("Wybierz plik");
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File("Resources/mazes"));

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path=fileChooser.getSelectedFile().getAbsolutePath();
            Main.changePanel();

            EditionPanel.setNewBoard(setBoard(path));
        }
    }

    private ArrayList<ArrayList<Integer>> setBoard(String filePath) {
        ArrayList<ArrayList<Integer>> board = new ArrayList<>();


        try (FileReader fr = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<Integer> row = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        row.add(Character.getNumericValue(c));
                    }
                }
                board.add(row);
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu pliku: " + e.getMessage());
        }

        // Wyświetlenie zawartości listy ArrayList<ArrayList<Integer>> board
//        for (ArrayList<Integer> row : board) {
//            System.out.println(row);
//        }

        ArrayList<ArrayList<Integer>> transposed = new ArrayList<>();
        for (int i = 0; i < board.get(0).size(); i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < board.size(); j++) {
                row.add(board.get(j).get(i));
            }
            transposed.add(row);
        }
        return transposed;
    }
}
