package astar;

import java.util.Scanner;
import javax.swing.ImageIcon;
import astar.game.Controller;

// Entry point.
public class AStarMain {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int ROWS = 16;
    public static final int COLS = 16;
    
    public static void main (String[] args) {
        intro();
        exec();
    }
    
    // Introduce game.
    public static void intro() {
        System.out.println("Welcome to the A Star Search pathfinding program! " +
                           "The algorithm is demonstrated through the use of an ant trying to get to a block of cheese.\n\n" +
                           "The cost of moving are as follows:\n" +
                           "    Gravel - 1 (Gray)\n" +
                           "    Grass  - 3 (Green)\n" +
                           "    Swamp  - 4 (Brown)\n" +
                           "    Water  - Impossible (Blue)\n\n" +
                           "You can setup the grid automatically or manually. Gravel is the default for automatic configuration.\n" +
                           "For manual configuration, please give the coordinate in the form \"row,column\" with the top left being 0,0.\n\n");
    }
    
    // Execution loop.
    public static void exec() {
        while (true) {
            System.out.println();
            
            Scanner in = new Scanner(System.in);
            Controller c = new Controller(WIDTH, HEIGHT, ROWS, COLS);
            c.play();
            
            System.out.println("\nType \"yes\" to play again:");
            String response = in.nextLine();
            if (!response.equalsIgnoreCase("yes")) // Repeat until user wants to stop.
                System.exit(0);
        }
    }
}