package Logic;

import java.io.*;
import java.util.HashSet;
/**
 * This class implements a user that plays the Sudoku Game.
 * @author Velisarios 
 */
public class User {
    /**
     * A User consists of their name, their file that keeps their statistics for the game,
     * two variables duidokuWins and duidokuLosses that we keep the wins and defeats for the duidoku version.Also there two HashSets in this class
     * classicPuzzlesPlayed and killerPuzzlesPlayed that keep the puzzles that the user has played for the classic and killer Sudoku versions
     * respectively.Moreover there are two more boolean variables exceptionThrowedByInsertMethod and exceptionThrowedByReadMethod.The first one
     * checks is used to understand if the code inside the insertStatisticsInTheFile method throws IOException.The exceptionThrowedByReadMethod is
     * is used to understand if the code inside the readTheStatisticsFromTheFile method throws IOException.
     */
    private int  duidokuWins, duidokuLosses;
    private HashSet<Integer> classicPuzzlesPlayed, killerPuzzlesPlayed;
    private String name;
    private File userStatistics;
    private boolean exceptionThrowedByInsertMethod = false, exceptionThrowedByReadMethod = false;
    /**
     *  Variable isGuest defines if the user is playing as guest.
     */
    private static boolean isGuest;

    /**
     * User constructor that takes a name as a parameter,it initializes the users's name and also the two hashSets in order to add, later,
     * the puzzles tha the user has solved.
     * @param aName This is the name of the user.
     */
    public User(String aName){
        this.name = aName;
        classicPuzzlesPlayed = new HashSet<>();
        killerPuzzlesPlayed = new HashSet<>();
    }

    /**
     * The getClassicPuzzlesPlayed method returns all the puzzles that the user has solved for the classic version of sudoku.
     * @return HashSet This returns a hashSet of integers.
     */
    public HashSet<Integer> getClassicPuzzlesPlayed() {
        return classicPuzzlesPlayed;
    }

    /**
     * The getKillerPuzzlesPlayed method returns all the puzzles that the user has solved for the killer version of sudoku.
     * @return HashSet This returns a hashSet of integers.
     */
    public HashSet<Integer> getKillerPuzzlesPlayed() {
        return killerPuzzlesPlayed;
    }

    /**
     * The getClassicPPlayed method returns all the puzzles that the user has solved for the classic version of sudoku in a form of a string.
     * @return String This returns a string that represents the solved puzzles of the classic sudoku.
     */
    public String getClassicPPlayed(){
        StringBuilder c = new StringBuilder();
        for (int x : classicPuzzlesPlayed) {
            c.append((x+1));
            c.append(' ');
        }
        return c.toString();
    }

    /**
     * The getKillerPPlayed method returns all the puzzles that the user has solved for the classic version of sudoku in a form of a string.
     * @return String This returns a string that represents the solved puzzles of the killer sudoku.
     */
    public String getKillerPPlayed(){
        StringBuilder c = new StringBuilder();
        for (Integer x : killerPuzzlesPlayed) {
            c.append((x+1));
            c.append(' ');
        }
        return c.toString();
    }


    /**
     * The addValueToClassicPuzzlesPlayed method is used to add an integer, to the classicPuzzlesPlayed, that represents the puzzle that the user
     * has solved.
     * @param i This is the number of the puzzle that has been solved.
     */
    public void addValueToClassicPuzzlesPlayed(int i){
        classicPuzzlesPlayed.add(i);
    }

    /**
     * The addValueToKillerPuzzlesPlayed methos is used to add an integer, to the killerPuzzlesPlayed, that represents the puzzle that the user
     * has solved.
     * @param i This is the number of the puzzle that has been solved.
     */
    public void addValueToKillerPuzzlesPlayed(int i){
        killerPuzzlesPlayed.add(i);
    }

    /**
     * The addWins method increases the duidokuWins variable by one every time the user gets a win in the duidoku version of sudoku game.
     */
    public void addWins(){
        this.duidokuWins++;
    }

    /**
     * The addWins method increases the duidokuWins variable by one every time the user gets a win in the duidoku version of sudoku game.
     */
    public void addLosses(){
        this.duidokuLosses++;
    }

    /**
     * The checkIfExistsInKillerPuzzlesPlayed method checks, using the .contains() method of the hashSet,
     * if a specific killer sudoku puzzle, represented by a number, exists in the
     * KillerPuzzlesPlayd hashSet.
     * @param value This parameter represents the number of the puzzle.
     * @return boolean This returns true if the specific value exists in the hashSet.
     */
    public boolean checkIfExistsInKillerPuzzlesPlayed(int value){
        return killerPuzzlesPlayed.contains(value);
    }

    /**
     * The checkIfExistsInClassicPuzzlesPlayed method does the same thing as the checkIfExistsInKillerPuzzlesPlayed method but for the classic
     * sudoku puzzles.
     * @param value This parameter represents the number of the puzzle.
     * @return boolean This returns true if the specific value exists in the hashSet.
     */
    public boolean checkIfExistsInClassicPuzzlesPlayed(int value){
        return classicPuzzlesPlayed.contains(value);
    }

    /**
     * The insertStatisticsInTheFile method writes the statistics of the user for the game, in a file named by user's name, in order to save them
     * for the next time that the user chooses to play with his account.
     */
    public void insertStatisticsInTheFile(){
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter("Users Files/"+name, false))
        ){
            for (Integer x : classicPuzzlesPlayed) {
                out.write(String.valueOf(x));
            }
            out.write("\n");


            for (Integer x : killerPuzzlesPlayed) {
                out.write(String.valueOf(x));
            }
            out.write("\n");

            out.write(String.valueOf(duidokuWins));
            out.write("\n");

            out.write(String.valueOf(duidokuLosses));

        }catch(IOException e){
            exceptionThrowedByInsertMethod = true;
            e.printStackTrace();
        }
    }

    /**
     * The readTheStatisticsFromTheFile method is used to read the user's statistics from its file..
     */
    public void readTheStatisticsFromTheFile(){
        try (BufferedReader in = new BufferedReader(
                new FileReader("Users Files/"+name))
        ) {

            String l;
            int k = 0;
            while ((l = in.readLine()) != null) {
                if (k == 0) {
                    for (int j = 0; j < l.length(); j++) {
                        classicPuzzlesPlayed.add(Character.getNumericValue(l.charAt(j)));

                    }
                } else if (k == 1) {
                    for (int j = 0; j < l.length(); j++) {
                        killerPuzzlesPlayed.add(Character.getNumericValue(l.charAt(j)));

                    }
                } else if (k == 2) {
                    this.duidokuWins = Integer.parseInt(l);

                } else if (k == 3) {

                    this.duidokuLosses = Integer.parseInt(l);
                }

                k++;

            }


        } catch (IOException e) {
            exceptionThrowedByReadMethod = true;
            e.printStackTrace();
        }

    }

    /**
     * The createUserFile method is used to create the user's file and creates the folder to which the users files, that hold
     * their statistics, will be saved.
     */
    public void createUserFile(){
        File users = new File("Users Files");
        if(!users.exists()){
            users.mkdir();
        }
        userStatistics = new File("Users Files/"+name);
    }

    /**This method defines isGuest's value
     *
     * @param isGuest Variable's isGuest value
     */
    public static void setGuest(boolean isGuest){
        User.isGuest = isGuest;
    }

    /** This method returns boolean that defines if the user is playing as guest.
     * @return boolean isGuest
     */
    public static boolean isGuest(){
        return isGuest;
    }
    /**
     * The getName method is used to get the user's name.
     * @return String This returns the user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * The getDuidokuWins method returns how many wins, in duidoku version of sudoku, the user has.
     * @return int This returns the user's duidoku wins.
     */
    public int getDuidokuWins() {
        return duidokuWins;
    }

    /**
     * The getDuidokuLosses method returns how many defeats, in duidoku version of sudoku, the user has.
     * @return int This returns the user's duidoku defeats.
     */
    public int getDuidokuLosses() {
        return duidokuLosses;
    }

    /**
     * The getExceptionThrowedByInsertMethod method return's whether or not there is an IOException in the insertStatisticsInTheFile.
     * @return boolean This returns true if there is an IOException in the insertStatisticsInTheFile.
     */
    public boolean getExceptionThrowedByInsertMethod() {
        return exceptionThrowedByInsertMethod;
    }

    /**
     * The getExceptionThrowedByReadMethod method return's whether or not there is an IOException in the readTheStatisticsFromTheFile.
     * @return boolean This returns true if there is an IOException in the readTheStatisticsFromTheFile.
     */
    public boolean getExceptionThrowedByReadMethod() {
        return exceptionThrowedByReadMethod;
    }

    /**
     * The getUserStatistics method return's the user's file.
     * @return File This return's a file.
     */
    public File getUserStatistics() {
        return userStatistics;
    }
}
