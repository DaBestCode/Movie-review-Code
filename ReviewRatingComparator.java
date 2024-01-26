// Assignment: Assignment 7
// Name: Pruthvi Nandan Janga
// StudentID: 1225338491
// Lecture: TU-TH 4:30-5:45
// Description:
import java.util.*;
import java.io.*;
import java.lang.*;
public class ReviewRatingComparator implements Comparator<Movie>{
 public int compare(Movie m1,Movie m2) {
	 if(m1.getStars()!=m2.getStars()) {
		 return Integer.compare(m1.getStars(),m2.getStars());
	 }
	 int nDiff=m1.getMovieName().compareTo(m2.getMovieName());
	 if(m1.getStars()==m2.getStars()){
		 return nDiff;
	 }
	 else if(nDiff==0){
		 return m1.getDirector().compareTo(m2.getDirector());
	 }
	 else {
		 return m1.getReview().compareTo(m2.getReview());
	 }
	 
 }
}

