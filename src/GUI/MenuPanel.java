package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * This class implements the main menu panel.
 * @author Velisarios 
 */
public class MenuPanel extends JPanel{
    /**
     * The playButton, statsButton, exitButton , menuPanelGoBackButton, languageButton variables are JButtons for
     * entering the play panel, seeing the stats of a user, exiting the game, going back to the selectUserPanel and entering the menu that
     * allow the user to change the language of the game.
     */
    private JButton playButton, statsButton, exitButton , menuPanelGoBackButton, languageButton;

    /**
     * The menuTitle variable is a JLabel that represents the main title of the main menu.
     */
    private JLabel menuTitle;
    /**
     * The menuFont variable is a Font that is applied to the menuTitle in order to change its appearance.
     */
    private Font  menuFont;
    /**
     * The buttonWidth, buttonHeight, buttonXaxisPos variables represent the main menu's buttons width, height and they 're
     * Xaxis position respectively.
     */
    private final int buttonWidth = 180, buttonHeight = 35, buttonXaxisPos = 220;

    /**
     * The constructor of the MenuPanel gets as parameter an object of the ResourceBundle class and sents it as a parameter to the
     *  menuPanelComponentsText in order, for the titles of every component, a title at the appropriate language.Also it initializes all of
     *  its buttons and sets itself up by calling the setUpMenuPanel method.
     * @param bundle This is the only parameter of the constructor.
     */
    public MenuPanel(ResourceBundle bundle){
        playButton = new JButton();
        statsButton = new JButton();
        exitButton = new JButton();
        languageButton = new JButton();
        menuPanelGoBackButton = new JButton();
        menuTitle = new JLabel();
        menuPanelComponentsText(bundle);
        setUpMenuPanel();
    }

    /**
     * This method is used to set up the main menu panel (MenuPanel) along with its title, buttons and an image that is being taken
     * from the SudokuFrame class.
     * @see SudokuFrame
     */
    public void setUpMenuPanel(){
        //setting up the title name and letter type etc.
        menuFont = new Font("Arial", Font.BOLD, 30);
        menuTitle.setFont(menuFont);
        menuTitle.setBounds(70, 40, 500, 40);
        menuTitle.setHorizontalAlignment(JLabel.CENTER);

        //setting up the position of each button
        playButton.setBounds(buttonXaxisPos, 100, buttonWidth, buttonHeight);
        playButton.setBackground(Color.WHITE);
        statsButton.setBounds(buttonXaxisPos, 100 + buttonHeight + 2, buttonWidth, buttonHeight);
        statsButton.setBackground(Color.WHITE);
        languageButton.setBounds(buttonXaxisPos,100+2*buttonHeight+4,buttonWidth,buttonHeight);
        languageButton.setBackground(Color.WHITE);
        exitButton.setBounds(buttonXaxisPos, 100 + 3*buttonHeight + 6, buttonWidth, buttonHeight);
        exitButton.setBackground(Color.WHITE);

        menuPanelGoBackButton.setBounds(61, 585, 70, 20);
        menuPanelGoBackButton.setBackground(Color.WHITE);
        menuPanelGoBackButton.setVisible(true);
        add(menuPanelGoBackButton);
        //Adding everything to the MenuPanel

        this.add(menuTitle);
        this.add(playButton);
        this.add(statsButton);
        this.add(languageButton);
        this.add(exitButton);

        //Setting up its layout
        add(SudokuFrame.userName);
        add(SudokuFrame.background);

        this.setLayout(null);
        this.setVisible(true);
    }

    /**
     * This method is used to set the language of every title of every component inside the menuPanel according to the system, that runs
     * the application, language or the user's preference.
     * @param bundle This parameter is used to get the proper string, for the titles, from the Resource Bundle Languages files.
     */
    public void menuPanelComponentsText(ResourceBundle bundle){
        playButton.setText(bundle.getString("playButton"));
        statsButton.setText(bundle.getString("statsButton"));
        exitButton.setText(bundle.getString("exitButton"));
        languageButton.setText(bundle.getString("languageLabel"));
        menuPanelGoBackButton.setText(bundle.getString("backButton"));
        menuTitle.setText(bundle.getString("welcomeLabel"));
    }

    /**
     * This method returns the button that is used to enter the panel that the user can choose which of the sudoku versions to play.
     * @return JButton This returns the playButton variable.
     */
    public JButton getPlayButton() {
        return playButton;
    }

    /**
     * This method returns the button that is used to enter the stats panel where the user can see they're statistics for the game.
     * @return JButton This returns the statsButton variable.
     */
    public JButton getStatsButton() {
        return statsButton;
    }

    /**
     * This method returns the button that enters the panel from which the user can change the language of the game.
     * @return JButton This returns the languageButton variable.
     */
    public JButton getLanguageButton() {
        return languageButton;
    }

    /**
     * This method returns the button that when pressed the user exits the game.
     * @return JButton This returns the exitButton variable.
     */
    public JButton getExitButton() {
        return exitButton;
    }

    /**
     * This method returns the button that when pressed the user is being taken back to the panel from where he can chose to create a new account
     * or select to play with another account.
     * @return JButton This returns the menuPanelGoBackButton variable.
     */
    public JButton getMenuPanelGoBackButton() {
        return menuPanelGoBackButton;
    }

}