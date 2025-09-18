/**
 * Checking class estends Account class and creates a checking account object with attributes that hold the data in csv file read in 
 * DataBankManager class. It holds methods to make transactions and inquiries about their checking accounts
 * availablet o customers.
 * 
 * @author Romelia Acosta 80599568
 */

public class Checking extends Account {
    public Checking(String accountNumber, double balance, Customer owner) {
        super(accountNumber, balance, owner);
    }
/**
 * to deposit
 * @param amount
 */
    @Override
    public void deposit(double amount) {
        balance += amount;
        TransactionLogger.logDeposit(owner.getName(), amount, getAccountType(), accountNumber, balance);
    }
/**
 * to withdraw
 * @param amount
 */
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            TransactionLogger.logWithdrawal(owner.getName(), amount, getAccountType(), accountNumber, balance);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    @Override
    protected String getAccountType() {
        return "Checking";
    }

/**
 * getters for saving data
 */
    public String getAccountNumber(){
        return accountNumber; 
    }
    public double getBalance(){
        return balance; 
    }

}
