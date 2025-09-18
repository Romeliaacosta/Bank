/**
 * Credit class creates a credit account object with attributes that hold the data in csv file read in 
 * DataBankManager class. It holds methods to make transactions and inquiries about their credit accounts
 * availablet o customers.
 * 
 * @author Romelia Acosta 80599568
 */

public class Credit{
    private String creditAccNumber;
    private double creditMax;
    private double balance;
    private static Account ac;


    public Credit(String creditAccNumber, double creditMax, double balance) {
        this.creditAccNumber = creditAccNumber;
        this.creditMax = creditMax;
        this.balance = balance;
    }
    // Account ac = new Account();

    public void charge(double amount) {
        if (balance + amount <= creditMax) {
            balance += amount;
            TransactionLogger.logCreditCharge(creditAccNumber, amount, balance);
        } else {
            System.out.println("Credit limit exceeded");
        }
    }

    public void pay(double amount) {
        balance -= amount;
        TransactionLogger.logCreditPayment(creditAccNumber, amount, balance);
    }

/**
 * getters for credit account info
 * @return balance
 */
    public double getBalance() {
        return balance;
    }
    public double getCreditMax() {
        return creditMax;
    }
    public String getCreditAccNumber() {
        return creditAccNumber;
    }

/**
 * getters for saving data
 */
    public String getAccountNumber(){
        return ac.accountNumber; 
    }
    

}
