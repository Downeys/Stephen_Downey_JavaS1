package us.haagen_dazs.domain;

import us.haagen_dazs.util.UniqueID;

public abstract class BusinessUnit {

    protected Address address;
    protected String businessId;
    protected String businessName;
    protected Contact[] contacts = new Contact[5];
    protected String firstDateKnown; //should store the date of the first mutually successful business deal
    protected Invoice[] invoices;

    public BusinessUnit(){};

    public BusinessUnit(String businessName, Contact contact, Address address,
                        String firstDateKnown) {
        this.businessName = businessName;
        this.contacts[0] = contact;
        this.address = address;
        this.firstDateKnown = firstDateKnown;
        this.businessId = UniqueID.generateUniqueID(businessName);
    }

    public Contact[] addContact(String contactName, String phoneNumber, String contactEmail, Address address){
        int ctr = 0;
        while(this.contacts[ctr] == null){
            ctr++;
        }
        this.contacts[ctr] = new Contact(contactName, phoneNumber, contactEmail, address);
        return this.contacts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Contact[] getContacts() {
        return contacts;
    }

    public void setContacts(Contact[] contacts) {
        this.contacts = contacts;
    }

    public String getFirstDateKnown() {
        return firstDateKnown;
    }

    public void setFirstDateKnown(String firstDateKnown) {
        this.firstDateKnown = firstDateKnown;
    }

}
