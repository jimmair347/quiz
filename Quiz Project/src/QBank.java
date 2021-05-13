import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class QBank {

    public int QBID;
    public String QBRef;
    public String QBNameEN;
    public String QBNameCW;

    /**
     * Constructor for the Question Bank
     */
    public void QBank() {
        QBID = 0;
        QBRef = "";
        QBNameEN = "";
        QBNameCW = "";
    }


    /**
     * Reads in Question Bank information from the file
     *
     * @throws FileNotFoundException if file doesn't exist
     * @throws IOException if some other IO error occurs
     * @throws IllegalArgumentException if infileName is null or empty
     */
    public void listQBank(String userlang,int selection) throws IOException, FileNotFoundException {

        String infileName = "QBank_"+selection+".txt";
        int QBID;
        String QBRef;
        String QBNameEN;
        String QBNameCW;

        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            if (userlang.equals("English")) {
                System.out.println("\nThe current list of Question Banks is:");
            }
            else {
                System.out.println("\nMae'r rhestr gyfredol o Fanciau Cwestiynau yn:");
            }
            int ModCount = infile.nextInt();
            while (infile.hasNext()) {
                QBID = infile.nextInt();
                QBRef = infile.next();
                QBNameEN = infile.next();
                QBNameCW = infile.next();

                if (QBID>0) {
                    System.out.println(QBID + ": " + QBRef + " " + QBNameEN);
                }
            }
        }
        catch (IOException e) {
            //e.printStackTrace();
            if (userlang.equals("English")) {
                System.out.println("This is a new Question Bank with no Questions");
                System.out.println("Go back and Choose add Question Bank");
            } else {
                System.out.println("Banc Cwestiynau newydd yw hwn heb unrhyw Gwestiynau");
                System.out.println("Ewch yn ôl a Dewiswch ychwanegu Banc Cwestiynau");
            }
            try (FileWriter fw = new FileWriter(infileName);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter outfile = new PrintWriter(bw)) {

                // First line of the file is the new module count
                outfile.println(0);
            }
           // add(selection);
        }
    }

    public void edit(int SelectID,int ModID) {
        Scanner scan;
        scan = new Scanner (System.in);
        String infileName = "QBank_"+ModID+".txt";

        // Create arrays to hold the existing Question Bank information that we are reading from the modules file
        List<Integer> QBID = new ArrayList<Integer>();
        List<String> Ref    = new ArrayList<String>();
        List<String> NameEN = new ArrayList<String>();
        List<String> NameCW = new ArrayList<String>();

        // Asks the user for the updated module information
        System.out.println("Enter the New Code/Rhowch y Cod");
        String ModCode = scan.nextLine();
        System.out.println("Enter the New Name in English/Rhowch Enw'r yn Saesneg");
        String ModNameEN = scan.nextLine();
        System.out.println("Enter the New Name in Welsh/Rhowch Enw'r yn Gymraeg");
        String ModNameCW = scan.nextLine();

        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            // The first line of the file is the module count so read this in
            // Note that the module ID's are unique
            int ModCount = infile.nextInt();

            // Loops though all the existing modules to read them into the arrays
            while (infile.hasNext()) { ;
                QBID.add(infile.nextInt());
                Ref.add(infile.next());
                NameEN.add(infile.next());
                NameCW.add(infile.next());
            }

            // Now we rewrite the entire module file with the edited module information
            try (FileWriter fw = new FileWriter(infileName);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter outfile = new PrintWriter(bw)) {

                // First line of the file is the new module count
                outfile.println(ModCount);

                // Loop through all the module records and rewrite them
                for (int i = 0; i < QBID.size(); i ++) {
                    int thisID = QBID.get(i);
                    String thisRef = Ref.get(i);
                    String thisNameEN = NameEN.get(i);
                    String thisNameCW = NameCW.get(i);
                    if ( thisID == SelectID){
                        thisRef = ModCode;
                        thisNameEN = ModNameEN;
                        thisNameCW = ModNameCW;
                    }

                    outfile.println(thisID);
                    outfile.println(thisRef);
                    outfile.println(thisNameEN);
                    outfile.println(thisNameCW);
                }


            }
            System.out.println("Module "+ ModCount + " Changed Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void add(int ModID) {
        Scanner scan;
        scan = new Scanner (System.in);
        String infileName = "QBank_"+ModID+".txt";

        // Create arrays to hold the existing information that we are reading from the Question Bank file
        List<Integer> QBID = new ArrayList<Integer>();
        List<String> Ref    = new ArrayList<String>();
        List<String> NameEN = new ArrayList<String>();
        List<String> NameCW = new ArrayList<String>();

        // Asks the user for the new Question Bank information
        System.out.println("Enter the Code/Rhowch y Cod");
        String ModCode = scan.nextLine();
        System.out.println("Enter the Name in English/Rhowch Enw'r yn Saesneg");
        String ModNameEN = scan.nextLine();
        System.out.println("Enter the Name in Welsh/Rhowch Enw'r yn Gymraeg");
        String ModNameCW = scan.nextLine();

        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            // The first line of the file is the Question Bank count so read this in and increment it by one
            // Note that the Question Bank ID's are unique
            int ModCount = infile.nextInt();
            ModCount++;
            System.out.println("Creating a new Question Bank "+ ModCount);

            // Loops though all the existing Question Bank to read them into the arrays
            while (infile.hasNext()) { ;
                QBID.add(infile.nextInt());
                Ref.add(infile.next());
                NameEN.add(infile.next());
                NameCW.add(infile.next());
            }
            // Append the new Question Bank information to the arrays
            QBID.add(ModCount);
            Ref.add(ModCode);
            NameEN.add(ModNameEN);
            NameCW.add(ModNameCW);

            // Now we rewrite the entire Question Bank file with the additional module information
            try (FileWriter fw = new FileWriter(infileName);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter outfile = new PrintWriter(bw)) {

                // First line of the file is the new module count
                outfile.println(ModCount);

                // Loop through all the Question Bank records and rewrite them
                for (int i = 0; i < QBID.size(); i ++) {
                    outfile.println(QBID.get(i));
                    outfile.println(Ref.get(i));
                    outfile.println(NameEN.get(i));
                    outfile.println(NameCW.get(i));
                }


            }
            System.out.println("Question Bank "+ ModCount + " Created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void remove(int SelectID,int ModID) {
        String infileName = "QBank_"+ModID+".txt";

        // Create arrays to hold the existing information that we are reading from the modules file
        List<Integer> QBID = new ArrayList<Integer>();
        List<String> Ref    = new ArrayList<String>();
        List<String> NameEN = new ArrayList<String>();
        List<String> NameCW = new ArrayList<String>();


        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            // The first line of the file is the module count so read this in and increment it by one
            // Note that the Question Bank ID's are unique
            int ModCount = infile.nextInt();

            // Loops though all the existing Question Banks to read them into the arrays
            while (infile.hasNext()) {
                QBID.add(infile.nextInt());
                Ref.add(infile.next());
                NameEN.add(infile.next());
                NameCW.add(infile.next());
            }

            // Now we rewrite the entire Question Bank file and flip the ID of the module that we want to remove
            try (FileWriter fw = new FileWriter(infileName);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter outfile = new PrintWriter(bw)) {

                // First line of the file is the new Question Bank count
                outfile.println(ModCount);

                // Loop through all the Question Bank records and rewrite them
                for (int i = 0; i < QBID.size(); i ++) {
                    int thisID = QBID.get(i);
                    if ( thisID == SelectID){
                        thisID = - thisID;
                    }

                    outfile.println(thisID);
                    outfile.println(Ref.get(i));
                    outfile.println(NameEN.get(i));
                    outfile.println(NameCW.get(i));
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

