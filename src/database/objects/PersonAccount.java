/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.objects;

/**
 *
 * @author onairo
 */
public class PersonAccount {
    private String accontID;
    private String email;
    private String hash;
    private String salt;
    private String personID;
    
    public PersonAccount() {}
    public PersonAccount(String accountID , String email, String hash, String salt, String personID) {
        this.accontID = accountID;
        this.email = email;
        this.hash = hash;
        this.salt = salt;
        this.personID = personID;
    }
    
    //Getters
    public String getAccountID() {
        return this.accontID;
    }
    public String getEmail() {
        return this.email;
    }
    public String getHash() {
        return this.hash;
    }
    public String getSalt() {
        return this.salt;
    }
    public String getPersonID() {
        return this.personID;
    }
}
