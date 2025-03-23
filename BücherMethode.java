package com.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class BücherMethode {
    String DATEIPFAD = "/home/silas/Projekte/vscode_projekte/uni_projekte/buecherregal-maven/data/bücher.json";
    Scanner scanner = new Scanner(System.in);    
    List<Book> books;

    public BücherMethode() {
        dataiExist();

        boolean start = true;
        while (start == true) {
            int auswahl = getAuswahl();
            System.out.println("Du hast Option " + auswahl + " gewählt.");
            System.out.println(" ");

            if (auswahl == 1) {
                books = loadBooks();
                
                System.out.println("Neues Buch hinzufügen:");
                System.out.print("Titel: ");
                String titel = scanner.nextLine();
                System.out.print("Autor: ");
                String autor = scanner.nextLine();
                System.out.print("Institution: ");
                String institution = scanner.nextLine();
                System.out.print("Zeitschrift: ");
                String zeitschrift = scanner.nextLine();
                System.out.print("Jahr: ");
                String jahr = scanner.nextLine();
                System.out.print("Bände: ");
                String bände = scanner.nextLine();
                System.out.print("Seiten: ");
                String seiten = scanner.nextLine();
                System.out.print("Verlag: ");
                String verlag = scanner.nextLine();
                System.out.print("Online-Adresse: ");
                String onlineAdresse = scanner.nextLine();

                books.add(new Book(titel, autor, institution, zeitschrift, jahr, bände, seiten, verlag, onlineAdresse));
                saveBooks(books);

                System.out.println("Buch wurde gespeichert.");

            } else if (auswahl == 2) {
                List<Book> books = loadBooks();
        
                for (Book buch : books) {
                    System.out.println("Titel: " + buch.getTitel());
                    System.out.println("Autor: " + buch.getAutor());
                    System.out.println("Institution: " + buch.getInstitution());
                    System.out.println("Zeitschrift: " + buch.getZeitschrift());
                    System.out.println("Jahr: " + buch.getJahr());
                    System.out.println("Bände: " + buch.getBände());
                    System.out.println("Seiten: " + buch.getSeiten());
                    System.out.println("Verlag: " + buch.getVerlag());
                    System.out.println("Online-Adresse: " + buch.getOnlineAdresse());
                    System.out.println("------------------------------------------");
                }
            } else if (auswahl == 3) {
                start = false;
                return;
            }
        }
        scanner.close();
    }

    public void dataiExist() {
        // Datei-Objekt erstellen
        File datei = new File(DATEIPFAD);

        //checked ob bücher.json existiert
        if (datei.exists()) {
            System.out.println(" ");
            System.out.println("Datei gefunden: " + datei.getAbsolutePath());
        } else {
            System.out.println(" ");
            System.out.println("Datei nicht gefunden. Stelle sicher, dass 'buecher.json' im 'data/'-Ordner liegt.");
            return;
        }
        System.out.println(" ");
    }

    public List<Book> loadBooks() {
        try (FileReader reader = new FileReader(DATEIPFAD)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Book>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveBooks(List<Book> books) {
        try (FileWriter writer = new FileWriter(DATEIPFAD)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Gson mit Pretty Printing konfigurieren
            gson.toJson(books, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAuswahl() {
        int choice;
        while (true) {
            System.out.print("Auswahl: Hinzufügen [1], Liste lesen [2], Beenden [3]");
            System.out.print(" ");
            System.out.print("Deine Wahl: ");

            if (scanner.hasNextInt()) { // Prüft, ob eine Zahl eingegeben wurde
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= 3) { // Überprüft, ob die Zahl gültig ist
                    return choice;
                } else {
                    System.out.println("Ungültige Auswahl. Bitte wähle 1, 2 oder 3.");
                }
            } else {
                System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben.");
                scanner.next(); // Ungültige Eingabe verwerfen
            }
        }
    }
    
    class Book {
        String titel;
        String autor;
        String institution;
        String zeitschrift;
        String jahr;
        String bände;
        String seiten;
        String verlag;
        String onlineAdresse;

        public Book(String titel, String autor, String institution, String zeitschrift, String jahr, String bände, String seiten, String verlag, String onlineAdresse) {
            this.titel = titel;
            this.autor = autor;
            this.institution = institution;
            this.zeitschrift = zeitschrift;
            this.jahr = jahr;
            this.bände = bände;
            this.seiten = seiten;
            this.verlag = verlag;
            this.onlineAdresse = onlineAdresse;
        }

        public String getTitel() {
            return titel;
        }

        public String getAutor() {
            return autor;
        }

        public String getInstitution() {
            return institution;
        }

        public String getZeitschrift() {
            return zeitschrift;
        }

        public String getJahr() {
            return jahr;
        }

        public String getBände() {
            return bände;
        }

        public String getSeiten() {
            return seiten;
        }

        public String getVerlag() {
            return verlag;
        }

        public String getOnlineAdresse() {
            return onlineAdresse;
        }
    }
}