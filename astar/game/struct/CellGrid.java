package astar.game.struct;

// Holds the search grid.
public class CellGrid {
    private Cell[][] grid;
    private int rows;
    private int cols;
    
    public CellGrid (int rows, int cols) {
        grid = new Cell[rows][cols];
        this.rows = rows;
        this.cols = cols;
        
        // Add cells and pre-compute neighbours.
        initCells();
        populateNeighbours();
    }
    
    public void setCell (Cell cell) {
        grid[cell.getX()][cell.getY()] = cell;
    }
    
    public Cell getCell (int x, int y) {
        return grid[x][y];
    }
    
    // Set G-score.
    public void setG (Coordinate[] coords, double g) {
        Cell cell;
        
        for (int i = 0; i < coords.length; i++) {
            cell = grid[coords[i].getX()][coords[i].getY()];
            cell.setG(g);
        }
    }
    
    // Create cells.
    private void initCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                setCell(new Cell(i, j));
            }
        }
    }
    
    // Point to all possible neighbours for each cell.
    private void populateNeighbours() {
        Cell cell;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cell = getCell(i, j);
                
                if (i > 0) {
                    if (j > 0)
                        cell.setTopLeft(getCell(i - 1, j - 1));
                    cell.setTop(getCell(i - 1, j));
                }
                
                if (i < rows - 1) {
                    if (j < cols - 1)
                        cell.setBottomRight(getCell(i + 1, j + 1));
                    cell.setBottom(getCell(i + 1, j));
                }
                
                if (j > 0) {
                    if (i < rows - 1)
                        cell.setBottomLeft(getCell(i + 1, j - 1));
                    cell.setLeft(getCell(i, j - 1));
                }
                
                if (j < cols - 1) {
                    if (i > 0)
                        cell.setTopRight(getCell(i - 1, j + 1));
                    cell.setRight(getCell(i, j + 1));
                }
            }
        }
    }
}
