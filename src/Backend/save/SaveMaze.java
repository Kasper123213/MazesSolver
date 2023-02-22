package Backend.save;

import GUI.Category;
import GUI.Panels.EditionPanel;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveMaze {
    public SaveMaze(ArrayList<ArrayList<Integer>> board) {
        String text = "";
        File file;
        for(int i=1;;i++){
            file = new File("Resources/mazes/labirynt"+i+".txt");
            if(!file.exists()){

                break;
            }
        }

            try {
                file.createNewFile();
                PrintWriter writer = new PrintWriter(file);
                for(int i=0;i<board.get(0).size();i++){
                    for(int j=0;j<board.size();j++){
                        if(board.get(j).get(i)== Category.WALL.ordinal())text+=Category.WALL.ordinal();
                        else text+=(Category.EMPTY.ordinal());
                    }
                    text+="\n";
                }

            writer.write(text);
            writer.close();
        } catch (IOException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
        if(file.exists()){
            EditionPanel.setSaveBTN(new Color(0xCC05F10B, true));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            EditionPanel.setSaveBTN(new Color(255, 255, 230));
        }
    }
}

