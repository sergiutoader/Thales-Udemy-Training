package main;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// You may add underscores to numbers for readability
		long phoneNumber = 744_561_404L; 
		System.out.println(phoneNumber);
		
		// Assignment 2
		 CurrencyConverter cc = new CurrencyConverter();
	     cc.printCurrencies();
	     
	     // Operators
	     System.out.println(9 * 5 - 'a' / 5.0); // 25.6
	     
	     /*
	      * Variable scope exercises:
	      * 	- Q1: illegal
	      * 	- Q2: 0
	      * 	- Q3: illegal
	      * 	- Q4: illegal
	      * 	- Q5: legal
	      * */
	     
	     int freqCount = IRUtil.getFrequencyCount("ab", "abbaaffgdfabsdfasdtfaerabasdfasdfg");
	     System.out.println(freqCount);
	     double freq = IRUtil.termFrequency("ab", "ba ab ab fd ab se");
	     System.out.println(freq);
	     
	     // ********************** //
	     
	    // String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";
		
		String review = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";
		
		String[][] featureSet = { 
		        { "ambiance", "ambience", "atmosphere", "decor" },
				{ "dessert", "ice cream", "desert" }, 
				{ "food" }, 
				{ "soup" },
				{ "service", "management", "waiter", "waitress", "bartender", "staff", "server" } };
		String[] posOpinionWords = { "good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome",
				"delicious" };
		String[] negOpinionWords = { "slow", "bad", "horrible", "awful", "unprofessional", "poor" };

		int[] featureOpinions = SentimentAnalyzer.detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
		System.out.println("Opinions on Features: " + Arrays.toString(featureOpinions));
		
	}

}