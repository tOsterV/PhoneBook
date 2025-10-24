import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private List<Contact> contacts;

    public PhoneBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(String phoneNumber, String name) {
        contacts.add(new Contact(phoneNumber, name));
    }

    public Contact getContact(String phoneNumber) {
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                return contact;
            }
        }
        return null;
    }

    public boolean contactExists(String phoneNumber) {
        return getContact(phoneNumber) != null;
    }

    public void deleteContact(String phoneNumber) {
        Contact contactToRemove = getContact(phoneNumber);
        if (contactToRemove != null) {
            contacts.remove(contactToRemove);
        }
    }

    public void updateContactName(String phoneNumber, String newName) {
        Contact contact = getContact(phoneNumber);
        if (contact != null) {
            contact.setName(newName);
        }
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    public boolean isEmpty() {
        return contacts.isEmpty();
    }
}