package service;

import java.util.ArrayList;
import java.util.List;

import model.ContactPerson;

public class AddressBook {
    
    private List<ContactPerson> addressBook = new ArrayList<>();
    

    //Adding contact to addressBook
    public boolean addContact(ContactPerson person){

        //stream logic
        boolean isDuplicate = addressBook.stream().anyMatch(existingPerson -> existingPerson.equals(person));

        if(isDuplicate){
            return false;
        }
        addressBook.add(person);
        return true;
    }

    //Edit the contact In addressBook
    public boolean editContact(String fName,ContactPerson updatedData){

        for(ContactPerson person : addressBook){

            if(fName.equalsIgnoreCase(person.getFirstName())){
                person.setAddress(updatedData.getAddress());
                person.setCity(updatedData.getCity());
                person.setState(updatedData.getState());
                person.setZip(updatedData.getZip());
                person.setPhoneNumber(updatedData.getPhoneNumber());
                person.setEmail(updatedData.getEmail());
                return true;
            }
        }
        return false;
    }

    //deleting contact
    public boolean deleteContact(String fName){

        for(ContactPerson person : addressBook){

            if(fName.equalsIgnoreCase(person.getFirstName())){
                addressBook.remove(person);
                return true;
            }
        }
        return false;
    }

    //sorted contactpersons from the address book
    public List<ContactPerson> getContactsSortedByName(){

        return addressBook.stream()
                            .sorted(
                                (p1, p2) -> {
                                        int result = p1.getFirstName().compareToIgnoreCase(p2.getFirstName());
                                        if (result != 0) {
                                            return result;
                                        }
                                        return p1.getLastName()
                                                .compareToIgnoreCase(p2.getLastName());
                                    }
                            )
                            .toList();
    }

      // Sort by City
    public List<ContactPerson> sortByCity() {
        return addressBook.stream()
                .sorted((p1, p2) -> p1.getCity().compareToIgnoreCase(p2.getCity()))
                .toList();
    }

    // Sort by State
    public List<ContactPerson> sortByState() {
        return addressBook.stream()
                .sorted((p1, p2) -> p1.getState().compareToIgnoreCase(p2.getState()))
                .toList();
    }

    // Sort by Zip
    public List<ContactPerson> sortByZip() {
        return addressBook.stream()
                .sorted((p1,p2) -> p1.getZip().compareToIgnoreCase(p2.getZip()))
                .toList();
    }

    public List<ContactPerson> getContacts(){
        return addressBook;
    }

    public void setContacts(List<ContactPerson> contacts){
        this.addressBook = contacts;
    }
}
