package logic;

/**
 * The SudokuLogic class implements the logic of the sudoku game
 * @author Velisarios 
 */
public class SudokuLogic {
    /**
     * The variable board is the board where every check is being done by the program in order to test the sudoku rules.
     * The other variables have to do with the size of the boar and the root of its size.
     * Also there is a user object in order to initialize its statistics for the game.
     */
    private  int[][] board;
    private int sizeOfBoard;
    private double rootOfSizeOfBoard;


    private User user;

    /**
     * Constructor that initializes the board compared to what version of the game the user has chosen to play.
     * Example:If the user chooses to play classic sudoku then the size will be 9.If duidoku version is chosen the size is 4.
     * Also the root is being initialized from the size and every position of the board gets the value zero which means that there is no number
     * in an of its cells.
     * @param sizeOfBoard
     */
    public SudokuLogic(int sizeOfBoard){
        this.sizeOfBoard = sizeOfBoard;
        board = new int[sizeOfBoard][sizeOfBoard];
        rootOfSizeOfBoard = Math.sqrt(sizeOfBoard);
        for (int i = 0; i < sizeOfBoard; i++) {
            for (int j = 0; j < sizeOfBoard; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    /**
     * Τhis method returns the board of the game.
     * @return int[][] This returns a two dimension array of integers.
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * The setBoard method sets a specific value into the cell that exists in the i,j position of the board.
     * @param i The row to which the value will be set
     * @param j The column to which the value will be set
     * @param value This is the number that will be set to the i j position
     */
    public void setBoard(int i, int j, int value){
        board[i][j] = value;
    }


    /**
     * This method checks if a number can be placed at the specific row of the board.
     * @param row  the row to check if the number can be placed
     * @param number the number to be checked if can be placed
     * @return boolean This returns true if the number can be placed at the specific row.
     */
    public boolean isInRow(int row, int number){
        for(int i = 0; i < sizeOfBoard; i++){
            if(board[row][i] == number)
                return true;

        }
        return false;
    }

    /**
     * This This method checks if a number can be placed at the specific column of the board.
     * @param col  the column to check if the number can be placed
     * @param number the number to be checked if can be placed
     * @return boolean  This returns true if the number can be placed at the specific column.
     */
    public boolean isInCol(int col, int number){
        for(int i = 0; i < sizeOfBoard; i++){
            if(board[i][col] == number){
                return true;
            }
        }

        return false;
    }

    /**
     * This method checks if the number can be placed at the specific 3x3 area of the board basd on the row and column.
     * @param row The row to check if the number can be placed
     * @param col The column to check if the number can be placed
     * @param number The number to be checked if can be placed at the 3x3 area
     * @return boolean This returns true if the number can be placed there.
     */
    public boolean isInBox(int row, int col, int number){

        int  r = row - row % (int)rootOfSizeOfBoard;
        int c = col - col % (int)rootOfSizeOfBoard;

        for(int i = r; i <  r + (int)rootOfSizeOfBoard; i++){
            for(int j = c; j < c + (int)rootOfSizeOfBoard; j++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This method checks if the number obeys in the above constraints which are basically the rules of classic sudoku.
     * @param row The row to check if the number can be placed
     * @param col The column to check if the number can be placed
     * @param number This parameter represents the value of the number
     * @return boolean This returns true if the number can be placed at the specific cell based on the rules
     */
    public boolean isOk(int row, int col, int number){
        return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row , col, number);
    }

    /** This method returns the user of the game.
     * @return User This is the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * This method sets user object.
     * @param aUser This is the user object.
     */
    public void setUser(User aUser){
        this.user = aUser;
    }


}
