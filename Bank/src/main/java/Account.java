/**
 * Person abstract class is where the Persons are assigned attributes connecting to the data in csv file read in BankDataManager.
 * 
 * @author Romelia Acosta 80599568
 * @author Jesus Munoz

 */

public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected Customer owner;

    public Account(String accountNumber, double balance, Customer owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    public void checkBalance() {
        TransactionLogger.logBalanceInquiry(owner.getName(), getAccountType(), accountNumber, balance);
    }

    protected abstract String getAccountType();

/**
 * Getters and setters for getting balance
 * @return balance
 */
    public double getBalance() {
    return balance;
    }

}
