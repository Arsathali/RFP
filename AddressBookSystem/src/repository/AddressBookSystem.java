package repository;
import model.ContactPerson;
import service.AddressBook;
import java.util.*;

public class AddressBookSystem {
    
    private Map<String,AddressBook> addressBookSystem = new HashMap<>();

    private Map<String,List<ContactPerson>> cityPersonMap = new HashMap<>();
    private Map<String, List<ContactPerson>> statePersonMap = new HashMap<>();

    public void addAddressBook(String name) {
        if (addressBookSystem.containsKey(name)) {
            System.out.println("Address Book already exists");
            return;
        }
        addressBookSystem.put(name, new AddressBook());
        System.out.println("Address Book created: " + name);
    }

    public AddressBook getAddressBook(String name) {
        return addressBookSystem.get(name);
    }

    //add person to city and state dictionary
    public void addPersonToDictionary(ContactPerson person){

        cityPersonMap
            .computeIfAbsent(person.getCity(), k-> new ArrayList<>())
            .add(person);

        statePersonMap
                .computeIfAbsent(person.getState(), k -> new ArrayList<>())
                .add(person);
    }
    
    public List<ContactPerson> searchPersonsByCity(String city){

       return cityPersonMap
                .getOrDefault(city, List.of())
                .stream()
                .toList();
    }


    public List<ContactPerson> searchPersonsByState(String state){

        return statePersonMap
                .getOrDefault(state, List.of())
                .stream()
                .toList();
    }

    //count persons by city
    public long countPersonsByCity(String city){

       return cityPersonMap
                .getOrDefault(city, List.of())
                .stream()
                .count();
    }

    //count persons by state
    public long countPersonsByState(String state){
        
       return statePersonMap
                .getOrDefault(state, List.of())
                .stream()
                .count();
    }



}

