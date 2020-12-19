package GUI;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;
import Logic.*;

/**
 * This is a GUI class. This class implements the Game Panel of a Sudoku game. This class is a Jpanel model, it extends JPanel.
 * @author Velisarios 
 */
public class GamePanel extends  JPanel{
    /** The array boxes is an array of JTextFields and it is the graphical board of Sudoku.
     */
    protected JTextField[][] boxes;
    /**Variable font is the Font used by boxes.
     */
    private Font font;
    /** gameGoBackButton, newGameButton and hintButton variables are JButtons to leave the panel, start a new game or get help for the game respectively.
     */
    private JButton gameGoBackButton, newGameButton, hintButton;
    /**Variable hintMenu is a JPopupMenu that contains all the acceptable numbers of a Sudoku board cell according to classic Sudoku rules.
     */
    private JPopupMenu hintMenu;
    /** Variable acceptableNumbers is every acceptable number included in hintMenu
     */
    private JMenuItem acceptableNumbers;

    /** Constructor that creates the array boxes with given size. It also creates gameGoBackButton, newGameButton and hintButton (all three of them
     * with text taken by aBundle) and font. It also calls private methods setBoard  and setGamePanel.
     * @param size Parameter that is send to setBoard method
     * @param upperLimit Parameter that is send to setBoard method
     * @param lowerLimit Parameter that is send to setBoard method
     * @param xAxisShow Parameter that is send to setBoard method
     * @param yAxisShow Parameter that is send to setBoard method
     * @param boxesWidthHeight Parameter that is send to setBoard method
     * @param aBundle The resource bundle that loads the strings used by the project and determines program's language
     */
    public GamePanel(int size, char upperLimit, char lowerLimit, int xAxisShow, int yAxisShow, int boxesWidthHeight, ResourceBundle aBundle) {
        font = new Font("SansSerif", Font.PLAIN, 20);
        boxes = new JTextField[size][size];
        gameGoBackButton = new JButton(aBundle.getString("backButton"));
        newGameButton = new JButton(aBundle.getString("newGameButton"));
        hintButton = new JButton(aBundle.getString("hintButton"));
        setBoard(size,upperLimit,lowerLimit,xAxisShow,yAxisShow,boxesWidthHeight);
        setGamePanel();
    }

    /** This method initialize array boxes. Every JTextField in array boxes has the same size but different position. Each JTextField has different border according to its
     * position(array borders), the same Horizontal Alignment and uses Font font. Also all JTextFields use the same document object from class
     * TextFieldLimit. All JTextFields are added to the JPanel.
     * @param size The number of cells in Sudoku board
     * @param upperLimit Parameter send to TextFieldLimit class' constructor
     * @param lowerLimit Parameter send to TextFieldLimit class' constructor
     * @param xAxisShow The horizontal coordinate of the top let corner of the board
     * @param yAxisShow The vertical coordinate of the top let corner of the board
     * @param boxesWidthHeight The size of each board cell
     */
    private void setBoard(int size, char upperLimit, char lowerLimit, int xAxisShow, int yAxisShow, int boxesWidthHeight){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int[] borders = new int[]{1, 1, 1, 1};
                boxes[i][j] = new JTextField();
                boxes[i][j].setDocument(new TextFieldLimit(1, upperLimit, lowerLimit));
                boxes[i][j].setBounds((xAxisShow + j * boxesWidthHeight), (yAxisShow + i * boxesWidthHeight), boxesWidthHeight, boxesWidthHeight);
                if (i % Math.sqrt(size) == Math.sqrt(size) - 1) {
                    borders[2] = 5;
                }
                if (j % Math.sqrt(size) == Math.sqrt(size) - 1) {
                    borders[3] = 5;
                }
                if (i == 0) {
                    borders[0] = 5;
                }
                if (j == 0) {
                    borders[1] = 5;
                }
                boxes[i][j].setBorder(BorderFactory.createMatteBorder(borders[0], borders[1], borders[2], borders[3], Color.black));
                boxes[i][j].setHorizontalAlignment(JTextField.CENTER);
                boxes[i][j].setFont(font);
                add(boxes[i][j]);
            }
        }
    }

    /**
     * This method set the position and size of gameGoBackButton, newGameButton and hintButton. Also it makes them visible, their background color white
     * and add them to the JPanel. Also it sets panel visible and its Layout to null.
     */
    private void setGamePanel(){
        gameGoBackButton.setBounds(61, 585, 70, 20);
        gameGoBackButton.setBackground(Color.WHITE);
        gameGoBackButton.setVisible(true);
        add(gameGoBackButton);

        newGameButton.setBounds(135, 585, 140, 20);
        newGameButton.setBackground(Color.WHITE);
        newGameButton.setVisible(true);
        add(newGameButton);

        hintButton.setBounds(500, 585, 90, 20);
        hintButton.setBackground(Color.WHITE);
        hintButton.setVisible(true);
        add(hintButton);

        add(SudokuFrame.userName);
        setVisible(true);
        setLayout(null);
    }

    /** This method adds a FocusListener to each JTextField in array boxes. If the user clicks to a JTextField of boxes, it creates hintMenu and acceptableNumber objects.
     * Also it adds ActionListener to hintButton. If hintButton is pressed then it shows the hint menu at left of hintButton.
     * @param sudoku Object from logic class. Different for each version of Sudoku (Classic, Killer or Duidoku).
     * @param ascii Lower's limit ascii code minus 1
     */
        public void setHintListener(SudokuLogic sudoku, int ascii){
            for (int i = 0;i<boxes.length; i++){
                for(int j = 0;j<boxes.length; j++){
                    final int row = i, col = j;
                    boxes[i][j].addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            hintMenu = new JPopupMenu();
                            for (int value = 1; value<boxes.length+1;value++){
                                if (sudoku.isOk(row,col,value)){
                                    acceptableNumbers = new JMenuItem(String.valueOf((char)(value+ascii)));
                                    acceptableNumbers.setBackground(Color.WHITE);
                                    hintMenu.add(acceptableNumbers);
                                }
                            }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            hintButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    hintMenu.show(hintButton,91,20-hintMenu.getPreferredSize().height);

                                }
                            });
                        }
                    });
                }
            }
        }

    /** This method set user's input (in Sudoku board) color. If it is acceptable, according to classic Sudoku rules, it is set to blue (for Classic Sudoku) or black
     * (for Killer Sudoku) and if it isn't to red.
     * @param isOK Parameter that determines if umber is acceptable
     * @param row The row of cell of user's input
     * @param col The column of the cel of user's input
     * @param color Parameter color that is set to number's Foreground when it's acceptable
     */
    public void setNumberColor(boolean isOK, int row, int col, Color color) {
        if (isOK) {
            boxes[row][col].setForeground(color);
        } else {
            boxes[row][col].setForeground(Color.RED);
        }
    }

    /** This method is used to reset the color of user's input in Sudoku board every time the user erases a number. Ιt checks every number in Sudoku board,
     * if a number is stored as a not acceptable but is acceptable after the erasure it change its color (from red to blue, for Classic, or black ,for Killer)
     * and store it as acceptable.
     * @param sudokuLogic Object from SudokuLogic subclass (either ClassicSudoku or KillerSudoku)
     * @param color Parameter Color that determines the Foreground color of every acceptable Number
     */
    public void resetNumbersColor(SudokuLogic sudokuLogic, Color color) {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(sudokuLogic.isOk(i, j, sudokuLogic.getBoard()[i][j]*(-1)) && sudokuLogic.getBoard()[i][j] < 0){
                    boxes[i][j].setForeground(color);
                    sudokuLogic.setBoard(i, j, sudokuLogic.getBoard()[i][j]*(-1));
                } }
        }
    }
    /** This method return the gameGoBackButton
     * @return JButton, it returns the gameGoBackButton
     */
    protected JButton getGameGoBackButton() {
        return gameGoBackButton;
    }

    /** Method that returns the newGameButton
     * @return JButton, it returns the newGameButton
     */
    protected JButton getNewGameButton() {
        return newGameButton;
    }

    /** Method that returns the hintButton
     * @return JButton, it returns the hintButton
     */
    protected JButton getHintButton() {
        return hintButton;
    }

    /** This class is used by array boxes and is actually the document for every JTextField in the array. This class is a PlainDocument class model, it extends PlainDocument.
     */
    private static class TextFieldLimit extends PlainDocument {
        /** Variable limit is the number of characters that can be inserted to JTextField.
         */
        private int limit;
        /** Variables upperLimit and lowerLimit are  respectively the biggest and smallest (according to Ascii table) characters that can be inserted to the JTextField.
         */
        private char upperLimit, lowerLimit;

        /** Constructor that initializes class' variables.
         * @param limit Parameter limit is variables' limit value
         * @param upperLimit Parameter upperLimit is variables' upperLimit value
         * @param lowerLimit Parameter upperLimit is variables' lowerLimit value
         */
        TextFieldLimit(int limit, char upperLimit, char lowerLimit) {
            this.upperLimit = upperLimit;
            this.limit = limit;
            this.lowerLimit = lowerLimit;


        }
        /** This method is being used to check if JTextField's document's input is acceptable. If it is it calls PlainDocument's insertString method to set the text to the
         * JTextField.
         * @param offset see link
         * @param str User's input, see link
         * @param attr see link
         * @throws BadLocationException see link
         * @see #insertString
         * {@link} http://www.java2s.com/Tutorial/Java/0240__Swing/LimitJTextFieldinputtoamaximumlength.htm
         */
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;
            if ((getLength() + str.length()) <= limit && str.charAt(0)>=this.lowerLimit && str.charAt(0)<=this.upperLimit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}