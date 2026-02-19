
import model.ContactPerson;
import repository.AddressBookSystem;
import service.AddressBook;
import util.FileHandler;
import java.util.Scanner;

public class AddressBookMain {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        AddressBookSystem system = new AddressBookSystem();
        char addMore;
        
        do {
            System.out.println("Enter Address Book Name:");
            String bookName = scanner.nextLine();

            system.addAddressBook(bookName);
            AddressBook addressBook = system.getAddressBook(bookName);

            var load = FileHandler.loadFromFile();
            addressBook.setContacts(load);
            System.out.println(load.size());

            int choice;
            do {
                System.out.println("\n1.Add Contact");
                System.out.println("2.Edit Contact");
                System.out.println("3.Delete Contact");
                System.out.println("4.Search by City");
                System.out.println("5.Search by State");
                System.out.println("6.Count Persons in city");
                System.out.println("7.Count Persons  in state");
                System.out.println("8.Sort And Display");
                System.out.println("9.Sort by city");
                System.out.println("10.Sort And state");
                System.out.println("11.Sort And zip code");
                System.out.println("12.Exit");
                System.out.print("Enter choice: ");

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addContact(addressBook,system);
                    case 2 -> editContact(addressBook);
                    case 3 -> deleteContact(addressBook);
                    case 4 -> searchByCity(system);
                    case 5 -> searchByState(system);
                    case 6 -> countByCity(system);
                    case 7 -> countByState(system);
                    case 8 -> sortAndDisplay(addressBook);
                    case 9 -> sortByCity(addressBook);
                    case 10-> sortByState(addressBook);
                    case 11-> sortByZip(addressBook);
                    case 12-> { 
                        FileHandler.saveToFile(addressBook.getContacts());
                        System.out.println("Contacts saved to file");
                    }
                    default -> System.out.println("Invalid choice");
                }

            } while (choice != 12);

            System.out.print("Add another Address Book? (Y/N): ");
            addMore = scanner.nextLine().charAt(0);

        } while (addMore == 'Y' || addMore == 'y');
    }

  
    private static void addContact(AddressBook addressBook,AddressBookSystem system) {
        System.out.print("First Name: ");
        String f = scanner.nextLine();
        System.out.print("Last Name: ");
        String l = scanner.nextLine();
        System.out.print("Address: ");
        String a = scanner.nextLine();
        System.out.print("City: ");
        String c = scanner.nextLine();
        System.out.print("State: ");
        String s = scanner.nextLine();
        System.out.print("Zip: ");
        String z = scanner.nextLine();
        System.out.print("Phone: ");
        String p = scanner.nextLine();
        System.out.print("Email: ");
        String e = scanner.nextLine();

        ContactPerson contactPerson = new ContactPerson(f, l, a, c, s, z, p, e);
        boolean added = addressBook.addContact(contactPerson);

        if(added){
            system.addPersonToDictionary(contactPerson);
            System.out.println("Contact Added Successfully");
        }else{
            System.out.println("Duplicate Contact. Entry not allowed.");
        }
    }

    private static void editContact(AddressBook addressBook) {
        System.out.print("Enter First Name to edit: ");
        String name = scanner.nextLine();

        System.out.print("New Address: ");
        String a = scanner.nextLine();
        System.out.print("New City: ");
        String c = scanner.nextLine();
        System.out.print("New State: ");
        String s = scanner.nextLine();
        System.out.print("New Zip: ");
        String z = scanner.nextLine();
        System.out.print("New Phone: ");
        String p = scanner.nextLine();
        System.out.print("New Email: ");
        String e = scanner.nextLine();

        boolean updated = addressBook.editContact(
                name, new ContactPerson(null, null, a, c, s, z, p, e));

        System.out.println(updated ? "Contact Updated" : "Contact Not Found");
    }

    private static void deleteContact(AddressBook addressBook) {
        System.out.print("Enter First Name to delete: ");
        String name = scanner.nextLine();
        System.out.println(addressBook.deleteContact(name)
                ? "Contact Deleted"
                : "Contact Not Found");
    }

    private static void searchByCity(AddressBookSystem system) {
        System.out.print("Enter City: "); 
        String city = scanner.nextLine();
        system.searchPersonsByCity(city)
                .forEach(p -> System.out.println(
                        p.getFirstName() + " " + p.getLastName()
                                + " | " + p.getCity() + " | " + p.getState()));
    }

    private static void searchByState(AddressBookSystem system) {
        System.out.print("Enter State: ");
        String state = scanner.nextLine();
        system.searchPersonsByState(state)
                .forEach(p -> System.out.println(
                        p.getFirstName() + " " + p.getLastName()
                                + " | " + p.getCity() + " | " + p.getState()));
    }

      private static void countByCity(AddressBookSystem system) {
      
        System.out.print("Enter City: ");
        String city = scanner.nextLine();
        long cityPersonsCount = system.countPersonsByCity(city);
        System.out.println("Persons in "+city +" is: " + cityPersonsCount);
    }

    private static void countByState(AddressBookSystem system) {
        System.out.print("Enter State: ");
        String state = scanner.nextLine();
        long statePersonsCount = system.countPersonsByState(state);
        System.out.println("Persons in "+state +" is: " + statePersonsCount);

    }

    private static void sortAndDisplay(AddressBook book) {
        book.getContactsSortedByName()
                    .forEach(System.out::println);
    }

    private static void sortByCity(AddressBook addressBook) {
        addressBook.sortByCity().forEach(System.out::println);
    }

    private static void sortByState(AddressBook addressBook) {
        addressBook.sortByState().forEach(System.out::println);
    }

    private static void sortByZip(AddressBook addressBook) {
        addressBook.sortByZip().forEach(System.out::println);
    }

}
