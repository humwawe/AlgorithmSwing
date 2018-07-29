package hum.moveBox;

import hum.util.AlgoVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * @author hum
 */
public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 80;

    private GameData data;
    private AlgoFrame frame;

    public AlgoVisualizer(String filename) {

        data = new GameData(filename);
        int sceneWidth = data.M() * blockSide;
        int sceneHeight = data.N() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Move the Box Solver", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    public void run() {
        setData();
        if (data.solve()) {
            System.out.println("The game has a solution!");
        } else {
            System.out.println("The game does NOT have a solution.");
        }

        setData();
    }

    private void setData() {
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }


    public static void main(String[] args) {

        String filename = AlgoVisHelper.getResources("boston_09.txt");

        AlgoVisualizer vis = new AlgoVisualizer(filename);
    }
}

