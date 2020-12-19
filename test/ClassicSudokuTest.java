import Logic.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ClassicSudokuTest {


    @Test
    public void chooseRandomClassicPuzzle() {
        User user = new User("David");
        ClassicSudoku classicSudoku = new ClassicSudoku(user);
        classicSudoku.chooseRandomClassicPuzzle();
        assertTrue(classicSudoku.getAvailableClassicPuzzles().contains(classicSudoku.getClassicPuzzle()));
        classicSudoku.chooseRandomClassicPuzzle();
        assertTrue(classicSudoku.getAvailableClassicPuzzles().contains(classicSudoku.getClassicPuzzle()));
        classicSudoku.chooseRandomClassicPuzzle();
        assertTrue(classicSudoku.getAvailableClassicPuzzles().contains(classicSudoku.getClassicPuzzle()));

    }

    @Test
    public void checkWinner() {
        User user = new User("David");
        ClassicSudoku classicSudoku = new ClassicSudoku(user);
        assertEquals(false, classicSudoku.checkWinner());
    }


}