import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Modules {

    public int ModID;
    public String ModRef;
    public String ModNameEN;
    public String ModNameCW;

    /**
     * Constructor for the Question Bank
     */
    public void Modules() {
        ModID = 0;
        ModRef = "";
        ModNameEN = "";
        ModNameCW = "";
    }


    /**
     * Reads in Modules information from the file
     *
     * @throws FileNotFoundException if file doesn't exist
     * @throws IOException if some other IO error occurs
     * @throws IllegalArgumentException if infileName is null or empty
     */
    public void listModules(String userlang) throws IOException, FileNotFoundException {

        String infileName = "Modules.txt";
        int ModID;
        String ModRef;
        String ModNameEN;
        String ModNameCW;

        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            if (userlang.equals("English")) {
                System.out.println("\nThe current list of Modules is:");
            }
            else {
                System.out.println("\nMae'r rhestr gyfredol o Fodiwlau yn:");
            }
            int ModCount = infile.nextInt();
            while (infile.hasNext()) {
                ModID = infile.nextInt();
                ModRef = infile.next();
                ModNameEN = infile.next();
                ModNameCW = infile.next();
                if (ModID>0) {
                    System.out.println(ModID + ": " + ModRef + " " + ModNameEN + " / " + ModNameCW);
                }
            }
        }
    }
    public void loadModules(int selection) throws IOException, FileNotFoundException {

        String infileName = "Modules.txt";
        int ID;
        String Ref;
        String NameEN;
        String NameCW;


        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            int ModCount = infile.nextInt();
            while (infile.hasNext()) {
                ID = infile.nextInt();
                Ref = infile.next();
                NameEN = infile.next();
                NameCW = infile.next();

                if (ModID==selection) {
                    ModID = ID;
                    ModRef = Ref;
                    ModNameEN = NameEN;
                    ModNameCW = NameCW;
                }
            }
        }
    }
    public void add() {
        Scanner scan;
        scan = new Scanner (System.in);
        String infileName = "Modules.txt";

        // Create arrays to hold the existing module information that we are reading from the modules file
        List<Integer> ModID = new ArrayList<Integer>();
        List<String> Ref    = new ArrayList<String>();
        List<String> NameEN = new ArrayList<String>();
        List<String> NameCW = new ArrayList<String>();

        // Asks the user for the new module information
        System.out.println("Enter the Module Code/Rhowch y Cod Modiwl");
        String ModCode = scan.nextLine();
        System.out.println("Enter the Module Name in English/Rhowch Enw'r Modiwl yn Saesneg");
        String ModNameEN = scan.nextLine();
        System.out.println("Enter the Module Name in Welsh/Rhowch Enw'r Modiwl yn Gymraeg");
        String ModNameCW = scan.nextLine();

        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            // The first line of the file is the module count so read this in and increment it by one
            // Note that the module ID's are unique
            int ModCount = infile.nextInt();
            ModCount++;
            System.out.println("Creating Module "+ ModCount);

            // Loops though all the existing modules to read them into the arrays
            while (infile.hasNext()) { ;
                ModID.add(infile.nextInt());
                Ref.add(infile.next());
                NameEN.add(infile.next());
                NameCW.add(infile.next());
            }
            // Append the new module information to the arrays
            ModID.add(ModCount);
            Ref.add(ModCode);
            NameEN.add(ModNameEN);
            NameCW.add(ModNameCW);

            // Now we rewrite the entire module file with the additional module information
            try (FileWriter fw = new FileWriter(infileName);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter outfile = new PrintWriter(bw)) {

                // First line of the file is the new module count
                 outfile.println(ModCount);

                 // Loop through all the module records and rewrite them
                for (int i = 0; i < ModID.size(); i ++) {
                    outfile.println(ModID.get(i));
                    outfile.println(Ref.get(i));
                    outfile.println(NameEN.get(i));
                    outfile.println(NameCW.get(i));
                }


            }
            System.out.println("Module "+ ModCount + " Created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edit(int SelectID) {
        Scanner scan;
        scan = new Scanner (System.in);
        String infileName = "Modules.txt";

        // Create arrays to hold the existing module information that we are reading from the modules file
        List<Integer> ModID = new ArrayList<Integer>();
        List<String> Ref    = new ArrayList<String>();
        List<String> NameEN = new ArrayList<String>();
        List<String> NameCW = new ArrayList<String>();

        // Asks the user for the updated module information
        System.out.println("Enter the New Module Code/Rhowch y Cod Modiwl");
        String ModCode = scan.nextLine();
        System.out.println("Enter the New Module Name in English/Rhowch Enw'r Modiwl yn Saesneg");
        String ModNameEN = scan.nextLine();
        System.out.println("Enter the New Module Name in Welsh/Rhowch Enw'r Modiwl yn Gymraeg");
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
                ModID.add(infile.nextInt());
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
                for (int i = 0; i < ModID.size(); i ++) {
                    int thisID = ModID.get(i);
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
            System.out.println("Module "+ ModCount + " Created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void remove(int SelectID) {
        String infileName = "Modules.txt";

        // Create arrays to hold the existing module information that we are reading from the modules file
        List<Integer> ModID = new ArrayList<Integer>();
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
            // Note that the module ID's are unique
            int ModCount = infile.nextInt();

            // Loops though all the existing modules to read them into the arrays
            while (infile.hasNext()) {
                ModID.add(infile.nextInt());
                Ref.add(infile.next());
                NameEN.add(infile.next());
                NameCW.add(infile.next());
            }

            // Now we rewrite the entire module file and flip the ID of the module that we want to remove
            try (FileWriter fw = new FileWriter(infileName);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter outfile = new PrintWriter(bw)) {

                // First line of the file is the new module count
                outfile.println(ModCount);

                // Loop through all the module records and rewrite them
                for (int i = 0; i < ModID.size(); i ++) {
                    int thisID = ModID.get(i);
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
