package ATM;

import java.util.*;

public class AtmProcessor {
    private HashMap<String, User> userInfo;
    private String signedIn;
    private Account acctAccessed;
    private HashMap<Integer, Account> tempExistingAcctMap;
    private HashMap<Integer, String> tempNewAcctMap;
    private HashMap<Integer, Account> tempXferAcctMap;
    private Account acctXferTo;
    private Class cls;

    public AtmProcessor() {
        userInfo = new HashMap<String, User>();
        signedIn = "No one is signed in";
        tempExistingAcctMap = new HashMap<Integer, Account>();
        tempNewAcctMap = new HashMap<Integer, String>();
        tempXferAcctMap = new HashMap<Integer, Account>();
        //===============================Debugging and Preloading==========================================
        Account[] account1 = {new Checking(100.00), new Savings(1000.00), new Investment(10000000.00)}; //debugging
        userInfo.put("Obsidian", new User("Zekai", "password", account1)); //debugging
        Account[] account2 = {new Checking(200.00), new Savings(2000.00)}; //debugging
        userInfo.put("Will.I.Am", new User("Will", "password", account2)); //debugging
        Account[] account3 = {new Checking(300.00)}; //debugging
        userInfo.put("BuzzLight", new User("Mike", "password", account3)); //debugging
        Account[] account4 = {}; //debugging
        userInfo.put("Newguy", new User("Nobody", "password", account4)); //debugging
    }

    public Integer readIntInput() { //Test this
        Scanner prompt = new Scanner(System.in);
        Integer output = null;
        try { //checks for integer input
            return prompt.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter valid input");
        }
        return output;
    }

    public String readStrInput() {
        Scanner prompt = new Scanner(System.in);
        return prompt.nextLine();
    }

    public void startScreen() {
        System.out.println("\nWELCOME TO C6 BANK!\n" +
                "=====================\n"+
                "1. Log In\n" +
                "2. Create a New Account");

        while (true) {
            Integer userInput = readIntInput();
            try {
                promptLogin(userInput);
            } catch (NullPointerException e) {
            }
        }
    }

    //used in startScreen()
    public void promptLogin(Integer userInput) {
        switch (userInput) {
            case 1:
                logInScreen();
            case 2:
                registerScreen();
            default:
                System.out.println("Please enter a valid option");
                break;
        }
    }

    public void registerScreen() {
        String username = "";
        String name = "";
        String password;
        System.out.println("NEW USER REGISTRATION\n"+
                "=====================\n");
        boolean loopName = true;
        boolean loopUserName = true;
        while (loopName) {
            name = promptName();
            loopName = newName(name);
        }
        while (loopUserName) {
            username = promptUsername();
            loopUserName = newUsername(username);
        }
        password = promptPassword();
        userInfo.put(username, new User(name, password, new Account[0])); //adds new user to list, no accounts have been created. 3rd arg, account is only there for debugging, remove after
        System.out.println("Thank you for registering! Please log in\n");
        logInScreen();
    }

    //used in registerScreen()
    public String promptName() {
        System.out.println("Please Enter Full Name:");
        return readStrInput();
    }

    //used in registerScreen()
    public boolean newName(String name) { //Test this ???
        boolean noExist = true;
        for (User u : userInfo.values()) {
            if (u.getName().equals(name)) {
                System.out.println("You are already an account with C6 Bank, please log in");
                logInScreen();
            } else {
                noExist = false;
            }
        }
        return noExist;
    }

    // used in registerScreen() //Test this
    public boolean newUsername(String username) {
        boolean noExist = true;
        if (!userInfo.containsKey(username)){
            noExist = false;
        } else {
            System.out.println("This username has been taken");
        }
        return noExist;
    }

    public void logInScreen() {
        String username;
        String password;
        System.out.println("\nLOG IN\n"+
                "=====================");
        boolean loop = true;
        while (loop) {
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

    //used in logInScreen() //Test this ??
    public boolean validateUserPass(String username, String password) {
        boolean notValidated = true;

        if (userInfo.containsKey(username) && userInfo.get(username).getPassword().equals(password)) {
            signedIn = username;
            accountScreen();
            notValidated = false;
        } else {
            System.out.println("Invalid combination of Username and Password");
        }

        return notValidated;
    }

    public void accountScreen() {
        ArrayList<Account> acctList = new ArrayList<Account>(userInfo.get(signedIn).getAccounts()); //retrieves ONLY existing accounts from user that is signed in
        tempExistingAcctMap.clear(); //resets hashmap for reassignment
        System.out.println("\nACCOUNTS SCREEN\n"+
                "=====================");
        for (int i = 0; i < acctList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, acctList.get(i).getClass().getSimpleName());
            tempExistingAcctMap.put(i + 1, acctList.get(i)); //putting into hashmap to recall later in promptAcctScrn
        }
        System.out.printf("%d. Log out\n",acctList.size()+1); //prints "log out" to the end of menu
        if (acctList.size() < 3) { //prints "Create an account" to the end of menu if user has less than 3 accounts
            System.out.printf("%d. Create an account!\n", acctList.size() + 2);
        }

        while (true) {
            Integer userInput = readIntInput();
            try {
                promptAcctScrn(userInput);
            } catch (NullPointerException e) {
            }
        }
    }

    //used in accountScreen()
    public void promptAcctScrn(Integer userInput) {
        for (Integer k : tempExistingAcctMap.keySet()) {
            if (k == userInput) {
                acctAccessed = tempExistingAcctMap.get(k);
                acctActionScreen();
            }
        }
        if (userInput == tempExistingAcctMap.size() + 1) {
            signedIn = null;
            startScreen();
        } else if (userInput == tempExistingAcctMap.size() + 2) {
            createAcctScreen();
        } else {
            //do nothing, the system.out is outside the loop so it doesnt repeat based on # of entries in hashMap
        }
        System.out.println("Please enter a valid option");
    }

    public void acctActionScreen() {
        System.out.println("\nACCOUNT OPTIONS\n" +
                "=====================\n"+
                "1. Withdraw\n" +
                "2. Deposit\n" +
                "3. Transfer\n" +
                "4. Check Balance\n" +
                "5. Print Transactions\n" +
                "6. Close Account\n" +
                "7. Accounts Screen\n" +
                "8. Logout");
        while (true) {
            Integer userInput = readIntInput();
            try {
                promptAction(userInput);
            } catch (NullPointerException e) {
            }
        }
    }

    //used in acctActionScreen()
    public void promptAction(Integer userInput) {
        switch(userInput){
            case 1:
                promptWithdraw();
            case 2:
                promptDeposit();
            case 3:
                transferScreen();
            case 4:
                checkBalScreen();
            case 5:
                printTranScreen();
            case 6:
                closeAcctScreen();
            case 7:
                accountScreen();
            case 8:
                startScreen();
                signedIn = null;
            default:
                System.out.println("Please enter a valid option");
                break;
        }
    }

    public  void promptWithdraw() { //test this
        Double amt = 0.00;
        System.out.println("How much would you like to withdraw?");
        boolean loop = true;
        while(loop) {
            amt = washMoney();
            loop = acctAccessed.withdraw(amt);
        }
        System.out.printf("%.2f withdrawn, take your money!\n",amt);
        acctActionScreen();
    }

    public void promptDeposit() { //test this
        Double amt = 0.00;
        System.out.println("How much would you like to deposit?");
        amt = washMoney();
        acctAccessed.Deposit(amt);
        System.out.printf("%.2f deposited, thank you!\n",amt);
        acctActionScreen();
    }

    public void transferScreen() { //test this?
        ArrayList<Account> acctList = new ArrayList<Account>(userInfo.get(signedIn).getAccounts()); //retrieves ONLY existing accounts from user that is signed in
        ArrayList<Account> strCurrentAcct = new ArrayList<Account>();
        strCurrentAcct.add(acctAccessed);
        acctList.removeAll(strCurrentAcct); //gets all accts to list on menu minus the current accessed account
        tempXferAcctMap.clear();

        if(acctList.size() != 0 ) {
            System.out.println("Account to transfer to:");
            for (int i = 0; i < acctList.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, acctList.get(i).getClass().getSimpleName());
                tempXferAcctMap.put(i + 1, acctList.get(i));
                if (i == acctList.size() - 1) { //prints "cancel transfer" to the end of menu
                    System.out.printf("%d. Cancel Transfer\n", i + 2);
                }
            }
        }
        else{
            System.out.println("There is not another account to transfer to.");
            acctActionScreen();
        }

        while (true) {
            Integer userInput = readIntInput();
            try {
                promptXferScrn(userInput);
            } catch (NullPointerException e) {
            }
        }
    }

    public void promptXferScrn(Integer userInput) {
        for (Integer k : tempXferAcctMap.keySet()) {
            if (k == userInput) {
                acctXferTo = tempXferAcctMap.get(k);
                promptXferAmt();
            } else if (userInput == tempXferAcctMap.size() + 1) {
                acctActionScreen();
            }
        }
    }

    public void promptXferAmt() {
        Double amt = 0.00;
        System.out.println("How much would you like to transfer?");
        boolean loop = true;
        while(loop) {
            amt = washMoney();
            loop = acctAccessed.withdraw(amt); //double check this if amt goes to deposit even if you cant withdraw due to insufficent funds
            acctXferTo.Deposit(amt);
        }
        System.out.printf("Your transfer of %.2f from %s to %s completed\n",amt,acctAccessed.getClass().getSimpleName(),acctXferTo.getClass().getSimpleName());
        acctActionScreen();
    }

    public void checkBalScreen() { //test this
        Double amt = acctAccessed.checkBalance();
        System.out.printf("%.2f in your account\n",amt);
        acctActionScreen();
    }

    public void printTranScreen() { //test this
        acctAccessed.printTranHis();
        acctActionScreen();
    }

    public void closeAcctScreen() { //test this
        //ArrayList<Account> acctList = new ArrayList<Account>(userInfo.get(signedIn).getAccounts());
        boolean acctClosed = userInfo.get(signedIn).closeAccount(acctAccessed);
        if(acctClosed) {
            System.out.printf("Your %s has been closed, have a great day!", acctAccessed.getClass().getSimpleName());
            accountScreen();
        }
        else{
            acctActionScreen();
        }
    }

    public void createAcctScreen() { //test this??
        //checks for accounts user doesnt have
        String[] arrAcctOffered = {"Checking", "Savings", "Investment"}; //creates an array of offered accounts, did not want to create new objects to compare against
        ArrayList<String> listAcctOffered = new ArrayList<String>(Arrays.asList(arrAcctOffered));
        ArrayList<Account> acctList = new ArrayList<Account>(userInfo.get(signedIn).getAccounts()); //retrieves ONLY existing accounts from user that is signed in
        ArrayList<String> strAcctList = new ArrayList<String>();
        for (Account a : acctList) { //making list of accts user have into strings to compare to accounts offered
            strAcctList.add(a.getClass().getSimpleName());
        }
        listAcctOffered.removeAll(strAcctList);

        tempNewAcctMap.clear(); //resets hashmap for reassignment
        System.out.println("CREATE AN ACCOUNT");
        for (int i = 0; i < listAcctOffered.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, listAcctOffered.get(i));
            tempNewAcctMap.put(i + 1, listAcctOffered.get(i)); //putting into hashmap to recall later in promptCreateAcctScrn
        }
        System.out.printf("%d. Previous Screen\n", tempNewAcctMap.size() + 1);//prints "Previous Screen" to the end of menu

        while (true) {
            Integer userInput = readIntInput();
            try {
                promptCreateAcct(userInput);
            } catch (NullPointerException e) {
            }
        }
    }

    //used in createAcctScreen()
    public void promptCreateAcct(Integer userInput) {
        String path = "ATM.";
        for (Integer k : tempNewAcctMap.keySet()) {
            if (k == userInput) {
                try {
                    cls = Class.forName(path + tempNewAcctMap.get(k));
                } catch (ClassNotFoundException e) {
                }
                initDepositScrn();
            }
            if (userInput == tempNewAcctMap.size() + 1) {
                accountScreen();
            }
        }
        System.out.println("Please enter a valid option");
    }

    public void initDepositScrn() {
        System.out.println("How much initial deposit would you like to make?");
        while (true) {
            promptInitDeposit();
        }
    }

    public void promptInitDeposit() {
        String userInput = readStrInput();
        try {
            if (Double.parseDouble(userInput) >= 0) {
                Double depositAmt = Double.parseDouble(userInput);
                System.out.printf("Congratulations on the new account! Your %.2f has been deposited\n", depositAmt);
                try {
                    Object obj = cls.newInstance(); //creates the instance of the account selected by user
                    Account newAccount = (Account) (obj);
                    newAccount.Deposit(depositAmt); //the instance above does not take arguments, used nullary constructor and deposited money separately
                    userInfo.get(signedIn).openAccount(newAccount); //adds the new account to userInfo array list
                    accountScreen();
                } catch (InstantiationException | IllegalAccessException e) {
                }

            } else {
                System.out.println("Please enter a positive number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    public Double washMoney() { //test this
        Double amt = 0.00;
        while(true) {
            String userInput = readStrInput();
            try {
                if (Double.parseDouble(userInput) > 0) {
                    amt = Double.parseDouble(userInput);
                    break;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return amt;
    }

    //For debugging
    public HashMap<String, User> returnUser() {
        return userInfo;
    }
}