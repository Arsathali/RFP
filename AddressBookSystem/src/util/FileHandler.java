package util;

import model.ContactPerson;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class FileHandler {

   private static final String FILE_PATH = "D:\\BridgeLabz\\RFP\\RFP\\AddressBookSystem\\contacts.txt";
   private static final String CSV_FILE_PATH = "D:\\BridgeLabz\\RFP\\RFP\\AddressBookSystem\\contacts.csv";
    private static final String JSON_FILE_PATH = "D:\\BridgeLabz\\RFP\\RFP\\AddressBookSystem\\contacts.json";
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

    //Reading from csv
    public static List<ContactPerson> readFromCSV() throws IOException, CsvValidationException{


        List<ContactPerson> contacts = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH));

        String[] line;

        while((line=reader.readNext())!=null){
            contacts.add(ContactPerson.fromCSV(line));
        }

        reader.close();
        return contacts;
    }

    //writing to CSV file
    public static void writeToCSV(List<ContactPerson> contacts) throws IOException{

        CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH));

        for(ContactPerson person : contacts){
            writer.writeNext(person.toCSV());
        }

        writer.close();
    }

    public static void writeInJson(List<ContactPerson> contacts){
        
       Gson gson = new Gson();
       FileWriter writer;
       try {

            writer = new FileWriter(JSON_FILE_PATH);
            gson.toJson(contacts, writer);
            writer.close(); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<ContactPerson> readFromJSON() throws Exception {

        Gson gson = new Gson();
        FileReader reader = new FileReader(JSON_FILE_PATH);

        Type listType = new TypeToken<List<ContactPerson>>(){}.getType();
        List<ContactPerson> persons = gson.fromJson(reader, listType);

        reader.close();
        return persons;
    }
}
