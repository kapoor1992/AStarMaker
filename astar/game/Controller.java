package astar.game;

import astar.game.struct.Coordinate;
import astar.game.gui.Display;

// High-level coordinator of the game.
public class Controller {
    private int rows;
    private int cols;
    private Coordinate start;
    private Coordinate goal;
    private Coordinate[] grasses;
    private Coordinate[] swamps;
    private Coordinate[] waters;
    private Display display;
    private InputManager input;
    private int random_size;     // Used for random configuration.
    
    public Controller (int width, int height, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        
        random_size = rows * cols / 4;
        
        display = new Display(width, height, rows, cols);
        input = new InputManager(rows, cols);
    }
    
    // Execute the game.
    public void play() {
        // Initialize display.
        setConfiguration();
        display.setGrid(null, grasses, swamps, waters, start, goal);
        
        // Delay further execution so the user can take a look at the setup.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }
        
        // Setup for the search.
        AStar pathFinder = new AStar(rows, cols, start.getX(), start.getY(), goal.getX(), goal.getY());
        pathFinder.setG(grasses, swamps, waters, 3, 4, -1);
        
        // Search!
        int visited = pathFinder.search(true);
        
        // Recover path.
        Coordinate[] path = pathFinder.getPath();
        
        // Did we find a solution?
        if (visited == -1) {
            System.out.println("No solution.");
            System.out.println();
            
        //Show the solution
        } else {
            System.out.println("Number of cells visited: " + visited);
            
            display.reset();
            display.setGrid(path, grasses, swamps, waters, goal, goal);
        }
    }
    
    // Allow for automatic or manual setup.
    private void setConfiguration() {
        if (getRandom())
            randomSetup();
        else
            manualSetup();
    }
    
    // Randomly configure grid.
    private void randomSetup() {
        int x;
        int y;
        
        grasses = new Coordinate[random_size];
        swamps = new Coordinate[random_size];
        waters = new Coordinate[random_size];
        
        for (int i = 0; i < random_size; i++) {
            x = (int)(Math.random() * rows);
            y = (int)(Math.random() * cols);
            grasses[i] = new Coordinate(x, y);
            x = (int)(Math.random() * rows);
            y = (int)(Math.random() * cols);
            swamps[i] = new Coordinate(x, y);
            x = (int)(Math.random() * rows);
            y = (int)(Math.random() * cols);
            waters[i] = new Coordinate(x, y);
        }
        
        x = (int)(Math.random() * rows);
        y = (int)(Math.random() * cols);
        start = new Coordinate(x, y);
        
        x = (int)(Math.random() * rows);
        y = (int)(Math.random() * cols);
        goal = new Coordinate(x, y);
    }
    
    // Manually configure grid.
    private void manualSetup() {
        setGrasses();
        setSwamps();
        setWaters();
        setStart();
        setGoal();
    }
    
    private boolean getRandom() {
        System.out.println("Do you want to randomly configure the grid (yes or no):");
        boolean result = input.getBool();
        System.out.println();
        return result;
    }
    
    private void setGrasses() {
        System.out.println("Enter the grass cells (enter -1,-1 to stop):");
        grasses = input.getCoords();
        System.out.println();
    }
    
    private void setSwamps() {
        System.out.println("Enter the swamp cells (enter -1,-1 to stop):");
        swamps = input.getCoords();
        System.out.println();
    }
    
    private void setWaters() {
        System.out.println("Enter the water cells (enter -1,-1 to stop):");
        waters = input.getCoords();
        System.out.println();
    }
    
    private void setStart() {
        System.out.println("Enter the starting cell for the ant:");
        start = input.getCoord(0);
        System.out.println();
    }
    
    private void setGoal() {
        System.out.println("Enter the cell for the cheese:");
        goal = input.getCoord(0);
        System.out.println();
    }
}
