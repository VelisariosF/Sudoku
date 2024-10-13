package gui;


import logic.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * This is a GUI class. This class is the game panel of classic a Sudoku game. This class is a GamePanel class model, it extends GamePanel.
 * @see GamePanel
 * @author Velisarios 
 */
public class ClassicGamePanel extends GamePanel{
    /** Variable classicSudoku is a ClassicSudoku object and is used to get the Sudoku table and make the required checks
     */
    private ClassicSudoku classicSudoku;
    /** Variable number is user's input in Sudoku board. Variable asciiCode is an integer used to determine if the version of the game is the classic one or wordoku. It
     * takes ascii Code of either '1' or 'A' minus 1 ('1' for classic and 'A' for Wordoku).
     */
    private int number, asciiCode;
    /** Variable isOk determines if user's input is acceptable (according to classic Sudoku rules).
     */
    private boolean isOk;
    /** redColorExplain1, redColorExplain2, blueColorExplain1 and blueColorExplain2 are JLabels used for clarifications for the game. Variables winLabel and classicTitle
     * are JLabels used to announce the end of the game or the name of the game respectively.
     */
    private JLabel classicTitle,redColorExplain1, redColorExplain2, blueColorExplain1, blueColorExplain2, winLabel;
    /** Variables classicTitleFont and winLabelFont are the Font for classicTitle and winLabel texts respectively.
     */
    private Font classicTitleFont, winLabelFont;

    /** Constructor that calls GamePanel's constructor with size 9, initializes variables classicSudoku and all the JLabels
     * and Fonts objects in this class.As for the redColorExplain2 and blueColorExplain2 labels the constructor checks the ascii code
     * variable in order to set those labels according to if to if the user chose to play the classic or wordoku version.
     * Also it calls private methods setBoardInitValues and setUpClassicGamePanel.
     * @param aUser User class object. The user that plays the game
     * @param upperLimit parameter that is send to super (GamePanel constructor)
     * @param lowerLimit parameter that is send to super (GamePanel constructor
     * @param anAsciiCode the ascii code that determines the game version (classic, wordoku)
     * @param bundle The resource bundle that loads the strings used by the project and which determines program's language
     */
    public ClassicGamePanel(User aUser, char upperLimit, char lowerLimit, int anAsciiCode, ResourceBundle bundle){
        super(9, upperLimit, lowerLimit, 70, 40, 55,bundle);
        asciiCode = anAsciiCode;
        classicSudoku = new ClassicSudoku(aUser);
        classicSudoku.chooseRandomClassicPuzzle();
        classicTitleFont = new Font("Arial", Font.BOLD, 20);
        winLabelFont = new Font("Arial", Font.BOLD, 25);
        classicTitle = new JLabel(bundle.getString("classicLabel")+'#'+(classicSudoku.getClassicPuzzle()+1));
        redColorExplain1 = new JLabel(bundle.getString("redLabel"));
        blueColorExplain1 = new JLabel(bundle.getString("blueLabel"));
        if (asciiCode == 48) {
            redColorExplain2 = new JLabel(bundle.getString("wrongNumber"));
            blueColorExplain2 = new JLabel(bundle.getString("rightNumber"));
        } else if (asciiCode == 64){
            redColorExplain2 = new JLabel(bundle.getString("wrongLetter"));
            blueColorExplain2 = new JLabel(bundle.getString("rightLetter"));
        }
        winLabel = new JLabel(bundle.getString("finishMessage"));
        setBoardInitValues();
        setUpClassicGamePanel();
    }

    /** This method add the initial numbers to the board of Classic Sudoku as they are taken from classicSudoku variable. It sets them as not editable and sets their
     * background color. Also it calls method setHintListeners from class GamePanel and private method setBoxListeners.
     */
    private void setBoardInitValues(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (classicSudoku.getBoard()[i][j] != 0) {
                    boxes[i][j].setText(String.valueOf((char)(classicSudoku.getBoard()[i][j]+asciiCode)));
                    boxes[i][j].setEditable(false);
                    boxes[i][j].setBackground(Color.decode("#C0C0C0"));
                }
            }
        }
        setBoxListeners();
        setHintListener(classicSudoku,asciiCode);
    }

    /** This method sets the size and position of all the JLabels variables in this class. Also it sets their visibility (winLabel false and the rest true) and sets
     * the font for classicTitle and winLabel. Also this method adds all the JLabels to the panel and sets the panel's background color.
     */
    private void setUpClassicGamePanel() {
        classicTitle.setFont(classicTitleFont);
        classicTitle.setBounds(240, 10, 500, 40);
        add(classicTitle);

        winLabel.setBounds(85, 538, 500, 40);
        winLabel.setFont(winLabelFont);
        winLabel.setVisible(false);
        add(winLabel);

        redColorExplain1.setBounds(77, 538, (int)(redColorExplain1.getText().length()*6.5), 20);
        redColorExplain1.setForeground(Color.RED);
        add(redColorExplain1);

        blueColorExplain1.setBounds(77, 555, blueColorExplain1.getText().length()*7, 20);
        blueColorExplain1.setForeground(Color.BLUE);
        add(blueColorExplain1);

        redColorExplain2.setBounds(77+Math.max(redColorExplain1.getWidth(),blueColorExplain1.getWidth()), 538, 150, 20);
        add(redColorExplain2);


        blueColorExplain2.setBounds(77+Math.max(redColorExplain1.getWidth(),blueColorExplain1.getWidth()), 555, 150, 20);
        add(blueColorExplain2);

        setBackground(Color.decode("#66FF66"));
    }

    /** This method adds a document listener to the document of every JTextField in array boxes (protected array from class GamePanel). If the user insert a number then
     * it check if it is acceptable for the board according to the Classic Sudoku rules. Also it store the number to board of classicSudoku (if it isn't acceptable,
     * its negative value) and calls method setNumberColor(GamePanel). Moreover. If the game is completed, it calls private method EndOfGameActions. If the user erases
     * a number, it stores 0 to classicSudoku board and calls method resetNumbersColor (GamePanel).
     */
    private void setBoxListeners(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final int row = i, col = j;
                boxes[i][j].getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        number = (int)boxes[row][col].getText().charAt(0)-asciiCode;
                        isOk = classicSudoku.isOk(row, col, number);
                        if (!isOk){
                            number = number *(-1);
                        }
                        setNumberColor(isOk, row, col,Color.BLUE);
                        classicSudoku.setBoard(row,col,number);
                        if(classicSudoku.checkWinner()){
                            EndOfGameActions();
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        classicSudoku.setBoard(row,col, 0);
                        resetNumbersColor(classicSudoku,Color.BLUE);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                });
            }
        }
    }

    /** This method is used to do all the required actions whenever the user completes a Sudoku game. It sets the clarification JLabels invisible and winLabel to visible
     * in order announce the end of the game.Also it sets every JTextField in array boxes (protected array from GamePanel class) to not editable. Furthermore it store
     * user's statistics and set hintButton to not enable.
     */
    private void EndOfGameActions(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                boxes[i][j].setEditable(false);
            }
        }
        redColorExplain1.setVisible(false);
        redColorExplain2.setVisible(false);
        blueColorExplain1.setVisible(false);
        blueColorExplain2.setVisible(false);
        winLabel.setVisible(true);
        if(!User.isGuest()){
            classicSudoku.getUser().addValueToClassicPuzzlesPlayed(classicSudoku.getClassicPuzzle());
            classicSudoku.getUser().insertStatisticsInTheFile();
        }
        
        getHintButton().setEnabled(false);
    }
}