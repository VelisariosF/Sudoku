import Logic.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {
    @Test
    public void getBoxCoordinateI() {
        Box box = new Box(2, 3, 12);
        assertEquals(2, box.getBoxCoordinateI());
    }

    @Test
    public void getBoxCoordinateJ() {
        Box box = new Box(2, 3, 12);
        assertEquals(3, box.getBoxCoordinateJ());
    }

    @Test
    public void getBoxValue() {
        Box box = new Box(2, 3, 12);
        assertEquals(12, box.getBoxValue());
    }

    @Test
    public void getNotEmpty() {
        Box box = new Box(2, 3, 12);
        assertEquals(false, box.getNotEmpty());
    }

    @Test
    public void setBoxValue() {
        Box box = new Box();
        box.setBoxValue(15);
        assertEquals(15, box.getBoxValue());
    }
}