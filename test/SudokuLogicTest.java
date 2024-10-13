import logic.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuLogicTest {

    @Test
    public void getBoard() {
        SudokuLogic sudokuLogic = new SudokuLogic(9);
        assertEquals(sudokuLogic.getBoard(),sudokuLogic.getBoard());
    }

    @Test
    public void setBoard() {
        SudokuLogic sudokuLogic = new SudokuLogic(9);
        sudokuLogic.setBoard(2,3,5);
        assertEquals(5,sudokuLogic.getBoard()[2][3]);
    }

    @Test
    public void isInRow() {
        SudokuLogic sudokuLogic = new SudokuLogic(9);
        int row = 2;
        sudokuLogic.setBoard(row,3,5);
        assertTrue(sudokuLogic.isInRow(row,5));
        assertFalse(sudokuLogic.isInRow(row,2));
    }

    @Test
    public void isInCol() {
        SudokuLogic sudokuLogic = new SudokuLogic(9);
        int col = 5;
        sudokuLogic.setBoard(4,col,3);
        assertTrue(sudokuLogic.isInCol(col,3));
        assertFalse(sudokuLogic.isInCol(col,7));
    }

    @Test
    public void isInBox() {
        SudokuLogic sudokuLogic = new SudokuLogic(9);
        int row = 3, col = 6;
        sudokuLogic.setBoard(row,col,4);
        assertTrue(sudokuLogic.isInBox(row,col,4));
        assertFalse(sudokuLogic.isInBox(row,col,8));
    }

    @Test
    public void isOk() {
        SudokuLogic sudokuLogic = new SudokuLogic(9);
        int row = 2, col = 5;
        sudokuLogic.setBoard(row,3,5);
        assertFalse(sudokuLogic.isOk(row,col,5));
        assertTrue(sudokuLogic.isOk(row,col,2));
        sudokuLogic.setBoard(4,col,3);
        assertFalse(sudokuLogic.isOk(row,col,3));
        assertTrue(sudokuLogic.isOk(row,col,7));
        sudokuLogic.setBoard(row,col,4);
        assertFalse(sudokuLogic.isOk(row,col,4));
        assertTrue(sudokuLogic.isOk(row,col,8));
    }

}