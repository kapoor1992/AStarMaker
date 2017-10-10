package astar.game.struct;

// Used for the opened and closed lists.
public class CellList {
    private Cell list[];
    private int size;
    
    public CellList (int capacity) {
        list = new Cell[capacity];
        size = 0;
    }
    
    public int getSize() {
        return size;
    }
    
    public Cell getCell (int index) {
        return list[index];
    }
    
    // Add to end.
    public void append (Cell cell) {
        list[size] = cell;
        size++;
    }
    
    // Remove a given cell.
    public void remove (Cell cell) {
        boolean shift = false;
                
        for (int i = 0; i < size; i++) {
            if (shift) {
                list[i - 1] = list[i];
            } else if (list[i] == cell) {
                list[i] = null;
                shift = true;
            }
        }
        
        size--;
    }
    
    // Linear search for a cell.
    public boolean doesInclude (Cell cell) {
        for (int i = 0; i < size; i++) {
            if (list[i] == cell)
                return true;
        }
        
        return false;
    }
}
