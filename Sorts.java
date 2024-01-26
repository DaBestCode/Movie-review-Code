// Assignment: Assignment 7
// Name: Pruthvi Nandan Janga
// StudentID: 1225338491
// Lecture: TU-TH 4:30-5:45
// Description:
import java.io.*;
import java.util.*;
import java.lang.*;
public class Sorts {
public static void sort(ArrayList<Movie>reviewList,Comparator<Movie>xComparator) {
	int x = reviewList.size();
	int i=0;
	int j=0;
	for(i=0;i<x-1;i++){
		int minimumIndex=i;
		for(j=0;j<x;j++) {
			if(xComparator.compare(reviewList.get(j), reviewList.get(minimumIndex))<0) {
				minimumIndex=j;
			}
			
		}
		if(minimumIndex!=i) {
			Movie art=reviewList.get(i);
			reviewList.set(i,reviewList.get(minimumIndex));
			reviewList.set(minimumIndex, art);
		}
	}
}
}
