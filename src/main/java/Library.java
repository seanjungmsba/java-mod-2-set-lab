import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
            if (booksGenre.get(book.genre) != null) { // if genre of added book already exist in the booksGenre, maintain the booklist
                booklist = booksGenre.get(book.genre);
            } else { // if the genre of the added book is new, then add the book to booklist and its genre and Book object into booksGenre, respectively 
                booklist.add(book); // ArrayList data type used add() to add element - here, book is a Book object
                booksGenre.put(book.genre, booklist); // Map data type used put() to add element - here, book.genre = String; booklist = List<Book>;
            }
    
            System.out.println("Added Book: " + book);
            System.out.println("Repository: " + books);

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
            for ( Map.Entry<String, List<Book>> genreName : booksGenre.entrySet() ) 
                System.out.println("Library has at least one book of the following genre you are looking for: " + genreName.getValue());
        } else {
            System.err.println("Based on genre you entered, book you are looking for does not exist");
        }
    }

}