package logic;

import java.util.ArrayList;

/**
 * The ColorArea class implements one of the killer Sudoku color areas.
 * @author Velisarios
 */
public class ColorArea {
    /**
     * Every box (cell) of the board belongs to a specific color area.For that reason in every color area there is an ArrayList of boxes called
     * ColorAreaBoxes where every box of the color area is placed.Also every color area has its own sum and color and they are
     * stored in the sum and color variables respectively.The color variable helps us to distinguish this color area from other color areas
     * when they appear on the panel of the killer sudoku.
     */
    private ArrayList<Box> ColorAreaBoxes;
    private int sum ;
    private String color;

    /**
     * ColorArea constructor that creates a new color area with an empty ArrayList of boxes.
     */
    public ColorArea(){
        ColorAreaBoxes = new ArrayList<>();
    }

    /**
     * This method is used to add a box into the color area.
     * @param box This parameter is the box to be added into the ColorAreaBoxes ArrayList.
     */
    public void addBox(Box box){
        ColorAreaBoxes.add(box);
    }

    /**
     * This method returns all the boxes that belong to this specific color area.
     * @return ArrayList This returns the color area's ArrayList of boxes.
     */
    public ArrayList<Box> getColorAreaBoxes() {
        return ColorAreaBoxes;
    }

    /**
     * This method returns a box that exists at a specific position inside the color area's ArrayList of boxes.
     * @param boxPos This parameter is used to understand which box should be returned.
     * @return Box This returns a box that that belongs to this specific color area.
     */
    public Box getColorAreaBox(int boxPos){
        return ColorAreaBoxes.get(boxPos);
    }

    /**
     * The setColorAreaBoxValue method is used to set the value of a box inside the color area.
     * @param boxPosition This parameter lets us understand what is the position of the box that its value will be set.
     * @param value This parameter represents the box's new value.
     * @param box This parameter represents the specific box that a new value will be given to.
     */
    public void setColorAreaBoxValue(int boxPosition, int value, Box box){
        box.setBoxValue(value);
        ColorAreaBoxes.set(boxPosition, box);
    }

    /**
     * This method makes a box of the specific color area empty or not compared to if the box haσ a value or not.
     * @param boxPosition This parameter represents the box's position that will be empty or not.
     * @param k           This parameter tells if the box should be empty.
     * @param box         This parameter is the box to which the notEmpty variable should be changed.
     */
    public void setColorAreaBoxNotEmptyVariable(int boxPosition, boolean k, Box box){
        box.setNotEmpty(k);
        ColorAreaBoxes.set(boxPosition, box);
    }

    /**
     * The getColorAreaSize method returns of how many boxes this specific color area consists of.
     * @return int This returns the size of the color area.
     */
    public int getColorAreaSize(){
        return ColorAreaBoxes.size();
    }

    /**
     * The setColorAreaSum method sets the sum that the color area should have.
     * @param sum This parameter represents the sum that this specific color area should have.
     */
    public void setColorAreaSum(int sum) {
        this.sum = sum;
    }

    /**
     * This method return the color area's sum.
     * @return int This returns the sum variable.
     */
    public int getColorAreaSum(){ return this.sum;}

    /**
     * The getSumOfBoxes method is used to calculate the sum of the values of every box inside the color area.
     * @return int This returns the sum of the values of every box.
     */
    public int getSumOfBoxes(){
        int sumOfBoxes = 0;
        for(int i = 0; i < ColorAreaBoxes.size(); i++){
            if(ColorAreaBoxes.get(i).getBoxValue() > 0) {
                sumOfBoxes = sumOfBoxes + ColorAreaBoxes.get(i).getBoxValue();
            }
        }

        return sumOfBoxes;
    }

    /**
     * This method checks if the sum of the values of every box insidet the color area is equal to the sum that the colorArea should have.
     * @return boolean This returns true if the sum of the values of all the boxes is equal to to the sum that the colorArea should have.
     */
    public boolean checkSumOfBoxes(){
        if(this.getSumOfBoxes() == this.sum)
            return true;
        return false;
    }

    /**
     * This method checks if the color area is full.A color area is full if every box, that exists in it, is not empty.
     * @return boolean This returns true if the specific color area is full.
     */
    public boolean checkIfColorAreaIsFull(){
        boolean full = true;
        for(int i = 0; i < ColorAreaBoxes.size(); i++){
            if(!ColorAreaBoxes.get(i).getNotEmpty()){
                full = false;
                break;
            }
        }
        return full;

    }

    /**
     * This method returns the color of the color area in a form of a string.
     * @return String This returns the color area's color.
     */
    public String getColorAreaColor(){
        return color;
    }

    /**
     * The setColorAreaColor is used to set the color area's color.
     * @param aColor This parameter represents the color that the color area should have.
     */
    public void setColorAreaColor(String aColor){
        this.color = aColor;
    }
}
