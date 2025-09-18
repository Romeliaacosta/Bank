/**
 * RunBank class is the main method of the system. It loops thorugh taking inputs 
 * and printing outputs.
 * 
 * This code loops through the program. It begins by opening up a scanner 
 * and takes user input from the console. It takes input regarding customer information
 * and customer desired actions such as completing a transaction or account inquiries. 
 * It outputs information from the csv file read in BankDataManager.
 * 
 * @author Romelia Acosta 80599568
 */

import java.io.IOException;
import java.util.Scanner;

public class RunBank {
    private static BankDataManager dataManager;
    private static Scanner scanner;

    public static void main(String[] args) {
        dataManager = new BankDataManager();
        dataManager.loadData("Bank Users.csv");

/*To Debug
*dataManager.printAllCustomerIds();
*/

        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHello, Welcome to the El Paso Miners Bank");
            System.out.println("Please select an option: ");
            System.out.println("1. Individual Customer Transaction");
            System.out.println("2. Multiple Customers Transaction");
            System.out.println("3. Exit Bank System");
            System.out.println("type 'EXIT' to leave the bank");
            System.out.print("Enter your choice: ");
    
            String input = scanner.nextLine().trim();
    
            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Thank you for using our El Paso Miners Bank! You are now exiting.");
                saveDataAndExit();
                break;
            }

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        handleIndividualCustomer();
                        break;
                    case 2:
                        handleMultipleCustomer();
                        break;
                    case 3:
                        System.out.println("Thank you for using our El Paso Miners Bank! Saving data and exiting...");
                        saveDataAndExit();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'EXIT'.");
            }
        }
    }

/**
 * Handles the case that user inputs 1. handleIndividualCustomer
 *
 */
    private static void handleIndividualCustomer() {
        scanner.nextLine(); // Consume any leftover newline
        System.out.print("Please enter your customer ID: ");
        String customerId = scanner.nextLine().trim();

/*To Debug
*System.out.println("Entered ID: '" + customerId + "'");
*/
        Customer customer = dataManager.getCustomer(customerId);

/*To Debug and Print
*System.out.println("Found customer: " + (customer != null ? customer.getFirstName() + " " + customer.getLastName() : "null"));
*/
    if (customer == null) {
        System.out.println("Customer not found. Please try again.");
        return;
        }

        System.out.println("Welcome, " + customer.getFirstName() + " " + customer.getLastName());

        while (true) {
            System.out.println("\n1. Inquire balance");
            System.out.println("2. Make a deposit");
            System.out.println("3. Make a withdrawal");
            System.out.println("4. Make a transfer");
            System.out.println("5. Return to main menu");
            System.out.print("Please select an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    inquireBalance(customer);
                    break;
                case 2:
                    makeDeposit(customer);
                    break;
                case 3:
                    makeWithdrawal(customer);
                    break;
                case 4:
                    makeTransfer(customer);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
/**
 * Gets balance data based on customer
 *
 * @param customer A Customer.
 */
    private static void inquireBalance(Customer customer) {
        System.out.println("\nSelect account type:");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        System.out.println("3. Credit");
        System.out.print("Please enter your choice: ");

        int choice = getIntInput();
        switch (choice) {
            case 1:
                customer.getCheckingAccount().checkBalance();
                break;
            case 2:
                customer.getSavingsAccount().checkBalance();
                break;
            case 3:
                System.out.println("Credit balance: $" + customer.getCreditAccount().getBalance());
                break;
            default:
                System.out.println("Invalid account type.");
        }
    }
/**
 * Completes a deposit based on a customer
 *
 * @param customer A Customer.
 */
    private static void makeDeposit(Customer customer) {
        System.out.println("\nSelect account type for deposit:");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        System.out.print("Please enter your choice: ");

        int choice = getIntInput();
        System.out.print("Please enter amount to deposit: $");
        double amount = getDoubleInput();

        switch (choice) {
            case 1:
                customer.getCheckingAccount().deposit(amount);
                break;
            case 2:
                customer.getSavingsAccount().deposit(amount);
                break;
            default:
                System.out.println("Invalid account type.");
        }
    }
/**
 * Completes a withdrawal based on a customer.
 *
 * @param customer A Customer.
 */
    private static void makeWithdrawal(Customer customer) {
        System.out.println("\nSelect account type for withdrawal:");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        System.out.print("Please enter your choice: ");

        int choice = getIntInput();
        System.out.print("Please enter amount to withdraw: $");
        double amount = getDoubleInput();

        switch (choice) {
            case 1:
                customer.getCheckingAccount().withdraw(amount);
                break;
            case 2:
                customer.getSavingsAccount().withdraw(amount);
                break;
            default:
                System.out.println("Invalid account type.");
        }
    }
/**
 * Completes a transfer based on a customer
 *
 * @param customer A Customer.
 */
    private static void makeTransfer(Customer customer) {
        System.out.print("Please enter recipient's account number: ");
        String recipientAccountNumber = scanner.nextLine();
        Account recipientAccount = dataManager.getAccount(recipientAccountNumber);

        if (recipientAccount == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        System.out.println("\nSelect account type to transfer from:");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        System.out.print("Please enter your choice: ");

        int choice = getIntInput();
        System.out.print("Please enter amount to transfer: $");
        double amount = getDoubleInput();

        Account senderAccount;
        switch (choice) {
            case 1:
                senderAccount = customer.getCheckingAccount();
                break;
            case 2:
                senderAccount = customer.getSavingsAccount();
                break;
            default:
                System.out.println("Invalid account type.");
                return;
        }

        if (senderAccount.getBalance() >= amount) {
            senderAccount.withdraw(amount);
            recipientAccount.deposit(amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }
/**
 * Handles the case that user inputs 2. handleMultipleCustomer
 *
 */
    private static void handleMultipleCustomer() {
        System.out.println("\nYou are completing a Multiple Customer Transaction");
        
        // Get sender information
        System.out.print("Please enter sender's customer ID: ");
        scanner.nextLine(); // Consume newline
        String senderCustomerId = scanner.nextLine();
        Customer sender = dataManager.getCustomer(senderCustomerId);
    
        if (sender == null) {
            System.out.println("Sender not found. Please try again.");
            return;
        }
    
/**
 * Get recipient information
 */
        System.out.print("Please enter recipient's customer ID: ");
        String recipientCustomerId = scanner.nextLine();
        Customer recipient = dataManager.getCustomer(recipientCustomerId);
    
        if (recipient == null) {
            System.out.println("Recipient not found. Please try again.");
            return;
        }
    
/**
 * Get sender's account type
 */
        System.out.println("\nSelect sender's account type:");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        System.out.print("Please enter your choice: ");
    
        int senderChoice = getIntInput();
        Account senderAccount;
        switch (senderChoice) {
            case 1:
                senderAccount = sender.getCheckingAccount();
                break;
            case 2:
                senderAccount = sender.getSavingsAccount();
                break;
            default:
                System.out.println("Invalid account type.");
                return;
        }
    
/**
 * Get recipient's account type
 */
        System.out.println("\nSelect recipient's account type:");
        System.out.println("1. Checking Account");
        System.out.println("2. Saving Account ");
        System.out.print("Please enter your choice: ");
    
        int recipientChoice = getIntInput();
        Account recipientAccount;
        switch (recipientChoice) {
            case 1:
                recipientAccount = recipient.getCheckingAccount();
                break;
            case 2:
                recipientAccount = recipient.getSavingsAccount();
                break;
            default:
                System.out.println("Invalid account type.");
                return;
        }
    
/**
 * Get transfer amount
 */
        System.out.print("Please enter amount to transfer: $");
        double amount = getDoubleInput();
    
/**
 * Perform transfer
 */
        if (senderAccount.getBalance() >= amount) {
            senderAccount.withdraw(amount);
            recipientAccount.deposit(amount);
            System.out.println("Transfer successful.");
            System.out.printf("%s transferred $%.2f to %s.\n", 
                sender.getName(), amount, recipient.getName());
            System.out.printf("%s's new balance: $%.2f\n", 
                sender.getName(), senderAccount.getBalance());
            System.out.printf("%s's new balance: $%.2f\n", 
                recipient.getName(), recipientAccount.getBalance());
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    //For updating data
    private static void saveDataAndExit() {
        try {
            dataManager.saveData("Updated_Bank_User.csv");
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
            e.printStackTrace();
        }
        scanner.close();
        System.exit(0);
    }


}
