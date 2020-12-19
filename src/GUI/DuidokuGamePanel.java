package GUI;

import Logic.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ResourceBundle;

/** This is a GUI class. This class is the game Panel for a Duidoku game. This class is a GamePanel model, it extends GamePanel.
 * @see GamePanel
 * @author Velisarios 
 */
public class DuidokuGamePanel extends GamePanel{
    /** Variable duidoku is a Duidoku objecta and is used to get the Duidoku table and make the required checks
     */
    private Duidoku duidoku;
    /** Array boxListeners is an array of DocumentListeners
     */
    private DocumentListener[][] boxListeners;
    /** Variable isOk determines if user's input is acceptable (according to classic Sudoku rules). Variable user_last_played determines if the last player tha played was
     * the user or the computer.
     */
    private boolean isOK, user_last_played;
    /** Variable number is user's input in Sudoku board. Variable asciiCode is an integer used to determine if the version of the game is the classic one or wordoku. It
     * takes ascii Code of either '1' or 'A' minus 1 ('1' for classic and 'A' for Wordoku).
     */
    private int number, asciiCode;
    /** blackBoxExplain1 and blackBoxExplain2 are JLabels used for clarifications for the game. Variables winLabel, defeatLabel and duidokuTitle are are JLabels used to
     * announce user's victory, defeats and the name of the game respectively.
     */
    private JLabel duidokuTitle, blackBoxExplain1, blackBoxExplain2, winLabel, defeatLabel;
    /** Variables duidokuTitleFont and winOrDefeatFont are the Font for killerTitle and winLabel or defeatLabel texts respectively.
     */
    private Font duidokuTitleFont, winOrDefeatFont;

    /** Constructor that calls GamePanel's constructor with size 4, set the asciiCode of the game and initializes variables duidoku, array boxListeners and
     * all the JLabels and Fonts objects in this class.As for the redColorExplain2 and blueColorExplain2 labels the constructor checks the ascii code
     * variable in order to set those labels according to if to if the user chose to play the classic or wordoku version.
     * Also it calls method from class GamePanel setHintListener and private methods setUpDuidokuGamePanel and
     * setBoxListeners.
     * @param aUser User class object. The user that plays the game
     * @param upperLimit parameter that is send to super (GamePanel constructor)
     * @param lowerLimit parameter that is send to super (GamePanel constructor)
     * @param anAsciiCode the ascii code that determines the game version (classic, wordoku)
     * @param bundle The resource bundle that loads the strings used by the project and which determines program's language
     */
    public DuidokuGamePanel(User aUser, char upperLimit, char lowerLimit, int anAsciiCode, ResourceBundle bundle){
        super(4, upperLimit, lowerLimit, 153,100, 80,bundle);
        asciiCode = anAsciiCode;
        duidoku = new Duidoku(aUser);
        duidokuTitle = new JLabel(bundle.getString("duidokuButton"));
        duidokuTitleFont = new Font("Arial", Font.BOLD, 20);
        blackBoxExplain1 = new JLabel(bundle.getString("blackLabel"));
        if (asciiCode == 48)
            blackBoxExplain2 = new JLabel(bundle.getString("blackLabeltext"));
        else
            blackBoxExplain2 = new JLabel(bundle.getString("blackLettertext"));

        winLabel = new JLabel(bundle.getString("winLabel"));
        defeatLabel = new JLabel(bundle.getString("loseLabel"));
        winOrDefeatFont = new Font("Arial", Font.BOLD, 30);
        boxListeners = new DocumentListener[4][4];
        setUpDuidokuGamePanel();
        setBoxListeners();
        setHintListener(duidoku,asciiCode);
    }

    /** This method sets the size and position of all the JLabels variables in this class. Also it sets their visibility (winLabel and defeatLabel false and the rest true)
     * and sets the font for killerTitle, winLabel and defeatLabel. Also this method adds all the JLabels to the panel and sets the panel's background color.
     */
    private void setUpDuidokuGamePanel(){

        duidokuTitle.setBounds(271, 60, 500, 40);
        duidokuTitle.setFont(duidokuTitleFont);
        add(duidokuTitle);

        winLabel.setBounds(247, 450,  200, 40);
        winLabel.setFont(winOrDefeatFont);
        winLabel.setVisible(false);
        add(winLabel);

        defeatLabel.setBounds(247, 450,  200, 40);
        defeatLabel.setVisible(false);
        defeatLabel.setFont(winOrDefeatFont);
        add(defeatLabel);

        blackBoxExplain1.setBounds(150, 430, 100, 20);
        add(blackBoxExplain1);

        blackBoxExplain2.setBounds(225, 430, 250, 20);
        add(blackBoxExplain2);

        setBackground(Color.decode("#FFB266"));
    }

    /**This method initialize every DocumentListener in array boxListeners. Each one DocumentListener is being added respectively to the document of each one of
     * JTextFields in array boxes (protected array in call GamePanel). If the user insert a value to the board (array boxes), it checks if it is acceptable,
     * according to the classic Sudoku rules. If it isn't, it is being deleted after the end of Listener's actions by using SwingUtilities.invokeLater, (see link below).
     * {@link} https://alvinalexander.com/java/java-swingutilities-invoke-later-example-edt. If it is accepted it store it to the board of object duidoku. Also it calls
     * privates methods setEditableFalse, setBoxBlack and update user_last_played as true. Also if the computer can make a next move, it calls private method ComPlays
     * and set user_last_played to false. Finally if the game is completed method EndGameActions is being called.
     */
    private void setBoxListeners() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                final int row = i, col = j;
                boxListeners[i][j] = new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        number = (int)boxes[row][col].getText().charAt(0)-asciiCode;
                        isOK = duidoku.isOk(row, col, number);
                        if (isOK) {
                            duidoku.setDuidokuTablePos(row, col, number);
                            duidoku.setNotEmptyPosArray(row, col, true);
                            setEditableFalse(row, col);
                            setBoxBlack();
                            user_last_played = true;
                            if (duidoku.com_turn()) {
                                ComPlays();
                                user_last_played = false;
                            }
                            if(duidoku.getFilledPosOfArray() == 16){
                                EndOfGameActions();
                            }
                        } else{
                            SwingUtilities.invokeLater(() -> boxes[row][col].setText(""));
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                };
                boxes[i][j].getDocument().addDocumentListener(boxListeners[i][j]);
            }
        }
    }

    /** This method is used every time the computer plays. It finds computer's next move (cell to insert value), removes the DocumentListener from this
     * JTextField's document, and insert the value. Also it calls private methods setBoxBlack and setEditableFalse
     */
    private void ComPlays() {
        int comRow = duidoku.getComRow();
        int comCol = duidoku.getComCol();
        int comNumber = duidoku.getComValue();
        boxes[comRow][comCol].getDocument().removeDocumentListener(boxListeners[comRow][comCol]);
        boxes[comRow][comCol].setText(String.valueOf((char)(comNumber+asciiCode)));
        setBoxBlack();
        setEditableFalse(comRow, comCol);
    }

    /**This method is used to do all the required actions whenever the user completes a Sudoku game. It checks for the winner, by using boolean variable user_last_played and
     * makes the winLabel or defeatLabel visible in order to announce the end of the game. Furthermore it store user's statistics and set hintButton to not enable.
     */
    private void EndOfGameActions(){
        if(user_last_played){
            winLabel.setVisible(true);
            duidoku.getUser().addWins();
        }else{
            defeatLabel.setVisible(true);
            duidoku.getUser().addLosses();
        }
        duidoku.getUser().insertStatisticsInTheFile();
        getHintButton().setEnabled(false);
    }

    /**  This method sets all the board's cells that can't be used, background color to black (DARK_GREY) and set them as non editable.
     */
    private void setBoxBlack() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (duidoku.getBlackColor()[i][j]) {
                    boxes[i][j].setBackground(Color.DARK_GRAY);
                    boxes[i][j].setEditable(false);
                }
            }
        }

    }

    /** This method is used to set the cell the user or computer has added a value as not editable.
     * @param row The row of the cell of the input
     * @param col The column of the cell of the input
     */
    private void setEditableFalse(int row, int col) {
        boxes[row][col].setEditable(false);
        boxes[row][col].setBackground(Color.white);
    }
}