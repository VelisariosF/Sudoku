package GUI;

import Logic.User;
import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * The StatsPanel implements the panel where the user can see they're statistics (if they are not playing as guest).
 * @author Velisarios 
 */

public class StatsPanel extends JPanel {
    /**
     * The user variable represents the user that they're statistics will appear to the stats panel.
     */
    private User user;
    /**
     * The classicPuzzlesSolvedTitle, killerPuzzlesSolvedTitle, duidokuWinsTitle, duidokuLossesTitle variables represent the titles
     * for the classic sudoku puzzles the user has solve, the killer sudoku puzzles the user has solve, the duidoku wins and duidoku deafeats
     * the user has against the com respectively.Also the classicPuzzlesSolvedData, killerPuzzlesSolvedData,  duidokuWinsData, duidokuLossesData
     * labels represent the values of the stats(classic puzzles and killer puzzles solved, duidoku wins and duidoku defeats). The  guestMessage
     * is JLabel that appears if the user is playing as guest, in order to inform that there are no statistics for guests.
     */
    private JLabel classicPuzzlesSolvedTitle, killerPuzzlesSolvedTitle, duidokuWinsTitle, duidokuLossesTitle,
            classicPuzzlesSolvedData, killerPuzzlesSolvedData,  duidokuWinsData, duidokuLossesData, guestMessage;
    /**
     * The aFont variable represents a Font that is applied to the stats titles.
     */
    private Font aFont;
    /**
     * The statsPanelGoBackButton variable represents the button that allows the user to return back to the main menu of the application.
     */
    private JButton statsPanelGoBackButton;


    /**
     * The StatsPanel constructor gets as a parameter a user object and a ResourceBundle object.The aBundle variable is used to set the
     * titles at the appropriate language.Then the constructor sets the user variable
     * and the StatsPanel appearance by calling the setUpStatsPanel method.Also it initializes all the objects that
     * will be used.
     * @param aUser This is the user that they're statistics will be displayed on screen.
     * @param aBundle
     */
    public StatsPanel(User aUser, ResourceBundle aBundle) {
        this.user = aUser;
        statsPanelGoBackButton = new JButton(aBundle.getString("backButton"));
        aFont = new Font("Arial", Font.PLAIN, 20);
        if (!User.isGuest()) {
            classicPuzzlesSolvedTitle = new JLabel(aBundle.getString("classicStats"));
            killerPuzzlesSolvedTitle = new JLabel(aBundle.getString("killerStats"));
            duidokuWinsTitle = new JLabel(aBundle.getString("duidokuWins"));
            duidokuLossesTitle = new JLabel(aBundle.getString("duidokuLosses"));
            classicPuzzlesSolvedData = new JLabel(user.getClassicPPlayed());
            killerPuzzlesSolvedData = new JLabel(user.getKillerPPlayed());
            duidokuWinsData = new JLabel(String.valueOf(user.getDuidokuWins()));
            duidokuLossesData = new JLabel(String.valueOf(user.getDuidokuLosses()));
        } else {
            guestMessage = new JLabel(aBundle.getString("guestStats"));
        }
        setUpStatsPanel();
    }

    /**
     * This method is used to set up all of the JLabels (besides guestMessage, if the user isn't a guest) or
     * only guestMessage's (if the user is guest)  titles , height, width, visibility and position at the
     * stats panel. Also in both cases (guest or not) it sets statsPanelGoBackButton above characteristics.
     */
    public void setUpStatsPanel(){
        if (!User.isGuest()) {
            classicPuzzlesSolvedTitle.setFont(aFont);
            classicPuzzlesSolvedTitle.setBounds(100, 100, 500, 40);
            classicPuzzlesSolvedTitle.setVisible(true);
            add(classicPuzzlesSolvedTitle);

            classicPuzzlesSolvedData.setBounds(100, 130, 200, 40);
            classicPuzzlesSolvedData.setFont(aFont);
            classicPuzzlesSolvedData.setVisible(true);
            add(classicPuzzlesSolvedData);

            killerPuzzlesSolvedTitle.setFont(aFont);
            killerPuzzlesSolvedTitle.setBounds(100, 160, 500, 40);
            killerPuzzlesSolvedTitle.setVisible(true);
            add(killerPuzzlesSolvedTitle);

            killerPuzzlesSolvedData.setBounds(100, 190, 200, 40);
            killerPuzzlesSolvedData.setFont(aFont);
            killerPuzzlesSolvedData.setVisible(true);
            add(killerPuzzlesSolvedData);


            duidokuWinsTitle.setBounds(100, 230, 180, 40);
            duidokuWinsTitle.setForeground(Color.BLUE);
            duidokuWinsTitle.setFont(aFont);
            duidokuWinsTitle.setVisible(true);
            add(duidokuWinsTitle);

            duidokuWinsData.setBounds(duidokuWinsTitle.getX() + 180, 230, 70, 40);
            duidokuWinsData.setFont(aFont);
            duidokuWinsData.setVisible(true);
            add(duidokuWinsData);


            duidokuLossesTitle.setBounds(100, 273, 180, 40);
            duidokuLossesTitle.setFont(aFont);
            duidokuLossesTitle.setForeground(Color.RED);
            duidokuLossesTitle.setVisible(true);
            add(duidokuLossesTitle);

            duidokuLossesData.setBounds(duidokuLossesTitle.getX() + 180, 273, 70, 40);
            duidokuLossesData.setFont(aFont);
            duidokuLossesData.setVisible(true);
            add(duidokuLossesData);
        }
        else {
            guestMessage.setBounds(120, 225, 500, 100);
            guestMessage.setForeground(Color.BLUE);
            guestMessage.setFont(new Font("Arial", Font.BOLD, 30));
            add(guestMessage);
        }
        statsPanelGoBackButton.setBounds(61, 585, 70, 20);
        statsPanelGoBackButton.setBackground(Color.WHITE);
        statsPanelGoBackButton.setVisible(true);

        add(statsPanelGoBackButton);

        add(SudokuFrame.userName);
        add(SudokuFrame.background);
        setLayout(null);
        setVisible(true);
    }

    /**
     * This method returns the button that if pressed the user returns back to the main menu.
     * @return JButton This returns the statsPanelGoBackButton.
     */
    public JButton getStatsPanelGoBackButton(){
        return statsPanelGoBackButton;
    }
}
