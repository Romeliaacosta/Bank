/**
 * Customer class extends Person to take the attribues of account information in csv file read in BankDataMa.
 * 
 * This code consists of a Customer constructor and getters and setters for checking, savings accounts, and credit.
 * 
 * @author Romelia Acosta 80599568
 */

import java.time.LocalDate;

public class Customer extends Person{
    private Checking checkingAccount;
    private Saving savingsAccount;
    private Credit creditAccount;

    public Customer(String id, String firstName, String lastName, LocalDate dateOfBirth, String address, String phoneNumber) {
        super(id, firstName, lastName, dateOfBirth, address, phoneNumber);
    }

/**
 * Getters and setters account data
 */
    public void setCheckingAccount(Checking checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public void setSavingsAccount(Saving savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

    public void setCreditAccount(Credit creditAccount) {
        this.creditAccount = creditAccount;
    }

    public Checking getCheckingAccount() {
        return checkingAccount;
    }

    public Saving getSavingsAccount() {
        return savingsAccount;
    }

    public Credit getCreditAccount() {
        return creditAccount;
    }

    public String getName(){
        return getFirstName() + " " + getLastName();
    }
/**
 * Getters and setters for saving data
 */
    public String getId(){
        return id; }

    public LocalDate getDateOfBirth() {
        return dateOfBirth; }

    public String getAddress(){
        return address; }

    public String getPhoneNumber(){
        return phoneNumber; }

}
