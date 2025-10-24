import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Phone phoneBook = new Phone();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("PhoneBook");
        System.out.println("Commands: all, info number, new number name, delete number, rename number name, exit, help");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "exit":
                    scanner.close();
                    return;
                case "all":
                    showAll();
                    break;
                case "help":
                    helpCommand();
                    break;
                default:
                    processCommand(input);
                    break;
            }
        }
    }

    private static void processCommand(String input) {
        if (input.startsWith("info ")) {
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
        } else {
            System.out.println("Don't know this command");
        }
    }

    private static void showAll() {
        if (phoneBook.isEmpty()) {
            System.out.println("Phonebook is empty :(");
        } else {
            for (Map.Entry<String, Contact> entry : phoneBook.getAllContacts().entrySet()) {
                System.out.println(entry.getValue());
            }
        }
    }

    private static void showInfo(String number) {
        Contact contact = phoneBook.getContact(number);
        if (contact == null) {
            System.out.println("Contact doesn't found :(");
        } else {
            System.out.println(contact.getName());
        }
    }

    private static void addContact(String data) {
        String[] parts = data.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Error: don't have any part of method");
            return;
        }
        String number = parts[0];
        String name = parts[1];

        if (phoneBook.contactExists(number)) {
            Contact existingContact = phoneBook.getContact(number);
            System.out.println("Number is exist: " + existingContact.getName());
            return;
        }

        phoneBook.addContact(number, name);
        System.out.println("Added: " + name);
    }

    private static void deleteCommand(String number) {
        Contact contact = phoneBook.getContact(number);
        if (contact == null) {
            System.out.println("Contact doesn't found");
        } else {
            phoneBook.deleteContact(number);
            System.out.println("Deleted: " + contact.getName());
        }
    }

    private static void renaming(String data) {
        String[] parts = data.split(" ", 2);

        if (parts.length < 2) {
            System.out.println("Error: rename number name");
            return;
        }

        String number = parts[0];
        String newName = parts[1];

        if (!phoneBook.contactExists(number)) {
            System.out.println("Contact doesn't exist");
            return;
        }
        Contact contact = phoneBook.getContact(number);
        String oldName = contact.getName();
        phoneBook.updateContactName(number, newName);
        System.out.println("Renaming success: " + oldName + " -> " + newName);
    }

    private static void helpCommand() {
        System.out.println("Help for u:" +
                "\n1) all: show all users in Phonebook" +
                "\n2) info + number: search and print info about number (for example: info +79991001010 -> name:vlad)" +
                "\n3) new number + name: add key form number:name in Phonebook (for example: new +79991001010 vlad -> added: vlad)" +
                "\n4) delete + number: delete number from Phonebook" +
                "\n5) rename number + name: renaming person (for example: rename +79991001010 egor)" +
                "\n6) exit: exit" +
                "\n7) help: help for u :)");
    }
}