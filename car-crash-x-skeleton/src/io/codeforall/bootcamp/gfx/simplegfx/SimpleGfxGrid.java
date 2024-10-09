package io.codeforall.bootcamp.gfx.simplegfx;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import io.codeforall.bootcamp.grid.Grid;
import io.codeforall.bootcamp.grid.position.GridPosition;

public class SimpleGfxGrid implements Grid {

    private static Screen screen;

    public static final int PADDING = 10;
    private int cols;
    private int rows;

    public SimpleGfxGrid(int cols, int rows){
        //throw new UnsupportedOperationException();
         this.cols = cols;
         this.rows = rows;
    }

    /**
     * @see Grid#init()
     */
    @Override
    public void init() {
        //throw new UnsupportedOperationException();
        screen = TerminalFacade.createScreen();

        // set the grid size
        screen.getTerminal().setCursorVisible(false); // Not Working
        screen.getTerminal().getTerminalSize().setColumns(cols);
        screen.getTerminal().getTerminalSize().setRows(rows);

        // display the grid
        screen.startScreen();
    }

    /**
     * @see Grid#getCols()
     */
    @Override
    public int getCols() {
        //throw new UnsupportedOperationException();
        return cols;
    }

    /**
     * @see Grid#getRows()
     */
    @Override
    public int getRows() {
        //throw new UnsupportedOperationException();
        return rows;
    }

    /**
     * Obtains the width of the grid in pixels
     * @return the width of the grid
     */
    public int getWidth() {
        throw new UnsupportedOperationException();
    }

    /**
     * Obtains the height of the grid in pixels
     * @return the height of the grid
     */
    public int getHeight() {
        throw new UnsupportedOperationException();
    }

    /**
     * Obtains the grid X position in the SimpleGFX canvas
     * @return the x position of the grid
     */
    public int getX() {
        throw new UnsupportedOperationException();
    }

    /**
     * Obtains the grid Y position in the SimpleGFX canvas
     * @return the y position of the grid
     */
    public int getY() {
        throw new UnsupportedOperationException();
    }

    /**
     * Obtains the pixel width and height of a grid position
     * @return
     */
    public int getCellSize() {
        //throw new UnsupportedOperationException();
        return 20;
    }

    /**
     * @see Grid#makeGridPosition()
     */
    @Override
    public GridPosition makeGridPosition() {
        //throw new UnsupportedOperationException();
        return new SimpleGfxGridPosition(this);
    }

    /**
     * @see Grid#makeGridPosition(int, int)
     */
    @Override
    public GridPosition makeGridPosition(int col, int row) {
        //throw new UnsupportedOperationException();
        return new SimpleGfxGridPosition(col, row, this);
    }

    /**
     * Auxiliary method to compute the y value that corresponds to a specific row
     * @param row index
     * @return y pixel value
     */
    public int rowToY(int row) {
        throw new UnsupportedOperationException();
    }

    /**
     * Auxiliary method to compute the x value that corresponds to a specific column
     * @param column index
     * @return x pixel value
     */
    public int columnToX(int column) {
        throw new UnsupportedOperationException();
    }
}
