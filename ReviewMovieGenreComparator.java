// Assignment: Assignment 7
// Name: Pruthvi Nandan Janga
// StudentID: 1225338491
// Lecture: TU-TH 4:30-5:45
// Description:
import java.util.*;
import java.lang.*;
import java.io.*;
public class ReviewMovieGenreComparator implements Comparator<Movie>{
	public int compare(Movie m1,Movie m2) {
		 if(m1.getMovieGenre()!=m2.getMovieGenre()) {
			 return m1.getMovieGenre().compareTo(m2.getMovieGenre());
		 }
		 else if(m1.getTotalCollections()!=m2.getTotalCollections()){
		 return Integer.compare(m1.getTotalCollections(), m2.getTotalCollections());
		 }
		 else if(m1.getMovieName()!=m2.getMovieName()) {
			 return m1.getMovieName().compareTo(m2.getMovieName());
		 }
		 else if(m1.getDirector()!=m2.getDirector()) {
			 return m1.getDirector().compareTo(m2.getDirector());
		 }
		 else {
			 return m1.getReview().compareTo(m2.getReview());
		 }
	 }
}

