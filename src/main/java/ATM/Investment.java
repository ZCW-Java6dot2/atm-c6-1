package ATM;

public class Investment extends Account {

    public Investment(){
    }

    public Investment(Double initDeposit){
        this.balance = initDeposit;
    }
/*
    public boolean withdraw(Double amount){
        this.balance -= amount;
    }

    public void Deposit(Double amount){
        this.balance =+ amount;
    }

    public void Transfer(Object account, Double amount){

    }

    public Double checkBalance(){
        return balance;
    }

    public void printTranHis() {
        //return "hi";
    }

    public boolean checkIfEmpty(){
        return false;
    } */

}
