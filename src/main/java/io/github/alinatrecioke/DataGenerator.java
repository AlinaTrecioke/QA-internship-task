package io.github.alinatrecioke;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    static String[] firstNames = {
            "Olivia", "Liam", "Emma", "Noah", "Ava",
            "Sophia", "Jackson", "Isabella", "Aiden", "Mia",
            "Lucas", "Amelia", "Ethan", "Harper", "Mason",
            "Evelyn", "Logan", "Abigail", "James", "Charlotte"
    };

    static String[] lastNames = {
            "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
            "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
            "Thomas", "Taylor", "Moore", "Jackson", "Martin"
    };

    static String[] addresses = {
            "Baker St. 221B", "Champs-Élysées 42", "Friedrichstr. 19",
            "Fifth Ave. 711", "Via Roma 27", "Gran Vía 15",
            "Königsallee 34", "Ginza 4-12", "Yonge St. 250",
            "George St. 85", "Nevsky Prospekt 100", "Rue de Rivoli 98",
            "Oxford St. 400", "Abbey Road 3", "Sunset Blvd. 600",
            "Kurfürstendamm 150", "Nanjing Rd. 88", "Rue Saint-Honoré 73",
            "Orchard Road 45", "Avenida Paulista 123"
    };

    static String[] cities = {
            "New York", "Toronto", "London", "Berlin", "Paris",
            "Rome", "Madrid", "Sydney", "Tokyo", "Seoul",
            "São Paulo", "Mexico City", "Mumbai", "Beijing", "Moscow",
            "Cairo", "Istanbul", "Buenos Aires", "Amsterdam", "Stockholm"
    };

    private static String storedEmail;

    public static String getRandomEmail() {
        storedEmail = RandomStringUtils.randomAlphabetic(5).toLowerCase() + "@test.com";
        return storedEmail;
    }

    public static String getStoredEmail() {
        return storedEmail;
    }

    public static String getRandomFirstName() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(firstNames.length);
        return firstNames[randomIndex];
    }

    public static String getRandomLastName() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(lastNames.length);
        return lastNames[randomIndex];
    }

    public static String getRandomAddress() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(addresses.length);
        return addresses[randomIndex];
    }

    public static String getRandomCity() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(cities.length);
        return cities[randomIndex];
    }

    public static int getRandomPostalCode() {
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }

    public static String getRandomPhoneNumber() {
        Random random = new Random();
        int randomNumber = 10000000 + random.nextInt(90000000);
        return "+370" + randomNumber;
    }
}



