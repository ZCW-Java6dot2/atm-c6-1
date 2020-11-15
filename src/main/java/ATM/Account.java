package ATM;

import java.util.ArrayList;

public class Account {
    ArrayList<String> transHistory = new ArrayList<String>();
    Double balance;

    public void Accounts(){
    }

    public void Accounts(Double initDeposit){
        this.balance = initDeposit;
    }

    public void withdraw(Double amount){
        this.balance -= amount;
    }

    public void Deposit(Double amount){
        this.balance += amount;
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
