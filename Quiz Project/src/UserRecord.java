import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * To support an individual user record
 * @author Jim Mair
 * @version v1 03/05/2021
 */

public class UserRecord {
    private String Name;
    private String Type;
    private String Lang;
    private ArrayList<UserRecord> userList;

    /**
     * Constructor to build a User
     */
    public UserRecord() {
        this.Name = Name;
        this.Type = Type;
        this.Lang = Lang;
        userList = new ArrayList<UserRecord>();
    }

    /**
     * Reads in User information from the file
     *
     * @param infileName The file to read from
     * @throws FileNotFoundException if file doesn't exist
     * @throws IOException if some other IO error occurs
     * @throws IllegalArgumentException if infileName is null or empty
     */
    public void load(String infileName) throws IOException, FileNotFoundException {

        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            while (infile.hasNext()) {
                UserRecord userrecord = new UserRecord();
                //userrecord.load(infile);
                userList.add(userrecord);
            }
        }
    }

    /**
     * Get the owner's name
     * @return The owner's name
     */
    public String getName() {
        return Name;
    }



}