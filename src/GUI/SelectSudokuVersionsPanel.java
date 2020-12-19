package GUI;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

/** This is a GUI class. This class is the panel for the Sudoku version game selections (Classic Sudoku, Killer Sudoku, Duidoku).
 * This class is a JPanel model, it extends JPanel
 * @author Velisarios 
 */
public class SelectSudokuVersionsPanel extends JPanel{
    /** classicButton, killerButton and duidokuButton are JButtons used to select the version of Sudoku game the user wants to play (Classic, Killer and Duidoku
     * respectively). SelectSudokuVersionsPanelGoBackButton is a JButton used to leave this panel
     */
    private JButton classicButton,killerButton, duidokuButton, SelectSudokuVersionsPanelGoBackButton;
    /** wordokuOption variable is a JPopupMenu which give the user the ability to choose to play wordoku.(wordoku only works at Classic sudoku and duidoku,
     * not to Killer Sudoku). Ιτ contains classic option (with numbers) and wordoku option (with letters)
     */
    private JPopupMenu wordokuOption;
    /** classic variable is a JMenuItem of wordokuOption and it is the option to play the classic version. wordoku variable is a JMenuItem of wordokuOption and
     * it is the option to play the wordoku version.
     */
    private JMenuItem wordoku, classic;
    /** SelectSudokuVersionsPanelTitle is the JLabel with the title of this panel.
     */
    private JLabel SelectSudokuVersionsTitle;
    /**  SelectSudokuVersionsTitleFont is the Font used by SelectSudokuVersionsTitle.
     */
    private Font SelectSudokuVersionsTitleFont;
    /** buttonWidth, buttonHeight and buttonXaxisPos determines classicButton's, killerButton's and duidokuButton's width, height and horizontal coordinate respectively.
     * All 3 of them are final integers.
     */
    private final int buttonWidth = 180, buttonHeight = 35, buttonXaxisPos = 220;

    /** Constructor tha initializes all the variables in this class (beside the final ones), using Strings from Resource Bundle bundle, for every text. Also it calls
     * private methods setUpSelectSudokuVersionsPanel and method setListeners twice.
     * @param bundle The resource bundle that loads the strings used by the project and which determines program's language
     */
    public SelectSudokuVersionsPanel(ResourceBundle bundle) {
        classicButton = new JButton(bundle.getString("classicButton"));
        killerButton = new JButton(bundle.getString("killerButton"));
        duidokuButton = new JButton(bundle.getString("duidokuButton"));
        SelectSudokuVersionsPanelGoBackButton = new JButton(bundle.getString("backButton"));
        wordokuOption = new JPopupMenu();
        classic = new JMenuItem(bundle.getString("classicItem"));
        wordoku = new JMenuItem(bundle.getString("wordokuItem"));
        SelectSudokuVersionsTitle = new JLabel((bundle.getString("typeLabel")));
        SelectSudokuVersionsTitleFont = new Font("Arial", Font.PLAIN, 30);
        setUpSelectSudokuVersionsPanel();
        setListeners(classicButton);
        setListeners(duidokuButton);
    }

    /** This method sets the size and position of all the JButtons and JLabels in this panel and add Font to SelectSudokuVersionsTitle. Also it sets background color for
     * classic and wordoku and add them to wordokuOption. Also it sets panel's layout to null and make it visible. Finally it adds public JLabel background
     * (from class SudokuFrame)
     * @see SudokuFrame
     */
    public void setUpSelectSudokuVersionsPanel(){

        SelectSudokuVersionsTitle.setFont(SelectSudokuVersionsTitleFont);
        SelectSudokuVersionsTitle.setBounds(70, 40, 500, 40);
        SelectSudokuVersionsTitle.setHorizontalAlignment(JLabel.CENTER);
        add(SelectSudokuVersionsTitle);

        classicButton.setBounds(buttonXaxisPos, 100, buttonWidth, buttonHeight);
        classicButton.setBackground(Color.WHITE);
        add(classicButton);

        killerButton.setBounds(buttonXaxisPos, 100 + buttonHeight + 2, buttonWidth, buttonHeight);
        killerButton.setBackground(Color.WHITE);
        add(killerButton);

        duidokuButton.setBounds(buttonXaxisPos, 100 + 2*buttonHeight + 4, buttonWidth, buttonHeight);
        duidokuButton.setBackground(Color.WHITE);
        add(duidokuButton);

        SelectSudokuVersionsPanelGoBackButton.setBounds(61, 585, 70, 20);
        SelectSudokuVersionsPanelGoBackButton.setBackground(Color.WHITE);
        add(SelectSudokuVersionsPanelGoBackButton);

        classic.setBackground(Color.WHITE);
        wordoku.setBackground(Color.WHITE);
        wordokuOption.add(classic);
        wordokuOption.add(wordoku);

        add(SudokuFrame.userName);
        add(SudokuFrame.background);
        setLayout(null);
        setVisible(true);
    }

    /** This method adds an ActionListener to a JButton. It is used for classicButton and duidokuButton to show the wordokuOption popup menu next to one of them. Also
     * it sets classic's and wordoku's (LMenuItems) ActionCommand as the JButton's ActionCommand (classicButton or duidokuButton).
     * @param button The JButton the listener is added to
     */
    private void setListeners(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wordokuOption.show(button,185,0);
                classic.setActionCommand(e.getActionCommand());
                wordoku.setActionCommand(e.getActionCommand());
            }
        });
    }

    /** This method returns JButton killerButton in order to add ActionListener to it wherever it is needed.
     * @return JButton killerButton
     */
    public JButton getKillerButton() {
        return killerButton;
    }

    /** This method returns JButton selectSudokuVersionPanelGoBackButton in order to add ActionListener to it wherever it is needed.
     * @return JButton selectSudokuVersionPanelGpBackButton
     */
    public JButton getSelectSudokuVersionsPanelGoBackButton() {
        return SelectSudokuVersionsPanelGoBackButton;
    }

    /** This method returns JMenuItem wordoku in order to add ActionListener to it wherever it is needed.
     * @return JMenuItem wordoku
     */
    public JMenuItem getWordoku() {
        return wordoku;
    }

    /** This method returns JMenuItem classic in order to add ActionListener to it wherever it is needed.
     * @return JMenuItem classic
     */
    public JMenuItem getClassic() {
        return classic;
    }

}