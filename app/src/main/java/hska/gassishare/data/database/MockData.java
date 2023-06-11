package hska.gassishare.data.database;

import java.util.ArrayList;
import java.util.List;

import hska.gassishare.data.entity.Dog;
import hska.gassishare.data.entity.User;

public class MockData {

    //Mockdaten fuer User

    User user1 = new User(100,
            "herrchen10",
            "Mustermann",
            "Max",
            "pw",
            "max.mustermann@m.de",
            "Ich bin Max",
            76131,
            "Musterstrasse",
            "Karlsruhe");

    User user2 = new User(101,
            "hundeliebhaber",
            "Schmidt",
            "Laura",
            "pass123",
            "laura.schmidt@m.de",
            "Ich liebe Hunde!",
            12345,
            "Hundeallee",
            "Berlin");

    User user3 = new User(102,
            "katzenfreund",
            "Fischer",
            "Julia",
            "catlover",
            "julia.fischer@m.de",
            "Katzen sind toll!",
            54321,
            "Katzenweg",
            "Hamburg");

    User user4 = new User(103,
            "tierfreund",
            "Wagner",
            "Anna",
            "animallover",
            "anna.wagner@m.de",
            "Alle Tiere sind meine Freunde!",
            67890,
            "Tiergartenstraße",
            "München");

    User user5 = new User(104,
            "haustierliebhaber",
            "Klein",
            "Sophie",
            "petsrock",
            "sophie.klein@m.de",
            "Haustiere machen das Leben bunter!",
            98765,
            "Haustiergasse",
            "Frankfurt");

    User user6 = new User(105,
            "hundekatzenbesitzer",
            "Müller",
            "Markus",
            "dogcat123",
            "markus.mueller@m.de",
            "Ich habe sowohl Hunde als auch Katzen!",
            24680,
            "Tierweg",
            "Stuttgart");

    // Mockdaten fuer Doggos

    Dog dog1 = new Dog(1,
            100,
            "Wuffi",
            3,
            "weiblich",
            "Labrador",
            100,
            true,
            "Ein lieber Wuffi");

    Dog dog2 = new Dog(2,
            101,
            "Bello",
            5,
            "männlich",
            "Dackel",
            30,
            false,
            "Ein verspielter Bello");

    Dog dog3 = new Dog(3,
            102,
            "Luna",
            2,
            "weiblich",
            "Golden Retriever",
            130,
            true,
            "Eine fröhliche Luna");

    Dog dog4 = new Dog(4,
            103,
            "Rocky",
            4,
            "männlich",
            "Bulldogge",
            120,
            true,
            "Ein starker Rocky");

    Dog dog5 = new Dog(5,
            104,
            "Molly",
            6,
            "weiblich",
            "Chihuahua",
            10,
            false,
            "Eine süße Molly");

    Dog dog6 = new Dog(6,
            105,
            "Max",
            1,
            "männlich",
            "Deutscher Schäferhund",
            140,
            true,
            "Ein energiegeladener Max");

    public List<Dog> doggoList = new ArrayList<Dog>();
    public List<User> userList = new ArrayList<User>();

    public MockData() {
        doggoList.add(dog1);
        doggoList.add(dog2);
        doggoList.add(dog3);
        doggoList.add(dog4);
        doggoList.add(dog5);
        doggoList.add(dog6);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);

    }
}
