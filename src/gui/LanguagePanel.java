package gui;

import logic.Internationalization;
import logic.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** This is a GUI class. This class is the panel for the language selection menu. This class is a JPanel model, it extends JPanel
 * @author Velisarios 
 */
public class LanguagePanel extends  JPanel{
    /** englishButton and greekButton are JButtons used for the selection of program's language(greekButton for greek language and englishButton for english language).
     * languagePanelGoBackButton is a JButton to leave this panel.
     */
    private JButton englishButton, greekButton, languagePanelGoBackButton;
    /** languageLabel is the JLabel with the title of this panel.
     */
    private JLabel languageTitle;
    /** Variable languageLabelFont is the Font used for languageLabel.
     */
    private Font languageTitleFont;
    /** greekFlag and ukFlag are images that are being added to greekButton and englishButton respectively.
     */
    private Icon greekFlag, ukFlag;
    /** internationalization variable is an Internationalization class' object which is used to change language.
     */
    private Internationalization internationalization;

    /** Constructor that gives the value to internationalization and initializes all the JButtons, Icons, JLabels and Fonts in this class, using Strings from class'
     * Internationalization Resource Bundle, for every text. Also it calls private method setUpLanguagePanel.
     * greekFlag image: {@link}  https://en.wikipedia.org/wiki/Flag_of_Greece, ukFlag image {@link} https://en.wikipedia.org/wiki/Flag_of_the_United_Kingdom
     * @param anInternationalization Parameter that determines variable's internationalization value.
     */
    public LanguagePanel(Internationalization anInternationalization){
        internationalization = anInternationalization;
        greekFlag = new ImageIcon("GreekFlag.png");
        ukFlag = new ImageIcon("UkFlag.png");
        greekButton = new JButton(greekFlag);
        englishButton = new JButton(ukFlag);
        languagePanelGoBackButton = new JButton(internationalization.getBundle().getString("backButton"));
        languageTitle = new JLabel(internationalization.getBundle().getString("languageLabel"));
        languageTitleFont = new Font("Arial", Font.PLAIN, 30);
        setUpLanguagePanel();
    }

    /** This method sets the size and position of all the JButtons and JLabels in this panel and add Font to languageLabel. Also it sets panel's layout to null and calls
     * private method setButtonListeners. Finally it adds public JLabel background (from class SudokuFrame)
     * @see SudokuFrame
     */
    private void setUpLanguagePanel(){
        englishButton.setBounds(180,220,100,100);
        add(englishButton);

        greekButton.setBounds(380,220,100,100);
        add(greekButton);

        languagePanelGoBackButton.setBounds(61, 585, 70, 20);
        languagePanelGoBackButton.setBackground(Color.WHITE);
        add(languagePanelGoBackButton);

        languageTitle.setBounds(70, 40, 500, 40);
        languageTitle.setFont(languageTitleFont);
        languageTitle.setHorizontalAlignment(JLabel.CENTER);
        add(languageTitle);


        setButtonListeners();
        add(SudokuFrame.userName);
        add(SudokuFrame.background);
        setLayout(null);
    }

    /** This method adds an ActionListener to englishButton and greekButton. If englishButton is pressed method setLanguageEnglish from class Internationalization is called
     * and if greekButton is pressed method setLanguageGreek from same class is called. Also in both cases it sets the text of languageLabel, userNameLabel(from SudokuFrame class
     * ) and languagePanelGoBackButton, in the new language.
     */
    private void setButtonListeners(){
        englishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                internationalization.setLanguageEnglish();
                languageTitle.setText(internationalization.getBundle().getString("languageLabel"));
                languagePanelGoBackButton.setText(internationalization.getBundle().getString("backButton"));
                if (User.isGuest())
                    SudokuFrame.userName.setText(internationalization.getBundle().getString("guestPlayer"));
            }
        });
        greekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                internationalization.setLanguageGreek();
                languageTitle.setText(internationalization.getBundle().getString("languageLabel"));
                languagePanelGoBackButton.setText(internationalization.getBundle().getString("backButton"));
                if(User.isGuest())
                    SudokuFrame.userName.setText(internationalization.getBundle().getString("guestPlayer"));
            }
        });
    }
    /** This method returns languagePanelGoBackButton in order to add ActionListener to it wherever it is needed.
     * @return JButton languagePanelGoBackButton
     */
    public JButton getLanguagePanelGoBackButton() {
        return languagePanelGoBackButton;
    }

}
