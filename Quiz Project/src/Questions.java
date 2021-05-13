import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;


public class Questions {

    public int QuestID;
    public String QuestType;
    public String QuestNameEN;
    public String QuestNameCW;

    /**
     * Constructor for the Question Bank
     */
    public void Questions() {
        QuestID = 0;
        QuestType = "";
        QuestNameEN = "";
        QuestNameCW = "";
    }

    /**
     * Reads in Question information from the file
     *
     * @throws FileNotFoundException if file doesn't exist
     * @throws IOException if some other IO error occurs
     * @throws IllegalArgumentException if infileName is null or empty
     */
    public void listQuestions(String userlang, int moduleID, int qbankID) throws IOException, FileNotFoundException {

        String infileName = "Questions_"+moduleID+"_"+qbankID+".txt";
        int    QuestID;
        String QuestType;
        String QuestNameEN;
        String QuestNameCW;
        String AnswerEN;
        String AnswerCW;
        int    AnswerID;

        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            if (userlang.equals("English")) {
                System.out.println("\nThe current list of Questions is:");
            }
            else {
                System.out.println("\nMae'r rhestr gyfredol o Gwestiynau yn:");
            }
            int ModCount = infile.nextInt();
            while (infile.hasNext()) {
                QuestID = infile.nextInt();
                QuestType = infile.next();
                QuestNameEN = infile.next();
                QuestNameCW = infile.next();
                AnswerEN = infile.next();
                AnswerCW = infile.next();
                AnswerID = infile.nextInt();

                if (QuestType.equals("Multiple Choice")) {
                    String[] splitEN = AnswerEN.split("¬");
                    String[] splitCW = AnswerCW.split("¬");
                    String AnameEN = splitEN[AnswerID];
                    String AnameCW = splitCW[AnswerID];

                    String mAnswerEN = AnswerEN.replace('¬', '|');
                    String mAnswerCW = AnswerCW.replace('¬', '|');


                    if (userlang.equals("English")) {
                        System.out.println(QuestID + ": " + QuestType + " " + QuestNameEN + "\n   Possible Answers: " + mAnswerEN + "\n   (Correct answer " + AnameEN + ")");
                    } else {
                        System.out.println(QuestID + ": " + QuestType + " " + QuestNameCW + "\n   Atebion Posib: " + mAnswerCW + "\n   (Ateb cywir " + AnameCW + ")");
                    }
                }
                else {
                    if (userlang.equals("English")) {
                        System.out.println(QuestID + ": " + QuestType + " " + QuestNameEN + "\n   Answer: " + AnswerEN);
                    } else {
                        System.out.println(QuestID + ": " + QuestType + " " + QuestNameCW + "\n   Ateb: " + AnswerCW);
                    }
                }
            }
        }
    }


    public void loadQuestions(String userlang, int moduleID, int qbankID) throws IOException, FileNotFoundException {
        Scanner scan;
        scan = new Scanner (System.in);
        String infileName = "Questions_"+moduleID+"_"+qbankID+".txt";

        // Create arrays to hold the existing information that we are reading from the Question Bank file
        List<Integer> QuestID = new ArrayList<Integer>();
        List<String> Type    = new ArrayList<String>();
        List<String> QuestionEN = new ArrayList<String>();
        List<String> QuestionCW = new ArrayList<String>();
        List<String> AnswerEN = new ArrayList<String>();
        List<String> AnswerCW = new ArrayList<String>();
        List<Integer> AnswerID = new ArrayList<Integer>();
        List<Integer> PossID = new ArrayList<Integer>();
        List<Integer> RandID = new ArrayList<Integer>();

        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            int NumQs = 0;
            int ModCount = infile.nextInt();
            while (infile.hasNext()) {
                NumQs++;
                QuestID.add(infile.nextInt());
                Type.add(infile.next());
                QuestionEN.add(infile.next());
                QuestionCW.add(infile.next());
                AnswerEN.add(infile.next());
                AnswerCW.add(infile.next());
                AnswerID.add(infile.nextInt());
            }
            System.out.println("This Question Bank has " + NumQs + ". How many do you want to answer?");
            int AnsQ = scan.nextInt();
            if (AnsQ > NumQs) {
                AnsQ = NumQs;
            }
            // We are going to generate a random array of questions.
            for (int i = 0; i < QuestID.size(); i++) {
                PossID.add(i, QuestID.get(i));
            }
            while (RandID.size()<AnsQ) {
                Random rand = new Random();
                int i = rand.nextInt(AnsQ);
                i++;
                if (PossID.get(i)>0) {
                    RandID.add(PossID.get(i));
                    PossID.set(i,0);
                }
            }
            // loop through the random questions
            String thisType;
            String thisQuestEN;
            String thisQuestCW;
            String thisAnsEN;
            String thisAnsCW;
            int    thisAnsID;
            int    Ans;
            String Str;

            Str = scan.nextLine();

            for (int i = 0; i < AnsQ; i++) {
                int Q = RandID.get(i);
                thisType = Type.get(Q);
                thisQuestEN = QuestionEN.get(Q);
                thisQuestCW = QuestionCW.get(Q);
                thisAnsEN = AnswerEN.get(Q);
                thisAnsCW = AnswerCW.get(Q);
                thisAnsID = AnswerID.get(Q);

                if (thisType.equals("Multiple Choice")) {
                    String[] splitEN = thisAnsEN.split("¬");
                    String[] splitCW = thisAnsCW.split("¬");
                    String AnameEN = splitEN[thisAnsID];
                    String AnameCW = splitCW[thisAnsID];

                    String mAnswerEN = thisAnsEN.replace('¬', '|');
                    String mAnswerCW = thisAnsCW.replace('¬', '|');

                    if (userlang.equals("English")) {
                        System.out.println("Question " + Q + ": " + thisQuestEN);
                        for (int j = 0; j < splitEN.length; j++) {
                            int n = j+1;
                            System.out.println("   "+j+": "+ splitEN[j]);
                        }
                        System.out.println("Select your Answer");
                        Ans = scan.nextInt();

                        if (Ans == thisAnsID) {
                            System.out.println("You were Correct");
                        }
                        else {
                            System.out.println("Wrong Answer");
                        }
                    }
                    else {
                        System.out.println(QuestID + ": " + QuestType + " " + QuestNameCW + "\n   Atebion Posib: " + mAnswerCW + "\n   (Ateb cywir " + AnameCW + ")");
                    }
                }
                else {
                    if (userlang.equals("English")) {
                        System.out.println("Question " + Q + ": " + thisQuestEN);
                        System.out.println("Select your Answer");
                        Str = scan.nextLine().toUpperCase();

                        if (Str == thisAnsEN.toUpperCase()) {
                            System.out.println("You were Correct");
                        }
                        else {
                            System.out.println("Wrong Answer");
                        }

                    }
                    else {
                        System.out.println(QuestID + ": " + QuestType + " " + QuestNameCW + "\n   Ateb: " + AnswerCW);
                    }
                }
            }
        }
    }


    public void add(int ModID, int QID) {
        Scanner scan;
        scan = new Scanner (System.in);
        String infileName = "Questions_"+ModID+"_"+QID+".txt";
        String QType;

        // Create arrays to hold the existing information that we are reading from the Question Bank file
        List<Integer> QuestID = new ArrayList<Integer>();
        List<String> Type    = new ArrayList<String>();
        List<String> QuestionEN = new ArrayList<String>();
        List<String> QuestionCW = new ArrayList<String>();
        List<String> AnswerEN = new ArrayList<String>();
        List<String> AnswerCW = new ArrayList<String>();
        List<Integer> AnswerID = new ArrayList<Integer>();

        // Asks the user for the new Question Bank information
        System.out.println("Enter the Type/Rhowch y Cod \n M for Multiple Choice or K for Key word/ M ar gyfer Dewis Lluosog neu K ar gyfer gair Allweddol ");
        String ModCode = scan.nextLine().toUpperCase();
        if (!ModCode.equals("M") && (!ModCode.equals("K"))){
            System.out.println("Neither M or K Detected, K assumed/Ni chanfuwyd M na K, tybiodd K.");
        }
        if (ModCode.equals("M")) {
            QType = "Multiple Choice";
        }
        else {
            QType = "Key Word";
        }
        System.out.println("Enter the Question in English/Rhowch Enw'r yn Saesneg");
        String QuestEN = scan.nextLine();
        System.out.println("Enter the Question in Welsh/Rhowch Enw'r yn Gymraeg");
        String QuestCW = scan.nextLine();

        String AnsEN;
        String AnsCW;
        int AnsID;

        if (QType.equals("Multiple Choice")) {
            System.out.println("Enter the All Possible Answers Seperated with a ¬ Character in English/Rhowch yr Holl Atebion Posibl Wedi'u Gwahanu â ¬ Cymeriad yn Saesneg");
            AnsEN = scan.nextLine();
            System.out.println("Enter the All Possible Answers Seperated with a ¬ Character in Welsh/Rhowch yr Holl Atebion Posibl Wedi'u Gwahanu â Chymeriad ¬ yn Gymraeg");
            AnsCW = scan.nextLine();

            System.out.println("Which is the correct answer in the Sequence");
            AnsID = scan.nextInt();
            AnsID--;
        }
        else {
            System.out.println("Enter the Key Word Answer in English/Rhowch yr Ateb Gair Allweddol yn Saesneg");
            AnsEN = scan.nextLine();
            System.out.println("Enter the Key Word Answer in Welsh/Rhowch yr Ateb Gair Allweddol yn Gymraeg");
            AnsCW = scan.nextLine();
            AnsID = 0;
        }

        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            // The first line of the file is the Question count so read this in and increment it by one
            // Note that the Question Bank ID's are unique
            int ModCount = infile.nextInt();
            ModCount++;
            System.out.println("Creating a new Question Bank "+ ModCount);

            // Loops though all the existing Question to read them into the arrays
            while (infile.hasNext()) {
                QuestID.add(infile.nextInt());
                Type.add(infile.next());
                QuestionEN.add(infile.next());
                QuestionCW.add(infile.next());
                AnswerEN.add(infile.next());
                AnswerCW.add(infile.next());
                AnswerID.add(infile.nextInt());
            }
            // Append the new Question information to the arrays
            QuestID.add(ModCount);
            Type.add(QType);
            QuestionEN.add(QuestEN);
            QuestionCW.add(QuestCW);
            AnswerEN.add(AnsEN);
            AnswerCW.add(AnsCW);
            AnswerID.add(AnsID);

            // Now we rewrite the entire Question file with the additional module information
            try (FileWriter fw = new FileWriter(infileName);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter outfile = new PrintWriter(bw)) {

                // First line of the file is the new module count
                outfile.println(ModCount);

                // Loop through all the Question records and rewrite them
                for (int i = 0; i < QuestID.size(); i ++) {
                    outfile.println(QuestID.get(i));
                    outfile.println(Type.get(i));
                    outfile.println(QuestionEN.get(i));
                    outfile.println(QuestionCW.get(i));
                    outfile.println(AnswerEN.get(i));
                    outfile.println(AnswerCW.get(i));
                    outfile.println(AnswerID.get(i));
                }

            }
            System.out.println("Question "+ ModCount + " Created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edit(int SelectID,int ModID, int QID) {
        Scanner scan;
        scan = new Scanner (System.in);
        String infileName = "Questions_"+ModID+"_"+QID+".txt";
        String QType;

        // Create arrays to hold the existing information that we are reading from the Question Bank file
        List<Integer> QuestID = new ArrayList<Integer>();
        List<String> Type    = new ArrayList<String>();
        List<String> QuestionEN = new ArrayList<String>();
        List<String> QuestionCW = new ArrayList<String>();
        List<String> AnswerEN = new ArrayList<String>();
        List<String> AnswerCW = new ArrayList<String>();
        List<Integer> AnswerID = new ArrayList<Integer>();

        // Asks the user for the new Question Bank information
        System.out.println("Enter the Type/Rhowch y Cod \n M for Multiple Choice or K for Key word/ M ar gyfer Dewis Lluosog neu K ar gyfer gair Allweddol ");
        String ModCode = scan.nextLine().toUpperCase();
        if (!ModCode.equals("M") && (!ModCode.equals("K"))){
            System.out.println("Neither M or K Detected, K assumed/Ni chanfuwyd M na K, tybiodd K.");
        }
        if (ModCode.equals("M")) {
            QType = "Multiple Choice";
        }
        else {
            QType = "Key Word";
        }
        System.out.println("Enter the Question in English/Rhowch Enw'r yn Saesneg");
        String QuestEN = scan.nextLine();
        System.out.println("Enter the Question in Welsh/Rhowch Enw'r yn Gymraeg");
        String QuestCW = scan.nextLine();

        String AnsEN;
        String AnsCW;
        int AnsID;

        if (QType.equals("Multiple Choice")) {
            System.out.println("Enter the All Possible Answers Seperated with a ¬ Character in English/Rhowch yr Holl Atebion Posibl Wedi'u Gwahanu â ¬ Cymeriad yn Saesneg");
            AnsEN = scan.nextLine();
            System.out.println("Enter the All Possible Answers Seperated with a ¬ Character in Welsh/Rhowch yr Holl Atebion Posibl Wedi'u Gwahanu â Chymeriad ¬ yn Gymraeg");
            AnsCW = scan.nextLine();

            System.out.println("Which is the correct answer in the Sequence");
            AnsID = scan.nextInt();
            AnsID--;
        }
        else {
            System.out.println("Enter the Key Word Answer in English/Rhowch yr Ateb Gair Allweddol yn Saesneg");
            AnsEN = scan.nextLine();
            System.out.println("Enter the Key Word Answer in Welsh/Rhowch yr Ateb Gair Allweddol yn Gymraeg");
            AnsCW = scan.nextLine();
            AnsID = 0;
        }

        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            // Note that the Question Bank ID's are unique
            int ModCount = infile.nextInt();

            // Loops though all the existing Question to read them into the arrays
            while (infile.hasNext()) { ;
                QuestID.add(infile.nextInt());
                Type.add(infile.next());
                QuestionEN.add(infile.next());
                QuestionCW.add(infile.next());
                AnswerEN.add(infile.next());
                AnswerCW.add(infile.next());
                AnswerID.add(infile.nextInt());
            }

            // Now we rewrite the entire Question file with the additional module information
            try (FileWriter fw = new FileWriter(infileName);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter outfile = new PrintWriter(bw)) {

                // First line of the file is the new module count
                outfile.println(ModCount);

                // Loop through all the Question records and rewrite them
                for (int i = 0; i < QuestID.size(); i ++) {
                    int thisID = QuestID.get(i);
                    String thisType = Type.get(i);
                    String thisQuestEN = QuestionEN.get(i);
                    String thisQuestCW = QuestionCW.get(i);
                    String thisAnsEN = AnswerEN.get(i);
                    String thisAnsCW = AnswerCW.get(i);
                    int thisAnsID = AnswerID.get(i);
                    if ( thisID == SelectID){
                        thisType = QType;
                        thisQuestEN = QuestEN;
                        thisQuestCW = QuestCW;
                        thisAnsEN = AnsEN;
                        thisAnsCW = AnsCW;
                        thisAnsID = AnsID;
                    }
                    outfile.println(thisID);
                    outfile.println(thisType);
                    outfile.println(thisQuestEN);
                    outfile.println(thisQuestCW);
                    outfile.println(thisAnsEN);
                    outfile.println(thisAnsCW);
                    outfile.println(thisAnsID);
                }

            }
            System.out.println("Question "+ ModCount + " Edited Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void remove(int SelectID,int ModID, int QID) {
        Scanner scan;
        scan = new Scanner (System.in);
        String infileName = "Questions_"+ModID+"_"+QID+".txt";
        String QType;

        // Create arrays to hold the existing information that we are reading from the Question Bank file
        List<Integer> QuestID = new ArrayList<Integer>();
        List<String> Type    = new ArrayList<String>();
        List<String> QuestionEN = new ArrayList<String>();
        List<String> QuestionCW = new ArrayList<String>();
        List<String> AnswerEN = new ArrayList<String>();
        List<String> AnswerCW = new ArrayList<String>();
        List<Integer> AnswerID = new ArrayList<Integer>();

        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            // Note that the Question Bank ID's are unique
            int ModCount = infile.nextInt();

            // Loops though all the existing Question to read them into the arrays
            while (infile.hasNext()) { ;
                QuestID.add(infile.nextInt());
                Type.add(infile.next());
                QuestionEN.add(infile.next());
                QuestionCW.add(infile.next());
                AnswerEN.add(infile.next());
                AnswerCW.add(infile.next());
                AnswerID.add(infile.nextInt());
            }

            // Now we rewrite the entire Question file with the additional module information
            try (FileWriter fw = new FileWriter(infileName);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter outfile = new PrintWriter(bw)) {

                // First line of the file is the new module count
                outfile.println(ModCount);

                // Loop through all the Question records and rewrite them
                for (int i = 0; i < QuestID.size(); i ++) {
                    int thisID = QuestID.get(i);
                    if (thisID == SelectID) {
                        thisID = -thisID;
                    }

                    outfile.println(thisID);
                    outfile.println(Type.get(i));
                    outfile.println(QuestionEN.get(i));
                    outfile.println(QuestionCW.get(i));
                    outfile.println(AnswerEN.get(i));
                    outfile.println(AnswerCW.get(i));
                    outfile.println(AnswerID.get(i));
                }
            }
            System.out.println("Question "+ ModCount + " Removed Successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


