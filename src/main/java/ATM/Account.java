package ATM;

import java.util.ArrayList;

public class Account {
    ArrayList<String> transHistory = new ArrayList<String>();
    Double balance;

    public Account(){
    }

    public void Accounts(Double initDeposit){
        this.balance = initDeposit;
    }

    public void withdraw(Double amount){
        if (amount > 0 && amount < balance){
            this.balance -= amount;
            addTransaction("Withdraw: " + amount);
        } else {
            System.out.print("Error. Insufficient funds");
        }
    }

    public void Deposit(Double amount){
        if (amount > 0){
            this.balance += amount;
            addTransaction("Deposit: " + amount);
        } else {
            System.out.print("Error. Please enter a valid amount.");
        }

    }

    public void Transfer(Object account, Double amount){

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
        for (String s : transHistory){
            System.out.println(s);
        }
    }

    public boolean checkIfEmpty(){
        return false;
    }

}
