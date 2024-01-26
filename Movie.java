// Assignment: Assignment 7
// Name: Pruthvi Nandan Janga
// StudentID: 1225338491
// Lecture: TU-TH 4:30-5:45
// Description:
import java.io.*;
import java.lang.*;
import java.util.*;
public class Movie implements Serializable{
	private static final long serialVersionUID = 205L;
    private String Moviename;
    private int stars;
    private String review;
    private int totalCollection;
    private String director;
    MovieGenre movieGenre = new MovieGenre(Moviename, director);
    public Movie(String Moviename,int stars,String review,int totalCollection,String director,MovieGenre movieGenre) {
    	this.Moviename=Moviename;
    	this.stars=stars;
    	this.review=review;
    	this.totalCollection=totalCollection;
    	this.director=director;
    	this.movieGenre=movieGenre;
    }
    public String getMovieName() {
    	return Moviename;
    }
    public int getStars() {
    	return stars;
    }
    public String getReview() {
    	return review;
    }
    public int getTotalCollections() {
    	return totalCollection;
    }
    public String getDirector() {
    	return director;
    }
    public String getMovieGenre() {
    	return movieGenre.getGenre();
    }
    public String toString() {
    	String str="";
    	for(int x=0;x<stars;x++) {
    		str=str+"*";
    	}
    		String d1="";
    		for(int i=0;i<totalCollection;i++) {
    			d1=d1+"$";
    		}
    	return Moviename+"Movie\n"+str+"\n"+
    		   "Total Collection earned: "+d1+"\n"+
    		   movieGenre+"\n"+"Director: "+director+"\n"+"Review:\t"+review+"\n";
    			
    		
    	
    }
}

