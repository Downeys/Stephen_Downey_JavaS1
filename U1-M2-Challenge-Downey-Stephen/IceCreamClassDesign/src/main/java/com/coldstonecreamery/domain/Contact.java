package com.coldstonecreamery.domain;


import com.coldstonecreamery.util.UniqueID;

public class Contact {
    private Address addresses;
    private String contactEmail;
    private String contactName;
    private String phoneNumber;
    private String uniqueID;

    public Contact(){};

    public Contact(String contactName, String phoneNumber, String contactEmail, Address addresses) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.contactEmail = contactEmail;
        this.addresses = addresses;
        this.uniqueID = UniqueID.generateUniqueID(contactName);
    }

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

}
