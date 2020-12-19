package Logic;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * This class implements the initialization of the classic sudoku and killer sudoku puzzles from the files.
 * @author Velisarios
 */

public class Initialize {
    /**
     * The Inititalize class uses two Arrays of 10 ArrayLists of Characters each.The classicPuzzles array is used to store the values, of the puzzles
     * of the classic sudoku version of the sudoku game, from the ClassicPuzzles.txt file and the killerPuzzles exists for the same reason as the
     * classicPuzzles but this is for the puzzzles of the killer sudoku version.Also it has four boolean variables.The isClassic and isKiller
     * variables are to check which version of the game has been chosen, from the user to play, in order to load the proper puzzles.The
     * exceptionThrowedFromReadingTheClassicFile and exceptionThrowedFromReadingTheKillerFile are used to understand which file has thrown an
     * IOException while trying to read it.
     */
    private ArrayList<Character>[] classicPuzzles = new ArrayList[10], killerPuzzles = new ArrayList[10];
    private boolean isClassic, isKiller, exceptionThrowedFromReadingTheClassicFile = false, exceptionThrowedFromReadingTheKillerFile = false;

    /**
     * The conrstructor of the Initialize class gets two boolean parameters in order to understand from which file between ClassicPuzzles.txt and
     * KillerPuzzles.txt,to load the puzzles from.For example, if the isCl is true it means that the program must the puuzles from the
     * ClassicPuzzles.txt .The same thing applies to the killer sudoku puzzles.The loading of the puzzles is being done by the readTheDataFromTheFiles
     * method.
     * @param isCl This is the first parameter of the constructor.
     * @param isKil This is the second parameter of the constructor.
     */
    public Initialize(boolean isCl, boolean isKil){
        this.isClassic = isCl;
        this.isKiller = isKil;
        readTheDataFromTheFiles();

    }

    /**
     * This method is used to load the puzzles for the classic and killer sudoku versions of the sudoku game.According to the boolean variables
     * isClassic and isKiller the method initializes the proper array of ArrayLists and dds to them the puzzles that the method read from the file.
     */
    public void readTheDataFromTheFiles(){

        if(isClassic) {
            for(int i = 0; i < 10; i++){
                classicPuzzles[i] = new ArrayList<>();
            }
            try (BufferedReader in = new BufferedReader(
                    new FileReader("ClassicPuzzles.txt"));
            ){
                String l;
                int i = 0;
                while ((l = in.readLine()) != null) {

                    if(l.isEmpty()){
                        i ++;
                        continue;
                    }
                    for (int j=0;j<l.length();j++){
                        classicPuzzles[i].add(l.charAt(j));
                    }

                }
            }catch(IOException e){
                exceptionThrowedFromReadingTheClassicFile = true;
                e.printStackTrace();
            }
        }else if(isKiller){

            for(int i = 0; i < 10; i++){
                killerPuzzles[i] = new ArrayList<>();
            }
            try(BufferedReader in = new BufferedReader(new FileReader("KillerPuzzles.txt"));){
                String l;
                int i = 0;
                while ((l = in.readLine()) != null) {
                    if(l.isEmpty()){
                        i ++;
                        continue;
                    }
                    for (int j=0;j<l.length();j++){

                        killerPuzzles[i].add(l.charAt(j));
                    }
                }
            }catch(IOException e){
                exceptionThrowedFromReadingTheKillerFile = true;
                e.printStackTrace();
            }

        }

    }

    /**
     * This method returns all the puzzles for the classic sudoku version.
     * @return ArrayList This returns the classicPuzzles ArrayLists.
     */
    public ArrayList<Character>[] getClassicPuzzles() {
        return classicPuzzles;
    }

    /**
     * This method returns all the puzzles for the killer sudoku version.
     * @return ArrayList This returns the KillerPuzzles ArrayLists.
     */
    public ArrayList<Character>[] getKillerPuzzles() {
        return killerPuzzles;
    }

    /**
     * This method is returns whether or not an IOException is thrown while reading the ClassicPuzzles.txt
     * @return boolean This returns true if there was an Exception and false otherwise.
     */
    public boolean getExceptionThrowedFromReadingTheClassicFile() {
        return exceptionThrowedFromReadingTheClassicFile;
    }

    /**
     * This method is returns whether or not an IOException is thrown while reading the KillerPuzzles.txt
     * @return boolean This returns true if there was an Exception and false otherwise.
     */
    public boolean getExceptionThrowedFromReadingTheKillerFile() {
        return exceptionThrowedFromReadingTheKillerFile;
    }
}
