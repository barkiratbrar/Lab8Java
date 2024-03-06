package com.example.lab8java;

import java.util.*;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String author;
    private int yearOfPublication;
    private double price;
    private String genre;

    public Book(String title, String author, int yearOfPublication, double price, String genre) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.price = price;
        this.genre = genre;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYearOfPublication() { return yearOfPublication; }
    public double getPrice() { return price; }
    public String getGenre() { return genre; }

    @Override
    public String toString() {
        return title + " by " + author + " (" + yearOfPublication + "), $" + price + ", Genre: " + genre;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();

    public Library() {
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", 1937, 22.90, "Fantasy"));
        books.add(new Book("1984", "George Orwell", 1949, 37.00, "Dystopian"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960, 18.99, "Fiction"));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 15.99, "Classic"));
        books.add(new Book("One Hundred Years of Solitude", "Gabriel García Márquez", 1967, 14.20, "Magical Realism"));
        books.add(new Book("A Passage to India", "E.M. Forster", 1924, 12.99, "Historical Fiction"));
        books.add(new Book("Invisible Man", "Ralph Ellison", 1952, 18.99, "Fiction"));
        books.add(new Book("Anna Karenina", "Leo Tolstoy", 1877, 13.95, "Classic"));
        books.add(new Book("Don Quixote", "Miguel de Cervantes", 1605, 20.00, "Classic"));
        books.add(new Book("Frankenstein", "Mary Shelley", 1818, 9.99, "Horror"));
        books.add(new Book("Dracula", "Bram Stoker", 1897, 10.99, "Gothic Fiction"));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 12.99, "Fiction"));
        books.add(new Book("Pride and Prejudice", "Jane Austen", 1813, 11.99, "Romance"));
        books.add(new Book("Moby-Dick", "Herman Melville", 1851, 17.99, "Adventure"));
        books.add(new Book("The Brothers Karamazov", "Fyodor Dostoevsky", 1880, 14.99, "Philosophical Novel"));
        books.add(new Book("Crime and Punishment", "Fyodor Dostoevsky", 1866, 13.99, "Psychological Fiction"));
        books.add(new Book("The Adventures of Huckleberry Finn", "Mark Twain", 1884, 8.99, "Picaresque Novel"));
        books.add(new Book("Jane Eyre", "Charlotte Brontë", 1847, 9.99, "Gothic Novel"));
        books.add(new Book("War and Peace", "Leo Tolstoy", 1869, 19.99, "Historical Fiction"));

    }


    public List<Book> searchByYear(int year) {
        return books.stream().filter(book -> book.getYearOfPublication() == year).collect(Collectors.toList());
    }

    public List<Book> searchByTitle(String title) {
        return books.stream().filter(book -> book.getTitle().contains(title)).collect(Collectors.toList());
    }

    public List<Book> searchByGenre(String genre) {
        return books.stream().filter(book -> book.getGenre().equalsIgnoreCase(genre)).collect(Collectors.toList());
    }

    public Book mostExpensiveBook() {
        return books.stream().max(Comparator.comparingDouble(Book::getPrice)).orElse(null);
    }

    public Book cheapestBook() {
        return books.stream().min(Comparator.comparingDouble(Book::getPrice)).orElse(null);
    }

    public Book oldestBook() {
        return books.stream().min(Comparator.comparingInt(Book::getYearOfPublication)).orElse(null);
    }

    public List<Book> recentBooks(int years) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return books.stream().filter(book -> book.getYearOfPublication() >= (currentYear - years)).collect(Collectors.toList());
    }

    public void printBooksSortedByAuthor() {
        books.stream().sorted(Comparator.comparing(Book::getAuthor)).forEach(System.out::println);
    }

    public void printBooksSortedByPublicationYear() {
        books.stream().sorted(Comparator.comparingInt(Book::getYearOfPublication)).forEach(System.out::println);
    }

    public String mostPopularGenre() {
        return books.stream().collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("None");
    }

    public int yearWithMostPublishedBooks() {
        return books.stream().collect(Collectors.groupingBy(Book::getYearOfPublication, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(-1);
    }
}

public class Lab8 {
    public static void main(String[] args) {
        Library library = new Library();
        // Testing search by year
        System.out.println("Books published in 1949:");
        library.searchByYear(1949).forEach(System.out::println);

        // Testing most expensive book
        System.out.println("\nMost expensive book:");
        System.out.println(library.mostExpensiveBook());

        // Testing print books sorted by author
        System.out.println("\nBooks sorted by author:");
        library.printBooksSortedByAuthor();

        // Testing the most popular genre
        System.out.println("\nMost popular genre:");
        System.out.println(library.mostPopularGenre());

        // Testing search by title
        System.out.println("\nBooks containing '1984' in their title:");
        library.searchByTitle("1984").forEach(System.out::println);

        // Testing search by genre
        System.out.println("\nFantasy genre books:");
        library.searchByGenre("Fantasy").forEach(System.out::println);

        // Testing cheapest book
        System.out.println("\nCheapest book:");
        System.out.println(library.cheapestBook());

        // Testing oldest book
        System.out.println("\nOldest book:");
        System.out.println(library.oldestBook());

        // Testing recent books (last X years)
        System.out.println("\nBooks published in the last 20 years:");
        library.recentBooks(20).forEach(System.out::println);

        // Testing books sorted by publication year
        System.out.println("\nBooks sorted by publication year:");
        library.printBooksSortedByPublicationYear();
    }
}
