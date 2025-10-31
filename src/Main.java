import java.util.Scanner;

public class Main {
    private static PhoneDataBase phoneDB = new PhoneDataBase();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("PhoneBook");
        System.out.println("Current SIM: " + phoneDB.getCurrentSimId());
        System.out.println("Commands: all, info number, new number name, delete number, rename number name, change simID, exit, help");

        while (true) {
            System.out.print("SIM " + phoneDB.getCurrentSimId() + "> ");
            String input = scanner.nextLine().trim();

            if (input.equals("exit")) {
                break;
            }
            processCommand(input);
        }
        scanner.close();
    }

    private static void processCommand(String input) {
        if (input.equals("all")) {
            showAll();
        } else if (input.equals("help")) {
            helpCommand();
        } else if (input.startsWith("info ")) {
            String number = input.substring(5).trim();
            showInfo(number);
        } else if (input.startsWith("new ")) {
            String data = input.substring(4).trim();
            addContact(data);
        } else if (input.startsWith("delete ")) {
            String number = input.substring(7).trim();
            deleteCommand(number);
        } else if (input.startsWith("rename ")) {
            String data = input.substring(7).trim();
            renaming(data);
        } else if (input.startsWith("change ")) {
            String simID = input.substring(7).trim();
            phoneDB.changeSIM(simID);
        } else {
            System.out.println("Don't know this command");
        }
    }

    private static void showAll() {
        Phone currentPhone = phoneDB.getCurrentPhone();
        if (currentPhone.isEmpty()) {
            System.out.println("Phonebook is empty :(");
        } else {
            for (Contact contact : currentPhone.getAllContacts()) {
                System.out.println(contact);
            }
        }
    }

    private static void showInfo(String number) {
        Phone currentPhone = phoneDB.getCurrentPhone();
        Contact contact = currentPhone.getContact(number);
        if (contact == null) {
            System.out.println("Contact doesn't found :(");
        } else {
            System.out.println(contact.getName());
        }
    }

    private static void addContact(String data) {
        Phone currentPhone = phoneDB.getCurrentPhone();
        String[] parts = data.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Error: don't have any part of method");
            return;
        }
        String number = parts[0];
        String name = parts[1];

        if (currentPhone.contactExists(number)) {
            Contact existingContact = currentPhone.getContact(number);
            System.out.println("Number is exist: " + existingContact.getName());
            return;
        }

        currentPhone.addContact(number, name);
        System.out.println("Added to SIM " + phoneDB.getCurrentSimId() + ": " + name);
    }

    private static void deleteCommand(String number) {
        Phone currentPhone = phoneDB.getCurrentPhone();
        Contact contact = currentPhone.getContact(number);
        if (contact == null) {
            System.out.println("Contact doesn't found");
        } else {
            currentPhone.deleteContact(number);
            System.out.println("Deleted from SIM " + phoneDB.getCurrentSimId() + ": " + contact.getName());
        }
    }

    private static void renaming(String data) {
        Phone currentPhone = phoneDB.getCurrentPhone();
        String[] parts = data.split(" ", 2);

        if (parts.length < 2) {
            System.out.println("Error: rename number name");
            return;
        }

        String number = parts[0];
        String newName = parts[1];

        if (!currentPhone.contactExists(number)) {
            System.out.println("Contact doesn't exist");
            return;
        }

        Contact contact = currentPhone.getContact(number);
        String oldName = contact.getName();
        currentPhone.updateContactName(number, newName);
        System.out.println("Renaming success on SIM " + phoneDB.getCurrentSimId() + ": " + oldName + " -> " + newName);
    }

    private static void helpCommand() {
        System.out.println("Help for u:" +
                "\n1) all: show all users in current SIM" +
                "\n2) info + number: search and print info about number" +
                "\n3) new number + name: add contact to current SIM" +
                "\n4) delete + number: delete number from current SIM" +
                "\n5) rename number + name: renaming person" +
                "\n6) change simID: switch to another SIM (1, 2, 3)" +
                "\n7) exit: exit" +
                "\n8) help: help for u :)");
    }
}