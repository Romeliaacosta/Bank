/**
 * TransactionLogger class serves to log transactions made in the program by customers.
 * 
 * This class creates a log file by appending the information of each inquiry and transaction completed
 * in the system. It also documents timing of each transaction/inquiry. It does this by appending the Log file.
 * See log file in submitted files.
 * 
 * @author Romelia Acosta 80599568
 */

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

public class TransactionLogger {
    private static final Logger logger = Logger.getLogger(TransactionLogger.class.getName());
    private static final String LOG_FILE = "bank_transactions.log";

    static {
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
 * Logs when a customer has a balance inquiry about an account's balance
 * @param customerName
 * @param accountType
 * @param accountNumber
 * @param balance
 */
    public static void logBalanceInquiry(String customerName, String accountType, String accountNumber, double balance) {
        String logMessage = String.format("%s made a balance inquiry on %s-%s. %s's Balance for %s-%s: $%.2f",
                customerName, accountType, accountNumber, customerName, accountType, accountNumber, balance);
        logger.info(addTimestamp(logMessage));
    }

/**
 * Logs when a customer completes a transfer
 * @param senderName
 * @param receiverName
 * @param amount
 */
    public static void logTransfer(String senderName, String receiverName, double amount, 
                                   String fromAccountType, String fromAccountNumber, double newSenderBalance) {
        String logMessage = String.format("%s paid %s $%.2f from %s-%s. %s's New Balance for %s-%s: $%.2f",
                senderName, receiverName, amount, fromAccountType, fromAccountNumber, 
                senderName, fromAccountType, fromAccountNumber, newSenderBalance);
        logger.info(addTimestamp(logMessage));
    }

/**
 * Logs when a customer completes a deposit
 * @param customerName
 * @param accountType
 * @param accountNumber
 * @param amount
 * @param newBalance
 */
    public static void logDeposit(String customerName, double amount, String accountType, String accountNumber, double newBalance) {
        String logMessage = String.format("%s deposited $%.2f to %s-%s. New Balance: $%.2f",
                customerName, amount, accountType, accountNumber, newBalance);
        logger.info(addTimestamp(logMessage));
    }

/**
 * Logs when a customer completes a withdrawl
 * @param customerName
 * @param accountType
 * @param accountNumber
 * @param amount
 * @param newBalance
 */
    public static void logWithdrawal(String customerName, double amount, String accountType, String accountNumber, double newBalance) {
        String logMessage = String.format("%s withdrew $%.2f from %s-%s. New Balance: $%.2f",
                customerName, amount, accountType, accountNumber, newBalance);
        logger.info(addTimestamp(logMessage));
    }

/**
 * Logs when a customer completes a credit charge
 * @param creditAccountNumber
 * @param amount
 * @param newBalance
 */
    public static void logCreditCharge(String creditAccountNumber, double amount, double newBalance) {
        String logMessage = String.format("Credit charge of $%.2f to account %s. New Balance: $%.2f",
                amount, creditAccountNumber, newBalance);
        logger.info(addTimestamp(logMessage));
    }

/**
 * Logs when a customer completes a credit payment
 * @param creditAccountNumber
 * @param amount
 * @param newBalance
 */
    public static void logCreditPayment(String creditAccountNumber, double amount, double newBalance) {
        String logMessage = String.format("Credit payment of $%.2f to account %s. New Balance: $%.2f",
                amount, creditAccountNumber, newBalance);
        logger.info(addTimestamp(logMessage));
    }

/**
 * Adds a timestamp to the action being logged
 * @param message
 */
    private static String addTimestamp(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter) + " - " + message;
    }
}
