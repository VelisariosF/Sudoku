package gui;

import logic.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class implements the panel from where the user can create a new account, choose to play as an existing user or play as an anonymous guest user..
 * @author Velisarios 
 */
public class SelectUserPanel extends JPanel {
    /**
     * The createUserLabel, noUserAddedLabel, userAlreadyAddedLabel, blankUserAdded variables represent the title of the create user text field,
     * the JLabel that appears when, the select user button is pressed, no user has been created and the message that appears if the user tries
     * to create an account that already exists or create a user with no name respectively.
     */
    private JLabel createUserLabel, noUserAddedLabel, userAlreadyAddedLabel, blankUserAdded;
    /**
     * The selectUserButton, createUserButton and guestPlayerButton variables represent the buttons that allow the user to choose to play as an existing user,
     * create a new user account or play as a guest respectively.
     */
    private JButton selectUserButton, createUserButton, guestPlayerButton;
    /**
     * The createUserTextField variable represents the text field where the player can insert name of the new user that they try to create.
     */
    private JTextField createUserTextField;
    /**
     * The selectUserPopUpMenu is the menu that contains the names of the already added users and appears when the select user button is pressed.
     */
    private JPopupMenu selectUserPopUpMenu;
    /**
     * The userNameMenuItem is an item that contains a user's name and is being added to the selectUserPopUpMenu.
     */
    private JMenuItem userΝameMenuItem;
    /**
     * The userNamesMenuItems is an ArrayList that contains all the userNameMenuItems.
     */
    private ArrayList<JMenuItem> userNamesMenuItems;
    /**
     * The useNamesList represents a list with the names of every user that has been created.
     */
    private ArrayList<String> userNamesList;
    /**
     * The userObjects variable is an ArrayList that contains objects of the User class which basically are all the users tha have been
     * created along with their statistics.
     */
    private ArrayList<User> userObjects;
    /**
     * The user variable is an object of the User class and represents a user.
     * @see User
     */
    private User user;


    /**
     * The nameInserted variable represents the name that the user inserted when creating a new user.
     */
    private String  nameInserted = " ";

    /**
     * The font variable is a Font that is applied to the labels of the SelectUserPanel class.
     */
    private Font font;

    /**
     * The constructor of the SelectUserPanel class gets as parameter an object of the ResourceBundle class which is used to translate the
     * language of the titles.Also  initializes the labels,buttons,font,textField and the ArrayLists that will
     * be used.Finally it sets up the select user panel by calling the setUpSelectUserPanel method.
     * @param aBundle This is the only parameter of the constructor.
     */
    public SelectUserPanel(ResourceBundle aBundle) {
        selectUserButton = new JButton(aBundle.getString("selectUserButton"));
        createUserButton = new JButton(aBundle.getString("createUserButton"));
        guestPlayerButton = new JButton(aBundle.getString("guestPlayer"));
        createUserLabel = new JLabel(aBundle.getString("userLabel"));
        noUserAddedLabel = new JLabel(aBundle.getString("noUserLabel"));
        userAlreadyAddedLabel = new JLabel(aBundle.getString("userAlreadyAddedLabel"));
        blankUserAdded = new JLabel(aBundle.getString("emptyUserName"));
        font = new Font("Arial", Font.BOLD, 17);
        createUserTextField = new JTextField();
        userObjects = new ArrayList<>();
        userNamesList = new ArrayList<>();
        setUpSelectUserPanel();
    }

    /**
     * This method is used to set up the panel's titles and all of its components size, color and the position that appear inside the
     * select user panel.
     */
    public void setUpSelectUserPanel(){
        noUserAddedLabel.setFont(font);
        noUserAddedLabel.setHorizontalAlignment(JLabel.CENTER);
        noUserAddedLabel.setBounds(100, 322, 500, 40);
        noUserAddedLabel.setForeground(Color.BLACK);
        noUserAddedLabel.setVisible(false);
        add(noUserAddedLabel);

        userAlreadyAddedLabel.setFont(font);
        userAlreadyAddedLabel.setBounds(200,322,400,40);
        userAlreadyAddedLabel.setVisible(false);
        add(userAlreadyAddedLabel);

        blankUserAdded.setFont(font);
        blankUserAdded.setBounds(200,322,400,40);
        blankUserAdded.setVisible(false);
        add(blankUserAdded);

        createUserButton.setBounds(200,200,200,40);
        createUserButton.setBackground(Color.WHITE);
        add(createUserButton);

        selectUserButton.setBounds(200, 242, 200, 40);
        selectUserButton.setBackground(Color.WHITE);
        add(selectUserButton);

        guestPlayerButton.setBounds(200,284,200,40);
        guestPlayerButton.setBackground(Color.WHITE);
        add(guestPlayerButton);

        createUserLabel.setFont(font);
        createUserLabel.setBounds(200,125,250,40);
        createUserLabel.setVisible(true);
        add(createUserLabel);

        createUserTextField.setBounds(200,158 , 200, 40);
        add(createUserTextField);
        add(SudokuFrame.background);
        initThePopUpMenuNames();
        componentsListeners();

        setVisible(true);
        setLayout(null);
    }

    /**
     * This method implements the listeners for the select user button.Basically if the button is pressed the listener checks if there are any
     * users created and if so the menu, from where the user can choose to play as an existing user, appears or if not then the noUserAddedLabel
     * shows off.Also this method implements the createUserButton listener.This listener checks if the name that has just ben inserted already exists
     * and if so,the userAlreadyAdded label, if not the new account is being created.The user is being created by creating a new object of the
     * User class with the name tha has been inserted, creating the users file using the createUserFile method from the User class,inserting
     * the users statistics inside they're file calling the insertStatisticsInTheFile method and writing the new user name to the
     * UsersNames.txt by calling the writeTheNewUserNames method.
     */
    public void componentsListeners(){
        selectUserButton.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userObjects.size() == 0) {
                    noUserAddedLabel.setVisible(true);
                }else
                    selectUserPopUpMenu.show(selectUserButton,selectUserButton.getWidth()+2,0);
                userAlreadyAddedLabel.setVisible(false);
                blankUserAdded.setVisible(false);

            }
        });

        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noUserAddedLabel.setVisible(false);
                nameInserted = createUserTextField.getText();
                if (!userNamesList.contains(createUserTextField.getText()) && !nameInserted.isBlank() ) {
                    user = new User(nameInserted);
                    user.createUserFile();
                    user.insertStatisticsInTheFile();
                    writeTheNewUserNames(user.getName());
                }
                else if (userNamesList.contains(createUserTextField.getText()) ) {
                    userAlreadyAddedLabel.setVisible(true);
                    blankUserAdded.setVisible(false);
                }else if(nameInserted.isBlank()){
                    blankUserAdded.setVisible(true);
                    userAlreadyAddedLabel.setVisible(false);
                }
            }
        });
    }

    /**
     * This method is used to read the names, of the already added users from the UsersNames.txt file and also adds those names
     * to the userNamesList.
     */
    public void readTheNamesFromTheFile(){
        try (BufferedReader in = new BufferedReader(
                new FileReader("UsersNames.txt"))
        ) {

            String l;
            while ((l = in.readLine()) != null) {
                StringBuilder n = new StringBuilder();

                for (int j = 0; j < l.length(); j++) {
                    n.append(l.charAt(j));
                }
                userNamesList.add(n.toString());
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    /**
     * This method is used to insert a name to the UsersNames.txt file.
     * @param aName This parameter represents the name to be written in the file.
     */
    public void writeTheNewUserNames(String aName){
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter("UsersNames.txt",true))
        ){
            out.write(aName + "\n");

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This method returns the button that allows the player to create a new user account.
     * @return JButton This returns the createUserButton variable.
     */
    public JButton getCreateUserButton(){
        return createUserButton;
    }

    /**
     * This method returns the button that allows the player to play as guest.
     * @return JButton This return the guestPlayerButton variable.
     */
    public JButton getGuestPlayerButton() {
        return guestPlayerButton;
    }

    /**
     * This method returns the user that the player has created or chosen to play as.
     * @return User This returns an object of the User class.
     */
    public User getUser() {
        return user;
    }

    /**
     * This method creates the pop up menu that appears when the player presses the select user button.First it calls the
     * readTheNamesFromTheFile and createUserObjects methods.Then it initializes the useNamesMenuItems ArrayLists along with
     * selectUserPopUpMenu.Also in every item of the pop up menu has a listener that gives to the user variable the info
     * of the user that is chosen form the player.
     */
    public void initThePopUpMenuNames(){
        readTheNamesFromTheFile();
        createUserObjects();
        userNamesMenuItems = new ArrayList<>();
        selectUserPopUpMenu = new JPopupMenu();
        selectUserPopUpMenu.setBackground(Color.WHITE);
        if(userObjects.size() != 0) {
            for (User aUser : userObjects) {
                userΝameMenuItem = new JMenuItem();
                userΝameMenuItem.setText(aUser.getName());
                userΝameMenuItem.setBackground(Color.WHITE);
                userΝameMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(User aUser : userObjects){
                            if(e.getActionCommand().equals(aUser.getName())){
                                user = aUser;
                            }
                        }
                    }
                });
                selectUserPopUpMenu.add(userΝameMenuItem);
                userNamesMenuItems.add(userΝameMenuItem);
            }
        }
    }

    /**
     * This method returns all the pop menu items.
     * @return ArrayList This returns the userNamesMenuItems ArrayList.
     */
    public ArrayList<JMenuItem> getUserNamesMenuItems() {
        return userNamesMenuItems;
    }

    /**
     * This method creates User objects with the names thea exists in the UsersNames.txt file and adds them to the userObjects ArrayList.
     */
    public void createUserObjects(){
        for(String userName : userNamesList) {
            User aUser = new User(userName);
            aUser.readTheStatisticsFromTheFile();
            userObjects.add(aUser);
        }
    }

    /**
     * This method returns the list with the names that exist inside the UsersNames.txt file.
     * @return ArrayList This returns the userNamesList ArrayList.
     */
    public ArrayList<String> getUserNamesList() {
        return userNamesList;
    }

    /**
     * This method returns the text field to which the user inserts the name of the new account they create.
     * @return JTextField This returns the createUserTextField JTextField.
     */
    public JTextField getCreateUserTextField() {
        return createUserTextField;
    }
}