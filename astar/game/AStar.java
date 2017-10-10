package astar.game;

import astar.game.struct.*;

// The heart of the program.
public class AStar {
    private int rows;
    private int cols;
    private Cell start;
    private Cell goal;
    private CellGrid grid;
    private CellList opened;
    private CellList closed;
    
    // Initialize search grid.
    public AStar (int rows, int cols, int startRow, int startCol, int goalRow, int goalCol) {
        this.rows = rows;
        this.cols = cols;
        
        grid = new CellGrid(rows, cols);
        
        start = grid.getCell(startRow, startCol);
        goal = grid.getCell(goalRow, goalCol);
        
        opened = new CellList(rows * cols);
        closed = new CellList(rows * cols);
        
        // Calculate heuristic upon instantitation to save time later.
        setHeuristics();
    }
    
    // Set G-scores. Gravel is 1, grass is 3, swamp is 4, and water is unpenetrable. 
    public void setG(Coordinate[] grass, Coordinate[] swamp, Coordinate[] water, double grassG, double swampG, double waterG) {
        grid.setG(grass, grassG);
        grid.setG(swamp, swampG);
        grid.setG(water, waterG);
        
        // Force start and goal cells to be on gravel.
        start.setG(1);
        goal.setG(1);
    }
    
    // Find path to goal and return the number of cells visited. -1 means no path was found.
    public int search (boolean showCurrent) {
        int visited = 0;
        Cell current;
        int currentIndex;
        boolean found = false;
        
        // Initialize opened and closed lists.
        opened.append(start);
        initClosed();
        
        // Do we want to track the algorithm?
        if (showCurrent)
            System.out.println("Tracking visited cells...");
        
        // Go until no cells left to be examined.
        while (opened.getSize() > 0) {
            currentIndex = 0;
            
            // Get most promising cell in the opened list.
            for (int i = 1; i < opened.getSize(); i++) {
                if (opened.getCell(i).getF() < opened.getCell(currentIndex).getF()) {
                    currentIndex = i;
                }
            }
            current = opened.getCell(currentIndex);
            visited++;
            
            // Track the algorithm if required.
            if (showCurrent) {
                try {
                    System.out.println(current.getX() + ", " + current.getY());
                    Thread.sleep(250);
                } catch (InterruptedException ie) {
                }
            }
            
            // Break loop if we got to the goal.
            if (current == goal) {
                found = true;
                break;
            }
            
            // Update search lists.
            opened.remove(current);
            closed.append(current);
            
            // Visit neighbours of current cell and get ready for next iteration.
            for (int i = 1; i <= 8; i++)
                visitNeighbour(current, i);
        }
        
        if (showCurrent)
            System.out.println();
        
        if (!found)
            return -1;
        return visited;
    }
    
    // Will be used to trace path on the GUI.
    public Coordinate[] getPath() {
        Coordinate[] path = null;
        int size = 1;
        Cell current = goal;
        
        // Get path size.
        for (int i = 0; current.getPrevious() != null; i++) {
            current = current.getPrevious();
            size++;
        }
        size--;
        
        // Only enter if we actually got to the goal.
        if (size > 0) {
            // Initialize path list.
            path = new Coordinate[size];
            path[0] = new Coordinate(goal.getX(), goal.getY());
            current = goal;
            
            // Backtrack from goal to get the path.
            for (int i = 0; i < path.length; i++) {
                current = current.getPrevious();
                path[i] = new Coordinate(current.getX(), current.getY());
            }
        }
        
        return path;
    }
    
    // Go through the grid to set the H-scores.
    private void setHeuristics() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                setHeuristic(grid.getCell(i, j));
            }
        }
    }
    
    // Close all water cells preemptively.
    private void initClosed() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid.getCell(i, j).getG() == -1)
                    closed.append(grid.getCell(i, j));
            }
        }
    }
    
    // Euclidean distance is used for the H-scores.
    private void setHeuristic (Cell cell) {
        int currentRow = cell.getX();
        int currentCol = cell.getY();
        int goalRow = goal.getX();
        int goalCol = goal.getY();
        
        double distance = Math.sqrt(Math.pow(goalRow - currentRow, 2) + Math.pow(goalCol - currentCol, 2));
        
        cell.setH(distance);
        cell.calcF();
    }
    
    // Check a cell's neighbour and update search lists.
    private void visitNeighbour (Cell current, int key) {
        // Get the neighbour based on the key provided.
        Cell neighbour = getNeighbour(current, key);
        
        // Does the neighbour exist?
        if (neighbour != null) {
            // Make sure they aren't already closed.
            if (!closed.doesInclude(neighbour)) {
                // New G-score used for comparisons.
                double newG = current.getG() + neighbour.getG();
                
                // Did we already have them in our prospective list?
                if (opened.doesInclude(neighbour)) {
                    // Update them if the new G-score is better.
                    if (newG < neighbour.getG()) {
                        neighbour.setG(newG);
                        neighbour.calcF();
                        neighbour.setPrevious(current);
                    }
                // Add them to the open list.
                } else {
                    neighbour.setG(newG);
                    neighbour.calcF();
                    neighbour.setPrevious(current);
                    opened.append(neighbour);
                }
            }
        }
    }
    
    // The neighbour key goes clockwise from left (1) to bottom left (8).
    private Cell getNeighbour (Cell current, int key) {
        Cell neighbour = null;
        
        if (key == 1) {
            if (current.getLeft() != null)
                neighbour = current.getLeft();
        } else if (key == 2) {
            if (current.getTopLeft() != null)
                neighbour = current.getTopLeft();
        } else if (key == 3) {
            if (current.getTop() != null)
                neighbour = current.getTop();
        } else if (key == 4) {
            if (current.getTopRight() != null)
                neighbour = current.getTopRight();
        } else if (key == 5) {
            if (current.getRight() != null)
                neighbour = current.getRight();
        } else if (key == 6) {
            if (current.getBottomRight() != null)
                neighbour = current.getBottomRight();
        } else if (key == 7) {
            if (current.getBottom() != null)
                neighbour = current.getBottom();
        } else if (key == 8) {
            if (current.getBottomLeft() != null)
                neighbour = current.getBottomLeft();
        }
        
        return neighbour;
    }
}
