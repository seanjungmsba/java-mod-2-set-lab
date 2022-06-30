import java.util.Scanner;

class Runner {

    private static String title, genre;
    private static int pageNum; 

    public static void selectOptions(Scanner sc) {

        try {
            System.out.println("Enter 'a' to add, 's' to search by title, 'g' to search by genre, or simply press [ENTER] once to return to the main menu; otherwise, press [ENTER] twice to exit out of the program");
            String userInput = sc.nextLine().trim().toLowerCase();
            if (userInput.equals("a")) {
                System.out.println("(adding book...)");
                addBook(sc);  
            } else if (userInput.equals("s")) {
                System.out.println("(searching book by title...)");
                getTitle(sc);
            } else if (userInput.equals("g")) {
                System.out.println("(searching book by genre...)");
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
            System.out.print("Enter the title of the book you are searching for: ");
            Library.getTitle(sc.nextLine().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getGenre(Scanner sc) {
        try {
            System.out.print("Enter the genre of the book you are searching for: ");
            Library.getGenre(sc.nextLine().trim()); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addBook(Scanner sc) {
        try {
            String[][] bookDescriptions = { // 2D string arrays that contain book descriptions
                {"title", "Book Title: "},
                {"genre","Book Genre: "},
                {"pageNum", "Number of Pages: "}};

            for (String[] description: bookDescriptions) {
                System.out.print(description[1]);
                String input = sc.nextLine();
        
                if (input.equals("")) { // if user simply press ENTER (meaning if the user's input is equal to ""), then simply end the program
                    return;
                }
        
                // assign title, genre, and pageNum based on user inputs
                if (description[0].equals("title")) {
                    title = input;
                } else if (description[0].equals("genre")) {
                    genre = input;
                } else if (description[0].equals("pageNum")) {
                    pageNum = Integer.parseInt(input);
                }
            }
           
            Book book = new Book(title, genre, pageNum); // create a Book object based on user input
            Library.addBook(book); // Add a Book object into a Library class
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}