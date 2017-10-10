package astar.game.struct;

// Holds all the information about a cell in the search algorithm.
// Diagonal neighbours are fair game.
public class Cell extends Coordinate {
    private double f;
    private double g;
    private double h;
    private Cell left;
    private Cell topLeft;
    private Cell top;
    private Cell topRight;
    private Cell right;
    private Cell bottomRight;
    private Cell bottom;
    private Cell bottomLeft;
    private Cell previous;
    
    // Setup cell.
    public Cell(int x, int y) {
        super(x, y);
        
        f = 0;
        g = 1; // Gravel is assumed.
        h = 0;
        
        this.left = null;
        this.topLeft = null;
        this.top = null;
        this.topRight = null;
        this.right = null;
        this.bottomRight = null;
        this.bottom = null;
        this.bottomLeft = null;
        
        this.previous = null;
    }
    
    public void calcF () {
        f = g + h;
    }
    
    public double getF() {
        return f;
    }
    
    public void setG (double g) {
        this.g = g;
    }
    
    public double getG() {
        return g;
    }
    
    public void setH (double h) {
        this.h = h;
    }
    
    public double getH() {
        return h;
    }
    
    public void setLeft (Cell left) {
        this.left = left;
    }
    
    public Cell getLeft() {
        return left;
    }
    
    public void setTopLeft (Cell topLeft) {
        this.topLeft = topLeft;
    }
    
    public Cell getTopLeft() {
        return topLeft;
    }
    
    public void setTop (Cell top) {
        this.top = top;
    }
    
    public Cell getTop() {
        return top;
    }
    
    public void setTopRight (Cell topRight) {
        this.topRight = topRight;
    }
    
    public Cell getTopRight() {
        return topRight;
    }
    
    public void setRight (Cell right) {
        this.right = right;
    }
    
    public Cell getRight() {
        return right;
    }
    
    public void setBottomRight (Cell bottomRight) {
        this.bottomRight = bottomRight;
    }
    
    public Cell getBottomRight() {
        return bottomRight;
    }
    
    public void setBottom (Cell bottom) {
        this.bottom = bottom;
    }
    
    public Cell getBottom() {
        return bottom;
    }
    
    public void setBottomLeft (Cell bottomLeft) {
        this.bottomLeft = bottomLeft;
    }
    
    public Cell getBottomLeft() {
        return bottomLeft;
    }
    
    public void setPrevious (Cell previous) {
        this.previous = previous;
    }
    
    public Cell getPrevious() {
        return previous;
    }
}
