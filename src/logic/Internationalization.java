package logic;

import java.util.Locale;
import java.util.ResourceBundle;
/** This is a class used for Internationalization. It is used to set the project's Strings' language.
 */
public class Internationalization{
    /** Variable locale is the language/country of the program.
     */
    private Locale locale;
    /** Variable bundle is the Resource Bundle that loads the Language properties file
     */
    private ResourceBundle bundle;
    /** Constructor that sets local to Default and loads the respectively Resource Bundle.
     */
    public Internationalization(){
        locale = Locale.getDefault();
        bundle = ResourceBundle.getBundle("Languages",locale);
    }
    /** This method loads ResourceBundle Greek/Greece
     */
    public void setLanguageGreek(){
        locale = new Locale("el","GR");
        bundle = ResourceBundle.getBundle("Languages",locale);
    }
    /** This method loads ResourceBundle English/United States
     */
    public  void  setLanguageEnglish(){
        locale = new Locale("en","US");
        bundle = ResourceBundle.getBundle("Languages",locale);
    }
    /** This method returns the Resource Bundle with a specific locale.
     * @return Resource Bundle bundle
     */
    public ResourceBundle getBundle() {
        return bundle;
    }
}

