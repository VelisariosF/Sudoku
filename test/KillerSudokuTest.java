import logic.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KillerSudokuTest {


    @Test
    public void chooseRandomKillerPuzzle() {
        User user = new User("David");
        KillerSudoku killerSudoku = new KillerSudoku(user);

        killerSudoku.chooseRandomKillerPuzzle();
        assertTrue(killerSudoku.getAvailableKillerPuzzles().contains( killerSudoku.getKillerPuzzle()));
        killerSudoku.chooseRandomKillerPuzzle();
        assertTrue(killerSudoku.getAvailableKillerPuzzles().contains( killerSudoku.getKillerPuzzle()));
        killerSudoku.chooseRandomKillerPuzzle();
        assertTrue(killerSudoku.getAvailableKillerPuzzles().contains( killerSudoku.getKillerPuzzle()));

    }

    @Test
    public void checkColorArea() {
        User user = new User("David");
        Box box11 = new Box(1,1,11);
        Box box12 = new Box(1,2,12);
        Box box13 = new Box(1,3,13);
        Box box21 = new Box(2,1,21);
        Box box22 = new Box(2,2,22);
        Box box23 = new Box(2,3,23);
        ColorArea colorArea1 = new ColorArea();
        ColorArea colorArea2 = new ColorArea();
        colorArea1.addBox(box11);
        colorArea1.addBox(box12);
        colorArea1.addBox(box13);
        colorArea2.addBox(box21);
        colorArea2.addBox(box22);
        colorArea2.addBox(box23);
        KillerSudoku killerSudoku = new KillerSudoku(user);
        killerSudoku.addColorAreaToKillerColorAreas(colorArea1);
        killerSudoku.addColorAreaToKillerColorAreas(colorArea2);
        assertEquals(1, killerSudoku.checkColorArea(2,1));
        assertEquals(0, killerSudoku.checkColorArea(1,1));
        assertNotEquals(1,killerSudoku.checkColorArea(1,2));
        assertNotEquals(0,killerSudoku.checkColorArea(2,3));


    }

    @Test
    public void checkWinner() {
        User user = new User("David");
        Box box11 = new Box(1,1,11);
        Box box12 = new Box(1,2,12);
        Box box13 = new Box(1,3,13);
        Box box21 = new Box(2,1,21);
        Box box22 = new Box(2,2,22);
        Box box23 = new Box(2,3,23);
        ColorArea colorArea1 = new ColorArea();
        ColorArea colorArea2 = new ColorArea();
        colorArea1.addBox(box11);
        colorArea1.addBox(box12);
        colorArea1.addBox(box13);
        colorArea1.setColorAreaSum(36);
        colorArea2.addBox(box21);
        colorArea2.addBox(box22);
        colorArea2.addBox(box23);
        colorArea2.setColorAreaSum(66);
        KillerSudoku killerSudoku = new KillerSudoku(user);
        killerSudoku.addColorAreaToKillerColorAreas(colorArea1);
        killerSudoku.addColorAreaToKillerColorAreas(colorArea2);
        assertTrue(killerSudoku.checkWinner());
        colorArea2.setColorAreaSum(23);
        assertFalse(killerSudoku.checkWinner());

    }

    @Test
    void checkIfTheSumIsValid() {
        User user = new User("David");
        Box box11 = new Box(1,1,11);
        box11.setNotEmpty(true);
        Box box12 = new Box(1,2,12);
        box12.setNotEmpty(true);
        Box box13 = new Box(1,3,13);
        box13.setNotEmpty(true);
        Box box21 = new Box(2,1,21);
        box21.setNotEmpty(true);
        Box box22 = new Box(2,2,22);
        box22.setNotEmpty(true);
        Box box23 = new Box(2,3,23);
        box23.setNotEmpty(true);
        ColorArea colorArea1 = new ColorArea();
        ColorArea colorArea2 = new ColorArea();
        colorArea1.addBox(box11);
        colorArea1.addBox(box12);
        colorArea1.addBox(box13);
        colorArea1.setColorAreaSum(36);
        colorArea2.addBox(box21);
        colorArea2.addBox(box22);
        colorArea2.addBox(box23);
        colorArea2.setColorAreaSum(70);
        KillerSudoku killerSudoku = new KillerSudoku(user);
        killerSudoku.addColorAreaToKillerColorAreas(colorArea1);
        killerSudoku.addColorAreaToKillerColorAreas(colorArea2);

        assertTrue(killerSudoku.checkIfTheSumIsValid(0));
        assertFalse(killerSudoku.checkIfTheSumIsValid(1));

    }
}