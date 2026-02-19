package util;

import model.ContactPerson;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

   private static final String FILE_PATH = "D:\\BridgeLabz\\RFP\\RFP\\AddressBookSystem\\contacts.txt";

    // Save contacts
    public static void saveToFile(List<ContactPerson> contacts) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (ContactPerson p : contacts) {
                writer.write(
                        p.getFirstName() + "," +
                        p.getLastName() + "," +
                        p.getAddress() + "," +
                        p.getCity() + "," +
                        p.getState() + "," +
                        p.getZip() + "," +
                        p.getPhoneNumber() + "," +
                        p.getEmail()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving contacts.");
        }
    }

    // Load contacts
    public static List<ContactPerson> loadFromFile() {

        List<ContactPerson> contacts = new ArrayList<>();
        System.out.println("Working Directory: " + System.getProperty("user.dir"));


        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                String[] data = line.split(",");
                if (data.length != 8) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                ContactPerson person = new ContactPerson(
                        data[0], data[1], data[2], data[3],
                        data[4], data[5], data[6], data[7]
                );

                contacts.add(person);
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        return contacts;
    }
}
