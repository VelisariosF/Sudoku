import logic.*;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class InternationalizationTest {

    @Test
    public void setLanguageGreek() {
        Internationalization internationalization = new Internationalization();
        Locale locale = new Locale("el","GR");
        ResourceBundle bundle = ResourceBundle.getBundle("Languages",locale);
        internationalization.setLanguageGreek();
        assertEquals(bundle,internationalization.getBundle());
    }

    @Test
    public void setLanguageEnglish() {
        Internationalization internationalization = new Internationalization();
        Locale locale = new Locale("en","US");
        ResourceBundle bundle = ResourceBundle.getBundle("Languages",locale);
        internationalization.setLanguageEnglish();
        assertEquals(bundle,internationalization.getBundle());
    }
}