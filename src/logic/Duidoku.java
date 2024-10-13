package logic;

/**
 * This class implements the duidoku version of the game.
 * @author Velisarios 
 */

public class Duidoku extends SudokuLogic {
    /**
     * The dudidoku class consists of two 4x4 arrays boolean arrays.The checkNotEmptyPosArray is used to understand if a position, inside the 4x4 board
     * of integers, is empty.If there is an empty position inside the board then the same pos in the checkNotEmptyPosArray is false.
     * Also the blackColor array is used to check if a value can be entered at a specific position inside the 4x4 board o integers.
     * If a position at the blackColor array is true it means that no number can be entered, at the same position,inside the board.
     * Moreover there are three more variables.The comRow and comCol determine the coordinate in which the com has placed its number
     * inside the board and the comValue is the value of the number that the com has placed inside the board.
     */
    private boolean[][] checkNotEmptyPosArray;
    private boolean[][] blackColor;
    private  int comRow, comCol, comValue;

    /**
     * The Duidoku constructor initializes the board of the SudokuLogic with a size of 4 rows and 4 columns and the board is being inherited.
     * Also it initializes the other two boolean boards.
     */
    public Duidoku(User aUser) {
        super(4);
        setUser(aUser);
        this.checkNotEmptyPosArray = new boolean[4][4];
        this.blackColor = new boolean[4][4];
    }

    /**
     * This method is used to give a value to a cell inside the board with the row,col coordinates. Also the same position inside the checkNotEmpty
     * board is changed to true because a value has been given to the same position at the 4x4 duidoku board. and moreover it calls the checkAlCells method.
     * @param row This parameter represents the row to which the value should be placed inside the board.
     * @param col This parameter represents the column to which the value should be placed inside the board.
     * @param value This parameter represents the value that the specific cell will get.
     */
    public void setDuidokuTablePos(int row, int col, int value) {
        setBoard(row, col, value);
        this.checkNotEmptyPosArray[row][col] = true;
        this.checkAllCells();
    }

    /**
     * The com_turn method is used to get the com to give a value to one of the cells inside the duidoku board.First it checks which positions inside the
     * duidoku board are empty,the it checks which numbers from 1 to 4 can be placed at this specific pos.
     * @return boolean This returns true if the com managed to find a pos to place a number to, otherwise it returns false.
     */
    public boolean com_turn() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!this.checkNotEmptyPosArray[i][j]) {
                    for (int k = 1; k < 5; k++) {
                        if (isOk(i, j, k)) {
                            this.setDuidokuTablePos(i, j, k);
                            comCol = j;
                            comValue = k;
                            comRow = i;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method returns the row that the com has placed its value to.
     * @return int This returns the comRow variable.
     */
    public int getComRow() {
        return comRow;
    }

    /**
     * This method returns the column that the com has placed its value to.
     * @return int This returns the comCol variable.
     */
    public int getComCol() {
        return comCol;
    }

    /**
     * This method returns the value that the com has given to a scpecific pos inside the duidoku board.
     * @return int This returns the comValue variable.
     */
    public int getComValue() {
        return comValue;
    }

    /**
     * This method checks which number from 1 to 4 can be placed, at the position with coordinates row col, inside the duidoku board.
     * @param row This parameter represents the row to be checked if the value can or cannot be placed to.
     * @param col This parameter represents the column to be checked if the value can or cannot be placed to.
     * @return boolean This returns true if a value from 1 to 4 can be placed at the specific position and false otherwise.
     */
    public boolean checkIfAcceptable(int row, int col){
        for(int i = 1; i < 5; i++){
            if(isOk(row, col, i)){
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks which cells inside the duidoku board cannot accept any value and updates the positions inside the blackColor and
     * checkNotEmptyPosArray arrays.
     */
    public void checkAllCells(){

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(!this.checkNotEmptyPosArray[i][j]) {
                    if(!checkIfAcceptable(i,j)){
                        this.blackColor[i][j] = true;
                        this.checkNotEmptyPosArray[i][j] = true;
                    }
                }

            }
        }

    }

    /**
     * This method returns all the positions that can and cannot accept a value.
     * @return boolean This returns a two dimensional boolean array.
     */
    public boolean[][] getBlackColor(){
        return blackColor;
    }

    /**
     * This method is used to set a position, with coordinates row col, empty or not inside the checkNotEmptyArray based on what value this cell ,
     * inside the duidoku board, has.
     * @param row This parameter is the row to which the value should be placed to.
     * @param col This parameter is the column to which the value shoud be place to.
     * @param value This parameter is the value that the position at the checkNotEmptyPosArray will get.
     */
    public void setNotEmptyPosArray(int row, int col, boolean value){
        this.checkNotEmptyPosArray[row][col] = value;
    }

    /**
     * This method checks how many cells inside the duidoku board are empty or not by using the checkNotEmptyPosArray array.
     * @return int This returns the number of the positions, inside the duidoku board, that are empty.
     */
    public int getFilledPosOfArray(){
        int s = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(this.checkNotEmptyPosArray[i][j])
                    s++;
            }
        }
        return s;
    }
}
