package us.haagen_dazs.domain;

import us.haagen_dazs.util.UniqueID;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(addresses, contact.addresses) && Objects.equals(contactEmail, contact.contactEmail) && Objects.equals(contactName, contact.contactName) && Objects.equals(phoneNumber, contact.phoneNumber) && Objects.equals(uniqueID, contact.uniqueID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addresses, contactEmail, contactName, phoneNumber, uniqueID);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "addresses=" + addresses +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactName='" + contactName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", uniqueID='" + uniqueID + '\'' +
                '}';
    }
}
