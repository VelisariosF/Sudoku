import logic.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {


    @Test
    public void getClassicPPlayed() {
        User user = new User("David");
        for(int i = 0; i < 5; i++){
            user.addValueToClassicPuzzlesPlayed(i);
        }
        assertEquals("1 2 3 4 5 ", user.getClassicPPlayed());
    }

    @Test
    public void getKillerPPlayed() {
        User user = new User("David");

        for(int i = 0; i < 5; i++){
            user.addValueToKillerPuzzlesPlayed(i);
        }
        assertEquals("1 2 3 4 5 ", user.getKillerPPlayed());
    }

    @Test
    public void getDuidokuWins(){
        User user = new User("David");
        user.addWins();
        user.addWins();
        assertEquals(2,user.getDuidokuWins());
    }

    @Test
    public void getDuidokuLosses(){
        User user = new User("David");
        user.addLosses();
        user.addLosses();
        assertEquals(2,user.getDuidokuLosses());
    }

    @Test
    public void checkIfExistsInKillerPuzzlesPlayed() {
        User user = new User("David");
        for(int i = 0; i < 5; i++){
            user.addValueToKillerPuzzlesPlayed(i);
        }
        assertEquals(true, user.checkIfExistsInKillerPuzzlesPlayed(0));
        assertEquals(false,user.checkIfExistsInKillerPuzzlesPlayed(5));
    }

    @Test
    public void checkIfExistsInClassicPuzzlesPlayed() {
        User user = new User("David");
        for(int i = 0; i < 5; i++){
            user.addValueToClassicPuzzlesPlayed(i);
        }
        assertEquals(true, user.checkIfExistsInClassicPuzzlesPlayed(0));
        assertEquals(false,user.checkIfExistsInClassicPuzzlesPlayed(5));
    }

    @Test
    void createUserFile() {
        User user = new User("David");
        user.createUserFile();
        assertTrue(user.getUserStatistics().exists());
    }

    @Test
    void insertStatisticsInTheFile() {
        User user = new User("David");
        user.createUserFile();
        user.insertStatisticsInTheFile();
        assertFalse(user.getExceptionThrowedByInsertMethod());
    }

    @Test
    void readTheStatisticsFromTheFile() {
        User user = new User("David");
        user.createUserFile();
        user.insertStatisticsInTheFile();
        user.readTheStatisticsFromTheFile();
        assertFalse(user.getExceptionThrowedByReadMethod());

    }
}