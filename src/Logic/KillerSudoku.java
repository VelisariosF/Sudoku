package Logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class implements the killer sudoku version of the sudoku game.
 * @author Velisarios 
 */
public class KillerSudoku extends SudokuLogic {
    /**
     * The killer sudoku version has its own puzzles.Every puzzle is stored in an ArrayList of characters.There are ten different puzzles.Therefore
     * there are ten different ArrayLists of characters called killerPuzzles.There is also another ArrayList of integers called availableKillerPuzzles
     * where the puzzles, that the user has not yet solve, are stored.And there is one more variable called killerPuzzle which represents the random
     * puzzle that is being given to the user to try to solve in case the choose to play killer sudoku.Finally, an object of the Initialize is used
     * in order to initialize the killerPuzzles ArrayLists and the KillerSudoku class has an ArrayList of ColorArea called ColorAreas which basically is
     * holds all the color areas that will be shown on the panel of the killer sudoku version.
     */
    private ArrayList<ColorArea> ColorAreas;
    private ArrayList<Character>[] killerPuzzles = new ArrayList[10];
    private ArrayList<Integer> availableKillerPuzzles;
    private Initialize initialize;
    private int killerPuzzle;

    /**
     * The KillerSudoku constructor gets the user as parameter in order to save their statistics for the game (if it isn't a guest player).Also it creates a two dimensional
     * board of integers with a size of 9 rows and 9 columns.The board is then inherited from the SudokuLogic.Then it initializes the three ArraiLists.
     * The killerPuzzles ArrayList is being initialized by the Initialize object.Moreover the constructor adds all the numbers of every puzzle
     * inside the availableKillerPuzzles arrayList and creates a new ColorAreas ArrayList.It also excludes the puzzles, that the user has solved
     * for the killer sudoku (if it isn't a guest player),from the availableClassicPuzzles.
     * @param aUser This parameter is used to understand to whom user the statistics should be saved to.
     */
    public KillerSudoku(User aUser){
        super(9);
        initialize = new Initialize(false, true);
        setUser(aUser);
        ColorAreas = new ArrayList<>();
        availableKillerPuzzles = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            availableKillerPuzzles.add(i);
        }

        killerPuzzles = initialize.getKillerPuzzles();
        if (!User.isGuest()) {
            if (getUser().getKillerPuzzlesPlayed().size() != 10)
                excludeKillerPlayedPuzzles();
            else {
                getUser().getKillerPuzzlesPlayed().clear();
                getUser().insertStatisticsInTheFile();
            }
        }
    }

    /**
     * This method returns all the color areas that a specific killer sudoku puzzle has.
     * @return ArrayList This returns the ColorAreas ArrayList.
     */
    public ArrayList<ColorArea> getColorAreas() {
        return ColorAreas;
    }

    /**
     * This method sets the value of the the box that belongs to a specific color area.
     * @param row This is the row that the box is placed inside the board.
     * @param col This is the column that the box is placed inside the board.
     * @param value This is the new value of the box.
     * @param notEmpty This is the boolean variable that tells if the box is empty or not based on the fact that the box's value has been erased
     *                  or not.
     */
    public void setKillerColorAreaBoxValue(int row, int col, int value, boolean notEmpty) {
        ColorArea colorArea = ColorAreas.get(checkColorArea(row, col));
        for (int j = 0; j < colorArea.getColorAreaSize(); j++) {
            if (colorArea.getColorAreaBox(j).getBoxCoordinateI() == row && colorArea.getColorAreaBox(j).getBoxCoordinateJ() == col) {
                colorArea.setColorAreaBoxValue(j, value, colorArea.getColorAreaBox(j));
                colorArea.setColorAreaBoxNotEmptyVariable(j, notEmpty, colorArea.getColorAreaBox(j));
            }
        }
    }

    /**
     * This method returns how many color areas the killer sudoku panel has.
     * @return int This returns the size of the ColorAreas ArrayList.
     */
    public int getColorAreasSize(){
        return ColorAreas.size();
    }

    /**
     * This method is used to initialize the board of the game and all the color areas along with they're sums,colors and box's
     * based on the random puzzle that has been selected.
     */
    public void chooseRandomKillerPuzzle(){
        Random r = new Random();
        int kK = r.nextInt(availableKillerPuzzles.size()), j = 0;
        killerPuzzle = availableKillerPuzzles.get(kK);
        ColorArea aColorArea;
        StringBuilder color;
        String sColor;
        while(j < killerPuzzles[killerPuzzle].size()){
            aColorArea = new ColorArea();
            color = new StringBuilder();
            while(killerPuzzles[killerPuzzle].get(j) != '/'){
                color.append(killerPuzzles[killerPuzzle].get(j));
                j++;
            }
            j = j + 1;
            while(killerPuzzles[killerPuzzle].get(j) != '-') {
                int boxI = Character.getNumericValue(killerPuzzles[killerPuzzle].get(j));
                int boxJ = Character.getNumericValue(killerPuzzles[killerPuzzle].get(j + 1));
                Box box = new Box(boxI, boxJ, 0);
                aColorArea.addBox(box);
                j = j + 2;
            }
            int s = Character.getNumericValue(killerPuzzles[killerPuzzle].get(j + 1)) * 10 + Character.getNumericValue(killerPuzzles[killerPuzzle].get(j + 2));
            aColorArea.setColorAreaSum(s);
            j = j + 3;

            sColor = color.toString();
            aColorArea.setColorAreaColor(sColor);
            this.ColorAreas.add(aColorArea);
        }
    }

    /**
     * This method is used to check to which color area the coordinate, described by the row and col variables, belongs to.
     * @param row This is the first parameter of the method.
     * @param col This is the second parameter of the method.
     * @return int This returns the position of the color area inside the ColorAreas ArrayList.
     */
    public int checkColorArea(int row, int col){
        int colorAreaPos = 0;
        for(int i = 0 ; i < ColorAreas.size(); i ++){
            ColorArea colorArea = ColorAreas.get(i);
            for(int j = 0; j < colorArea.getColorAreaSize(); j++ ){
                if(colorArea.getColorAreaBox(j).getBoxCoordinateI() == row && colorArea.getColorAreaBox(j).getBoxCoordinateJ() == col){
                    colorAreaPos = i;
                }
            }
        }
        return  colorAreaPos;
    }

    /**
     * This method checks if the sum of all the boxes inside a color area is equal to the sum that the specific color area must have.
     * @param colorAreaPos This parameter is represents the position of the color area, inside the ColorAreas ArrayList, that should be checked.
     * @return boolean This returns true if the sum of the values is equal to the sum of the color area and false otherwise.
     */
    public boolean checkIfTheSumIsValid(int colorAreaPos){
        if(ColorAreas.get(colorAreaPos).checkIfColorAreaIsFull()){
            return ColorAreas.get(colorAreaPos).checkSumOfBoxes();
        }
        return false;
    }

    /**
     * This method returns the number of the puzzle that has been randomly chosen for the user to solve.
     * @return int This returns the killerPuzzle value.
     */
    public int getKillerPuzzle() {
        return killerPuzzle;
    }

    /**
     * This method checks if the user has solved the killer puzzle that has been randomly chosen to solve.
     * In order for the user to solve a puzzle all the sums of the values of every box inside every color area must be the same as the sum
     * that every color area, inside the ColorAreas ArrayList, must have.
     * @return boolean This returns true if the user has solved the puzzle and false otherwise.
     */
    public boolean checkWinner(){
        int s = 0;
        for(ColorArea c : ColorAreas){
            if(c.checkSumOfBoxes())
                s++;
        }
        return s == getColorAreasSize();
    }

    /**
     * This method is used to remove from the available puzzles ArrayList the puzzles that the user has solved in order not to
     * choose again the same puzzles for the user to solve.
     */
    private void excludeKillerPlayedPuzzles(){
        for(int i = 0; i < availableKillerPuzzles.size(); i++){
            if(getUser().checkIfExistsInKillerPuzzlesPlayed(availableKillerPuzzles.get(i))){
                availableKillerPuzzles.remove(i);
                i--;
            }
        }
    }

    /**
     * This method returns all the puzzles that the user has not yet solve.
     * @return ArrayList This returns the availableKillerPuzzles ArrayList.
     */
    public ArrayList<Integer> getAvailableKillerPuzzles() {
        return availableKillerPuzzles;
    }

    /**
     * This method is used to add a color area inside the ColorAreas ArrayList.
     * @param aColorArea This parameter is the color area to be added to the color areas of the killer Sudoku.
     */
    public void addColorAreaToKillerColorAreas(ColorArea aColorArea){
        ColorAreas.add(aColorArea);
    }
}
