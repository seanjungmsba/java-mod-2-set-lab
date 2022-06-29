import java.util.Scanner;

class Runner {

    private static String title, genre;
    private static int pageNum; 

    public static void showOptions(Scanner sc) {

        System.out.println("Enter 's' to search, 'a' to add, 'g' to search book by genre, or simply press [ENTER] to exit out of the program");
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