import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class User {

    public String userName;
    public String userType;
    public String userLang;

     /**
     * Constructor for the User
     */
    public void User() {
        userName = "Jim";
        userType = "Admin";
        userLang = "Lang";
    }

    /**
     * Reads in User information from the file
     *
     * @throws FileNotFoundException if file doesn't exist
     * @throws IOException if some other IO error occurs
     * @throws IllegalArgumentException if infileName is null or empty
     */
    public void searchUsers(String who) throws IOException, FileNotFoundException {

        String infileName = "Users.txt";
        String name;
        String type;
        String lang;

        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            while (infile.hasNext()) {
                name = infile.next().toUpperCase();
                type = infile.next();
                lang = infile.next();

                if (name.equals(who)) {
                    userName = name;
                    userType = type;
                    userLang = lang;
                }
            }
        }
    }

}


