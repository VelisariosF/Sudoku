package gui;

import logic.*;
import logic.Box;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ResourceBundle;
/** This is a GUI class. This class is the game panel for a Killer Sudoku game. This class is a GamePanel class model, it extends GamePanel.
 * @see GamePanel
 * @author Velisarios 
 */
public class KillerGamePanel extends  GamePanel {
    /** Variable killerSudoku is a KillerSudoku object ans is used to get the Killer Sudoku table and make the required checks.
     * @see KillerSudoku
     */
    private KillerSudoku killerSudoku;
    /** Variable isOk determines if user's input is acceptable (according to classic Sudoku rules). Variable sumIsOk determines if the sum of a colored area is the right one.
     */
    private boolean isOK, sumIsOk;
    /** Array sums is an array of JLabels. Each one of them is a sum of a colored area.
     */
    private JLabel[] sums;
    /** redColorExplain1, redColorExplain2, blackColorExplain1 and blackColorExplain2, sumRedColorExplain1, sumRedColorExplain2, sumBlackColorExplain1,
     * sumBlackColorExplain2 are JLabels used for clarifications for the game. Variables winLabel and killerTitle
     * are JLabels used to announce the end of the game or the name of the game respectively.
     */
    private JLabel killerTitle, redColorExplain1, redColorExplain2, blackColorExplain1, blackColorExplain2, winLabel, sumRedColorExplain1,
            sumRedColorExplain2, sumBlackColorExplain1, sumBlackColorExplain2;
    /** Variables killerTitleFont and winLabelFont are the Font for killerTitle and winLabel texts respectively.
     */
    private Font killerTitleFont, winLabelFont;
    /** Variables coordinateI and coordinateJ are used to take the row and column of the tile, in Sudoku board (array boxes from GamePanel), that each JLabel, from array
     * sums, is placed in. Variable number is user's input to a Sudoku board tile.
     */
    private int coordinateI, coordinateJ, number;

    /** Constructor that calls GamePanel's constructor with size 9, initializes variables killerSudoku and all the JLabel
     * and Fonts objects in this class. Also it calls private methods setColorAreasColors and setUpKillerGamePanel.
     * @param aUser User class object. The user that plays the game
     * @param bundle The resource bundle that loads the strings used by the project and which determines program's language
     */
    public KillerGamePanel(User aUser, ResourceBundle bundle) {
        super(9,'9', '1', 70, 40, 55,bundle);
        killerSudoku = new KillerSudoku(aUser);
        killerSudoku.chooseRandomKillerPuzzle();
        killerTitleFont = new Font("Arial", Font.BOLD, 20);
        winLabelFont = new Font("Arial", Font.BOLD, 25);
        killerTitle = new JLabel(bundle.getString("killerLabel")+'#'+(killerSudoku.getKillerPuzzle()+1));
        redColorExplain1 = new JLabel(bundle.getString("redLabel"));
        redColorExplain2 = new JLabel(bundle.getString("wrongNumber"));
        sumRedColorExplain1 = new JLabel(bundle.getString("redSumLabel"));
        sumBlackColorExplain1 = new JLabel(bundle.getString("blackSumLabel"));
        blackColorExplain1 = new JLabel(bundle.getString("blackLabel2"));
        blackColorExplain2 = new JLabel(bundle.getString("rightNumber"));
        sumRedColorExplain2 = new JLabel(bundle.getString("redSumLabel2"));
        sumBlackColorExplain2 = new JLabel(bundle.getString("blackSumLabel2"));
        winLabel = new JLabel(bundle.getString("finishMessage"));
        setColorAreasColors();
        setUpKillerGamePanel();
    }
    /** This method calls private method setAColorAreaColor for every colored area in Killer Sudoku board. Also it calls private method initTheKillerColorAreas.
     */
    private void setColorAreasColors(){
        for(int i = 0; i < killerSudoku.getColorAreasSize(); i++){
            setAColorAreaColor(killerSudoku.getColorAreas().get(i).getColorAreaColor(), killerSudoku.getColorAreas().get(i));
        }
        initTheKillerColorAreas();
    }
    /** This method is used to set the color to a colored area of the board.
     * @param aColor The hex code of a colored area'a color
     * @param aColorArea A colored area
     */
    private void setAColorAreaColor(String aColor, ColorArea aColorArea){
        for(int i = 0; i < aColorArea.getColorAreaSize(); i++){
            int coordinateI = aColorArea.getColorAreaBox(i).getBoxCoordinateI();
            int coordinateJ = aColorArea.getColorAreaBox(i).getBoxCoordinateJ();
            boxes[coordinateI][coordinateJ].setBackground(Color.decode(aColor));
        }
    }
    /** This method is used to initialize the Killer Sudoku Board. It add one JLabel from array sums to a cell of every colored area and sets cells FlowLayout to Left. Also
     * it sets JLabels's color to red. It also calls method setHintListener, from class GamePanel and private method setBoxListeners.
     */
    private void initTheKillerColorAreas(){
        sums = new JLabel[killerSudoku.getColorAreasSize()];
        for (int i = 0; i<sums.length;i++) {
            ColorArea colorArea  = killerSudoku.getColorAreas().get(i);
            sums[i] = new JLabel(String.valueOf(colorArea.getColorAreaSum()));
            sums[i].setForeground(Color.RED);
            int x = colorArea.getColorAreaBoxes().get(0).getBoxCoordinateI();
            int y = colorArea.getColorAreaBoxes().get(0).getBoxCoordinateJ();
            boxes[x][y].add(sums[i]);
            boxes[x][y].setLayout(new FlowLayout(FlowLayout.LEFT));
        }
        setBoxListeners();
        setHintListener(killerSudoku,48);
    }

    /**This method sets the size and position of all the JLabels variables in this class. Also it sets their visibility (winLabel false and the rest true) and sets
     * the font for killerTitle and winLabel. Also this method adds all the JLabels to the panel and sets the panel's background color.
     */
    private void setUpKillerGamePanel(){
        killerTitle.setFont(killerTitleFont);
        killerTitle.setBounds(253, 10, 500, 40);
        add(killerTitle);

        winLabel.setBounds(85, 538, 500, 40);
        winLabel.setFont(winLabelFont);
        winLabel.setVisible(false);
        add(winLabel);

        redColorExplain1.setBounds(77, 538, (int)(redColorExplain1.getText().length()*6.5), 20);
        redColorExplain1.setForeground(Color.RED);
        add(redColorExplain1);

        blackColorExplain1.setBounds(77, 555, blackColorExplain1.getText().length()*7, 20);
        add(blackColorExplain1);


        redColorExplain2.setBounds(77+Math.max(redColorExplain1.getWidth(),blackColorExplain1.getWidth()), 538, 150, 20);
        add(redColorExplain2);

        blackColorExplain2.setBounds(77+Math.max(redColorExplain1.getWidth(),blackColorExplain1.getWidth()), 555, 150, 20);
       // blackColorExplain2.setVisible(true);
        add(blackColorExplain2);

        sumRedColorExplain1.setBounds(320,538, 200, 20);
        sumRedColorExplain1.setForeground(Color.RED);
        add(sumRedColorExplain1);

        sumBlackColorExplain1.setBounds(320, 555, 200, 20);
        add(sumBlackColorExplain1);

        sumRedColorExplain2.setBounds(450, 538, 200,20);
        add(sumRedColorExplain2);

        sumBlackColorExplain2.setBounds(450, 555, 200,20);
        add(sumBlackColorExplain2);

        setBackground(Color.decode("#FFFFFF"));
    }

    /** This method adds a document listener to the document of every JTextField in array boxes (protected array from class GamePanel). If the user insert
     * a number then it check if it is acceptable for the board according to the Classic Sudoku rules. Also it store the number to board of killerSudoku
     * (if it isn't acceptable, its negative value) and calls method setNumberColor (GamePanel). Furthermore, If the game is completed, it calls private
     * method EndOfGameActions. If the user erases a number, it stores 0 to killerSudoku board and calls private method resetNumbersColor. Also in both
     * situations(insert or erase) it updates the Box object of the ColorArea object of killerSudoku, checks if the sum of the colored area is right and
     * sets the sum's label color (red for wrong and black for right).
     */
    private void setBoxListeners() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final int row = i, col = j;
                boxes[i][j].getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        number = (int)boxes[row][col].getText().charAt(0)-48;
                        isOK = killerSudoku.isOk(row,col,number);
                        if (!isOK){
                            number = number * (-1);
                        }
                        killerSudoku.setBoard(row, col, number);
                        setNumberColor(isOK, row, col,Color.BLACK);
                        killerSudoku.setKillerColorAreaBoxValue(row, col, number, true);
                        sumIsOk = killerSudoku.checkIfTheSumIsValid(killerSudoku.checkColorArea(row, col));
                        coordinateI = killerSudoku.getColorAreas().get(killerSudoku.checkColorArea(row, col)).getColorAreaBoxes().get(0).getBoxCoordinateI();
                        coordinateJ = killerSudoku.getColorAreas().get(killerSudoku.checkColorArea(row, col)).getColorAreaBoxes().get(0).getBoxCoordinateJ();
                        setSumColor(coordinateI,coordinateJ,sumIsOk);
                        if(killerSudoku.checkWinner()){
                            EndOfGameActions();
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        killerSudoku.setBoard(row,col, 0);
                        resetNumbersColor();
                        killerSudoku.setKillerColorAreaBoxValue(row, col, 0, false);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                });
            }
        }
    }

    /** This method is used to do all the required actions whenever the user completes a Killer Sudoku game. It sets the clarification JLabels invisible and winLabel to
     * visible in order announce the end of the game.Also it sets every JTextField in array boxes (protected array from GamePanel class) to not editable. Furthermore it
     * store user's statistics (if it isn't a guest player) and set hintButton to not enable.
     */
    private void EndOfGameActions(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                boxes[i][j].setEditable(false);

            }
        }
        redColorExplain1.setVisible(false);
        redColorExplain2.setVisible(false);
        blackColorExplain1.setVisible(false);
        blackColorExplain2.setVisible(false);
        sumRedColorExplain1.setVisible(false);
        sumRedColorExplain2.setVisible(false);
        sumBlackColorExplain1.setVisible(false);
        sumBlackColorExplain2.setVisible(false);
        winLabel.setVisible(true);
        if (!User.isGuest()) {
            killerSudoku.getUser().addValueToKillerPuzzlesPlayed(killerSudoku.getKillerPuzzle());
            killerSudoku.getUser().insertStatisticsInTheFile();
        }
        getHintButton().setEnabled(false);
    }

    /** This method extends GamePanel's resetNumberColor method. It checks all the boxes of every color area in killerSudoku and if its value is negative but it is
     * stored as acceptable in killerSudoku board it changes its box value to the positive one. Also it calls method setSumColor for every box's colored area sum label.
     */
    private void resetNumbersColor() {
        super.resetNumbersColor(killerSudoku, Color.BLACK);
        boolean sum;
        for (ColorArea colorArea: killerSudoku.getColorAreas()){
            for(Box box: colorArea.getColorAreaBoxes()){
                int i = box.getBoxCoordinateI(), j = box.getBoxCoordinateJ();
                box.setBoxValue(killerSudoku.getBoard()[i][j]);
                box.setNotEmpty(true);
                sum = killerSudoku.checkIfTheSumIsValid(killerSudoku.checkColorArea(i,j));
                int row = killerSudoku.getColorAreas().get(killerSudoku.checkColorArea(i, j)).getColorAreaBoxes().get(0).getBoxCoordinateI();
                int col = killerSudoku.getColorAreas().get(killerSudoku.checkColorArea(i, j)).getColorAreaBoxes().get(0).getBoxCoordinateJ();
                setSumColor(row, col,sum);
            }
        }
    }
    /** This method sets the color of the label that represents a colored area's acquired sum. Red if sum is wrong, blue if sum is right.
     * @param row The row in Sudoku board of a cell that contains a JLabel
     * @param col The column in Sudoku board of a cell that contains a JLabel
     */
    private void setSumColor(int row, int col, boolean sum){
        JLabel label =  (JLabel)boxes[row][col].getComponent(0);
        if(!sum){
           label.setForeground(Color.RED);
        }else {
            label.setForeground(Color.BLACK);
        }
    }
}