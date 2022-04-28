package main;

public class SentimentAnalyzer {
    // Tip: labeled continue can be used when iterating featureSet + convert review to lower-case
	public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords,
			String[] negOpinionWords) {
		int[] featureOpinions = new int[featureSet.length]; // output
 
        // your code ~ you will be invoking getOpinionOnFeature
		if (review == null) {
			return featureOpinions;
		}
		review = review.toLowerCase();
		
		outerloop: for(int i = 0; i < featureSet.length; i++) {
			for (int j = 0; j < featureSet[i].length; j++) {
				int currentOpinion = getOpinionOnFeature(review, featureSet[i][j], posOpinionWords, negOpinionWords);
				if (currentOpinion == 1) {
					featureOpinions[i] = 1;
					continue outerloop;
				} else if (currentOpinion == -1) {
					featureOpinions[i] = -1;
					continue outerloop;
				}
			}	
		}		
 
		return featureOpinions;
	}

	// First invoke checkForWasPhrasePattern and 
	// if it cannot find an opinion only then invoke checkForOpinionFirstPattern
	private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
		
		// your code
		int opinion = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
		if (opinion != 0) {
			return opinion;
		}
		
		opinion = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
		
		return opinion;
	}	

	// Tip: Look at String API doc. Methods like indexOf, length, substring(beginIndex), startsWith can come into play
	// Return 1 if positive opinion found, -1 for negative opinion, 0 for no opinion
	// You can first look for positive opinion. If not found, only then you can look for negative opinion
	private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
		String pattern = feature + " was ";

		// your code
		int index = review.indexOf(pattern);
		
		// pattern not found
		if (index < 0) {
			return 0;
		}
		
		int opinionIndex = index + pattern.length();
		String potentialOpinion = review.substring(opinionIndex);
		
		for(String posOpinion : posOpinionWords) {
			if (potentialOpinion.startsWith(posOpinion)) {
				return 1;
			}
		}
		
		for(String negOpinion : negOpinionWords) {
			if (potentialOpinion.startsWith(negOpinion)) {
				return -1;
			}
		}
		
		return 0; 	
	}
	
	// You can first look for positive opinion. If not found, only then you can look for negative opinion
	private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords,
			String[] negOpinionWords) {
		// Extract sentences as feature might appear multiple times. 
		// split() takes a regular expression and "." is a special character 
		// for regular expression. So, escape it to make it work!!
		String[] sentences = review.split("\\.");
		int opinion = 0;
		
		String pattern = " " + feature;
		
		// your code for processing each sentence. You can return if opinion is found in a sentence (no need to process subsequent ones)
		for (String sentence : sentences) {
			// your code
			int index = sentence.indexOf(pattern);
			
			// pattern not found
			if (index < 0) {
				continue;
			}
			
			String potentialOpinion = sentence.substring(0, index);
			for(String posOpinion : posOpinionWords) {
				if (potentialOpinion.endsWith(posOpinion)) {
					return 1;
				}
			}
		}
		
		for (String sentence : sentences) {
			// your code
			int index = sentence.indexOf(pattern);
			
			// pattern not found
			if (index < 0) {
				continue;
			}
			
			String potentialOpinion = sentence.substring(0, index);
			for(String negOpinion : negOpinionWords) {
				if (potentialOpinion.endsWith(negOpinion)) {
					return -1;
				}
			}
		}


		return opinion;
	}
}
