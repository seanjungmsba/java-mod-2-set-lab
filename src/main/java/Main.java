import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* Main Class */
public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Runner.selectOptions(sc);
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/* Runner Class */
class Runner {

    private static String title, genre;
    private static int pageNum; 

    public static void showOptions(Scanner sc) {

        System.out.println("Enter 's' to search, 'a' to add, 'g' to search book by genre; otherwise, simply press [ENTER] to exit out of the program");
        try {
            String userInput = sc.nextLine().trim().toLowerCase();
            if (userInput.equals("s")) {
                getTitle(sc);
            } else if (userInput.equals("a")) {
                addBook(sc);  
            } else if (userInput.equals("g")) {
                getGenre(sc);
            } else {
                System.exit(0);
                return;  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getTitle(Scanner sc) {
        try {
            System.out.print("Title: ");
            Library.getTitle(sc.nextLine().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getGenre(Scanner sc) {
        try {
            System.out.print("Genre: ");
            Library.getGenre(sc.nextLine().trim()); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addBook(Scanner sc) {

        try {

            String input; // user input
            String[][] bookDescriptions = { // 2D string arrays that contain book descriptions
                {"title", "Book Title: "},
                {"genre","Book Genre: "},
                {"pageNum", "Number of Pages: "}};
    
            for (String[] description: bookDescriptions) {
                System.out.print(description[1]);
                input = sc.nextLine();
    
                if (input.equals("")) { // if user simply press ENTER (meaning if the user's input is equal to ""), then simply end the program
                    return;
                }
    
                // assign title, genre, and pageNum based on user inputs
                if (description[0] == "title") {
                    title = input;
                } else if (description[0] == "genre") {
                    genre = input;
                } else if (description[0] == "pageNum") {
                    pageNum = Integer.parseInt(input);
                }
    
            }

            Book book = new Book(title, genre, pageNum); // create a Book object based on user input
            Library.addBook(book); // Add a Book object into a Library class

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void selectOptions(Scanner sc) {
        while (true) {
            showOptions(sc);
        }
    }

}

/* Library Class */
class Library {

    static Map<String, Book> books = new HashMap<String, Book>(); // stores information of all the books that user enters
    static Map<String, List<Book>> booksGenre = new HashMap<String, List<Book>>();
    static Set<String> genres = new HashSet<String>(); // Set doesn't allow duplicate values - genres will contain unique genres of the book user enters
    
    public static void addBook(Book book) {

        try {

            if(books.containsKey(book.title)) {
                System.out.println("Book Already Exists");
                return;
            } else {
                books.put(book.title, book); // Map data type used put() to add element
                genres.add(book.genre); // Set data type used add() to add element
            }
        
            List<Book> booklist = new ArrayList<Book>(); 
    
            if(booksGenre.get(book.genre) != null) { // if genre of added book already exist in the booksGenre, maintain the booklist
                booklist = booksGenre.get(book.genre);
            } else { // if the genre of the added book is new, then add the book to booklist and its genre and Book object into booksGenre, respectively 
                booklist.add(book); // ArrayList data type used add() to add element - here, book is a Book object
                booksGenre.put(book.genre, booklist); // Map data type used put() to add element - here, book.genre = String; booklist = List<Book>;
            }
    
            System.out.println("Added Book: " + book);
            System.out.println("Repository: " + books);
            System.out.println("Genre: " + genres);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
 
    // getter method that returns title of the book
    public static void getTitle(String title) {
        try{
            System.out.println("Book you are searching for is found: " + books.get(title));
        } catch (Exception e) {
            System.err.println("Book you are looking for does not exist");
        }
    }
 
    // getter method that returns genre of the book
    public static void getGenre(String genre) {
        if (booksGenre.get(genre) != null) {
            System.out.println("Library has at least one book of the following genre you are looking for: " + booksGenre.get(genre));
        } else {
            System.err.println("Based on genre you entered, book you are looking for does not exist");
        }
    }

}

/* Book Class */
class Book {

    // each book has title, genre, and number of pages
    public String title, genre;
    private int numPages;

    // constructor
    public Book(String title, String genre, int numPages) {
        this.title = title;
        this.genre = genre;
        this.numPages = numPages;
    }

    @Override
    public String toString() {
        return "[Title: " + this.title + " | Genre: " + this.genre + " | Pages: " + this.numPages + "]";
    }

}