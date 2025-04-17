package com.eventpulse.EventPulse.utils;

import java.io.FileWriter;
import java.io.IOException;

public class GenerateUsersSql {
    public static void main(String[] args) throws IOException {
        String[] firstNames = {
                "John", "Anna", "Michael", "Olena", "David", "Sofia", "James", "Maria", "Robert", "Yulia",
                "William", "Oksana", "Richard", "Nina", "Joseph", "Ivanna", "Thomas", "Iryna", "Charles", "Kateryna",
                "Daniel", "Anastasia", "Matthew", "Tetyana", "Andrew", "Daria", "Anthony", "Alina", "Joshua", "Oleh",
                "Christopher", "Natalia", "Alexander", "Liliya", "Nicholas", "Inna", "Benjamin", "Zoriana", "Samuel", "Lesya",
                "Jonathan", "Vira", "Logan", "Solomiya", "Ryan", "Larysa", "Noah", "Halyna", "Liam", "Bohdana",
                "Elijah", "Veronika", "Mason", "Olesya", "Ethan", "Yevheniya", "Jacob", "Zinaida", "Jayden", "Tamara",
                "Oliver", "Oriana", "Lucas", "Lyubov", "Jackson", "Lina", "Aiden", "Ruslana", "Grayson", "Ivanka",
                "Leo", "Milena", "Sebastian", "Kristina", "Jack", "Ola", "Levi", "Marianna", "Isaac", "Svitlana",
                "Gabriel", "Darina", "Julian", "Uliana", "Mateo", "Viktoria", "Caleb", "Marta", "Nathan", "Yana",
                "Hunter", "Ludmila", "Owen", "Nadiya", "Aaron", "Roksolana", "Henry", "Zlata", "Eli", "Kalyna"
        };

        String[] lastNames = {
                "Smith", "Kovalenko", "Brown", "Shevchenko", "Johnson", "Melnyk", "Taylor", "Petrenko", "Anderson", "Tkachenko",
                "White", "Bondarenko", "Harris", "Kravchenko", "Martin", "Boyko", "Thompson", "Polishchuk", "Garcia", "Lysenko",
                "Martinez", "Kozak", "Robinson", "Shapoval", "Clark", "Hnatyuk", "Rodriguez", "Dovzhenko", "Lewis", "Tymoshenko",
                "Lee", "Pavlenko", "Walker", "Zinchenko", "Hall", "Yurchenko", "Allen", "Mazurenko", "Young", "Romaniuk",
                "Hernandez", "Zakharchenko", "King", "Stepanenko", "Wright", "Vashchenko", "Lopez", "Didukh", "Hill", "Biletskyi",
                "Scott", "Savchenko", "Green", "Moroz", "Adams", "Kuzmenko", "Baker", "Onyshchenko", "Gonzalez", "Kushnir",
                "Nelson", "Soroka", "Carter", "Fedorchuk", "Mitchell", "Nechyporenko", "Perez", "Tkachuk", "Roberts", "Yefimenko",
                "Turner", "Vovk", "Phillips", "Shcherbak", "Campbell", "Prokopenko", "Parker", "Havryliuk", "Evans", "Vernydub",
                "Edwards", "Lukyanenko", "Collins", "Sytnyk", "Stewart", "Zadorozhnyi", "Sanchez", "Hladiy", "Morris", "Chumak",
                "Rogers", "Bezruk", "Reed", "Vozniuk", "Cook", "Baran", "Morgan", "Taran", "Bell", "Kharchenko"
        };


        FileWriter writer = new FileWriter("insert_users.sql");
        writer.write("INSERT INTO USERS (id, username) VALUES\n");

        int id = 1;
        for (int i = 0; i < firstNames.length; i++) {
            for (int j = 0; j < lastNames.length && id <= 5000; j++) {
                String username = firstNames[i] + lastNames[j];
                writer.write("(" + id + ", '" + username + "')");
                if (id < 5000) {
                    writer.write(",");
                }
                writer.write("\n");
                id++;
            }
        }

        while (id <= 5000) {
            String username = firstNames[(id - 1) % firstNames.length] + lastNames[(id - 1) % lastNames.length] + (id / (firstNames.length * lastNames.length) + 1);
            writer.write("(" + id + ", '" + username + "')");
            if (id < 5000) {
                writer.write(",");
            }
            writer.write("\n");
            id++;
        }

        writer.write(";");
        writer.close();
        System.out.println("SQL file generated: insert_users.sql");
    }
}