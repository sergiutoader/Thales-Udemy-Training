package apiresponse;

public class APIResponseParser {
    
    /**
	 * Parses the input text and returns a Book instance containing
	 * the parsed data. 
	 * @param response text to be parsed
	 * @return Book instance containing parsed data
	 */
    public static Book parse(String response) {
       Book book = new Book();
		String endRule = "<";
		
		String startRule = "<title>";		
		String title = parse(response, startRule, endRule); 
	    book.setTitle(title);
	    
	    startRule = "<name>";
	    String author = parse(response, startRule, endRule);
	    book.setAuthor(author);
	    
	    startRule = "<original_publication_year type=\"integer\">";
	    String publicationYear = parse(response, startRule, endRule);
	    book.setPublicationYear(Integer.parseInt(publicationYear));
	    
	    startRule = "<average_rating>";
	    String averageRating = parse(response, startRule, endRule);
	    book.setAverageRating(Double.parseDouble(averageRating));
	    
	    startRule = "<ratings_count type=\"integer\">";
	    String ratingsCountStr = parse(response, startRule, endRule);
	    String[] digitGroups = ratingsCountStr.split(",");
	    
	    StringBuilder sb = new StringBuilder();
	    for (String s : digitGroups) {
	    	sb.append(s);
	    }
	   
	    book.setRatingsCount(Integer.parseInt(sb.toString()));
	    
	    startRule = "<image_url>";
	    String imageUrl = parse(response, startRule, endRule);
	    book.setImageUrl(imageUrl); 
		
		// Your code
		return book;
    }
    
    // write overloaded parse method with the 3 parameters response, startRule, endRule
    private static String parse(String response, String startRule, String endRule) {
		int startRuleIndex = response.indexOf(startRule);
		if (startRuleIndex < 0) {
			return null;
		}
		
		response = response.substring(startRuleIndex + startRule.length());
		
		int endRuleIndex = response.indexOf(endRule);
		if (endRuleIndex < 0) {
			return null;
		}
		
		String data = response.substring(0, endRuleIndex);
		data = data.strip();
		
		response = response.substring(endRuleIndex + endRule.length());
    	
		return data;
	}

    
    
    public static void main(String[] args) {
		String response = "<work>" + 
	                            "<id type=\"integer\">2361393</id>" +
	                            "<books_count type=\"integer\">813</books_count>" +
	                            "<ratings_count type=\"integer\">1,16,315</ratings_count>" + 
	                            "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
	                            "<original_publication_year type=\"integer\">1854</original_publication_year>" +
								"<original_publication_month type=\"integer\" nil=\"true\"/>" +
								"<original_publication_day type=\"integer\" nil=\"true\"/>" +
								"<average_rating>3.79</average_rating>" +
								"<best_book type=\"Book\">" +
									"<id type=\"integer\">16902</id>" +
									"<title>Walden</title>" + 
									"<author>" +
										"<id type=\"integer\">10264</id>" + 
										"<name>Henry David Thoreau</name>" + 
									"</author>" +
									"<image_url>" + 
										"http://images.gr-assets.com/books/1465675526m/16902.jpg" +
									"</image_url>" +
									"<small_image_url>" + 
										"http://images.gr-assets.com/books/1465675526s/16902.jpg" +
									"</small_image_url>" +
								"</best_book>" +
							"</work>";
		
		APIResponseParser.parse(response);
	}
}