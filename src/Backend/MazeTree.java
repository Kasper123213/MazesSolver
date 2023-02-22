package Backend;

import GUI.Panels.EditionPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MazeTree {
    public HashMap<Integer, ArrayList<Point>> verticesPaths = new HashMap<>();//numer wierzcholka i sciezki idące do tego wierzcholka (lista sciezek)
    public HashMap<Integer, Point> verticesPoint = new HashMap<>();//wierzchołki
    public ArrayList<ArrayList<Point>> paths = new ArrayList<>();//sciezki miedzy wierzcholkami łącznie z wierzchołkami granicznymi (lista list punktow)
    public ArrayList<Integer> rightPath = new ArrayList<>();
    public ArrayList<Integer> number_pathId = new ArrayList<>();


    public void addField(int x, int y, int id) {
//        System.out.println("---added" + "\u001B[35m" + "x: " + x + " y: " + y + " \u001B[0m" + " index= " + id);
        verticesPaths.get(id).add(new Point(x, y));

    }

    public void clear() {
        verticesPaths.clear();
        rightPath.clear();
        number_pathId.clear();
    }

    public synchronized int setId(int sequenceNumber) {
        for (int i = 0; ; i++) {
            if (!verticesPaths.containsKey(i)) {
                verticesPaths.put(i, new ArrayList<>());
                number_pathId.add(sequenceNumber);
                return i;
            }
        }
    }


    public ArrayList<Integer> showRightPath() {
//        System.out.println(number_pathId);

        for (int i = number_pathId.size() - 1; i >= 0; i--) {
            if (verticesPaths.get(i).get(verticesPaths.get(i).size() - 1).equals(new Point(EditionPanel.getColumns() - 1, 0))) {
                rightPath.add(i);
//                System.err.println("dodano");
            }
        }


        try {
            for (int i = number_pathId.size() - 1; i >= 0; i--) {
                if (i < rightPath.get(0) &&
                        verticesPaths.get(i).get(verticesPaths.get(i).size() - 1)
                                .equals(verticesPaths.get(rightPath.get(rightPath.size() - 1)).get(0))) {
                    rightPath.add(i);
                }
            }
        } catch (IndexOutOfBoundsException e) {
        }


        return rightPath;
    }

    public HashMap<Integer, ArrayList<Point>> getWays() {
        return verticesPaths;
    }
}

