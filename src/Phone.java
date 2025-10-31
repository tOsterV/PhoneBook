import java.util.*;

public class Phone {
    private String simId;
    private Map<String, Contact> contactsMap;

    public Phone(String simId) {
        this.simId = simId;
        this.contactsMap = new HashMap<>();
    }

    public String getSimId() {
        return simId;
    }

    public void addContact(Contact contact) {
        contactsMap.put(contact.getPhoneNumber(), contact);
    }

    public void addContact(String phoneNumber, String name) {
        contactsMap.put(phoneNumber, new Contact(phoneNumber, name));
    }

    public void deleteContact(String number) {
        contactsMap.remove(number);
    }

    public Contact getContact(String number) {
        return contactsMap.get(number);
    }


    public Contact[] getAllContacts() {
        return contactsMap.values().toArray(new Contact[0]);
    }

    public boolean contactExists(String number) {
        return contactsMap.containsKey(number);
    }

    public void updateContactName(String number, String newName) {
        Contact contact = contactsMap.get(number);
        if (contact != null) {
            contact.setName(newName);
        }
    }
    public boolean isEmpty() {
        return contactsMap.isEmpty();
    }
}