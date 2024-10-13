import logic.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InitializeTest {

    @Test
    public void readTheDataFromTheFiles() {
        Initialize initialize1 = new Initialize(true, false);
        assertFalse(initialize1.getExceptionThrowedFromReadingTheClassicFile());
        Initialize initialize2 = new Initialize(false, true);
        assertFalse(initialize2.getExceptionThrowedFromReadingTheKillerFile());
    }
}