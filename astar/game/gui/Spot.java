package astar.game.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

// Used to colour a visual grid cell.
class Spot extends JPanel {
    private Color colour;

    public Spot (Color colour) {
        this.colour = colour;
    }

    protected void paintComponent (Graphics g) {
        super.paintComponent(g);

        g.setColor(colour);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
