package ATM;

public class Checking extends Account {

    public Checking(){
    }

    public Checking(Double initDeposit){
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
