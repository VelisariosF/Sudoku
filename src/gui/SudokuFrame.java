package gui;

import logic.Internationalization;
import logic.User;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class implements the graphical user interface of the application.
 * @author Velisarios 
 */


public class SudokuFrame {

    /**
     * The user variable represents the user that plays the game.
     */
    private User user;

    /**
     * The sudokuFrame variable represents the main frame of the game.
     */
    private JFrame sudokuFrame;

    /**
     *The menuPanel, statsPanel, selectUserPanel, selectSudokuVersionsPanel, killerGamePanel, classicGamePanel, duidokuGamePanel and languagePanel
     * represent the panels from where the user navigates through the application.
     */
    private  MenuPanel menuPanel;
    private StatsPanel statsPanel;
    private SelectUserPanel selectUserPanel;
    private SelectSudokuVersionsPanel selectSudokuVersionsPanel;
    private  KillerGamePanel killerGamePanel;
    private  ClassicGamePanel classicGamePanel;
    private  DuidokuGamePanel duidokuGamePanel;
    private LanguagePanel languagePanel;

    /**
     * The sudoku_frame_size is the size of the main frame of the application.
     */
    private final int sudoku_frame_size = 650;

    /**
     * The wordoku variable checks if the user chose to play wordoku instead of classic Sudoku.
     */
    private boolean wordoku;

    /**
     * The internationalization variable is used to define the language of the application.
     */
    private Internationalization internationalization;

    /**
     * This represents the background image of the game.
     * {@link} https://www.daily-sudoku.com/
     */
    private Icon backgroundImage;

    /**
     * background variable represents the label where the image backgroundImage is loaded to. userName variable is the label with the name of
     * the user that is playing the game.
     */
    public static JLabel background, userName;


    /**
     * The constructor initializes the main frame, the internationalization object, the background image, the userName label, the selectUserPanel
     * and also calls the setUpSudokuFrame and selectUserPanelButtonPressedActions.
     */
    public SudokuFrame() {
        sudokuFrame = new JFrame();
        internationalization = new Internationalization();
        backgroundImage = new ImageIcon("Background.jpg");
        background = new JLabel(backgroundImage);
        userName = new JLabel("user");
        selectUserPanel = new SelectUserPanel(internationalization.getBundle());
        setUpSudokuFrame();
        selectUserPanelButtonPressedActions();
    }

    /**
     * This method is used to set the main frame's location at the screen, its size,its resizability and  its visibility.Also
     * it sets the background's,userName's label size and where its going to appear on the main frame.Lastly it sets the main frame's operation when closed.
     */
    public void setUpSudokuFrame() {
        sudokuFrame.add(selectUserPanel);
        sudokuFrame.setLocation(300, 80); //I must change the location to null
        background.setBounds(0,0,sudoku_frame_size,sudoku_frame_size);
        userName.setBounds(5,5,sudoku_frame_size,30);
        sudokuFrame.setSize(sudoku_frame_size, sudoku_frame_size);
        sudokuFrame.setVisible(true);
        sudokuFrame.setResizable(false);
        sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method implements the select user panel's buttons action listeners that are used to change between the selectUserPanel and the menuPanel
     * (using private method changePanels). For every JMenuItem of the UserNamesMenuItems ArrayList, for the createUserButton and for guestPlayerButton
     * it adds the action listener to it. Also it calls private method menuPanelButtonPressedActions. Also it sets guestPlayer to true or false (false for
     * JMenuItems and createUserButton and true for guestPlayerButton).Also it sets the text of the userName label and its foreground color.
     */
    public void selectUserPanelButtonPressedActions(){

        for (JMenuItem item: selectUserPanel.getUserNamesMenuItems()){
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    User.setGuest(false);
                    menuPanel = new MenuPanel(internationalization.getBundle());
                    userName.setForeground(Color.BLACK);
                    userName.setText(item.getText());
                    changePanels(selectUserPanel,menuPanel);
                    menuPanelButtonPressedActions();
                }
            });
        }

        selectUserPanel.getCreateUserButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.setGuest(false);
                if (!selectUserPanel.getUserNamesList().contains(selectUserPanel.getCreateUserTextField().getText()) &&
                     !selectUserPanel.getCreateUserTextField().getText().isBlank()) {
                    menuPanel = new MenuPanel(internationalization.getBundle());
                    userName.setForeground(Color.BLACK);
                    userName.setText(selectUserPanel.getCreateUserTextField().getText());
                    changePanels(selectUserPanel,menuPanel);
                    menuPanelButtonPressedActions();
                }
            }
        });

        selectUserPanel.getGuestPlayerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.setGuest(true);
                menuPanel = new MenuPanel(internationalization.getBundle());
                changePanels(selectUserPanel,menuPanel);
                userName.setForeground(Color.BLUE);
                userName.setText(internationalization.getBundle().getString("guestPlayer"));
                menuPanelButtonPressedActions();
            }
        });
    }

    /**
     * This method implements the action listeners for the menuPanel's buttons.For every button (playButton, statsButton, languageButton,
     * menuPanelGoBackButton) it adds the listener to it in order  to change from the menuPanel to the appropriate panel
     * (selectSudokuVersionsPanel, statsPanel, languagePanel, selectUserPanel), (using private method changePanels) and calls private methods
     * SelectSudokuVersionsPanelPressedAction, StatsPanelPressedActions, LanguagePanelPressedActions and SelectUserPanelButtonPressedActions for
     * every button respectively .Also it adds an action listener to the exitButton in order
     * to close the application.
     */
    public void menuPanelButtonPressedActions() {
        menuPanel.getPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!User.isGuest())
                    user = selectUserPanel.getUser();
                selectSudokuVersionsPanel = new SelectSudokuVersionsPanel(internationalization.getBundle());
                changePanels(menuPanel, selectSudokuVersionsPanel);
                SelectSudokuVersionsPanelPressedActions();
            }
        });

        menuPanel.getStatsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!User.isGuest())
                    user = selectUserPanel.getUser();
                statsPanel = new StatsPanel(user,internationalization.getBundle());
                changePanels(menuPanel,statsPanel);
                statsPanelPressedActions();
            }
        });

        menuPanel.getLanguageButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languagePanel = new LanguagePanel(internationalization);
                changePanels(menuPanel,languagePanel);
                languagePanelPressedActions();
            }
        });

        menuPanel.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Exit the game
                sudokuFrame.dispose();
                sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        menuPanel.getMenuPanelGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectUserPanel = new SelectUserPanel(internationalization.getBundle());
                changePanels(menuPanel,selectUserPanel);
                selectUserPanelButtonPressedActions();
            }
        });


    }

    /**
     * This method implements the action Listeners for the SelectSudokuVersionPanel buttons. For buttons killerButton
     * and SelectSudokuVersionPanelGoBackButton it adds an Action Listener to change the panel from SelectSudokuVersionPanel to killerGamePanel and
     * menuPanel respectively and calls methods KillerGamePanelButtonPressedActions and MenuPanelButtonPressedActions respectively. Also it adds an
     * action Listener to SelectSudokuVersionPanel's JMenuItems classic and wordoku in order to load the duidokuGamePanel or classicGamePanel,
     * according to if the user chose to play the classic or wordoku version, of the duidoku or classic sudoku respectively. Also it calls methods
     * ClassicGamePanelPressedActions or DuidokuGamePanelPressedActions if the user chooses to play Classic Sudoku or Duidoku respectively. Also
     * in SelectSudokuVersionPanelGoBackButton listener it adds JLabel background to menuPanel.
     */
    public void SelectSudokuVersionsPanelPressedActions() {

        selectSudokuVersionsPanel.getKillerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                killerGamePanel = new KillerGamePanel(user, internationalization.getBundle());
                changePanels(selectSudokuVersionsPanel,killerGamePanel);
                killerGamePanelButtonPressedActions();
            }
        });

        selectSudokuVersionsPanel.getClassic().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wordoku = false;
                if (e.getActionCommand().equals(internationalization.getBundle().getString("classicButton"))) {
                    classicGamePanel = new ClassicGamePanel(user, '9', '1', 48, internationalization.getBundle());
                    changePanels(selectSudokuVersionsPanel,classicGamePanel);
                    classicGamePanelButtonPressedActions();
                } else{
                    duidokuGamePanel = new DuidokuGamePanel(user,'4','1', 48,internationalization.getBundle());
                    changePanels(selectSudokuVersionsPanel,duidokuGamePanel);
                    duidokuGamePanelPressedActions();
                }
            }
        });
        selectSudokuVersionsPanel.getWordoku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wordoku = true;
                if (e.getActionCommand().equals(internationalization.getBundle().getString("classicButton")))
                {
                    classicGamePanel = new ClassicGamePanel(user, 'I', 'A', 64, internationalization.getBundle());
                    changePanels(selectSudokuVersionsPanel,classicGamePanel);
                    classicGamePanelButtonPressedActions();
                } else {
                    duidokuGamePanel = new DuidokuGamePanel(user,'D','A',64,internationalization.getBundle());
                    changePanels(selectSudokuVersionsPanel,duidokuGamePanel);
                    duidokuGamePanelPressedActions();
                }
            }
        });

        selectSudokuVersionsPanel.getSelectSudokuVersionsPanelGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanels(selectSudokuVersionsPanel,menuPanel);
                menuPanel.add(userName);
                menuPanel.add(background);
            }
        });
    }

    /**
     * This method adds an action Listener to gameGoBackButton JButton and newGameButton for classicGamePanel panel. The gameGoBackButton listener
     * changes the panel between classicGamePanel and selectSudokuVersionPanel and the newGameButton listener refreshes the classicGamePanel. Also it
     * calls method ClassicGamePanelButtonPressedActions at newGameButton listener.
     */
    public void classicGamePanelButtonPressedActions(){
        classicGamePanel.getGameGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanels(classicGamePanel, selectSudokuVersionsPanel);
                selectSudokuVersionsPanel.add(userName);
                selectSudokuVersionsPanel.add(background);
            }
        });

        classicGamePanel.getNewGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classicGamePanel.setVisible(false);
                sudokuFrame.remove(classicGamePanel);
                if (!wordoku) {
                    classicGamePanel = new ClassicGamePanel(user, '9', '1', 48, internationalization.getBundle());
                } else {
                    classicGamePanel = new ClassicGamePanel(user, 'I', 'A', 64, internationalization.getBundle());
                }
                classicGamePanelButtonPressedActions();
                sudokuFrame.add(classicGamePanel);
                classicGamePanel.setVisible(true);
                sudokuFrame.validate();
            }
        });
    }

    /**
     * This method adds an action Listener to gameGoBackButton JButton and newGameButton for killerGamePanel panel. The gameGoBackButton listener
     * changes the panel between killerGamePanel and selectSudokuVersionPanel and the newGameButton listener refreshes the killerGamePanel. Also it
     * calls method ClassicGamePanelButtonPressedActions at newGameButton listener.
     */
    public void killerGamePanelButtonPressedActions(){
        killerGamePanel.getGameGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanels(killerGamePanel, selectSudokuVersionsPanel);
                selectSudokuVersionsPanel.add(userName);
                selectSudokuVersionsPanel.add(background);
            }
        });

        killerGamePanel.getNewGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                killerGamePanel.setVisible(false);
                sudokuFrame.remove(killerGamePanel);
                killerGamePanel = new KillerGamePanel(user, internationalization.getBundle());
                killerGamePanelButtonPressedActions();
                sudokuFrame.add(killerGamePanel);
                killerGamePanel.setVisible(true);
                sudokuFrame.revalidate();
            }
        });
    }

    /**
     * This method adds an action Listener to gameGoBackButton JButton and newGameButton for duidokuGamePanel panel. The gameGoBackButton listener
     * changes the panel between duidokuGamePanel and selectSudokuVersionPanel and the newGameButton listener refreshes the duidokuGamePanel. Also it
     * calls method ClassicGamePanelButtonPressedActions at newGameButton listener.
     */
    public void duidokuGamePanelPressedActions(){
        duidokuGamePanel.getGameGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanels(duidokuGamePanel, selectSudokuVersionsPanel);
                selectSudokuVersionsPanel.add(userName);
                selectSudokuVersionsPanel.add(background);
            }
        });

        duidokuGamePanel.getNewGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                duidokuGamePanel.setVisible(false);
                sudokuFrame.remove(duidokuGamePanel);
                if(!wordoku) {
                    duidokuGamePanel = new DuidokuGamePanel(user,'4','1', 48,internationalization.getBundle());
                }else{
                    duidokuGamePanel = new DuidokuGamePanel(user,'D','A',64,internationalization.getBundle());
                }
                duidokuGamePanelPressedActions();
                sudokuFrame.add(duidokuGamePanel);
                duidokuGamePanel.setVisible(true);
                sudokuFrame.revalidate();
            }
        });
    }

    /**
     * This method is used to add an action Listener to statsPanelGoBackButton in order to change the panel between menuPanel and statsPanel by
     * calling method changePanels. Also it adds JLabel background to menuPanel.
     */
    public void statsPanelPressedActions(){
        statsPanel.getStatsPanelGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanels(statsPanel,menuPanel);
                menuPanel.add(userName);
                menuPanel.add(background);
            }
        });
    }

    /**
     * This method is used to add an action Listener to languagePanelGoBackButton in order to change the panel between languagePanel and menuPanel
     * by calling method changePanels. Also it adds JLabel background to menuPanel.
     */
    public void languagePanelPressedActions(){
        languagePanel.getLanguagePanelGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanels(languagePanel,menuPanel);
                menuPanel.menuPanelComponentsText(internationalization.getBundle());
                menuPanel.add(userName);
                menuPanel.add(background);
            }
        });
    }

    /** This method is used to change between to panels.
     * @param oldPanel This parameter is the panel from which the user leaves.
     * @param newPanel This parameter is the panel to which the user gets to.
     */
    private void changePanels(JPanel oldPanel, JPanel newPanel){
        oldPanel.setVisible(false);
        sudokuFrame.remove(oldPanel);
        sudokuFrame.add(newPanel);
        newPanel.setVisible(true);
        sudokuFrame.validate();
    }

}