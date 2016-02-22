/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.smathukumilli.mp2.model;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author shanmukh
 */
public class Customer {
    
    @Column
    @NotNull
    @Min(value = 1, message = "Customer ID must be within 1 and 99999")
    @Max(value = 99999, message = "Customer ID must be within 10 and 99999")
    private String customerID;
    @NotNull
    @Column
    private String firstname;
    @Column
    @NotNull
    private String lastname;

    /**
     *
     * @param customerID
     * @param firstname
     * @param lastname
     */
    public Customer(String  customerID, String firstname, String lastname) {
        this.customerID= customerID;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     *
     */
    public Customer(){}

    /**
     *
     * @return
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     *
     * @param customerID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     *
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
   
}

