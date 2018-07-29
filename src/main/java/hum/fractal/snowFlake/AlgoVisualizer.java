package hum.fractal.snowFlake;

import hum.util.AlgoVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author hum
 */
public class AlgoVisualizer {

    private static int DELAY = 10;

    private FractalData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int depth, int side) {

        data = new FractalData(depth);
        int sceneWidth = 3 * side + 3;
        int sceneHeight = side;

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Fractal Visualizer", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {

        setData(data.depth);
    }

    private void setData(int depth) {
        data.depth = depth;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() >= '0' && event.getKeyChar() <= '9') {
                int depth = event.getKeyChar() - '0';
                setData(depth);
            }
        }
    }

    public static void main(String[] args) {

        int depth = 6;
        int side = 300;

        AlgoVisualizer vis = new AlgoVisualizer(depth, side);
    }
}

