// Assignment: Assignment 7
// Name: Pruthvi Nandan Janga
// StudentID: 1225338491
// Lecture: TU-TH 4:30-5:45
// Description:You will use the "YoMovies" movie review management system for this assignment. You can contribute a review, list all reviews sorted by stars or movie genre, serialize or deserialize personal reviews

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Assignment7
{
    public static void main(String[] args) {
        // Menu options
        char inputOpt = ' ';
        String inputLine;
        // Movie and Movie Genre information
        String movieName, movieGenre;
        String review = null, director, productionCompany, totalCollection;

        int rating;
        // Output information
        String outFilename, inFilename;
        String outMsg, inMsg;
        // Movie manager
        ReviewManager reviewManager = new ReviewManager();
        // Operation result
        boolean opResult;   
        
        try {
            printMenu();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader stdin = new BufferedReader(isr);

            do {
                System.out.print("\nWhat action would you like to perform?\n");
                inputLine = stdin.readLine().trim();
                if (inputLine.isEmpty()) {
                    continue;
                }
                inputOpt = inputLine.charAt(0);
                inputOpt = Character.toUpperCase(inputOpt);

                switch (inputOpt) {

                    case 'A': // Add a new Movie Review
                        System.out.print("Please enter the movie information:\n");
                        System.out.print("Enter the movie name:\n");
                        movieName = stdin.readLine().trim(); 
                        System.out.print("Enter the review:\n");
                        review = stdin.readLine().trim();
                        System.out.print("Enter the total collection:\n");
                        totalCollection = stdin.readLine().trim();
                        System.out.print("Enter the rating:\n");
                        rating = Integer.parseInt(stdin.readLine().trim());
                        System.out.print("Enter the movie genre:\n");
                        movieGenre = stdin.readLine().trim();
                        System.out.print("Enter the movie's Director:\n");
                        director = stdin.readLine().trim();
                        System.out.print("Enter the movie's production company\n");
                        productionCompany = stdin.readLine().trim();
                        
                        /*********************************************************************
                        * Complete the code by calling the addReview method.                 *
                        * If the review has been added successfully, show                    *
                        * "Movie added to the database!\n" on screen, otherwise "Movie NOT added!\n" *
                        **********************************************************************/
                        boolean addedReview;
                        addedReview= ReviewManager.addReview(movieName,rating,review,totalCollection,movieGenre,director,productionCompany);
                        if(addedReview) {
                        	System.out.println("Movie added to the database!");
                        }
                        else {
                        	System.out.println("Movie NOT added!");
                        }
                    case 'D': // Search for a movie
                        System.out.print("Please enter the Movie name to search:\n");
                        movieName = stdin.readLine().trim();
                        System.out.print("Please enter the movies's director':\n");
                        director = stdin.readLine().trim();
                        
                        /*********************************************************************
                        * Complete the code. If the movie review exists, print            *
                        * "Movie found. Here's the review:\n"                           *
                        * Otherwise, print "Movie not found. Please try again\n"        *
                        **********************************************************************/
                        if(reviewManager.movieExists(movieName, director)==-1) {
                        	System.out.println("Movie not found. Please try again");
                        }
                        else {
                        	System.out.println("Movie found. Here's the review:"+reviewManager.reviewList.get(reviewManager.movieExists(movieName, director)).getReview());
                        }
                        break;
                    case 'E': // Search for a Movie Genre
                        System.out.print("Please enter the movie genre to search:\n");
                        movieGenre = stdin.readLine().trim();
                        
                        /*******************************************************************************
                        * Complete the code. If a movie genre is found, show on the screen how many    *
                        * movies match that genre by printing                                          *
                        * "%s Movies matching %s type were found:\n" followed by the reviews.          *
                        * Otherwise, print "Movie Genre: %s was NOT found\n"                           *
                        ******************************************************************************/   
                        ArrayList<Integer> mm1 = new ArrayList<Integer>();
                        mm1=reviewManager.movieGenreExists(movieGenre);
                        int size1 = mm1.size();
                        if(size1!=0) {
                        	System.out.println(size1+" Movies matching "+movieGenre+" type were found:");
                        	for(int i=0;i<size1;i++) {
                        		System.out.println(reviewManager.getMovie(mm1.get(i)).toString());
                        	}
                        }
                        else {
                        	System.out.println("Movie Genre: "+movieGenre+" was NOT found");
                        }
                        break;
                    case 'L': // List movie's reviews
                        System.out.print("\n" + reviewManager.listReviews() + "\n");
                        break;
                        
                     /******************************************************************************************
                     * Complete the code by adding two cases:                                                  *
                     * case 'N': sorts the movie reviews by rating and prints "sorted by rating\n"        *
                     * case 'P': sorts the movie reviews by movie genre and prints "sorted by genre\n" *
                     ******************************************************************************************/                        
                    case 'N':
                    	reviewManager.sortByRating();
                    	System.out.println("sorted by rating");
                    	break;
                    case 'P':
                    	reviewManager.sortByMovieGenre();
                    	System.out.println("sorted by genre");
                    	break;
                    case 'Q': // Quit
                        break;

                    case 'R': // Remove a review
                        System.out.print("Please enter the name of the movie for which you want the review removed:\n");
                        movieName = stdin.readLine().trim();
                        System.out.print("Please enter the name of the movie's director:\n");
                        director = stdin.readLine().trim();
                        
                        /*******************************************************************************
                        * Complete the code. If a review for a certain movie directed by the given     *
                        * director is found, remove the review and print that it was removed. Otherwise*
                        * print that it was NOT removed.                                               * 
                        *******************************************************************************/
                         if(reviewManager.removeReview(movieName, director)) {
                        	 System.out.println(movieName+", "+director+" was removed");
                         }
                         else {
                        	 System.out.println(movieName+", "+director+" was NOT removed");
                         }
                    case 'T': // Close reviewList
                        reviewManager.closeReviewManager();
                        System.out.print("The movie management system was reset!\n");
                        break;

                    case 'U': // Write movies' names and reviews to a text file
                        System.out.print("Please enter a file name that we will write to:\n");
                        outFilename = stdin.readLine().trim();
                        System.out.print("Please enter the name of the movie:\n");
                        movieName = stdin.readLine().trim();
                        System.out.println("Please enter a review to save locally:\n");
                        review = stdin.readLine().trim();
                        outMsg = movieName + "\n" + review + "\n";
                        
                        /*************************************************************************************
                        * Add a try and catch block to write the string outMsg into the user-specified file  *
                        * Then, print in the screen that the file " is written\n"                            *
                        * In case of an IO Exception, print "Write string inside the file error\n"           *                                                             
                        *************************************************************************************/                    
                        try {
                        	FileWriter f1 = new FileWriter(outFilename);
                        	BufferedWriter b1 = new BufferedWriter(f1);
                        	b1.write(outMsg+"\n");
                        	System.out.println(outFilename+" is written");
                        }
                        catch(IOException e){
                        	System.out.println("Write string inside the file error");
                        }
                        break;
                    case 'V': // Read strings from a text file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();
                        
                        /******************************************************************************************
                        * Add a try and catch block to read from the above text file. Confirm that the file       *
                        * " was read\n" and then print "The contents of the file are:\n" followed by the contents *
                        * In case of an IO Exception, print "Read string from file error\n"                       *  
                        * In case of a file not found exception, print that the file " was not found\n"           *                                                             
                        ******************************************************************************************/ 
                        try {
                        	FileReader fr = new FileReader(inFilename);
                        	BufferedReader br = new BufferedReader(fr);
                        	System.out.println(inFilename+" was read");
                        	System.out.println("The contents of the file are");
                        	String l1 = br.readLine();
                        	while(l1!=null) {
                        		System.out.println(l1);
                        		l1=br.readLine();
                        	}
                        }
                        catch(IOException e) {
                        	System.out.println(inFilename+" was not found");
                        }
                        break;
                    case 'W': // Serialize ReviewManager to a data file
                        System.out.print("Please enter a file name to write:\n");
                        outFilename = stdin.readLine().trim();
                        
                        /****************************************************************************
                        * Add a try and catch block to serialize ReviewManager to a data file.      *
                        * Catch two exceptions and print the corresponding messages on the screen:  *
                        * "Not serializable exception\n"                                            *
                        * "Data file written exception\n"                                           * 
                        ****************************************************************************/                    
                        try {
                        	FileOutputStream r1 = new FileOutputStream(outFilename);
                        	ObjectOutputStream r2 = new ObjectOutputStream(r1);
                        	r2.writeObject(reviewManager);
                        }
                        catch(NotSerializableException e) {
                        	System.out.println("Not serializable exception");
                        }
                        catch(IOException e){
                        	System.out.println("Data file written exception");
                        }
                    case 'X': // Deserialize ReviewManager from a data file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();
                        
                        /*****************************************************************************
                         * Add a try and catch block to deserialize ReviewManager from a data file.  *
                         * Catch three exceptions and print the corresponding messages on the screen:*
                         * "Class not found exception\n"                                             *
                         * "Not serializable exception\n"                                            * 
                         * "Data file read exception\n"                                              *
                         ****************************************************************************/  
                         try {
                        	 FileInputStream m1 = new FileInputStream(inFilename);
                        	 ObjectInputStream o1 = new ObjectInputStream(m1);
                        	 System.out.println(inFilename+" was read");
                        	 reviewManager = (ReviewManager) o1.readObject();
                         }
                         catch(NotSerializableException e){
                        	 System.out.println("Not serializable exception");
                        	 
                         }
                         catch(IOException e) {
                        	 System.out.println("Data file read exception");
                         }
                         catch(ClassNotFoundException e) {
                         System.out.println("Class not found exception");
                         }
                    case '?': // Display help
                        printMenu();
                        break;

                    default:
                        System.out.print("Unknown action\n");
                        break;
                }

            } while (inputOpt != 'Q' || inputLine.length() != 1);
        }
        catch (IOException exception) {
            System.out.print("IO Exception\n");
        }
    }

    public static void printMenu() {
        System.out.println("Welcome to YoMovies! ");
        System.out.println("Find or post reviews for your favorite (and not so favorite) movies.");

        System.out.print("Choice\t\tAction\n" + "------\t\t------\n" + "A\t\tAdd a review\n"
                + "D\t\tSearch for a movie\n" + "E\t\tSearch for a genre\n"
                + "L\t\tList all reviews\n" + "N\t\tSort by stars\n" + "P\t\tSort by genre\n"
                + "Q\t\tQuit\n" + "R\t\tRemove a review\n"
                + "U\t\tAdd personal review to a local file\n" + "V\t\tRetrieve personal review from a local file\n"
                + "W\t\tSave reviews to a file\n"
                + "X\t\tUpload reviews from a file\n"
                + "T\t\t(admin) reset database\n"
                + "?\t\tDisplay Help\n");
    }
   
}

