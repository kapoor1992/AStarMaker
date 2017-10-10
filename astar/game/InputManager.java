package astar.game;

import java.util.Scanner;
import astar.game.struct.Coordinate;

// Gets user input (mostly for manual configuration.
public class InputManager {
    private Scanner reader;
    private int rows;
    private int cols;
    
    public InputManager (int rows, int cols) {
        reader = new Scanner(System.in);
        
        this.rows = rows;
        this.cols = cols;
    }
    
    // Gets a yes/no response with some built-in input validation.
    public boolean getBool() {
        try {
            String response = reader.nextLine();
            response.replaceAll("\\s","");
            
            if (response.equalsIgnoreCase("yes"))
                return true;
            else if (response.equalsIgnoreCase("no"))
                return false;
            else
                throw new RuntimeException();
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again");
            return getBool();
        }
    }
    
    // Gets a list of coordinates. A response of "-1,X" or "X,-1" stops further probing.
    public Coordinate[] getCoords() {
        Coordinate[] result = new Coordinate[rows * cols];
        Coordinate current;
        int size = 0;
        
        while (true) {
            current = getCoord(-1);
            
            if (current.getX() == -1 || current.getY() == -1 || size >= rows * cols)
                break;
            
            result[size] = current;
            size++;
        }
        
        // Resize list.
        result = shrink(result, size);
        
        return result;
    }
    
    // Gets a coordinate in the form "row,col" with some built-in input validation.
    public Coordinate getCoord (int min) {
        try {
            String response = reader.nextLine();
            response.replaceAll("\\s","");
            String[] vals = response.split(",");
            
            int row = Integer.parseInt(vals[0]);
            int col = Integer.parseInt(vals[1]);
            
            if (row < min || col < min || row >= rows || col >= cols)
                throw new IndexOutOfBoundsException();
            
            Coordinate coord = new Coordinate(row, col);
            
            return coord;
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again");
            return getCoord(min);
        }
    }
    
    // Remove null values from a list.
    private Coordinate[] shrink (Coordinate[] coords, int size) {
        Coordinate[] shrunk = new Coordinate[size];
        
        for (int i = 0; i < size; i++)
            shrunk[i] = coords[i];
        
        return shrunk;
    }
}
