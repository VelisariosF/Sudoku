package Logic;

/**
 * This class implements one the 81 boxes of the sudoku field.Basically every cell of the board is a box.
 * @author Velisarios 
 *
 */
public class Box {
    /**
     * Every box has its own coordinates, that represent its position in the board.The variables coordinateI and coordinateJ are used to keep box's
     * position in the board.Also every box haw its own value saved in the boxValue variable.Moreover, there is boolean variable called notEmtpy
     * which basically turns true, if there is a value in the box, and false otherwise.
     */
    private  int coordinateI ,coordinateJ;//Each box has its own coordinates i,j; that reprsent its position in the
    private int boxValue;                       //field.Als it
    private boolean notEmpty;//if there is no value in the box then notEmpty = false

    /**
     * Empty constructor that creates a box object.
     */
    public Box(){
        //
    }

    /**
     * This box constructos takes as parameters the box's coordinates and its value.
     * @param i This parameter represents the box's row position in the board.
     * @param j This parameter represents the box's col position in the board.
     * @param aValue This paramter represents the box's value;
     */
    public Box(int i, int j, int aValue){
        this.coordinateI = i;
        this.coordinateJ = j;
        this.boxValue = aValue;
        this.notEmpty = false;
    }

    /**
     * The getBoxCoordinateI method returns one of the box's coordinate.
     * @return int This returns coordinateI.
     */
    public int getBoxCoordinateI() {

        return coordinateI;
    }

    /**
     * The getBoxCoordinateJ method returns one of the box's coordinate.
     * @return int This returns coordinateJ.
     */
    public int getBoxCoordinateJ() {

        return coordinateJ;
    }

    /**
     * The getBoxValue method returns the box's value.
     * @return int This returns the boxValue variable.
     */
    public int getBoxValue() {
        return boxValue;
    }

    /**
     * The setBoxValue method is used to give a value to a box.
     * @param value This parameter is the value that will be given in the box.
     */
    public void setBoxValue(int value){
        this.boxValue = value;
    }

    /**
     * The getNotEmpty method returns whether or not a box is empty(has no value).
     * @return boolean This returns true if a box has a value in it or false otherwise.
     */
    public boolean getNotEmpty(){
        return this.notEmpty;
    }

    /**
     * The setNotEmpty method is used to make a box empty or not.
     * @param k This parameter tells if the specific box must be turned empty or not.
     */
    public void setNotEmpty(boolean k){
        this.notEmpty = k;

    }
}
