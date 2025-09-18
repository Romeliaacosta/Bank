/**
 * BankDataManager class manages the customer's and account's data.
 * 
 * This code loads data from Bank Users.csv file, organizes data, 
 * and updates Bank Users.csv
 * 
 * @author Romelia Acosta 80599568
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class BankDataManager {
    private Map<String, Customer> customers;
    private Map<String, Account> accounts;

    public BankDataManager() {
        customers = new HashMap<>();
        accounts = new HashMap<>();
    }


/**
 * Method to load person/customer data from a Bank Users CSV file. 
 * The file should follow the format of the excel sheet heading.
 *
 * @param fileName The name of the CSV file to load data from
 * @throws IOException If an I/O error occurs while reading the file
 */
public void loadData(String fileName) {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header line
                }
                try {
                    String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Split by comma, but not inside quotes
                    for (int i = 0; i < data.length; i++) {
                        data[i] = data[i].trim().replace("\"", ""); // Remove quotes and trim whitespace
                    }
                    createCustomerAndAccounts(data);
                    // Debug print
                    // System.out.println("Loaded customer with ID: " + data[0]); // Debug print
                } catch (Exception e) {
                    System.err.println("Error processing line " + lineNumber + ": " + line);
                    e.printStackTrace();
                }
            }
            // Debug print
            //System.out.println("Total customers loaded: " + customers.size()); // Debug print
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


/**
 * Creates a `Customer` object the 'Customer's' 'Account' which includes:
 * `Checking`, `Saving`, and a `Credit` account
 *
 * @param data A String array containing person/customer information
 */
    private void createCustomerAndAccounts(String[] data) {
        String id = data[0];
        String firstName = data[1];
        String lastName = data[2];
        LocalDate dateOfBirth = LocalDate.parse(data[3], DateTimeFormatter.ofPattern("d-MMM-yy"));
        String address = data[4];
        String phoneNumber = data[5];

        Customer customer = new Customer(id, firstName, lastName, dateOfBirth, address, phoneNumber);
        customers.put(id, customer);

        // Checking Account
        String checkingAccountNumber = data[6];
        double checkingBalance = Double.parseDouble(data[7]);
        Checking checkingAccount = new Checking(checkingAccountNumber, checkingBalance, customer);
        customer.setCheckingAccount(checkingAccount);
        accounts.put(checkingAccountNumber, checkingAccount);

        // Savings Account
        String savingsAccountNumber = data[8];
        double savingsBalance = Double.parseDouble(data[9]);
        Saving savingsAccount = new Saving(savingsAccountNumber, savingsBalance, customer);
        customer.setSavingsAccount(savingsAccount);
        accounts.put(savingsAccountNumber, savingsAccount);

        // Credit Account
        String creditAccNumber = data[10];
        double creditMax = Double.parseDouble(data[11]);
        double creditBalance = Double.parseDouble(data[12]);
        Credit creditAccount = new Credit(creditAccNumber, creditMax, creditBalance);
        customer.setCreditAccount(creditAccount);
    }


/**
 * Gets a customer based on an id provided. The id is retrieved from the account data above.
 *
 * @param id A Customer id
 * @return The Customer. Also prints the customer retrieved.
 */
    public Customer getCustomer(String id) {
        // To Debug Print
        // System.out.println("Searching for customer with ID: " + id); 
        Customer customer = customers.get(id);
        // To Debug Print
        // System.out.println("Found customer: " + (customer != null ? customer.getName() : "null")); // Debug print
        return customer;    }

/**
 * Retrieves an `Account` object from the data using the account number pertaiing to a customer's account.
 *
 * @param accountNumber The account number inputed.
 * @return Found or null
 */

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

/**
 * Method to save customer and account data back to a CSV file.
 *
 * @param fileName Name of the CSV file to save the data to.
 * @throws IOException If an I/O error occurs while writing to the file.
 */
public void saveData(String fileName) throws IOException {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
        bw.write("ID,FirstName,LastName,DOB,Address,PhoneNumber,CheckingAccountNumber,CheckingBalance,SavingsAccountNumber,SavingsBalance,CreditAccountNumber,CreditMax,CreditBalance");
        bw.newLine();

        // Write data for customers: checking, saving, credit.
        //build string of customer information
        for (Customer customer : customers.values()) {
            StringBuilder sb = new StringBuilder();
            sb.append(customer.getId()).append(",");
            sb.append(customer.getFirstName()).append(",");
            sb.append(customer.getLastName()).append(",");
            sb.append(customer.getDateOfBirth().format(DateTimeFormatter.ofPattern("d-MMM-yy"))).append(",");
            sb.append("\"").append(customer.getAddress()).append("\",");
            sb.append(customer.getPhoneNumber()).append(",");
            
            Checking checkingAccount = customer.getCheckingAccount();
            sb.append(checkingAccount.getAccountNumber()).append(",");
            sb.append(String.format("%.2f", checkingAccount.getBalance())).append(",");
            
            Saving savingsAccount = customer.getSavingsAccount();
            sb.append(savingsAccount.getAccountNumber()).append(",");
            sb.append(String.format("%.2f", savingsAccount.getBalance())).append(",");
            
            Credit creditAccount = customer.getCreditAccount();
            sb.append(creditAccount.getAccountNumber()).append(",");
            sb.append(String.format("%.2f", creditAccount.getCreditMax())).append(",");
            sb.append(String.format("%.2f", creditAccount.getBalance()));

            bw.write(sb.toString());
            bw.newLine();
        }
    }
}
/**
 * Method To Test and Print
 * public void printAllCustomerIds() {
    * System.out.println("All customer IDs:");
    * for (String id : customers.keySet()) {
    * System.out.println(id);
    * }
 * }
 */
}
