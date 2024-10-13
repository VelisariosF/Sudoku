package logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class implements the classic sudoku version of the sudoku game.
 * @author Velisarios 
 */
public class ClassicSudoku extends SudokuLogic {
    /**
     * The classic sudoku version has its own puzzles.Everey puzzle is stored in an ArrayList of characters.There are ten different puzzles.Therefore
     * there are ten different ArrayLists of characters called classicPuzzles.There is also another ArrayList of integers called availableClassicPuzzles
     * where the puzzles, that the user has not yet solve, are stored.And there is one more variable called classicPuzzle which represents the random
     * puzzle that is being given to the user to try to solve in case the choose to play classic sudoku.Finally, an object of the Initialize
     * in order to initialize the classicPuzzles ArrayLists.
     */
    private ArrayList<Character>[] classicPuzzles = new ArrayList[10];
    private ArrayList<Integer> availableClassicPuzzles;
    private Initialize initialize;
    private int classicPuzzle;

    /**
     * The ClassicSudoku constructor gets the user as parameter in order to save their statistics for the game (if it isn't a guest player).Also it creates
     * a two dimensional board of integers with a size of 9 rows and 9 columns.The board is then inherited from the SudokuLogic.Then it initializes
     * the two ArrayLists. The classicPuzzles ArrayList is being initialized by the Initialize object.Moreover the constructor adds all the numbers
     * of every puzzle inside the availableClassicPuzzles arrayList.It also excludes the puzzles, that the user has solved for the classic sudoku (if it isn't
     * a guest player), from the availableClassicPuzzles.
     * @param aUser This parameter is used to understand to whom user the statistics should be saved to.
     */
    public ClassicSudoku(User aUser) {
        super(9);
        initialize = new Initialize(true, false);
        setUser(aUser);
        availableClassicPuzzles = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            availableClassicPuzzles.add(i);
        }
        classicPuzzles = initialize.getClassicPuzzles();
        if (!User.isGuest()) {
            if (getUser().getClassicPuzzlesPlayed().size() != 10)
                excludeClassicPlayedPuzzles();
            else {
                getUser().getClassicPuzzlesPlayed().clear();
                getUser().insertStatisticsInTheFile();
            }
        }
    }

    /**
     * This method is used to initialize the board of the game based on the random puzzle that has been selected.
     */
    public void chooseRandomClassicPuzzle(){
        Random r = new Random();
        int kC = r.nextInt(availableClassicPuzzles.size());
        classicPuzzle = availableClassicPuzzles.get(kC);
        int col = 0;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++){
                if(classicPuzzles[classicPuzzle].get(col) >= '1' && classicPuzzles[classicPuzzle].get(col) <= '9' ){
                    int a= Character.getNumericValue(classicPuzzles[classicPuzzle].get(col));
                    this.setBoard(i, j, a);
                }else if(classicPuzzles[classicPuzzle].get(col) >= '-'){
                    this.setBoard(i, j, 0);
                }
                col++;
            }
        }
    }

    /**
     * This method checks if the user has solved the classic puzzle that has been randomly chosen to solve.
     * In order for the user to solve a puzzle they must fill all the blank cells with the proper numbers.
     * @return boolean This returns true if the user has solved the puzzle and false otherwise.
     */
    public boolean checkWinner(){
        int s = 0;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(getBoard()[i][j] > 0){
                    s++;
                }
            }
        }
        return s == 81;
    }

    /**
     * This method returns the number of the puzzle that has been randomly chosen for the user to solve.
     * @return int This returns the classicPuzzle value.
     */
    public int getClassicPuzzle() {
        return classicPuzzle;
    }

    /**
     * This method is used to remove from the available puzzles ArrayList the puzzles that the user has solved in order not to
     * choose again the same puzzles for the user to solve.
     */
    private void excludeClassicPlayedPuzzles(){
        for(int i = 0; i < availableClassicPuzzles.size(); i++){
            if(getUser().checkIfExistsInClassicPuzzlesPlayed(availableClassicPuzzles.get(i))){
                availableClassicPuzzles.remove(i);
                i--;
            }
        }
    }

    /**
     * This method returns all the puzzles that the user has not yet solve.
     * @return ArrayList This returns the availableClassicPuzzles ArrayList.
     */
    public ArrayList<Integer> getAvailableClassicPuzzles() {
        return availableClassicPuzzles;
    }
}
