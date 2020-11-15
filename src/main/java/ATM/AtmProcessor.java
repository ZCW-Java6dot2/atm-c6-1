package ATM;

import java.util.*;

public class AtmProcessor {
    private HashMap<String,User> userInfo;
    private String signedIn;
    private Account acctAccessed;
    private HashMap<Integer, Account> tempExistingAcctMap;
    private HashMap<Integer, String> tempNewAcctMap;
    private Class cls;

    public AtmProcessor(){
    userInfo = new HashMap<String, User>();
    signedIn = "No one is signed in";
    tempExistingAcctMap = new HashMap<Integer, Account>();
    tempNewAcctMap = new HashMap<Integer, String>();
    //===============================Debugging and Preloading==========================================
    Account[] accounts = {new Checking(100.00), new Savings(1000.00)}; //debugging
    userInfo.put("zekaihe", new User("zekai","password", accounts)); //debugging
    }

    public Integer readIntInput(){
        Scanner prompt = new Scanner(System.in);
        Integer output = null;
        try { //checks for integer input
            return prompt.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Please enter valid input");
        }
        return output;
    }

    public String readStrInput(){
        Scanner prompt = new Scanner(System.in);
        return prompt.nextLine();
    }

    public void startScreen(){
        System.out.println("WELCOME TO C6 BANK!\n"+
                "1. Log In\n"+
                "2. Create a New Account");

        while (true){
            Integer userInput = readIntInput();
            try {
            promptLogin(userInput);
            }
            catch(NullPointerException e){
            }
        }
    }

    //used in startScreen()
    public boolean promptLogin(Integer userInput){
        boolean loop= true;
            switch (userInput) {
                case 1:
                    logInScreen();
                case 2:
                    registerScreen();
                default:
                    System.out.println("Please enter a valid option");
                    break;
            }
        return loop;
    }

    public void registerScreen() {
        String username = "";
        String name = "";
        String password;
        System.out.println("NEW USER REGISTRATION");
        boolean loopName = true;
        boolean loopUserName = true;
        while (loopName) {
            name = promptName();
            loopName = newName(name);
        }
        while(loopUserName) {
            username = promptUsername();
            loopUserName = newUsername(username);
        }
        password = promptPassword();
        userInfo.put(username, new User(name,password,new Account[0])); //adds new user to list, no accounts have been created. 3rd arg, account is only there for debugging, remove after
    }

    //used in registerScreen()
    public String promptName() {
        System.out.println("Please Enter Full Name:");
        return readStrInput();
    }

    //used in registerScreen()
    public boolean newName(String name) {
        boolean noExist = true;
        for(User u: userInfo.values()){
            if(u.getName().equals(name)) {
                System.out.println("You are already an account with C6 Bank, please log in");
                logInScreen();
            }
            else{
                noExist = false;
            }
        }
        return noExist;
    }

    // used in registerScreen()
    public boolean newUsername(String username) {
        boolean noExist = true;
            if(!userInfo.containsKey(username)){
                noExist = false;
            }
            else{
                System.out.println("This username has been taken");
            }
        return noExist;
    }

    public void logInScreen() {
        String username;
        String password;
        System.out.println("LOG IN\n");
        boolean loop = true;
        while (loop){
            username = promptUsername();
            password = promptPassword();
            loop = validateUserPass(username, password);
        }
    }

    //used in logInScreen()
    public String promptUsername() {
        System.out.println("Please Enter Username:");
        return readStrInput();
    }

    //used in logInScreen()
    public String promptPassword() {
        System.out.println("Please Enter Password:");
        return readStrInput();
    }

    //used in logInScreen()
    public boolean validateUserPass(String username, String password) {
        boolean notValidated = true;

        if(userInfo.containsKey(username) && userInfo.get(username).getPassword().equals(password)){
                signedIn = username;
                accountScreen();
                notValidated = false;
        }
        else {
            System.out.println("Invalid combination of Username and Password");
        }

        return notValidated;
    }

    public void accountScreen() {
        ArrayList<Account> acctList = new ArrayList<Account>(userInfo.get(signedIn).getAccounts()); //retrieves ONLY existing accounts from user that is signed in
        System.out.println("ACCOUNTS SCREEN\n");
        for(int i = 0; i< acctList.size(); i++){
            System.out.printf("%d. %s\n",i+1,acctList.get(i).getClass().getSimpleName());
            tempExistingAcctMap.put(i+1,acctList.get(i)); //putting into hashmap to recall later in promptAcctScrn
            if(i == acctList.size()-1){ //prints "log out" to the end of menu
                System.out.printf("%d. Log out\n",i+2);
            }
            if(acctList.size()<3 && i == acctList.size()-1){ //prints "Create an account" to the end of menu if user has less than 3 accounts
                System.out.printf("%d. Create an account!\n",i+3);
            }
        }

        while (true){
            Integer userInput = readIntInput();
           try {
                promptAcctScrn(userInput);
           }
           catch(NullPointerException e){
           }
        }
    }

    //used in accountScreen()
    public void promptAcctScrn(Integer userInput) {
        for(Integer k: tempExistingAcctMap.keySet()){
            if(k == userInput){
                acctAccessed = tempExistingAcctMap.get(k);
                acctActionScreen();
            }
            else if(userInput == tempExistingAcctMap.size()+1){
                signedIn = null;
                startScreen();
            }
            else if(userInput == tempExistingAcctMap.size()+2){
                createAcctScreen();
            }
            else{
                //do nothing, the system.out is outside the loop so it doesnt repeat based on # of entries in hashMap
            }
        }
        System.out.println("Please enter a valid option");
    }

    public void acctActionScreen() {
        System.out.println("ACCOUNT OPTIONS\n+" +
                "1. Withdraw\n"+
                "2. Deposit\n"+
                "3. Transfer\n"+
                "4. Check Balance\n"+
                "5. Print Transactions\n"+
                "6. Close Account\n"+
                "7. Accounts Screen\n"+
                "8. Logout");
        while (true){
            Integer userInput = readIntInput();
            try {
                promptAction(userInput);
            }
            catch(NullPointerException e){
            }
        }
    }

    //used in acctActionScreen()
    public void promptAction(Integer userInput) {

    }

    public void createAcctScreen() {
        //checks for accounts user doesnt have
        String[] arrAcctOffered = {"Checking","Savings","Investment"}; //creates an array of offered accounts, did not want to create new objects to compare against
        ArrayList<String> listAcctOffered = new ArrayList<String>(Arrays.asList(arrAcctOffered));
        ArrayList<Account> acctList = new ArrayList<Account>(userInfo.get(signedIn).getAccounts()); //retrieves ONLY existing accounts from user that is signed in
        ArrayList<String> strAcctList = new ArrayList<String>();
        for(Account a: acctList){ //making list of accts user have into strings to compare to accounts offered
            strAcctList.add(a.getClass().getSimpleName());
        }
        listAcctOffered.removeAll(strAcctList);

        System.out.println("CREATE AN ACCOUNT");
        for (int i = 0; i < listAcctOffered.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, listAcctOffered.get(i));
            tempNewAcctMap.put(i+1,listAcctOffered.get(i)); //putting into hashmap to recall later in promptCreateAcctScrn
            if(i == acctList.size()-2){ //prints "log out" to the end of menu
                System.out.printf("%d. Previous Screen\n",i+2);
            }
        }

        while (true){
            Integer userInput = readIntInput();
            try {
                promptCreateAcct(userInput);
            }
            catch(NullPointerException e){
            }
        }
    }

    //used in createAcctScreen()
    public void promptCreateAcct(Integer userInput) {
        String path = "ATM.";
        for(Integer k: tempNewAcctMap.keySet()) {
            if (k == userInput) {
                try {
                    cls = Class.forName(path+tempNewAcctMap.get(k));
                }
                catch(ClassNotFoundException e){
                }
                initDepositScrn();
            }
        }
        System.out.println("Please enter a valid option");
    }

    public void initDepositScrn(){
        System.out.println("How much initial deposit would you like to make?");
        while(true){
            promptDeposit();
        }
    }

    public void promptDeposit() {
        String userInput = readStrInput();
        try {
            if (Double.parseDouble(userInput) >= 0) {
                Double depositAmt = Double.parseDouble(userInput);
                System.out.printf("Congratulations on the new account! Your %.2f has been deposited",depositAmt);
                try{
                    Object obj = cls.newInstance();
                    Account newAccount = (Account)(obj);
                    newAccount.Deposit(depositAmt);
                    userInfo.get(signedIn).openAccount(newAccount);

                }
                catch(InstantiationException | IllegalAccessException e){
                }

                } else {
                System.out.println("Please enter a positive number.");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    //For debugging
    public HashMap<String,User> returnUser(){
        return userInfo;
    }

}




