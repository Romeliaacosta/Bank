/**
 * Person abstract class is where the Persons are assigned attributes connecting to the data in csv file read in BankDataManager.
 * 
 * @author Romelia Acosta 80599568
 */

import java.time.LocalDate;

public abstract class Person {
    protected String id;
    protected String firstName;
    protected String lastName;
    protected LocalDate dateOfBirth;
    protected String address;
    protected String phoneNumber;

    public Person(String id, String firstName, String lastName, LocalDate dateOfBirth, String address, String phoneNumber) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

/**
 * Getters and setters for name of persons
 */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
