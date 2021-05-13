import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class runs the Quiz Project
 *
 * There are 3 key functions:
 *
 * 1. The user login - so we can determine their access rights and create a quiz history
 * 2. Standard users (students) then get to select the QuizBank they want to use - then take a test
 * 2. Admins (teachers) can manage the QuizBanks
 *
 * @author Jim Mair
 * @version v1 03/05/2021
 */
public class QuizMain {
    private String username;     // holds the name of the current user
    private String usertype;     // holds the name of the current user
    private String userlang;     // holds the name of the current user
    private String quizbankname; // holds the name of the current QuizBank
    private Scanner scan;       // so we can read from keyboard

    /* Get the login ID of the user
     *
     * this is not security its just for identification but security could be added at a later stage
     *
     */
    private QuizMain() {
        scan = new Scanner(System.in);
        System.out.print("Please enter your Username or Enter R to register:\n");
        System.out.print("Rhowch eich Enw Defnyddiwr neu Rhowch R i gofrestru:\n");
        username = scan.nextLine().toUpperCase();

        if (username.equals("R")) {
            System.out.print("Function not available in this version\n\n");
            new QuizMain();
        }
        else {
            // Search for the user by name
            User user = new User();
            try {
                user.searchUsers(username);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (user.userName != null) {
                if (user.userLang.equals("English")) {
                    System.out.println("You are logged in as " + user.userName + " (You are " + user.userType + ")\n");
                }
                else {
                    System.out.println("Rydych wedi mewngofnodi fel " + user.userName + " (Yr ydych " + user.userType + ")\n");
                }
                this.username = user.userName;
                this.usertype = user.userType;
                this.userlang = user.userLang;
            }
            else {
                System.out.println("User Not found/Defnyddiwr Heb ei ddarganfod\n\n");
                new QuizMain();
            }
        }
    }

    /* Runs the main Admin (teacher) menu ----------------------------------------------------------------------   */
    private void runAMenu() {
        String response;
        do {
            printAMenu();
            if  (this.userlang.equals("English")) {
                System.out.println("What would you like to do:");
            }
            else {
                System.out.println("Beth hoffech chi ei wneud:");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "1":
                    openModules();
                    break;
                case "2":
                    newModules();
                    break;
                case "3":
                    editModules();
                    break;
                case "4":
                    deleteModules();
                    break;
                case "5":
                    seeResults();
                    break;
                case "9":
                    changeLang();
                    break;
                case "Q":
                    break;
                default:
                    if (this.userlang.equals("English")) {
                        System.out.println("Try again");
                    }
                    else {
                        System.out.println("Ceisio eto");
                    }
            }
        } while (!(response.equals("Q")));
    }

    private void printAMenu() {
        if (this.userlang.equals("English")) {
            System.out.println("1 - List all Modules");
            System.out.println("2 - Add new Module ");
            System.out.println("3 - Edit Module ");
            System.out.println("4 - Remove a Module");
            System.out.println("5 - Analyse Results");
            System.out.println("9 - Change Language");
            System.out.println("q - Quit");
        }
        else {
            System.out.println("1 - Rhestrwch yr holl Fodiwlau");
            System.out.println("2 - Ychwanegu Modiwl newydd");
            System.out.println("3 - Golygu Modiwl");
            System.out.println("4 - Tynnu Modiwl");
            System.out.println("5 - Dadansoddwch y Canlyniadau");
            System.out.println("9 - Newid Iaith");
            System.out.println("q - Rhoi'r gorau iddi");
        }
    }
    /* End of main Admin menu ----------------------------------------------------------------------------------- */

    /* Runs the main Student menu ----------------------------------------------------------------------   */
    private void runSMenu() {
        String response;
        do {
            printSMenu();
            if (this.userlang.equals("English")) {
                System.out.println("What would you like to do:");
            }
            else {
                System.out.println("Beth hoffech chi ei wneud:");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "1":
                    openModules();
                    break;
                case "2":
                    //changeKennelName();
                    break;
                case "3":
                    //printAll();
                    break;
                case "4":
                    //searchForDog();
                    break;

                case "9":
                    //setKennelCapacity();
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Try again");
            }
        } while (!(response.equals("Q")));
    }

    private void printSMenu() {
        if (this.userlang.equals("English")) {
            System.out.println("1 - Select a Module");
            System.out.println("2 - Analyse Results");
            System.out.println("3 - Change Language");
            System.out.println("q - Quit");
        }
        else {
            System.out.println("1 - Dewiswch Fodiwl");
            System.out.println("2 - Dadansoddwch y Canlyniadau");
            System.out.println("3 - Newid Iaith");
            System.out.println("q - Rhoi'r gorau iddi");
        }
    }
    /* End of main Student menu ----------------------------------------------------------------------------------- */

    /* ***************************************************************************************************************
     * MISC QUIZMAIN FUNCTIONS
     * ************************************************************************************************************ */

    private void seeResults() {
        System.out.println("Function not available yet");
    }

    private void changeLang() {
        String response;
        do {
            if (this.userlang.equals("English")) {
                System.out.println("Enter E for English or W for Welsh or Press Q to Return");
            }
            else {
                System.out.println("Rhowch E ar gyfer Saesneg neu W ar gyfer Cymraeg neu Pwyswch Q i Dychwelyd");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "E":
                    this.userlang = "English";
                    response = "Q";
                    break;
                case "W":
                    this.userlang = "Cymraeg";
                    response = "Q";
                    break;
                case "Q":
                    break;

            }
        } while (!(response.equals("Q")));
    }

    /* **************************************************************************************************************
     * START OF MODULE FUNCTIONS
     * ************************************************************************************************************ */


    /* Opens a selected Module and gets the QBanks associated with it
    */
    private void openModules() {
        Modules modules = new Modules();

        // List all the available modules
        try {
            modules.listModules(this.userlang);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ask the Teacher to select a module from the list
        int mselection = runMMenu();

        // Load the selected module
        if (mselection!= 0){
            try {
                modules.loadModules(mselection);
                if (this.userlang.equals("English")) {
                    System.out.println("\nModule " + mselection + " loaded\n");
                }
                else{
                    System.out.println("\nModiwl " + mselection + " wedi'i lwytho\n");
                }
                if (this.usertype.equals("Admin")){
                    runAQMenu(mselection);
                }
                else {
                    openQBank(mselection);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* Deletes a selected module
    */
    private void deleteModules() {
        Modules modules = new Modules();

        // List all the available modules
        try {
            modules.listModules(this.userlang);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ask the Teacher to select a module from the list
        int mselection = runRMenu();

        // Remove the selected module
        if (mselection!= 0){
            Modules newMod = new Modules();
            newMod.remove(mselection);
            System.out.println("Module successfully removed");
        }
    }

    /* Edit a selected module
    */
    private void editModules() {
        Modules modules = new Modules();

        // List all the available modules
        try {
            modules.listModules(this.userlang);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ask the Teacher to select a module from the list
        int mselection = runEMenu();

        // Remove the selected module
        if (mselection!= 0){
            Modules newMod = new Modules();
            newMod.edit(mselection);
            System.out.println("Module successfully changed");
        }
    }

    /* Creates a new module -------------------------------------------------------------------------------------- */
    private void newModules() {
        Modules newMod = new Modules();
        newMod.add();
        System.out.println("Module successfully added");
    }

    /* Displays the Open module menu for the Teacher -----------------------------------------------------------  */
    private int runMMenu() {
        String response;
        do {
            if (this.userlang.equals("English")) {
                System.out.println("Select a module number or press Q to go back to the previous menu:");
            }
            else {
                System.out.println("Dewiswch rif modiwl neu pwyswch Q i fynd yn ôl i'r ddewislen flaenorol");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "Q":
                    return 0;
                default:
                    int selection = Integer.parseInt(response);
                    return selection;
            }
        } while (!(response.equals("Q")));
    }

    /* Displays the Remove Module menus --------------------------------------------------------------------------- */
    private int runRMenu() {
        String response;
        do {
            if (this.userlang.equals("English")) {
                System.out.println("Select the module number you want to remove or press Q to go back to the previous menu:");
            }
            else {
                System.out.println("Dewiswch rif y modiwl rydych chi am ei dynnu neu pwyswch Q i fynd yn ôl i'r ddewislen flaenorol:");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "Q":
                    return 0;
                default:
                    int selection = Integer.parseInt(response);
                    return selection;
            }
        } while (!(response.equals("Q")));
    }

    /* Displays the Edit module menu ------------------------------------------------------------------------------ */
    private int runEMenu() {
        String response;
        do {
            if (this.userlang.equals("English")) {
                System.out.println("Select the module number you want to edit or press Q to go back to the previous menu:");
            }
            else {
                System.out.println("Dewiswch rif y modiwl rydych chi am ei olygu neu pwyswch Q i fynd yn ôl i'r ddewislen flaenorol:");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "Q":
                    return 0;
                default:
                    int selection = Integer.parseInt(response);
                    return selection;
            }
        } while (!(response.equals("Q")));
    }

    /* ****************************************************************************************************************
     * START OF QBANK FUNCTIONS
     * ************************************************************************************************************* */
    private void runAQMenu(int selection) {
        String response;
        do {
            printAQMenu();
            if  (this.userlang.equals("English")) {
                System.out.println("What would you like to do:");
            }
            else {
                System.out.println("Beth hoffech chi ei wneud:");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "1":
                    openQBank(selection);
                    break;
                case "2":
                    newQBank(selection);
                    break;
                case "3":
                    editQBank(selection);
                    break;
                case "4":
                    deleteQBank(selection);
                    break;
                case "Q":
                    break;
                default:
                    if (this.userlang.equals("English")) {
                        System.out.println("Try again");
                    }
                    else {
                        System.out.println("Ceisio eto");
                    }
            }
        } while (!(response.equals("Q")));
    }

    private void printAQMenu() {
        if (this.userlang.equals("English")) {
            System.out.println("1 - List all Question Banks in this Module");
            System.out.println("2 - Add new Question Bank ");
            System.out.println("3 - Edit Question Bank ");
            System.out.println("4 - Remove a Question Bank");
            System.out.println("q - Quit");
        }
        else {
            System.out.println("1 -");
            System.out.println("2 - ");
            System.out.println("3 - G");
            System.out.println("4 - l");
            System.out.println("q - Rhoi'r gorau iddi");
        }
    }



    private int runQBMenu() {
        String response;
        do {
            if (this.userlang.equals("English")) {
                System.out.println("Select a Question Bank number to see its Questions or press Q to go back to the previous menu:");
            }
            else {
                System.out.println("Dewiswch rif Banc Cwestiynau neu pwyswch Q i fynd yn ôl i'r ddewislen flaenorol");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "Q":
                    return 0;
                default:
                    int selection = Integer.parseInt(response);
                    return selection;
            }
        } while (!(response.equals("Q")));
    }

    private int runEQBMenu() {
        String response;
        do {
            if (this.userlang.equals("English")) {
                System.out.println("Select the Question Bank number you want to edit or press Q to go back to the previous menu:");
            }
            else {
                System.out.println("Dewiswch y rhif Banc Cwestiynau rydych chi am ei olygu neu pwyswch Q i fynd yn ôl i'r ddewislen flaenorol:");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "Q":
                    return 0;
                default:
                    int selection = Integer.parseInt(response);
                    return selection;
            }
        } while (!(response.equals("Q")));
    }

    private int runMQBMenu() {
        String response;
        do {
            if (this.userlang.equals("English")) {
                System.out.println("Select a Question Bank number you want to open or press Q to go back to the previous menu:");
            }
            else {
                System.out.println("Dewiswch rif Banc Cwestiynau neu pwyswch Q i fynd yn ôl i'r ddewislen flaenorol");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "Q":
                    return 0;
                default:
                    int selection = Integer.parseInt(response);
                    return selection;
            }
        } while (!(response.equals("Q")));
    }

    private int runRQBMenu() {
        String response;
        do {
            if (this.userlang.equals("English")) {
                System.out.println("Select the Question Bank number you want to remove or press Q to go back to the previous menu:");
            }
            else {
                System.out.println("Dewiswch rif y Banc Cwestiynau rydych chi am ei dynnu neu pwyswch Q i fynd yn ôl i'r ddewislen flaenorol:");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "Q":
                    return 0;
                default:
                    int selection = Integer.parseInt(response);
                    return selection;
            }
        } while (!(response.equals("Q")));
    }

    private void openQBank(int ModID) {
        QBank qBank = new QBank();

        // List all the available Question Bank
        try {
            qBank.listQBank(this.userlang,ModID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ask the Teacher to select a Question Bank from the list
        int qbselection = runMQBMenu();

        // Load the selected module
        if (qbselection!= 0){
            try {
                if (this.userlang.equals("English")) {
                    System.out.println("\nQuestion Bank " + qbselection + " loaded\n");
                }
                else{
                    System.out.println("\nBanc Cwestiynau " + qbselection + " wedi'i lwytho\n");
                }
                Questions questions = new Questions();

                if (this.usertype.equals("Admin")){
                    questions.listQuestions(this.userlang,ModID,qbselection);
                    runQMenu(ModID,qbselection);
                }
                else {
                    questions.loadQuestions(this.userlang,ModID,qbselection);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void newQBank(int ModID) {
        QBank newQB = new QBank();
        newQB.add(ModID);
        System.out.println("Module successfully added");

    }

    private void editQBank(int ModID) {
        QBank qBank = new QBank();

        // List all the available Question Banks
        try {
            qBank.listQBank(this.userlang,ModID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ask the Teacher to select a Question Bank from the list
        int QBselection = runEQBMenu();

        if (QBselection!= 0){
            QBank newQBank = new QBank();
            newQBank.edit(QBselection,ModID);
            System.out.println("Question Bank successfully changed");
        }
    }
    private void deleteQBank(int ModID) {

        QBank qBank = new QBank();

        // List all the available Question Banks
        try {
            qBank.listQBank(this.userlang,ModID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ask the Teacher to select a module from the list
        int mselection = runRQBMenu();

        // Remove the selected module
        if (mselection!= 0){
            QBank newQB = new QBank();
            newQB.remove(mselection,ModID);
            System.out.println("Question Bank successfully removed");
        }
    }

    /* ******************************************************************************************************
     * START OF QUESTION FUNCTIONS
     * *************************************************************************************************** */
    private void newQuestion(int ModID,int QID) {
        Questions newQ = new Questions();
        newQ.add(ModID,QID);
        System.out.println("Question successfully added");
    }

    private void editQuestion(int ModID,int QID) {
        Questions questions = new Questions();

        // List all the available Questions
        try {
            questions.listQuestions(this.userlang,ModID,QID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ask the Teacher to select a Question from the list
        int Qselection = runEQMenu();

        if (Qselection!= 0){
            Questions newQ = new Questions();
            newQ.edit(Qselection,ModID,QID);
            System.out.println("Question Bank successfully changed");
        }
    }

    private void deleteQuestion(int ModID,int QID) {

        Questions questions = new Questions();

        // List all the available Questions
        try {
            questions.listQuestions(this.userlang,ModID,QID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ask the Teacher to select a module from the list
        int mselection = runRQMenu();

        // Remove the selected module
        if (mselection!= 0){
            Questions newQ = new Questions();
            newQ.remove(mselection,ModID,QID);
            System.out.println("Question successfully removed");
        }
    }

    /* Displays the main Questions menu -------------------------------------------------------------------------- */
    private void runQMenu(int ModID,int QID) {
        String response;
        do {
            printQMenu();
            if  (this.userlang.equals("English")) {
                System.out.println("What would you like to do:");
            }
            else {
                System.out.println("Beth hoffech chi ei wneud:");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "1":
                    newQuestion(ModID,QID);
                    break;
                case "2":
                    editQuestion(ModID,QID);
                    break;
                case "3":
                    deleteQuestion(ModID,QID);
                    break;
                case "Q":
                    break;
                default:
                    if (this.userlang.equals("English")) {
                        System.out.println("Try again");
                    }
                    else {
                        System.out.println("Ceisio eto");
                    }
            }
        } while (!(response.equals("Q")));
    }

    private int runRQMenu() {
        String response;
        do {
            if (this.userlang.equals("English")) {
                System.out.println("Select the Question number you want to remove or press Q to go back to the previous menu:");
            }
            else {
                System.out.println("Dewiswch rif y Cwestiynau rydych chi am ei dynnu neu pwyswch Q i fynd yn ôl i'r ddewislen flaenorol:");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "Q":
                    return 0;
                default:
                    int selection = Integer.parseInt(response);
                    return selection;
            }
        } while (!(response.equals("Q")));
    }

    private int runEQMenu() {
        String response;
        do {
            if (this.userlang.equals("English")) {
                System.out.println("Select the Question number you want to edit or press Q to go back to the previous menu:");
            }
            else {
                System.out.println("Dewiswch y rhif Cwestiynau rydych chi am ei olygu neu pwyswch Q i fynd yn ôl i'r ddewislen flaenorol:");
            }
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "Q":
                    return 0;
                default:
                    int selection = Integer.parseInt(response);
                    return selection;
            }
        } while (!(response.equals("Q")));
    }

    private void printQMenu() {
        if (this.userlang.equals("English")) {
            System.out.println("\n1 - Add new Question ");
            System.out.println("2 - Edit Question");
            System.out.println("3 - Delete Question");
            System.out.println("q - Quit");
        }
        else {
            System.out.println("\n1 - Ychwanegwch Gwestiwn newydd");
            System.out.println("2 - Golygu Cwestiwn");
            System.out.println("3 - Dileu Cwestiwn");
            System.out.println("q - Rhoi'r gorau iddi");
        }
    }
    /* end of Question Menu --------------------------------------------------------------------------------------- */


    /* ================================
     * This is the main processing loop
     * ================================
     */
    public static void main(String args[]) {
        System.out.println("Welcome/Croeso");
        QuizMain quiz = new QuizMain();

        if (quiz.usertype.equals("Admin") ) quiz.runAMenu();
        else quiz.runSMenu();

        System.out.println("Goodbye Thank You/Hwyl Fawr Diolch");
    }
}
