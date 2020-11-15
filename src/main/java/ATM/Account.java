package ATM;

import com.sun.javafx.binding.StringFormatter;

import java.util.ArrayList;

public class Account {
    ArrayList<String> transHistory = new ArrayList<String>();
    Double balance = 0.0;

    public Account(){
    }

    public Account(Double initDeposit){
        this.balance = initDeposit;
    }

    public boolean withdraw(Double amount){
        boolean withDrawFail = true;
        if (amount > 0 && amount <= balance){
            this.balance -= amount;
            addTransaction(String.format("Withdraw: %.2f",amount)); //used string format to output 2 decimal places - ZH
            withDrawFail = false;
        } else {
            System.out.print("Error. Insufficient funds, enter a smaller amount\n");
            withDrawFail = true;
        }
        return withDrawFail;
    }

    public void Deposit(Double amount){
        //if (amount > 0){
            this.balance += amount;
            addTransaction(String.format("Deposit: %.2f",amount)); //used string format to output 2 decimal places - ZH
        //} else {
            //System.out.print("Error. Please enter a valid amount.");
       // }

    }

    public Double checkBalance(){
        return balance;
    }

    //Added an arrayList to maintain transaction history
    //would have to implement transaction at the end of each account action
    public void addTransaction(String transaction){
        transHistory.add(transaction);
    }

    public void printTranHis() {
        if (transHistory.size()==0){ //add conditional statement for 0 transactions - ZH
            System.out.println("There are no recent transactions");
        }
        else {
        for (String s : transHistory){
                System.out.println(s);
            }
        }
    }

    public boolean checkIfEmpty(){
        if(balance == 0.00) {
            return true;
        }
        else{
            return false;
        }
    }

}
