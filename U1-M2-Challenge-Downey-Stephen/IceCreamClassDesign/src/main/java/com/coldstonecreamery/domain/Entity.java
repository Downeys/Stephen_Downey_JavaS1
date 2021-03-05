package com.coldstonecreamery.domain;

import com.coldstonecreamery.util.UniqueID;

public abstract class Entity {

    protected Address address;
    protected String businessId;
    protected Contact[] contacts = new Contact[5];
    protected String entityName; //For individuals, I would store name in their Contact object
    protected String firstDateKnown; //should store the date of the first mutually successful business deal

    public Entity(){};

    public Entity(String entityName, Contact contact, Address address,
                  String firstDateKnown) {
        this.entityName = entityName;
        this.contacts[0] = contact;
        this.address = address;
        this.firstDateKnown = firstDateKnown;
        this.businessId = UniqueID.generateUniqueID(entityName);
    }

    public Contact[] addContact(String contactName, String phoneNumber, String contactEmail, Address address){
        int ctr = 0;
        while(this.contacts[ctr] == null){
            ctr++;
        }
        this.contacts[ctr] = new Contact(contactName, phoneNumber, contactEmail, address);
        return this.contacts;
    }

}
