// Assignment: Assignment 7
// Name: Pruthvi Nandan Janga
// StudentID: 1225338491
// Lecture: TU-TH 4:30-5:45
// Description: The reviewList Constructor will be created and instantiated by this constructor

import java.io.Serializable;
import java.util.ArrayList;

public class ReviewManager implements Serializable {
    // The serialVersionUID is used to verify compatibility of senders and
    // receivers. See the document for more details:
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html
    private static final long serialVersionUID = 205L;

    static ArrayList<Movie> reviewList;

    public ReviewManager() {
        reviewList = new ArrayList<>();
    }

    /**
     * Add a Movie object to the reviewList and return true if such an object
     * is added successfully. Otherwise, return false. Two Movies are
     * considered duplicated if they have exactly the same movie name and genre.
     * 
     * @param  movieName          the name of the movie
     * @param  stars              the number of stars the movie recieved
     * @param  review             the movie review
     * @param  totalCollection    the integer total collection earned by the movie
     * @param  genre              the movie's genre
     * @param  director           the movie's director
     * @param  prodictionCompany  production comapny of the movie
     * @return                    true if the operation is successful; false otherwise
     */
    
    //Adds a movie review to the reviewList
    public static boolean addReview(String movieName, int stars, String review, String totalCollection, String genre, String director, String productionCompany) {
        if (movieExists(movieName, director) == -1) {
            int collection = totalCollection.length();
            MovieGenre newGenre = new MovieGenre(genre, productionCompany);
            Movie newMovie = new Movie(movieName, stars, review, collection, director, newGenre);
            reviewList.add(newMovie);
            return true;
        }
        return false;
    }
    public static int movieExists(String movieName,String director) {
    	for(int n=0;n<reviewList.size();n++) {
    	if(movieName.equals(reviewList.get(n).getMovieName())&&(director.equals(reviewList.get(n).getDirector()))) {
    		return n;
    	}
    	}
    	return -1;
    }
    public ArrayList<Integer> movieGenreExists(String genre){
    	ArrayList<Integer> gn1=new ArrayList<Integer>();
    	for(int j=0;j<reviewList.size();j++) {
    		if(genre.equals(reviewList.get(j).getMovieGenre())) {
    			gn1.add(j);
    		}
    	}
		return gn1;
    }
    public Movie getMovie(int n1) {
    	return reviewList.get(n1);
    }
    public boolean removeReview(String movieName,String director) {
    	if(movieExists(movieName,director)==-1) {
    		return false;
    	}
    	else {
    		reviewList.remove(movieExists(movieName,director));
    		return true;
    	}
    }
    public void sortByRating() {
    	ReviewRatingComparator c1=new ReviewRatingComparator();
    	Sorts.sort(reviewList,c1);
    }
    public void sortByMovieGenre() {
    	ReviewMovieGenreComparator c2 = new ReviewMovieGenreComparator();
    	Sorts.sort(reviewList,c2);
    }
    public void closeReviewManager() {
    	reviewList.clear();
    }
    public String listReviews() {
    	String rl="";
    	for(int i=0;i<reviewList.size();i++) {
    		rl = rl+reviewList.get(i)+"\n";
    	}
    return rl;}
}