package hska.gassishare.data.database;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import hska.gassishare.data.entity.Dog;
import hska.gassishare.data.entity.User;

public class MockData {

    // Mockdaten für User

    public List<Dog> doggoList = new ArrayList<Dog>();
    public List<User> userList = new ArrayList<User>();
    UUID user1UUID = UUID.fromString("e9f52a8a-1c18-4a20-9c7d-4c4a15708e8a");
    User user1 = new User(user1UUID,
            "herrchen10",
            "Mustermann",
            "Max",
            "pw",
            "max.mustermann@m.de",
            "Ich bin Max",
            76131,
            "Musterstrasse",
            "Karlsruhe");
    UUID user2UUID = UUID.fromString("d6a30b7e-549f-4ef2-86e4-43ebf2ee79e4");
    User user2 = new User(user2UUID,
            "hundeliebhaber",
            "Schmidt",
            "Laura",
            "pass123",
            "laura.schmidt@m.de",
            "Ich liebe Hunde!",
            12345,
            "Hundeallee",
            "Berlin");
    UUID user3UUID = UUID.fromString("5a26c5c7-7994-481c-9bb1-28e68b02b8e5");
    User user3 = new User(user3UUID,
            "katzenfreund",
            "Fischer",
            "Julia",
            "catlover",
            "julia.fischer@m.de",
            "Katzen sind toll!",
            54321,
            "Katzenweg",
            "Hamburg");
    UUID user4UUID = UUID.fromString("32a2f352-93c3-4f81-9e0c-11b4a3bebe68");
    User user4 = new User(user4UUID,
            "tierfreund",
            "Wagner",
            "Anna",
            "animallover",
            "anna.wagner@m.de",
            "Alle Tiere sind meine Freunde!",
            67890,
            "Tiergartenstraße",
            "München");
    UUID user5UUID = UUID.fromString("3c146da6-7e7f-4f6c-bff3-329ff2dbdb0b");
    User user5 = new User(user5UUID,
            "haustierliebhaber",
            "Klein",
            "Sophie",
            "petsrock",
            "sophie.klein@m.de",
            "Haustiere machen das Leben bunter!",
            98765,
            "Haustiergasse",
            "Frankfurt");

    // Mockdaten für Doggos
    UUID user6UUID = UUID.fromString("68a7d1b9-71d3-4c6f-b524-6fd77924ab3b");
    User user6 = new User(user6UUID,
            "hundekatzenbesitzer",
            "Müller",
            "Markus",
            "dogcat123",
            "markus.mueller@m.de",
            "Ich habe sowohl Hunde als auch Katzen!",
            24680,
            "Tierweg",
            "Stuttgart");
    UUID dog1UUID = UUID.fromString("7e4b21f7-3d44-49c3-8558-2c5f830bedc0");
    Dog dog1 = new Dog(dog1UUID,
            user1UUID,
            "Wuffi",
            3,
            "weiblich",
            "Labrador",
            100,
            true,
            "Ein lieber Wuffi");
    UUID dog2UUID = UUID.fromString("8bdf5470-633b-47a1-bd2c-ae68d7e6d0e5");
    Dog dog2 = new Dog(dog2UUID,
            user2UUID,
            "Bello",
            5,
            "männlich",
            "Dackel",
            30,
            false,
            "Ein verspielter Bello");
    UUID dog3UUID = UUID.fromString("be1d5862-0a9d-4962-b235-014a5c1e2da2");
    Dog dog3 = new Dog(dog3UUID,
            user3UUID,
            "Luna",
            2,
            "weiblich",
            "Golden Retriever",
            130,
            true,
            "Eine fröhliche Luna");
    UUID dog4UUID = UUID.fromString("84e1f2b4-3773-44b7-8db4-7006034e2a11");
    Dog dog4 = new Dog(dog4UUID,
            user4UUID,
            "Rocky",
            4,
            "männlich",
            "Bulldogge",
            120,
            true,
            "Ein starker Rocky");
    UUID dog5UUID = UUID.fromString("903abf4d-d9f3-4191-83dd-871f36641d86");
    Dog dog5 = new Dog(dog5UUID,
            user5UUID,
            "Molly",
            6,
            "weiblich",
            "Chihuahua",
            10,
            false,
            "Eine süße Molly");
    UUID dog6UUID = UUID.fromString("f37c97ae-d95e-43d0-ba42-94a2c0422f8b");
    Dog dog6 = new Dog(dog6UUID,
            user6UUID,
            "Max",
            1,
            "männlich",
            "Deutscher Schäferhund",
            140,
            true,
            "Ein energiegeladener Max");

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
