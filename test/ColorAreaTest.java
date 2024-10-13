import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import logic.*;
public class ColorAreaTest {

    @Test
    public void getColorAreaBox() {
        Box box1 = new Box(0,0,10);
        Box box2 = new Box(1,1,20);
        Box box3 = new Box(2,2,30);
        ColorArea colorArea = new ColorArea();
        colorArea.addBox(box1);
        colorArea.addBox(box2);
        colorArea.addBox(box3);
        assertSame(box2, colorArea.getColorAreaBox(1));
    }

    @Test
    public void getColorAreaColor() {
        ColorArea colorArea = new ColorArea();
        colorArea.setColorAreaColor("#660066");
        assertEquals("#660066", colorArea.getColorAreaColor());
    }

    @Test
    public void getColorAreaSize() {
        Box box1 = new Box(0,0,10);
        Box box2 = new Box(1,1,20);
        Box box3 = new Box(2,2,30);
        ColorArea colorArea = new ColorArea();
        colorArea.addBox(box1);
        colorArea.addBox(box2);
        colorArea.addBox(box3);
        assertEquals(3, colorArea.getColorAreaSize());
    }

    @Test
    public void getColorAreaSum() {
        ColorArea colorArea = new ColorArea();
        colorArea.setColorAreaSum(12);
        assertEquals(12, colorArea.getColorAreaSum());
    }

    @Test
    public void getSumOfBoxes() {
        Box box1 = new Box(0,0,10);
        Box box2 = new Box(1,1,20);
        Box box3 = new Box(2,2,30);
        ColorArea colorArea = new ColorArea();
        colorArea.addBox(box1);
        colorArea.addBox(box2);
        colorArea.addBox(box3);
        assertEquals(60, colorArea.getSumOfBoxes());


        colorArea.setColorAreaBoxValue(1,-10,box2);
        assertEquals(40, colorArea.getSumOfBoxes());
    }

    @Test
    public void checkSumOfBoxes() {
        Box box1 = new Box(0,0,10);
        Box box2 = new Box(1,1,20);
        Box box3 = new Box(2,2,30);
        ColorArea colorArea = new ColorArea();
        colorArea.addBox(box1);
        colorArea.addBox(box2);
        colorArea.addBox(box3);
        colorArea.setColorAreaSum(60);
        assertEquals(true, colorArea.checkSumOfBoxes());

        colorArea.setColorAreaSum(50);
        assertEquals(false, colorArea.checkSumOfBoxes());
    }

    @Test
    public void checkIfColorAreaIsFull() {
        Box box1 = new Box(0,0,10);
        Box box2 = new Box(1,1,20);
        Box box3 = new Box(2,2,30);
        ColorArea colorArea = new ColorArea();
        colorArea.addBox(box1);
        colorArea.addBox(box2);
        colorArea.addBox(box3);
        box1.setNotEmpty(true);
        box2.setNotEmpty(true);
        box3.setNotEmpty(true);
        assertEquals(true, colorArea.checkIfColorAreaIsFull());
        box2.setNotEmpty(false);
        assertEquals(false, colorArea.checkIfColorAreaIsFull());
    }
}