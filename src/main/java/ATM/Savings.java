package ATM;

public class Savings extends Account {

    public Savings(Double initDeposit){
        this.balance = initDeposit;
    }

    public void withdraw(Double amount){
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
    }
}
