package astar.game.gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import astar.game.struct.Coordinate;

// The GUI.
public class Display extends JFrame {
    public static final Color path = new Color (255, 0, 255);
    public static final Color gravel = new Color(204, 204, 204);
    public static final Color grass = new Color(44, 176, 55);
    public static final Color swamp = new Color(121, 76, 19);
    public static final Color water = new Color(28, 107, 160);
    
    private JPanel grid;
    private int width;
    private int height;
    private int rows;
    private int cols;
    private JLabel antPic;
    private JLabel cheesePic;
    
    public Display (int width, int height, int rows, int cols) {
        grid = new JPanel();
        
        this.width = width;
        this.height = height;
        this.rows = rows;
        this.cols = cols;
        
        antPic = new JLabel(new ImageIcon(getClass().getResource("img/ant.jpg")));
        cheesePic = new JLabel(new ImageIcon(getClass().getResource("img/cheese.jpg")));
        
        // Sets up JFrame parameters.
        initDisplay();
    }
    
    // Add appropriate colours and images to the visual grid.
    public void setGrid(Coordinate[] pathing, Coordinate[] grasses, Coordinate[] swamps, Coordinate[] waters, Coordinate start, Coordinate goal) {
        // Map from 2D coordinates to 1D arrays so that they can be added to GridLayout.
        int[] pathSpots = mapCoordinates(pathing);
        int[] grassSpots = mapCoordinates(grasses);
        int[] swampSpots = mapCoordinates(swamps);
        int[] waterSpots = mapCoordinates(waters);
        int antSpot = mapCoordinate(start);
        int cheeseSpot = mapCoordinate(goal);
        
        antPic = new JLabel(new ImageIcon(getClass().getResource("img/ant.jpg")));
        cheesePic = new JLabel(new ImageIcon(getClass().getResource("img/cheese.jpg")));
        
        // Add to each visual cell.
        for (int i = 0; i < rows * cols; i++) {
            if (i == antSpot) {
                grid.add(antPic);
            } else if (i == cheeseSpot) {
                grid.add(cheesePic);
            } else if (doesInclude(pathSpots, i)) {
                grid.add(new Spot(path));
            } else if (doesInclude(grassSpots, i)) {
                grid.add(new Spot(grass));
            } else if (doesInclude(swampSpots, i)) {
                grid.add(new Spot(swamp));
            } else if (doesInclude(waterSpots, i)) {
                grid.add(new Spot(water));
            } else {
                grid.add(new Spot(gravel));
            }
        }
        
        add(grid);
        setVisible(true);
    }
    
    // Refreshes visual grid before future use.
    public void reset() {
        grid = new JPanel();
        grid.setLayout(new GridLayout(rows, cols, 1, 1));
        grid.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }
    
    // Setup JFrame.
    private void initDisplay() {
        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        grid.setLayout(new GridLayout(rows, cols, 1, 1));
        grid.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }
    
    // 2D to 1D list mapper.
    private int[] mapCoordinates (Coordinate[] coords) {
        if (coords == null)
            return null;
        
        int[] result = new int[coords.length];
        
        for (int i = 0; i < coords.length; i++)
            result[i] = mapCoordinate(coords[i]);
        
        return result;
    }
    
    // Low-level 2D to 1D mapper.
    private int mapCoordinate (Coordinate coord) {
        return coord.getX() * cols + coord.getY();
    }
    
    // Utility function for checking whether a colour spot is in a list.
    private boolean doesInclude (int[] spots, int spot) {
        if (spots != null) {
            for (int i = 0; i < spots.length; i++) {
                if (spots[i] == spot)
                    return true;
            }
        }
        
        return false;
    }
}