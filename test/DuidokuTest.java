import Logic.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DuidokuTest {

    @Test
    public void setDuidokuTablePos() {
        User user = new User("David");
        Duidoku duidoku = new Duidoku(user);
        int row = 1, col = 3;
        duidoku.setDuidokuTablePos(row,col,5);
        assertEquals(5,duidoku.getBoard()[row][col]);
    }

    @Test
    public void com_turn() {
        User user = new User("David");
        Duidoku duidoku = new Duidoku(user);
        assertTrue(duidoku.com_turn());
    }

    @Test
    public void checkIfAcceptable() {
        User user = new User("David");
        Duidoku duidoku = new Duidoku(user);
        assertTrue(duidoku.checkIfAcceptable(1,3));
    }

    @Test
    public void checkAllCells() {
        User user = new User("David");
        Duidoku duidoku = new Duidoku(user);
        duidoku.checkAllCells();
        for (int i = 0;i<4; i++) {
            for (int j = 0; j < 4; j++)
                assertFalse(duidoku.getBlackColor()[i][j]);
        }
    }

    @Test
    public void getFilledPosOfArray() {
        User user = new User("David");
        Duidoku duidoku = new Duidoku(user);
        duidoku.setDuidokuTablePos(2,1,2);
        duidoku.setDuidokuTablePos(3,2,3);
        assertEquals(2,duidoku.getFilledPosOfArray());
    }
}